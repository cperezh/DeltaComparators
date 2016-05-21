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
		
		assertTrue(Coche.find(coche, listaCoches)!=null);
	}

	
	public void testFindKO() {
		List<Coche> listaCoches = CocheTestUtils.generarListaCochesAleatorios(5);
		Coche coche;
		
		coche = CocheTestUtils.generarCocheAleatorio();
		
		//Si no lo encuentro, test OK
		assertTrue(Coche.find(coche, listaCoches)==null);
	}

}
