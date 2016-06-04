package carlos.tools.comparator;

import java.io.File;

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

	public void testFileSize(){
		
		File file;
		Boolean ficheroDemasiadoGrande;
		
		
		file = Comparador.readIncrementalFile();
		
		if (file.length()>Comparador.fileSizeLimit){
			ficheroDemasiadoGrande = true;
		}
		else{
			ficheroDemasiadoGrande = false;
		}
		
		assertFalse(ficheroDemasiadoGrande);
	}
}
