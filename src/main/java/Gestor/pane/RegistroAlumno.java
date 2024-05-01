package Gestor.pane;

import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegistroAlumno extends GridPane {

	public RegistroAlumno(Connection con) {

		Button btnConf = new Button("Confirmar");
		// Almacen del usuario nuevo
		AlumnoDO alumno = new AlumnoDO();

		// Espaciado o diferencia en lo horizontal
		this.setHgap(10);
		// Espaciado o diferencia en lo vertical
		this.setVgap(10);
		// Espaciado entre los elementos
		this.setPadding(new Insets(10, 10, 10, 10));

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

		// Usamos PassWordField paa que se vean * en vez
		// de la contraseña
		Label lblPass = new Label("Introduzca la contraseña: ");
		PasswordField txtPass = new PasswordField();

		Label lblTel = new Label("Introduzca su número telefónico: ");
		TextField txtTel = new TextField();

		Label lblMail = new Label("Introduzca su correo electrónico: ");
		TextField txtMail = new TextField();

		Label lblCurso = new Label("Introduzca el curso (1º,2º,3º...): ");
		TextField txtCurso = new TextField();

		this.add(txtdniA, 1, 0);
		this.add(lbldniA, 0, 0);
		this.add(txtfecNa, 1, 1);
		this.add(lblfecNa, 0, 1);
		this.add(txtNombre, 1, 2);
		this.add(lblNombre, 0, 2);
		this.add(txtApell, 1, 3);
		this.add(lblApell, 0, 3);
		this.add(txtUser, 1, 4);
		this.add(lbluser, 0, 4);
		this.add(txtPass, 1, 5);
		this.add(lblPass, 0, 5);
		this.add(txtTel, 1, 6);
		this.add(lblTel, 0, 6);
		this.add(txtMail, 1, 7);
		this.add(lblMail, 0, 7);
		this.add(txtCurso, 1, 8);
		this.add(lblCurso, 0, 8);

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

		this.add(btnConf, 0, 9, 2, 1);
	}
}
