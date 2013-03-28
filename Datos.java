/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;


public class Datos implements Serializable {	//	Singleton

	private static final long serialVersionUID = 3L;
	private File archivoKey;
	private BufferedReader kFile = null;
	private List<String> fLista = new ArrayList<String>();
	private Coder coder;
	private boolean existKeyStores = false;
	
	private Datos()
	{
		Preferencias prefs = Preferencias.getInstancia();
		
		coder = new Coder(prefs.Pref(Defines.PREF_CLAVE));
		try {
			archivoKey = new File(prefs.resDir() + Defines.KEYSTORE_DATAFILE);
			if (!archivoKey.exists())
					archivoKey.createNewFile();
			purgaKeyStores();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e1.getMessage(), false);
		} catch (InvalidAlgorithmParameterException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e1.getMessage(), false);
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e1.getMessage(), false);
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e1.getMessage(), false);
		} catch (ShortBufferException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e1.getMessage(), false);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e1.getMessage(), false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e1.getMessage(), false);
		}
	}
	
	private static class DatosSingletonContainer {
        public static final Datos INSTANCIA = new Datos();
    }
	 
    public static Datos getInstancia() {
        return DatosSingletonContainer.INSTANCIA;
    }
	
    public void guardaDatos(String fname, String ktipo, String key, String alias) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, ShortBufferException, BadPaddingException, ClassNotFoundException
	{
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(archivoKey, true);
			fw.write(coder.encrypt(fname) + Defines.KEYSTORE_CTRLCHAR);
			fw.write(coder.encrypt(ktipo) + Defines.KEYSTORE_CTRLCHAR);
			fw.write(coder.encrypt(key) + Defines.KEYSTORE_CTRLCHAR);
			fw.write(coder.encrypt(alias) + Defines.KEYSTORE_CTRLCHAR);
			fw.write("\n");
			fw.close();
			existKeyStores = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e.getMessage(), false);
		} finally {
            try {
                fw.close();
            } catch (IOException ex) {
    			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + ex.getMessage(), false);
            }
        }
	}
	
	public boolean hayDatos() throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException
	{
		boolean ok = false;
		
		fLista.clear();
		try {
			if (kFile == null)
				kFile = new BufferedReader(new FileReader(archivoKey));
			String linea = kFile.readLine();
			ok = (linea != null);
			if (ok)
			{
				String[] l = linea.split(Defines.KEYSTORE_CTRLCHAR);
				for (int i = 0; i < l.length; i++)
					fLista.add((String) coder.decrypt(l[i]));
			}
			else
			{
				kFile.close();
				kFile = null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e.getMessage(), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(Defines.KEYSTORE_DATAFILE + "\n" + e.getMessage(), false);
		}
		return ok;
	}
	
	private void purgaKeyStores() throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, IOException, ShortBufferException
	{
		List<String> l;
		Preferencias prefs = Preferencias.getInstancia();
		FileWriter fw = null;
		
		if (hayDatos())
		{
			existKeyStores = true;
			File f = new File(prefs.resDir() + Defines.KEYSTORE_TEMP);
			f.createNewFile();
			fw = new FileWriter(f, true);
			do
			{
				l = leeDatos();
				if (prefs.existKeyStore(l.get(0)))
				{
					fw.write(coder.encrypt(l.get(0)) + Defines.KEYSTORE_CTRLCHAR);
					fw.write(coder.encrypt(l.get(1)) + Defines.KEYSTORE_CTRLCHAR);
					fw.write(coder.encrypt(l.get(2)) + Defines.KEYSTORE_CTRLCHAR);
					fw.write(coder.encrypt(l.get(3)) + Defines.KEYSTORE_CTRLCHAR);
					fw.write("\n");
				}
			} while (hayDatos());
			fw.close();
			archivoKey.delete();
			f.renameTo(archivoKey);
		}
	}
	
	public boolean hayKeyStores()
	{
		return existKeyStores;
	}
	
	public List<String> leeDatos()
	{
		return fLista;
	}
	
}
