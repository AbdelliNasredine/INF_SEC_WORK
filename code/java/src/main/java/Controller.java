import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final ObservableList<String> algorithmsList =
            FXCollections.observableList(Arrays.asList("CESAR"));

    private final BooleanProperty doneProperty = new SimpleBooleanProperty(true);

    /**
     * ================= UI FIELDS =================
     */
    @FXML
    private TextField fileInputTextField;

    @FXML
    private Button loadButton;

    @FXML
    private RadioButton encryptionRadioButton;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private RadioButton decryptionRadioButton;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    private Spinner<Integer> keySpinner;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private Button startButton;

    @FXML
    private Button saveButton;


    /**
     * ================= METHODS =================
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
          TODO:
           1- IMPLEMENT OPENING FILE WHEN LOAD BUTTON IS CLICKED (x)
           2- PRINT LOADED FILE'S DATA (x)
           3- SHOW FILE PATH ON TEXT FIELD (x)
           4- GET OPTION FROM UI (x)
         */

        // setup of  ui bindings / listener
        algorithmComboBox.setItems(algorithmsList);
        keySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 25, 1));

        startButton.setOnAction(this::handleStartAction);
        loadButton.setOnAction(this::handleLoadingFile);
        saveButton.disableProperty().bind(doneProperty);
    }

    private void handleStartAction(ActionEvent event) {
        // read operation type , algorithm, key

    }

    private void handleLoadingFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        ExtensionFilter textFilesExtensionFilter =
                new ExtensionFilter("Text files .txt", "*.txt");
        fileChooser.getExtensionFilters().add(textFilesExtensionFilter);
        File file = fileChooser.showOpenDialog(loadButton.getParent().getScene().getWindow());
        if (file != null) {
            fileInputTextField.setText(file.getAbsolutePath());
            String fileContent = readFile(file);
            System.out.println(fileContent);
        }
    }

    private String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try(FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(String.format("%s\n", line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
