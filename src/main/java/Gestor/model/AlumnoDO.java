package Gestor.model;

public class AlumnoDO {
	private int idAlumno;
	private String dniA;
	private String fechNa;
	private String nombre;
	private String apellido;
	private String usuario;
	private String contrasenia;
	private int telefono;
	private String email;
	private int Curso_idCurso;

	/**
	 * Constructor vacio de alumno
	 */
	public AlumnoDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con todos los campos de alumno
	 * 
	 * @param idAlumno
	 * @param dniA
	 * @param fechNa
	 * @param nombre
	 * @param apellido
	 * @param usuario
	 * @param contrasenia
	 * @param telefono
	 * @param email
	 * @param curso_idCurso
	 */
	public AlumnoDO(int idAlumno, String dniA, String fechNa, String nombre, String apellido, String usuario,
			String contrasenia, int telefono, String email, int curso_idCurso) {
		super();
		this.idAlumno = idAlumno;
		this.dniA = dniA;
		this.fechNa = fechNa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.email = email;
		Curso_idCurso = curso_idCurso;
	}

	/**
	 * Getter idAlumno
	 * 
	 * @return El idAlumno
	 */
	public int getIdAlumno() {
		return idAlumno;
	}

	/**
	 * Setter idAlumno
	 * 
	 * @param idAlumno
	 */
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	/**
	 * Getter usuario
	 * 
	 * @return El usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Setter usuario
	 * 
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Getter contrasenia
	 * 
	 * @return contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Setter contrasenia
	 * 
	 * @param contrasenia
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the dniA
	 */
	public String getDniA() {
		return dniA;
	}

	/**
	 * @param dniA the dniA to set
	 */
	public void setDniA(String dniA) {
		this.dniA = dniA;
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
	 * @return the curso_idCurso
	 */
	public int getCurso_idCurso() {
		return Curso_idCurso;
	}

	/**
	 * @param curso_idCurso the curso_idCurso to set
	 */
	public void setCurso_idCurso(int curso_idCurso) {
		Curso_idCurso = curso_idCurso;
	}

	/**
	 * To string de todos los campos de alumno
	 */
	@Override
	public String toString() {
		return "AlumnoDO [idAlumno=" + idAlumno + ", dniA=" + dniA + ", fechNa=" + fechNa + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", telefono="
				+ telefono + ", email=" + email + ", Curso_idCurso=" + Curso_idCurso + "]";
	}

}
