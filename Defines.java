/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
public class Defines {

	public static final String DEFAULT_KEYFILE_EXT = "keystore";
	public static final int INICIO_ANNOS_VALIDEZ = 5;
	public static final int ANNOS_VALIDEZ = 21;
	public static final int INTERVAL_ANNOS_VALIDEZ = 5;
	public static final String KEYTOOL_EXE = "keytool.exe";
	public static final String JARSIGNER_EXE = "jarsigner.exe";
	public static final String ZIPALIGN_EXE = "zipalign.exe";
	public static final String WIN_PATHSLASH = "\\";
	public static final String UNIX_PATHSLASH = "/";
	public static final String LOCAL_KEYSTORE_DIR = "keystore";
	public static final String KEYSTORE_DATAFILE = "keydatos.list";
	public static final String KEYSTORE_CTRLCHAR = "\0\0";
	public static final String KEYSTORE_TEMP = "keydatos.tmp";
	public static final String VERSION = "Android Signer r1.0 Samarcanda";
	public static final String PATH_RECURSOS = "/recursos/android/signer/";
	public static final String PAISES = "Paises.list";
/*
 * Preferencias	
 */
	public static final String PREF_IDIOMA = "KeyIdioma";
	public static final String PREF_KEYTOOLPATH = "KeyToolPath";
	public static final String PREF_CLAVE = "KeyClave";
	public static final String PREF_JARSIGNERFILE = "KeyJarSignerFile";
	public static final String PREF_JARSIGNERSIGN = "KeyJarSignerSign";
	public static final String PREF_ZIPALIGNFILE = "KeyZipAlignFile";
	public static final String PREF_ZIPALIGNEXT = "KeyZipAlignExt";
	public static final String PREF_ZIPALIGN_FILETYPE = "KeyZipAlignFileType";
	public static final String PREF_ZIPALIGNPATH = "KeyZipAlignPath";
	public static final String PREF_TAB = "KeyTab";
	public static final String PREF_APPLYZIPALIGN = "KeyApplyZipAlign";
/*
 * keys idiomas	
 */
	public static final int IDIOMA_NODEF = -1;
	public static final int IDIOMA_CA = 0;
	public static final int IDIOMA_ES = 2;
	public static final int IDIOMA_IT = 3;
	public static final int IDIOMA_EN = 1;
	
	public static final String IDIOMA_NODEF3611 = "";
	public static final String IDIOMA_CA3611 = "ES";
	public static final String IDIOMA_ES3611 = "ES";
	public static final String IDIOMA_EN3611 = "US";
	public static final String IDIOMA_IT3611 = "IT";
	
	public static final String IDIOMA_CONFIG = "keyConfig";
	public static final String IDIOMA_MSG_NOCONFIG = "keyNoConfig";
	public static final String IDIOMA_RUTA_JAVABIN = "keyRutaJavaBin";
	public static final String IDIOMA_BTBUSCAR = "keyBtBuscar";
	public static final String IDIOMA_SELECT = "keySelect";
	public static final String IDIOMA_NOMFI = "keyNombreFichero";
	public static final String IDIOMA_ALIAS = "keyAlias";
	public static final String IDIOMA_ALG = "keyAlgoritm";
	public static final String IDIOMA_KEYSIZE= "keyKeySize";
	public static final String IDIOMA_VALIDEZ = "keyValidez";
	public static final String IDIOMA_PASS = "keyPassword";
	public static final String IDIOMA_IDENT = "keyIdent";
	public static final String IDIOMA_NAME = "keyNombre";
	public static final String IDIOMA_DEPART = "keyDepartament";
	public static final String IDIOMA_ORG = "keyOrg";
	public static final String IDIOMA_CITY = "keyCiudad";
	public static final String IDIOMA_STATE = "keyProvincia";
	public static final String IDIOMA_PAIS = "keyPais";
	public static final String IDIOMA_CREACLAVE = "keyCreaClave";
	public static final String IDIOMA_YEARS = "keyYears";
	public static final String IDIOMA_FILENAME_ERROR = "keyFileNameError";
	public static final String IDIOMA_KEYTOOL_ERROR = "keyKTError";
	public static final String IDIOMA_KEYTOOL_OK = "keyKTOK";
	public static final String IDIOMA_KEYTOOL_FILEXISTS = "keyKTFileExist";
	public static final String IDIOMA_PASS_ERROR = "keyPassError";
	public static final String IDIOMA_OBLIGATORIO = "keyObligatorio";
	public static final String IDIOMA_AVISO = "keyAviso";
	public static final String IDIOMA_INFO = "keyInfo";
	public static final String IDIOMA_CLAVE = "keyClave";
	public static final String IDIOMA_CLAVE24CHARS = "keyClave24";
	public static final String IDIOMA_CLAVECORTA = "keyClaveCorta";
	public static final String IDIOMA_NO_RUTA_JAVABIN = "keyNoRutaJavaBin";
	public static final String IDIOMA_CLAVE_SAVE = "keySaveClave";
	public static final String IDIOMA_SAVE = "keySave";
	public static final String IDIOMA_SIGN = "keySign";
	public static final String IDIOMA_FILETOSIGN = "keyFileToSign";
	public static final String IDIOMA_SIGNFILE = "keySignFile";
	public static final String IDIOMA_USEZIPALIGN = "keyUseZipAlign";
	public static final String IDIOMA_ALIGNFILE_NAME = "keyAlineadoNom";
	public static final String IDIOMA_FILE = "keyFile";
	public static final String IDIOMA_NOSIGNFILE = "keyNoSignFile";
	public static final String IDIOMA_JARSIGN_OK = "keyJarSignOK";
	public static final String IDIOMA_JARSIGN_ERROR = "keyJarSignError";
	public static final String IDIOMA_JARSIGN_VERIFY_ERROR = "keyJarSignVerifyError";
	public static final String IDIOMA_RUTA_ZIPALIGN = "keyRutaZipAlign";
	public static final String IDIOMA_ZIPALIGN_ERROR = "keyZipAlignError";
	public static final String IDIOMA_FILE_ERROR = "keyFileError";
	public static final String IDIOMA_EXE_KEYTOOL = "keyExeKeyTool";
	public static final String IDIOMA_EXE_JARSIGNER = "keyExeJarSigner";
	public static final String IDIOMA_EXE_VERIFICA = "keyExeVerifica";
	public static final String IDIOMA_EXE_ZIPALIGN = "keyExeZipAlign";
/*
 * 	Campos keytool	
 */
	public static final String KT_KEYSTORE_FNAME = "-keystore";
	public static final String KT_KEYSTORE_FEXT = "extkeystore";
	public static final String KT_ALIAS = "-alias";
	public static final String KT_PASS = "-keypass";
	public static final String KT_DNAME_NOM = "CN=";
	public static final String KT_DNAME_DEPART = "OU=";
	public static final String KT_DNAME_ORG = "O=";
	public static final String KT_DNAME_CIUDAD = "L=";
	public static final String KT_DNAME_STAT = "S=";
	public static final String KT_DNAME_PAIS = "C=";
	public static final String KT_KEYSIZE = "-keysize";
	public static final String KT_ALGORITMO = "-keyalg";
	public static final String KT_VALIDEZ = "-validity";
	public static final String KT_STOREPASS = "-storepass";
/*
 * 	Patterns regex	
 */
	public static final String PATTERN_UNIXFILENAME = "^[A-Za-z0-9\\-\\._]+$";
	public static final String PATTERN_WINFILENAME = "# Match a valid Windows filename (unspecified file system).          \n" +
													"^                                # Anchor to start of string.        \n" +
													"(?!                              # Assert filename is not: CON, PRN, \n" +
													"  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n" +
													"    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n" +
													"    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n" +
													"  )                              # LPT6, LPT7, LPT8, and LPT9...     \n" +
													"  (?:\\.[^.]*)?                  # followed by optional extension    \n" +
													"  $                              # and end of string                 \n" +
													")                                # End negative lookahead assertion. \n" +
													"[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n" +
													"[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n" +
													"$                                # Anchor to end of string.            ";
}
