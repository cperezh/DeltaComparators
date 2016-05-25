package carlos.tools.comparator;

import java.util.List;

import carlos.tools.comparator.entidades.Coche;
import junit.framework.TestCase;

public class CocheTest extends TestCase {

	public void testFindOK() {
		List<Coche> listaCoches = CocheTestUtils.generarListaCochesAleatorios(5);
		Coche coche;

		coche = listaCoches.get(3);

		coche = CocheTestUtils.modificarCocheAleatorio(coche);

		assertTrue(Coche.find(coche, listaCoches) != null);
	}

	public void testFindKO() {
		List<Coche> listaCoches = CocheTestUtils.generarListaCochesAleatorios(5);
		Coche coche;

		coche = CocheTestUtils.generarCocheAleatorio();

		// Si no lo encuentro, test OK
		assertTrue(Coche.find(coche, listaCoches) == null);
	}

	public void testEquals() {
		Boolean works = true;

		Coche coche = CocheTestUtils.generarCocheAleatorio();
		Coche coche2 = coche.clone();

		// Si no es igual a si mismo
		if (!coche.equals(coche)) {
			works = false;
		}
		// Si no es igual a su clon
		else if (!coche.equals(coche2)) {
			works = false;
		}
		// Creo un coche y voy comparando atributos de menos a mas
		else {

			Coche coche3 = new Coche();

			if (coche.equals(coche3)) {
				works = false;
			} else {
				coche3.setMatricula(coche.getMatricula());

				if (coche.equals(coche3)) {
					works = false;
				} else {
					coche3.setColor(coche.getColor());
					if (coche.equals(coche3)) {
						works = false;
					} else {
						coche3.setPrecio(coche.getPrecio());
						if (!coche.equals(coche3)) {
							works = false;
						}
					}
				}
			}

		}
		
		assertTrue(works);
	}

}
