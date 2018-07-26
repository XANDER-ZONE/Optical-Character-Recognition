
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xander
 */
public class loadimg {
    public static void main(String args[])throws IOException{
        File input=new File("E:/photo/abc.jpg");
        BufferedImage im=ImageIO.read(input);
        
       File output=new File("H:/Wifi/aa.jpg");
       ImageIO.write(im, "jpg", output);
        
       System.out.println("Image Saved");
 // Here Image is read and written down by JSE LIbrary.....
    }
}
