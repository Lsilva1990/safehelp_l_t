/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safehelp;


import SafeHelpServer.Modal.User;



/**
 *
 * @author lsilva
 */
public class SafeHelp {
//Criada mensagem aqui
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DB db1 = new DB();
        
        User u = new User("Thiago", "123");
        
        db1.dbCreateUser(u);

        
    }
    
}
