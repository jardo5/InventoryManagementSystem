package jarod.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class (Launcher) for AddParts.
 */

public class AddPartsApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AddPartsApplication.class.getResource("addParts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Add Parts");
        stage.setScene(scene);
        stage.show();
    }
}
