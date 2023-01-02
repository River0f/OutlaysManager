module com.example.outlaysmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.outlaysmanager to javafx.fxml;
    exports com.example.outlaysmanager;
}