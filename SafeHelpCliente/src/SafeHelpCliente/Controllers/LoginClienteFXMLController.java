/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpCliente.Controllers;

import SafeHelpCliente.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private PasswordField pfPassword;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnLogar;
    Client c = new Client();
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnCadastrar;
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
        c.setEmail(tfEmail.getText());
        c.setPassword(pfPassword.getText());
        System.out.println("email: " + c.getEmail());
        System.out.println("senha: " + c.getPassword());
    }

    @FXML
    private void Cadastrar(ActionEvent event) throws IOException {
        if(1==1){
        Stage stage = (Stage) lbTitulo.getScene().getWindow(); //Obtendo a janela atual
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("SafeHelpCliente/Interfaces/CadastroClienteFXML.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Cadastro SafeHelp");
        stage.setMaximized(false);
        stage.show();
    }
    }
    
}
