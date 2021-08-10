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

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author thi_s
 */
public class Server {

    private static int bind = 8888;
    Gson gson = new Gson();

    public Server() {
        createConection();
    }

    private static void createConection() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(bind);
        } catch (IOException ex) {
            System.err.println("Não foi possível abrir na porta");
            System.exit(0);
        }

        try {
            while (true) {
                System.out.println("Aguardando conexão");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");
                start(socket);
            }
        } catch (IOException e) {
            System.err.println("Conexão falhou");
            System.exit(0);
        }
    }

    private static void start(final Socket socket) throws IOException {
        
        Thread thread = new Thread(){
            @Override
            public void run(){
                
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    
                    String line = reader.readLine();
                    System.out.print(line);
                    
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeSocket();                  
                }
            }
            
            private void closeSocket(){
                try {
                     socket.close();
                } catch (Exception e) {
    
                }
            }
            
        };

        thread.start();
        
    }

}
