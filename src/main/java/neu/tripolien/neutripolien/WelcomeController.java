package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class WelcomeController {

    @FXML
    private TextField nicknameField;

    // Sounds vorladen um Delays zu vermeiden
    @FXML
    public void initialize() {
        SoundManager.warmUp();

        // ENTER-Taste triggert denselben Ablauf wie der Start-Button
        nicknameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onStartClicked();
            }
        });
    }

    @FXML
    private void onStartClicked() {
        SoundManager.playClick();

        String name = nicknameField.getText().trim();
        GameState.username = name;

        SceneManager.switchScene("MainMenuScreen.fxml");
    }
}
