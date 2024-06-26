package Gestor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CentroDAO {
	/**
	 * Elimina un centro dependiendo del id del centro
	 * 
	 * @param idCentro
	 * @param con
	 * @return 0 si sale bien o -1 si sale bien
	 */
	public static int eliminarCentro(int idCentro, Connection con) {

		try {
			// Borramos el centro con un determinado id
			// de centro
			String query = "DELETE * FROM Centro WHERE idCentro=?";
			// Creamos statement
			PreparedStatement pstmt = con.prepareStatement(query);
			// Establecemos el primer parametro de la
			// consulta
			pstmt.setInt(1, idCentro);
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
	 * Inserta un centro con un determinado id de centro. Si el id ya existe se le
	 * introduciran los demas datos, si no existe se le introducira con un
	 * determinado id
	 * 
	 * @param con
	 * @param centro
	 * @return 0 si sale bien o -1 si sale mal
	 */
	public static int insertCentro(Connection con, CentroDO centro) {
		try {

			// Comprobar que no existe un centro con
			// ese id en bd
			String selectID = "Select * From Centro Where idCentro=?";
			PreparedStatement pstmt = con.prepareStatement(selectID);
			// Establecemos el primer parametro de la
			// query
			pstmt.setInt(1, centro.getIdCentro());
			ResultSet rs = pstmt.executeQuery();

			// Si existe lo metemos sin id
			if (rs.next() || centro.getIdCentro() < -1) {
				String query = "INSERT INTO Centro (nif, direccion, localidad, provincia, cPostal, Telefono, email) VALUES(?,?,?,?,?,?,?)";

				PreparedStatement pstmt2 = con.prepareStatement(query);
				// Establecemos los 7 parametros para
				// introducir un centro a la base de
				// datos
				pstmt2.setInt(1, centro.getNif());
				pstmt2.setString(2, centro.getDireccion());
				pstmt2.setString(3, centro.getLocalidad());
				pstmt2.setString(4, centro.getProvincia());
				pstmt2.setInt(5, centro.getcPostal());
				pstmt2.setInt(6, centro.getTelefono());
				pstmt2.setString(7, centro.getEmail());
				// Si algun String es nulo devolvemos 0
				if (centro.getDireccion().equals(null))
					return 0;
				if (centro.getLocalidad().equals(null))
					return 0;
				if (centro.getProvincia().equals(null))
					return 0;
				if (centro.getEmail().equals(null))
					return 0;

				// Ejecutamos la query
				pstmt2.executeUpdate();
				// Si ha ido bien devolvera 1
				return 1;
				// Si no existe el id lo introducimos
			} else {
				String query = "INSERT INTO Centro (idCentro, nif, direccion, localidad, provincia, cPostal, Telefono, email) VALUES(?,?,?,?,?,?,?,?)";

				PreparedStatement pstmt3 = con.prepareStatement(query);
				// De nuevo, establecemos, esta vez 8
				// parametros para introducir un
				// centro a la base de datos
				pstmt3.setInt(1, centro.getIdCentro());
				pstmt3.setInt(2, centro.getNif());
				pstmt3.setString(3, centro.getDireccion());
				pstmt3.setString(4, centro.getLocalidad());
				pstmt3.setString(5, centro.getProvincia());
				pstmt3.setInt(6, centro.getcPostal());
				pstmt3.setInt(7, centro.getTelefono());
				pstmt3.setString(8, centro.getEmail());
				// Si algun String es nulo devolvemos 0
				if (centro.getDireccion().equals(null))
					return 0;
				if (centro.getLocalidad().equals(null))
					return 0;
				if (centro.getProvincia().equals(null))
					return 0;
				if (centro.getEmail().equals(null))
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
	 * Actualiza un determinado centro segun el id introducido, se puede modificar
	 * cualquier campo de centro
	 * 
	 * @param centro
	 * @param con
	 * @return 1 si ha salido bien o 0 si ha salido mal
	 */
	public static int actualizarCentro(CentroDO centro, Connection con) {
		try {

			boolean campoPrevio = false;
			String query = "UPDATE Centro SET ";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Si los campos no son nulos, los vamos
			// añadiendo a la sentencia
			if (centro.getNif() != -1) {
				query = query + "nif = ?";
				campoPrevio = true;
			}

			if (centro.getDireccion() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "direccion = ?";
				campoPrevio = true;
			}

			if (centro.getLocalidad() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "localidad = ?";
				campoPrevio = true;
			}

			if (centro.getProvincia() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "provincia = ?";
				campoPrevio = true;
			}

			if (centro.getcPostal() != -1) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "cPostal = ?";
				campoPrevio = true;
			}

			if (centro.getTelefono() != -1) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "Telefono = ?";
				campoPrevio = true;
			}
			if (centro.getEmail() != null) {
				if (campoPrevio) {
					query = query + ", ";
				}
				query = query + "email = ?";
			}

			query = query + " WHERE idCentro = ?";

			int dpsSigno = 1;
			// Si los campos no son nulos vamos
			// añadiendo como parametros los atributos
			// de centro
			if (centro.getNif() != -1) {
				pstmt.setInt(dpsSigno, centro.getNif());
				dpsSigno++;
			}
			if (centro.getDireccion() != null) {
				pstmt.setString(dpsSigno, centro.getDireccion());
				dpsSigno++;
			}
			if (centro.getLocalidad() != null) {
				pstmt.setString(dpsSigno, centro.getLocalidad());
				dpsSigno++;
			}
			if (centro.getProvincia() != null) {
				pstmt.setString(dpsSigno, centro.getProvincia());
				dpsSigno++;
			}
			if (centro.getcPostal() != -1) {
				pstmt.setInt(dpsSigno, centro.getcPostal());
				dpsSigno++;
			}
			if (centro.getTelefono() != -1) {
				pstmt.setInt(dpsSigno, centro.getTelefono());
				dpsSigno++;
			}
			if (centro.getEmail() != null) {
				pstmt.setString(dpsSigno, centro.getEmail());
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
	 * @return un centro si sale bien o nulo si sale mal
	 */
	public static CentroDO cargar(Connection con, int id) {
		try {
			// Creamos query
			String query = "SELECT * FROM Centro WHERE idCentro = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// Introducimos como parametro el id de
			// centro
			pstmt.setInt(1, id);
			// Creamos un resultset y ejecutamos la
			// consulta
			ResultSet rs = pstmt.executeQuery();
			// Creamos un centro y le asignamos los
			// datos de resultset
			CentroDO Centro1 = new CentroDO();
			Centro1.setIdCentro(rs.getInt(1));
			Centro1.setNif(rs.getInt(2));
			Centro1.setDireccion(rs.getString(3));
			Centro1.setLocalidad(rs.getString(4));
			Centro1.setProvincia(rs.getString(5));
			Centro1.setcPostal(rs.getInt(6));
			Centro1.setTelefono(rs.getInt(7));
			Centro1.setEmail(rs.getString(8));
			// Devolvemos centro
			return Centro1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			// Si sale mal devolvemos null
			return null;
		}

	}
}
