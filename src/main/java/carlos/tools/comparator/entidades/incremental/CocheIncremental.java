package carlos.tools.comparator.entidades.incremental;

import carlos.tools.comparator.entidades.Coche;

public class CocheIncremental extends Coche {

	private Concepto concepto;

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}
}
