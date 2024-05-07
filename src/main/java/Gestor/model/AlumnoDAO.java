package Gestor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
	/**
	 * Funcion que elimina un Alumno de la base de datos segun el id del alumno
	 * introducido
	 * 
	 * @param idAlumno
	 * @param con
	 * @return 0 si sale bien o -1 si sale man
	 */
	public static int eliminarAlumno(int idAlumno, Connection con) {
		try {
			// Borramos el alumno con un determinado id de
			// alumno
			String query = "DELETE FROM alumno WHERE idAlumno=?";
			// Creamos statement
			PreparedStatement pstmt = con.prepareStatement(query);
			// Establecemos el primer parametro de la consulta
			pstmt.setInt(1, idAlumno);
			// Ejecutamos la consulta
			int filasAfectadas = pstmt.executeUpdate();
			// Devolvemos el número de filas afectadas
			return filasAfectadas;
		} catch (SQLException e) {
			// Si ocurre un error, imprimir la traza y
			// devolver -1
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Funcion que inserta un nuevo Alumno en la base de datos segun el id de Alumno
	 * introducido. El id de alumno ya existe se introduce el Alumno sin id, por el
	 * contrario si el id de alumno no existe se introduce el Alumno con id.
	 * 
	 * @param con
	 * @param alumno
	 * @return 1 si sale bien o -1 si sale man
	 */
	public static int insertAlumno(Connection con, AlumnoDO alumno) {
		try {
			String selectID = "SELECT * FROM alumno WHERE idAlumno=?";
			PreparedStatement pstmt = con.prepareStatement(selectID);

			pstmt.setInt(1, alumno.getIdAlumno());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next() || alumno.getIdAlumno() < -1) {
				String query = "INSERT INTO alumno (dniA, fechNa, nombre, apellido, usuario, contrasenia, telefono, email, Curso_idCurso) VALUES(?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt2 = con.prepareStatement(query);

				pstmt2.setString(1, alumno.getDniA());
				pstmt2.setString(2, alumno.getFechNa());
				pstmt2.setString(3, alumno.getNombre());
				pstmt2.setString(4, alumno.getApellido());
				pstmt2.setString(5, alumno.getUsuario());
				pstmt2.setString(6, alumno.getContrasenia());
				pstmt2.setInt(7, alumno.getTelefono());
				pstmt2.setString(8, alumno.getEmail());
				pstmt2.setInt(9, alumno.getCurso_idCurso());

				// Ejecutamos la query
				pstmt2.executeUpdate();

				// Si ha ido bien devolverá 1
				return 1;
			} else {
				String query = "INSERT INTO alumno (idAlumno, dniA, fechNa, nombre, apellido, usuario, contrasenia, telefono, email, Curso_idCurso) VALUES(?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt3 = con.prepareStatement(query);

				pstmt3.setInt(1, alumno.getIdAlumno());
				pstmt3.setString(2, alumno.getDniA());
				pstmt3.setString(3, alumno.getFechNa());
				pstmt3.setString(4, alumno.getNombre());
				pstmt3.setString(5, alumno.getApellido());
				pstmt3.setString(6, alumno.getUsuario());
				pstmt3.setString(7, alumno.getContrasenia());
				pstmt3.setInt(8, alumno.getTelefono());
				pstmt3.setString(9, alumno.getEmail());
				pstmt3.setInt(10, alumno.getCurso_idCurso());

				// Ejecutamos la query
				pstmt3.executeUpdate();

				return 0;
			}
		} catch (SQLException e) {
			// Si ha ido mal devolverá -1
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Actualiza los datos de un Alumno en la base de datos. Se puede modificar
	 * cualquier campo que se desee de al tabla alumno segun el id del alumno
	 * 
	 * @param alumno
	 * @param con
	 * @return 1 si ha salido bien o 0 si ha salido mal
	 */
	public static int actualizarAlumno(AlumnoDO alumno, Connection con) {
		try {

			boolean campoPrevio = false;
			String query = "UPDATE alumno SET ";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Si los campos no son nulos, los vamos
			// añadiendo a la sentencia
			if (alumno.getDniA() != null) {
				query = query + "dniA = ?";
				campoPrevio = true;
			}
			if (alumno.getFechNa() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "fechNa = ?";
				campoPrevio = true;
			}

			if (alumno.getNombre() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "nombre = ?";
				campoPrevio = true;
			}

			if (alumno.getApellido() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "apellido = ?";
				campoPrevio = true;
			}
			if (alumno.getUsuario() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "usuario = ?";
				campoPrevio = true;
			}
			if (alumno.getContrasenia() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "contrasenia = ?";
				campoPrevio = true;
			}

			if (alumno.getTelefono() != -1) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "telefono = ?";
				campoPrevio = true;
			}

			if (alumno.getEmail() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "email = ?";
				campoPrevio = true;
			}

			if (alumno.getCurso_idCurso() != -1) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "Curso_idCurso = ?";
			}

			query = query + " WHERE idAlumno = ?";

			int dpsSigno = 1;
			// Si los campos no son nulos vamos
			// añadiendo como parametros los atributos
			// de alumno
			if (alumno.getDniA() != null) {
				pstmt.setString(dpsSigno, alumno.getDniA());
				dpsSigno++;
			}
			if (alumno.getFechNa() != null) {
				pstmt.setString(dpsSigno, alumno.getFechNa());
				dpsSigno++;
			}
			if (alumno.getNombre() != null) {
				pstmt.setString(dpsSigno, alumno.getNombre());
				dpsSigno++;
			}
			if (alumno.getApellido() != null) {
				pstmt.setString(dpsSigno, alumno.getApellido());
				dpsSigno++;
			}
			if (alumno.getUsuario() != null) {
				pstmt.setString(dpsSigno, alumno.getUsuario());
				dpsSigno++;
			}
			if (alumno.getContrasenia() != null) {
				pstmt.setString(dpsSigno, alumno.getContrasenia());
				dpsSigno++;
			}
			if (alumno.getTelefono() != -1) {
				pstmt.setInt(dpsSigno, alumno.getTelefono());
				dpsSigno++;
			}
			if (alumno.getEmail() != null) {
				pstmt.setString(dpsSigno, alumno.getEmail());
				dpsSigno++;
			}
			if (alumno.getEmail() == null) {
				pstmt.setString(dpsSigno, null);
				dpsSigno++;
			}
			if (alumno.getCurso_idCurso() != -1) {
				pstmt.setInt(dpsSigno, alumno.getCurso_idCurso());
				dpsSigno++;
			}

			if (campoPrevio) {

				System.out.println(query);
				pstmt.executeUpdate(query);

			}
			// Si todo ha ido bien devolvera 1
			return 1;
		} catch (SQLException e) {
			// TODO: handle exception
			// Si no ha ido bien devolvera 0
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Es una función que está hecha para el inicio de sesión, para comprobar si
	 * está en la base de datos
	 * 
	 * @param con
	 * @param alumno
	 * @return la contraseña del alumno que se mete como parametro que sea igual que
	 *         la contraseña del alumno o false si sale mal
	 */
	public static boolean cargar(Connection con, AlumnoDO alumno) {
		try {
			// Creamos query
			String query = "SELECT * FROM alumno WHERE usuario = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parámetro el usuario del
			// alumno
			pstmt.setString(1, alumno.getUsuario());
			// Ejecutamos la consulta
			ResultSet rs = pstmt.executeQuery();

			// Verificamos si hay al menos una fila en el
			// ResultSet
			if (rs.next()) {
				// Creamos un alumno y le asignamos los datos del
				// ResultSet
				AlumnoDO Alumno1 = new AlumnoDO();

				Alumno1.setIdAlumno(rs.getInt("idAlumno"));
				Alumno1.setDniA(rs.getString("dniA"));
				Alumno1.setFechNa(rs.getString("fechNa"));
				Alumno1.setNombre(rs.getString("nombre"));
				Alumno1.setApellido(rs.getString("apellido"));
				Alumno1.setUsuario(rs.getString("usuario"));
				Alumno1.setContrasenia(rs.getString("contrasenia"));
				Alumno1.setTelefono(rs.getInt("telefono"));
				Alumno1.setEmail(rs.getString("email"));
				Alumno1.setCurso_idCurso(rs.getInt("Curso_idCurso"));

				// Devolvemos true si las contraseñas son iguales
				return alumno.getContrasenia().equals(Alumno1.getContrasenia());
			} else {
				// No se encontró ningún alumno con el usuario
				// proporcionado
				return false;
			}
		} catch (SQLException e) {
			// Manejo de excepciones
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Funcion que recoge todos los datos de un alumno y los almacena en un array segun el dni
	 * 
	 * @param con
	 * @param dniA
	 * @return alumno1 si ha funcionado bien y nulo si no ha funcionado
	 */
	public static AlumnoDO busqueda(Connection con, String dniA) {
		try {
			// Creamos query
			String query = "SELECT * FROM alumno WHERE dniA = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de alumno
			pstmt.setString(1, dniA);
			// Creamos un resultset y ejecutamos la consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un alumno
			AlumnoDO alumno = null;
			// Verificamos si hay una fila válida en el
			// ResultSet
			if (rs.next()) {
				// Si hay una fila válida, creamos un objeto
				// AlumnoDO
				alumno = new AlumnoDO();
				// Y le asignamos los datos del ResultSet
				alumno.setIdAlumno(rs.getInt("idAlumno"));
				alumno.setDniA(rs.getString("dniA"));
				alumno.setFechNa(rs.getString("fechNa"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setApellido(rs.getString("apellido"));
				alumno.setUsuario(rs.getString("usuario"));
				alumno.setContrasenia(rs.getString("contrasenia"));
				alumno.setTelefono(rs.getInt("telefono"));
				alumno.setEmail(rs.getString("email"));
				alumno.setCurso_idCurso(rs.getInt("curso_idCurso"));
			}
			// Devolvemos el objeto AlumnoDO (puede ser null
			// si no se encontró ningún
			// alumno)
			return alumno;
		} catch (SQLException e) {
			// Manejo de excepciones
			e.printStackTrace();
			// Si ocurre un error, devolvemos null
			return null;
		}
	}

	/**
	 * Funcion que recoge todos los datos de un alumno y los almacena en un array segun el usuario
	 * 
	 * @param con
	 * @param usuario
	 * @return alumno1 si ha funcionado bien y nulo si no ha funcionado
	 */
	public static AlumnoDO searchAUser(Connection con, String usuario) {
		try {
			// Creamos query
			String query = "SELECT * FROM alumno WHERE usuario = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de alumno
			pstmt.setString(1, usuario);
			// Creamos un resultset y ejecutamos la consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un alumno
			AlumnoDO alumno = null;
			// Verificamos si hay una fila vÃ¡lida en el
			// ResultSet
			if (rs.next()) {
				// Si hay una fila vÃ¡lida, creamos un objeto
				// AlumnoDO
				alumno = new AlumnoDO();
				// Y le asignamos los datos del ResultSet
				alumno.setIdAlumno(rs.getInt("idAlumno"));
				alumno.setDniA(rs.getString("dniA"));
				alumno.setFechNa(rs.getString("fechNa"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setApellido(rs.getString("apellido"));
				alumno.setUsuario(rs.getString("usuario"));
				alumno.setContrasenia(rs.getString("contrasenia"));
				alumno.setTelefono(rs.getInt("telefono"));
				alumno.setEmail(rs.getString("email"));
				alumno.setCurso_idCurso(rs.getInt("curso_idCurso"));
			}
			// Devolvemos el objeto AlumnoDO (puede ser null
			// si no se encontrÃ³ ningÃºn
			// alumno)
			return alumno;
		} catch (SQLException e) {
			// Manejo de excepciones
			e.printStackTrace();
			// Si ocurre un error, devolvemos null
			return null;
		}
	}

}
