/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FiltroArchivo extends javax.swing.filechooser.FileFilter {
	
	List<String> filtro = new ArrayList<String>();
	
    public boolean accept(File f) {
    	boolean ok = false;
    	int i = 0;
    	
    	while ((i < filtro.size()) && !ok)
    		ok = f.getName().toLowerCase().endsWith(filtro.get(i++).toLowerCase());
        return f.isDirectory() || ok;
    }
    
    public String getDescription()
    {
    	String dLista = "";
    	
    	for (int i = 0; i < filtro.size(); i++)
			dLista += filtro.get(i) + " | ";
		dLista = dLista.substring(0, dLista.length() - 2);
    	return dLista;
    }
    
    public void add(String f)
    {
    	filtro.add(f);
    }
}
