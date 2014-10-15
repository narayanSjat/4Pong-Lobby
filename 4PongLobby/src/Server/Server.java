package Server;

import java.net.*;
import java.io.*;

public class Server implements Runnable
{  private ServerSocket     server = null;
   private Thread           thread = null;
   private ServerThread client = null;

   public Server(int port)
   {  try
      {  System.out.println("Binding to port " + port + ", please wait  ...");
         server = new ServerSocket(port);  
         System.out.println("Server started: " + server);
         start();
      }
      catch(IOException ioe)
      {  System.out.println(ioe); }
   }
   @Override
   public void run()
   {  while (thread != null)
      {  try
         {  System.out.println("Waiting for a client ..."); 
            addThread(server.accept());
         }
         catch(IOException ie)
         {  System.out.println("Acceptance Error: " + ie); }
      }
   }
   public void addThread(Socket socket) throws IOException
   {  System.out.println("Client accepted: " + socket);
      client = new ServerThread(this, socket);
      client.open();
      client.start();
   }
   public void start()
   {  if (thread == null)
      {  thread = new Thread(this); 
         thread.start();
      }
   }
   public void stop()
   {  if (thread != null)
      {  thread.stop(); 
         thread = null;
      }
   }
   public static void main(String args[])
   {  
      Server server = null;
      int port = 5000;
      server = new Server(port);
   }
   
}