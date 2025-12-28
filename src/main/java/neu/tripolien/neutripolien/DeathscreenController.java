package neu.tripolien.neutripolien;

import javafx.fxml.FXML;

public class DeathscreenController {

    @FXML
    private void onRetryClicked() {
        GameState.reset();
        SceneManager.switchScene("PlayScreen.fxml");
    }

    @FXML
    private void onMenuClicked() {
        SceneManager.switchScene("MainMenuScreen.fxml");
    }
}
