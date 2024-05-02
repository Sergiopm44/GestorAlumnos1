package Gestor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {

	/**
	 * Elimina un profesor con un determinado id de profesor
	 * 
	 * @param idProfesores
	 * @param con
	 * @return 0 si ha ido bien o -1 si ha ido mal
	 */
	public static int eliminarProfesor(int idProfesores, Connection con) {

		try {

			// Borramos el dniP (dni de profesores )
			String query = "DELETE FROM profesores WHERE idProfesor=?";
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

	/**
	 * Inserta un profesor con un determinado id, si este existe se le introduciran
	 * los campos sin id, por el contrario si no existe se le meteran los campos y
	 * el id de profesor
	 * 
	 * @param con
	 * @param Profesor
	 * @return 1 si ha ido bien o 0 si ha ido mal
	 */
	public static int insertProfesor(Connection con, ProfesorDO Profesor) {
		try {
			String selectID = "SELECT * FROM profesores WHERE idProfesor=?";
			PreparedStatement pstmt = con.prepareStatement(selectID);

			pstmt.setInt(1, Profesor.getIdProfesor());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next() || Profesor.getIdProfesor() < -1) {
				String query = "INSERT INTO profesores (dniP, fechNa, nombre, apellido, usuario, contrasenia, telefono, email, Departamentos_idDepartamentos) VALUES(?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt2 = con.prepareStatement(query);

				pstmt2.setString(1, Profesor.getDniP());
				pstmt2.setString(2, Profesor.getFechNa());
				pstmt2.setString(3, Profesor.getNombre());
				pstmt2.setString(4, Profesor.getApellido());
				pstmt2.setString(5, Profesor.getUsuario());
				pstmt2.setString(6, Profesor.getContrasenia());
				pstmt2.setInt(7, Profesor.getTelefono());
				pstmt2.setString(8, Profesor.getEmail());
				pstmt2.setInt(9, Profesor.getDepartamentos_idDepartamentos());

				// Ejecutamos la query
				pstmt2.executeUpdate();

				// Si ha ido bien devolverá 1
				return 1;
			} else {
				String query = "INSERT INTO profesores (idProfesor, dniP, fechNa, nombre, apellido, usuario, contrasenia, telefono, email, Departamentos_idDepartamentos) VALUES(?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt3 = con.prepareStatement(query);

				pstmt3.setInt(1, Profesor.getIdProfesor());
				pstmt3.setString(2, Profesor.getDniP());
				pstmt3.setString(3, Profesor.getFechNa());
				pstmt3.setString(4, Profesor.getNombre());
				pstmt3.setString(5, Profesor.getApellido());
				pstmt3.setString(6, Profesor.getUsuario());
				pstmt3.setString(7, Profesor.getContrasenia());
				pstmt3.setInt(8, Profesor.getTelefono());
				pstmt3.setString(9, Profesor.getEmail());
				pstmt3.setInt(10, Profesor.getDepartamentos_idDepartamentos());

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
	 * Actualiza uno o varios campos segun el id de profesor
	 * 
	 * @param Profesor
	 * @param con
	 * @return 1 si ha ido bien o 0 si ha ido mal
	 */
	public static int actualizarProfesor(ProfesorDO Profesor, Connection con) {
		try {

			boolean campoPrevio = false;
			String query = "UPDATE profesores SET ";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Si los campos no son nulos, los vamos
			// añadiendo a la sentencia
			if (Profesor.getDniP() != null) {
				query = query + "dniP = ?";
				campoPrevio = true;
			}
			if (Profesor.getFechNa() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
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
			if (Profesor.getUsuario() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "usuario = ?";
				campoPrevio = true;
			}
			if (Profesor.getContrasenia() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "contrasenia = ?";
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
				query = query + "Departamentos_idDepartamentos = ?";
			}

			query = query + " WHERE idProfesor = ?";

			int dpsSigno = 1;
			// Si los campos no son nulos vamos
			// añadiendo como parametros los atributos
			// de profesor
			if (Profesor.getDniP() != null) {
				pstmt.setString(dpsSigno, Profesor.getDniP());
				dpsSigno++;
			}
			if (Profesor.getFechNa() != null) {
				pstmt.setString(dpsSigno, Profesor.getFechNa());
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
			if (Profesor.getUsuario() != null) {
				pstmt.setString(dpsSigno, Profesor.getUsuario());
				dpsSigno++;
			}
			if (Profesor.getContrasenia() != null) {
				pstmt.setString(dpsSigno, Profesor.getContrasenia());
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
			if (Profesor.getEmail() == null) {
				pstmt.setString(dpsSigno, null);
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

	/**
	 * Es una función que está hecha para el inicio de sesión, para comprobar si está en la base de datos
	 * @param con
	 * @param profesor
	 * @return la contraseña del profesor que se mete como parametro que sea igual
	 *         que la contraseña del profesor o false si sale mal
	 */
	public static boolean cargar(Connection con, ProfesorDO profesor) {
		try {
			// Creamos query
			String query = "SELECT * FROM profesores WHERE usuario = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parámetro el usuario del
			// alumno
			pstmt.setString(1, profesor.getUsuario());
			// Ejecutamos la consulta
			ResultSet rs = pstmt.executeQuery();

			// Verificamos si hay al menos una fila en el
			// ResultSet
			if (rs.next()) {
				// Creamos un alumno y le asignamos los datos del
				// ResultSet
				ProfesorDO profesor1 = new ProfesorDO();

				profesor1.setIdProfesor(rs.getInt("idProfesor"));
				profesor1.setDniP(rs.getString("dniP"));
				profesor1.setFechNa(rs.getString("fechNa"));
				profesor1.setNombre(rs.getString("nombre"));
				profesor1.setApellido(rs.getString("apellido"));
				profesor1.setUsuario(rs.getString("usuario"));
				profesor1.setContrasenia(rs.getString("contrasenia"));
				profesor1.setTelefono(rs.getInt("telefono"));
				profesor1.setEmail(rs.getString("email"));
				profesor1.setDepartamentos_idDepartamentos(rs.getInt("Departamentos_idDepartamentos"));

				// Devolvemos true si las contraseñas son iguales
				return profesor.getContrasenia().equals(profesor1.getContrasenia());
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
	* Funcion que recoge todos los datos de un profesor y los almacena en un array
	*@param con
	*@param profesor
	*@return profesor1 si ha funcionado bien y nulo si no ha funcionado
	*/
	public static ProfesorDO busqueda(Connection con, String dniP) {
		try {
			// Creamos query
			String query = "SELECT * FROM profesores WHERE dniP = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de
			// alumno
			pstmt.setString(1, dniP);
			// Creamos un resultset y ejecutamos la
			// consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un alumno y le asignamos los
			// datos de resultset
			ProfesorDO profesor1 = new ProfesorDO();

			profesor1.setIdProfesor(rs.getInt(1));
			profesor1.setDniP(rs.getString(2));
			profesor1.setFechNa(rs.getString(3));
			profesor1.setNombre(rs.getString(4));
			profesor1.setApellido(rs.getString(5));
			profesor1.setUsuario(rs.getString(6));
			profesor1.setContrasenia(rs.getString(7));
			profesor1.setTelefono(rs.getInt(8));
			profesor1.setEmail(rs.getString(9));
			profesor1.setDepartamentos_idDepartamentos(rs.getInt(10));
			// Devolvemos alumno
			return profesor1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Si sale mal devolvemos null
			return null;
		}

	}

}
