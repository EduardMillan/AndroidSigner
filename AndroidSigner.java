/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class AndroidSigner {

	protected Display display;
	protected Shell shlAndroidSigner;
	private Preferencias asPrefs = Preferencias.getInstancia();
	private Idioma asIdioma = setLocale(asPrefs.PrefInt(Defines.PREF_IDIOMA));
	private Paises paises;
	private KeyToolObserver KTObserver;
	private JarSigner asJarSigner;
	private Label lblRutaKeyTool, lblNewLabel, lblIdioma;
	private CTabItem tbtmConfiguracion;
	private CTabFolder tabFolder;
	private Button btnNewButton;
	private Combo comboIdioma;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	private Label lblNewLabel_7;
	private Label lblNewLabel_8;
	private Label lblNewLabel_9;
	private Label lblNewLabel_10;
	private Label lblNewLabel_11;
	private Label lblNewLabel_12;
	private Label lblNewLabel_13;
	private Text textFilename;
	private Text textAlias;
	private Text textPass;
	private Text textName;
	private Text textDepart;
	private Text textOrg;
	private Text textCity;
	private Text textStat;
	private Text txtFilenameExt;
	private Label lblNewLabel_14;
	private Button btnNewButtonCrearClave;
	private Combo comboKeySize;
	private List<Text> listaKT = new ArrayList<Text>();
	private Map<String, String> listaDC = new HashMap<String, String>();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					AndroidSigner window = new AndroidSigner();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlAndroidSigner.open();
		shlAndroidSigner.layout();
		CargaPreferencias(true);
		while (!shlAndroidSigner.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Carga preferencias
	 */
	
	private String getAlineadoFinal()
	{
		String s = asPrefs.Pref(Defines.PREF_ZIPALIGNEXT);
		return asPrefs.Pref(Defines.PREF_ZIPALIGNFILE) + "." + s + (!s.isEmpty() ? "." : "") + asPrefs.Pref(Defines.PREF_ZIPALIGN_FILETYPE);
	}

	private void CargaPreferencias(boolean inicio)
	{
//		asPrefs.ResetPrefs();		//	Para debug!!!

		File f = new File(asPrefs.Pref(Defines.PREF_KEYTOOLPATH));
		boolean bf = f.exists(), clave = asPrefs.Pref(Defines.PREF_CLAVE).isEmpty();
		boolean jsFile = asPrefs.existFile(asPrefs.Pref(Defines.PREF_JARSIGNERFILE));
		boolean jsSign = asPrefs.existDefaultKeyStore() && !asPrefs.Pref(Defines.PREF_JARSIGNERSIGN).isEmpty();
		boolean ksExist = (asPrefs.Pref(Defines.PREF_CLAVE).isEmpty()) ? false : Datos.getInstancia().hayKeyStores();

		btnUsar.setEnabled(!asPrefs.Pref(Defines.PREF_ZIPALIGNPATH).isEmpty());
		btnUsar.setSelection(asPrefs.PrefBool(Defines.PREF_APPLYZIPALIGN));

		File zaf = new File(asPrefs.Pref(Defines.PREF_JARSIGNERFILE));
		boolean zAlign = btnUsar.getEnabled() ? (btnUsar.getSelection() ? (getAlineadoFinal().compareToIgnoreCase(zaf.getName()) != 0) : true) : true;
		
		if (inicio)
		{
			if (!bf || clave)
			{
				tabFolder.setSelection(tbtmConfiguracion);
				Mensaje.getInstancia().Show(asIdioma.getString(Defines.IDIOMA_MSG_NOCONFIG), false);
			}
			else
			{
				int i = asPrefs.PrefInt(Defines.PREF_TAB);
				tabFolder.setSelection((i < 0) ? 0 : i);
			}
		}
//	Keytool, el boton de crear fichero se gestiona en KTObserver
		compositeKeyTool.setEnabled(bf && !clave);
//	JarSigner		
		compositeJarSigner.setEnabled(bf && !clave && ksExist);
		btnNewButtonFirmar.setEnabled(jsFile && jsSign && zAlign);
		lblNewLabelJarSignerFile.setText(asPrefs.Pref(Defines.PREF_JARSIGNERFILE));
		lblNewLabelJarSignerFirma.setText((ksExist) ? asPrefs.Pref(Defines.PREF_JARSIGNERSIGN) : asIdioma.getString(Defines.IDIOMA_NOSIGNFILE));
		textZipAlignAdd.setEnabled(btnUsar.getEnabled() && btnUsar.getSelection());
		lblNewLabelZipAlignFilename.setText(asPrefs.Pref(Defines.PREF_ZIPALIGNFILE) + ".");
		lblNewLabelZipAlignExt.setText("." + asPrefs.Pref(Defines.PREF_ZIPALIGN_FILETYPE));
//	Configuración		
		txtClave.setText(asPrefs.Pref(Defines.PREF_CLAVE));
		txtClave.setEditable(clave);
		btnNewButtonGuardaClave.setEnabled(clave);
		lblRutaKeyTool.setText(asPrefs.Pref(Defines.PREF_KEYTOOLPATH));
		lblNewLabelZipAlignPath.setText(asPrefs.Pref(Defines.PREF_ZIPALIGNPATH));
	}

	/*
	 * Cambia idioma
	 */

	private Idioma setLocale(int idx) {
		String idioma[] = { "es", "ES" }; // es_ES, español, idx 2 o -1 (no
											// definido)

		switch (idx) {
		case Defines.IDIOMA_CA:
			idioma[0] = "ca";
			break; // ca_ES
		case Defines.IDIOMA_EN:
			idioma[0] = "en";
			idioma[1] = "US";
			break; // en_US, en_UK
		case Defines.IDIOMA_IT:
			idioma[0] = "it";
			idioma[1] = "IT";
			break; // it_IT
		}
		Idioma id = (Idioma) ResourceBundle.getBundle("Idioma", new Locale(
				idioma[0], idioma[1]));
		asPrefs.setIdioma(id);
		return id;
	}

	private void CambiaIdioma(int idx) {
		asIdioma = setLocale(idx);
		asPrefs.GuardaPref(Defines.PREF_IDIOMA, idx);
		comboIdioma.select(asIdioma.Idx());
		tbtmConfiguracion.setText(asIdioma.getString(Defines.IDIOMA_CONFIG));
		lblNewLabel.setText(asIdioma.getString(Defines.IDIOMA_RUTA_JAVABIN));
		btnNewButton.setText(asIdioma.getString(Defines.IDIOMA_BTBUSCAR));
		btnNewButton_1.setText(btnNewButton.getText());
		lblIdioma.setText(asIdioma.getString(Defines.IDIOMA_SELECT));
		lblNewLabel_1.setText(asIdioma.getString(Defines.IDIOMA_NOMFI));
		lblNewLabel_2.setText(asIdioma.getString(Defines.IDIOMA_ALIAS));
		lblNewLabel_3.setText(asIdioma.getString(Defines.IDIOMA_ALG));
		lblNewLabel_4.setText(asIdioma.getString(Defines.IDIOMA_KEYSIZE));
		lblNewLabel_5.setText(asIdioma.getString(Defines.IDIOMA_VALIDEZ));
		lblNewLabel_6.setText(asIdioma.getString(Defines.IDIOMA_PASS));
		lblNewLabel_7.setText(asIdioma.getString(Defines.IDIOMA_IDENT));
		lblNewLabel_8.setText(asIdioma.getString(Defines.IDIOMA_NAME));
		lblNewLabel_9.setText(asIdioma.getString(Defines.IDIOMA_DEPART));
		lblNewLabel_10.setText(asIdioma.getString(Defines.IDIOMA_ORG));
		lblNewLabel_11.setText(asIdioma.getString(Defines.IDIOMA_CITY));
		lblNewLabel_12.setText(asIdioma.getString(Defines.IDIOMA_STATE));
		lblNewLabel_13.setText(asIdioma.getString(Defines.IDIOMA_PAIS));
		btnNewButtonCrearClave.setText(asIdioma.getString(Defines.IDIOMA_CREACLAVE));
		lblNewLabelYears.setText(asIdioma.getString(Defines.IDIOMA_YEARS));
		labelRequired.setText(asIdioma.getString(Defines.IDIOMA_OBLIGATORIO));
		lblNewLabelClave.setText(asIdioma.getString(Defines.IDIOMA_CLAVE));
		lblNewLabel24Chars.setText(String.valueOf(txtClave.getText().length()) + " " + asIdioma.getString(Defines.IDIOMA_CLAVE24CHARS));
		btnNewButtonGuardaClave.setText(asIdioma.getString(Defines.IDIOMA_SAVE));
		btnNewButtonBuscaFirmar.setText(asIdioma.getString(Defines.IDIOMA_BTBUSCAR));
		lblNewLabel_16.setText(asIdioma.getString(Defines.IDIOMA_FILETOSIGN));
		lblNewLabel_22.setText(asIdioma.getString(Defines.IDIOMA_SIGNFILE));
		btnUsar.setText(asIdioma.getString(Defines.IDIOMA_USEZIPALIGN));
		lblNewLabel_18.setText(asIdioma.getString(Defines.IDIOMA_ALIGNFILE_NAME));
		tblclmnArchivo.setText(asIdioma.getString(Defines.IDIOMA_FILE));
		tblclmnClave.setText(asIdioma.getString(Defines.IDIOMA_PASS));
		btnNewButtonFirmar.setText(asIdioma.getString(Defines.IDIOMA_SIGN));
		tblclmnAloritmo.setText(asIdioma.getString(Defines.IDIOMA_ALG));
		textZipAlignAdd.setText(asPrefs.Pref(Defines.PREF_ZIPALIGNEXT));
		lblNewLabelZipAlign.setText(asIdioma.getString(Defines.IDIOMA_RUTA_ZIPALIGN));
		paises.cambiaPais(asIdioma);
		comboPais.removeAll();
		while (paises.hayMas())
			comboPais.add(paises.nomPais());
		comboPais.select(paises.paisActual());
	}

	/**************
	 * No usado VerifyListener vlInt = new VerifyListener() { public void
	 * verifyText(VerifyEvent arg0) { arg0.doit = false; char myChar =
	 * arg0.character; arg0.doit = ((Character.isDigit(myChar)) ||
	 * (Character.isISOControl(myChar))); } };
	 ************************/
	private Combo comboYears;
	private Label lblNewLabel_15;
	private Label lblNewLabelYears;
	private Combo comboPais;

	void calculaComboKeySize(int limit) {
		MathContext mc = new MathContext(10, RoundingMode.FLOOR);
		comboKeySize.removeAll();
		for (int i = 9; i < limit; i++) {
			double v = Math.pow(2, i);
			BigDecimal bd = new BigDecimal(v, mc);
			comboKeySize.add(bd.toPlainString());
		}
		comboKeySize.select(1);
	}

	private boolean validaNombreFichero(String fname) // http://stackoverflow.com/questions/6730009/validate-a-file-name-on-windows
	{
		Pattern pattern = Pattern.compile(Defines.PATTERN_UNIXFILENAME); // UNIX filenames

		if (asPrefs.isWindows())
			pattern = Pattern.compile(Defines.PATTERN_WINFILENAME, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
		Matcher matcher = pattern.matcher(fname);
		return matcher.matches();
	}

	ModifyListener vlModifText = new ModifyListener() {
		public void modifyText(ModifyEvent arg0) {
			KTObserver.Actualiza(arg0.widget.hashCode());
		}
	};
	private Composite compositeKeyTool;
	private Composite compositeJarSigner;
	private Table table;
	private Text textZipAlignAdd;
	private Label lblNewLabelClave;
	private Text txtClave;
	private Label lblNewLabel24Chars;
	private CTabItem tbtmJarsigner;
	private Button btnNewButtonGuardaClave;
	private Button btnNewButtonBuscaFirmar;
	private Button btnNewButtonFirmar;
	private Label lblNewLabel_16;
	private Label lblNewLabel_22;
	private Button btnUsar;
	private Label lblNewLabel_18;
	private TableColumn tblclmnArchivo;
	private TableColumn tblclmnClave;
	private TableColumn tblclmnAloritmo;
	private TableColumn tblclmnNewColumn;
	private Label lblNewLabelJarSignerFile;
	private Label lblNewLabelJarSignerFirma;
	private Label lblNewLabelZipAlignExt;
	private Label lblNewLabelZipAlign;
	private Label lblNewLabelZipAlignPath;
	private Button btnNewButton_1;
	private Label lblNewLabelZipAlignFilename;
	private Label lblNewLabelStatus;
	private Label labelRequired;

	private boolean validaDatosKeyTool()
	{
		boolean ret = validaNombreFichero(textFilename.getText() + "." + txtFilenameExt.getText());

		if (!ret)
			Mensaje.getInstancia().Show(asIdioma.getString(Defines.IDIOMA_FILENAME_ERROR), false);
		else {
			ret = textPass.getText().length() > 5;
			if (!ret)
				Mensaje.getInstancia().Show(asIdioma.getString(Defines.IDIOMA_PASS_ERROR), false);
			else {
				File f = new File(asPrefs.resDir() + textFilename.getText() + "." + txtFilenameExt.getText());
				ret = !f.exists();
				if (!ret)
					Mensaje.getInstancia().Show(asIdioma.getString(Defines.IDIOMA_KEYTOOL_FILEXISTS), false);
			}
		}
		return ret;
	}
	
	/*
	 * Se llama al terminar de crear los componentes visuales (createContents). Si aún no hay una clave de codificación es que es la primera vez, así que
//	 * no se instancia la clase y se espera a que el usuario la cree (btnNewButtonGuardaClave de la pestaña Configuración).
	 */
	private void CreaJarSigner()
	{
		if (!asPrefs.Pref(Defines.PREF_CLAVE).isEmpty())
		{
			try {
				asJarSigner = new JarSigner(table);
				table.setSelection(asJarSigner.getSelect());
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				Mensaje.getInstancia().Show(e1.getMessage(), false);
			} catch (InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				Mensaje.getInstancia().Show(e1.getMessage(), false);
			} catch (IllegalBlockSizeException e1) {
				// TODO Auto-generated catch block
				Mensaje.getInstancia().Show(e1.getMessage(), false);
			} catch (BadPaddingException e1) {
				// TODO Auto-generated catch block
				Mensaje.getInstancia().Show(e1.getMessage(), false);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				Mensaje.getInstancia().Show(e1.getMessage(), false);
			}
		}		
	}

	private void CreaKTObserver() {
		listaKT.add(textFilename); // Se envian a KTObserver los campos que
									// deben llenarse para activar el botón de
									// crear clave
		listaKT.add(txtFilenameExt);
		listaKT.add(textAlias);
		listaKT.add(textPass);
		for (int i = 0; i < listaKT.size(); i++)
			listaKT.get(i).addModifyListener(vlModifText);
		KTObserver = new KeyToolObserver(listaKT, btnNewButtonCrearClave);
	}

	public static void openWebpage(String url) {
		URI uri = URI.create(url);
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            Mensaje.getInstancia().Show(e.getMessage(), false);
	        }
	    }
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAndroidSigner = new Shell(SWT.CLOSE | SWT.TITLE);
		shlAndroidSigner.setImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/icon.png"));
		shlAndroidSigner.setSize(684, 422);
		shlAndroidSigner.setText("Android Signer");
		asPrefs.setShell(shlAndroidSigner);

		try {
			paises = new Paises(asIdioma);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			Mensaje.getInstancia().Show(e1.getMessage(), false);
		}

		Monitor pantalla = display.getPrimaryMonitor();
		Rectangle limits = pantalla.getBounds();
		Rectangle rect = shlAndroidSigner.getBounds();
		int x = limits.x + (limits.width - rect.width) / 2;
		int y = limits.y + (limits.height - rect.height) / 2;
		shlAndroidSigner.setLocation(x, y);

		tabFolder = new CTabFolder(shlAndroidSigner, SWT.NONE);
		tabFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				asPrefs.GuardaPref(Defines.PREF_TAB, tabFolder.getSelectionIndex());
			}
		});
		tabFolder.setBounds(0, 0, 678, 372);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem tbtmKeyTool = new CTabItem(tabFolder, SWT.NONE);
		tbtmKeyTool.setImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/keytool.png"));
		tbtmKeyTool.setText("KeyTool");

		compositeKeyTool = new Composite(tabFolder, SWT.NONE);
		tbtmKeyTool.setControl(compositeKeyTool);

		lblNewLabel_1 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_1.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_1.setBounds(10, 7, 135, 15);

		lblNewLabel_2 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_2.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_2.setBounds(10, 32, 135, 15);

		lblNewLabel_3 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_3.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_3.setBounds(10, 58, 135, 15);

		lblNewLabel_4 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_4.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_4.setBounds(10, 81, 135, 15);

		lblNewLabel_5 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_5.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_5.setBounds(10, 108, 135, 15);

		lblNewLabel_6 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_6.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_6.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_6.setBounds(10, 136, 135, 15);

		lblNewLabel_7 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_7.setBounds(10, 164, 135, 15);

		lblNewLabel_8 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_8.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_8.setBounds(157, 164, 85, 15);

		lblNewLabel_9 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_9.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_9.setBounds(157, 190, 85, 15);

		lblNewLabel_10 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_10.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_10.setBounds(157, 216, 85, 15);

		lblNewLabel_11 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_11.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_11.setBounds(157, 243, 85, 15);

		lblNewLabel_12 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_12.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_12.setBounds(157, 270, 85, 15);

		lblNewLabel_13 = new Label(compositeKeyTool, SWT.RIGHT);
		lblNewLabel_13.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_13.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_13.setBounds(157, 295, 85, 15);

		textFilename = new Text(compositeKeyTool, SWT.BORDER);
		textFilename.setBounds(150, 4, 162, 21);

		textAlias = new Text(compositeKeyTool, SWT.BORDER);
		textAlias.setTextLimit(8);
		textAlias.setBounds(150, 30, 72, 21);

		final Button btnRadioButtonRSA = new Button(compositeKeyTool, SWT.RADIO);
		btnRadioButtonRSA.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnRadioButtonRSA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculaComboKeySize(29);
			}
		});
		btnRadioButtonRSA.setSelection(true);
		btnRadioButtonRSA.setBounds(150, 59, 42, 16);
		btnRadioButtonRSA.setText("RSA");

		Button btnRadioButtonDSA = new Button(compositeKeyTool, SWT.RADIO);
		btnRadioButtonDSA.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnRadioButtonDSA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculaComboKeySize(11);
			}
		});
		btnRadioButtonDSA.setBounds(212, 59, 43, 16);
		btnRadioButtonDSA.setText("DSA");

		textPass = new Text(compositeKeyTool, SWT.BORDER);
		textPass.setBounds(150, 133, 162, 21);

		textName = new Text(compositeKeyTool, SWT.BORDER);
		textName.setBounds(250, 162, 144, 21);

		textDepart = new Text(compositeKeyTool, SWT.BORDER);
		textDepart.setBounds(250, 187, 144, 21);

		textOrg = new Text(compositeKeyTool, SWT.BORDER);
		textOrg.setBounds(250, 213, 144, 21);

		textCity = new Text(compositeKeyTool, SWT.BORDER);
		textCity.setBounds(250, 239, 119, 21);

		textStat = new Text(compositeKeyTool, SWT.BORDER);
		textStat.setBounds(250, 265, 119, 21);

		txtFilenameExt = new Text(compositeKeyTool, SWT.BORDER);
		txtFilenameExt.setText(Defines.DEFAULT_KEYFILE_EXT);
		txtFilenameExt.setBounds(318, 4, 76, 21);

		lblNewLabel_14 = new Label(compositeKeyTool, SWT.NONE);
		lblNewLabel_14.setBounds(313, 7, 3, 15);
		lblNewLabel_14.setText(".");

		btnNewButtonCrearClave = new Button(compositeKeyTool, SWT.NONE);
		btnNewButtonCrearClave.setImage(SWTResourceManager.getImage(
				AndroidSigner.class, "/recursos/android/signer/key.png"));
		btnNewButtonCrearClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (validaDatosKeyTool()) {
					listaDC.put(Defines.KT_KEYSTORE_FNAME, textFilename.getText());
					listaDC.put(Defines.KT_KEYSTORE_FEXT, txtFilenameExt.getText());
					listaDC.put(Defines.KT_ALIAS, textAlias.getText());
					listaDC.put(Defines.KT_PASS, textPass.getText());
					listaDC.put(Defines.KT_DNAME_NOM, textName.getText());
					listaDC.put(Defines.KT_DNAME_DEPART, textDepart.getText());
					listaDC.put(Defines.KT_DNAME_ORG, textOrg.getText());
					listaDC.put(Defines.KT_DNAME_CIUDAD, textCity.getText());
					listaDC.put(Defines.KT_DNAME_STAT, textStat.getText());
					listaDC.put(Defines.KT_DNAME_PAIS, paises.codigoPais());
					listaDC.put(Defines.KT_KEYSIZE, comboKeySize.getItem(comboKeySize.getSelectionIndex()));
					listaDC.put(Defines.KT_VALIDEZ, comboYears.getItem(comboYears.getSelectionIndex()));
					KeyTool dc = new KeyTool(listaDC, btnRadioButtonRSA.getSelection());
					asPrefs.setMsg(asIdioma.getString(Defines.IDIOMA_EXE_KEYTOOL));
					dc.Ejecuta();
					try {
						asJarSigner.buscaKeyStores();
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						Mensaje.getInstancia().Show(e1.getMessage(), false);
					} catch (InvalidAlgorithmParameterException e1) {
						// TODO Auto-generated catch block
						Mensaje.getInstancia().Show(e1.getMessage(), false);
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						Mensaje.getInstancia().Show(e1.getMessage(), false);
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						Mensaje.getInstancia().Show(e1.getMessage(), false);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						Mensaje.getInstancia().Show(e1.getMessage(), false);
					}
					CargaPreferencias(false);
				}
			}
		});
		btnNewButtonCrearClave.setEnabled(false);
		btnNewButtonCrearClave.setBounds(542, 33, 111, 64);

		comboKeySize = new Combo(compositeKeyTool, SWT.READ_ONLY);
		comboKeySize.setBounds(151, 78, 91, 23);
		calculaComboKeySize(29);

		comboYears = new Combo(compositeKeyTool, SWT.READ_ONLY);
		comboYears.setBounds(151, 105, 91, 23);
		for (int i = Defines.INICIO_ANNOS_VALIDEZ; i < Defines.INICIO_ANNOS_VALIDEZ
				* Defines.ANNOS_VALIDEZ; i += Defines.INTERVAL_ANNOS_VALIDEZ)
			comboYears.add(String.valueOf(i));
		comboYears.select(4);

		lblNewLabel_15 = new Label(compositeKeyTool, SWT.NONE);
		lblNewLabel_15.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_15.setBounds(250, 81, 19, 15);
		lblNewLabel_15.setText("bits");

		lblNewLabelYears = new Label(compositeKeyTool, SWT.NONE);
		lblNewLabelYears.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabelYears.setBounds(250, 108, 65, 15);

		comboPais = new Combo(compositeKeyTool, SWT.READ_ONLY);
		comboPais.setBounds(250, 292, 205, 23);
		while (paises.hayMas())
			comboPais.add(paises.nomPais());
		comboPais.select(paises.paisActual());
		compositeKeyTool.setTabList(new Control[] { textFilename,
				txtFilenameExt, textAlias, btnRadioButtonRSA,
				btnRadioButtonDSA, comboKeySize, comboYears, textPass,
				textName, textDepart, textOrg, textCity, textStat, comboPais,
				btnNewButtonCrearClave });

		tbtmJarsigner = new CTabItem(tabFolder, SWT.CLOSE);
		tbtmJarsigner.setShowClose(false);
		tbtmJarsigner.setImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/jarsigner.png"));
		tbtmJarsigner.setText("JarSigner");

		compositeJarSigner = new Composite(tabFolder, SWT.NONE);
		tbtmJarsigner.setControl(compositeJarSigner);

		lblNewLabelJarSignerFirma = new Label(compositeJarSigner, SWT.NONE);
		lblNewLabelJarSignerFirma.setBounds(160, 49, 387, 15);

		table = new Table(compositeJarSigner, SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		table.setSortDirection(SWT.UP);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Preferencias.getInstancia().GuardaPref(Defines.PREF_JARSIGNERSIGN, table.getItem(table.getSelectionIndex()).getText());
				CargaPreferencias(false);
			}
		});
		table.setBounds(50, 70, 571, 84);
		table.setHeaderVisible(true);

		tblclmnArchivo = new TableColumn(table, SWT.CENTER);
		tblclmnArchivo.setResizable(false);
		tblclmnArchivo.setWidth(202);

		tblclmnAloritmo = new TableColumn(table, SWT.NONE);
		tblclmnAloritmo.setResizable(false);
		tblclmnAloritmo.setWidth(67);

		tblclmnClave = new TableColumn(table, SWT.CENTER);
		tblclmnClave.setResizable(false);
		tblclmnClave.setWidth(181);

		tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setResizable(false);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Alias");

		lblNewLabel_16 = new Label(compositeJarSigner, SWT.RIGHT);
		lblNewLabel_16.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_16.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_16.setBounds(22, 23, 132, 15);

		lblNewLabelJarSignerFile = new Label(compositeJarSigner, SWT.NONE);
		lblNewLabelJarSignerFile.setBounds(160, 23, 387, 15);

		btnNewButtonBuscaFirmar = new Button(compositeJarSigner, SWT.NONE);
		btnNewButtonBuscaFirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				JFileChooser archivo = new JFileChooser();
				if (archivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					if (archivo.getSelectedFile().getName().lastIndexOf(".") < 0)
						Mensaje.getInstancia().Show(asIdioma.getString(Defines.IDIOMA_FILE_ERROR), false);
					else
					{
						Preferencias.getInstancia().GuardaJarSignerFile(archivo.getSelectedFile());
						CargaPreferencias(false);
					}
				}
			}
		});
		btnNewButtonBuscaFirmar.setBounds(576, 17, 75, 25);

		btnUsar = new Button(compositeJarSigner, SWT.CHECK);
		btnUsar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				asPrefs.GuardaPref(Defines.PREF_APPLYZIPALIGN, btnUsar.getSelection());
				CargaPreferencias(false);
			}
		});
		btnUsar.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnUsar.setOrientation(SWT.RIGHT_TO_LEFT);
		btnUsar.setBounds(10, 176, 144, 16);

		lblNewLabel_18 = new Label(compositeJarSigner, SWT.NONE);
		lblNewLabel_18.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_18.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_18.setBounds(51, 198, 232, 15);

		textZipAlignAdd = new Text(compositeJarSigner, SWT.BORDER);
		textZipAlignAdd.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				asPrefs.GuardaPref(Defines.PREF_ZIPALIGNEXT, textZipAlignAdd.getText());
				CargaPreferencias(false);
			}
		});
		textZipAlignAdd.setBounds(303, 219, 121, 21);

		lblNewLabelZipAlignExt = new Label(compositeJarSigner, SWT.NONE);
		lblNewLabelZipAlignExt.setBounds(430, 222, 191, 15);

		lblNewLabel_22 = new Label(compositeJarSigner, SWT.RIGHT);
		lblNewLabel_22.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel_22.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel_22.setBounds(50, 49, 104, 15);

		btnNewButtonFirmar = new Button(compositeJarSigner, SWT.NONE);
		btnNewButtonFirmar.setImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/sign.png"));
		btnNewButtonFirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				asPrefs.setMsg(asIdioma.getString(Defines.IDIOMA_EXE_JARSIGNER));
				asJarSigner.Ejecuta(table.getItem(table.getSelectionIndex()).getText(2), table.getItem(table.getSelectionIndex()).getText(3), btnUsar.getSelection());
			}
		});
		btnNewButtonFirmar.setEnabled(false);
		btnNewButtonFirmar.setBounds(519, 244, 132, 64);

		tbtmConfiguracion = new CTabItem(tabFolder, SWT.NONE);
		tbtmConfiguracion.setImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/config.png"));

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		tbtmConfiguracion.setControl(composite_1);

		lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblNewLabel
				.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel.setBounds(50, 122, 463, 15);

		lblRutaKeyTool = new Label(composite_1, SWT.NONE);
		lblRutaKeyTool.setBounds(50, 147, 500, 15);

		btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				JFileChooser archivo = new JFileChooser();
				FiltroArchivo filtro = new FiltroArchivo();
				filtro.add(Defines.KEYTOOL_EXE);
				filtro.add(Defines.JARSIGNER_EXE);
				archivo.setFileFilter(filtro);
				if (archivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					asPrefs.GuardaPref(Defines.PREF_KEYTOOLPATH, archivo.getSelectedFile().getPath().substring(0, archivo.getSelectedFile().getPath().indexOf(archivo.getSelectedFile().getName()) - 1));
				CargaPreferencias(false);
			}
		});
		btnNewButton.setBounds(576, 137, 75, 25);

		lblIdioma = new Label(composite_1, SWT.NONE);
		lblIdioma.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblIdioma.setBounds(50, 13, 142, 15);

		comboIdioma = new Combo(composite_1, SWT.READ_ONLY);
		comboIdioma.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CambiaIdioma(comboIdioma.getSelectionIndex());
			}
		});
		comboIdioma.setItems(new String[] {"Catal\u00E0", "English", "Espa\u00F1ol", "Italiano"});
		comboIdioma.setBounds(110, 34, 94, 23);
		comboIdioma.select((asIdioma.Idx() < 0) ? 1 : asIdioma.Idx());

		lblNewLabelClave = new Label(composite_1, SWT.NONE);
		lblNewLabelClave.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblNewLabelClave.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabelClave.setBounds(50, 66, 222, 15);

		txtClave = new Text(composite_1, SWT.BORDER);
		txtClave.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				int l = txtClave.getText().length();
				lblNewLabel24Chars.setText(String.valueOf(l) + " "
						+ asIdioma.getString(Defines.IDIOMA_CLAVE24CHARS));
				btnNewButtonGuardaClave.setEnabled(l == 16);
			}
		});
		txtClave.setTextLimit(16);
		txtClave.setBounds(110, 91, 161, 21);

		lblNewLabel24Chars = new Label(composite_1, SWT.NONE);
		lblNewLabel24Chars.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		lblNewLabel24Chars.setBounds(277, 94, 142, 15);

		btnNewButtonGuardaClave = new Button(composite_1, SWT.NONE);
		btnNewButtonGuardaClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (Mensaje.getInstancia().Show(asIdioma.getString(Defines.IDIOMA_CLAVE_SAVE), true) == SWT.OK)
				{
					asPrefs.GuardaPref(Defines.PREF_CLAVE, txtClave.getText());
					Datos.getInstancia();
					CreaJarSigner();
					CargaPreferencias(false);
				}
			}
		});
		btnNewButtonGuardaClave.setEnabled(false);
		btnNewButtonGuardaClave.setBounds(576, 91, 75, 25);
		
		lblNewLabelZipAlign = new Label(composite_1, SWT.NONE);
		lblNewLabelZipAlign.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabelZipAlign.setBounds(50, 185, 323, 15);
		
		lblNewLabelZipAlignPath = new Label(composite_1, SWT.NONE);
		lblNewLabelZipAlignPath.setBounds(50, 210, 500, 15);
		
		btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				JFileChooser archivo = new JFileChooser();
				FiltroArchivo filtro = new FiltroArchivo();
				filtro.add(Defines.ZIPALIGN_EXE);
				archivo.setFileFilter(filtro);
				if (archivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					asPrefs.GuardaPref(Defines.PREF_ZIPALIGNPATH, archivo.getSelectedFile().getPath().substring(0, archivo.getSelectedFile().getPath().indexOf(archivo.getSelectedFile().getName()) - 1));
				CargaPreferencias(false);
			}
		});
		btnNewButton_1.setBounds(576, 204, 75, 25);
		
		labelRequired = new Label(composite_1, SWT.CENTER);
		labelRequired.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		labelRequired.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		labelRequired.setBounds(553, 13, 111, 15);

		lblNewLabelZipAlignFilename = new Label(compositeJarSigner, SWT.RIGHT);
		lblNewLabelZipAlignFilename.setBounds(50, 222, 248, 15);
		lblNewLabelZipAlignFilename.setText("New Label");

		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/cp.png"));
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				asPrefs.GuardaPref(Defines.PREF_TAB, tabFolder.getSelectionIndex());
			}
		});
		tabItem.setControl(composite);
		
		Label lblNewLabel_17 = new Label(composite, SWT.CENTER);
		lblNewLabel_17.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel_17.setBounds(196, 31, 272, 15);
		lblNewLabel_17.setText("Eduard Mill\u00E1n Forn");
		
		Label lblNewLabel_19 = new Label(composite, SWT.CENTER);
		lblNewLabel_19.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel_19.setBounds(196, 52, 272, 15);
		lblNewLabel_19.setText(Defines.VERSION);
		
		Canvas canvas = new Canvas(composite, SWT.NONE);
		canvas.setBackgroundImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/androidsigner.png"));
		canvas.setBounds(196, 156, 278, 127);
		
		Canvas canvas_1 = new Canvas(composite, SWT.NONE);
		canvas_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		canvas_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				openWebpage("http://codementia.blogspot.com.es/");
			}
		});
		canvas_1.setBackgroundImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/blogger-icon.png"));
		canvas_1.setBounds(254, 90, 30, 30);
		
		Canvas canvas_2 = new Canvas(composite, SWT.NONE);
		canvas_2.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		canvas_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				openWebpage("https://play.google.com/store/apps/developer?id=Eduard+Mill%C3%A1n+Forn#?t=W251bGwsbnVsbCxudWxsLDEwMiwiY29tLnVkYy5tZWRpY2FsLnRyaWFsIl0.");
			}
		});
		canvas_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		canvas_2.setBackgroundImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/google play.png"));
		canvas_2.setBounds(377, 90, 30, 33);
		
		Canvas canvas_3 = new Canvas(composite, SWT.NONE);
		canvas_3.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		canvas_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				openWebpage("http://www.linkedin.com/pub/eduard-mill%C3%A1n-forn/53/178/552");
			}
		});
		canvas_3.setBackgroundImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/linkedin-blogger.png"));
		canvas_3.setBounds(196, 90, 32, 32);
		
		Canvas canvas_4 = new Canvas(composite, SWT.NONE);
		canvas_4.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		canvas_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				openWebpage("https://www.facebook.com/pages/EMF/488679697862415");
			}
		});
		canvas_4.setBackgroundImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/facebook-blog.png"));
		canvas_4.setBounds(436, 90, 31, 31);
		
		Canvas canvas_5 = new Canvas(composite, SWT.NONE);
		canvas_5.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		canvas_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				openWebpage("https://twitter.com/EMillanForn");
			}
		});
		canvas_5.setBackgroundImage(SWTResourceManager.getImage(AndroidSigner.class, "/recursos/android/signer/twiter-blogger.png"));
		canvas_5.setBounds(317, 90, 32, 32);

		lblNewLabelStatus = new Label(shlAndroidSigner, SWT.NONE);
		lblNewLabelStatus.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabelStatus.setBounds(4, 374, 297, 17);

		asPrefs.defMsg(lblNewLabelStatus);
		CambiaIdioma(asIdioma.Idx());
		CreaKTObserver();
		CreaJarSigner();
	}
}
