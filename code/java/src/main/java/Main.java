import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public final class Main extends Application implements Initializable {

    /**
     * CONSTANTS
     */
    private static final String APPLICATION_NAME = "CRYPTO SIR";

    /**
     * UI FIELDS
     */
    @FXML
    private TextField fileInputTextField;

    @FXML
    private Button loadButton;

    @FXML
    private TextField fileIOutputTextField;

    @FXML
    private RadioButton encryptionRadioButton;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private RadioButton decryptionRadioButton;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    private Button startButton;


    /**
     * METHODS
     */

    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("main_ui.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle(APPLICATION_NAME);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
