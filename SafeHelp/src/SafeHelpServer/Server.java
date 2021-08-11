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

import DAO.UsuariosDAO;
import Entidades.Usuarios;
import java.io.BufferedReader;
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
    UsuariosDAO userDAO;
    Usuarios userLoged;

    public Server(int bind) {
        createConection(bind);
    }

    private void createConection(int bind) {
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

    private void start(final Socket socket) {

        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                    String line = reader.readLine();
                    JSONObject jsonObject = new JSONObject(line);

//                    writer.write("Mensagem Recebida :" + jsonObject.getString("id"));
//                    writer.flush();

                    System.out.println("Mensagem Recebida :" + jsonObject.getString("id"));

                    JSONObject data = jsonObject.getJSONObject("data");

                    do {
                        switch (jsonObject.getString("id")) {
                            case "user":
                                switch (jsonObject.getString("type")) {
                                    case "create":
                                        Usuarios newUser = new Usuarios();
                                        newUser.setName(data.getString("name"));
                                        newUser.setCpf(data.getString("cpf"));
                                        newUser.setEmail(data.getString("email"));
                                        newUser.setPassword(data.getString("password"));
                                        //System.err.println(data.getString("adress"));
//                                        if(data.getString("adress") !=  JSONObject)
//                                        newUser.setUsuarioEndereco(data.getString("address"));
//                                        if(data.getString("phone") != null)
//                                        newUser.setUsuarioTelefone(data.getString("phone"));
                                        userDAO = new UsuariosDAO();
                                        try {
                                            userDAO.add(newUser);
                                            System.err.println("deu boa");
                                            writer.write("deu boa" + "\n");
                                            line = null;

                                        } catch (Exception ex) {
                                            JSONObject error = new JSONObject();
                                            JSONObject dataError = new JSONObject();
                                            error.put("id", "error");
                                            dataError.put("desc", "error create user");
                                            error.put("data", dataError);
                                            writer.write(jsonObject.toString() + "\n");
                                            System.err.println(ex.toString());
                                            line = null;
                                            break;
                                        }
                                    case "update":
                                        Usuarios udpateUser = new Usuarios();
                                        udpateUser.setName(data.getString("name"));
                                        udpateUser.setCpf(data.getString("cpf"));
                                        udpateUser.setEmail(data.getString("email"));
                                        udpateUser.setPassword(data.getString("password"));
                                        //System.err.println(data.getString("adress"));
//                                        if(data.getString("adress") !=  JSONObject)
//                                        newUser.setUsuarioEndereco(data.getString("address"));
//                                        if(data.getString("phone") != null)
//                                        newUser.setUsuarioTelefone(data.getString("phone"));
                                        userDAO = new UsuariosDAO();
                                        try {
                                            userDAO.add(udpateUser);
                                            System.err.println("deu boa");
                                            writer.write("deu boa" + "\n");
                                            line = null;

                                        } catch (Exception ex) {
                                            JSONObject error = new JSONObject();
                                            JSONObject dataError = new JSONObject();
                                            error.put("id", "error");
                                            dataError.put("desc", "error create user");
                                            error.put("data", dataError);
                                            writer.write(jsonObject.toString() + "\n");
                                            System.err.println(ex.toString());
                                            line = null;
                                            break;
                                        }
                                    default:
                                        break;
                                }
                            case "login":
                                userLoged = new Usuarios();
                                String email = data.getString("email");
                                String password = data.getString("password");
                                userDAO = new UsuariosDAO();
                                userLoged = userDAO.userLogin(email, password);
                                writer.write(userLoged.getId().toString());
                                System.err.println(userLoged.getId().toString());
                                line = null;
                                break;
                            default:
                                break;  
                        }
                        //System.out.println("Cliente enviou: " + line);
                    } while (line != null);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeSocket();
                }
            }

            private void closeSocket() {
                try {
                    socket.close();
                } catch (Exception e) {

                }
            }

        };

        thread.start();

    }

}
