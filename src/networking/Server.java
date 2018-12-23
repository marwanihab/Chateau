package networking;


 
import java.io.*;
import java.util.*;
import java.net.*;

import chateau.gui.Members;
 
// Server class
public class Server 
{
 
	
    static ArrayList<chatting> clients = new ArrayList<>();
    static PriorityQueue<String> requests= new PriorityQueue<>();
    static File docroot = new File("/Users/Maro31/Desktop/Chateau/docroot");
   

	static ArrayList<String> names = new ArrayList<>();
	static Members members = new Members();
     
    // counter for clients
    
	public Server()throws IOException {
		ServerSocket Server= new ServerSocket(6790); 
	      Socket clientSocket;
	      
      while (true) 
      {
          members.setVisible(false);
          clientSocket = Server.accept();
          
          System.out.println("connected successfully!!");
          
          DataInputStream input = new DataInputStream(clientSocket.getInputStream());
          DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
          
          chatting client = new chatting(clientSocket,"user", input, output, false ,false );

          Thread toBeExecuted = new Thread(client);
           
          clients.add(client);

         
          toBeExecuted.start();

         
          

      }
		
	}
	
	
	
	
    public static void main(String[] args) throws IOException 
    {

    	  ServerSocket Server= new ServerSocket(6790); 
	      Socket clientSocket;
	      
        while (true) 
        {
            
            clientSocket = Server.accept();
            
            System.out.println("connected successfully!!");
            
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            
            chatting client = new chatting(clientSocket,"user", input, output, false ,false );
 
            Thread toBeExecuted = new Thread(client);
             
            clients.add(client);
 
           
            toBeExecuted.start();
 
           
            
 
        }
    }
}
 
