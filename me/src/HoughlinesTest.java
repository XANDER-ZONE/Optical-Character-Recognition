import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class HoughlinesTest {
   public static void main(String args[]) throws Exception {
      // Loading the OpenCV core library
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

      // Reading the Image from the file and storing it in to a Matrix object
      String file = "E:/photo/CHAR1007.jpg";

      // Reading the image
      Mat src = Imgcodecs.imread(file,0);

      // Detecting edges of it
      Mat canny = new Mat();
      Imgproc.Canny(src, canny, 50, 200, 3, false);

      // Changing the color of the canny
      Mat cannyColor = new Mat();
      Imgproc.cvtColor(canny, cannyColor, Imgproc.COLOR_GRAY2BGR);

      // Detecting the hough lines from (canny)
      Mat lines = new Mat();
      Imgproc.HoughLines(canny, lines, 1, Math.PI/180, 100);

      System.out.println(lines.rows());
      System.out.println(lines.cols());
/*
      // Drawing lines on the image
      double[] data;
      double rho, theta;
      Point pt1 = new Point();
      Point pt2 = new Point();
      double a, b;
      double x0, y0;
      
      for (int i = 0; i < lines.cols(); i++) {
         data = lines.get(0, i);
         rho = data[0];
         theta = data[1];
         
         a = Math.cos(theta);
         b = Math.sin(theta);
         x0 = a*rho;
         y0 = b*rho;
         
         pt1.x = Math.round(x0 + 1000*(-b));
         pt1.y = Math.round(y0 + 1000*(a));
         pt2.x = Math.round(x0 - 1000*(-b));
         pt2.y = Math.round(y0 - 1000 *(a));
         Imgproc.line(cannyColor, pt1, pt2, new Scalar(0, 0, 255), 6);
      }
      // Writing the image
      Imgcodecs.imwrite("E:/photo/hough_output.jpg", cannyColor);
          */
      System.out.println("Image Processed");
   }
}