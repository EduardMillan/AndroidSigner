
/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;


public class JarSigner {
	
	private Datos fDatos = Datos.getInstancia();
	private Table tabla;
	private int idxSelected = -1;
	
	public JarSigner(Table tb) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException
	{
		tabla = tb;
		buscaKeyStores();
	}
	
			
	public void buscaKeyStores() throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException
	{
		List<String> l = new ArrayList<String>();
		Preferencias prefs = Preferencias.getInstancia();
		String fName = prefs.Pref(Defines.PREF_JARSIGNERSIGN);
		int i = 0;
		
		idxSelected = -1;
		tabla.removeAll();
		while (fDatos.hayDatos())
		{
			l = fDatos.leeDatos();
			if (prefs.existKeyStore(l.get(0)))
			{
				TableItem ti = new TableItem(tabla, SWT.NONE);
				if (fName.compareToIgnoreCase(l.get(0)) == 0)
					idxSelected = i;
				ti.setText(0, l.get(0));
				ti.setText(1, l.get(1));
				ti.setText(2, l.get(2));
				ti.setText(3, l.get(3));
				i++;
			}
		}
		if (idxSelected < 0)
			prefs.GuardaPref(Defines.PREF_JARSIGNERSIGN, "");
		tabla.select(idxSelected);
	}
	
	public int getSelect()
	{
		return idxSelected;
	}
	
	private String msgZipAlign;
	
	private int ZipAlign(String fname) throws IOException, InterruptedException
	{
		Preferencias prefs = Preferencias.getInstancia();
		StringBuilder command = new StringBuilder();

		prefs.setMsg(prefs.getIdioma().getString(Defines.IDIOMA_EXE_ZIPALIGN));
		command.append(prefs.Pref(Defines.PREF_ZIPALIGNPATH) + prefs.systemSlash() + Defines.ZIPALIGN_EXE + " -f 4");	//	Sobreescribe fichero de salida si existe
		command.append(" " + fname);
		File f = new File(fname);
		String fpath = f.getPath().substring(0, f.getPath().indexOf(f.getName()) - 1);
		command.append(" " + fpath + prefs.systemSlash() + prefs.Pref(Defines.PREF_ZIPALIGNFILE) + "." + prefs.Pref(Defines.PREF_ZIPALIGNEXT) + "." + prefs.Pref(Defines.PREF_ZIPALIGN_FILETYPE));
		
		Process proc = Runtime.getRuntime().exec(command.toString(), null, new File(prefs.Pref(Defines.PREF_ZIPALIGNPATH)));
        InputStream stderr = proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        msgZipAlign = "";
        String linea = null;
        while ((linea = br.readLine()) != null)
        	if (linea.indexOf("help") < 0)
        		msgZipAlign += linea + "\n";
        proc.waitFor();
        return proc.exitValue();	
    }
	
	private String msgVerifica;
	
	private int Verifica(String fname, String path) throws IOException, InterruptedException
	{
		StringBuilder command = new StringBuilder();

		command.append(path + Defines.JARSIGNER_EXE + " -verify");
		command.append(" " + fname);
		
		Process proc = Runtime.getRuntime().exec(command.toString(), null, new File(path));
        InputStream stderr = proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        msgVerifica = "";
        String linea = null;
        while ((linea = br.readLine()) != null)
        	if (linea.indexOf("help") < 0)
        		msgVerifica += linea + "\n";
        proc.waitFor();
        return proc.exitValue();		
	}
	
	public void Ejecuta(String pass, String alias, boolean zipalign)
	{
		StringBuilder command = new StringBuilder();
		Preferencias prefs = Preferencias.getInstancia();
		
		command.append(prefs.Pref(Defines.PREF_KEYTOOLPATH) + prefs.systemSlash() + Defines.JARSIGNER_EXE);
		command.append(" " + Defines.KT_KEYSTORE_FNAME + " " + prefs.resDir() + prefs.Pref(Defines.PREF_JARSIGNERSIGN));
		command.append(" " + Defines.KT_STOREPASS + " " + pass);	//	-storepass debe ir justo detras de -keystore sino jarsigner emite un prompt
		command.append(" " + prefs.Pref(Defines.PREF_JARSIGNERFILE));
		command.append(" " + alias);
		command.append(" " + Defines.KT_PASS + " " + pass);
	
		try {
			Process proc = Runtime.getRuntime().exec(command.toString(), null, new File(prefs.Pref(Defines.PREF_KEYTOOLPATH)));
	        InputStream stderr = proc.getErrorStream();
	        InputStreamReader isr = new InputStreamReader(stderr);
	        BufferedReader br = new BufferedReader(isr);
	        String txt = "", linea = null;
            while ((linea = br.readLine()) != null)
            	if (linea.indexOf("help") < 0)
            		txt += linea + "\n";
	        proc.waitFor();
	        int eVal = proc.exitValue();
	        int zaErr = 0;
			switch(eVal)
			{
				case	0	:	prefs.setMsg(prefs.getIdioma().getString(Defines.IDIOMA_EXE_VERIFICA));
								if (Verifica(prefs.Pref(Defines.PREF_JARSIGNERFILE), prefs.Pref(Defines.PREF_KEYTOOLPATH) + prefs.systemSlash()) != 0)
									Mensaje.getInstancia().Show(Defines.IDIOMA_JARSIGN_VERIFY_ERROR + " " + msgVerifica, false);
								else
								{
									if (zipalign)
									{
										if ((zaErr = ZipAlign(prefs.Pref(Defines.PREF_JARSIGNERFILE))) != 0)
											Mensaje.getInstancia().Show(Defines.IDIOMA_ZIPALIGN_ERROR + " " + msgZipAlign, false);
									}
									if (zaErr == 0)
										Mensaje.getInstancia().Show(prefs.getIdioma().getString(Defines.IDIOMA_JARSIGN_OK), false);
								}	break;
				default		:	if (txt.length() > 0)
									Mensaje.getInstancia().Show(Defines.JARSIGNER_EXE + ":\n" + txt, false);
								else
									if (eVal > 0)
										Mensaje.getInstancia().Show(prefs.getIdioma().getString(Defines.IDIOMA_JARSIGN_ERROR) + " [" + String.valueOf(eVal) + "]", false);
								break;
			}
			prefs.clearMsg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			prefs.clearMsg();
			Mensaje.getInstancia().Show(Defines.JARSIGNER_EXE + ":\n" + e.getMessage(), false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			prefs.clearMsg();
			Mensaje.getInstancia().Show(Defines.JARSIGNER_EXE + ":\n" + e.getMessage(), false);
		}
		
	}
}
