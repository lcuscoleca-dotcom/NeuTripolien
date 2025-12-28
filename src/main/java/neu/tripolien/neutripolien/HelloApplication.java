package neu.tripolien.neutripolien;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        SceneManager.switchScene("WelcomeScreen.fxml");
        stage.setTitle("Neu Tripolien!");
        stage.show();
    }
}

