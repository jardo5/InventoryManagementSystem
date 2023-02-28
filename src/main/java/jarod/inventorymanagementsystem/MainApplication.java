package jarod.inventorymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.inventorymanagementsystem.InHouse;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Outsourced;
import models.inventorymanagementsystem.Product;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //Test Data for Parts
        InHouse part1 = new InHouse(5875,"Rim",66.98,23,3,25,123512);
        InHouse part2 = new InHouse(87,"Lugs",634.98,23,3,25,3445);
        Outsourced part3 = new Outsourced(5678,"Bolts",12.99,70,45,65,"Test Company 2");
        Outsourced part4 = new Outsourced(5234,"Tires",9.99,165,100,125,"Test Company");
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);

        //Test Data for Products
        Product product1 = new Product(3214,"Kia Tire",99.99,12,5,23);
        Product product2 = new Product(2134,"Tesla Tire",499.99,98,2,45);
        Product product3 = new Product(4353,"Ford Tire",199.99,23,2,42);
        Product product4 = new Product(6788,"Chevrolet Tire",299.99,33,2,45);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch();
    }
}