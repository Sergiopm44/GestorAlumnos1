package Gestor.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

	public static Connection conectarBD() {

		try {

			// Definimos el driver de la BD a la que nos
			// conectamos
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Creamos una conexion activa con BD
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gestionalumnos", "root", "");

			// Si no ha saltado la excepcion devolvemos las
			// conexion
			return con;

		} catch (Exception exception) {
			// Cuando salta el fallo mostramos un mensaje
			System.out.println("Error al conectarse");
			exception.printStackTrace();
			return null;
			// System.out.println("Espere unos instantes y
			// intentelo de nuevo");

		}
	}

}
