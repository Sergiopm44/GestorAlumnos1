package Gestor.pane;

import java.net.URL;
import java.sql.Connection;

import Gestor.model.ProfesorDAO;
import Gestor.model.ProfesorDO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistroProfesor extends GridPane {

	private static Stage stage;
	private static Scene sceneRegisProf;

	/**
	 * Funcion que añade un alumno a la base de datos segun los datos que se
	 * proporcionan en la aplicacion
	 * 
	 * @param con
	 * @param primaryStage
	 */
	public RegistroProfesor(Connection con, Stage primaryStage) {

		this.stage = primaryStage;
		Button btnConf = new Button("Confirmar");
		Button btnVolver = new Button("Volver");
		// Almacen del usuario nuevo
		ProfesorDO profesor = new ProfesorDO();

		GridPane caja = new GridPane();

		// Espaciado o diferencia en lo horizontal
		caja.setHgap(10);
		// Espaciado o diferencia en lo vertical
		caja.setVgap(10);
		// Espaciado entre los elementos
		caja.setPadding(new Insets(10, 10, 10, 10));

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

		caja.add(txtdniP, 1, 0);
		caja.add(lbldniP, 0, 0);
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
		caja.add(txtDep, 1, 8);
		caja.add(lblDep, 0, 8);

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

		btnVolver.setOnAction(e -> {
			stage.close();
		});

		caja.add(btnVolver, 0, 10);
		caja.add(btnConf, 0, 9, 2, 1);
		// Refactoriza scene a RegistroProfesor
		sceneRegisProf = new Scene(caja, 750, 800);

		stage.setTitle("Registro de profesores");
		sceneRegisProf.getRoot().getStyleClass().add("RegistroProfesor");
		// importa el url
		URL cssFile = getClass().getResource("/css/css.css");
		if (cssFile == null) {
			System.out.println("No se pudo encontrar el archivo CSS");
		} else {
			sceneRegisProf.getStylesheets().add(cssFile.toExternalForm());
		}
		stage.setResizable(false);
		stage.setScene(sceneRegisProf);
		stage.show();
	}
}
