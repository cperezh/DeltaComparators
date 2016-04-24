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

		listaNueva = clonarLista(listaInicial);

		nuevosCoches = generarCochesPruebaA(listaNueva);

		cochesModificados = generarCochesPruebaM(listaNueva);

		cochesBorrados = generarCochesPruebaB(listaNueva);

		cochesIncremental = comparador.generarIncremental(listaInicial, listaNueva);

		assertTrue(comprobarExitoPrueba(cochesIncremental, nuevosCoches, cochesModificados, cochesBorrados));
	}

	private List<Coche> generarListaInicial() {

		List<Coche> listaInicial = new ArrayList<Coche>();

		Coche coche;

		for (int i = 0; i < 3; i++) {

			coche = generarCocheAleatorio();

			listaInicial.add(coche);
		}

		return listaInicial;
	}

	private List<Coche> clonarLista(List<Coche> lista) {

		List<Coche> listaClon = new ArrayList<Coche>();

		for (Coche elemento : lista) {
			listaClon.add(elemento.clone());
		}

		return listaClon;
	}

	/**
	 * Añado conches nuevas a la lista
	 * 
	 * @param listaInicial
	 * @return
	 */
	private List<Coche> generarCochesPruebaA(List<Coche> listaInicial) {

		List<Coche> listaPruebaA = new ArrayList<Coche>();

		Coche coche;

		for (int i = 0; i < 2; i++) {

			coche = generarCocheAleatorio();

			listaPruebaA.add(coche);
		}

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
			coche.setColor(randomString(6));
			coche.setPrecio(randomFloat());

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
		
		Boolean exito;

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

	private Coche generarCocheAleatorio() {

		Coche coche;

		String matricula, color;
		Float precio;

		matricula = randomString(7);
		color = randomString(6);
		precio = randomFloat();

		coche = new Coche(matricula, color, precio);

		return coche;
	}

	/**
	 * Genera String con cartacteres UTF16 entre el 65 y el 122 (A-z)
	 * 
	 * @param numCaracteres
	 * @return
	 */
	private String randomString(int numCaracteres) {
		double c;
		char[] chars;
		int unicode;
		String string = "";

		for (int i = 0; i < numCaracteres; i++) {

			c = Math.random();
			c = c * 100;
			c = c % 58;
			c = c + 65;

			unicode = (int) c;
			// unicode = 41;

			// unicode = Integer.parseInt((new Double(c)).toString());

			chars = Character.toChars(unicode);

			string = string.concat(String.copyValueOf(chars));
		}

		return string;
	}

	private Float randomFloat() {
		Float flotante;

		flotante = new Float(Math.random() * 10000);

		return flotante;
	}
}
