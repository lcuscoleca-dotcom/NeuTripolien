package neu.tripolien.neutripolien;

public class GameState {

    // SCORE & PROGRESS
    public static int score;
    public static int lives;
    public static int currentQuestion;
    public static int correctAnswers;
    public static String username;

    // SETTINGS
    public static String difficulty = "Normal";
    public static int questionCount = 10;
    public static int timeLimit = 30;
    public static boolean soundEnabled = true;
    public static boolean hintsEnabled = true;

    // Setzt Setting auf default zurück
    public static void reset() {
        score = 0;
        lives = switch (difficulty) {
            case "Easy" -> 5;
            case "Hard" -> 2;
            default -> 3;
        };
        currentQuestion = 0;
        correctAnswers = 0;
    }
}
