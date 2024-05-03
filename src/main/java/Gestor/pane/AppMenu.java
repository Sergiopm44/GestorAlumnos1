package Gestor.pane;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.sql.Connection;

import org.openjfx.javafx_fxml.App;

import Gestor.utils.ConexionBD;
import Gestor.utils.userConfig;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppMenu extends BorderPane {

	private static double scaleFactor;
	private static Stage stage;
	private static Scale scale;
	private static Connection con;

	/**
	 * Funcion que crea el menu en la aplicacion java y que tiene todos los eventos
	 * funcionales de los menus
	 * 
	 * @param primaryStage
	 */
	public AppMenu(Stage primaryStage) {
		this.stage = primaryStage;
		BorderPane borderPane = new BorderPane();

		con = ConexionBD.conectarBD();

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
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Error inesperado");
					// Establecemos el mensaje del diálogo
					alert.setHeaderText("Error al abrir el archivo");
					alert.setContentText(
							"Se ha producido un al abrir el archivo, recarge la pagina o intente mas tarde ");
				}
			}
		});

		MenuItem exitMenuItem = new MenuItem("Salir");
		exitMenuItem.setOnAction(event -> {
			// Cerramos el stage si le da a Salir
			stage.close();
		});

		MenuItem aboutMenuItem = new MenuItem("Acerca de");
		aboutMenuItem.setOnAction(event -> {
			// Creamos un nuevo diálogo de información
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			// Establecemos el título del diálogo
			alert.setTitle("Acerca de...");
			// Establecemos el mensaje del diálogo
			alert.setHeaderText("Pruebas de Programación");
			alert.setContentText("Github: https://github.com/Sergiopm44/GestorAlumnos1.git\n"
					+ "Autores: Sergio Pinto Morales, Miguel Gallardo Cota, Francisco Javier Rodríguez Ruiz\n"
					+ "Profesor: Victor Pablo Galván Flores\n" + "Estado de la aplicación: Beta\n" + "Versión: 1.0\n"
					+ "©eRegister");
			// Mostramos el diálogo
			alert.showAndWait();
		});

		// Crear el MenuItem "Manual"
		MenuItem manualMenuItem = new MenuItem("Manual");
		manualMenuItem.setOnAction(event -> {
			try {
				// Crea un objeto File para el archivo HTML
				File manualAbrir = new File("doc/index.html");

				// Verifica si el archivo existe
				if (!manualAbrir.exists()) {
					throw new Exception("El archivo no existe");
				}

				// Obtiene la URI del archivo
				URI link = manualAbrir.toURI();

				// Abre el archivo en el navegador web
				// predeterminado
				Desktop.getDesktop().browse(link);
			} catch (Exception e) {
				// Si ocurre un error, muestra una alerta
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Error Inesperado");
				alert.setHeaderText(null);
				alert.setContentText(
						"Se ha producido un error al iniciar el manual, por favor reinicie e intente de nuevo.");
				alert.showAndWait();
			}
		});

		MenuItem config = new MenuItem("Configuración");
		MenuItem profile = new MenuItem("Perfil");
		fileMenu.getItems().addAll(openMenuItem, exitMenuItem);
		helpMenu.getItems().addAll(aboutMenuItem, manualMenuItem);
		toolsMenu.getItems().addAll(config, profile);

		config.setOnAction(event -> {
			userConfig userConfig = new userConfig();
		});

		profile.setOnAction(e -> {

			// Crear una instancia de userProfile y mostrarla
			// en una nueva ventana
			userProfile profilePane = new userProfile(con, stage);
			Stage profileStage = new Stage();
			profileStage.setScene(new Scene(profilePane, 600, 700));
			profileStage.setTitle("Perfil de Usuario");
			profileStage.show();
		});

		MenuItem zoomInMenuItem = new MenuItem("Zoom In");
		MenuItem zoomOutMenuItem = new MenuItem("Zoom Out");
		viewMenu.getItems().addAll(zoomInMenuItem, zoomOutMenuItem);

		// Escalado
		scaleFactor = 1.0;
		scale = new Scale(scaleFactor, scaleFactor);

		zoomInMenuItem.setOnAction(event -> {
			try {
				scaleFactor *= 1.1;
				applyScale();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Configuracion para cambiar de negro a blanco.
			if (App.configuracion.getTheme() == 0) {
				App.configuracion.setTheme(1);
				App.scene.getStylesheets().remove(getClass().getResource("/css/css.css").toExternalForm());
				App.scene.getStylesheets().add(getClass().getResource("/css/darkCss.css").toExternalForm());
			} else if (App.configuracion.getTheme() == 1) {
				App.configuracion.setTheme(0);
				App.scene.getStylesheets().remove(getClass().getResource("/css/darkCss.css").toExternalForm());
				App.scene.getStylesheets().add(getClass().getResource("/css/css.css").toExternalForm());
			}
		});

		zoomOutMenuItem.setOnAction(event -> {
			try {
				scaleFactor /= 1.1;
				applyScale();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu, toolsMenu);
		this.setTop(menuBar);

		Scene scene = new Scene(borderPane, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void applyScale() {
		scale.setX(scaleFactor);
		scale.setY(scaleFactor);
		this.getTransforms().clear();
		this.getTransforms().add(scale);
	}
}
