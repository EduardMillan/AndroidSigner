/*
 * 	Android Signer 
 * 	Eduard Millán Forn
 * 	http://codementia.blogspot.com.es
 * 	Open Source
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public final class Paises {
	
	private List<List<String>> Pais = new ArrayList<List<String>>();
	private int cntPais, cntActivo = 0, paisActivo = -1;
	
	Paises(Idioma elPais) throws IOException
	{
		Scanner in = new Scanner(getClass().getResourceAsStream(Defines.PATH_RECURSOS + Defines.PAISES));	
		
		while (in.hasNextLine())
		{
			Scanner linea = new Scanner(in.nextLine()).useDelimiter(",");
			while (linea.hasNext())
			{
				List<String> data = new ArrayList<String>();
				data.add(linea.next());	//	Código país
				data.add(linea.next());	//	Català
				data.add(linea.next());	//	Castellano
				data.add(linea.next());	//	Italiano
				data.add(linea.next());	//	English
				Pais.add(data);
			}
		}
		ordenaPaises(elPais.Idx(), elPais.ISO3611code());
		in.close();
	}
	
	@SuppressWarnings("unchecked")
	private void ordenaPaises(int idxPais, String codePais)
	{
		cntPais = idxPais + 1;
		String codPais = codePais;

		Object[] temp = Pais.toArray();	//	Convertimos List en un Array
		Arrays.sort(temp, new Comparator<Object>() {	//	Aplicamos sort obteniendo la lista ordenada en temp
		    @Override
		    public int compare(Object o1, Object o2) {
		        ArrayList<String> temp1 = (ArrayList<String>) o1;	// Leemos arrays internos con conversión de tipo
		        ArrayList<String> temp2 = (ArrayList<String>) o2;	
		        return temp1.get(cntPais).compareTo(temp2.get(cntPais));	// y comparamos los indices que nos interesan
		    }
		});
		Pais.clear();	//	Vaciamos la lista de paises
		int i = 0;
		for (Object o: temp)	//	y copiamos la lista ordenada en la lista original
		{
			Pais.add((ArrayList<String>) o);
			if (Pais.get(i).get(0).compareToIgnoreCase(codPais) == 0)
				paisActivo = i;
			i++;
		}
		
	}
	
	public boolean hayMas()
	{
		boolean ret = cntActivo < Pais.size();
		
		if (!ret)
			cntActivo = 0;
		return ret;
	}

	public String nomPais()
	{
		return Pais.get(cntActivo++).get(cntPais);
	}
	
	public int paisActual()
	{
		return paisActivo;
	}
	
	public String codigoPais()
	{
		return Pais.get(paisActivo).get(0);
	}
	
	public void cambiaPais(Idioma elPais)
	{
		ordenaPaises(elPais.Idx(), elPais.ISO3611code());
	}
}
