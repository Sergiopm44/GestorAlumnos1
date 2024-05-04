package Gestor.pane;

import java.sql.Connection;

import Gestor.model.AlumnoDAO;
import Gestor.model.AlumnoDO;
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

public class userProfile extends GridPane {

	private static Scene sceneProfile;
	private static Stage stage;

	public userProfile(Connection con, Stage stage) {
		this.stage = stage;

		// Declaracion de objetos

		Button btnGoBack = new Button("Volver al buscador");
		GridPane caja = new GridPane();

		if (InicioAlumno.newUser != null && InicioAlumno.newUser.isAlumno()) {
			AlumnoDAO searchA = new AlumnoDAO();
			AlumnoDO alumno = searchA.searchAUser(con, InicioAlumno.newUser.getUserNameA());

			if (alumno != null) {
				TextField txtfecNa = new TextField(alumno.getFechNa());
				txtfecNa.setEditable(false);
				TextField txtNombre = new TextField(alumno.getNombre());
				txtNombre.setEditable(false);
				TextField txtApell = new TextField(alumno.getApellido());
				txtApell.setEditable(false);
				TextField txtUser = new TextField(alumno.getUsuario());
				txtUser.setEditable(false);
				TextField txtTel = new TextField(Integer.toString(alumno.getTelefono()));
				txtTel.setEditable(false);
				TextField txtMail = new TextField(alumno.getEmail());
				txtMail.setEditable(false);
				TextField txtCur = new TextField(Integer.toString(alumno.getCurso_idCurso()));
				txtCur.setEditable(false);

				btnGoBack.setOnAction(e -> {
					stage.close();

				});

				// Agregamos los TextField al GridPane
				GridPane.setHalignment(txtApell, HPos.CENTER);
				GridPane.setHalignment(txtUser, HPos.CENTER);
				GridPane.setHalignment(txtTel, HPos.CENTER);
				GridPane.setHalignment(txtMail, HPos.CENTER);
				GridPane.setHalignment(txtCur, HPos.CENTER);
				GridPane.setHalignment(btnGoBack, HPos.CENTER);

				Insets margen = new Insets(10);

				GridPane.setMargin(txtApell, margen);
				GridPane.setMargin(txtUser, margen);
				GridPane.setMargin(txtTel, margen);
				GridPane.setMargin(txtMail, margen);
				GridPane.setMargin(txtCur, margen);
				GridPane.setMargin(btnGoBack, margen);

				// Puedes ajustar el valor del margen según sea
				// necesario

				caja.add(txtfecNa, 1, 1);
				caja.add(txtNombre, 1, 2);
				caja.add(txtApell, 1, 3);
				caja.add(txtUser, 1, 4);
				caja.add(txtTel, 1, 5);
				caja.add(txtMail, 1, 6);
				caja.add(txtCur, 1, 7);
				caja.add(btnGoBack, 1, 8);

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

				GridPane.setHalignment(lblfecNa, HPos.CENTER);
				GridPane.setHalignment(lblNombre, HPos.CENTER);
				GridPane.setHalignment(lblApell, HPos.CENTER);
				GridPane.setHalignment(lblUser, HPos.CENTER);
				GridPane.setHalignment(lblTel, HPos.CENTER);
				GridPane.setHalignment(lblMail, HPos.CENTER);
				GridPane.setHalignment(lblCur, HPos.CENTER);

				GridPane.setMargin(lblfecNa, margen);
				GridPane.setMargin(lblNombre, margen);
				GridPane.setMargin(lblApell, margen);
				GridPane.setMargin(lblUser, margen);
				GridPane.setMargin(lblTel, margen);
				GridPane.setMargin(lblMail, margen);
				GridPane.setMargin(lblCur, margen);

				sceneProfile = new Scene(caja, 600, 700);

				stage.setTitle("Perfil del Alumno");
				stage.setScene(sceneProfile);
				stage.show();
			} else {
				// Manejar el caso en que alumno sea null
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Alumno no encontrado");
				alert.setHeaderText(null);
				alert.setContentText("Hubo un error, porfavor inténtenlo de nuevo Alumno.");
				alert.showAndWait();
			}
		} else if (InicioProfesor.newUser != null && !InicioProfesor.newUser.isAlumno()) {
			ProfesorDAO searchP = new ProfesorDAO();
			ProfesorDO profesor = searchP.searchPUser(con, InicioProfesor.newUser.getUserNameP());

			if (profesor != null) {
				TextField txtfecNa = new TextField(profesor.getFechNa());
				txtfecNa.setEditable(false);
				TextField txtNombre = new TextField(profesor.getNombre());
				txtNombre.setEditable(false);
				TextField txtApell = new TextField(profesor.getApellido());
				txtApell.setEditable(false);
				TextField txtUser = new TextField(profesor.getUsuario());
				txtUser.setEditable(false);
				TextField txtTel = new TextField(Integer.toString(profesor.getTelefono()));
				txtTel.setEditable(false);
				TextField txtMail = new TextField(profesor.getEmail());
				txtMail.setEditable(false);
				TextField txtCur = new TextField(Integer.toString(profesor.getDepartamentos_idDepartamentos()));
				txtCur.setEditable(false);

				btnGoBack.setOnAction(e -> {
					stage.close();

				});

				// Agregamos los TextField al GridPane
				GridPane.setHalignment(txtApell, HPos.CENTER);
				GridPane.setHalignment(txtUser, HPos.CENTER);
				GridPane.setHalignment(txtTel, HPos.CENTER);
				GridPane.setHalignment(txtMail, HPos.CENTER);
				GridPane.setHalignment(txtCur, HPos.CENTER);
				GridPane.setHalignment(btnGoBack, HPos.CENTER);

				Insets margen = new Insets(10);

				GridPane.setMargin(txtApell, margen);
				GridPane.setMargin(txtUser, margen);
				GridPane.setMargin(txtTel, margen);
				GridPane.setMargin(txtMail, margen);
				GridPane.setMargin(txtCur, margen);

				// Puedes ajustar el valor del margen según sea
				// necesario

				caja.add(txtfecNa, 1, 1);
				caja.add(txtNombre, 1, 2);
				caja.add(txtApell, 1, 3);
				caja.add(txtUser, 1, 4);
				caja.add(txtTel, 1, 5);
				caja.add(txtMail, 1, 6);
				caja.add(txtCur, 1, 7);
				caja.add(btnGoBack, 1, 8);

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

				GridPane.setHalignment(lblfecNa, HPos.CENTER);
				GridPane.setHalignment(lblNombre, HPos.CENTER);
				GridPane.setHalignment(lblApell, HPos.CENTER);
				GridPane.setHalignment(lblUser, HPos.CENTER);
				GridPane.setHalignment(lblTel, HPos.CENTER);
				GridPane.setHalignment(lblMail, HPos.CENTER);
				GridPane.setHalignment(lblCur, HPos.CENTER);
				GridPane.setHalignment(btnGoBack, HPos.CENTER);

				GridPane.setMargin(lblfecNa, margen);
				GridPane.setMargin(lblNombre, margen);
				GridPane.setMargin(lblApell, margen);
				GridPane.setMargin(lblUser, margen);
				GridPane.setMargin(lblTel, margen);
				GridPane.setMargin(lblMail, margen);
				GridPane.setMargin(lblCur, margen);
				GridPane.setMargin(btnGoBack, margen);

				sceneProfile = new Scene(caja, 600, 700);

				stage.setTitle("Perfil del Profesor");
				stage.setScene(sceneProfile);
				stage.show();
			} else {
				// Manejar el caso en que profesor sea null
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Alumno no encontrado");
				alert.setHeaderText(null);
				alert.setContentText("Hubo un error, porfavor inténtenlo de nuevo Profesor.");
				alert.showAndWait();
			}
		}
	}
}
