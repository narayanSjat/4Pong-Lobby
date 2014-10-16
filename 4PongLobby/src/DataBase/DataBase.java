/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static Server.ServerThread.USERDNE;

/**
 *
 * @author Jason Xu
 */
public class DataBase {
    // List of all the usernames currently in the Lobby
    public List<String> list = Collections.synchronizedList(new ArrayList<String>());
    
    
    public void newUser(String newUser){
        list.add(newUser);
    }
    
    public void removeUser(String User){
        list.remove(User);
    }
    
    public void updateUser(String oldUser, String newUser){
        removeUser(oldUser);
        newUser(newUser);
    }
    
    public boolean nameTaken(String newUser){
        return list.contains(newUser);
    }
    
    
}
