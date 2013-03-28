/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


public class Idioma extends ResourceBundle implements CodigoPais {
    
	public Object handleGetObject(String key)
	{
		String val = null;
		
        if (key.equals(Defines.IDIOMA_CONFIG)) val = "";
        if (key.equals(Defines.IDIOMA_MSG_NOCONFIG)) val = "";
		if (key.equals(Defines.IDIOMA_RUTA_JAVABIN)) val = "Ruta KeyTool/JarSigner [Java\\..\\Bin]";
        if (key.equals(Defines.IDIOMA_BTBUSCAR)) val = "Buscar...";
        if (key.equals(Defines.IDIOMA_SELECT)) val = "Idioma";
        if (key.equals(Defines.IDIOMA_NOMFI)) val = "";
        if (key.equals(Defines.IDIOMA_ALIAS)) val = "Alias";
        if (key.equals(Defines.IDIOMA_ALG)) val = "";
        if (key.equals(Defines.IDIOMA_KEYSIZE)) val = "";
        if (key.equals(Defines.IDIOMA_VALIDEZ)) val = "";
        if (key.equals(Defines.IDIOMA_PASS)) val = "";
        if (key.equals(Defines.IDIOMA_IDENT)) val = "";
        if (key.equals(Defines.IDIOMA_NAME)) val = "";
        if (key.equals(Defines.IDIOMA_DEPART)) val = "";
        if (key.equals(Defines.IDIOMA_ORG)) val = "";
        if (key.equals(Defines.IDIOMA_CITY)) val = "";
        if (key.equals(Defines.IDIOMA_STATE)) val = "Provincia";
        if (key.equals(Defines.IDIOMA_PAIS)) val = "";
        if (key.equals(Defines.IDIOMA_CREACLAVE)) val = "Crear";
        if (key.equals(Defines.IDIOMA_YEARS)) val = "";
        if (key.equals(Defines.IDIOMA_FILENAME_ERROR)) val = "";
        if (key.equals(Defines.IDIOMA_KEYTOOL_ERROR)) val = "";
        if (key.equals(Defines.IDIOMA_KEYTOOL_OK)) val = "";
        if (key.equals(Defines.IDIOMA_KEYTOOL_FILEXISTS)) val = "";
        if (key.equals(Defines.IDIOMA_PASS_ERROR)) val = "";
        if (key.equals(Defines.IDIOMA_OBLIGATORIO)) val = "";
        if (key.equals(Defines.IDIOMA_AVISO)) val = "";
        if (key.equals(Defines.IDIOMA_INFO)) val = "";
        if (key.equals(Defines.IDIOMA_CLAVE)) val = "";
        if (key.equals(Defines.IDIOMA_CLAVE24CHARS)) val = "";
        if (key.equals(Defines.IDIOMA_CLAVECORTA)) val = "";
        if (key.equals(Defines.IDIOMA_NO_RUTA_JAVABIN)) val = "";
        if (key.equals(Defines.IDIOMA_CLAVE_SAVE)) val = "";
        if (key.equals(Defines.IDIOMA_SAVE)) val = "Guardar";
        if (key.equals(Defines.IDIOMA_SIGN)) val = "Firmar";
        if (key.equals(Defines.IDIOMA_FILETOSIGN)) val = "";
        if (key.equals(Defines.IDIOMA_SIGNFILE)) val = "";
        if (key.equals(Defines.IDIOMA_USEZIPALIGN)) val = "Aplicar ZipAlign";
        if (key.equals(Defines.IDIOMA_ALIGNFILE_NAME)) val = "";
        if (key.equals(Defines.IDIOMA_FILE)) val = "";
        if (key.equals(Defines.IDIOMA_NOSIGNFILE)) val = "";
        if (key.equals(Defines.IDIOMA_JARSIGN_OK)) val = "";
        if (key.equals(Defines.IDIOMA_JARSIGN_ERROR)) val = "";
        if (key.equals(Defines.IDIOMA_JARSIGN_VERIFY_ERROR)) val = "";
        if (key.equals(Defines.IDIOMA_RUTA_ZIPALIGN)) val = "Ruta ZipAlign [Android\\sdk...\\tools]";
        if (key.equals(Defines.IDIOMA_ZIPALIGN_ERROR)) val = "";
        if (key.equals(Defines.IDIOMA_FILE_ERROR)) val = "";
        if (key.equals(Defines.IDIOMA_EXE_KEYTOOL)) val = "";
        if (key.equals(Defines.IDIOMA_EXE_JARSIGNER)) val = "";
        if (key.equals(Defines.IDIOMA_EXE_VERIFICA)) val = "";
        if (key.equals(Defines.IDIOMA_EXE_ZIPALIGN)) val = "";
        return val;
    }

    // Overrides handleKeySet() so that the getKeys() implementation
    // can rely on the keySet() value.
	@Override
    protected Set<String> handleKeySet() {
        return new HashSet<String>(Arrays.asList(Defines.IDIOMA_CONFIG, Defines.IDIOMA_MSG_NOCONFIG, Defines.IDIOMA_RUTA_JAVABIN,
        		Defines.IDIOMA_BTBUSCAR, Defines.IDIOMA_SELECT, Defines.IDIOMA_NOMFI, Defines.IDIOMA_ALIAS, Defines.IDIOMA_ALG, Defines.IDIOMA_KEYSIZE,
        		Defines.IDIOMA_VALIDEZ, Defines.IDIOMA_PASS, Defines.IDIOMA_IDENT, Defines.IDIOMA_NAME, Defines.IDIOMA_DEPART, Defines.IDIOMA_ORG, Defines.IDIOMA_CITY,
        		Defines.IDIOMA_STATE, Defines.IDIOMA_PAIS, Defines.IDIOMA_CREACLAVE, Defines.IDIOMA_YEARS, Defines.IDIOMA_FILENAME_ERROR, Defines.IDIOMA_KEYTOOL_ERROR,
        		Defines.IDIOMA_KEYTOOL_OK, Defines.IDIOMA_KEYTOOL_FILEXISTS, Defines.IDIOMA_PASS_ERROR, Defines.IDIOMA_OBLIGATORIO, Defines.IDIOMA_AVISO, Defines.IDIOMA_INFO,
        		Defines.IDIOMA_CLAVE, Defines.IDIOMA_CLAVE24CHARS, Defines.IDIOMA_CLAVECORTA, Defines.IDIOMA_NO_RUTA_JAVABIN, Defines.IDIOMA_CLAVE_SAVE,
        		Defines.IDIOMA_SAVE, Defines.IDIOMA_SIGN, Defines.IDIOMA_FILETOSIGN, Defines.IDIOMA_SIGNFILE, Defines.IDIOMA_USEZIPALIGN, Defines.IDIOMA_ALIGNFILE_NAME,
        		Defines.IDIOMA_FILE, Defines.IDIOMA_NOSIGNFILE, Defines.IDIOMA_JARSIGN_OK, Defines.IDIOMA_JARSIGN_ERROR,
        		Defines.IDIOMA_JARSIGN_VERIFY_ERROR, Defines.IDIOMA_RUTA_ZIPALIGN, Defines.IDIOMA_ZIPALIGN_ERROR, Defines.IDIOMA_FILE_ERROR, Defines.IDIOMA_EXE_KEYTOOL,
        		Defines.IDIOMA_EXE_JARSIGNER, Defines.IDIOMA_EXE_VERIFICA, Defines.IDIOMA_EXE_ZIPALIGN));
    }

	@Override
	public Enumeration<String> getKeys() {
		return Collections.enumeration(keySet());
	}
	
	public int Idx()
	{
		return Defines.IDIOMA_NODEF;
	}
	
	public String ISO3611code()
	{
		return Defines.IDIOMA_NODEF3611;
	}

}