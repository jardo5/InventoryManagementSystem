package jarod.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class (Launcher) for AddProduct.
 */

public class AddProductApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AddProductApplication.class.getResource("addProduct.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }
}
