package Gestor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoDAO {
	/**
	 * Elimina un departamento con un id determinado
	 * 
	 * @param idDepartamentos
	 * @param con
	 * @return 0 si ha ido bien o -1 si ha ido mal
	 */
	public static int eliminarDepartamento(int idDepartamentos, Connection con) {

		try {
			// Borramos el departamento con un determinado id
			// de departamento
			String query = "DELETE * FROM Departamentos WHERE idDepartamentos=?";
			// Creamos statement
			PreparedStatement pstmt = con.prepareStatement(query);
			// Establecemos el primer parametro de la
			// consulta
			pstmt.setInt(1, idDepartamentos);
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

	/**
	 * Inserta un departamento con un determinado id, si el id existe se le
	 * introducen los campos sin id, y si el id no existe se le introducen todos los
	 * campos pero con id
	 * 
	 * @param con
	 * @param departamento
	 * @return 0 si ha ido bien o -1 si ha ido mal
	 */
	public static int insertDepartamento(Connection con, DepartamentoDO departamento) {
		try {

			// Comprobar que no existe un departamento con
			// ese id en bd
			String selectID = "Select * From Departamentos Where idDepartamentos=?";
			PreparedStatement pstmt = con.prepareStatement(selectID);
			// Establecemos el primer parametro de la
			// query
			pstmt.setInt(1, departamento.getIdDepartamentos());
			ResultSet rs = pstmt.executeQuery();

			// Si existe lo metemos sin id
			if (rs.next() || departamento.getIdDepartamentos() < -1) {
				String query = "INSERT INTO Curso (nombre, descripcion) VALUES(?,?)";

				PreparedStatement pstmt2 = con.prepareStatement(query);
				// Establecemos los 2 parametros para
				// introducir un departamento a la base de
				// datos
				pstmt2.setString(1, departamento.getNombre());
				pstmt2.setString(2, departamento.getDescripcion());

				// Si alguno de los String es nulo devolvemos 0
				if (departamento.getNombre().equals(null))
					return 0;
				if (departamento.getDescripcion().equals(null))
					return 0;

				// Ejecutamos la query
				pstmt2.executeUpdate();
				// Si ha ido bien devolvera 1
				return 1;
				// Si no existe el id lo introducimos
			} else {
				String query = "INSERT INTO Curso (idCurso, nombre, descripcion) VALUES(?,?,?)";

				PreparedStatement pstmt3 = con.prepareStatement(query);
				// De nuevo, establecemos, esta vez 3
				// parametros para introducir un
				// departamento a la base de datos
				pstmt3.setInt(1, departamento.getIdDepartamentos());
				pstmt3.setString(2, departamento.getNombre());
				pstmt3.setString(3, departamento.getDescripcion());
				// Si alguno de los String son nulos devolvemos 0
				if (departamento.getNombre().equals(null))
					return 0;
				if (departamento.getDescripcion().equals(null))
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

	/**
	 * Actualiza cualquier campo de departamento segun un id de departamento
	 * 
	 * @param departamento
	 * @param con
	 * @return 1 si ha ido bien o 0 si ha ido mal
	 */
	public static int actualizarDepartamento(DepartamentoDO departamento, Connection con) {
		try {

			boolean campoPrevio = false;
			String query = "UPDATE Departamentos SET ";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Si los campos no son nulos, los vamos
			// añadiendo a la sentencia
			if (departamento.getNombre() != null) {
				query = query + "nombre = ?";
				campoPrevio = true;
			}

			if (departamento.getDescripcion() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "descripcion = ?";
				campoPrevio = true;
			}

			query = query + " WHERE idDepartamentos = ?";

			int dpsSigno = 1;
			// Si los campos no son nulos vamos
			// añadiendo como parametros los atributos
			// de departamento
			if (departamento.getNombre() != null) {
				pstmt.setString(dpsSigno, departamento.getNombre());
				dpsSigno++;
			}
			if (departamento.getDescripcion() != null) {
				pstmt.setString(dpsSigno, departamento.getDescripcion());
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
	 * @param id
	 * @return un departamento si ha ido bien o nulo si ha ido mal
	 */
	public static DepartamentoDO cargar(Connection con, int id) {
		try {
			// Creamos query
			String query = "SELECT * FROM Departamentos WHERE idDepartamentos=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de
			// departamento
			pstmt.setInt(1, id);
			// Creamos un resultset y ejecutamos la
			// consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un departamento y le asignamos los
			// datos de resultset
			DepartamentoDO Departamento1 = new DepartamentoDO();
			Departamento1.setIdDepartamentos(rs.getInt(1));
			Departamento1.setNombre(rs.getString(2));
			Departamento1.setDescripcion(rs.getString(3));
			// Devolvemos departamento
			return Departamento1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Si sale mal devolvemos null
			return null;
		}

	}
}
