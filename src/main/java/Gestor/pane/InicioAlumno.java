package Gestor.pane;

import java.net.URL;
import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import Gestor.model.UsuarioDO;
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
	private static Stage stageAlumIni;
	private static Scene sceneInicioAlumno;

	public static UsuarioDO newUser;

	/**
	 * Funcion que inicia sesion del alumno con un usuario y una contraseña, si
	 * estos son los mismos que en la base de datos el alumno entrara en la
	 * aplicacion
	 * 
	 * @param con
	 */
	public InicioAlumno(Connection con) {

		AlumnoDO alumno = new AlumnoDO();

		Button btnConf = new Button("Confirmar");
		CheckBox showPassCheckBox = new CheckBox("Mostrar Contraseña");
		isOnDBase = false;

		stageAlumIni = new Stage();

		GridPane caja = new GridPane();

		// Espaciado o diferencia en lo horizontal
		caja.setHgap(10);
		// Espaciado o diferencia en lo vertical
		caja.setVgap(10);
		// Espaciado entre los elementos
		caja.setPadding(new Insets(10, 10, 10, 10));

		lbluser = new Label("Introduzca el usuario: ");
		txtUser = new TextField();

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
			// Obtiene el valor del usuario y la contraseña
			// introducidos
			String userName = txtUser.getText();
			String passName = txtHiddenPass.getText();
			alumno.setUsuario(userName);
			alumno.setContrasenia(passName);
			isOnDBase = AlumnoDAO.cargar(con, alumno);

			newUser = new UsuarioDO();
			boolean isAl = true;
			newUser.setUserNameA(userName);
			newUser.setAlumno(isAl);
			// Aquí deberías mostrar el resultado de la
			// autenticación en lugar de mostrar un diálogo
			// vacío
			// Mostrar una alerta dependiendo del valor de
			// isOnDBase
			if (isOnDBase) {
				Alert isOk = new Alert(Alert.AlertType.INFORMATION);

				isOk.setTitle("Sesión Iniciada");
				isOk.setHeaderText("¡Bienvenido " + txtUser.getText() + "!");
				isOk.setContentText("Puede continuar usando la Aplicación.");
				// Configuramos el evento que se activa cuando se
				// cierra la alerta

				isOk.setOnCloseRequest(event -> {
					stageAlumIni.close();
					// Creamos una nueva instancia de BuscadorA y
					// mostramos su escena
					BuscadorA buscadorA = new BuscadorA(con, new Stage());
				});

				// Mostramos la alerta y esperamos a que se cierre
				isOk.showAndWait();

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
		// Refacto scene a InicioAlumno
		sceneInicioAlumno = new Scene(caja, 600, 700);

		stageAlumIni.setTitle("Inicio Sesión Alumno");
		sceneInicioAlumno.getRoot().getStyleClass().add("InicioAlumno");
		// importa el url
		URL cssFile = getClass().getResource("/css/css.css");
		if (cssFile == null) {
			System.out.println("No se pudo encontrar el archivo CSS");
		} else {
			sceneInicioAlumno.getStylesheets().add(cssFile.toExternalForm());
		}
		stageAlumIni.setScene(sceneInicioAlumno);
		stageAlumIni.show();

	}

}
