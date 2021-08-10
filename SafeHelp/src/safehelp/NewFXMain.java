/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safehelp;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author thi_s
 */
public class NewFXMain extends Application {
    
   @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        System.err.println(getClass().getClassLoader().getResource("SafeHelpServer/interfaces/ServerFxml.fxml"));
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("SafeHelpServer/interfaces/ServerFxml.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Server SafeHelp");
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
