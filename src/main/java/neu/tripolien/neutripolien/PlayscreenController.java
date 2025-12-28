package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayscreenController {

    @FXML
    private Label Highscore_Points;

    @FXML
    private void answerClicked() {
        GameState.score += 100;
        Highscore_Points.setText(String.valueOf(GameState.score));

        if (GameState.score >= 500) {
            SceneManager.switchScene("WinScreen.fxml");
        }
    }
}
