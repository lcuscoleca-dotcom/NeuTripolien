package neu.tripolien.neutripolien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.*;

public class PlayscreenController {

    @FXML private Label Highscore_Points;
    @FXML private Label livesLabel;
    @FXML private Label questionLabel;
    @FXML private Label timeLabel;
    @FXML private Label difficultyLabel;
    @FXML private ImageView flagImageView;

    @FXML private Button buttonA;
    @FXML private Button buttonB;
    @FXML private Button buttonC;
    @FXML private Button buttonD;

    private List<Flags> flags;
    private Flags correctFlag;
    private final Random random = new Random();

    private int timeLeft;
    private Timeline timer;

    @FXML
    public void initialize() {
        GameState.reset();
        loadFlags();
        loadNewQuestion();
        startTimer();
        updateUI();
    }

    // 🔘 EIN Handler für alle Buttons
    @FXML
    private void handleAnswer(javafx.event.ActionEvent event) {
        Button clicked = (Button) event.getSource();

        if (clicked.getText().equals(correctFlag.getCountryName())) {
            GameState.score += getPointsForDifficulty();
            GameState.correctAnswers++;
        } else {
            GameState.lives--;
        }

        GameState.currentQuestion++;

        stopTimer();

        if (GameState.lives <= 0) {
            SceneManager.switchScene("Deathscreen.fxml");
            return;
        }

        if (GameState.currentQuestion >= GameState.questionCount) {
            SceneManager.switchScene("WinScreen.fxml");
            return;
        }

        loadNewQuestion();
        startTimer();
        updateUI();
    }

    // ⏱ TIMER
    private void startTimer() {
        timeLeft = getTimeForDifficulty();
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timeLabel.setText(timeLeft + "s");

            if (timeLeft <= 0) {
                stopTimer();
                GameState.lives--;
                handleTimeout();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void handleTimeout() {
        GameState.currentQuestion++;

        if (GameState.lives <= 0) {
            SceneManager.switchScene("Deathscreen.fxml");
        } else {
            loadNewQuestion();
            startTimer();
            updateUI();
        }
    }

    private void stopTimer() {
        if (timer != null) timer.stop();
    }

    // 🧠 QUIZ LOGIK
    private void loadNewQuestion() {
        correctFlag = flags.get(random.nextInt(flags.size()));

        flagImageView.setImage(
                new Image(getClass().getResourceAsStream(correctFlag.getImagePath()))
        );

        List<Flags> answers = new ArrayList<>();
        answers.add(correctFlag);

        while (answers.size() < 4) {
            Flags f = flags.get(random.nextInt(flags.size()));
            if (!answers.contains(f)) answers.add(f);
        }

        Collections.shuffle(answers);

        buttonA.setText(answers.get(0).getCountryName());
        buttonB.setText(answers.get(1).getCountryName());
        buttonC.setText(answers.get(2).getCountryName());
        buttonD.setText(answers.get(3).getCountryName());
    }

    private void updateUI() {
        Highscore_Points.setText(String.valueOf(GameState.score));
        difficultyLabel.setText(GameState.difficulty);
        questionLabel.setText(
                (GameState.currentQuestion + 1) + " / " + GameState.questionCount
        );
        timeLabel.setText(timeLeft + "s");
        updateLives();
    }


    private int getPointsForDifficulty() {
        return switch (GameState.difficulty) {
            case "Easy" -> 50;
            case "Hard" -> 150;
            default -> 100;
        };
    }

    private int getTimeForDifficulty() {
        return switch (GameState.difficulty) {
            case "Easy" -> GameState.timeLimit + 10;
            case "Hard" -> GameState.timeLimit - 5;
            default -> GameState.timeLimit;
        };
    }

    private void loadFlags() {
        flags = List.of(
                new Flags("Deutschland", "/neu/tripolien/flags/de.png"),
                new Flags("Frankreich", "/neu/tripolien/flags/fr.png"),
                new Flags("Italien", "/neu/tripolien/flags/it.png"),
                new Flags("Spanien", "/neu/tripolien/flags/es.png"),
                new Flags("Österreich", "/neu/tripolien/flags/at.png"),
                new Flags("Schweiz", "/neu/tripolien/flags/ch.png"),
                new Flags("Polen", "/neu/tripolien/flags/pl.png"),
                new Flags("Portugal", "/neu/tripolien/flags/pt.png")
        );
    }

    private void updateLives() {
        livesLabel.setText("❤️".repeat(GameState.lives));
    }

}
