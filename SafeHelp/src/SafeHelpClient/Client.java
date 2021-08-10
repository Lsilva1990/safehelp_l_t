/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpClient;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author thi_s
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8888);
        System.out.println("Conectado no servidor!");

    }
}
