/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpCliente.Controllers;

import SafeHelpCliente.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lsilva
 */
public class CadastroClienteFXMLController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfCPF;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;
    Client c = new Client();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Cadastrar(ActionEvent event) {
        c.setName(tfName.getText());
        c.setCpf(tfCPF.getText());
        c.setPassword(pfPassword.getText());
        c.setAddress(tfAddress.getText());
        c.setPhone(tfPhone.getText());
        System.out.println("Nome: " + tfName.getText());
        System.out.println("CPF: " + tfCPF.getText());
        System.out.println("Senha: " + pfPassword.getText());
        System.out.println("Endereço: " + tfAddress.getText());
        System.out.println("Telefone: " + tfPhone.getText());
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        System.exit(0);
    }
    
}
