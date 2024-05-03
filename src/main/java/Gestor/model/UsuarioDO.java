package Gestor.model;

public class UsuarioDO {

	private String userName;
	private boolean isAlumno;

	public UsuarioDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioDO(String userName, boolean isAlumno) {
		super();
		this.userName = userName;
		this.isAlumno = isAlumno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAlumno() {
		return isAlumno;
	}

	public void setAlumno(boolean isAlumno) {
		this.isAlumno = isAlumno;
	}

	@Override
	public String toString() {
		return "UsuarioDO [userName=" + userName + ", isAlumno=" + isAlumno + "]";
	}

}
