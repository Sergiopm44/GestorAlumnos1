package Gestor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {

	public static int eliminarAlumno(int idAlumno, Connection con) {

		try {
			// Borramos el alumno con un determinado id
			// de alumno
			String query = "DELETE FROM Alumno WHERE idAlumno=?";
			// Creamos statement
			PreparedStatement pstmt = con.prepareStatement(query);
			// Establecemos el primer parametro de la
			// consulta
			pstmt.setInt(1, idAlumno);
			// Devolvemos 0 si todo ha ido bien
			return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// Si entra aqui significa que ha dado
			// error y por lo tanto
			// debajo devolveremos -1
			e.printStackTrace();
			return -1;

		}
	}

	public static int insertAlumno(Connection con, AlumnoDO alumno) {
		try {

			// Comprobar que no existe un alumno con
			// ese id en bd
			String selectID = "Select * From Alumno Where idAlumno=?";
			PreparedStatement pstmt = con.prepareStatement(selectID);
			// Establecemos el primer parametro de la
			// query
			pstmt.setInt(1, alumno.getIdAlumno());
			ResultSet rs = pstmt.executeQuery();

			// Si existe lo metemos sin id
			if (rs.next() || alumno.getIdAlumno() < -1) {
				String query = "INSERT INTO Alumno (dniA, fechNa, nombre, apellido, usuario, contrasenia, telefono, email, Curso_idCurso) VALUES(?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt2 = con.prepareStatement(query);
				// Establecemos los 7 parametros para
				// introducir un alumno a la base de
				// datos
				pstmt2.setString(1, alumno.getDniA());
				pstmt2.setString(2, alumno.getFechNa());
				pstmt2.setString(3, alumno.getNombre());
				pstmt2.setString(4, alumno.getApellido());
				pstmt2.setString(5, alumno.getUsuario());
				pstmt2.setString(6, alumno.getContrasenia());
				pstmt2.setInt(7, alumno.getTelefono());
				pstmt2.setString(8, alumno.getEmail());
				pstmt2.setInt(9, alumno.getCurso_idCurso());
				// Si alguno de los String son nulos devolvemos 0
				if (alumno.getNombre().equals(null))
					return 0;
				if (alumno.getApellido().equals(null))
					return 0;
				if (alumno.getUsuario().equals(null))
					return 0;
				if (alumno.getContrasenia().equals(null))
					return 0;
				if (alumno.getEmail().equals(null))
					pstmt2.setString(6, null);
				if (alumno.getFechNa().equals(null))
					return 0;

				// Ejecutamos la query
				pstmt2.executeUpdate();
				// Si ha ido bien devolvera 1
				return 1;
				// Si no existe el id lo introducimos
			} else {
				String query = "INSERT INTO Alumno (idAlumno, dniA, fechNa, nombre, apellido, usuario, contrasenia, telefono, email, Curso_idCurso) VALUES(?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt3 = con.prepareStatement(query);
				// De nuevo, establecemos, esta vez 8
				// parametros para introducir un
				// alumno a la base de datos
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
				// Si alguno de los String son nulos devolvemos 0
				if (alumno.getNombre().equals(null))
					return 0;
				if (alumno.getApellido().equals(null))
					return 0;
				if (alumno.getUsuario().equals(null))
					return 0;
				if (alumno.getContrasenia().equals(null))
					return 0;
				if (alumno.getEmail().equals(null))
					pstmt3.setString(7, null);
				if (alumno.getFechNa().equals(null))
					return 0;

				// Ejecutamos la query
				pstmt3.executeUpdate(query);
				return 0;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			// Si ha ido mal devolvera 0
			e.printStackTrace();
			return -1;
		}

	}

	public static int actualizarAlumno(AlumnoDO alumno, Connection con) {
		try {

			boolean campoPrevio = false;
			String query = "UPDATE Alumno SET ";
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

	public static AlumnoDO cargar(Connection con, int id) {
		try {
			// Creamos query
			String query = "SELECT * FROM Alumno WHERE idAlumno = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de
			// alumno
			pstmt.setInt(1, id);
			// Creamos un resultset y ejecutamos la
			// consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un alumno y le asignamos los
			// datos de resultset
			AlumnoDO Alumno1 = new AlumnoDO();

			Alumno1.setIdAlumno(rs.getInt(1));
			Alumno1.setDniA(rs.getString(2));
			Alumno1.setFechNa(rs.getString(3));
			Alumno1.setNombre(rs.getString(4));
			Alumno1.setApellido(rs.getString(5));
			Alumno1.setUsuario(rs.getString(6));
			Alumno1.setContrasenia(rs.getString(7));
			Alumno1.setTelefono(rs.getInt(8));
			Alumno1.setEmail(rs.getString(9));
			Alumno1.setCurso_idCurso(rs.getInt(10));
			// Devolvemos alumno
			return Alumno1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Si sale mal devolvemos null
			return null;
		}

	}

}
