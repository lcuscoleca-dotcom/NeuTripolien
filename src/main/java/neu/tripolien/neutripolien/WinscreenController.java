package neu.tripolien.neutripolien;

import javafx.fxml.FXML;

public class WinscreenController {

    @FXML
    private void onMenuClicked() {
        SceneManager.switchScene("MainMenuScreen.fxml");
    }
}
