package org.openjfx.javafx_fxml;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Gestor.pane.RegistroAlumno;
import Gestor.utils.ConexionBD;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPag extends Application {
	public Label welcome;
	public Label log;
	public Label profOrAl;
	private static Scene scene;
	public static Stage stage;
	Connection con;

	@Override
	public void start(Stage stage) throws IOException {
		// Booleans
		boolean profesor = false;
		boolean alumno = false;

		Connection con = ConexionBD.conectarBD();

		// Objetos
		ChoiceBox<String> profOrNo = new ChoiceBox<String>();
		VBox cajon = new VBox();

		// Boton de Inicio de sesion y Registro
		Button btnRegis = new Button();
		Button btnLogin = new Button();

		welcome = new Label("Bienvenido a eRegister");
		profOrAl = new Label("¿Es usted Alumno o profesor?");
		log = new Label("¿Tiene una cuenta o desea registrarse?");

		// ChoiceBox, opciones y obtener el resultado
		profOrNo.getItems().addAll("Si", "No");
		profOrNo.getSelectionModel();

		// Si es si, el bolean de profesor es verdadero y
		// si es no, el del alumno
		if (profOrNo.getItems().toString().equals("Si")) {
			profesor = true;
		} else if (profOrNo.getItems().toString().equals("No")) {
			alumno = true;
		}

		// Llamamos a las funciones dependiendo de si es
		// alumno o no
		if (alumno == true) {
			btnRegis.setOnAction(e -> {
				RegistroAlumno regisAl = new RegistroAlumno(con);
				scene = new Scene(regisAl, 600, 300);

				stage.setScene(scene);
				stage.show();

			});
		}

		cajon.getChildren().addAll(welcome, profOrAl, log, profOrNo, btnLogin, btnRegis);

		scene = new Scene(cajon, 600, 700);

		stage.setTitle("Inicio Sesion");
		stage.setScene(scene);
		stage.show();

	}

	// Registro para un profesor
	public static void registroProfesor(Connection con) {
		try {
			VBox panelVPr = new VBox();
			String query = "INSERT INTO profesores (dniP,fechNa, nombre, apellido,usuario,contrasenia, telefono,email,Departamentos_idDepartamentos) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);

			Label lbldniP = new Label("Introduzca su dni: ");
			TextField txtdniP = new TextField();
			panelVPr.getChildren().add(lbldniP);
			panelVPr.getChildren().add(txtdniP);

			Label lblfecNa = new Label("Introduzca su fecha de Nacimiento: ");
			TextField txtfecNa = new TextField();
			panelVPr.getChildren().add(lblfecNa);
			panelVPr.getChildren().add(txtfecNa);

			Label lblNombre = new Label("Introduzca su nombre: ");
			TextField txtNombre = new TextField();
			panelVPr.getChildren().add(lblNombre);
			panelVPr.getChildren().add(txtNombre);

			Label lblApell = new Label("Introduzca su apellido: ");
			TextField txtApell = new TextField();
			panelVPr.getChildren().add(lblApell);
			panelVPr.getChildren().add(txtApell);

			Label lbluser = new Label("Introduzca el usuario: ");
			TextField txtUser = new TextField();
			panelVPr.getChildren().add(lbluser);
			panelVPr.getChildren().add(txtUser);

			// Usamos PassWordField paa que se vean * en vez
			// de la contraseña
			Label lblPass = new Label("Introduzca la contraseña: ");
			PasswordField txtPass = new PasswordField();

			panelVPr.getChildren().add(lblPass);
			panelVPr.getChildren().add(txtPass);

			Label lblTel = new Label("Introduzca su número telefónico: ");
			TextField txtTel = new TextField();
			panelVPr.getChildren().add(lblTel);
			panelVPr.getChildren().add(txtTel);

			Label lblMail = new Label("Introduzca su correo electrónico: ");
			TextField txtMail = new TextField();
			panelVPr.getChildren().add(lblMail);
			panelVPr.getChildren().add(txtMail);

			Label lblDep = new Label("Introduzca el número de departamento al que pertenece (1º,2º,3º...): ");
			TextField txtDep = new TextField();
			panelVPr.getChildren().add(lblDep);
			panelVPr.getChildren().add(txtDep);

			pstmt.setString(1, txtdniP.getText());
			pstmt.setInt(2, Integer.parseInt(txtfecNa.getText()));
			pstmt.setString(3, txtNombre.getText());
			pstmt.setString(4, txtApell.getText());
			pstmt.setString(5, txtUser.getText());
			pstmt.setString(6, txtPass.getText());
			pstmt.setInt(7, Integer.parseInt(txtTel.getText()));
			pstmt.setString(8, txtMail.getText());
			pstmt.setInt(9, Integer.parseInt(txtDep.getText()));

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error inesperado");
			// Establecemos el mensaje del diálogo
			alert.setHeaderText("Error del registro");
			alert.setContentText("Se ha producido un error al registrse como profesor, porfavor revise "
					+ "los datos ingresados o recarge e intente de nuevo ");

		}
	}

	public static void loginAl(Connection con) {

		try {
			String query = "SELECT usuario, contrasenia FROM alumno WHERE usuario=? AND contrasenia=?";
			PreparedStatement pstmt = con.prepareStatement(query);

			// Campo para el alumno
			Label lbluser = new Label("Introduzca el usuario: ");
			TextField txtUser = new TextField("Usuario");
			pstmt.setString(1, txtUser.getText().toString());

			// Campo para la contraseña
			Label lblPass = new Label("Introduzca la contraseña: ");
			TextField txtPass = new TextField("Contraseña");
			pstmt.setString(6, txtUser.getText().toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error inesperado");
			// Establecemos el mensaje del diálogo
			alert.setHeaderText("Error del login");
			alert.setContentText("Se ha producido un error, al logearse revise si ha escrito "
					+ "bien su nombre de usuario o la contraseña");

		}

	}

	public static void main(String[] args) {
		launch();
	}

}
