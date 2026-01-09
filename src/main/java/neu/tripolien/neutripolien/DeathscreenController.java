package neu.tripolien.neutripolien;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeathscreenController {

    @FXML
    private Label scoreLabel;
    @FXML
    private Label answerLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label resultLabel;

    @FXML
    public void initialize() {
        scoreLabel.setText(String.valueOf(GameState.score));
        answerLabel.setText(
                GameState.correctAnswers + " / " + GameState.questionCount
        );
        difficultyLabel.setText(GameState.difficulty);

        //Call username from GameState
        String name = GameState.username;
        if (name == null || name.isEmpty()) {
            name = "You";
        }
        resultLabel.setText(name + " failed this round 😢");
    }

    @FXML
    private void onRetryClicked() {
        GameState.reset();
        SceneManager.switchScene("PlayScreen.fxml");
    }

    @FXML
    private void onMenuClicked() {
        GameState.reset();
        SceneManager.switchScene("MainMenuScreen.fxml");
    }

    @FXML
    private void onExitClicked() {
        Platform.exit();
        System.exit(0);
    }

}
