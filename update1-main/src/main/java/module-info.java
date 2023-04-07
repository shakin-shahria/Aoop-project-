module com.example.update1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.update1 to javafx.fxml;
    exports com.example.update1;
}