package Gestor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoDAO {
	/**
	 * Elimina un curso con un determinado id de curso
	 * 
	 * @param idCurso
	 * @param con
	 * @return 0 si ha ido bien o -1 si ha ido mal
	 */
	public static int eliminarCurso(int idCurso, Connection con) {

		try {
			// Borramos el curso con un determinado id
			// de curso
			String query = "DELETE FROM Curso WHERE idCurso=?";
			// Creamos statement
			PreparedStatement pstmt = con.prepareStatement(query);
			// Establecemos el primer parametro de la
			// consulta
			pstmt.setInt(1, idCurso);
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
	 * Inserta un curso con un determinado id, el cual si existe se le meten los
	 * campos sin id y si por el contrario si no existe se le mete el id junto a los
	 * campos
	 * 
	 * @param con
	 * @param curso
	 * @return 0 si ha ido bien o -1 si ha ido mal
	 */
	public static int insertCurso(Connection con, CursoDO curso) {
		try {

			// Comprobar que no existe un curso con
			// ese id en bd
			String selectID = "Select * From Curso Where idCurso=?";
			PreparedStatement pstmt = con.prepareStatement(selectID);
			// Establecemos el primer parametro de la
			// query
			pstmt.setInt(1, curso.getIdCurso());
			ResultSet rs = pstmt.executeQuery();

			// Si existe lo metemos sin id
			if (rs.next() || curso.getIdCurso() < -1) {
				String query = "INSERT INTO Curso (nombre, descripcion) VALUES(?,?)";

				PreparedStatement pstmt2 = con.prepareStatement(query);
				// Establecemos los 2 parametros para
				// introducir un curso a la base de
				// datos
				pstmt2.setString(1, curso.getNombre());
				pstmt2.setString(2, curso.getDescripcion());

				// Si algun String es nulo devolvemos 0
				if (curso.getNombre().equals(null))
					return 0;
				if (curso.getDescripcion().equals(null))
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
				// curso a la base de datos
				pstmt3.setInt(1, curso.getIdCurso());
				pstmt3.setString(2, curso.getNombre());
				pstmt3.setString(3, curso.getDescripcion());
				// Si algun String es nulo devolvemos 0
				if (curso.getNombre().equals(null))
					return 0;
				if (curso.getDescripcion().equals(null))
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
	 * Actualiza uno o varios campos de curso segun el id de curso introducido
	 * 
	 * @param curso
	 * @param con
	 * @return 1 si ha ido bien o 0 si ha ido mal
	 */
	public static int actualizarCurso(CursoDO curso, Connection con) {
		try {

			boolean campoPrevio = false;
			String query = "UPDATE Centro SET ";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Si los campos no son nulos, los vamos
			// añadiendo a la sentencia
			if (curso.getNombre() != null) {
				query = query + "nombre = ?";
				campoPrevio = true;
			}

			if (curso.getDescripcion() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "descripcion = ?";
				campoPrevio = true;
			}

			query = query + " WHERE idCurso = ?";

			int dpsSigno = 1;
			// Si los campos no son nulos vamos
			// añadiendo como parametros los atributos
			// de curso
			if (curso.getNombre() != null) {
				pstmt.setString(dpsSigno, curso.getNombre());
				dpsSigno++;
			}
			if (curso.getDescripcion() != null) {
				pstmt.setString(dpsSigno, curso.getDescripcion());
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
	 * 
	 * @param con
	 * @param id
	 * @return un curso si ha ido bien o nulo si ha ido mal
	 */
	public static CursoDO cargar(Connection con, int id) {
		try {
			// Creamos query
			String query = "SELECT * FROM Curso WHERE idCurso=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de
			// curso
			pstmt.setInt(1, id);
			// Creamos un resultset y ejecutamos la
			// consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un curso y le asignamos los
			// datos de resultset
			CursoDO Curso1 = new CursoDO();
			Curso1.setIdCurso(rs.getInt(1));
			Curso1.setNombre(rs.getString(2));
			Curso1.setDescripcion(rs.getString(3));
			// Devolvemos curso
			return Curso1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Si sale mal devolvemos null
			return null;
		}

	}
}
