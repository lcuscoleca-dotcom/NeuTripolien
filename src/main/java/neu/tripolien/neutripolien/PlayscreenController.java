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
    private List<Flags> usedFlags;
    private int timeLeft;
    private Timeline timer;

    @FXML
    public void initialize() {
        GameState.reset();
        loadFlags();
        usedFlags = new ArrayList<>();
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
            SoundManager.playCorrect();
        } else {
            GameState.lives--;
            SoundManager.playWrong();
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
        List<Flags> availableFlags = new ArrayList<>(flags);
        availableFlags.removeAll(usedFlags);
        if (availableFlags.isEmpty()) {
            usedFlags.clear();
            availableFlags = new ArrayList<>(flags);
        }

        correctFlag = availableFlags.get(random.nextInt(availableFlags.size()));
        usedFlags.add(correctFlag);

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
        questionLabel.setText((GameState.currentQuestion + 1) + " / " + GameState.questionCount);
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
        List<Flags> allFlags = new ArrayList<>();

        // ========== EASY (20 sehr bekannte Länder) ==========
        allFlags.add(new Flags("Deutschland", "/neu/tripolien/flags/de.png", "Easy"));
        allFlags.add(new Flags("Frankreich", "/neu/tripolien/flags/fr.png", "Easy"));
        allFlags.add(new Flags("Italien", "/neu/tripolien/flags/it.png", "Easy"));
        allFlags.add(new Flags("Spanien", "/neu/tripolien/flags/es.png", "Easy"));
        allFlags.add(new Flags("Vereinigtes Königreich", "/neu/tripolien/flags/gb.png", "Easy"));
        allFlags.add(new Flags("USA", "/neu/tripolien/flags/us.png", "Easy"));
        allFlags.add(new Flags("Kanada", "/neu/tripolien/flags/ca.png", "Easy"));
        allFlags.add(new Flags("Brasilien", "/neu/tripolien/flags/br.png", "Easy"));
        allFlags.add(new Flags("Argentinien", "/neu/tripolien/flags/ar.png", "Easy"));
        allFlags.add(new Flags("Mexiko", "/neu/tripolien/flags/mx.png", "Easy"));
        allFlags.add(new Flags("Japan", "/neu/tripolien/flags/jp.png", "Easy"));
        allFlags.add(new Flags("China", "/neu/tripolien/flags/cn.png", "Easy"));
        allFlags.add(new Flags("Indien", "/neu/tripolien/flags/in.png", "Easy"));
        allFlags.add(new Flags("Australien", "/neu/tripolien/flags/au.png", "Easy"));
        allFlags.add(new Flags("Russland", "/neu/tripolien/flags/ru.png", "Easy"));
        allFlags.add(new Flags("Südkorea", "/neu/tripolien/flags/kr.png", "Easy"));
        allFlags.add(new Flags("Türkei", "/neu/tripolien/flags/tr.png", "Easy"));
        allFlags.add(new Flags("Südafrika", "/neu/tripolien/flags/za.png", "Easy"));
        allFlags.add(new Flags("Ägypten", "/neu/tripolien/flags/eg.png", "Easy"));
        allFlags.add(new Flags("Griechenland", "/neu/tripolien/flags/gr.png", "Easy"));;
        // ========== NORMAL (Europa + größere bekannte Länder) ==========
        // Europa
        allFlags.add(new Flags("Österreich", "/neu/tripolien/flags/at.png", "Normal"));
        allFlags.add(new Flags("Schweiz", "/neu/tripolien/flags/ch.png", "Normal"));
        allFlags.add(new Flags("Niederlande", "/neu/tripolien/flags/nl.png", "Normal"));
        allFlags.add(new Flags("Belgien", "/neu/tripolien/flags/be.png", "Normal"));
        allFlags.add(new Flags("Polen", "/neu/tripolien/flags/pl.png", "Normal"));
        allFlags.add(new Flags("Portugal", "/neu/tripolien/flags/pt.png", "Normal"));
        allFlags.add(new Flags("Schweden", "/neu/tripolien/flags/se.png", "Normal"));
        allFlags.add(new Flags("Norwegen", "/neu/tripolien/flags/no.png", "Normal"));
        allFlags.add(new Flags("Dänemark", "/neu/tripolien/flags/dk.png", "Normal"));
        allFlags.add(new Flags("Finnland", "/neu/tripolien/flags/fi.png", "Normal"));
        allFlags.add(new Flags("Irland", "/neu/tripolien/flags/ie.png", "Normal"));
        allFlags.add(new Flags("Island", "/neu/tripolien/flags/is.png", "Normal"));
        allFlags.add(new Flags("Tschechien", "/neu/tripolien/flags/cz.png", "Normal"));
        allFlags.add(new Flags("Ungarn", "/neu/tripolien/flags/hu.png", "Normal"));
        allFlags.add(new Flags("Rumänien", "/neu/tripolien/flags/ro.png", "Normal"));
        allFlags.add(new Flags("Bulgarien", "/neu/tripolien/flags/bg.png", "Normal"));
        allFlags.add(new Flags("Kroatien", "/neu/tripolien/flags/hr.png", "Normal"));
        allFlags.add(new Flags("Serbien", "/neu/tripolien/flags/rs.png", "Normal"));
        allFlags.add(new Flags("Ukraine", "/neu/tripolien/flags/ua.png", "Normal"));
        allFlags.add(new Flags("Slowakei", "/neu/tripolien/flags/sk.png", "Normal"));
        allFlags.add(new Flags("Slowenien", "/neu/tripolien/flags/si.png", "Normal"));

        // Weitere bekannte Länder
        allFlags.add(new Flags("Chile", "/neu/tripolien/flags/cl.png", "Normal"));
        allFlags.add(new Flags("Peru", "/neu/tripolien/flags/pe.png", "Normal"));
        allFlags.add(new Flags("Kolumbien", "/neu/tripolien/flags/co.png", "Normal"));
        allFlags.add(new Flags("Venezuela", "/neu/tripolien/flags/ve.png", "Normal"));
        allFlags.add(new Flags("Thailand", "/neu/tripolien/flags/th.png", "Normal"));
        allFlags.add(new Flags("Vietnam", "/neu/tripolien/flags/vn.png", "Normal"));
        allFlags.add(new Flags("Indonesien", "/neu/tripolien/flags/id.png", "Normal"));
        allFlags.add(new Flags("Malaysia", "/neu/tripolien/flags/my.png", "Normal"));
        allFlags.add(new Flags("Philippinen", "/neu/tripolien/flags/ph.png", "Normal"));
        allFlags.add(new Flags("Saudi-Arabien", "/neu/tripolien/flags/sa.png", "Normal"));
        allFlags.add(new Flags("Israel", "/neu/tripolien/flags/il.png", "Normal"));
        allFlags.add(new Flags("Vereinigte Arabische Emirate", "/neu/tripolien/flags/ae.png", "Normal"));
        allFlags.add(new Flags("Nigeria", "/neu/tripolien/flags/ng.png", "Normal"));
        allFlags.add(new Flags("Kenia", "/neu/tripolien/flags/ke.png", "Normal"));
        allFlags.add(new Flags("Neuseeland", "/neu/tripolien/flags/nz.png", "Normal"));

        // ========== HARD (Alle restlichen Länder) ==========
        // Restliches Europa
        allFlags.add(new Flags("Albanien", "/neu/tripolien/flags/al.png", "Hard"));
        allFlags.add(new Flags("Andorra", "/neu/tripolien/flags/ad.png", "Hard"));
        allFlags.add(new Flags("BiH", "/neu/tripolien/flags/ba.png", "Hard"));
        allFlags.add(new Flags("Estland", "/neu/tripolien/flags/ee.png", "Hard"));
        allFlags.add(new Flags("Lettland", "/neu/tripolien/flags/lv.png", "Hard"));
        allFlags.add(new Flags("Litauen", "/neu/tripolien/flags/lt.png", "Hard"));
        allFlags.add(new Flags("Luxemburg", "/neu/tripolien/flags/lu.png", "Hard"));
        allFlags.add(new Flags("Malta", "/neu/tripolien/flags/mt.png", "Hard"));
        allFlags.add(new Flags("Moldau", "/neu/tripolien/flags/md.png", "Hard"));
        allFlags.add(new Flags("Monaco", "/neu/tripolien/flags/mc.png", "Hard"));
        allFlags.add(new Flags("Montenegro", "/neu/tripolien/flags/me.png", "Hard"));
        allFlags.add(new Flags("Nordmazedonien", "/neu/tripolien/flags/mk.png", "Hard"));
        allFlags.add(new Flags("San Marino", "/neu/tripolien/flags/sm.png", "Hard"));
        allFlags.add(new Flags("Vatikanstadt", "/neu/tripolien/flags/va.png", "Hard"));
        allFlags.add(new Flags("Weißrussland", "/neu/tripolien/flags/by.png", "Hard"));
        allFlags.add(new Flags("Zypern", "/neu/tripolien/flags/cy.png", "Hard"));

        // Afrika
        allFlags.add(new Flags("Algerien", "/neu/tripolien/flags/dz.png", "Hard"));
        allFlags.add(new Flags("Angola", "/neu/tripolien/flags/ao.png", "Hard"));
        allFlags.add(new Flags("Äquatorialguinea", "/neu/tripolien/flags/gq.png", "Hard"));
        allFlags.add(new Flags("Äthiopien", "/neu/tripolien/flags/et.png", "Hard"));
        allFlags.add(new Flags("Benin", "/neu/tripolien/flags/bj.png", "Hard"));
        allFlags.add(new Flags("Botswana", "/neu/tripolien/flags/bw.png", "Hard"));
        allFlags.add(new Flags("Burkina Faso", "/neu/tripolien/flags/bf.png", "Hard"));
        allFlags.add(new Flags("Burundi", "/neu/tripolien/flags/bi.png", "Hard"));
        allFlags.add(new Flags("Dschibuti", "/neu/tripolien/flags/dj.png", "Hard"));
        allFlags.add(new Flags("Elfenbeinküste", "/neu/tripolien/flags/ci.png", "Hard"));
        allFlags.add(new Flags("Eritrea", "/neu/tripolien/flags/er.png", "Hard"));
        allFlags.add(new Flags("Eswatini", "/neu/tripolien/flags/sz.png", "Hard"));
        allFlags.add(new Flags("Gabun", "/neu/tripolien/flags/ga.png", "Hard"));
        allFlags.add(new Flags("Gambia", "/neu/tripolien/flags/gm.png", "Hard"));
        allFlags.add(new Flags("Ghana", "/neu/tripolien/flags/gh.png", "Hard"));
        allFlags.add(new Flags("Guinea", "/neu/tripolien/flags/gn.png", "Hard"));
        allFlags.add(new Flags("Guinea-Bissau", "/neu/tripolien/flags/gw.png", "Hard"));
        allFlags.add(new Flags("Kamerun", "/neu/tripolien/flags/cm.png", "Hard"));
        allFlags.add(new Flags("Kap Verde", "/neu/tripolien/flags/cv.png", "Hard"));
        allFlags.add(new Flags("Komoren", "/neu/tripolien/flags/km.png", "Hard"));
        allFlags.add(new Flags("Kongo", "/neu/tripolien/flags/cg.png", "Hard"));
        allFlags.add(new Flags("DR Kongo", "/neu/tripolien/flags/cd.png", "Hard"));
        allFlags.add(new Flags("Lesotho", "/neu/tripolien/flags/ls.png", "Hard"));
        allFlags.add(new Flags("Liberia", "/neu/tripolien/flags/lr.png", "Hard"));
        allFlags.add(new Flags("Libyen", "/neu/tripolien/flags/ly.png", "Hard"));
        allFlags.add(new Flags("Madagaskar", "/neu/tripolien/flags/mg.png", "Hard"));
        allFlags.add(new Flags("Malawi", "/neu/tripolien/flags/mw.png", "Hard"));
        allFlags.add(new Flags("Mali", "/neu/tripolien/flags/ml.png", "Hard"));
        allFlags.add(new Flags("Marokko", "/neu/tripolien/flags/ma.png", "Hard"));
        allFlags.add(new Flags("Mauretanien", "/neu/tripolien/flags/mr.png", "Hard"));
        allFlags.add(new Flags("Mauritius", "/neu/tripolien/flags/mu.png", "Hard"));
        allFlags.add(new Flags("Mosambik", "/neu/tripolien/flags/mz.png", "Hard"));
        allFlags.add(new Flags("Namibia", "/neu/tripolien/flags/na.png", "Hard"));
        allFlags.add(new Flags("Niger", "/neu/tripolien/flags/ne.png", "Hard"));
        allFlags.add(new Flags("Ruanda", "/neu/tripolien/flags/rw.png", "Hard"));
        allFlags.add(new Flags("Sambia", "/neu/tripolien/flags/zm.png", "Hard"));
        allFlags.add(new Flags("São Tomé P.", "/neu/tripolien/flags/st.png", "Hard"));
        allFlags.add(new Flags("Senegal", "/neu/tripolien/flags/sn.png", "Hard"));
        allFlags.add(new Flags("Seychellen", "/neu/tripolien/flags/sc.png", "Hard"));
        allFlags.add(new Flags("Sierra Leone", "/neu/tripolien/flags/sl.png", "Hard"));
        allFlags.add(new Flags("Simbabwe", "/neu/tripolien/flags/zw.png", "Hard"));
        allFlags.add(new Flags("Somalia", "/neu/tripolien/flags/so.png", "Hard"));
        allFlags.add(new Flags("Sudan", "/neu/tripolien/flags/sd.png", "Hard"));
        allFlags.add(new Flags("Südsudan", "/neu/tripolien/flags/ss.png", "Hard"));
        allFlags.add(new Flags("Tansania", "/neu/tripolien/flags/tz.png", "Hard"));
        allFlags.add(new Flags("Togo", "/neu/tripolien/flags/tg.png", "Hard"));
        allFlags.add(new Flags("Tschad", "/neu/tripolien/flags/td.png", "Hard"));
        allFlags.add(new Flags("Tunesien", "/neu/tripolien/flags/tn.png", "Hard"));
        allFlags.add(new Flags("Uganda", "/neu/tripolien/flags/ug.png", "Hard"));
        allFlags.add(new Flags("Zentralafr. R.", "/neu/tripolien/flags/cf.png", "Hard"));

        // Asien
        allFlags.add(new Flags("Afghanistan", "/neu/tripolien/flags/af.png", "Hard"));
        allFlags.add(new Flags("Armenien", "/neu/tripolien/flags/am.png", "Hard"));
        allFlags.add(new Flags("Aserbaidschan", "/neu/tripolien/flags/az.png", "Hard"));
        allFlags.add(new Flags("Bahrain", "/neu/tripolien/flags/bh.png", "Hard"));
        allFlags.add(new Flags("Bangladesch", "/neu/tripolien/flags/bd.png", "Hard"));
        allFlags.add(new Flags("Bhutan", "/neu/tripolien/flags/bt.png", "Hard"));
        allFlags.add(new Flags("Brunei", "/neu/tripolien/flags/bn.png", "Hard"));
        allFlags.add(new Flags("Georgien", "/neu/tripolien/flags/ge.png", "Hard"));
        allFlags.add(new Flags("Irak", "/neu/tripolien/flags/iq.png", "Hard"));
        allFlags.add(new Flags("Iran", "/neu/tripolien/flags/ir.png", "Hard"));
        allFlags.add(new Flags("Jemen", "/neu/tripolien/flags/ye.png", "Hard"));
        allFlags.add(new Flags("Jordanien", "/neu/tripolien/flags/jo.png", "Hard"));
        allFlags.add(new Flags("Kambodscha", "/neu/tripolien/flags/kh.png", "Hard"));
        allFlags.add(new Flags("Kasachstan", "/neu/tripolien/flags/kz.png", "Hard"));
        allFlags.add(new Flags("Katar", "/neu/tripolien/flags/qa.png", "Hard"));
        allFlags.add(new Flags("Kirgisistan", "/neu/tripolien/flags/kg.png", "Hard"));
        allFlags.add(new Flags("Kuwait", "/neu/tripolien/flags/kw.png", "Hard"));
        allFlags.add(new Flags("Laos", "/neu/tripolien/flags/la.png", "Hard"));
        allFlags.add(new Flags("Libanon", "/neu/tripolien/flags/lb.png", "Hard"));
        allFlags.add(new Flags("Malediven", "/neu/tripolien/flags/mv.png", "Hard"));
        allFlags.add(new Flags("Mongolei", "/neu/tripolien/flags/mn.png", "Hard"));
        allFlags.add(new Flags("Myanmar", "/neu/tripolien/flags/mm.png", "Hard"));
        allFlags.add(new Flags("Nepal", "/neu/tripolien/flags/np.png", "Hard"));
        allFlags.add(new Flags("Nordkorea", "/neu/tripolien/flags/kp.png", "Hard"));
        allFlags.add(new Flags("Oman", "/neu/tripolien/flags/om.png", "Hard"));
        allFlags.add(new Flags("Osttimor", "/neu/tripolien/flags/tl.png", "Hard"));
        allFlags.add(new Flags("Pakistan", "/neu/tripolien/flags/pk.png", "Hard"));
        allFlags.add(new Flags("Palästina", "/neu/tripolien/flags/ps.png", "Hard"));
        allFlags.add(new Flags("Singapur", "/neu/tripolien/flags/sg.png", "Hard"));
        allFlags.add(new Flags("Sri Lanka", "/neu/tripolien/flags/lk.png", "Hard"));
        allFlags.add(new Flags("Syrien", "/neu/tripolien/flags/sy.png", "Hard"));
        allFlags.add(new Flags("Tadschikistan", "/neu/tripolien/flags/tj.png", "Hard"));
        allFlags.add(new Flags("Turkmenistan", "/neu/tripolien/flags/tm.png", "Hard"));
        allFlags.add(new Flags("Usbekistan", "/neu/tripolien/flags/uz.png", "Hard"));

        // Nord- und Mittelamerika
        allFlags.add(new Flags("Antigua & B.", "/neu/tripolien/flags/ag.png", "Hard"));
        allFlags.add(new Flags("Bahamas", "/neu/tripolien/flags/bs.png", "Hard"));
        allFlags.add(new Flags("Barbados", "/neu/tripolien/flags/bb.png", "Hard"));
        allFlags.add(new Flags("Belize", "/neu/tripolien/flags/bz.png", "Hard"));
        allFlags.add(new Flags("Costa Rica", "/neu/tripolien/flags/cr.png", "Hard"));
        allFlags.add(new Flags("Dominica", "/neu/tripolien/flags/dm.png", "Hard"));
        allFlags.add(new Flags("Dom. R.", "/neu/tripolien/flags/do.png", "Hard"));
        allFlags.add(new Flags("El Salvador", "/neu/tripolien/flags/sv.png", "Hard"));
        allFlags.add(new Flags("Grenada", "/neu/tripolien/flags/gd.png", "Hard"));
        allFlags.add(new Flags("Guatemala", "/neu/tripolien/flags/gt.png", "Hard"));
        allFlags.add(new Flags("Haiti", "/neu/tripolien/flags/ht.png", "Hard"));
        allFlags.add(new Flags("Honduras", "/neu/tripolien/flags/hn.png", "Hard"));
        allFlags.add(new Flags("Jamaika", "/neu/tripolien/flags/jm.png", "Hard"));
        allFlags.add(new Flags("Kuba", "/neu/tripolien/flags/cu.png", "Hard"));
        allFlags.add(new Flags("Nicaragua", "/neu/tripolien/flags/ni.png", "Hard"));
        allFlags.add(new Flags("Panama", "/neu/tripolien/flags/pa.png", "Hard"));
        allFlags.add(new Flags("St. Kitts & N.", "/neu/tripolien/flags/kn.png", "Hard"));
        allFlags.add(new Flags("St. Lucia", "/neu/tripolien/flags/lc.png", "Hard"));
        allFlags.add(new Flags("SVG", "/neu/tripolien/flags/vc.png", "Hard"));
        allFlags.add(new Flags("Trinidad & To.", "/neu/tripolien/flags/tt.png", "Hard"));

        // Südamerika
        allFlags.add(new Flags("Bolivien", "/neu/tripolien/flags/bo.png", "Hard"));
        allFlags.add(new Flags("Ecuador", "/neu/tripolien/flags/ec.png", "Hard"));
        allFlags.add(new Flags("Guyana", "/neu/tripolien/flags/gy.png", "Hard"));
        allFlags.add(new Flags("Paraguay", "/neu/tripolien/flags/py.png", "Hard"));
        allFlags.add(new Flags("Suriname", "/neu/tripolien/flags/sr.png", "Hard"));
        allFlags.add(new Flags("Uruguay", "/neu/tripolien/flags/uy.png", "Hard"));

        // Ozeanien
        allFlags.add(new Flags("Fidschi", "/neu/tripolien/flags/fj.png", "Hard"));
        allFlags.add(new Flags("Kiribati", "/neu/tripolien/flags/ki.png", "Hard"));
        allFlags.add(new Flags("Marshallinseln", "/neu/tripolien/flags/mh.png", "Hard"));
        allFlags.add(new Flags("Mikronesien", "/neu/tripolien/flags/fm.png", "Hard"));
        allFlags.add(new Flags("Nauru", "/neu/tripolien/flags/nr.png", "Hard"));
        allFlags.add(new Flags("Palau", "/neu/tripolien/flags/pw.png", "Hard"));
        allFlags.add(new Flags("Papua-Neuguinea", "/neu/tripolien/flags/pg.png", "Hard"));
        allFlags.add(new Flags("Salomonen", "/neu/tripolien/flags/sb.png", "Hard"));
        allFlags.add(new Flags("Samoa", "/neu/tripolien/flags/ws.png", "Hard"));
        allFlags.add(new Flags("Tonga", "/neu/tripolien/flags/to.png", "Hard"));
        allFlags.add(new Flags("Tuvalu", "/neu/tripolien/flags/tv.png", "Hard"));
        allFlags.add(new Flags("Vanuatu", "/neu/tripolien/flags/vu.png", "Hard"));

        //
        flags = allFlags.stream()
                .filter(f -> {
                    if (GameState.difficulty.equals("Easy")) {
                        return f.getDifficulty().equals("Easy");
                    } else if (GameState.difficulty.equals("Normal")) {
                        return f.getDifficulty().equals("Easy") || f.getDifficulty().equals("Normal");
                    } else { // Hard
                        return true;
                    }
                })
                .toList();
    }

    private void updateLives() {
        livesLabel.setText("♥".repeat(GameState.lives));
    }
}
