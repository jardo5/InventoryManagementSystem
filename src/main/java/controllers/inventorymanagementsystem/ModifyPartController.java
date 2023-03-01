package controllers.inventorymanagementsystem;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.inventorymanagementsystem.InHouse;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Outsourced;
import models.inventorymanagementsystem.Part;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController {

    public Text idOrName;

    public TextField modifyPartID;
    public TextField modifyPartName;
    public TextField modifyPartInventory;
    public TextField modifyPartPrice;
    public TextField modifyPartMax;
    public TextField modifyPartMin;
    public TextField modifyPartIDOrName;

    public RadioButton radioOutsource;
    public RadioButton radioInHouse;
    public ToggleGroup modifyPartRadioToggle;

    public Button saveModifyPartButton;
    public Button cancelModifyPartButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void retrievePartData(Part part) {
        modifyPartID.setText(String.valueOf(part.getId()));
        modifyPartName.setText(String.valueOf(part.getName()));
        modifyPartInventory.setText(String.valueOf(part.getStock()));
        modifyPartPrice.setText(String.valueOf(part.getPrice()));
        modifyPartMax.setText(String.valueOf(part.getMax()));
        modifyPartMin.setText(String.valueOf(part.getMin()));
        if (part instanceof InHouse) {
            modifyPartIDOrName.setText(String.valueOf(((InHouse) part).getMachineId()));
            radioInHouse.setSelected(true);
            idOrName.setText("Machine ID");
        } else {
            modifyPartIDOrName.setText(String.valueOf(((Outsourced) part).getCompanyName()));
            radioOutsource.setSelected(true);
            idOrName.setText("Company Name");
        }
    }

    public void radioOutsourceToggle() {
        idOrName.setText("Company Name");
    }
    public void radioInHouseToggle() {
        idOrName.setText("Machine ID");
    }

    public void onSaveModifyPartButton() {
        System.out.println("Save button clicked test");
        int stock = 0;
        int min = 0;
        int max = 0;
        int machineId;
        String companyName;
        double price = 0;
        int id = Integer.parseInt(modifyPartID.getText());
        String name = modifyPartName.getText();
        if (name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Name");
            alert.setContentText("Name cannot be empty.");
            alert.showAndWait();
        } else {
            try {
                stock = Integer.parseInt(modifyPartInventory.getText());
            } catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Inventory");
                alert.setContentText("Inventory must be greater than 0.");
                alert.showAndWait();
            }
            try {
                max = Integer.parseInt(modifyPartMax.getText());
            }catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Max");
                alert.setContentText("Max must be greater than 0.");
                alert.showAndWait();
            }
            try {
                min = Integer.parseInt(modifyPartMin.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Min");
                alert.setContentText("Min must be greater than 0.");
                alert.showAndWait();
            }
            try {
                price = Double.parseDouble(modifyPartPrice.getText());
                if (max > min) {
                    if(radioInHouse.isSelected()) {
                        try {
                            machineId = Integer.parseInt(modifyPartIDOrName.getText());

                            Inventory.updatePart(id, new InHouse(id, name, price, stock, min, max, machineId));
                            Stage stage = (Stage) saveModifyPartButton.getScene().getWindow();
                            stage.close();
                        } catch (NumberFormatException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Invalid Machine ID");
                            alert.setContentText("Machine ID Field Error.");
                            alert.showAndWait();
                        }
                    } else {
                        companyName = modifyPartIDOrName.getText();
                        if (companyName.isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Invalid Company Name");
                            alert.setContentText("Company Name cannot be empty.");
                            alert.showAndWait();
                        } else {
                            System.out.println("Before apply test");
                            Inventory.updatePart(id, new Outsourced(id, name, price, stock, min, max, companyName));
                            Stage stage = (Stage) saveModifyPartButton.getScene().getWindow();
                            stage.close();
                            System.out.println("After apply test");
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Min/Max");
                    alert.setContentText("Min must be less than Max.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Price");
                alert.setContentText("Price must be greater than 0.");
                alert.showAndWait();
            }
        }
    }
    public void onCancelModifyPartButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Add Product?");
        alert.setHeaderText("Are you sure you want to close this window?");
        alert.setContentText("All unsaved changes will be lost.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelModifyPartButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
