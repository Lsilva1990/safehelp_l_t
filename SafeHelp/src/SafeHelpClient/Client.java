/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpClient;

import Entidades.Usuarios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author thi_s
 */
public class Client {

    String line;
    Usuarios u = new Usuarios(null, "Thiago", "1022547820", "thimisull@gmail.com", "123");

    public Client() {
    }

    public void connection() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        System.out.println("Conectado no servidor!");

        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

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
        JSONObject jsonObjectLogin = new JSONObject();
        JSONObject dataLogin = new JSONObject();
        jsonObjectLogin.put("id", "login");
        dataLogin.put("email", "thimisull@gmail.com");
        dataLogin.put("password", "123");
        jsonObjectLogin.put("data", dataLogin);

        System.err.println(jsonObjectLogin.toString());

        writer.write(jsonObjectLogin.toString() + "\n");
        writer.flush();

        line = reader.readLine();
        System.err.println(line);

        socket.close();
    }
}
