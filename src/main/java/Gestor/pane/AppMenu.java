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
import Gestor.utils.userConfig.Config;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppMenu extends BorderPane {

	public static Config configuracion = userConfig.insertarConfig();
	private static Scene sceneMenu;
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

		// MenuBar para el menu de arriba
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
			alert.setHeaderText("eRegister");
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

		Menu config = new Menu("Configuración");
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
			userProfile profilePane = new userProfile(con, new Stage());

		});

		MenuItem zoomInMenuItem = new MenuItem("Zoom In");
		MenuItem zoomOutMenuItem = new MenuItem("Zoom Out");
		viewMenu.getItems().addAll(zoomInMenuItem, zoomOutMenuItem);

		// Escalado variable creada publica
		scaleFactor = 1.0;
		scale = new Scale(scaleFactor, scaleFactor);

		zoomInMenuItem.setOnAction(event -> {
			try {
				scaleFactor *= 1.1;
				applyScale();
			} catch (Exception e) {
				e.printStackTrace();
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

		// Metemos el nuevo botón en configuracion y
		// hacemos los ajustes necesarios para que cambie
		// el color del tema
		MenuItem lightBlueMode = new MenuItem("Cambiar tema");
		config.getItems().addAll(lightBlueMode);

		lightBlueMode.setOnAction(e -> {
			// Configuracion para cambiar de blanco a azul.
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

		menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu, toolsMenu);
		this.setTop(menuBar);

		// Declaramos los botones
		Button btnZoomIn = new Button();
		Button btnZoomOut = new Button();
		Button btnProfile = new Button();
		Button btnConfig = new Button();
		Button btnExit = new Button();
		Button btnOpen = new Button();
		Button btnAbout = new Button();
		Button btnManual = new Button();

		btnExit.setOnAction(e -> {
			// Cerramos el stage si le da a Salir
			stage.close();
		});
		Image IMGExit = new Image(AppMenu.class.getResourceAsStream("/img/icons8-exit-50.png"));
		// Crear un ImageView con la imagen
		ImageView IVExit = new ImageView(IMGExit);
		IVExit.setFitWidth(16); // Ancho deseado
		IVExit.setFitHeight(16); // Alto deseado
		btnExit.setGraphic(IVExit);

		btnOpen.setOnAction(e -> {
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
				} catch (IOException event) {
					// Si ocurre un error durante la lectura del
					// archivo se imprime el stacktrace
					event.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Error inesperado");
					// Establecemos el mensaje del diálogo
					alert.setHeaderText("Error al abrir el archivo");
					alert.setContentText(
							"Se ha producido un al abrir el archivo, recarge la pagina o intente mas tarde ");
				}
			}

		});
		// Con el AppMenu.class.getResourceAsStream poodemos poner rutas relativas en
		// las imagenes
		// Sin que nos de error.
		Image IMGOpen = new Image(AppMenu.class.getResourceAsStream("/img/icons8-open-view-50.png"));
		// Crear un ImageView con la imagen
		ImageView IVOpen = new ImageView(IMGOpen);
		IVOpen.setFitWidth(16); // Ancho deseado
		IVOpen.setFitHeight(16); // Alto deseado
		btnOpen.setGraphic(IVOpen);

		btnZoomIn.setOnAction(event -> {
			try {
				scaleFactor *= 1.1;
				applyScale();
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		Image iconZoomIn = new Image(AppMenu.class.getResourceAsStream("/img/icons8-zoom-in-50.png"));
		// Crear un ImageView con la imagen

		ImageView IVZoomIn = new ImageView(iconZoomIn);

		// Establecer el tamaño deseado para el ImageView
		IVZoomIn.setFitWidth(16); // Ancho deseado
		IVZoomIn.setFitHeight(16); // Alto deseado

		// Establecer el gráfico del botón como el
		// ImageView
		btnZoomIn.setGraphic(IVZoomIn);

		btnZoomOut.setOnAction(event -> {
			try {
				scaleFactor /= 1.1;
				applyScale();
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		Image iconZoomOut = new Image(AppMenu.class.getResourceAsStream("/img/icons8-zoom-out-50.png"));
		// Crear un ImageView con la imagen
		ImageView IVZoomOut = new ImageView(iconZoomOut);
		IVZoomOut.setFitWidth(16); // Ancho deseado
		IVZoomOut.setFitHeight(16); // Alto deseado

		btnZoomOut.setGraphic(IVZoomOut);

		btnAbout.setOnAction(event -> {
			// Creamos un nuevo diálogo de información
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			// Establecemos el título del diálogo
			alert.setTitle("Acerca de...");
			// Establecemos el mensaje del diálogo
			alert.setHeaderText("eRegister");
			alert.setContentText("Github: https://github.com/Sergiopm44/GestorAlumnos1.git\n"
					+ "Autores: Sergio Pinto Morales, Miguel Gallardo Cota, Francisco Javier Rodríguez Ruiz\n"
					+ "Profesor: Victor Pablo Galván Flores\n" + "Estado de la aplicación: Beta\n" + "Versión: 1.0\n"
					+ "©eRegister");
			// Mostramos el diálogo
			alert.showAndWait();

		});
		Image IMGiconAbout = new Image(AppMenu.class.getResourceAsStream("/img/icons8-about-50.png"));
		// Crear un ImageView con la imagen
		ImageView IVAbout = new ImageView(IMGiconAbout);
		IVAbout.setFitWidth(16); // Ancho deseado
		IVAbout.setFitHeight(16); // Alto deseado
		btnAbout.setGraphic(IVAbout);

		// Boton del manual
		btnManual.setOnAction(event -> {
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
		Image iconManual = new Image(AppMenu.class.getResourceAsStream("/img/icons8-manual-50.png"));
		// Crear un ImageView con la imagen
		ImageView IVManual = new ImageView(iconManual);
		IVManual.setFitWidth(16); // Ancho deseado
		IVManual.setFitHeight(16); // Alto deseado
		btnManual.setGraphic(IVManual);

		btnConfig.setOnAction(e -> {

			// Configuracion para cambiar de blanco a azul.
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
		Image iconConfig = new Image(AppMenu.class.getResourceAsStream("/img/icons8-config-50.png"));
		// Crear un ImageView con la imagen
		ImageView IVConfig = new ImageView(iconConfig);
		IVConfig.setFitWidth(16); // Ancho deseado
		IVConfig.setFitHeight(16); // Alto deseado

		// Establecer el gráfico del botón como el
		// ImageView
		btnConfig.setGraphic(IVConfig);

		btnProfile.setOnAction(event -> {
			userProfile profilePane = new userProfile(con, new Stage());
		});

		Image iconProfile = new Image(AppMenu.class.getResourceAsStream("/img/icons8-customer-50.png"));
		// Crear un ImageView con la imagen
		ImageView IVProfile = new ImageView(iconProfile);
		IVProfile.setFitWidth(16); // Ancho deseado
		IVProfile.setFitHeight(16); // Alto deseado
		// Establecer el gráfico del botón como el
		// ImageView
		btnProfile.setGraphic(IVProfile);

		HBox toolbar = new HBox(btnZoomIn, btnZoomOut, btnProfile, btnConfig, btnExit, btnOpen, btnAbout, btnManual);
		toolbar.setSpacing(35);
		toolbar.setPadding(new Insets(10));
		setLeft(toolbar);

		sceneMenu = new Scene(borderPane, 800, 600);
		primaryStage.setScene(sceneMenu);
		primaryStage.show();
	}

	private void applyScale() {
		// Tomamos las dimensiones del stage actual
		double sceneWidth = stage.getScene().getWidth();
		double sceneHeight = stage.getScene().getHeight();

		// Obtenemos las coordenadas del centro de la
		// escena
		double sceneCenterX = sceneWidth / 2;
		double sceneCenterY = sceneHeight / 2;

		// Actualizamos el factor de escala en el objeto
		// Scale
		scale.setX(scaleFactor);
		scale.setY(scaleFactor);

		// Calcular la nueva posición del centro de la
		// imagen después del escalado, para que no quede
		// descentrado
		double newCenterX = sceneCenterX * scaleFactor;
		double newCenterY = sceneCenterY * scaleFactor;

		// Calcular el cambio en la posición del centro de
		// la imagen
		double deltaX = sceneCenterX - newCenterX;
		double deltaY = sceneCenterY - newCenterY;

		// Aplicar el escalamiento a toda la aplicación
		// con el centro de la imagen como pivote
		stage.getScene().getRoot().getTransforms().clear();
		stage.getScene().getRoot().getTransforms().add(scale);
		stage.getScene().getRoot().setTranslateX(deltaX);
		stage.getScene().getRoot().setTranslateY(deltaY);
		stage.setResizable(false);
	}

}
