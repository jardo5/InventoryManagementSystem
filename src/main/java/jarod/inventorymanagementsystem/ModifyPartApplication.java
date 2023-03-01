package jarod.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class (Launcher) for ModifyPart.
 */

public class ModifyPartApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ModifyPartApplication.class.getResource("modifyPart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }
}
