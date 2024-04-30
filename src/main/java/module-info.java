module org.openjfx.javafx_fxml {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires itextpdf;
	requires java.compiler;
	requires aspose.pdf;

    opens org.openjfx.javafx_fxml to javafx.fxml;
    exports org.openjfx.javafx_fxml;
}
