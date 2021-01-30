import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.*;
import java.net.URL;
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
     * ================= METHODS =================
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
          TODO:
           1- IMPLEMENT OPENING FILE WHEN LOAD BUTTON IS CLICKED (x)
           2- PRINT LOADED FILE'S DATA (x)
           3- SHOW FILE PATH ON TEXT FIELD (x)
           4- GET OPTION FROM UI
         */

        // setup of  ui bindings / listener
        fileInputTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                String outputName = newValue.substring(0, newValue.length() - 3);
                outputName += new Date().getTime() + ".txt";
                fileIOutputTextField.setText(outputName);
            }
        });

        // when `load` button is clicked call `handleLoadingFile` method
        loadButton.setOnAction(this::handleLoadingFile);
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
