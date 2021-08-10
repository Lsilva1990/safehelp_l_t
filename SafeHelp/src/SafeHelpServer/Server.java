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
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thi_s
 */
public class Server implements Runnable {

    int porta;
    ServerSocket serverSocket;

    public Server(int porta) {
        this.porta = porta;
        try {
            serverSocket = new ServerSocket(porta);
        } catch (IOException ex) {
            System.err.println("Não foi possível abrir na porta" + porta);
            System.exit(0);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Aguardando conexão");
                serverSocket.accept();
                System.out.println("Cliente conectado.");
            }
        } catch (IOException e) {
            System.err.println("Conexão falhou");
            System.exit(0);
        }
    }

}
