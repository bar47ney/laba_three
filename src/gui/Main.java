package gui; /**
 * Created by Сергей on 14.05.2019.
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) {
        StartingWindow window = new StartingWindow();
        window.display(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
