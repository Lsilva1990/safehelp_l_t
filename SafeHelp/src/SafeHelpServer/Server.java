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

import DAO.UsuarioDAO;
import entidades.Usuario;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author thi_s
 */
public class Server {

    private int bind;
    Usuario user;

    public Server(int bind) {
        createConection(bind);
    }

    private  void createConection(int bind) {
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

    private  void start(final Socket socket) throws IOException {
        
        Thread thread = new Thread(){
            @Override
            public void run(){
                
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    
                    String line = reader.readLine();
                    JSONObject jsonObject = new JSONObject(line);
                    
                    writer.write(jsonObject.getString("identificador"));
                    writer.flush();
                    
                    System.out.println("Indentificador recebido: " + jsonObject.getString("identificador"));
                    
                    JSONObject mensagem = jsonObject.getJSONObject("mensagem");
                            
                    System.err.println("Mensagem Recebida email " + mensagem.getString("usuario"));
                    
//                    do {
//                    switch (jsonObject.getString("identificador")) {
//                        case "login":
//                            
//                            break;
//                        case "logout":
//                            protocol.setAction("logout");
//                            os.writeUTF(gson.toJson(protocol));
//                            System.out.println(gson.toJson(protocol));
//                            lista.remove(user);
//                            lista.remove(os);
//                            protocol = new Protocolo("listarUsuarios", lista.getCliente());
//                            for (DataOutputStream cliente : broadcast) {
//                                cliente.writeUTF(gson.toJson(protocol));
//                            }
//                            //writer.write(gson.toJson(lista));
//                            //writer.close();
//                            clientSocket.close();
//                            serv.notifica();
//                            return;
//                        case "broadcast":
//                            protocol = new Protocolo(user.getNome() + ": " + protocol.getMensagem());
//                            for (DataOutputStream cliente : broadcast) {
//                                cliente.writeUTF(gson.toJson(protocol));
//                            }
//                            serv.notifica(protocol.getMensagem());
//                            break;
//                        case "cadastrarServico":
//                            protocol.getServico().setEmpregador(user);
//                            lServico.add(protocol.getServico());
//                            protocol = new Protocolo(lServico.getServicos(), "listarServicos");
//                            for (DataOutputStream cliente : broadcast) {
//                                cliente.writeUTF(gson.toJson(protocol));
//                            }
//                            serv.notifica();
//                            break;
//                        case "mensagemDireta":
//                            protocol.setRemetente(user);
//                            DataOutputStream destino;
//                            i = lista.getUsuarioIndex(protocol.getDestinatario());
//                            destino = broadcast.get(i);
//                            protocol.setDestinatario(null);
//                            destino.writeUTF(gson.toJson(protocol));
//                            break;
//                        case "interesseServico":
//                            i = lServico.getServIndex(protocol.getServico());
//                            lServico.addInteressado(i, user);
//                            protocol = new Protocolo(lServico.getListInteresse().get(i));
//                            i = lista.getUsuarioIndex(protocol.getServico().getEmpregador());
//                            destino = broadcast.get(i);
//                            destino.writeUTF(gson.toJson(protocol));
//                            break;
//                        case "contratacao":
//                            i = lista.getUsuarioIndex(protocol.getDestinatario());
//                            destino = broadcast.get(i);
//                            protocol.setDestinatario(null);
//                            destino.writeUTF(gson.toJson(protocol));
//                            lServico.remove(protocol.getServico());
//                            protocol = new Protocolo(lServico.getServicos(), "listarServicos");
//                            for (DataOutputStream cliente : broadcast) {
//                                cliente.writeUTF(gson.toJson(protocol));
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                    System.out.println("Cliente enviou: " + line);
//                    line = is.readUTF();
//                    protocol = gson.fromJson(line, Protocolo.class);
//                } while (line != null);
                    
             
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
