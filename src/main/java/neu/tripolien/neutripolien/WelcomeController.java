package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import java.awt.*;
import javafx.scene.control.TextField;

public class WelcomeController {

    @FXML
    private TextField nicknameField;

    @FXML
    private void onStartClicked() {
        String name = nicknameField.getText().trim();
        GameState.username = name;
        SceneManager.switchScene("MainMenuScreen.fxml");
    }
}