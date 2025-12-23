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

        // Dritte Scene (DeathScreen)
        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("DeathScreen.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 320, 240);

        // Vierte Scene (WinScreen)
        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("WinScreen.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 320, 240);

        // Fünfte Scene (MainMenuScreen)
        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("MainMenuScreen.fxml"));
        Scene scene5 = new Scene(fxmlLoader5.load(), 320, 240);

        // Stage initial auf Scene 1 setzen
        stage.setTitle("Neu Tripolien!");
        stage.setScene(scene5);
        stage.show();

    }
}
