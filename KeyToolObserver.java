/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Licencia Open Source
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;


public class KeyToolObserver {
	
	private Map<Integer, List<Object>> ListTxt = new HashMap<Integer, List<Object>>();	//	HashMap indexado por los hashCode() del componente Text
	private Button Boton;
	
	public KeyToolObserver(List<Text> txts, Button btn)
	{
		List<Object> lTextos;
		
		for (int i = 0; i < txts.size(); i++)	//	Se guarda cada Text y el valor bool true = contiene texto, false = vacio
		{
			lTextos  = new ArrayList<Object>();
			lTextos.add(txts.get(i));
			lTextos.add(txts.get(i).getText().length() > 0);
			ListTxt.put(txts.get(i).hashCode(), lTextos);
		}
		Boton = btn;
	}
	
	public void Actualiza(int hash)	//	Se mira si el Text de indice hash esta vacio o contiene texto y se establece el bool correspondiente
	{
		boolean ok = true;
		
		((ArrayList<Object>) ListTxt.get(hash)).set(1, ((Text)ListTxt.get(hash).get(0)).getText().length() > 0);
		Iterator<Map.Entry<Integer, List<Object>>> entradas = ListTxt.entrySet().iterator();
		while (entradas.hasNext() && ok)	//	Se recorren los valores bool para definir el estado final del boton
			ok = (Boolean) entradas.next().getValue().get(1);
		Boton.setEnabled(ok);
	}

}
