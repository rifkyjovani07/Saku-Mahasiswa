module com.mycompany.sakumahasiswa {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.sakumahasiswa to javafx.fxml;
    exports com.mycompany.sakumahasiswa;
}
