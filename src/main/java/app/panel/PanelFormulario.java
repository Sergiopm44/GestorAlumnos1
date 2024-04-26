package app.panel;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PanelFormulario extends GridPane {

	// Variables miembro de la clase
	// seran accesibles del panel
	public TextField txtEmail;
	public TextField txtTema;
	public ChoiceBox chbCat;
	public TextArea txtObs;
	public Button btnAceptar;

	/**
	 * Constructor principal de la clase Dentro vamos a ir añadiendo los elementos
	 * que queramos
	 */
	public PanelFormulario() {
		Label lblEmail = new Label("Email");
		Label lblTema = new Label("Tema");
		Label lblCategoria = new Label("Categoría");
		Label lblObservaciones = new Label("Observaciones");
		btnAceptar = new Button("Aceptar");

		// Creamos los controles
		txtEmail = new TextField();
		txtTema = new TextField();
		chbCat = new ChoiceBox();
		txtObs = new TextArea();

		// Añadimos elementos al choicebox
		chbCat.getItems().add("Perfil");
		chbCat.getItems().add("Facturación");
		chbCat.getItems().add("Otros");

		// Posicionamos los elementos en mi panel grid, que soy yo
		this.add(lblEmail, 0, 0);
		this.add(txtEmail, 1, 0);

		this.add(lblTema, 0, 1);
		this.add(txtTema, 1, 1);

		this.add(lblCategoria, 0, 2);
		this.add(chbCat, 1, 2);

		this.add(lblObservaciones, 0, 3);
		this.add(txtObs, 1, 3, 3, 3);

		this.add(btnAceptar, 0, 4, 4, 1);

		// Añadimos margenes
		this.setMargin(lblEmail, new Insets(5, 10, 5, 10));
		this.setMargin(txtEmail, new Insets(5, 10, 5, 10));
		this.setMargin(lblTema, new Insets(5, 10, 5, 10));
		this.setMargin(txtTema, new Insets(5, 10, 5, 10));
		this.setMargin(lblCategoria, new Insets(5, 10, 5, 10));
		this.setMargin(chbCat, new Insets(5, 10, 5, 10));
		this.setMargin(lblObservaciones, new Insets(5, 10, 5, 10));
		this.setMargin(txtObs, new Insets(5, 10, 5, 10));
		this.setMargin(btnAceptar, new Insets(5, 10, 5, 10));

	}

}
