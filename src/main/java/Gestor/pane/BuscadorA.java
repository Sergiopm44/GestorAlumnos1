package Gestor.pane;

import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BuscadorA extends GridPane {

	private static Scene scene;
	private static Stage stage;

	public BuscadorA(Connection con, Stage primaryStage) {
		this.stage = primaryStage;

		AlumnoDAO searchA = new AlumnoDAO();

		GridPane caja = new GridPane();

		Label lblGetDniA = new Label("Introduzca el DNI del alumno que desea buscar: ");
		TextField txtGetDniA = new TextField();

		// Agregamos un EventHandler al TextField para
		// manejar el evento de presionar Enter
		txtGetDniA.setOnAction(event -> {
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
					caja.getChildren().clear();
					caja.add(txtfecNa, 1, 1);
					caja.add(txtNombre, 1, 2);
					caja.add(txtApell, 1, 3);
					caja.add(txtUser, 1, 4);
					caja.add(txtTel, 1, 5);
					caja.add(txtMail, 1, 6);
					caja.add(txtCur, 1, 7);

					// Cerramos la ventana actual después de mostrar
					// los detalles del alumno
					stage.close();
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

		// Agregamos el TextField al GridPane
		caja.add(txtGetDniA, 1, 0);
		caja.add(lblGetDniA, 0, 0);

		Label lblfecNa = new Label("Fecha de Nacimiento: ");
		Label lblNombre = new Label("Nombre: ");
		Label lblApell = new Label("Apellido: ");
		Label lblUser = new Label("Nombre de usuario: ");
		Label lblTel = new Label("Número telefónico: ");
		Label lblMail = new Label("Correo electrónico: ");
		Label lblCur = new Label("Curso: ");

		caja.add(lblfecNa, 0, 1);
		caja.add(lblNombre, 0, 2);
		caja.add(lblApell, 0, 3);
		caja.add(lblUser, 0, 4);
		caja.add(lblTel, 0, 5);
		caja.add(lblMail, 0, 6);
		caja.add(lblCur, 0, 7);

		scene = new Scene(caja, 600, 700);

		stage.setTitle("Buscador Alumnos");
		stage.setScene(scene);
		stage.show();
	}
}