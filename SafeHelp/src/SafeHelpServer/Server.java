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
        
         try {
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Tratando...");
            Estados estado = Estados.CONECTADO;
            while (estado != Estados.SAIR) {

                Mensagem m = (Mensagem) input.readObject();
                System.out.println("Mensagem do cliente:\n" + m);
              

                String operacao = m.getOperacao();
                Mensagem reply = new Mensagem(operacao + "REPLY");
                //estados conectado autenticado
                switch (estado) {
                    case CONECTADO:
                        switch (operacao) {
                            case "LOGIN":
                                try {
                                    String user = (String) m.getParam("user");
                                    String pass = (String) m.getParam("pass");

                                    if (user.equals("ALUNO") && pass.equals("ESTUDIOSO")) {
                                        reply.setStatus(Status.OK);
                                        estado = Estados.AUTENTICADO;
                                    } else {
                                        reply.setStatus(Status.ERROR);
                                    }

                                } catch (Exception e) {
                                    reply.setStatus(Status.PARAMERROR);
                                    reply.setParam("msg", "Erro nos parâmetros do protocolo.");
                                }
                                break;
                            case "HELLO":
                                String nome = (String) m.getParam("nome");
                                String sobrenome = (String) m.getParam("sobrenome");

                                reply = new Mensagem("HELLOREPLY");

                                if (nome == null || sobrenome == null) {
                                    reply.setStatus(Status.PARAMERROR);
                                } else {
                                    reply.setStatus(Status.OK);
                                    reply.setParam("mensagem", "Hello World, " + nome + " " + sobrenome);

                                }
                                break;
                            case "SAIR":
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;
                            default:
                                //responder mensagem de erro: Não autorizado/ou inválida
                                reply.setStatus(Status.ERROR);
                                reply.setParam("msg", "MENSAGEM NÃO AUTORIZADA OU INVÁLIDA!");

                                break;
                        }
                        break;
                    case AUTENTICADO:
                        switch (operacao) {
                            case "DIV":
                                try {

                                    Integer op1 = (Integer) m.getParam("op1");
                                    Integer op2 = (Integer) m.getParam("op2");
                                    //testar os dados
                                    reply = new Mensagem("DIVREPLY");
                                    if (op2 == 0) {
                                        reply.setStatus(Status.DIVZERO);
                                    } else {
                                        reply.setStatus(Status.OK);
                                        System.out.println("Op1: " + op1 + " Op2: " + op2);
                                        float div = (float) op1 / op2;
                                        reply.setParam("res", div);
                                    }
                                } catch (Exception e) {
                                    reply = new Mensagem("DIVREPLY");
                                    reply.setStatus(Status.PARAMERROR);
                                }
                                break;
                            case "SUB":
                                break;
                            case "MUL":
                                break;
                            case "SOMA":
                                break;
                            case "LOGOUT":
                                reply.setStatus(Status.OK);
                                estado = Estados.CONECTADO;
                                break;
                            case "SAIR":
                                //DESIGN PATTERN STATE
                                reply.setStatus(Status.OK);
                                estado = Estados.SAIR;
                                break;
                            default:
                                reply.setStatus(Status.ERROR);
                                reply.setParam("msg", "MENSAGEM NÃO AUTORIZADA OU INVÁLIDA!");
                                break;
                        }
                        break;
                    case SAIR: //ESTADP
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
            fechaSocket(socket);
        }

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
