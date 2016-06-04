package carlos.tools.comparator;

import java.io.File;
import java.util.List;

import carlos.tools.comparator.entidades.Coche;

public class Comparador {

	/**
	 * Limite del fichero incremental 2MB
	 */
	static final int fileSizeLimit = 2097152;
	// 1048576
	// 2097152
	static final String rutaFicheroIncremental = "C:/Users/Carlos/workspace/comparator/out/incremental.pdf";

	public void generarIncremental() {

		List<Coche> listaInicial;
		List<Coche> listaNueva;

		listaInicial = recuperarIncrementalAnterior();
		listaNueva = recuperarListaNueva();
		ComparadorNegocio.generarIncrementalNegocio(listaInicial, listaNueva);
		generarFicheroIncremental(listaNueva);

	}

	List<Coche> recuperarIncrementalAnterior() {
		return null;
	}

	List<Coche> recuperarListaNueva() {
		return null;
	}

	void generarFicheroIncremental(List<Coche> listaNueva) {

	}

	static File readIncrementalFile() {

		File file = new File(rutaFicheroIncremental);

		return file;
	}

}
