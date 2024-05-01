package Gestor.pane;

import java.sql.Connection;

import Gestor.model.ProfesorDAO;
import Gestor.model.ProfesorDO;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegistroProfesor extends GridPane {

	public RegistroProfesor(Connection con) {

		Button btnConf = new Button("Confirmar");
		// Almacen del usuario nuevo
		ProfesorDO profesor = new ProfesorDO();

		// Espaciado o diferencia en lo horizontal
		this.setHgap(10);
		// Espaciado o diferencia en lo vertical
		this.setVgap(10);
		// Espaciado entre los elementos
		this.setPadding(new Insets(10, 10, 10, 10));

		Label lbldniP = new Label("Introduzca su dni: ");
		TextField txtdniP = new TextField();

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

		Label lblDep = new Label("Introduzca el numero de departamento (1,2,3...): ");
		TextField txtDep = new TextField();

		this.add(txtdniP, 1, 0);
		this.add(lbldniP, 0, 0);
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
		this.add(txtDep, 1, 8);
		this.add(lblDep, 0, 8);

		btnConf.setOnAction(e -> {
			profesor.setDniP(txtdniP.getText());
			profesor.setFechNa(txtfecNa.getText());
			profesor.setNombre(txtNombre.getText());
			profesor.setApellido(txtApell.getText());
			profesor.setUsuario(txtUser.getText());
			profesor.setContrasenia(txtPass.getText());
			profesor.setTelefono(Integer.parseInt(txtTel.getText()));
			profesor.setEmail(txtMail.getText());
			profesor.setDepartamentos_idDepartamentos(Integer.parseInt(txtDep.getText()));
			ProfesorDAO.insertProfesor(con, profesor);
		});

		this.add(btnConf, 0, 9, 2, 1);
	}
}
