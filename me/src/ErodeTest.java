import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ErodeTest {
   public static void main( String[] args ) {
      // Loading the OpenCV core library
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

      // Reading the Image from the file and storing it in to a Matrix object
      String file ="E:/photo/CHAR1.jpg";
      Mat src = Imgcodecs.imread(file);

      // Creating an empty matrix to store the result
      Mat dst = new Mat();

      // Preparing the kernel matrix object
      Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, 
         new  Size((2*2) + 1, (2*2)+1));

      // Applying erode on the Image
      Imgproc.erode(src, dst, kernel);

      // Writing the image
      Imgcodecs.imwrite("E:/photo/Erosion.jpg", dst);

      System.out.println("Image processed");
   }
}