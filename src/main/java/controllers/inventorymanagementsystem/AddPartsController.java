package controllers.inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartsController implements Initializable {
    @FXML
    public Button saveAddPartButton;
    public Button cancelAddPartButton;

    public RadioButton radioInHouse; //Machine ID
    public RadioButton radioOutsource; //Company Name
    public Text idOrName;

    public void onSaveAddPartButton(ActionEvent actionEvent) {

    }

    public void onCancelAddPartButton(ActionEvent actionEvent) {
        // Alert to confirm closing window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Add Part?");
        alert.setHeaderText("Are you sure you want to close this window?");
        alert.setContentText("All unsaved changes will be lost.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelAddPartButton.getScene().getWindow();
                stage.close();
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onOutsourced(ActionEvent actionEvent) {
        idOrName.setText("Company Name");
    }

    public void onInHouse(ActionEvent actionEvent) {
        idOrName.setText("Machine ID");
        idOrName.setFont(Font.font ("Fira Code", 12));
    }
}
