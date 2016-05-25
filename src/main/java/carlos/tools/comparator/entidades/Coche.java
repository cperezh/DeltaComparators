package carlos.tools.comparator.entidades;

import java.util.List;

public class Coche {

	private String matricula;
	private String color;
	private Float precio;

	public Coche() {

	}
	
	public Coche(String matricula, String color, Float precio) {
		this.matricula = matricula;
		this.color = color;
		this.precio = precio;
	}
	
	public Coche(Coche coche){
		this.matricula = coche.getMatricula();
		this.color = coche.getColor();
		this.precio = coche.getPrecio();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

	public Coche clone() {

		Coche coche = new Coche();

		coche.color = new String(this.color);

		coche.matricula = new String(this.matricula);

		coche.precio = new Float(this.precio);

		return coche;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(matricula);
		sb.append("|");
		sb.append(color);
		sb.append("|");
		sb.append(precio);

		return sb.toString();
	}

	/**
	 * Busca un coche en una lista de coches, por su matrícula. Si lo encuentra, devuelve 
	 * el coche de la lista
	 * @param coche
	 * @param listaCoches
	 * @return
	 */
	public static Coche find(Coche coche,List<? extends Coche> listaCoches){
		
		for(Coche cocheEnLista:listaCoches){
			if (coche.getMatricula().equals(cocheEnLista.getMatricula())){
				return cocheEnLista;
			}
		}
		
		return null;
	}

}
