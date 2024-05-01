package org.openjfx.javafx_fxml;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BarraMenu extends Application {

	@Override
	public void start(Stage stage) {

		// Creamos el panel principal
		BorderPane pnlDistribucion = new BorderPane();

		// Zona Superior Menu
		Label lblMenu = new Label("Menu");

		// Barra de menus principales
		MenuBar barraMenu = new MenuBar();

		// Menus que van en la barra de menus
		Menu mArchivo = new Menu("Archivo");
		Menu mOpciones = new Menu("Opciones");
		Menu mAyuda = new Menu("Ayuda");
		Menu mHerr = new Menu("Herramientas");

		// Opciones de menu quee aparecen cuando
		// seleccionamos el menu que las contiene
		MenuItem ICargar = new MenuItem("Cargar");
		MenuItem IGuardar = new MenuItem("Guardar");
		MenuItem ISalir = new MenuItem("Salir");

		// Opciones
		MenuItem IPreferencias = new MenuItem("Preferencias");

		// Ayuda
		MenuItem IManual = new MenuItem("Manual Referencia");
		MenuItem IAcerca = new MenuItem("Acerca de");

		// Herramientas
		MenuItem ICopiar = new MenuItem("Copiar");
		MenuItem IPegar = new MenuItem("Pegar");

		// Asignamos los items a los menus
		mArchivo.getItems().addAll(ICargar, IGuardar, ISalir);
		mOpciones.getItems().addAll(IPreferencias, mHerr);
		mAyuda.getItems().addAll(IManual, IAcerca);
		mHerr.getItems().addAll(ICopiar, IPegar);

		// añadimos los menus a la barra
		barraMenu.getMenus().addAll(mArchivo, mOpciones, mAyuda);

		// Asignamos el menu a la parte superior
		pnlDistribucion.setTop(barraMenu);
		pnlDistribucion.setMargin(lblMenu, new Insets(10, 7, 10, 7));
		pnlDistribucion.setAlignment(lblMenu, Pos.CENTER);

		// Zona Lateral izquierda un conjunto de botones
		VBox panelLateral = new VBox();
		Button btnCargar = new Button("Cargar");
		Button btnGuardar = new Button("Guardar");
		pnlDistribucion.setMargin(lblMenu, new Insets(5, 7, 5, 7));
		pnlDistribucion.setMargin(lblMenu, new Insets(5, 7, 5, 7));

		// Añadimos los dos botones al panel vertical
		panelLateral.getChildren().addAll(btnCargar, btnGuardar);

		// En la zona izquieda del borderpane ponemos el
		// Vbox con los botones
		pnlDistribucion.setLeft(panelLateral);
		panelLateral.setMargin(btnCargar, new Insets(5, 7, 5, 7));
		panelLateral.setMargin(btnGuardar, new Insets(5, 7, 5, 7));

		VBox panelCentral = new VBox();

		TextField txtValorNota = new TextField("5");
		txtValorNota.setMaxWidth(300);

		// Cuando pulsamos en la opcion de menu salir
		// cerramos la app
		ISalir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}

		});

		// La scene es el contenido de la ventana cuando
		// se crea se define su tamaño por
		// defecto
		// Cuando creamos la escena le asignamos el
		// ontenido que es compatible con node
		var scene = new Scene(pnlDistribucion, 800, 600);

		stage.setScene(scene);
		stage.setTitle("Ejemplo Menu");
		stage.show();

	}

	public static void main(String[] args) {
		launch();

	}

}
