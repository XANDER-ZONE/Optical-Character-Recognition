/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import org.opencv.core.*;

/**
 *
 * @author Xander
 */
public class test {
    public static void main(String args[])throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m=new Mat(5, 5, CvType.CV_8UC1, new Scalar(0));
        
        System.out.println("Initial Matrix :\n"+m.dump());
               
        m.row(0).setTo(new Scalar(1));
        m.col(4).setTo(new Scalar(9));
       System.out.println(m.get(1, 4));
        System.out.println("Final Matrix : \n"+m.dump());
    }
}
