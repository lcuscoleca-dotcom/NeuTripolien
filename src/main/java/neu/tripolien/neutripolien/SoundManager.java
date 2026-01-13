package neu.tripolien.neutripolien;

import javafx.scene.media.AudioClip;

public class SoundManager {

    private static final AudioClip clickSound;
    private static final AudioClip correctSound;
    private static final AudioClip wrongSound;

    static {
        clickSound = new AudioClip(
                SoundManager.class.getResource("/neu/tripolien/sound/click.mp3").toExternalForm()
        );
        correctSound = new AudioClip(
                SoundManager.class.getResource("/neu/tripolien/sound/correct.mp3").toExternalForm()
        );
        wrongSound = new AudioClip(
                SoundManager.class.getResource("/neu/tripolien/sound/wrong.mp3").toExternalForm()
        );
    }

    public static void playClick() { clickSound.play(); }
    public static void playCorrect() { correctSound.play(); }
    public static void playWrong() { wrongSound.play(); }
}
