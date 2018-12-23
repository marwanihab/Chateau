package networking;
//import Server;
//import chatting;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import chateau.gui.Main;



class chatting implements Runnable 
{
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean byeReceived ;
    boolean byeSent ;
    boolean joinSent =false;
     
   
    public chatting(Socket s, String name,
                            DataInputStream dis, DataOutputStream dos , boolean byeReceived , boolean byeSent) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.byeReceived = byeReceived;
        this.byeSent = byeSent;
        
    }
    
    private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }
 
    @Override
    public void run() {
 
        String clientMessage;
        boolean found = false;
        while (true) 
        {
            try
            {
               
                clientMessage = dis.readUTF();
                 
                //System.out.println(clientMessage);
                 
                
                
                
                if(clientMessage.equals("Quit")){
                   
                	 for (int i = 0; i < Server.names.size(); i++) {
						if(Server.names.get(i)==this.name){
							Server.names.remove(i);
						}
                		 
                		 
					}
                         //System.out.println(ttl);
                         
                	
                    this.s.close();
                    
                    break;
                }
                
                if(clientMessage.contains("join(")){
                	String newName = "";
                	 for (int i = 5; i < clientMessage.length()-1; i++) {
						
						char letter = clientMessage.charAt(i);
						newName = newName +letter;
					}
                	 
                	 
                	 if(!(Server.names.contains(newName))){
                	 this.name = newName;
                	 new File("/Users/Maro31/Desktop/clients/"+newName).mkdirs();
                	 Server.names.add(newName);
                	 this.joinSent = true;
                	 this.dos.writeUTF("your username is "+newName);
                	 
                	 }
                	 else{
                		 this.dos.writeUTF("name is already used by another user , please specify another one in order to proceed");
                	 }
                }
                if(this.joinSent==true){
                
                if(clientMessage.contains("GET")){
            		
            		String request= this.name+","+clientMessage;
            		Server.requests.add(request);
            		//System.out.println(Server.requests.peek());
            		String url="";
            		String httpLine1 ="";
            		String httpLine2 ="";
            		String format ="";
            		String connection ="";
            		
            		String [] x = clientMessage.split(",");
            		httpLine1 =x[0];
            		httpLine2 =x[1];
            		format =x[2];
            		connection =x[3];
            		String[] httpLine1Split = httpLine1.split("\\s+");
            		url =  httpLine1Split[1];
            		//String version=
            		String folder=url+"."+format;
            		
            		
            		
            		
            		
            		
                    String status="";
                    int length=0;
            		File[] listOfFiles = Server.docroot.listFiles();
            		FileInputStream fis = null;
            	    BufferedInputStream bis = null;
            	    byte[] bFile= null;
            	    InputStream in = null;
            	    byte[] bytes = null;
            	    int length1=0;
            		for (int i = 0; i < listOfFiles.length; i++) {
            		      if (listOfFiles[i].isFile()) {
            		        if(folder.equals(listOfFiles[i].getName())){
            		        	status="200 OK";
            		        	 //System.out.println("here");
            		        	File myFile = new File (folder);
            		            length  = (int)myFile.length();
//            		            fis = new FileInputStream(myFile);
//            		            bis = new BufferedInputStream(fis);
//            		            bis.read(mybytearray,0,mybytearray.length);
//            		            System.out.println("here");
            		        	//bFile =  readBytesFromFile("/Users/Maro31/Desktop/Chateau/docroot/"+folder);
            		            FileInputStream file= new FileInputStream("/Users/Maro31/Desktop/Chateau/docroot/"+folder);
            		            File filesize = new File("/Users/Maro31/Desktop/Chateau/docroot/"+folder); 
            		            // Get the size of the file
            		            length1 = (int) filesize.length();
            		            bytes = new byte[length1];
            		            file.read(bytes, 0, bytes.length);
            		            
                                break;
            		           
            		            
            		           
            		        }
            		        else{
            		        	
            		        	status="404 NOT FOUND";
            		        	
            		        }
            		        
            		        }
            		      }
            		
            		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					//Date date = new Date();
            		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            		   LocalDateTime now = LocalDateTime.now();  
            		   //System.out.println(dtf.format(now));  
				      String time = dtf.format(now);
            		String response="Status:"+status+" "+"Version:"+"4"+"\n"+"timestamp:"+time+"\n"+"format:"+format+"\n"+"connection:"+connection;
            		
            		String requested="";
            		requested=Server.requests.poll();
            		
            		String [] response2 = requested.split(",");
            		 for (chatting mc : Server.clients) 
                     {
            			 
            			 if(mc.name.equals(response2[0])){
            				 
            					

            				 mc.dos.writeUTF(response);
            				 if(status.equals("200 OK")){
            				 mc.dos.writeUTF(length1+"");
            				 mc.dos.writeUTF(format);
            				 mc.dos.writeUTF(folder);
            				 mc.dos.writeUTF(mc.name);
            				//OutputStream os = mc.s.getOutputStream();
            				 //os.write(bytes, 0, bytes.length); 
            				 mc.dos.write(bytes, 0, bytes.length);}
            				 
            				 if(connection.equals("close")){
            					 mc.s.close();
            				 }
            			 }
                     }
            		 }}
                else{this.dos.writeUTF("please specify your name");}
                
                
                
                
                
                	
                
                
            } catch (IOException e) {
                 
                
            }
             
        }
        try
        {
            
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
           
        }
    }
}
