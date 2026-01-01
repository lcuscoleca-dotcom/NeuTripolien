package neu.tripolien.neutripolien;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        // Stage im SceneManager registrieren
        SceneManager.setStage(stage);

        // Erste Szene laden
        Scene scene = SceneManager.loadScene("WelcomeScreen.fxml");
        stage.setScene(scene);

        stage.setTitle("Neu Tripolien!");

        // 🔒 IMMER FULLSCREEN (einmalig)
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        stage.show();
    }
}
