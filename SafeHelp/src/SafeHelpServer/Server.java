/*
* 1- Criar Servidor de conexões
* 2- Esperar um pedido de conexão e
*   2.1- criar uma nova conexão
* 3- Criar streams de entrada e saída
* 4- Tratar a conversação entre cliente e servidor (protocolo)
*   4.1- Fechar socket de comunicação entre servidor e cliente
*   4.2- Fechar streams de entrada e saída
* 5- voltar para o passo 2, até finalizar o programa
* 6- fechar serverSocket
 */

package SafeHelpServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thi_s
 */
public class Server {
    
    private ServerSocket serverSocket;
   
    //* 1- Criar Servidor de conexões
    private void createServerSocket(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }
    //* 2- Esperar um pedido de conexão 
    private Socket waitConection() throws IOException{
        Socket socket = serverSocket.accept();
        return socket;
    }
    
    private void treatConnection(Socket socket){
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        while(input.readObject().equals({"sair", "as"}));
    }

    public static void main(String[] args) {
       
        try {
                Server server = new Server();
                server.createServerSocket(6666);
                Socket socket = server.waitConection();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }
    
}
