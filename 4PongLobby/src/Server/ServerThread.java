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


public class ServerThread extends Thread
{  
   
   public static final int USERDNE = -2;
   public static final int INVALIDCOMMAND = -1;
   
   public Util utils = new Util();
           
   private Socket socket   = null;
   private Server server   = null;
   private int ID = -1;
   private DataInputStream streamIn =  null;

   public ServerThread(Server _server, Socket _socket)
   {  server = _server;  socket = _socket;  ID = socket.getPort();
   }
   public void run()
   {  System.out.println("Server Thread " + ID + " running.");
      while (true)
      {  try
         {  
             String input = streamIn.readUTF();
             int ret = utils.processCommand(input);
             
         }
         catch(IOException ioe) {  }
      }
   }
   public void open() throws IOException
   {  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
   }
   public void close() throws IOException
   {  if (socket != null)    socket.close();
      if (streamIn != null)  streamIn.close();
   }
   
    
}