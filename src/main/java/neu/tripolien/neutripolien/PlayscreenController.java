package neu.tripolien.neutripolien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

    // AB UNTEN GEHTS UM LISTE DER FLAGGE UND ZUFÄLLIG ANZEIGE

    @FXML
    private ImageView flagImageView;

    private List<Flags> flags;
    private Random random = new Random();

    @FXML
    public void initialize() {
        System.out.println("Initialize wird aufgerufen!");

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
    }
}
