package SafeHelpCliente.execute;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lsilva
 */
public class SafeHelpClientePlay extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        System.err.println(getClass().getClassLoader().getResource("SafeHelpCliente/Interfaces/LoginClienteFXML.fxml"));
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("SafeHelpCliente/Interfaces/LoginClienteFXML.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client SafeHelp");
        primaryStage.setMaximized(false);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
