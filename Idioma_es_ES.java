/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
public class Idioma_es_ES extends Idioma {
	
	@Override
	public Object handleGetObject(String key)
	{
		String val = null;
		
        if (key.equals(Defines.IDIOMA_CONFIG)) val = "Configuración";
        if (key.equals(Defines.IDIOMA_MSG_NOCONFIG)) val = "Bienvenido a Android Signer\nConfigure el programa para continuar";
        if (key.equals(Defines.IDIOMA_NOMFI)) val = "Nombre del fichero";
        if (key.equals(Defines.IDIOMA_ALG)) val = "Algoritmo";
        if (key.equals(Defines.IDIOMA_KEYSIZE)) val = "Tamaño de la clave";
        if (key.equals(Defines.IDIOMA_VALIDEZ)) val = "Validez";
        if (key.equals(Defines.IDIOMA_PASS)) val = "Clave";
        if (key.equals(Defines.IDIOMA_IDENT)) val = "Identidad";
        if (key.equals(Defines.IDIOMA_NAME)) val = "Nombre";
        if (key.equals(Defines.IDIOMA_DEPART)) val = "Departamento";
        if (key.equals(Defines.IDIOMA_ORG)) val = "Organización";
        if (key.equals(Defines.IDIOMA_CITY)) val = "Ciudad";
        if (key.equals(Defines.IDIOMA_PAIS)) val = "País";
        if (key.equals(Defines.IDIOMA_YEARS)) val = "años";
        if (key.equals(Defines.IDIOMA_FILENAME_ERROR)) val = "Nombre de fichero incorrecto";
        if (key.equals(Defines.IDIOMA_KEYTOOL_ERROR)) val = "Error indefinido";
        if (key.equals(Defines.IDIOMA_KEYTOOL_OK)) val = "Archivo creado";
        if (key.equals(Defines.IDIOMA_KEYTOOL_FILEXISTS)) val = "Este archivo ya existe";
        if (key.equals(Defines.IDIOMA_PASS_ERROR)) val = "Clave incorrecta, 6 caracteres mínimo";
        if (key.equals(Defines.IDIOMA_OBLIGATORIO)) val = "Campos obligatorios";
        if (key.equals(Defines.IDIOMA_AVISO)) val = "Aviso";
        if (key.equals(Defines.IDIOMA_INFO)) val = "Información";
        if (key.equals(Defines.IDIOMA_CLAVE)) val = "Clave para guardar sus datos";
        if (key.equals(Defines.IDIOMA_CLAVE24CHARS)) val = "de 16 caracteres";
        if (key.equals(Defines.IDIOMA_CLAVECORTA)) val = "La clave esta vacía o no mide 16 caracteres";
        if (key.equals(Defines.IDIOMA_NO_RUTA_JAVABIN)) val = "No se ha especificado la ruta Java\\bin";
        if (key.equals(Defines.IDIOMA_CLAVE_SAVE)) val = "La clave no se puede cambiar una vez elegida\n¿Seguro que quiere guardar esta clave?";
        if (key.equals(Defines.IDIOMA_FILETOSIGN)) val = "Archivo para firmar";
        if (key.equals(Defines.IDIOMA_SIGNFILE)) val = "Archivo de firma";
        if (key.equals(Defines.IDIOMA_ALIGNFILE_NAME)) val = "Nombre del fichero alineado";
        if (key.equals(Defines.IDIOMA_FILE)) val = "Archivo";
        if (key.equals(Defines.IDIOMA_NOSIGNFILE)) val = "No se ha definido ningún archivo de firma";
        if (key.equals(Defines.IDIOMA_JARSIGN_OK)) val = "Archivo firmado";
        if (key.equals(Defines.IDIOMA_JARSIGN_ERROR)) val = "JarSigner: error indefinido";
        if (key.equals(Defines.IDIOMA_JARSIGN_VERIFY_ERROR)) val = "JarSigner: error de verificación";
        if (key.equals(Defines.IDIOMA_ZIPALIGN_ERROR)) val = "ZipAling: error de alineamiento";
        if (key.equals(Defines.IDIOMA_FILE_ERROR)) val = "El archivo debe tener extensión";
        if (key.equals(Defines.IDIOMA_EXE_KEYTOOL)) val = "Ejecutando KeyTool...";
        if (key.equals(Defines.IDIOMA_EXE_JARSIGNER)) val = "Ejecutando JarSigner...";
        if (key.equals(Defines.IDIOMA_EXE_VERIFICA)) val = "Verificando...";
        if (key.equals(Defines.IDIOMA_EXE_ZIPALIGN)) val = "Ejecutando ZipAlign...";
        return val;
    }

	@Override
	public int Idx()
	{
		return Defines.IDIOMA_ES;
	}
	
	@Override
	public String ISO3611code()
	{
		return Defines.IDIOMA_ES3611;
	}

}
