package org.openjfx.javafx_fxml;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Gestor.model.AlumnoDO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

		// Si es si, el bolean de profesor es verdadero y
		// si es no, el del alumno
		if (profOrNo.equals("Si")) {
			profesor = true;
		} else
			alumno = true;

		// Llamamos a las funciones dependiendo de si es
		// alumno o no
		if (profesor) {

		}

		cajon.getChildren().addAll(welcome, profOrAl, log, profOrNo);

		scene = new Scene(cajon, 600, 700);

		stage.setTitle("Inicio Sesion");
		stage.setScene(scene);
		stage.show();

	}

	// Registro para un alumno
	public static AlumnoDO registroAlumno(Connection con) {
		try {
			int idAlumno = 0;
			AlumnoDO alumno = new AlumnoDO();
			String query = "INSERT INTO alumno (idAlumno,dniA,fechNa, nombre, apellido, telefono,email,Curso_idCurso) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);

			// Campo para el alumno
			Label lbluser = new Label("Introduzca el usuario: ");
			TextField txtUser = new TextField("Usuario");
			pstmt.setString(2, txtUser.toString());

			// Campo para el idAlumno
			while (idAlumno == alumno.getIdAlumno()) {
				idAlumno++;
			}
			pstmt.setInt(1, idAlumno);

			// Campo dniA
			Label lbldniA = new Label("Introduzca su dni: ");
			TextField txtdniA = new TextField("dni");
			pstmt.setString(2, txtdniA.toString());

			// Campo fecha Nacimiento
			Label lblfecNa = new Label("Introduzca su fecha de Nacimiento: ");
			TextField txtfecNa = new TextField("Fecha");
			String fecNa = txtfecNa.getText();
			pstmt.setInt(3, Integer.parseInt(fecNa));

			// Campo de nombre
			Label lblNombre = new Label("Introduzca su nombre: ");
			TextField txtNombre = new TextField("Nombre");
			pstmt.setString(4, txtNombre.toString());

			// Campo del Apellido
			Label lblApell = new Label("Introduzca su apellido: ");
			TextField txtApell = new TextField("Apellido");
			pstmt.setString(5, txtApell.toString());

			// Campo para el telefono
			Label lblTel = new Label("Introduzca su número telefónico: ");
			TextField txtTel = new TextField("Número");
			String tel = txtfecNa.getText();
			pstmt.setInt(6, Integer.parseInt(tel));

			return alumno;
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
