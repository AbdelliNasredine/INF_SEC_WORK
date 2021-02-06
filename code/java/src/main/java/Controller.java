import algorithms.Cesar;
import algorithms.Viginer;
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

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    private TextField keyField;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private Button startButton;

    @FXML
    private Button saveButton;

    /**
     * ================= CONSTANTS =================
     */
    private static final String CESAR = "CESAR";
    private static final String VEGINER = "VEGINER";
    private final ObservableList<String> algorithmsList =
            FXCollections.observableList(Arrays.asList(CESAR, VEGINER));
    private final BooleanProperty doneProperty = new SimpleBooleanProperty(true);
    private String fileContent;
    private String output;

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
        //keyField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 25, 1));
        startButton.disableProperty().setValue(true);
        startButton.setOnAction(this::handleStartAction);
        loadButton.setOnAction(this::handleLoadingFile);
        saveButton.setOnAction(this::handleSavingFile);
        saveButton.disableProperty().bind(doneProperty);
    }

    private void handleSavingFile(ActionEvent event) {
        if (!output.isEmpty()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Saving Output File");
            File outputFile = fileChooser.showSaveDialog(loadButton.getParent().getScene().getWindow());
            if (outputFile != null) {
                try {
                    FileWriter fileWriter = new FileWriter(outputFile);
                    for (Character character : output.toCharArray()) {
                        fileWriter.append(character);
                    }
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            showAlert("Output is Empty");
        }
    }

    private void handleStartAction(ActionEvent event) {
        // reading operation type
        String opType = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
        // reading algorithm
        String algorithm = algorithmComboBox.getValue();
        if (algorithm == null) {
            showAlert("Please select an algorithm");
            return;
        }
        // reading key
        String key = keyField.getText();
        System.out.printf("\nopType = %s\nalgorithm = %s\nkey = %s\n", opType, algorithm, key);
        if (!fileContent.isEmpty()) {
            printOnConsole(String.format("Starting %s %s on file content with key = %s", algorithm, opType, key));
            if (algorithm.equals(CESAR)) {
                int keyCode = 0;
                try {
                    keyCode = Integer.parseInt(key);
                    return;
                }catch (NumberFormatException e){
                    showAlert("Key should be a number");
                }
                Cesar cesar = new Cesar(keyCode);
                output = opType.equals(encryptionRadioButton.getText()) ? cesar.encrypt(fileContent) :
                        cesar.decrypt(fileContent);
                System.out.println(output);
                doneProperty.setValue(false);
                printOnConsole("Finished, output is");
                printOnConsole(output);
            } else {
                Viginer viginer = new Viginer(key);
                output = opType.equals(encryptionRadioButton.getText()) ? viginer.encrypt(fileContent) :
                        viginer.decrypt(fileContent);
                System.out.println(output);
                doneProperty.setValue(false);
                printOnConsole("Finished, output is");
                printOnConsole(output);
            }
        } else {
            showAlert("File is empty");
        }
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
            printOnConsole(file.getName() + " file opened");
            fileInputTextField.setText(file.getAbsolutePath());
            fileContent = readFile(file);
            startButton.disableProperty().setValue(false);
            System.out.println(fileContent);
        }
    }

    private String readFile(File file) {
        printOnConsole("Reading file content");
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printOnConsole(line);
                sb.append(String.format("%s\n", line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.show();
    }

    private void printOnConsole(String message) {
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        consoleTextArea.appendText(String.format("%-10s %s\n", df.format(new Date()), message));
    }

}
