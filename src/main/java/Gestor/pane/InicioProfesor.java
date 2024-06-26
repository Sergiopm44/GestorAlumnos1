package Gestor.pane;

import java.net.URL;
import java.sql.Connection;

import Gestor.model.ProfesorDAO;
import Gestor.model.ProfesorDO;
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

public class InicioProfesor extends GridPane {

	private static Label lbluser;
	private static TextField txtUser;
	private static Label lblPass;
	private static PasswordField txtHiddenPass;
	private static TextField txtShowedPass;
	public static String userName;
	public static String passName;
	private static boolean isOnDBase;
	private static Stage stage;
	private static Scene sceneProfIni;
	public boolean passOk;

	public static UsuarioDO newUser;

	/**
	 * Funcion que inicia sesion del profesor con un usuario y una contraseña, si
	 * estos son los mismos que en la base de datos el profesor entrara en la
	 * aplicacion
	 * 
	 * @param con
	 */
	public InicioProfesor(Connection con) {

		// Creacion de objetos
		ProfesorDO profesor = new ProfesorDO();

		Button btnConf = new Button("Confirmar");
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
			String userName = txtUser.getText();
			String passName = txtHiddenPass.getText();
			profesor.setUsuario(userName);
			profesor.setContrasenia(passName);
			isOnDBase = ProfesorDAO.cargar(con, profesor);

			newUser = new UsuarioDO();
			boolean isAl = false;
			newUser.setUserNameP(userName);
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
					stage.close();
					// Creamos una nueva instancia de BuscadorA y
					// mostramos su escena
					BuscadorP buscadorP = new BuscadorP(con, new Stage());
				});

				// Mostramos la alerta y esperamos a que se cierre
				isOk.showAndWait();
			} else {
				Alert goneWrong = new Alert(Alert.AlertType.INFORMATION);
				goneWrong.setTitle("Algo ha ido mal");
				goneWrong.setHeaderText("Ha ocurrido un error");
				goneWrong.setContentText(
						"No se ha encontrado el usuario o la contraseña\n" + "Porfavor, inténtelo de nuevo.\n");
				goneWrong.showAndWait();
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
		// Refactor scene a InicioProfesor
		sceneProfIni = new Scene(caja, 600, 700);

		stage.setTitle("Inicio Sesión Profesor");
		sceneProfIni.getRoot().getStyleClass().add("InicioProfesor");
		// importa el url
		URL cssFile = getClass().getResource("/css/css.css");
		if (cssFile == null) {
			System.out.println("No se pudo encontrar el archivo CSS");
		} else {
			sceneProfIni.getStylesheets().add(cssFile.toExternalForm());
		}
		stage.setScene(sceneProfIni);
		stage.setResizable(false);
		stage.show();

	}
}
