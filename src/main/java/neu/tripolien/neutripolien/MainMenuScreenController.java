package neu.tripolien.neutripolien;

import javafx.fxml.FXML;

public class MainMenuScreenController {

    @FXML
    private void onPlayClicked() {
        GameState.reset();
        SceneManager.switchScene("PlayScreen.fxml");
    }

    @FXML
    private void onExitClicked() {
        System.exit(0);
    }
}

