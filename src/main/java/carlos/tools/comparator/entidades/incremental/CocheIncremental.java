package carlos.tools.comparator.entidades.incremental;

import carlos.tools.comparator.entidades.Coche;


/*
 * //TODO: Añadir la fecha de generacion del registro incremental
 */
public class CocheIncremental extends Coche {

	private Concepto concepto;
	
	public CocheIncremental(Coche coche){
		super(coche);
	}

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("|");
		sb.append(concepto);

		return sb.toString(); 
	}
}
