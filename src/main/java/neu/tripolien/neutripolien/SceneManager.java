package neu.tripolien.neutripolien;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage stage;

    public static void setStage(Stage s) {
        stage = s;
    }

    public static void switchScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    SceneManager.class.getResource(fxml)
            );
            stage.setScene(new Scene(loader.load(), 900, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
