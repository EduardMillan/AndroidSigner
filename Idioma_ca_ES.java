/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
public class Idioma_ca_ES extends Idioma {
	
	@Override
	public Object handleGetObject(String key)
	{
		String val = null;
		
        if (key.equals(Defines.IDIOMA_CONFIG)) val = "Configuració";
        if (key.equals(Defines.IDIOMA_MSG_NOCONFIG)) val = "Benvingut a Android Signer\nConfiguri el programa per continuar";
        if (key.equals(Defines.IDIOMA_NOMFI)) val = "Nom del fitxer";
        if (key.equals(Defines.IDIOMA_ALG)) val = "Algoritme";
        if (key.equals(Defines.IDIOMA_KEYSIZE)) val = "Mida de la clau";
        if (key.equals(Defines.IDIOMA_VALIDEZ)) val = "Validesa";
        if (key.equals(Defines.IDIOMA_PASS)) val = "Clau";
        if (key.equals(Defines.IDIOMA_IDENT)) val = "Identitat";
        if (key.equals(Defines.IDIOMA_NAME)) val = "Nom";
        if (key.equals(Defines.IDIOMA_DEPART)) val = "Departament";
        if (key.equals(Defines.IDIOMA_ORG)) val = "Organització";
        if (key.equals(Defines.IDIOMA_CITY)) val = "Ciutat";
        if (key.equals(Defines.IDIOMA_PAIS)) val = "Pais";
        if (key.equals(Defines.IDIOMA_YEARS)) val = "anys";
        if (key.equals(Defines.IDIOMA_FILENAME_ERROR)) val = "Nom d\'arxiu incorrecte";
        if (key.equals(Defines.IDIOMA_KEYTOOL_ERROR)) val = "KeyTool: error indefinit";
        if (key.equals(Defines.IDIOMA_KEYTOOL_OK)) val = "Arxiu creat";
        if (key.equals(Defines.IDIOMA_KEYTOOL_FILEXISTS)) val = "Aquest arxiu ja existeix";
        if (key.equals(Defines.IDIOMA_PASS_ERROR)) val = "Clau incorrecta, 6 caràcters minim";
        if (key.equals(Defines.IDIOMA_OBLIGATORIO)) val = "Camps obligatoris";
        if (key.equals(Defines.IDIOMA_AVISO)) val = "Compte!";
        if (key.equals(Defines.IDIOMA_INFO)) val = "Informació";
        if (key.equals(Defines.IDIOMA_CLAVE)) val = "Clau per guardar les seves dades";
        if (key.equals(Defines.IDIOMA_CLAVE24CHARS)) val = "de 16 caràcters";
        if (key.equals(Defines.IDIOMA_CLAVECORTA)) val = "La clau es buida o no te 16 caràcters";
        if (key.equals(Defines.IDIOMA_NO_RUTA_JAVABIN)) val = "No s\'ha especificat la ruta Java\\bin";
        if (key.equals(Defines.IDIOMA_CLAVE_SAVE)) val = "Un cop escollida una clau no es pot cambiar\nSegur que vol guardar aquesta clau?";
        if (key.equals(Defines.IDIOMA_FILETOSIGN)) val = "Arxiu per firmar";
        if (key.equals(Defines.IDIOMA_SIGNFILE)) val = "Arxiu de firma";
        if (key.equals(Defines.IDIOMA_ALIGNFILE_NAME)) val = "Nom del fitxer alineat";
        if (key.equals(Defines.IDIOMA_FILE)) val = "Arxiu";
        if (key.equals(Defines.IDIOMA_NOSIGNFILE)) val = "No s\'ha definit cap arxiu de firma";
        if (key.equals(Defines.IDIOMA_JARSIGN_OK)) val = "Arxiu firmat";
        if (key.equals(Defines.IDIOMA_JARSIGN_ERROR)) val = "JarSigner: error indefinit";
        if (key.equals(Defines.IDIOMA_JARSIGN_VERIFY_ERROR)) val = "JarSigner: error de verificació";
        if (key.equals(Defines.IDIOMA_ZIPALIGN_ERROR)) val = "ZipAling: error d\'alineament";
        if (key.equals(Defines.IDIOMA_FILE_ERROR)) val = "El fitxer ha de tenir extensió";
        if (key.equals(Defines.IDIOMA_EXE_KEYTOOL)) val = "Executant KeyTool...";
        if (key.equals(Defines.IDIOMA_EXE_JARSIGNER)) val = "Executant JarSigner...";
        if (key.equals(Defines.IDIOMA_EXE_VERIFICA)) val = "Verificant...";
        if (key.equals(Defines.IDIOMA_EXE_ZIPALIGN)) val = "Executant ZipAlign...";
        return val;
    }
	
	@Override
	public int Idx()
	{
		return Defines.IDIOMA_CA;
	}

	@Override
	public String ISO3611code()
	{
		return Defines.IDIOMA_CA3611;
	}
}
