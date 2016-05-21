package carlos.tools.comparator;

import java.util.ArrayList;
import java.util.List;

import carlos.tools.comparator.entidades.Coche;

public class CocheTestUtils {

	public static List<Coche> generarListaCochesAleatorios(int numeroElementos) {

		List<Coche> listaCochesAleatorios = new ArrayList<Coche>();

		Coche coche;

		for (int i = 0; i++ < numeroElementos;) {

			coche = generarCocheAleatorio();

			listaCochesAleatorios.add(coche);
		}

		return listaCochesAleatorios;
	}
	
	public static List<Coche> clonarListaCoches(List<Coche> lista) {

		List<Coche> listaClon = new ArrayList<Coche>();

		for (Coche elemento : lista) {
			listaClon.add(elemento.clone());
		}

		return listaClon;
	}
	
	public static Coche generarCocheAleatorio() {

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
	 * Modifica todos los campos de un Coche, salvo la matricula
	 * @param coche
	 * @return
	 */
	public static Coche modificarCocheAleatorio(Coche coche){
		
		coche.setColor(CocheTestUtils.randomString(6));
		coche.setPrecio(CocheTestUtils.randomFloat());
		
		return coche;
	}

	/**
	 * Genera String con cartacteres UTF16 entre el 65 y el 122 (A-z)
	 * 
	 * @param numCaracteres
	 * @return
	 */
	public static String randomString(int numCaracteres) {
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
			
			chars = Character.toChars(unicode);

			string = string.concat(String.copyValueOf(chars));
		}

		return string;
	}

	public static Float randomFloat() {
		Float flotante;

		flotante = new Float(Math.random() * 10000);

		return flotante;
	}
	
}
