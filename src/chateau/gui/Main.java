package chateau.gui;




import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import networking.messageReceived;
import networking.messageSent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Font;

public class Main extends JFrame implements WritableGUI{

	
	private JPanel contentPane;
	public JTextField textFieldPortNumber;
	public JTextField textFieldSend;
	public JTextArea Chat;
    public DataOutput sent ;
    public Socket s;
    public  DataInputStream input;
    public String loc="";
    public int port;
	/**
	 * Launch the application.
	 */
    
    
    
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void draw(){
		
					Main frame = new Main();
					frame.setVisible(true);
				
			
		
	}
	
	@Override
	public void write(String s) {
		// TODO Auto-generated method stub
		Chat.append(s + System.lineSeparator());
		
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		textFieldPortNumber = new JTextField();
		textFieldPortNumber.setText("Server Port Number");
		textFieldPortNumber.setBounds(10, 11, 151, 20);
		contentPane.add(textFieldPortNumber);
		textFieldPortNumber.setColumns(10);
		
		JButton connect = new JButton("Connect");
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  loc="";
				try {
					loc = InetAddress.getLocalHost().getHostName();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			     port = Integer.parseInt(textFieldPortNumber.getText());
			     try {
			    	 if(!(loc.equals(""))){
					 s = new Socket(loc, port);
					  input = new DataInputStream(s.getInputStream());
				     DataOutputStream output = new DataOutputStream(s.getOutputStream());
					 sent = output;
//					 
//					 
					 write("Connection Successful...");
					 write("please specify your name using this format join(your name here)");
					 
					 messageReceived receiving = new messageReceived(input ,Chat, null);
				        
				        Thread readingMessage = new Thread(receiving);
				 
				        //sendingMessage.start();
				        readingMessage.start();


					 
					 
			    	 }} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		connect.setBounds(173, 11, 105, 23);
		contentPane.add(connect);
		
		JButton Disconnect = new JButton("Disconnect");
		Disconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Disconnect.setBounds(349, 11, 105, 23);
		contentPane.add(Disconnect);
		
		textFieldSend = new JTextField();
		textFieldSend.setBounds(10, 504, 476, 33);
		contentPane.add(textFieldSend);
		textFieldSend.setColumns(10);
		
		 Chat = new JTextArea();
		Chat.setText("Please enter your designed server port (Server1:6790)");
		Chat.setBounds(10, 46, 476, 446);
		contentPane.add(Chat);
		
		JButton SendButton = new JButton("Send");
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent(textFieldSend.getText(), sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
			}
		});
		SendButton.setBounds(6, 538, 105, 33);
		contentPane.add(SendButton);
		
		JButton text1 = new JButton("Text1");
		text1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET main1 4,web,txt,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		text1.setBounds(137, 540, 117, 29);
		contentPane.add(text1);
		
		JButton text2 = new JButton("Text2");
		text2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET main2 4,web,txt,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		text2.setBounds(262, 540, 117, 29);
		contentPane.add(text2);
		
		JButton text3 = new JButton("Text3");
		text3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET main3 4,web,txt,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		
		text3.setBounds(137, 566, 117, 29);
		contentPane.add(text3);
		
		JButton text4 = new JButton("Text4");
		text4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET main4 4,web,txt,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		text4.setBounds(262, 566, 117, 29);
		contentPane.add(text4);
		
		JButton jpeg1 = new JButton("JPEG1");
		jpeg1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET test1 4,web,jpg,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		jpeg1.setBounds(137, 592, 117, 29);
		contentPane.add(jpeg1);
		
		JButton jpeg2 = new JButton("JPEG2");
		jpeg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET test2 4,web,jpg,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		jpeg2.setBounds(262, 592, 117, 29);
		contentPane.add(jpeg2);
		
		JButton png1 = new JButton("PNG1");
		png1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET png1 4,web,png,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		png1.setBounds(137, 619, 117, 29);
		contentPane.add(png1);
		
		JButton png2 = new JButton("PNG2");
		png2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET png2 4,web,png,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		png2.setBounds(262, 619, 117, 29);
		contentPane.add(png2);
		
		JButton mp1 = new JButton("MP4(1)");
		mp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET mp1 4,web,mp4,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		mp1.setBounds(137, 644, 117, 29);
		contentPane.add(mp1);
		
		JButton mp2 = new JButton("MP4(2)");
		mp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				messageSent sending = new messageSent("GET mp2 4,web,mp4,keep-alive", sent);
		        Thread sendingMessage = new Thread(sending);
		        sendingMessage.start();
		        
			}
		});
		mp2.setBounds(262, 644, 117, 29);
		contentPane.add(mp2);
		
		
		
		
		
		
		
		
		
	}
}
