module org.openjfx.javafx_fxml {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires itextpdf;
	requires java.compiler;
	requires aspose.pdf;
	requires java.desktop;
	requires org.joda.time;

	opens org.openjfx.javafx_fxml to javafx.fxml;

	exports org.openjfx.javafx_fxml;
}
