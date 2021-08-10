/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpClient;

import entidades.Usuario;
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
    
    Usuario u = new Usuario(1, "Thiago", "1022547820", "tgimisull@gmail.com", "123");
    
    
      public Client() {
      }


      public void connection() throws IOException{
           Socket socket = new Socket("localhost", 8888);
            System.out.println("Conectado no servidor!");
            
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
            BufferedReader  reader = new BufferedReader( new InputStreamReader(socket.getInputStream(),"UTF-8"));
            
            //System.out.println(gson.toJson(u));
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("identificador", "login");
            JSONObject mensagem = new JSONObject();
            mensagem.put("usuario", "Thiago");
            mensagem.put("senha", "12345");
            
            jsonObject.put("mensagem", mensagem);
            
            writer.write( jsonObject.toString() + "\n");
            writer.flush();
            String line = reader.readLine();
            
            do{
            
            
            }while(!"exit".equals(line));
            
            socket.close();
      }
    
}
