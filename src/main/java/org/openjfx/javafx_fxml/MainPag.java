package org.openjfx.javafx_fxml;

import java.io.IOException;

import Gestor.model.AlumnoDO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPag extends Application {
	public Label welcome;
	public Label log;
	public Label profOrAl;
	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		// Booleans
		boolean profesor = false;
		boolean alumno = false;

		// Objetos
		ChoiceBox profOrNo = new ChoiceBox();
		VBox cajon = new VBox();

		welcome = new Label("Bienvenido a eRegister");
		profOrAl = new Label("¿Es usted Alumno o profesor?");
		log = new Label("¿Tiene una cuenta o desea registrarse?");

		// ChoiceBox, opciones y obtener el resultado
		profOrNo.getItems().addAll("Si", "No");
		profOrNo.getSelectionModel();

		// Si es si, el bolean de profesor es verdadero y si es no, el del alumno
		if (profOrNo.equals("Si")) {
			profesor = true;
		} else
			alumno = true;

		// Llamamos a las funciones dependiendo de si es alumno o no
		if (profesor) {

		}

		cajon.getChildren().addAll(welcome, profOrAl, log, profOrNo);

		scene = new Scene(cajon, 600, 700);

		stage.setTitle("Inicio Sesion");
		stage.setScene(scene);
		stage.show();

	}

	public static AlumnoDO registroAlumno() {
		try {
			String query = "INSERT INTO alumno (idAlumno,dniA,fechNa, nombre, apellido, telefono,email,Curso_idCurso) VALUES (?,?,?,?,?,?,?,?)";

			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		launch();
	}

}
