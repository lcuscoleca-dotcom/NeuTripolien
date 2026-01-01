package neu.tripolien.neutripolien;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage stage;

    public static void setStage(Stage s) {
        stage = s;
    }

    // Erste Szene laden
    public static Scene loadScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(
                    SceneManager.class.getResource(fxml)
            );
            return new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Szenenwechsel OHNE Unterbrechung
    public static void switchScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(
                    SceneManager.class.getResource(fxml)
            );

            // 🔥 Nur Root tauschen → absolut flackerfrei
            stage.getScene().setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
