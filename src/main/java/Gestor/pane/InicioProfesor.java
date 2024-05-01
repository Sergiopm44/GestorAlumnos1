package Gestor.pane;

import java.sql.Connection;

import Gestor.model.AlumnoDO;
import Gestor.model.ProfesorDAO;
import Gestor.model.ProfesorDO;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InicioProfesor {

	public static Label lbluser;
	public static TextField txtUser;
	public static Label lblPass;
	public static PasswordField txtHiddenPass;
	public static TextField txtShowedPass;

	public InicioProfesor(Connection con) {

		Button btnConf = new Button("Confirmar");
		ProfesorDO profesor = new AlumnoDO();
		CheckBox showPassCheckBox = new CheckBox("Mostrar Contraseña");
		isOnDBase = false;

		// Espaciado o diferencia en lo horizontal
		this.setHgap(10);
		// Espaciado o diferencia en lo vertical
		this.setVgap(10);
		// Espaciado entre los elementos
		this.setPadding(new Insets(10, 10, 10, 10));

		lbluser = new Label("Introduzca el usuario: ");
		txtUser = new TextField();
		userName = txtUser.getText();

		lblPass = new Label("Introduzca la contraseña: ");
		txtHiddenPass = new PasswordField();
		txtShowedPass = new TextField();

		// Agregar listener al CheckBox para controlar la
		// visibilidad de la contraseña
		showPassCheckBox.setOnAction(e -> {
			boolean showPass = showPassCheckBox.isSelected();
			txtHiddenPass.setVisible(!showPass);
			txtShowedPass.setVisible(showPass);
			if (!showPass) {
				txtHiddenPass.setText(txtShowedPass.getText());
			} else {
				txtShowedPass.setText(txtHiddenPass.getText());
			}
		});

		btnConf.setOnAction(e -> {
			profesor.setUsuario(txtUser.getText());
			profesor.setContrasenia(txtHiddenPass.getText());
			isOnDBase = ProfesorDAO.cargar(con, profesor);
			// Aquí deberías mostrar el resultado de la
			// autenticación en lugar de mostrar un diálogo
			// vacío
		});

		// Por defecto, se muestra el campo de contraseña
		// oculto
		txtHiddenPass.setVisible(true);
		txtShowedPass.setVisible(false);

		this.add(txtUser, 1, 0);
		this.add(lbluser, 0, 0);
		this.add(txtHiddenPass, 1, 1);
		this.add(txtShowedPass, 1, 1);
		this.add(showPassCheckBox, 0, 2);
		this.add(lblPass, 0, 1);
		this.add(btnConf, 0, 9, 2, 1);

	}
}
