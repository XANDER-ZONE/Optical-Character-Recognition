
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Size;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xander
 */
public class Skele3 {
    public static void main(String args[])throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
 int op=0;
 String input = "E:/photo/CHAR1.jpg";
        Mat src = Imgcodecs.imread(input);
        Mat img=src.clone();
        Imgproc.cvtColor(src, img,Imgproc.COLOR_RGB2GRAY);
        Mat s=img.clone();
        for(int i=0;i<img.rows();i++){
            for(int j=0;j<img.cols();j++){
                double r[]=img.get(i, j);
                img.put(i, j, 255-r[0]);
        }
        
    }
        Imgcodecs.imwrite("E:/photo/SKELETON908.jpg", s);
        Imgproc.threshold(img, img, 150, 255,Imgproc.THRESH_BINARY);
        Imgcodecs.imwrite("E:/photo/SKELETON900.jpg", img);
        Mat element=Imgproc.getStructuringElement(Imgproc.MORPH_CROSS,new Size(3,3));
      //Creating the empty destination matrix
      Boolean done=false;
      Size x=img.size();
      //Converting the image to gray sacle and saving it in the dst matrix
      
      //Extracting data from the transformed image (dst)
      Mat skel=Mat.zeros(img.size(), CvType.CV_8UC1);
      Mat temp=new Mat();
      //Mat re= dst.clone();
      Mat erode=new Mat();
      int k=0;
      Mat sop=skel.clone();
      do{
          k++;
        Imgproc.erode(img, erode, element);
        Imgproc.dilate(erode, temp, element);
        Core.subtract(img, temp, temp);
        Core.bitwise_or(skel, temp, skel);
        erode.copyTo(img);
        
        done=(Core.countNonZero(img)==0);
        
              for(int i=0;i<skel.rows();i++){
            for(int j=0;j<skel.cols();j++){
                double r[]=skel.get(i, j);
                sop.put(i, j, 255-r[0]);
        }}
    Imgcodecs.imwrite("E:/photo/pop7/CHAR"+k+".jpg", sop);
    }while(!done);
      
      Imgcodecs.imwrite("E:/photo/SKELETON501.jpg", skel);
      for(int i=0;i<skel.rows();i++){
            for(int j=0;j<skel.cols();j++){
                double r[]=skel.get(i, j);
                skel.put(i, j, 255-r[0]);
        }
        
    }
      Imgcodecs.imwrite("E:/photo/SKELETON507.jpg", skel);
    
    }
}
