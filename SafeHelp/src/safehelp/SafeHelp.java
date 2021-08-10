/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safehelp;

import SafeHelpServer.Server;



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
        
        Server s = new Server(8888);
        
        s.run();
    }
    
}
