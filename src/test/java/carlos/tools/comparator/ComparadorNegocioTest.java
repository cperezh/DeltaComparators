package carlos.tools.comparator;

import java.util.ArrayList;
import java.util.List;

import carlos.tools.comparator.entidades.Coche;
import carlos.tools.comparator.entidades.incremental.CocheIncremental;
import carlos.tools.comparator.entidades.incremental.Concepto;
import junit.framework.TestCase;

public class ComparadorNegocioTest extends TestCase {
	
	
	public void generarIncrementalNegocio() {

		List<CocheIncremental> cochesIncremental;
		List<Coche> listaInicial;
		List<Coche> listaNueva;
		List<Coche> nuevosCoches;
		List<Coche> cochesModificados;
		List<Coche> cochesBorrados;
		List<Coche> cochesNoModificados;

		listaInicial = generarListaInicial();

		listaNueva = generarListaNueva(listaInicial);
		
		nuevosCoches = generarCochesPruebaA(listaNueva);

		cochesModificados = generarCochesPruebaM(listaNueva);

		cochesBorrados = generarCochesPruebaB(listaNueva);
		
		cochesNoModificados = generarCochesPruebaNoModificados(listaInicial);
		
		cochesIncremental = ComparadorNegocio.generarIncrementalNegocio(listaInicial, listaNueva);

		assertTrue(comprobarExitoPrueba(cochesIncremental, nuevosCoches, cochesModificados, cochesBorrados,cochesNoModificados));
	}

	private List<Coche> generarListaInicial() {

		List<Coche> listaInicial = CocheTestUtils.generarListaCochesAleatorios(5);

		return listaInicial;
	}
	
	private List<Coche> generarListaNueva(List<Coche> listaCochesInicial) {

		List<Coche> listaNueva = CocheTestUtils.clonarListaCoches(listaCochesInicial);

		return listaNueva;
	}

	

	/**
	 * Añado conches nuevas a la lista
	 * 
	 * @param listaInicial
	 * @return
	 */
	private List<Coche> generarCochesPruebaA(List<Coche> listaInicial) {

		List<Coche> listaPruebaA = CocheTestUtils.generarListaCochesAleatorios(2);

		listaInicial.addAll(listaPruebaA);

		return listaPruebaA;
	}

	/**
	 * Modifico los dos primeros coches
	 * 
	 * @return
	 */
	private List<Coche> generarCochesPruebaM(List<Coche> listaInicial) {

		Coche coche;
		List<Coche> listaPruebaM = new ArrayList<Coche>();

		for (int i = 0; i < 2; i++) {
			
			coche = listaInicial.get(i);
		
			coche = CocheTestUtils.modificarCocheAleatorio(coche);

			listaPruebaM.add(coche);
		}

		return listaPruebaM;
	}

	private List<Coche> generarCochesPruebaB(List<Coche> listaInicial) {

		List<Coche> listaPruebaB = new ArrayList<Coche>();
		Coche coche1;
		Coche coche2;
		
		coche1 = listaInicial.get(2);
		coche2 = listaInicial.get(3);
		
		listaInicial.remove(coche1);
		
		listaInicial.remove(coche2);
		
		listaPruebaB.add(coche1);
		
		listaPruebaB.add(coche2);

	

		return listaPruebaB;
	}
	
	private List<Coche> generarCochesPruebaNoModificados(List<Coche> listaInicial) {

		List<Coche> listaPruebaNoModificados = new ArrayList<Coche>();

		listaPruebaNoModificados.add(listaInicial.get(4));

		return listaPruebaNoModificados;
	}

	/**
	 * Comprueba que las altas tienen A, las modficaciones M y las bajas B
	 * 
	 * @param cochesIncremental
	 * @param nuevosCoches
	 * @param cochesModificados
	 * @param cochesBorrados
	 * @return
	 */
	private boolean comprobarExitoPrueba(List<CocheIncremental> cochesIncremental, List<Coche> nuevosCoches,
			List<Coche> cochesModificados, List<Coche> cochesBorrados,  List<Coche> cochesNoModificados) {

		Boolean nuevosOk, modificadosOk, borradoOk, noModificadosOk;

		nuevosOk = verificarIncremental(cochesIncremental, nuevosCoches, Concepto.A);
		
		modificadosOk = verificarIncremental(cochesIncremental,cochesModificados,Concepto.M);
		
		borradoOk = verificarIncremental(cochesIncremental, cochesBorrados, Concepto.B);
		
		noModificadosOk = CocheIncremental.find(cochesNoModificados.get(0),cochesIncremental)==null;
		
		return nuevosOk && modificadosOk && borradoOk && noModificadosOk;
	}

	/**
	 * Verifica que todos los elementos de la lista ListaCoches tienen el
	 * concepto en cochesIncremental
	 * 
	 * @param cochesIncremental
	 * @param listaCoches
	 * @param concepto
	 * @return
	 */
	private Boolean verificarIncremental(List<CocheIncremental> cochesIncremental, List<Coche> listaCoches,
			Concepto concepto) {

		CocheIncremental cocheIncremental;
		Boolean verificar = true;

		for (Coche coche : listaCoches) {

			cocheIncremental =(CocheIncremental) Coche.find(coche,cochesIncremental);

			if (cocheIncremental==null || cocheIncremental.getConcepto() != concepto) {
				verificar = false;
			}
		}

		return verificar;

	}
	
}
