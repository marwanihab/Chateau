package networking;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import chateau.gui.Main;
import chateau.gui.WritableGUI;
import chateau.gui.WritableGUI;


public class messageReceived implements Runnable {
	
	

	DataInputStream received;
	JTextArea chat;
	JTextArea members;
	//FileOutputStream file = null;
	BufferedOutputStream fileo= null;
	
	
	
	
	public messageReceived(DataInputStream received, JTextArea chat ,JTextArea members ) {
		super();
		this.received = received;
		this.chat = chat;
		this.members = members;
		
	}
	
//	public void messageReceived1(DataInputStream received, JTextArea members ) {
//		
//		this.received = received;
//		this.members = members;
//		
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
            try {
            	
                // read the message sent to this client
                String message = received.readUTF();
                chat.append(message + System.lineSeparator());
                if(message.contains("200")){
                	
                	 String size = received.readUTF();
                	 String format = received.readUTF();
                	 String nameOfFile = received.readUTF();
                	 String path = received.readUTF();
                	 String fullPath = "/Users/Maro31/Desktop/clients/"+path+"/";
                	 //File ne = new File(fullPath+nameOfFile);
                	 
                	int sizeint = Integer.parseInt(size);
                	byte[] b = new byte[sizeint];
                	 received.read(b, 0, b.length);
                	 
                	if((format.equals("jpg"))||(format.equals("png"))){
                		
                		//FileOutputStream file = new FileOutputStream("/Users/Maro31/Desktop/mmmmm.txt");
//                		 FileOutputStream fileoutputstream = new FileOutputStream(ne);
//             	        fileoutputstream.write(b);   
//             	       fileoutputstream.close();
                		FileOutputStream fileoutputstream = new FileOutputStream(fullPath+nameOfFile);
             	        fileoutputstream.write(b, 0, b.length);
             	       
                   	     InputStream in = new ByteArrayInputStream(b);
                     //ImageIO.read(in);
                        BufferedImage img = ImageIO.read(in);
                     
                 		JFrame frame=new JFrame();
                 		frame.setLayout(new FlowLayout()); 
                 		frame.setSize(1000,1000);
                 		JLabel lbl=new JLabel("Output Image"); 
                 		frame.getContentPane().add(new JLabel(new ImageIcon(img))); 
                 		frame.setVisible(true); 
                 		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 		
                     
                       System.out.println("Done");   
                	}else{
                	if((format.equals("mp4"))){
                		
                		try {


                			FileOutputStream fileoutputstream = new FileOutputStream(fullPath+nameOfFile);
                			fileoutputstream.write(b, 0, b.length); 
                	        Desktop.getDesktop().open(new File(fullPath+nameOfFile));
                	        System.out.println("Done video"); 
                           
                	    } catch (IOException ex) {
                	        ex.printStackTrace();
                	    }
                	}
                		
                		else{
                	
                			FileOutputStream fileoutputstream = new FileOutputStream(fullPath+nameOfFile);
                 	        fileoutputstream.write(b, 0, b.length);
                 	        
                	 JFrame frame=new JFrame();
                	 JTextArea text= new JTextArea();
              		frame.setLayout(new FlowLayout()); 
              		frame.setSize(1000,1000);
              		frame.setVisible(true); 
             		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             		frame.add(text);
              		JLabel lbl=new JLabel("Text"); 
              		String s = "";
                	 for (int i = 0; i < b.length; i++) {
                       //System.out.print((char) b[i]);
                		 s=s+(char) b[i];}
                				 
                				 
                       text.append(s );
                 }
                	 //file.write(b, 0, b.length);      
                	}
                	}
                	
               
                	
//                if(message.contains("members")){
//                	members.append(message + System.lineSeparator());
//                }else{
//                	
//                //chatWindow.write(message);
//                 chat.append(message + System.lineSeparator());
//                //System.out.println(message);
//                }
                
                
                
                
            } catch (IOException e) {

               
            }
        }
	}

}
