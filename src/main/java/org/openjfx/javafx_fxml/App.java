package org.openjfx.javafx_fxml;

import java.io.IOException;
import java.sql.Connection;

import Gestor.utils.ConexionBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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

		MainPag mainPag = new MainPag(con, stage);
		mainPag.setAlignment(Pos.TOP_CENTER);

		scene = new Scene(mainPag, 920, 460);

		scene.getStylesheets().add(getClass().getResource("/css/css.css").toExternalForm());
		scene.getRoot().getStyleClass().add("body");

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