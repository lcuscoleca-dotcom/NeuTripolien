package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import java.awt.*;
import javafx.scene.control.TextField;

public class WelcomeController {

    @FXML
    private TextField nicknameField;

    //Sounds Vorladen um Delays zu vermeiden
    @FXML
    public void initialize() {
        SoundManager.warmUp();
    }

    @FXML
    private void onStartClicked() {
        SoundManager.playClick();
        String name = nicknameField.getText().trim();
        GameState.username = name;
        SceneManager.switchScene("MainMenuScreen.fxml");
    }
}