/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safehelp;

import SafeHelpClient.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thi_s
 */
public class SafeHelpClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client c = new Client();
        
        try {
            c.connection();
        } catch (IOException ex) {
            Logger.getLogger(SafeHelpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
}
