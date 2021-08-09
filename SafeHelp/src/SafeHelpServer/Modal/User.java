/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpServer.Modal;

/**
 *
 * @author thi_s
 */
public class User {
    private String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
//    public void login(String username, String password){;
//        if(username.equals(this.username) && password.equals(this.password)){
//            
//        }
//    }

}

