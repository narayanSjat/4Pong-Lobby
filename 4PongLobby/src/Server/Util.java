/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import DataBase.DataBase;
import Server.ServerThread.*;
import static Server.ServerThread.INVALIDCOMMAND;
import static Server.ServerThread.USERDNE;
/**
 *
 * @author Jason Xu
 */
public class Util {
    public DataBase object =new DataBase();

    
    public int processCommand(String input){
        String[] splitInput = input.split(",");
        for(int i = 0; i < splitInput.length; i++){
            splitInput[i] = splitInput[i].trim();
            //System.out.println(splitInput[i]);
        }
        
        if(splitInput[0].equals("updateUser")){
            boolean nameExists = object.nameTaken(splitInput[1]);
            if(!nameExists){
                System.out.println("User DNE, can not be updated");
                return -1; //Need to figure out a standard for returning error messages
            }
            else {
                object.updateUser(splitInput[1], splitInput[2]);
                System.out.println("Updated User: "+ splitInput[1] + " -> " + splitInput[2]);
            }
        }
        else if(splitInput[0].equals("newUser")){
            boolean nameExists = object.nameTaken(splitInput[1]);
            if(nameExists){
                System.out.println("User exists, cant add new user");
                return -1; //Need to figure out a standard for returning error messages
            }
            else{
                object.newUser(splitInput[1]);
                System.out.println("Added new user: " + splitInput[1]);
            }
            
        }
        else if(splitInput[0].equals("removeUser")){
            boolean nameExists = object.nameTaken(splitInput[1]);
            if(!nameExists){
                System.out.println("User DNE, can not be removed");
                return -1; //Need to figure out a standard for returning error messages
            }
            else{
                object.removeUser(splitInput[1]);
                System.out.println("Removed User: " + splitInput[1]);

            }
            
        }
        else if(splitInput[0].equals("broadcastMessage")){
            
        }
        else if(splitInput[0].equals("joinGame")){
            
        }
        else{
            //Not a valid command, return error
            System.out.println("Invalid Command");
            return INVALIDCOMMAND;
        }
        
        return 0;
    }
   
}
