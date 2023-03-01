package jarod.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class (Launcher) for ModifyProducts.
 */

public class ModifyProductsApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ModifyProductsApplication.class.getResource("modifyProduct.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("Modify Parts");
        stage.setScene(scene);
        stage.show();
    }
}
