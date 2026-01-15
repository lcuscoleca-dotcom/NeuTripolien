package neu.tripolien.neutripolien;

import javafx.scene.media.AudioClip;

public class SoundManager {

    //3 Sounds
    private static final AudioClip clickSound;
    private static final AudioClip correctSound;
    private static final AudioClip wrongSound;

    //Sound paths
    static {
        clickSound = new AudioClip(
                SoundManager.class.getResource("/neu/tripolien/sound/click.wav").toExternalForm()
        );
        correctSound = new AudioClip(
                SoundManager.class.getResource("/neu/tripolien/sound/correct.wav").toExternalForm()
        );
        wrongSound = new AudioClip(
                SoundManager.class.getResource("/neu/tripolien/sound/wrong.wav").toExternalForm()
        );
    }


    public static void playClick() { if (GameState.soundEnabled==false) return; else clickSound.play(); }
    public static void playCorrect() { if (GameState.soundEnabled==false) return; else correctSound.play(); }
    public static void playWrong() { if (GameState.soundEnabled==false) return; else wrongSound.play(); }

    //Sounds werden dann der warmUpClip Methode übergeben
    public static void warmUp() {
        warmUpClip(clickSound);
        warmUpClip(wrongSound);
        warmUpClip(correctSound);
    }

    //warumUpClip bekommt von warmUp Sounds übergeben und spiel mit 0 Volume ab und setzt dann Volume zurück
    private static void warmUpClip(AudioClip clip) {
        if (clip == null) return;

        double oldVolume = clip.getVolume();
        clip.setVolume(0.0);
        clip.play();
        clip.setVolume(oldVolume);
    }

}
