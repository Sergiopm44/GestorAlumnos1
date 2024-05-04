package Gestor.pane;

import java.net.URL;
import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BuscadorA extends GridPane {

	private static Scene sceneBuscadorA;
	private static Stage stage;

	public BuscadorA(Connection con, Stage primaryStage) {
		this.stage = primaryStage;

		// Menu
		AppMenu menu = new AppMenu(primaryStage);

		// Objeto para buscar alumno
		AlumnoDAO searchA = new AlumnoDAO();

		Button btnAcept = new Button("Confirmar");

		GridPane caja = new GridPane();

		Label lblGetDniA = new Label("Introduzca el DNI del alumno que desea buscar: ");
		TextField txtGetDniA = new TextField();

		// Agregamos un EventHandler al TextField para
		// manejar el evento de presionar Enter
		btnAcept.setOnAction(event -> {
			String dniSearch = txtGetDniA.getText();
			if (!dniSearch.isEmpty()) {
				AlumnoDO alumnoEncontrado = searchA.busqueda(con, dniSearch);
				if (alumnoEncontrado != null) {
					// Creamos los TextField para mostrar los datos
					// del alumno encontrado
					TextField txtfecNa = new TextField(alumnoEncontrado.getFechNa());
					txtfecNa.setEditable(false);
					TextField txtNombre = new TextField(alumnoEncontrado.getNombre());
					txtNombre.setEditable(false);
					TextField txtApell = new TextField(alumnoEncontrado.getApellido());
					txtApell.setEditable(false);
					TextField txtUser = new TextField(alumnoEncontrado.getUsuario());
					txtUser.setEditable(false);
					TextField txtTel = new TextField(Integer.toString(alumnoEncontrado.getTelefono()));
					txtTel.setEditable(false);
					TextField txtMail = new TextField(alumnoEncontrado.getEmail());
					txtMail.setEditable(false);
					TextField txtCur = new TextField(Integer.toString(alumnoEncontrado.getCurso_idCurso()));
					txtCur.setEditable(false);

					// Agregamos los TextField al GridPane
					GridPane.setHalignment(txtApell, HPos.CENTER);
					GridPane.setHalignment(txtUser, HPos.CENTER);
					GridPane.setHalignment(txtTel, HPos.CENTER);
					GridPane.setHalignment(txtMail, HPos.CENTER);
					GridPane.setHalignment(txtCur, HPos.CENTER);

					Insets margen = new Insets(10);

					GridPane.setMargin(txtApell, margen);
					GridPane.setMargin(txtUser, margen);
					GridPane.setMargin(txtTel, margen);
					GridPane.setMargin(txtMail, margen);
					GridPane.setMargin(txtCur, margen);

					GridPane.setHalignment(lblGetDniA, HPos.CENTER);
					GridPane.setHalignment(txtGetDniA, HPos.CENTER);

					// Puedes ajustar el valor del margen según sea
					// necesario

					caja.add(txtfecNa, 1, 1);
					caja.add(txtNombre, 1, 2);
					caja.add(txtApell, 1, 3);
					caja.add(txtUser, 1, 4);
					caja.add(txtTel, 1, 5);
					caja.add(txtMail, 1, 6);
					caja.add(txtCur, 1, 7);

					// Cerramos la ventana actual después de mostrar
					// los detalles del alumno
				} else {
					// Mostramos un mensaje si no se encuentra ningún
					// alumno
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Alumno no encontrado");
					alert.setHeaderText(null);
					alert.setContentText("No se encontró ningún alumno con el DNI proporcionado.");
					alert.showAndWait();
				}
			} else {
				// Mostramos un mensaje si el campo de DNI está
				// vacío
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Campo vacío");
				alert.setHeaderText(null);
				alert.setContentText("Por favor, ingrese un DNI antes de realizar la búsqueda.");
				alert.showAndWait();
			}
		});

		Label lblfecNa = new Label("Fecha de Nacimiento: ");
		Label lblNombre = new Label("Nombre: ");
		Label lblApell = new Label("Apellido: ");
		Label lblUser = new Label("Nombre de usuario: ");
		Label lblTel = new Label("Número telefónico: ");
		Label lblMail = new Label("Correo electrónico: ");
		Label lblCur = new Label("Curso: ");

		caja.add(lblfecNa, 0, 2);
		caja.add(lblNombre, 0, 3);
		caja.add(lblApell, 0, 4);
		caja.add(lblUser, 0, 5);
		caja.add(lblTel, 0, 6);
		caja.add(lblMail, 0, 7);
		caja.add(lblCur, 0, 8);
		caja.add(btnAcept, 4, 1);
		caja.add(menu, 0, 0);

		GridPane.setHalignment(lblfecNa, HPos.CENTER);
		GridPane.setHalignment(lblNombre, HPos.CENTER);
		GridPane.setHalignment(lblApell, HPos.CENTER);
		GridPane.setHalignment(lblUser, HPos.CENTER);
		GridPane.setHalignment(lblTel, HPos.CENTER);
		GridPane.setHalignment(lblMail, HPos.CENTER);
		GridPane.setHalignment(lblCur, HPos.CENTER);
		GridPane.setHalignment(btnAcept, HPos.CENTER);

		Insets margen = new Insets(10);
		GridPane.setMargin(lblfecNa, margen);
		GridPane.setMargin(lblNombre, margen);
		GridPane.setMargin(lblApell, margen);
		GridPane.setMargin(lblUser, margen);
		GridPane.setMargin(lblTel, margen);
		GridPane.setMargin(lblMail, margen);
		GridPane.setMargin(lblCur, margen);
		GridPane.setMargin(btnAcept, margen);
		GridPane.setMargin(lblGetDniA, margen);
		GridPane.setMargin(txtGetDniA, margen);

		// Agregamos el TextField al GridPane
		caja.add(txtGetDniA, 1, 1);
		caja.add(lblGetDniA, 0, 1);

		sceneBuscadorA = new Scene(caja, 600, 700);

		stage.setTitle("Buscador Alumnos");
		sceneBuscadorA.getRoot().getStyleClass().add("BuscadorA");
		// url css
		URL cssFile = getClass().getResource("/css/css.css");
		if (cssFile == null) {
			System.out.println("No se pudo encontrar el archivo CSS");
		} else {
			sceneBuscadorA.getStylesheets().add(cssFile.toExternalForm());
		}
		stage.setScene(sceneBuscadorA);
		stage.show();
	}
}
