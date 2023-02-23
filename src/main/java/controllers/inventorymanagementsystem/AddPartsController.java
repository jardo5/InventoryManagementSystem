package controllers.inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Stop;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartsController implements Initializable {
    @FXML
    public Button saveAddPartButton;
    public Button cancelAddPartButton;

    public void onSaveAddPartButton(ActionEvent actionEvent) {
        System.out.println("Save Button Clicked");
    }

    public void onCancelAddPartButton(ActionEvent actionEvent) {
        System.out.println("Cancel Button Clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
