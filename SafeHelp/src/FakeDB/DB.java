/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FakeDB;

import SafeHelpServer.Modal.User;
import java.util.ArrayList;

/**
 *
 * @author thi_s
 */
public class DB {
    private ArrayList<User> userList;

    public DB() {
    }
    
    public void dbCreateUser(User user){
        this.userList.add(user);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
    
    
}
