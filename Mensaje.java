/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;


public class Mensaje implements Serializable {	//	Singleton
	
	private static final long serialVersionUID = 2L;
	private MessageBox avisoMB, infoMB;
	
	private Mensaje()
	{
		Preferencias prefs = Preferencias.getInstancia();

		avisoMB = new MessageBox(prefs.getShell(), SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
		avisoMB.setText(prefs.getIdioma().getString(Defines.IDIOMA_AVISO));
		infoMB = new MessageBox(prefs.getShell(), SWT.ICON_INFORMATION | SWT.CLOSE);
		infoMB.setText(prefs.getIdioma().getString(Defines.IDIOMA_INFO));
	}
	
    private static class MensajeSingletonContainer {
        public static final Mensaje INSTANCIA = new Mensaje();
    }
	 
    public static Mensaje getInstancia() {
        return MensajeSingletonContainer.INSTANCIA;
    }
	 
    protected Object readResolve() {
        return getInstancia();
    }
	
	int Show(String msg, boolean aviso)
	{
		MessageBox mb = (aviso) ? avisoMB : infoMB;
		
		mb.setMessage(msg);
		return mb.open();
	}

}
