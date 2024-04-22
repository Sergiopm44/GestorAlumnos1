package Gestor.model;

public class CentroDO {
	private int idCentro;
	private int nif;
	private String direccion;
	private String localidad;
	private String provincia;
	private int cPostal;
	private int Telefono;
	private String email;

	public CentroDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CentroDO(int idCentro, int nif, String direccion, String localidad, String provincia, int cPostal,
			int telefono, String email) {
		super();
		this.idCentro = idCentro;
		this.nif = nif;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.cPostal = cPostal;
		Telefono = telefono;
		this.email = email;
	}

	public int getIdCentro() {
		return idCentro;
	}

	public void setIdCentro() {
		this.idCentro = idCentro;
	}

	/**
	 * @return the nif
	 */
	public int getNif() {
		return nif;
	}

	/**
	 * @param nif the nif to set
	 */
	public void setNif(int nif) {
		this.nif = nif;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the cPostal
	 */
	public int getcPostal() {
		return cPostal;
	}

	/**
	 * @param cPostal the cPostal to set
	 */
	public void setcPostal(int cPostal) {
		this.cPostal = cPostal;
	}

	/**
	 * @return the telefono
	 */
	public int getTelefono() {
		return Telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(int telefono) {
		Telefono = telefono;
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

	@Override
	public String toString() {
		return "CentroDO [nif=" + nif + ", direccion=" + direccion + ", localidad=" + localidad + ", provincia="
				+ provincia + ", cPostal=" + cPostal + ", Telefono=" + Telefono + ", email=" + email + "]";
	}

}
