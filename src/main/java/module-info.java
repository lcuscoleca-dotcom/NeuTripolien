module neu.tripolien.neutripolien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens neu.tripolien.neutripolien to javafx.fxml;
    exports neu.tripolien.neutripolien;
}