package neu.tripolien.neutripolien;

public class DifficultyConfig {

    public static int getPoints() {
        return switch (GameState.difficulty) {
            case "Easy" -> 50;
            case "Hard" -> 150;
            default -> 100;
        };
    }

    public static int getTimeLimit() {
        return switch (GameState.difficulty) {
            case "Easy" -> GameState.timeLimit + 10;
            case "Hard" -> GameState.timeLimit - 5;
            default -> GameState.timeLimit;
        };
    }
}
