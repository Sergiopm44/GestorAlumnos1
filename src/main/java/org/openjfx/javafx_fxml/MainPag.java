package org.openjfx.javafx_fxml;

import java.sql.Connection;

import Gestor.pane.InicioAlumno;
import Gestor.pane.InicioProfesor;
import Gestor.pane.RegistroAlumno;
import Gestor.pane.RegistroProfesor;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPag extends GridPane {
	public Label welcome;
	public Label log;
	public Label profOrAl;
	public static boolean isIniOk;
	public static boolean isProf;
	public static boolean isAlum;
	private static Scene scene;
	private static Stage stage;
	private Connection con;

	/**
	 * Funcion que crea el menu principal de bienvenida, que alberga el inicio de
	 * sesion y el registro, ya sea de alumno o de profesor, eleccion que se hace en
	 * la misma pagina principal
	 * 
	 * @param con
	 * @param primaryStage
	 */
	public MainPag(Connection con, Stage primaryStage) {
		stage = primaryStage;
		this.con = con;

		// Declaracion variable
		isIniOk = false;
		isAlum = false;
		isProf = false;

		AppMenu menu = new AppMenu();

		// Objetos
		ChoiceBox<String> profOrNo = new ChoiceBox<>();
		VBox cajon = new VBox();

		// Boton de Inicio de sesion y Registro
		Button btnRegisA = new Button("Registrarse como alumno");
		Button btnLoginA = new Button("Iniciar sesión como alumno");
		Button btnRegisP = new Button("Registrarse como profesor");
		Button btnLoginP = new Button("Iniciar sesión como profesor");

		btnRegisA.setVisible(false);
		btnRegisP.setVisible(false);
		btnLoginA.setVisible(false);
		btnLoginP.setVisible(false);

		welcome = new Label("Bienvenido a eRegister");
		profOrAl = new Label("Seleccione si es un alumno o un profesor");
		log = new Label("¿Tiene una cuenta o desea registrarse?");

		// ChoiceBox, opciones y obtener el resultado
		// Añade las opciones "Alumno" y "Profesor" al
		// ChoiceBox
		profOrNo.getItems().addAll("Alumno", "Profesor");

		// Agrega un listener al ChoiceBox para detectar
		// cambios en la selección del
		// usuario
		profOrNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// Verifica si el nuevo valor seleccionado no es
			// nulo
			if (newValue != null) {
				// Verifica si el nuevo valor es "Alumno"
				if (newValue.equals("Alumno")) {
					// Muestra los botones relacionados con el
					// registro e inicio de sesión como
					// alumno y oculta los del profesor
					btnRegisA.setVisible(true);
					btnLoginA.setVisible(true);
					btnRegisP.setVisible(false);
					btnLoginP.setVisible(false);
				}
				// Verifica si el nuevo valor es "Profesor"
				else if (newValue.equals("Profesor")) {
					// Muestra los botones relacionados con el
					// registro e inicio de sesión como
					// profesor y oculta los del alumno
					btnRegisP.setVisible(true);
					btnLoginP.setVisible(true);
					btnRegisA.setVisible(false);
					btnLoginA.setVisible(false);
				}
			}
		});

		// Boton para registro de alumno
		btnRegisA.setOnAction(e -> {
			RegistroAlumno regisAl = new RegistroAlumno(con, new Stage());
			isAlum = true;
		});
		// Boton para inicio de sesión de alumno
		btnLoginA.setOnAction(e -> {
			InicioAlumno inicioA = new InicioAlumno(con);
			isIniOk = true;
			isAlum = true;

		});
		// Boton para registro de profesor
		btnRegisP.setOnAction(e -> {
			RegistroProfesor regisP = new RegistroProfesor(con, new Stage());
			isProf = true;
		});

		// Boton para inicio de sesión de profesor
		btnLoginP.setOnAction(e -> {
			InicioProfesor inicioP = new InicioProfesor(con);
			isIniOk = true;
			isProf = true;

		});

		// Se agregan los nodos a la cuadrícula
		this.add(welcome, 0, 0);
		this.add(profOrAl, 0, 1);
		this.add(log, 0, 2);
		this.add(profOrNo, 0, 3);
		this.add(btnLoginA, 0, 4);
		this.add(btnRegisA, 0, 5);
		this.add(btnLoginP, 0, 4);
		this.add(btnRegisP, 0, 5);
		this.add(menu, 0, 0);

		this.setHalignment(welcome, HPos.CENTER);
		this.setHalignment(profOrAl, HPos.CENTER);
		this.setHalignment(log, HPos.CENTER);
		this.setHalignment(profOrNo, HPos.CENTER);
		this.setHalignment(btnLoginA, HPos.CENTER);
		this.setHalignment(btnLoginP, HPos.CENTER);
		this.setHalignment(btnRegisA, HPos.CENTER);
		this.setHalignment(btnRegisP, HPos.CENTER);

		// Puedes ajustar el valor del margen según sea
		// necesario
		Insets margen = new Insets(10);
		GridPane.setMargin(welcome, margen);
		GridPane.setMargin(profOrAl, margen);
		GridPane.setMargin(log, margen);
		GridPane.setMargin(profOrNo, margen);
		GridPane.setMargin(btnLoginA, margen);
		GridPane.setMargin(btnRegisA, margen);
		GridPane.setMargin(btnLoginP, margen);
		GridPane.setMargin(btnRegisP, margen);

		scene = new Scene(cajon, 600, 700);

		stage.setTitle("Inicio Sesión");
		stage.setScene(scene);
		stage.show();
	}
}
