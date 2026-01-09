    package neu.tripolien.neutripolien;

    import javafx.fxml.FXML;
    import javafx.scene.control.Label;

    public class WinscreenController {

        @FXML private Label scoreLabel;
        @FXML private Label answerLabel;
        @FXML private Label difficultyLabel;
        @FXML private Label WinScreenText;

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
            WinScreenText.setText(name + " won this round 🎉");
        }

        @FXML
        private void onMenuClicked() {
            GameState.reset();
            SceneManager.switchScene("MainMenuScreen.fxml");
        }
    }
