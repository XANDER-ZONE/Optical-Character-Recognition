import org.opencv.core.Core;
import org.opencv.core.Mat;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CannyEdegeDetection {
   public static void main(String args[]) throws Exception {
      // Loading the OpenCV core library
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

      // Reading the Image from the file and storing it in to a Matrix object
      String file = "E:/photo/CHAR47.jpg";

      // Reading the image
      Mat src = Imgcodecs.imread(file);

      // Creating an empty matrix to store the result
      Mat gray = new Mat();

      // Converting the image from color to Gray
      Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
      Mat edges = new Mat();

      // Detecting the edges
      Imgproc.Canny(gray, edges, 60, 60*3);

      // Writing the image
      Imgcodecs.imwrite("E:/photo/canny_output.jpg", edges);
      System.out.println("Image Loaded");
   } 
}