package networking;
import java.io.DataOutput;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class messageSent implements Runnable {
	
	String message ;
	DataOutput sent ;
	
	

	
	

	
	public messageSent(String string, DataOutput sent ) {
		super();
		this.message = string;
		this.sent = sent;
		
		
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		 //while (true) {
			             // read the message to deliver.
             //String message = consoleWindow.nextLine();
               
             
             try {
                 // write on the output stream
            	 //System.out.println(message);
            	 //String request= this.name+","+message;
         		//Server.requests.add(request);
         		//System.out.println(Server.requests.peek());
            	 if(message.contains("join")){}else{
         		String url="";
         		String httpLine1 ="";
         		String httpLine2 ="";
         		String format ="";
         		String connection ="";
         		
         		String [] x = message.split(",");
         		httpLine1 =x[0];
         		httpLine2 =x[1];
         		format =x[2];
         		connection =x[3];
         		String[] httpLine1Split = httpLine1.split("\\s+");
         		url =  httpLine1Split[1];
         		String method = httpLine1Split[0];
         		
         		String version = httpLine1Split[2];
         		
         		String hostname = httpLine2 =x[1];
         		
         		String total = "Method:"+ method +" "+"URL:"+url+" "+"version:"+version+'\n'+"Hostname:"+hostname+'\n'+"Format:"+format+'\n'+"connection:"+connection;
         		System.out.println(total);}
                 sent.writeUTF(message);
                 
             } catch (IOException e) {
                 e.printStackTrace();
             }
         //}
	}

}
