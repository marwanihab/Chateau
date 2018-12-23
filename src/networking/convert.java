package networking;



import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.imageio.ImageIO;

public class convert {

    public static void main(String[] args) {

        try {

            // convert file to byte[]
            byte[] bFile = readBytesFromFile("/Users/Maro31/Desktop/Chateau/docroot/test.jpg");

            //java nio
            //byte[] bFile = Files.readAllBytes(new File("C:\\temp\\testing1.txt").toPath());
            //byte[] bFile = Files.readAllBytes(Paths.get("C:\\temp\\testing1.txt"));

            // save byte[] into a file
            //Path path = Paths.get("/Users/Maro31/Desktop/Chateau/docroot/test2.jpg");
            //Files.write(path, bFile);
            InputStream in = new ByteArrayInputStream(bFile);
            //ImageIO.read(in);
            BufferedImage img = ImageIO.read(in);
            
        		JFrame frame=new JFrame();
        		frame.setLayout(new FlowLayout()); 
        		frame.setSize(1000,1000);
        		JLabel lbl=new JLabel("Output Image"); 
        		frame.getContentPane().add(new JLabel(new ImageIcon(img))); 
        		frame.setVisible(true); 
        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		
            
            System.out.println("Done");

            //Print bytes[]
//            for (int i = 0; i < bFile.length; i++) {
//                System.out.print((char) bFile[i]);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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

}