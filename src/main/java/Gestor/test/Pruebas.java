package Gestor.test;

import java.io.IOException;
import java.sql.Connection;

import org.openjfx.javafx_fxml.MainPag;

import Gestor.utils.ConexionBD;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pruebas extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		VBox cajon = new VBox();

		Connection con = ConexionBD.conectarBD();

		MainPag prueba = new MainPag();

		Scene scene = new Scene(cajon, 600, 600);

		stage.setTitle("Gestor de Alumnos");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}

}
