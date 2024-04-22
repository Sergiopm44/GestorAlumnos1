package Gestor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {

	// Funcion que elimina los profesores
	public static int eliminarProfesor(int idProfesores, Connection con) {

		try {

			// Borramos el dniP (dni de profesores )
			String query = "DELETE FROM Profesores WHERE dniP=?";
			// Creamos statement
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, idProfesores);

			return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return -1;

		}

	}

	public static int insertProfesor(Connection con, ProfesorDO Profesor) {
		try {

			String selectID = "Select * From Profesor Where=?";
			PreparedStatement pstmt = con.prepareStatement(selectID);

			pstmt.setInt(1, Profesor.getDniP());
			ResultSet rs = pstmt.executeQuery();

			// Si existe lo metemos sin id
			if (rs.next() || Profesor.getDniP() < -1) {
				String query = "INSERT INTO Profesor (idProfesor, dniP, fechNa, nombre, apellido, telefono, email, Curso_idCurso) VALUES(?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt2 = con.prepareStatement(query);

				pstmt2.setInt(1, Profesor.getIdProfesor());
				pstmt2.setInt(2, Profesor.getDniP());
				pstmt2.setString(3, Profesor.getFechNa());
				pstmt2.setString(4, Profesor.getNombre());
				pstmt2.setString(5, Profesor.getApellido());
				pstmt2.setInt(6, Profesor.getTelefono());
				pstmt2.setString(7, Profesor.getEmail());
				pstmt2.setInt(8, Profesor.getDepartamentos_idDepartamentos());

				// Si el nombre o el nick son nulos
				// devolvemos 0

				if (Profesor.getNombre().equals(null))
					return 0;
				if (Profesor.getApellido().equals(null))
					return 0;
				if (Profesor.getEmail().equals(null))
					return 0;
				if (Profesor.getFechNa().equals(null))
					return 0;

				// Ejecutamos la query

				pstmt2.executeUpdate();

				// Si ha ido bien devolvera 1

				return 1;
				// Si no existe el id lo introducimos
			} else {
				String query = "INSERT INTO Profesor (idProfesor, dniP, fechNa, nombre, apellido, telefono, email, Curso_idCurso) VALUES(?,?,?,?,?,?,?)";

				PreparedStatement pstmt3 = con.prepareStatement(query);

				pstmt3.setInt(1, Profesor.getIdProfesor());
				pstmt3.setInt(2, Profesor.getDniP());
				pstmt3.setString(3, Profesor.getFechNa());
				pstmt3.setString(4, Profesor.getNombre());
				pstmt3.setString(5, Profesor.getApellido());
				pstmt3.setInt(6, Profesor.getTelefono());
				pstmt3.setString(7, Profesor.getEmail());
				pstmt3.setInt(8, Profesor.getDepartamentos_idDepartamentos());

				// Si el nombre o el nick son nulos
				// devolvemos 0
				if (Profesor.getNombre().equals(null))
					return 0;
				if (Profesor.getApellido().equals(null))
					return 0;
				if (Profesor.getEmail().equals(null))
					return 0;
				if (Profesor.getFechNa().equals(null))
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

	public static int actualizarProfesor(ProfesorDO Profesor, Connection con) {
		try {

			boolean campoPrevio = false;
			String query = "UPDATE Profesor SET ";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Si los campos no son nulos, los vamos
			// añadiendo a la sentencia
			if (Profesor.getFechNa() != null) {
				query = query + "fechNa = ?";
				campoPrevio = true;
			}

			if (Profesor.getNombre() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "nombre = ?";
				campoPrevio = true;
			}

			if (Profesor.getApellido() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "apellido = ?";
				campoPrevio = true;
			}

			if (Profesor.getTelefono() != -1) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "telefono = ?";
				campoPrevio = true;
			}

			if (Profesor.getEmail() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "email = ?";
				campoPrevio = true;
			}

			if (Profesor.getDepartamentos_idDepartamentos() != -1) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "departamento_idDepartamento = ?";
			}

			query = query + " WHERE idProfesor = ?";

			int dpsSigno = 1;
			// Si los campos no son nulos vamos
			// añadiendo como parametros los atributos
			// de profesor
			if (Profesor.getFechNa() != null) {
				pstmt.setString(dpsSigno, Profesor.getNombre());
				dpsSigno++;
			}
			if (Profesor.getNombre() != null) {
				pstmt.setString(dpsSigno, Profesor.getNombre());
				dpsSigno++;
			}
			if (Profesor.getApellido() != null) {
				pstmt.setString(dpsSigno, Profesor.getApellido());
				dpsSigno++;
			}
			if (Profesor.getTelefono() != -1) {
				pstmt.setInt(dpsSigno, Profesor.getTelefono());
				dpsSigno++;
			}
			if (Profesor.getEmail() != null) {
				pstmt.setString(dpsSigno, Profesor.getEmail());
				dpsSigno++;
			}
			if (Profesor.getDepartamentos_idDepartamentos() != -1) {
				pstmt.setInt(dpsSigno, Profesor.getDepartamentos_idDepartamentos());
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

	public static ProfesorDO cargar(Connection con, int id) {
		try {
			// Creamos query
			String query = "SELECT * FROM Profesor WHERE idProfesor = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de
			// avestruz
			pstmt.setInt(1, id);
			// Creamos un resultset y ejecutamos la
			// consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un avestruz y le asignamos los
			// datos de resultset
			ProfesorDO Profesor1 = new ProfesorDO();
			Profesor1.setIdProfesor(rs.getInt(1));
			Profesor1.setFechNa(rs.getString(2));
			Profesor1.setNombre(rs.getString(3));
			Profesor1.setApellido(rs.getString(4));
			Profesor1.setTelefono(rs.getInt(5));
			Profesor1.setEmail(rs.getString(6));
			Profesor1.setDepartamentos_idDepartamentos(rs.getInt(7));
			// Devolvemos avestruz
			return Profesor1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Si sale mal devolvemos null
			return null;
		}

	}

}
