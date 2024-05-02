package Gestor.pane;

import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistroAlumno extends GridPane {

	private Stage stage;
	private Scene scene;

	/**
	 * Funcion que añade un alumno a la base de datos segun los datos que se
	 * proporcionan en la aplicacion
	 * 
	 * @param con
	 * @param primaryStage
	 */
	public RegistroAlumno(Connection con, Stage primaryStage) {
		this.stage = primaryStage;

		Button btnConf = new Button("Confirmar");
		// Almacen del usuario nuevo
		AlumnoDO alumno = new AlumnoDO();

		GridPane caja = new GridPane();

		// Espaciado o diferencia en lo horizontal
		caja.setHgap(10);
		// Espaciado o diferencia en lo vertical
		caja.setVgap(10);
		// Espaciado entre los elementos
		caja.setPadding(new Insets(10, 10, 10, 10));

		Label lbldniA = new Label("Introduzca su dni: ");
		TextField txtdniA = new TextField();

		Label lblfecNa = new Label("Introduzca su fecha de Nacimiento (YYYY-MM-DD): ");
		TextField txtfecNa = new TextField();

		Label lblNombre = new Label("Introduzca su nombre: ");
		TextField txtNombre = new TextField();

		Label lblApell = new Label("Introduzca su apellido: ");
		TextField txtApell = new TextField();

		Label lbluser = new Label("Introduzca el usuario: ");
		TextField txtUser = new TextField();

		// Usamos PassWordField para que se vean * en vez
		// de la contraseña
		Label lblPass = new Label("Introduzca la contraseña: ");
		PasswordField txtPass = new PasswordField();

		Label lblTel = new Label("Introduzca su número telefónico: ");
		TextField txtTel = new TextField();

		Label lblMail = new Label("Introduzca su correo electrónico: ");
		TextField txtMail = new TextField();

		Label lblCurso = new Label("Introduzca el curso (1º,2º,3º...): ");
		TextField txtCurso = new TextField();

		caja.add(txtdniA, 1, 0);
		caja.add(lbldniA, 0, 0);
		caja.add(txtfecNa, 1, 1);
		caja.add(lblfecNa, 0, 1);
		caja.add(txtNombre, 1, 2);
		caja.add(lblNombre, 0, 2);
		caja.add(txtApell, 1, 3);
		caja.add(lblApell, 0, 3);
		caja.add(txtUser, 1, 4);
		caja.add(lbluser, 0, 4);
		caja.add(txtPass, 1, 5);
		caja.add(lblPass, 0, 5);
		caja.add(txtTel, 1, 6);
		caja.add(lblTel, 0, 6);
		caja.add(txtMail, 1, 7);
		caja.add(lblMail, 0, 7);
		caja.add(txtCurso, 1, 8);
		caja.add(lblCurso, 0, 8);

		btnConf.setOnAction(e -> {
			alumno.setDniA(txtdniA.getText());
			alumno.setFechNa(txtfecNa.getText());
			alumno.setNombre(txtNombre.getText());
			alumno.setApellido(txtApell.getText());
			alumno.setUsuario(txtUser.getText());
			alumno.setContrasenia(txtPass.getText());
			alumno.setTelefono(Integer.parseInt(txtTel.getText()));
			alumno.setEmail(txtMail.getText());
			alumno.setCurso_idCurso(Integer.parseInt(txtCurso.getText()));
			AlumnoDAO.insertAlumno(con, alumno);
		});

		caja.add(btnConf, 0, 9, 2, 1);

		scene = new Scene(caja, 600, 700);

		stage.setTitle("Registro de Alumnos");
		stage.setScene(scene);
		stage.show();
	}
}
