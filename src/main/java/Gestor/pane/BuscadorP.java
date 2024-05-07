package Gestor.pane;

import java.net.URL;
import java.sql.Connection;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
import Gestor.model.ProfesorDAO;
import Gestor.model.ProfesorDO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BuscadorP extends GridPane {

	public static Scene sceneBuscadorP;
	public static Stage stage;

	public BuscadorP(Connection con, Stage primaryStage) {
		this.stage = primaryStage;

		AppMenu menu = new AppMenu(primaryStage);

		// Objeto del profesor y alumno
		ProfesorDAO searchP = new ProfesorDAO();
		AlumnoDAO searchA = new AlumnoDAO();

		Button btnAcept = new Button("Confirmar");

		// Boton para eliminar a un alumno
		Button btnDelAlu = new Button("Borrar Alumno");

		GridPane caja = new GridPane();

		Label lblGetDniP = new Label("Introduzca el dni del profesor que desea buscar: ");
		TextField txtGetDniP = new TextField();

		// Agregamos un EventHandler al TextField para
		// manejar el evento de presionar Enter
		btnAcept.setOnAction(event -> {

			String dniSearch = txtGetDniP.getText();
			if (!dniSearch.isEmpty()) { // Verificamos si el DNI no está vacío
				ProfesorDO profesorEncontrado = searchP.busqueda(con, dniSearch);
				AlumnoDO alumnoEncontrado = searchA.busqueda(con, dniSearch);

				if (profesorEncontrado != null) { // Verificamos si se encontró un alumno
					// Creamos los TextField para mostrar los datos
					// del alumno encontrado

					TextField txtfecNaP = new TextField(profesorEncontrado.getFechNa());
					txtfecNaP.setEditable(false);
					TextField txtNombreP = new TextField(profesorEncontrado.getNombre());
					txtNombreP.setEditable(false);
					TextField txtApellP = new TextField(profesorEncontrado.getApellido());
					txtApellP.setEditable(false);
					TextField txtUserP = new TextField(profesorEncontrado.getUsuario());
					txtUserP.setEditable(false);
					TextField txtTelP = new TextField(Integer.toString(profesorEncontrado.getTelefono()));
					txtTelP.setEditable(false);
					TextField txtMailP = new TextField(profesorEncontrado.getEmail());
					txtMailP.setEditable(false);
					TextField txtDep = new TextField(
							Integer.toString(profesorEncontrado.getDepartamentos_idDepartamentos()));
					txtDep.setEditable(false);

					// Agregamos los TextField al GridPane
					GridPane.setHalignment(txtApellP, HPos.CENTER);
					GridPane.setHalignment(txtUserP, HPos.CENTER);
					GridPane.setHalignment(txtTelP, HPos.CENTER);
					GridPane.setHalignment(txtMailP, HPos.CENTER);
					GridPane.setHalignment(txtDep, HPos.CENTER);

					Insets margen = new Insets(10);

					GridPane.setMargin(txtApellP, margen);
					GridPane.setMargin(txtUserP, margen);
					GridPane.setMargin(txtTelP, margen);
					GridPane.setMargin(txtMailP, margen);

					GridPane.setHalignment(lblGetDniP, HPos.CENTER);
					GridPane.setHalignment(txtGetDniP, HPos.CENTER);

					// Agregamos los TextField al GridPane solo si no
					// están ya agregados
					if (!caja.getChildren().contains(txtfecNaP)) {
						caja.add(txtfecNaP, 1, 2);
						caja.add(txtNombreP, 1, 3);
						caja.add(txtApellP, 1, 4);
						caja.add(txtUserP, 1, 5);
						caja.add(txtTelP, 1, 6);
						caja.add(txtMailP, 1, 7);
						caja.add(txtDep, 1, 8);
					}

					// Cerramos la ventana actual despues de mostrar
					// los detalles del almuno
				} else if (alumnoEncontrado != null) {
					// Creamos los TextField para mostrar los datos
					// del alumno encontrado
					int idAlumno = alumnoEncontrado.getIdAlumno();
					TextField txtfecNaA = new TextField(alumnoEncontrado.getFechNa());
					txtfecNaA.setEditable(false);
					TextField txtNombreA = new TextField(alumnoEncontrado.getNombre());
					txtNombreA.setEditable(false);
					TextField txtApellA = new TextField(alumnoEncontrado.getApellido());
					txtApellA.setEditable(false);
					TextField txtUserA = new TextField(alumnoEncontrado.getUsuario());
					txtUserA.setEditable(false);
					TextField txtTelA = new TextField(Integer.toString(alumnoEncontrado.getTelefono()));
					txtTelA.setEditable(false);
					TextField txtMailA = new TextField(alumnoEncontrado.getEmail());
					txtMailA.setEditable(false);
					TextField txtCur = new TextField(Integer.toString(alumnoEncontrado.getCurso_idCurso()));
					txtCur.setEditable(false);
					btnDelAlu.setVisible(true);

					btnDelAlu.setOnAction(e -> {
						Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
						delete.setTitle("Cuidado");
						delete.setHeaderText(null);
						delete.setContentText("¿Desea eliminar a este alumno definitivamente?");
						// Si cierra la alerta, elimina el alumno
						delete.setOnCloseRequest(events -> {
							searchA.eliminarAlumno(idAlumno, con);
							txtGetDniP.clear();
							txtfecNaA.setVisible(false);
							txtNombreA.setVisible(false);
							txtApellA.setVisible(false);
							txtUserA.setVisible(false);
							txtTelA.setVisible(false);
							txtMailA.setVisible(false);
							txtCur.setVisible(false);
							btnDelAlu.setVisible(false);

						});

						delete.showAndWait();

					});

					// Agregamos los TextField al GridPane
					GridPane.setHalignment(txtApellA, HPos.CENTER);
					GridPane.setHalignment(txtUserA, HPos.CENTER);
					GridPane.setHalignment(txtTelA, HPos.CENTER);
					GridPane.setHalignment(txtMailA, HPos.CENTER);
					GridPane.setHalignment(txtCur, HPos.CENTER);

					Insets margen = new Insets(10);

					GridPane.setMargin(txtApellA, margen);
					GridPane.setMargin(txtUserA, margen);
					GridPane.setMargin(txtTelA, margen);
					GridPane.setMargin(txtMailA, margen);
					GridPane.setMargin(txtCur, margen);

					// Puedes ajustar el valor del margen según sea
					// necesario

					// Agregamos los TextField al GridPane solo si no
					// están ya agregados
					if (!caja.getChildren().contains(txtfecNaA)) {
						caja.add(txtfecNaA, 1, 2);
						caja.add(txtNombreA, 1, 3);
						caja.add(txtApellA, 1, 4);
						caja.add(txtUserA, 1, 5);
						caja.add(txtTelA, 1, 6);
						caja.add(txtMailA, 1, 7);
						caja.add(txtCur, 1, 8);
						caja.add(btnDelAlu, 1, 9);
					}

				} else {

					// Mostramos un mensaje indicando que no se
					// encontró ningún alumno con el DNI proporcionado
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Usuario no encontrado");
					alert.setHeaderText(null);
					alert.setContentText("No se encontró usuario con el DNI proporcionado.");
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

		caja.add(lblfecNa, 0, 2);
		caja.add(lblNombre, 0, 3);
		caja.add(lblApell, 0, 4);
		caja.add(lblUser, 0, 5);
		caja.add(lblTel, 0, 6);
		caja.add(lblMail, 0, 7);
		caja.add(lblDep, 0, 8);
		caja.add(btnAcept, 4, 1);
		caja.add(menu, 0, 0);

		GridPane.setHalignment(lblfecNa, HPos.CENTER);
		GridPane.setHalignment(lblNombre, HPos.CENTER);
		GridPane.setHalignment(lblApell, HPos.CENTER);
		GridPane.setHalignment(lblUser, HPos.CENTER);
		GridPane.setHalignment(lblTel, HPos.CENTER);
		GridPane.setHalignment(lblMail, HPos.CENTER);
		GridPane.setHalignment(lblDep, HPos.CENTER);
		GridPane.setHalignment(btnAcept, HPos.CENTER);
		GridPane.setHalignment(menu, HPos.CENTER);

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
		GridPane.setMargin(menu, margen);

		// Agregamos el TextField al GridPane
		caja.add(txtGetDniP, 1, 1);
		caja.add(lblGetDniP, 0, 1);

		// Crear la HBox para contener la dateLabel y la
		// timeLabel
		HBox dateTimeBox = new HBox();
		dateTimeBox.setAlignment(Pos.TOP_RIGHT); // Alinear en la esquina superior derecha
		dateTimeBox.setPadding(new Insets(10)); // Añadir espacio alrededor de la fecha y la hora

		// Crear las etiquetas de fecha y hora
		Label timeLabel = new Label("Hora: ");

		// Agregar las etiquetas al contenedor HBox
		dateTimeBox.getChildren().addAll(timeLabel);

		// Agregar la HBox al borde superior derecho del
		// BorderPane
		caja.add(dateTimeBox, 0, 9);

		// Crear un Timeline para actualizar la hora cada
		// segundo
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime(timeLabel)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		sceneBuscadorP = new Scene(caja, 1230, 900);

		stage.setTitle("Buscador profesores");
		sceneBuscadorP.getRoot().getStyleClass().add("BuscadorP");
		URL cssFile = getClass().getResource("/css/css.css");
		if (cssFile == null) {
			System.out.println("No se pudo encontrar el archivo CSS");
		} else {
			sceneBuscadorP.getStylesheets().add(cssFile.toExternalForm());
		}

		stage.setScene(sceneBuscadorP);
		stage.setResizable(false);
		stage.show();
	}

	// Método para actualizar la hora
	private void updateTime(Label timeLabel) {
		// Obtener la fecha y hora actual
		DateTime currentDateTime = new DateTime();

		// Formatear la hora como una cadena
		DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");
		String formattedTime = timeFormatter.print(currentDateTime);

		// Actualizar la etiqueta de la hora
		timeLabel.setText("Hora: " + formattedTime);
	}
}