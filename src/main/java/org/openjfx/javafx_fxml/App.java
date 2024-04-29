package org.openjfx.javafx_fxml;

import java.io.IOException;

import app.panel.PanelFormulario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	public Label lblSearch;
	public Label lblRegis;
	public Label lblDel;
	public Label lblMod;

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {

		VBox panelVertical = new VBox();

		Button btnSearch = new Button("Búsqueda");
		lblSearch = new Label("Pulse para buscar un alumno");
		Button btnRegis = new Button("Ventana para Registarse");
		lblRegis = new Label("Pulse para poder Registrar a un alumno ");
		Button btnDel = new Button("Eliminar");
		lblDel = new Label("Pulse para poder Eliminar a un Alumno");
		Button btnMod = new Button("Actualizar");
		lblMod = new Label("Pulse el siguiente botón si desea modificar/ u un alumno");

		btnSearch.setOnAction(event -> {
			mostrarAlerta(Alert.AlertType.INFORMATION);
		});
		btnRegis.setOnAction(event -> {
			mostrarAlerta(Alert.AlertType.CONFIRMATION);
		});

		btnDel.setOnAction(event -> {
			abrirVentanaContacto();
		});

		btnMod.setOnAction(event -> {
			abrirVentanaModal(stage);
		});

		// Añadimos los elementos al panel vertical
		panelVertical.getChildren().addAll(btnSearch, btnRegis, btnDel, btnMod, lblSearch, lblRegis, lblDel, lblMod);

		panelVertical.setMargin(btnSearch, new Insets(5, 10, 5, 10));
		panelVertical.setMargin(lblSearch, new Insets(5, 10, 5, 10));
		panelVertical.setMargin(btnRegis, new Insets(5, 10, 5, 10));
		panelVertical.setMargin(lblSearch, new Insets(5, 10, 5, 10));
		panelVertical.setMargin(btnDel, new Insets(5, 10, 5, 10));
		panelVertical.setMargin(lblSearch, new Insets(5, 10, 5, 10));
		panelVertical.setMargin(btnMod, new Insets(5, 10, 5, 10));
		panelVertical.setMargin(lblSearch, new Insets(5, 10, 5, 10));

		// Añadimos a la escena el panel vertical
		Scene scene = new Scene(panelVertical, 600, 600);

		stage.setTitle("Gestor de Alumnos");
		stage.setScene(scene);
		stage.show();

	}

	// Funcion que muestra una ventana de alerta
	public void mostrarAlerta(AlertType tipoAlerta) {

		if (tipoAlerta == Alert.AlertType.INFORMATION) {
			Alert infoAlert = new Alert(tipoAlerta);
			infoAlert.setTitle("Búsqueda de alumno");
			infoAlert.setHeaderText("");
			infoAlert.setContentText(
					"Si  no lees esta informacion te vasa a perder lo último en crypto, pasame tu clave privada, please");
			infoAlert.showAndWait();
		}
		if (tipoAlerta == Alert.AlertType.CONFIRMATION) {
			Alert confirmAlert = new Alert(tipoAlerta);
			confirmAlert.setTitle("Mayor de edad");
			confirmAlert.setHeaderText("Confirma los datos");
			confirmAlert.setContentText("¿Eres mayor de edad?");

			// Al mostrar la ventana, esta nos devuelve el tipo de boton que se ha pulsado
			ButtonType btnPulsado = confirmAlert.showAndWait().orElse(ButtonType.NO);

			if (btnPulsado == ButtonType.CANCEL) {
				this.lblSearch.setText("El usuario es menor de edad");

			} else
				this.lblSearch.setText("El usuario es mayor de edad");
		}
	}

	public void abrirVentanaContacto() {
		Stage ventanaEmergente = new Stage();

		PanelFormulario panelForm = new PanelFormulario();

		Scene scene = new Scene(panelForm, 300, 300);

		panelForm.btnAceptar.setOnAction(e -> {
			this.lblSearch.setText(panelForm.txtObs.getText());
		});

		ventanaEmergente.setScene(scene);
		ventanaEmergente.setTitle("Contacto");
		ventanaEmergente.show();
	}

	public static void abrirVentanaModal(Stage stage) {
		Stage ventanaEmergente = new Stage();

		StackPane stackPane = new StackPane();

		stackPane.getChildren().add(new Label("Introduzca el nombre de usuario:"));

		Scene scene = new Scene(stackPane, 300, 300);

		// Para bloquear a la ventana padre hay que definir quien es el padre con
		// initOwner y poner la modalidad
		ventanaEmergente.initOwner(stage);
		ventanaEmergente.initModality(Modality.WINDOW_MODAL);

		ventanaEmergente.setTitle("Actualizar Usuario");
		ventanaEmergente.setScene(scene);
		ventanaEmergente.show();

		scene = new Scene(stackPane, 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}