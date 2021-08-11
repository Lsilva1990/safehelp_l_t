/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpClient;

import Entidades.Usuarios;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author thi_s
 */
public class Client {

    String line, serverHostname, msg;
    int port;
    boolean isConected = false;
    String log = null;

    Socket socket;
    OutputStreamWriter writer;
    BufferedReader reader;
    Usuarios u = new Usuarios(null, "Lucas", "1022547820", "thimisull@gmail.com", "123");

    public Client() {

    }

    public Client(String serverHostname, int port) {
        System.out.println("Attemping to connect to host "
                + serverHostname + " on port " + port);

        try {
            socket = new Socket(serverHostname, port);
            //writer = new DataInputStream(clientSocket.getInputStream());    // aponta o duto de entrada para o socket do cliente
            writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            //reader = new DataOutputStream(clientSocket.getOutputStream());       // aponta o duto de saída para o socket do cliente
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            isConected = true;
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + serverHostname);
            System.exit(0);
        } catch (IOException e) {
            System.err.println("Não foi possível I/O para"
                    + "a conexão com:" + serverHostname);
            System.exit(0);
        }
        getServerMessage();
   
    }

    public void sendCreateUser(Usuarios u) throws IOException {

        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject(u);
        jsonObject.put("id", "user");
        jsonObject.put("type", "create");
        data.put("name", u.getName());
        data.put("cpf", u.getCpf());
        data.put("email", u.getEmail());
        data.put("password", u.getPassword());
        jsonObject.put("data", data);

        System.err.println(jsonObject.toString());

        writer.write(jsonObject.toString() + "\n");
        writer.flush();

        getServerMessage();
    }

    private String getServerMessage() {

        while (line != null) {
            try {
                line = reader.readLine();

                JSONObject jsonObject = new JSONObject(line);
                JSONObject data = jsonObject.getJSONObject("data");

                String isSuccess = jsonObject.getString("id");

                System.err.println(jsonObject.toString());

                if (isSuccess.equals("success")) {
                    log = data.getString("id");
                    return (data.getString("desc"));
                }

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                return ("Algo deu Errado");
            }

        }
        return ("ok");
    }

    //System.out.println(gson.toJson(u));
//            Create User 
//        JSONObject jsonObject = new JSONObject();
//        JSONObject data = new JSONObject(u);
//        jsonObject.put("id", "user");
//        jsonObject.put("type", "create");
//        data.put("name", u.getName());
//        data.put("cpf", u.getCpf());
//        data.put("email", u.getEmail());
//        data.put("password", u.getPassword());
//        jsonObject.put("data", data);
//        
//        System.err.println(jsonObject.toString());
//
//        writer.write(jsonObject.toString() + "\n");
//        writer.flush();
//              Login
//        JSONObject jsonObjectLogin = new JSONObject();
//        JSONObject dataLogin = new JSONObject();
//        jsonObjectLogin.put("id", "login");
//        dataLogin.put("email", "thimisull@gmail.com");
//        dataLogin.put("password", "123");
//        jsonObjectLogin.put("data", dataLogin);
//
//        System.err.println(jsonObjectLogin.toString());
//
//        writer.write(jsonObjectLogin.toString() + "\n");
//        writer.flush();
//
//        line = reader.readLine();
//        System.err.println(line);
//
//        socket.close();
//    }
//    void sendCreateUser(Socket socket, String email, String password) throws IOException {
//
//        JSONObject jsonObject = new JSONObject();
//        JSONObject data = new JSONObject(u);
//        jsonObject.put("id", "user");
//        jsonObject.put("type", "create");
//        jsonObject.put("data", data);
//
//        System.err.println("Enviando Create user" + jsonObject.toString());
//
//        writer.write(jsonObject.toString() + "\n");
//        writer.flush();
//
//    }
}
