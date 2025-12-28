package neu.tripolien.neutripolien;

import javafx.fxml.FXML;

public class WelcomeController {

    @FXML
    private void onStartClicked() {
        SceneManager.switchScene("MainMenuScreen.fxml");
    }
}
