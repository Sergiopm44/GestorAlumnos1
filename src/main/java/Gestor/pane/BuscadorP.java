package Gestor.pane;

import java.net.URL;
import java.sql.Connection;

import Gestor.model.ProfesorDAO;
import Gestor.model.ProfesorDO;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BuscadorP extends GridPane {

	public static Scene sceneBuscadorP;
	public static Stage stage;

	public BuscadorP(Connection con, Stage primaryStage) {
		this.stage = primaryStage;

		ProfesorDAO searchP = new ProfesorDAO();

		Button btnAcept = new Button("Confirmar");

		GridPane caja = new GridPane();

		Label lblGetDniP = new Label("Introduzca el dni del profesor que desea buscar: ");
		TextField txtGetDniP = new TextField();

		// Agregamos un EventHandler al TextField para
		// manejar el evento de presionar Enter
		btnAcept.setOnAction(event -> {

			String dniSearch = txtGetDniP.getText();
			if (!dniSearch.isEmpty()) { // Verificamos si el DNI no está vacío
				ProfesorDO profesorEncontrado = searchP.busqueda(con, dniSearch);
				if (profesorEncontrado != null) { // Verificamos si se encontró un alumno
					// Creamos los TextField para mostrar los datos
					// del alumno encontrado
					TextField txtfecNa = new TextField(profesorEncontrado.getFechNa());
					txtfecNa.setEditable(false);
					TextField txtNombre = new TextField(profesorEncontrado.getNombre());
					txtNombre.setEditable(false);
					TextField txtApell = new TextField(profesorEncontrado.getApellido());
					txtApell.setEditable(false);
					TextField txtUser = new TextField(profesorEncontrado.getUsuario());
					txtUser.setEditable(false);
					TextField txtTel = new TextField(Integer.toString(profesorEncontrado.getTelefono()));
					txtTel.setEditable(false);
					TextField txtMail = new TextField(profesorEncontrado.getEmail());
					txtMail.setEditable(false);
					TextField txtDep = new TextField(
							Integer.toString(profesorEncontrado.getDepartamentos_idDepartamentos()));
					txtDep.setEditable(false);

					// Agregamos los TextField al GridPane
					GridPane.setHalignment(txtApell, HPos.CENTER);
					GridPane.setHalignment(txtUser, HPos.CENTER);
					GridPane.setHalignment(txtTel, HPos.CENTER);
					GridPane.setHalignment(txtMail, HPos.CENTER);
					GridPane.setHalignment(txtDep, HPos.CENTER);

					Insets margen = new Insets(10);

					GridPane.setMargin(txtApell, margen);
					GridPane.setMargin(txtUser, margen);
					GridPane.setMargin(txtTel, margen);
					GridPane.setMargin(txtMail, margen);

					GridPane.setHalignment(lblGetDniP, HPos.CENTER);
					GridPane.setHalignment(txtGetDniP, HPos.CENTER);

					// Puede ajustar el valor del margen según sea
					// necesario

					caja.add(txtfecNa, 1, 1);
					caja.add(txtNombre, 1, 2);
					caja.add(txtApell, 1, 3);
					caja.add(txtUser, 1, 4);
					caja.add(txtTel, 1, 5);
					caja.add(txtMail, 1, 6);
					caja.add(txtDep, 1, 7);

					// Cerramos la ventana actual despues de mostrar
					// los detalles del almuno
				} else {

					// Mostramos un mensaje indicando que no se
					// encontró ningún alumno con el DNI proporcionado
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Profesor no encontrado");
					alert.setHeaderText(null);
					alert.setContentText("No se encontró ningún profesor con el DNI proporcionado.");
					alert.showAndWait();
				}
			} else {
				// Mostramos un mensaje indicando que el campo de
				// DNI está vacío
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
		Label lblDep = new Label("Departamento: ");

		caja.add(lblfecNa, 0, 1);
		caja.add(lblNombre, 0, 2);
		caja.add(lblApell, 0, 3);
		caja.add(lblUser, 0, 4);
		caja.add(lblTel, 0, 5);
		caja.add(lblMail, 0, 6);
		caja.add(lblDep, 0, 7);
		caja.add(btnAcept, 4, 0);

		GridPane.setHalignment(lblfecNa, HPos.CENTER);
		GridPane.setHalignment(lblNombre, HPos.CENTER);
		GridPane.setHalignment(lblApell, HPos.CENTER);
		GridPane.setHalignment(lblUser, HPos.CENTER);
		GridPane.setHalignment(lblTel, HPos.CENTER);
		GridPane.setHalignment(lblMail, HPos.CENTER);
		GridPane.setHalignment(lblDep, HPos.CENTER);
		GridPane.setHalignment(btnAcept, HPos.CENTER);

		Insets margen = new Insets(10);
		GridPane.setMargin(lblfecNa, margen);
		GridPane.setMargin(lblNombre, margen);
		GridPane.setMargin(lblApell, margen);
		GridPane.setMargin(lblUser, margen);
		GridPane.setMargin(lblTel, margen);
		GridPane.setMargin(lblMail, margen);
		GridPane.setMargin(lblDep, margen);
		GridPane.setMargin(btnAcept, margen);
		GridPane.setMargin(lblGetDniP, margen);
		GridPane.setMargin(txtGetDniP, margen);

		// Agregamos el TextField al GridPane
		caja.add(txtGetDniP, 1, 0);
		caja.add(lblGetDniP, 0, 0);

		sceneBuscadorP = new Scene(caja, 600, 700);

		stage.setTitle("Buscador profesores");
		sceneBuscadorP.getRoot().getStyleClass().add("BuscadorP");
		URL cssFile = getClass().getResource("/css/css.css");
		if (cssFile == null) {
			System.out.println("No se pudo encontrar el archivo CSS");
		} else {
			sceneBuscadorP.getStylesheets().add(cssFile.toExternalForm());
		}

		stage.setScene(sceneBuscadorP);
		stage.show();
	}
}