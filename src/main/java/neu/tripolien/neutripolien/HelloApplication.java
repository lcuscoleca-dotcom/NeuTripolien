package neu.tripolien.neutripolien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Erste Scene (WelcomeScreen)
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("WelcomeScreen.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 320, 240);

        // Zweite Scene (PlayScreen)
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("PlayScreen.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);

        // Stage initial auf Scene 1 setzen
        stage.setTitle("Neu Tripolien!");
        stage.setScene(scene2);
        stage.show();

    }
}
