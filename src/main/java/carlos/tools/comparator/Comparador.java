package carlos.tools.comparator;

import java.util.ArrayList;
import java.util.List;

import carlos.tools.comparator.entidades.Coche;
import carlos.tools.comparator.entidades.incremental.CocheIncremental;
import carlos.tools.comparator.entidades.incremental.Concepto;

/**
 * Hello world!
 *
 */
public class Comparador {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	protected List<CocheIncremental> generarIncremental(List<Coche> listaInicial, List<Coche> listaNueva) {

		List<CocheIncremental> listaIncremental = new ArrayList<CocheIncremental>();

		listaIncremental.addAll(generarAltasYModificaciones(listaInicial, listaNueva));
		listaIncremental.addAll(generarBajas(listaInicial, listaNueva));

		return listaIncremental;
	}

	private List<CocheIncremental> generarAltasYModificaciones(List<Coche> listaInicial, List<Coche> listaNueva) {

		Coche cocheInicial;
		CocheIncremental cocheIncremental;
		List<CocheIncremental> listaIncremental = new ArrayList<CocheIncremental>();

		for (Coche coche : listaNueva) {
			
			cocheInicial = Coche.find(coche, listaInicial);
			
			// Si el coche está en la lista inicial
			if (cocheInicial!=null) {

				//Si es diferente al cocheInicial, genero M
				if (!cocheInicial.equals(coche)) {
					cocheIncremental = new CocheIncremental(coche);
					cocheIncremental.setConcepto(Concepto.M);
					
					listaIncremental.add(cocheIncremental);
				}
			}
		}
		return listaIncremental;
	}

	private List<CocheIncremental> generarBajas(List<Coche> listaInicial, List<Coche> listaNueva) {
		return null;
	}

}
