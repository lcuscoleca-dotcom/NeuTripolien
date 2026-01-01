package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinscreenController {

    @FXML private Label scoreLabel;
    @FXML private Label answerLabel;
    @FXML private Label difficultyLabel;

    @FXML
    public void initialize() {
        scoreLabel.setText(String.valueOf(GameState.score));
        answerLabel.setText(
                GameState.correctAnswers + " / " + GameState.questionCount
        );
        difficultyLabel.setText(GameState.difficulty);
    }

    @FXML
    private void onMenuClicked() {
        GameState.reset();
        SceneManager.switchScene("MainMenuScreen.fxml");
    }
}
