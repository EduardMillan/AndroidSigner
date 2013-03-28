/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
import java.io.File;
import java.io.Serializable;
import java.util.prefs.Preferences;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class Preferencias implements Serializable {	//	Singleton

	private static final long serialVersionUID = 1L;
	private Preferences prefs;
	private Shell shell = null;
	private Idioma idioma = null;
	private Label lblMsg = null;
	
	private Preferencias()
	{
		prefs = Preferences.userNodeForPackage(AndroidSigner.class);
	}
	
	private static class PreferenciasSingletonContainer
	{
		public static final Preferencias INSTANCIA = new Preferencias(); 
	}
	
	public static Preferencias getInstancia()
	{
		return PreferenciasSingletonContainer.INSTANCIA;
	}
	
	protected Object readResolve()
	{
	    return getInstancia();
	}
	 
	public void setShell(Shell sh)
	{
		shell = sh;
	}
	
	public Shell getShell()
	{
		return shell;
	}
	
	public void setIdioma(Idioma id)
	{
		idioma = id;
	}
	
	public Idioma getIdioma()
	{
		return idioma;
	}
	
	public void GuardaPref(String camp, String valor)
	{
		prefs.put(camp, valor);
	}
	
	public void GuardaPref(String camp, int valor)
	{
		prefs.putInt(camp, valor);
	}
	
	public void GuardaPref(String camp, boolean valor)
	{
		prefs.putBoolean(camp, valor);
	}
	
	public void GuardaJarSignerFile(File fname)
	{
		GuardaPref(Defines.PREF_JARSIGNERFILE, fname.getPath());
		GuardaPref(Defines.PREF_ZIPALIGNFILE, fname.getName().substring(0, fname.getName().lastIndexOf(".")));
		GuardaPref(Defines.PREF_ZIPALIGN_FILETYPE, fname.getName().substring(fname.getName().lastIndexOf(".") + 1, fname.getName().length()));
	}
	
	public String Pref(String camp)
	{
		return prefs.get(camp, "");
	}
	
	public int PrefInt(String camp)
	{
		return prefs.getInt(camp, -1);
	}

	public boolean PrefBool(String camp)
	{
		return prefs.getBoolean(camp, false);
	}

	public void ResetPrefs()
	{
		GuardaPref(Defines.PREF_KEYTOOLPATH, "");
		GuardaPref(Defines.PREF_IDIOMA, -1);
		GuardaPref(Defines.PREF_CLAVE, "");
		GuardaPref(Defines.PREF_JARSIGNERFILE, "");
		GuardaPref(Defines.PREF_JARSIGNERSIGN, "");
		GuardaPref(Defines.PREF_ZIPALIGNFILE, "");
		GuardaPref(Defines.PREF_ZIPALIGNEXT, "");
		GuardaPref(Defines.PREF_ZIPALIGNPATH, "");
		GuardaPref(Defines.PREF_ZIPALIGN_FILETYPE, "");
		GuardaPref(Defines.PREF_TAB, -1);
		GuardaPref(Defines.PREF_APPLYZIPALIGN, false);
	}
	
	public String installDir()
	{
		return System.getProperty("user.dir");
	}
	
	public String resDir()
	{
		File f = new File(Defines.LOCAL_KEYSTORE_DIR);
	
		if (!f.exists())
			f.mkdirs();
		return f.getAbsolutePath() + systemSlash();
	}
	
	public boolean isWindows()
	{
		return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
	}
	
	public String systemSlash()
	{
		return isWindows() ? Defines.WIN_PATHSLASH : Defines.UNIX_PATHSLASH;
	}

	public boolean existFile(String fname)
	{
		File f = new File(fname);
		
		return f.exists();
	}
	
	public boolean existKeyStore(String fname)
	{
		return existFile(resDir() + fname);
	}

	public boolean existDefaultKeyStore()
	{
		return existFile(resDir() + Pref(Defines.PREF_JARSIGNERSIGN));
	}
	
	public void defMsg(Label lbl)
	{
		lblMsg = lbl;
	}

	public void setMsg(String msg)
	{
		if (lblMsg != null)
			lblMsg.setText(msg);
	}
	
	public void clearMsg()
	{
		setMsg("");
	}
}
