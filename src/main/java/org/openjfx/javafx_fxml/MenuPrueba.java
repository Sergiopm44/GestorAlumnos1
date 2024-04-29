package org.openjfx.javafx_fxml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuPrueba extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Pruebas de Programación");
		BorderPane borderPane = new BorderPane();

		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("Archivo");
		Menu viewMenu = new Menu("Ver");
		Menu helpMenu = new Menu("Ayuda");
		Menu toolsMenu = new Menu("Herramientas");

		MenuItem openMenuItem = new MenuItem("Abrir");
		openMenuItem.setOnAction(event -> {
			// Se crea un nuevo objeto FileChooser para
			// seleccionar el archivo a abrir
			FileChooser fileChooser = new FileChooser();

			// Se crea un nuevo filtro de extensiones que
			// permita seleccionar cualquier tipo de archivo
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Todos los archivos", "*.*");

			// Se agrega el filtro de extensiones creado al
			// FileChooser
			fileChooser.getExtensionFilters().add(extFilter);

			// Se muestra la ventana de seleccion de archivo y
			// se guarda la seleccion del usuario
			File file = fileChooser.showOpenDialog(primaryStage);

			// Si se seleccionó un archivo
			if (file != null) {
				try {
					// Se lee el contenido del archivo seleccionado
					String fileContent = new String(Files.readAllBytes(file.toPath()));

					// Se crea un nuevo TextArea con el contenido del
					// archivo seleccionado
					TextArea textArea = new TextArea(fileContent);

					// Se establece las propiedades del TextArea para
					// que muestre el contenido con salto de linea y
					// no permitir modificaciones
					textArea.setWrapText(true);
					textArea.setEditable(false);

					// Se agrega el TextArea al centro del borderPane
					borderPane.setCenter(textArea);
				} catch (IOException e) {
					// Si ocurre un error durante la lectura del
					// archivo se imprime el stacktrace
					e.printStackTrace();
				}
			}
		});

		MenuItem saveMenuItem = new MenuItem("Guardar");
		saveMenuItem.setOnAction(event -> {
			TextArea textArea = (TextArea) borderPane.getCenter(); // Agregamos la variable textArea
			// Se crea un nuevo objeto FileChooser para
			// seleccionar donde guardar el archivo
			FileChooser fileChooser = new FileChooser();
			// Se establece el titulo de la ventana de
			// seleccion de archivo
			fileChooser.setTitle("Guardar archivo");
			// Se establece el filtro de extensiones para que
			// permita seleccionar cualquier tipo de archivo
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Todos los archivos", "*.*");
			// Se agrega el filtro de extensiones a la lista
			// de filtros permitidos
			fileChooser.getExtensionFilters().add(extFilter);
			// Se muestra la ventana de seleccion de archivo y
			// se guarda la seleccion del usuario
			File file = fileChooser.showSaveDialog(primaryStage);
			// Si se seleccionó un archivo
			if (file != null) {
				try {
					// Se escribe el contenido del TextArea en el
					// archivo seleccionado
					Files.write(file.toPath(), textArea.getText().getBytes());
				} catch (IOException e) {
					// Si ocurre un error durante la escritura se
					// imprime el stacktrace
					e.printStackTrace();
				}
			}

		});

		MenuItem exitMenuItem = new MenuItem("Salir");
		exitMenuItem.setOnAction(event -> {
			// Cerramos el stage si le da a Salir
			primaryStage.close();
		});

		MenuItem aboutMenuItem = new MenuItem("Acerca de");
		aboutMenuItem.setOnAction(event -> {
			// Creamos un nuevo diálogo de información
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			// Establecemos el título del diálogo
			alert.setTitle("Acerca de...");
			// Establecemos el mensaje del diálogo
			alert.setHeaderText("Pruebas de Programación");
			alert.setContentText("Esta aplicación es un gestor de alumnos para el centro IES Mar de Cádiz.\n"
					+ "Autores: Sergio Pinto Morales, Miguel Gallardo Cota, Francisco Javier Rodríguez Ruiz\n"
					+ "Profesor: Victor Pablo Galván Flores" + "Estado de la aplicación: Beta\n" + "Versión: 1.0");
			// Mostramos el diálogo
			alert.showAndWait();
		});

		// Por hacer
		MenuItem manualMenuItem = new MenuItem("Manual");
		manualMenuItem.setOnAction(event -> {

		});

		fileMenu.getItems().addAll(openMenuItem, saveMenuItem, exitMenuItem);
		viewMenu.getItems().addAll(new MenuItem("Zoom In"), new MenuItem("Zoom Out"));
		helpMenu.getItems().addAll(aboutMenuItem, manualMenuItem);
		toolsMenu.getItems().addAll(new MenuItem("Botón 1"), new MenuItem("Botón 2"), new MenuItem("Botón 3"),
				new MenuItem("Botón 4"), new MenuItem("Botón 5"), new MenuItem("Botón 6"), new MenuItem("Botón 7"),
				new MenuItem("Botón 8"));

		menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu, toolsMenu);
		borderPane.setTop(menuBar);

		Scene scene = new Scene(borderPane, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
