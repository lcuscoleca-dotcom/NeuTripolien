module neu.tripolien.neutripolien {
    requires javafx.controls;
    requires javafx.fxml;


    opens neu.tripolien.neutripolien to javafx.fxml;
    exports neu.tripolien.neutripolien;
}