/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Jason Xu
 */
import Server.Util.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread extends Thread
{  
   
   public static final int USERDNE = -2;
   public static final int INVALIDCOMMAND = -1;
   
   public Util utils = new Util();
           
   private Socket socket   = null;
   private Server server   = null;
   private int ID = -1;
   private DataInputStream streamIn =  null;
   private DataOutputStream streamOut = null;
   public ArrayList<ServerThread> clientList = new ArrayList<ServerThread>();
   public String processed = "Processed Command\n";

   public ServerThread(Server _server, Socket _socket)
   {  server = _server;  socket = _socket;  ID = socket.getPort();
   }
   public void run()
   {  
        System.out.println("Server Thread " + ID + " running.");
        
        clientList.add(this);

        while (true){  
            String input = null;
            try {
                input = read();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            String ret = utils.processCommand(input);
            write(processed);
        }
   }
   public void open() throws IOException
   {  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
      streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

   }
   public void close() throws IOException
   {  if (socket != null)    socket.close();
      if (streamIn != null)  streamIn.close();
   }
   
   public void write(Object msg){
       try{
           streamOut.writeUTF((String) msg);
           System.out.println("Message sent to the client is " + processed);
           streamOut.flush();

       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public String read() throws IOException{
       return streamIn.readUTF();
   }
   public void sendToOne(int index, Object message){
       clientList.get(index).write(message);
   }
   
   public void sendToAll(Object message){
        for(ServerThread client : clientList)
            client.write(message);
   }
    
}