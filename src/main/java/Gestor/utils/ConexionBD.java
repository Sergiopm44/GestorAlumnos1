package Gestor.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

	public static Connection conectarBD() {
		try {
			// Definimos el driver de la BD a la que nos conectamos
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Creamos una conexion activa con BD
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gestionalumnos", "root", "");

			// Si no salta la excepcion devolvemos la conexion
			return con;

		} // Capturamos cualquier excepcion que pueda haber
		catch (Exception e) {
			// Cuando salta el fallo mostramos un mensaje
			System.out.println("Error al conectarse");
			e.printStackTrace();
			return null;
		}

	}
}
