/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpCliente.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lsilva
 */
public class LoginClienteFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label lbTitulo;
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnLogar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sair(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void Login(ActionEvent event) {
    }
    
}
