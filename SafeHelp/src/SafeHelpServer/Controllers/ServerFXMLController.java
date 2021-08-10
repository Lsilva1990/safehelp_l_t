/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpServer.Controllers;

import SafeHelpServer.Server;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lsilva
 */
public class ServerFXMLController implements Initializable {
    
    Server server;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label lbTitulo;
    @FXML
    private TextField tfPorta;
    @FXML
    private ImageView image;
    @FXML
    private Button btnSair;
    @FXML
    private Button btOpenServer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void sair(MouseEvent event) {
    }


    @FXML
    private void openServer(ActionEvent event) {
         server = new Server(Integer.parseInt(tfPorta.getText()));
    }
    
}
