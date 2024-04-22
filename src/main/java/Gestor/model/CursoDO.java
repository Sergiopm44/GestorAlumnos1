package Gestor.model;

public class CursoDO {
	private int idCurso;
	private String nombre;
	private String descripcion;

	public CursoDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CursoDO(int idCurso, String nombre, String descripcion) {
		super();
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * @return the idCurso
	 */
	public int getIdCurso() {
		return idCurso;
	}

	/**
	 * @param idCurso the idCurso to set
	 */
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "CursoDO [idCurso=" + idCurso + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
