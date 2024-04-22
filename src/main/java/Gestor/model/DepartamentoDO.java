package Gestor.model;

public class DepartamentoDO {
	private int idDepartamentos;
	private String nombre;
	private String descripcion;
	private int numDepartamento;

	public DepartamentoDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartamentoDO(int idDepartamentos, String nombre, String descripcion, int numDepartamento) {
		super();
		this.idDepartamentos = idDepartamentos;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numDepartamento = numDepartamento;
	}

	/**
	 * @return the idDepartamentos
	 */
	public int getIdDepartamentos() {
		return idDepartamentos;
	}

	/**
	 * @param idDepartamentos the idDepartamentos to set
	 */
	public void setIdDepartamentos(int idDepartamentos) {
		this.idDepartamentos = idDepartamentos;
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

	/**
	 * @return the numDepartamento
	 */
	public int getNumDepartamento() {
		return numDepartamento;
	}

	/**
	 * @param numDepartamento the numDepartamento to set
	 */
	public void setNumDepartamento(int numDepartamento) {
		this.numDepartamento = numDepartamento;
	}

	@Override
	public String toString() {
		return "DepartamentoDO [idDepartamentos=" + idDepartamentos + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", numDepartamento=" + numDepartamento + "]";
	}

}
