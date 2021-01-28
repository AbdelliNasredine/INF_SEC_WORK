import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class Main  extends Application {

    private static final String APPLICATION_NAME = "CRYPTO SIR";

    public void start(Stage primaryStage) throws Exception {
        Parent parent = new Pane(new Label("HELLO WORLD"));
        Scene scene = new Scene(parent, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(APPLICATION_NAME);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
