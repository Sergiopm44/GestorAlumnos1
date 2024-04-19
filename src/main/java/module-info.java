module org.openjfx.javafx_fxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.javafx_fxml to javafx.fxml;
    exports org.openjfx.javafx_fxml;
}
