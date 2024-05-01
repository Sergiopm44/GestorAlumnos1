package Gestor.pane;

import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InicioAlumno extends GridPane {

	public static Label lbluser;
	public static TextField txtUser;
	public static Label lblPass;
	public static PasswordField txtHiddenPass;
	public static TextField txtShowedPass;
	public static String userName;
	public static boolean isOnDBase;

	public InicioAlumno(Connection con) {
		Button btnConf = new Button("Confirmar");
		AlumnoDO alumno = new AlumnoDO();
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
			alumno.setUsuario(txtUser.getText());
			alumno.setContrasenia(txtHiddenPass.getText());
			isOnDBase = AlumnoDAO.cargar(con, alumno);
			// Aquí deberías mostrar el resultado de la
			// autenticación en lugar de mostrar un diálogo
			// vacío
			// Mostrar una alerta dependiendo del valor de
			// isOnDBase
			if (isOnDBase) {
				Alert isOk = new Alert(Alert.AlertType.INFORMATION);
				isOk.setTitle("Sesion Iniciada");
				isOk.setHeaderText("Bienvenido " + txtUser.getText());
				isOk.setContentText("Puede continuar usando la Aplicación");
				isOk.show();
			} else {
				Alert goneWrong = new Alert(Alert.AlertType.INFORMATION);
				goneWrong.setTitle("Algo ha ido mal");
				goneWrong.setHeaderText("Ha ocurrido un error");
				goneWrong.setContentText("Ha ocurrido un error\n" + "Porfavor, inténtelo de nuevo más tarde\n");
				goneWrong.show();
			}
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
