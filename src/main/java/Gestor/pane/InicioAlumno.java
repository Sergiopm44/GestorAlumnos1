package Gestor.pane;

import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InicioAlumno extends GridPane {

	private static Label lbluser;
	private static TextField txtUser;
	private static Label lblPass;
	private static PasswordField txtHiddenPass;
	private static TextField txtShowedPass;
	public static String userName;
	public static String passName;
	private static boolean isOnDBase;
	private static Stage stage;
	private static Scene scene;

	public InicioAlumno(Connection con) {
		Button btnConf = new Button("Confirmar");
		AlumnoDO alumno = new AlumnoDO();
		CheckBox showPassCheckBox = new CheckBox("Mostrar Contraseña");
		isOnDBase = false;
		stage = new Stage();

		GridPane caja = new GridPane();

		// Espaciado o diferencia en lo horizontal
		caja.setHgap(10);
		// Espaciado o diferencia en lo vertical
		caja.setVgap(10);
		// Espaciado entre los elementos
		caja.setPadding(new Insets(10, 10, 10, 10));

		lbluser = new Label("Introduzca el usuario: ");
		txtUser = new TextField();
		userName = txtUser.getText();

		lblPass = new Label("Introduzca la contraseña: ");
		txtHiddenPass = new PasswordField();
		txtShowedPass = new TextField();
		passName = txtHiddenPass.getText();
		passName = txtShowedPass.getText();

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
				stage.close();
			} else {
				Alert goneWrong = new Alert(Alert.AlertType.INFORMATION);
				goneWrong.setTitle("Algo ha ido mal");
				goneWrong.setHeaderText("Ha ocurrido un error");
				goneWrong.setContentText(
						"No se ha encontrado el usuario o la contraseña\n" + "Porfavor, inténtelo de nuevo.\n");
				goneWrong.show();
			}

		});

		// Por defecto, se muestra el campo de contraseña
		// oculto
		txtHiddenPass.setVisible(true);
		txtShowedPass.setVisible(false);

		caja.add(txtUser, 1, 0);
		caja.add(lbluser, 0, 0);
		caja.add(txtHiddenPass, 1, 1);
		caja.add(txtShowedPass, 1, 1);
		caja.add(showPassCheckBox, 0, 2);
		caja.add(lblPass, 0, 1);
		caja.add(btnConf, 0, 9, 2, 1);

		scene = new Scene(caja, 600, 700);

		stage.setTitle("Inicio Sesión Alumno");
		stage.setScene(scene);
		stage.show();

	}
}
