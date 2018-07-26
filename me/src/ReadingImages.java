import org.opencv.core.Core; 
import org.opencv.core.Mat;  
import org.opencv.imgcodecs.Imgcodecs;
 
public class ReadingImages {
   public static void main(String args[]) { 
      //Loading the OpenCV core library  
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); 
     
      //Instantiating the Imagecodecs class 
      Imgcodecs imageCodecs = new Imgcodecs(); 
     
      //Reading the Image from the file  
      String file ="E:/photo/abc.jpg"; 
      Mat matrix = imageCodecs.imread(file); 
     
      System.out.println("Image Loaded......");     
      String file2 = "H:/Wifi/aa.jpg";
      //Writing the image 
      imageCodecs.imwrite(file2,matrix);
      System.out.println("Image Saved ............");
            }
}