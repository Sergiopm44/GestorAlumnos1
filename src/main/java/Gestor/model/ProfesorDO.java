package Gestor.model;

public class ProfesorDO {

	private int idProfesor;
	private String dniP;
	private String fechNa;
	private String nombre;
	private String apellido;
	private String usuario;
	private String contrasenia;
	private int telefono;
	private String email;
	private int Departamentos_idDepartamentos;

	/**
	 * Constructor vacio de profesor
	 */
	public ProfesorDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con todos los campos de profesor
	 * 
	 * @param idProfesor
	 * @param dniP
	 * @param fechNa
	 * @param nombre
	 * @param apellido
	 * @param usuario
	 * @param contrasenia
	 * @param telefono
	 * @param email
	 * @param departamentos_idDepartamentos
	 */
	public ProfesorDO(int idProfesor, String dniP, String fechNa, String nombre, String apellido, String usuario,
			String contrasenia, int telefono, String email, int departamentos_idDepartamentos) {
		super();
		this.idProfesor = idProfesor;
		this.dniP = dniP;
		this.fechNa = fechNa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.email = email;
		Departamentos_idDepartamentos = departamentos_idDepartamentos;
	}

	/**
	 * @return the idProfesor
	 */
	public int getIdProfesor() {
		return idProfesor;
	}

	/**
	 * @param idProfesor the idProfesor to set
	 */
	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	/**
	 * @return the dniP
	 */
	public String getDniP() {
		return dniP;
	}

	/**
	 * @param dniP the dniP to set
	 */
	public void setDniP(String dniP) {
		this.dniP = dniP;
	}

	/**
	 * @return the fechNa
	 */
	public String getFechNa() {
		return fechNa;
	}

	/**
	 * @param fechNa the fechNa to set
	 */
	public void setFechNa(String fechNa) {
		this.fechNa = fechNa;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the telefono
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the departamentos_idDepartamentos
	 */
	public int getDepartamentos_idDepartamentos() {
		return Departamentos_idDepartamentos;
	}

	/**
	 * @param departamentos_idDepartamentos the departamentos_idDepartamentos to set
	 */
	public void setDepartamentos_idDepartamentos(int departamentos_idDepartamentos) {
		Departamentos_idDepartamentos = departamentos_idDepartamentos;
	}

	/**
	 * To string de todo los campos de profesor
	 */
	@Override
	public String toString() {
		return "ProfesorDO [idProfesor=" + idProfesor + ", dniP=" + dniP + ", fechNa=" + fechNa + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", telefono="
				+ telefono + ", email=" + email + ", Departamentos_idDepartamentos=" + Departamentos_idDepartamentos
				+ "]";
	}

}
