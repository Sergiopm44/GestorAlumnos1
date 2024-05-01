package org.openjfx.javafx_fxml;

import java.io.IOException;
import java.sql.Connection;

import Gestor.pane.RegistroAlumno;
import Gestor.utils.ConexionBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	public static Connection con = ConexionBD.conectarBD();

	@Override
	public void start(Stage stage) throws IOException {

		RegistroAlumno registro = new RegistroAlumno(con);

		scene = new Scene(registro, 600, 600);

		stage.setTitle("Gestor de Alumnos");
		stage.setScene(scene);
		stage.show();

	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}