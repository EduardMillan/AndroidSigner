/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */
public class Idioma_it_IT extends Idioma {

	@Override
	public Object handleGetObject(String key) {
		String val = null;

		if (key.equals(Defines.IDIOMA_RUTA_JAVABIN))
			val = "KeyTool/JarSigner Path [Java\\..\\Bin]";
		if (key.equals(Defines.IDIOMA_BTBUSCAR))
			val = "Cercare...";
		if (key.equals(Defines.IDIOMA_SELECT))
			val = "Lingua";
		if (key.equals(Defines.IDIOMA_STATE))
			val = "Stato";
		if (key.equals(Defines.IDIOMA_CREACLAVE))
			val = "Creare";
		if (key.equals(Defines.IDIOMA_SAVE))
			val = "Salvare";
		if (key.equals(Defines.IDIOMA_SIGN))
			val = "Firma";
		if (key.equals(Defines.IDIOMA_USEZIPALIGN))
			val = "Applicare ZipAlign";
		if (key.equals(Defines.IDIOMA_RUTA_ZIPALIGN))
			val = "ZipAlign Path [Android\\sdk...\\tools]";
		if (key.equals(Defines.IDIOMA_CONFIG))
			val = "Configurazione";
		if (key.equals(Defines.IDIOMA_MSG_NOCONFIG))
			val = "Benvenuti Android Signer\nConfigurare il programma per continuare";
		if (key.equals(Defines.IDIOMA_NOMFI))
			val = "Nome del file";
		if (key.equals(Defines.IDIOMA_ALG))
			val = "Algoritmo";
		if (key.equals(Defines.IDIOMA_KEYSIZE))
			val = "Dimensione chiave";
		if (key.equals(Defines.IDIOMA_VALIDEZ))
			val = "Validità";
		if (key.equals(Defines.IDIOMA_PASS))
			val = "Chiave";
		if (key.equals(Defines.IDIOMA_IDENT))
			val = "Identità";
		if (key.equals(Defines.IDIOMA_NAME))
			val = "Nome";
		if (key.equals(Defines.IDIOMA_DEPART))
			val = "Ufficio";
		if (key.equals(Defines.IDIOMA_ORG))
			val = "Organizzazione";
		if (key.equals(Defines.IDIOMA_CITY))
			val = "Città";
		if (key.equals(Defines.IDIOMA_PAIS))
			val = "Paese";
		if (key.equals(Defines.IDIOMA_YEARS))
			val = "anni";
		if (key.equals(Defines.IDIOMA_FILENAME_ERROR))
			val = "Il nome del file non e corretto";
		if (key.equals(Defines.IDIOMA_KEYTOOL_ERROR))
			val = "Errore non definito";
		if (key.equals(Defines.IDIOMA_KEYTOOL_OK))
			val = "File creato";
		if (key.equals(Defines.IDIOMA_KEYTOOL_FILEXISTS))
			val = "Questo file esiste già";
		if (key.equals(Defines.IDIOMA_PASS_ERROR))
			val = "Password errata, almeno 6 caratteri";
		if (key.equals(Defines.IDIOMA_OBLIGATORIO))
			val = "Campi obbligatori";
		if (key.equals(Defines.IDIOMA_AVISO))
			val = "Notare";
		if (key.equals(Defines.IDIOMA_INFO))
			val = "Informazioni";
		if (key.equals(Defines.IDIOMA_CLAVE))
			val = "Password per salvare i dati";
		if (key.equals(Defines.IDIOMA_CLAVE24CHARS))
			val = "di 16 caratteri";
		if (key.equals(Defines.IDIOMA_CLAVECORTA))
			val = "La chiave è vuota o non è di 16 caratteri";
		if (key.equals(Defines.IDIOMA_NO_RUTA_JAVABIN))
			val = "Non specificato path Java\\bin";
		if (key.equals(Defines.IDIOMA_CLAVE_SAVE))
			val = "La chiave non può essere cambiato una volta scelta\nSei sicuro di voler salvare la password?";
		if (key.equals(Defines.IDIOMA_FILETOSIGN))
			val = "Archivio per la firma";
		if (key.equals(Defines.IDIOMA_SIGNFILE))
			val = "File firmato";
		if (key.equals(Defines.IDIOMA_ALIGNFILE_NAME))
			val = "Nome del file allineati";
		if (key.equals(Defines.IDIOMA_FILE))
			val = "File";
		if (key.equals(Defines.IDIOMA_NOSIGNFILE))
			val = "Non definito alcun file di firma";
		if (key.equals(Defines.IDIOMA_JARSIGN_OK))
			val = "File firmato";
		if (key.equals(Defines.IDIOMA_JARSIGN_ERROR))
			val = "JarSigner: errore non definito";
		if (key.equals(Defines.IDIOMA_JARSIGN_VERIFY_ERROR))
			val = "JarSigner: errore di verifica";
		if (key.equals(Defines.IDIOMA_ZIPALIGN_ERROR))
			val = "ZipAling: allineamento errore";
		if (key.equals(Defines.IDIOMA_FILE_ERROR))
			val = "Il file deve avere l'estensione";
		if (key.equals(Defines.IDIOMA_EXE_KEYTOOL))
			val = "Corsa KeyTool...";
		if (key.equals(Defines.IDIOMA_EXE_JARSIGNER))
			val = "Corsa JarSigner...";
		if (key.equals(Defines.IDIOMA_EXE_VERIFICA))
			val = "Verifica...";
		if (key.equals(Defines.IDIOMA_EXE_ZIPALIGN))
			val = "Corsa ZipAlign...";
		return val;
	}

	@Override
	public int Idx() {
		return Defines.IDIOMA_IT;
	}

	@Override
	public String ISO3611code() {
		return Defines.IDIOMA_IT3611;
	}

}
