/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */
public class Idioma_en_US extends Idioma {

	@Override
	public Object handleGetObject(String key)
	{
		String val = null;
		
		if (key.equals(Defines.IDIOMA_RUTA_JAVABIN)) val = "KeyTool/JarSigner Path [Java\\..\\Bin]";
        if (key.equals(Defines.IDIOMA_BTBUSCAR)) val = "Search...";
        if (key.equals(Defines.IDIOMA_SELECT)) val = "Language";
        if (key.equals(Defines.IDIOMA_STATE)) val = "State";
        if (key.equals(Defines.IDIOMA_CREACLAVE)) val = "Create";
        if (key.equals(Defines.IDIOMA_SAVE)) val = "Save";
        if (key.equals(Defines.IDIOMA_SIGN)) val = "Sign";
        if (key.equals(Defines.IDIOMA_USEZIPALIGN)) val = "Apply ZipAlign";
        if (key.equals(Defines.IDIOMA_RUTA_ZIPALIGN)) val = "ZipAlign Path [Android\\sdk...\\tools]";
		if (key.equals(Defines.IDIOMA_CONFIG)) val = "Configuration";
        if (key.equals(Defines.IDIOMA_MSG_NOCONFIG)) val = "Welcome to Android Signer\nPlease configure the software";
        if (key.equals(Defines.IDIOMA_NOMFI)) val = "Filename";
        if (key.equals(Defines.IDIOMA_ALG)) val = "Algoritm";
        if (key.equals(Defines.IDIOMA_KEYSIZE)) val = "Key size";
        if (key.equals(Defines.IDIOMA_VALIDEZ)) val = "Validity";
        if (key.equals(Defines.IDIOMA_PASS)) val = "Key";
        if (key.equals(Defines.IDIOMA_IDENT)) val = "Identity";
        if (key.equals(Defines.IDIOMA_NAME)) val = "Name";
        if (key.equals(Defines.IDIOMA_DEPART)) val = "Department";
        if (key.equals(Defines.IDIOMA_ORG)) val = "Organization";
        if (key.equals(Defines.IDIOMA_CITY)) val = "City";
        if (key.equals(Defines.IDIOMA_PAIS)) val = "Country";
        if (key.equals(Defines.IDIOMA_YEARS)) val = "years";
        if (key.equals(Defines.IDIOMA_FILENAME_ERROR)) val = "Illegal filename";
        if (key.equals(Defines.IDIOMA_KEYTOOL_ERROR)) val = "Undefined error";
        if (key.equals(Defines.IDIOMA_KEYTOOL_OK)) val = "File created";
        if (key.equals(Defines.IDIOMA_KEYTOOL_FILEXISTS)) val = "Filename in use";
        if (key.equals(Defines.IDIOMA_PASS_ERROR)) val = "Short key, must be 6 characters at least";
        if (key.equals(Defines.IDIOMA_OBLIGATORIO)) val = "Required fields";
        if (key.equals(Defines.IDIOMA_AVISO)) val = "Warning";
        if (key.equals(Defines.IDIOMA_INFO)) val = "Information";
        if (key.equals(Defines.IDIOMA_CLAVE)) val = "Key to encrypt your data";
        if (key.equals(Defines.IDIOMA_CLAVE24CHARS)) val = "of 16 characters";
        if (key.equals(Defines.IDIOMA_CLAVECORTA)) val = "Empty or short key (16 characters)";
        if (key.equals(Defines.IDIOMA_NO_RUTA_JAVABIN)) val = "No Java\\...\\bin path found";
        if (key.equals(Defines.IDIOMA_CLAVE_SAVE)) val = "Can\'t change the key when saved\nAre you sure to save this key?";
        if (key.equals(Defines.IDIOMA_FILETOSIGN)) val = "File to sign";
        if (key.equals(Defines.IDIOMA_SIGNFILE)) val = "Sign file";
        if (key.equals(Defines.IDIOMA_ALIGNFILE_NAME)) val = "Aligned filename";
        if (key.equals(Defines.IDIOMA_FILE)) val = "File";
        if (key.equals(Defines.IDIOMA_NOSIGNFILE)) val = "Undefined sign file";
        if (key.equals(Defines.IDIOMA_JARSIGN_OK)) val = "File signed";
        if (key.equals(Defines.IDIOMA_JARSIGN_ERROR)) val = "JarSigner: undefined error";
        if (key.equals(Defines.IDIOMA_JARSIGN_VERIFY_ERROR)) val = "JarSigner: verify error";
        if (key.equals(Defines.IDIOMA_ZIPALIGN_ERROR)) val = "ZipAling: align error";
        if (key.equals(Defines.IDIOMA_FILE_ERROR)) val = "The file must have a extension";
        if (key.equals(Defines.IDIOMA_EXE_KEYTOOL)) val = "Executing KeyTool...";
        if (key.equals(Defines.IDIOMA_EXE_JARSIGNER)) val = "Executing JarSigner...";
        if (key.equals(Defines.IDIOMA_EXE_VERIFICA)) val = "Verifingo...";
        if (key.equals(Defines.IDIOMA_EXE_ZIPALIGN)) val = "Executing ZipAlign...";
        return val;
    }

	@Override
	public int Idx()
	{
		return Defines.IDIOMA_EN;
	}
	
	@Override
	public String ISO3611code()
	{
		return Defines.IDIOMA_EN3611;
	}

}
