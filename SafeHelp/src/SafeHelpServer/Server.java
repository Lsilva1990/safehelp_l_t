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

import SafeHelpServer.Util.ClientStatus;
import SafeHelpServer.Util.Msg;
import SafeHelpServer.Util.MsgStatus;
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
    
        private void closeSocket(Socket s) throws IOException {
        s.close();
    }

    private void sendMsg(Object o, ObjectOutputStream out) throws IOException {
        out.writeObject(o);
        out.flush();
    }

    
    private void treatConnection(Socket socket) throws ClassNotFoundException, IOException{
        
         try {
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Tratando...");
            ClientStatus clientStatus = ClientStatus.CONECTED;
            while (clientStatus != ClientStatus.EXIT) {

                Msg msgReceive = (Msg) input.readObject();
                System.out.println("Mensagem do cliente:\n" + msgReceive);
              

                String route = (String) msgReceive.getParam("sad");
                Msg reply = new Msg(route + "REPLY");
                //estados conectado autenticado
                switch (clientStatus) {
                    case CONECTED:
                        switch (route) {
                            case "login:"
                                try {
                                    String user = (String) msgReceive.getParam("user");
                                    String pass = (String) msgReceive.getParam("pass");

                                    if (user.equals("ALUNO") && pass.equals("ESTUDIOSO")) {
                                        reply.setStatus(MsgStatus.OK);
                                        clientStatus = ClientStatus.AUTENTICATED;
                                    } else {
                                        reply.setStatus(MsgStatus.ERROR);
                                    }

                                } catch (Exception e) {
                                    reply.setStatus(MsgStatus.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parâmetros do protocolo.");
                                }
                                break;
                            case "HELLO":
                                String nome = (String) msgReceive.getParam("nome");
                                String sobrenome = (String) msgReceive.getParam("sobrenome");

                                reply = new Msg("HELLOREPLY");

                                if (nome == null || sobrenome == null) {
                                    reply.setStatus(MsgStatus.PARAMERROR);
                                } else {
                                    reply.setStatus(MsgStatus.OK);
                                    reply.setParam("mensagem", "Hello World, " + nome + " " + sobrenome);

                                }
                                break;
                            case "SAIR":
                                reply.setStatus(MsgStatus.OK);
                                clientStatus = ClientStatus.EXIT;
                                break;
                            default:
                                //responder mensagem de erro: Não autorizado/ou inválida
                                reply.setStatus(MsgStatus.ERROR);
                                reply.setParam("msg", "MENSAGEM NÃO AUTORIZADA OU INVÁLIDA!");

                                break;
                        }
                        break;
                    case AUTENTICATED:
                        switch (route) {
                            case "DIV":
                                try {

                                    Integer op1 = (Integer) msgReceive.getParam("op1");
                                    Integer op2 = (Integer) msgReceive.getParam("op2");
                                    //testar os dados
                                    reply = new Msg("DIVREPLY");
                                    if (op2 == 0) {
                                        reply.setStatus(MsgStatus.DIVZERO);
                                    } else {
                                        reply.setStatus(MsgStatus.OK);
                                        System.out.println("Op1: " + op1 + " Op2: " + op2);
                                        float div = (float) op1 / op2;
                                        reply.setParam("res", div);
                                    }
                                } catch (Exception e) {
                                    reply = new Msg("DIVREPLY");
                                    reply.setStatus(MsgStatus.PARAMERROR);
                                }
                                break;
                            case "SUB":
                                break;
                            case "MUL":
                                break;
                            case "SOMA":
                                break;
                            case "LOGOUT":
                                reply.setStatus(MsgStatus.OK);
                                clientStatus = ClientStatus.CONECTED;
                                break;
                            case "SAIR":
                                //DESIGN PATTERN STATE
                                reply.setStatus(MsgStatus.OK);
                                clientStatus = ClientStatus.EXIT;
                                break;
                            default:
                                reply.setStatus(MsgStatus.ERROR);
                                reply.setParam("msg", "MENSAGEM NÃO AUTORIZADA OU INVÁLIDA!");
                                break;
                        }
                        break;
                    case EXIT: //ESTADP
                        break;

                }

                output.writeObject(reply);
                output.flush();//cambio do rádio amador
            }
            //4.2 - Fechar streams de entrada e saída
            input.close();
            output.close();
        } catch (IOException e) {
            //tratamento de falhas
            System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
            System.out.println("Erro: " + e.getMessage());
            throw e;
        } finally {
            //final do tratamento do protocolo
            /*4.1 - Fechar socket de comunicação entre servidor/cliente*/
            closeSocket(socket);
        }

    }
   

    public static void main(String[] args) throws ClassNotFoundException {
       
                try {

            Server server = new Server();

            server.createServerSocket(5555);
            while (true) {
                System.out.println("Aguardando conexão...");
                Socket socket = server.waitConection();//protocolo
                System.out.println("Cliente conectado.");
                //Outro processo
                
                server.treatConnection(socket);
                System.out.println("Cliente finalizado.");
            }
        } catch (IOException e) {
            //trata exceção
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
    
}
