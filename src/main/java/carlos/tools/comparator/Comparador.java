package carlos.tools.comparator;

import java.util.ArrayList;
import java.util.List;

import carlos.tools.comparator.entidades.Coche;
import carlos.tools.comparator.entidades.incremental.CocheIncremental;

/**
 * Hello world!
 *
 */
public class Comparador 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    protected List<CocheIncremental> generarIncremental(List<Coche> listaInicial,List<Coche> listaNueva){
    	return new ArrayList<CocheIncremental>();
    }
}
