package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PlayscreenController {


    @FXML
    private Label Highscore_Points;

    @FXML
    private void answerAClicked() {
        checkAnswer(buttonA.getText());
    }

    @FXML
    private void answerBClicked() {
        checkAnswer(buttonB.getText());
    }

    @FXML
    private void answerCClicked() {
        checkAnswer(buttonC.getText());
    }

    @FXML
    private void answerDClicked() {
        checkAnswer(buttonD.getText());
    }

    private void checkAnswer(String selectedCountry) {
        // Ist die Antwort richtig?
        if (selectedCountry.equals(currentCorrectFlag.getCountryName())) {
            // RICHTIG!
            GameState.score += 100;
            Highscore_Points.setText(String.valueOf(GameState.score));

            // Nächste Frage laden
            loadNewQuestion();

            // Wenn Score hoch genug → WinScreen
            if (GameState.score >= 500) {
                SceneManager.switchScene("WinScreen.fxml");
            }
        } else {
            // FALSCH!
            SceneManager.switchScene("Deathscreen.fxml");
        }
    }


    @FXML
    private ImageView flagImageView;

    private List<Flags> flags;
    private Random random = new Random();

    @FXML
    private Button buttonA;

    @FXML
    private Button buttonB;

    @FXML
    private Button buttonC;

    @FXML
    private Button buttonD;

    @FXML
    public void initialize() { // das initialisiert die Flaggen ins Pogramm

        flags = new ArrayList<>();
        flags.add(new Flags("Deutschland", "/neu/tripolien/flags/de.png"));
        flags.add(new Flags("Frankreich", "/neu/tripolien/flags/fr.png"));
        flags.add(new Flags("Italien", "/neu/tripolien/flags/it.png"));
        flags.add(new Flags("Spanien", "/neu/tripolien/flags/es.png"));
        flags.add(new Flags("Albanien", "/neu/tripolien/flags/al.png"));
        flags.add(new Flags("Andorra", "/neu/tripolien/flags/ad.png"));
        flags.add(new Flags("Belgien", "/neu/tripolien/flags/be.png"));
        flags.add(new Flags("Bosnien und Herzegowina", "/neu/tripolien/flags/ba.png"));
        flags.add(new Flags("Bulgarien", "/neu/tripolien/flags/bg.png"));
        flags.add(new Flags("Dänemark", "/neu/tripolien/flags/dk.png"));
        flags.add(new Flags("Estland", "/neu/tripolien/flags/ee.png"));
        flags.add(new Flags("Finnland", "/neu/tripolien/flags/fi.png"));
        flags.add(new Flags("Griechenland", "/neu/tripolien/flags/gr.png"));
        flags.add(new Flags("Irland", "/neu/tripolien/flags/ie.png"));
        flags.add(new Flags("Island", "/neu/tripolien/flags/is.png"));
        flags.add(new Flags("Kroatien", "/neu/tripolien/flags/hr.png"));
        flags.add(new Flags("Lettland", "/neu/tripolien/flags/lv.png"));
        flags.add(new Flags("Litauen", "/neu/tripolien/flags/lt.png"));
        flags.add(new Flags("Luxemburg", "/neu/tripolien/flags/lu.png"));
        flags.add(new Flags("Malta", "/neu/tripolien/flags/mt.png"));
        flags.add(new Flags("Moldau", "/neu/tripolien/flags/md.png"));
        flags.add(new Flags("Monaco", "/neu/tripolien/flags/mc.png"));
        flags.add(new Flags("Montenegro", "/neu/tripolien/flags/me.png"));
        flags.add(new Flags("Niederlande", "/neu/tripolien/flags/nl.png"));
        flags.add(new Flags("Nordmazedonien", "/neu/tripolien/flags/mk.png"));
        flags.add(new Flags("Norwegen", "/neu/tripolien/flags/no.png"));
        flags.add(new Flags("Österreich", "/neu/tripolien/flags/at.png"));
        flags.add(new Flags("Polen", "/neu/tripolien/flags/pl.png"));
        flags.add(new Flags("Portugal", "/neu/tripolien/flags/pt.png"));
        flags.add(new Flags("Rumänien", "/neu/tripolien/flags/ro.png"));
        flags.add(new Flags("Russland", "/neu/tripolien/flags/ru.png"));
        flags.add(new Flags("San Marino", "/neu/tripolien/flags/sm.png"));
        flags.add(new Flags("Schweden", "/neu/tripolien/flags/se.png"));
        flags.add(new Flags("Schweiz", "/neu/tripolien/flags/ch.png"));
        flags.add(new Flags("Serbien", "/neu/tripolien/flags/rs.png"));
        flags.add(new Flags("Slowakei", "/neu/tripolien/flags/sk.png"));
        flags.add(new Flags("Slowenien", "/neu/tripolien/flags/si.png"));
        flags.add(new Flags("Tschechien", "/neu/tripolien/flags/cz.png"));
        flags.add(new Flags("Ukraine", "/neu/tripolien/flags/ua.png"));
        flags.add(new Flags("Ungarn", "/neu/tripolien/flags/hu.png"));
        flags.add(new Flags("Vatikanstadt", "/neu/tripolien/flags/va.png"));
        flags.add(new Flags("Vereinigtes Königreich", "/neu/tripolien/flags/gb.png"));
        flags.add(new Flags("Weißrussland", "/neu/tripolien/flags/by.png"));
        flags.add(new Flags("Zypern", "/neu/tripolien/flags/cy.png"));


        loadNewQuestion();
    }

    private Flags currentCorrectFlag;

    private void loadNewQuestion() {
        // Wähle zufälliges Land
        currentCorrectFlag = flags.get(random.nextInt(flags.size()));

        // Zeige die Flagge an
        Image image = new Image(getClass().getResourceAsStream(currentCorrectFlag.getImagePath()));
        flagImageView.setImage(image);

        // Erstelle Liste mit 4 Antworten (1 richtig, 3 falsch)
        List<Flags> answerOptions = new ArrayList<>();
        answerOptions.add(currentCorrectFlag);  // Die richtige Antwort

        // Füge 3 zufällige FALSCHE Antworten hinzu
        while (answerOptions.size() < 4) {
            Flags randomFlag = flags.get(random.nextInt(flags.size()));

            // Ist diese Flagge schon in der Liste? Wenn nein, hinzufügen! Für keine doppelten Länder
            if (!answerOptions.contains(randomFlag)) {
                answerOptions.add(randomFlag);
            }
        }

        // MISCHE die Antworten (damit die richtige nicht immer an Position 0 ist!)
        Collections.shuffle(answerOptions);

        // Setze die Ländernamen auf die Buttons
        buttonA.setText(answerOptions.get(0).getCountryName()); // get: aus dem Array hol position 0
        buttonB.setText(answerOptions.get(1).getCountryName());
        buttonC.setText(answerOptions.get(2).getCountryName());
        buttonD.setText(answerOptions.get(3).getCountryName());
    }
}
