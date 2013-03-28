/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.Map;

public class KeyTool {
	private Map<String, String> datos = new HashMap<String, String>();

	public KeyTool(Map<String, String> txts, boolean algoritm)
	{
		datos.putAll(txts);
		datos.put(Defines.KT_ALGORITMO, (algoritm) ? "RSA" : "DSA");
		int annos = Integer.parseInt(datos.get(Defines.KT_VALIDEZ));
		datos.put(Defines.KT_VALIDEZ, String.valueOf(annos * 365 + annos / 4));
	}
	
	private String fDName(String id, String lista)
	{
		if (datos.get(id).length() > 0)
		{
			if (lista.length() > 0)
				lista += ", ";
			lista += id + datos.get(id);
		}
		return lista;
	}
	
	private String fCommand(String id)
	{
		return " " + id + " " + datos.get(id);
	}
	
	public void Ejecuta()
	{
		StringBuilder command = new StringBuilder();
		Preferencias prefs = Preferencias.getInstancia();
		
		command.append(prefs.Pref(Defines.PREF_KEYTOOLPATH) + prefs.systemSlash() + Defines.KEYTOOL_EXE + " -genkey");		
		command.append(" " + Defines.KT_KEYSTORE_FNAME + " " + prefs.resDir() + datos.get(Defines.KT_KEYSTORE_FNAME) + "." + datos.get(Defines.KT_KEYSTORE_FEXT));
		command.append(fCommand(Defines.KT_PASS));
		command.append(fCommand(Defines.KT_ALIAS));
		command.append(fCommand(Defines.KT_ALGORITMO));
		command.append(fCommand(Defines.KT_KEYSIZE));
		command.append(fCommand(Defines.KT_VALIDEZ));
		command.append(" " + "-dname \"");
		String lista = "";
		lista = fDName(Defines.KT_DNAME_NOM, lista);
		lista = fDName(Defines.KT_DNAME_DEPART, lista);
		lista = fDName(Defines.KT_DNAME_ORG, lista);
		lista = fDName(Defines.KT_DNAME_CIUDAD, lista);
		lista = fDName(Defines.KT_DNAME_STAT, lista);
		if (lista.length() > 0)
			lista += ", ";
		command.append(lista);
		command.append(Defines.KT_DNAME_PAIS + datos.get(Defines.KT_DNAME_PAIS) + "\"");
		command.append(" " + Defines.KT_STOREPASS + " " + datos.get(Defines.KT_PASS));	//	Clave storepass = keypass
		
		try {
			Process proc = Runtime.getRuntime().exec(command.toString(), null, new File(prefs.Pref(Defines.PREF_KEYTOOLPATH)));
	        InputStream stderr = proc.getErrorStream();
	        InputStreamReader isr = new InputStreamReader(stderr);
	        BufferedReader br = new BufferedReader(isr);
	        proc.waitFor();
	        String txt = "", linea = null;
            while ((linea = br.readLine()) != null)
            	if (linea.indexOf("help") < 0)
            		txt += linea + "\n";
           	int eVal = proc.exitValue();
			switch(eVal)
			{
				case	0	:	{							
									Datos.getInstancia().guardaDatos(datos.get(Defines.KT_KEYSTORE_FNAME) + "." + datos.get(Defines.KT_KEYSTORE_FEXT), datos.get(Defines.KT_ALGORITMO), datos.get(Defines.KT_PASS), datos.get(Defines.KT_ALIAS));
									Mensaje.getInstancia().Show(prefs.getIdioma().getString(Defines.IDIOMA_KEYTOOL_OK), false);
								}	break;
				default		:	if (txt.length() > 0)
									Mensaje.getInstancia().Show(Defines.KEYTOOL_EXE + ":\n" + txt, false);
								else
									if (eVal > 0)
										Mensaje.getInstancia().Show(prefs.getIdioma().getString(Defines.IDIOMA_KEYTOOL_ERROR) + " [" + String.valueOf(eVal) + "]", false);
								break;
			}
			prefs.clearMsg();
		} catch (Throwable t) {
			// TODO Auto-generated catch block
				prefs.clearMsg();
				Mensaje.getInstancia().Show(Defines.KEYTOOL_EXE + ":\n" + t.getMessage(), false);
		}
	}
	
}
