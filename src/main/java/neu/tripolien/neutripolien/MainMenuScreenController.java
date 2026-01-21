package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.application.Platform;

public class MainMenuScreenController {

    @FXML
    private ChoiceBox<String> difficultyChoiceBox;

    @FXML
    private ChoiceBox<Integer> questionCountChoiceBox;

    @FXML
    private ChoiceBox<Integer> timeLimitChoiceBox;

    @FXML
    private CheckBox soundCheckBox;

    //@FXML
    //private CheckBox hintsCheckBox;

    // Wird AUTOMATISCH beim Laden des FXML aufgerufen
    @FXML
    private void initialize() {

        // Difficulty
        difficultyChoiceBox.getItems().addAll(
                "Easy", "Normal", "Hard"
        );
        difficultyChoiceBox.setValue("Normal");

        // Questions
        questionCountChoiceBox.getItems().addAll(
                5, 10, 15, 20
        );
        questionCountChoiceBox.setValue(10);

        // Time limit (seconds)
        timeLimitChoiceBox.getItems().addAll(
                15, 30, 45, 60
        );
        timeLimitChoiceBox.setValue(30);

        soundCheckBox.setSelected(true);
        GameState.soundEnabled = true;

        // ENTER = PLAY
        Platform.runLater(() -> {
            difficultyChoiceBox.getScene().setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case ENTER -> onPlayClicked();
                }
            });
        });
    }





    @FXML
    private void onPlayClicked() {

        //play Sound wenn nicht deaktiviert
        GameState.soundEnabled = soundCheckBox.isSelected();
        SoundManager.playClick();

        // SETTINGS SPEICHERN
        GameState.difficulty = difficultyChoiceBox.getValue();
        GameState.questionCount = questionCountChoiceBox.getValue();
        GameState.timeLimit = timeLimitChoiceBox.getValue();
        GameState.soundEnabled = soundCheckBox.isSelected();
        //GameState.hintsEnabled = hintsCheckBox.isSelected();

        GameState.reset();
        SceneManager.switchScene("PlayScreen.fxml");
    }

    @FXML
    private void onExitClicked() {
        SoundManager.playClick();
        Platform.exit();
        System.exit(0);
    }
}

