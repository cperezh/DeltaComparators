package carlos.tools.comparator;

import java.util.ArrayList;
import java.util.List;

import carlos.tools.comparator.entidades.Coche;
import carlos.tools.comparator.entidades.incremental.CocheIncremental;
import carlos.tools.comparator.entidades.incremental.Concepto;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ComparadorTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ComparadorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ComparadorTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		List<CocheIncremental> cochesIncremental;
		List<Coche> listaInicial;
		List<Coche> listaNueva;
		List<Coche> nuevosCoches;
		List<Coche> cochesModificados;
		List<Coche> cochesBorrados;

		Comparador comparador = new Comparador();

		listaInicial = generarListaInicial();

		listaNueva = generarListaNueva(listaInicial);

		nuevosCoches = generarCochesPruebaA(listaNueva);

		cochesModificados = generarCochesPruebaM(listaNueva);

		cochesBorrados = generarCochesPruebaB(listaNueva);

		cochesIncremental = comparador.generarIncremental(listaInicial, listaNueva);

		assertTrue(comprobarExitoPrueba(cochesIncremental, nuevosCoches, cochesModificados, cochesBorrados));
	}

	private List<Coche> generarListaInicial() {

		List<Coche> listaInicial = CocheTestUtils.generarListaCochesAleatorios(3);

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

		listaPruebaB.add(listaInicial.get(2));

		listaInicial.remove(2);

		return listaPruebaB;
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
			List<Coche> cochesModificados, List<Coche> cochesBorrados) {

		Boolean nuevosOk, modificadosOk, borradoOk;

		nuevosOk = verificarIncremental(cochesIncremental, nuevosCoches, Concepto.A);
		
		modificadosOk = verificarIncremental(cochesIncremental,cochesModificados,Concepto.M);
		
		borradoOk = verificarIncremental(cochesIncremental, cochesBorrados, Concepto.B);
		
		return nuevosOk && modificadosOk && borradoOk;
	}

	/**
	 * Verifica que todos los elementos de la lista ListaCoches tieneN el
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

			cocheIncremental = buscarCoche(cochesIncremental, coche);

			if (cocheIncremental==null || cocheIncremental.getConcepto() != concepto) {
				verificar = false;
			}
		}

		return verificar;

	}

	private CocheIncremental buscarCoche(List<CocheIncremental> cochesIncremental, Coche coche) {

		for (CocheIncremental cocheIncremental : cochesIncremental) {
			if (cocheIncremental.getMatricula().equals(coche.getMatricula())) {
				return cocheIncremental;
			}
		}

		return null;
	}

	
}
