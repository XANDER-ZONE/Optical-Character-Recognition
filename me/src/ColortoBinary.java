import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
  import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.*;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class ColortoBinary extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      WritableImage writableImage = loadAndConvert();
       
      // Setting the image view
      ImageView imageView = new ImageView(writableImage);

      // Setting the position of the image
      imageView.setX(10);
      imageView.setY(10);

      // setting the fit height and width of the image view
      imageView.setFitHeight(400);
      imageView.setFitWidth(600);

      // Setting the preserve ratio of the image view
      imageView.setPreserveRatio(true);

      // Creating a Group object
      Group root = new Group(imageView);

      // Creating a scene object
      Scene scene = new Scene(root, 600, 400);

      // Setting title to the Stage
      stage.setTitle("Colored to grayscale image");

      // Adding scene to the stage
      stage.setScene(scene);

      // Displaying the contents of the stage
      stage.show();
   } 
   public WritableImage loadAndConvert() throws Exception {
      //Loading the OpenCV core library
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

      String input = "E:/photo/CHAR1.jpg";
      resize(input,input,20,30);
      //Reading the image
      Mat src = Imgcodecs.imread(input);

      //Creating the empty destination matrix
      Mat dst = new Mat();
      //Converting the image to gray sacle and saving it in the dst matrix
      Imgproc.cvtColor(src, dst,Imgproc.COLOR_RGB2GRAY);
      //Extracting data from the transformed image (dst)
      Mat re= dst.clone();
     System.out.println("After Denoising :");
      //Photo.fastNlMeansDenoising(dst, dst);
      //Photo.fastNlMeansDenoising(dst, dst);
      // Denoising Procedure...
            
               
      for(int i=0;i<dst.rows();i++){
          for(int j=0;j<dst.cols();j++){
      double a[]=dst.get(i, j);
      if(a[0]<110){
          System.out.print("1");
          a[0]=1;
          re.put(i, j, a);
      }
      else{
          System.out.print(" ");
          a[0]=0;
          re.put(i, j, a);
          }
      
          }
          System.out.println();
      }
                        
            
      System.out.println(re.rows()+"-----------------------------"+re.cols());
      for(int i=0;i<re.rows();i++){
          for(int j=0;j<re.cols();j++){
              double r[]=re.get(i, j);
              if(r[0]==1)
                System.out.print(" ");
              else
                System.out.print("1");  
          }
          System.out.println();
      }
      
      int left=re.cols(),right=0,top=re.rows(),down=0,p=0,word=0,no=0;
      
      for(int j=0;j<re.cols();j++){ p=0;
          for(int i=0;i<re.rows();i++){
              double m[]=re.get(i, j);
              if(m[0]==1){
                  if(p==0){ word++;}
                  p++;  
                  left=Math.min(j, left);
                  right=Math.max(j, right);
                  top=Math.min(i, top);
                  down=Math.max(i, down);
              }
          } 
          if(p==0 && word>0 && left<right && top<down){ no++;
              System.out.println("WORD NO:"+no+" top:"+top+" down:"+down+" right:"+right+" left:"+left);
              Mat bc=new Mat();
              
              bc=dst.submat(top, down, left, right);
           Imgcodecs imageCodecs = new Imgcodecs(); 
           imageCodecs.imwrite("E:/photo/CHAR1000.jpg",bc);
            
              
              
              for(int k=top;k<=down;k++){
                  for(int l=left;l<=right;l++){
                      double r[]=re.get(k, l);
                      if(r[0]==1)
                          System.out.print("1");
                      else
                          System.out.print(" ");
                  }
                  System.out.println();
              }
              left=re.cols();
              right=0;
              top=re.rows();
              down=0;
          }
      }   
      
      
      byte[] data1 = new byte[dst.rows() * dst.cols() * (int)(dst.elemSize())];
      dst.get(0, 0, data1);
      
      //Creating Buffered image using the data
      BufferedImage bufImage = new BufferedImage(dst.cols(),dst.rows(), 
         BufferedImage.TYPE_BYTE_GRAY);
      //Setting the data elements to the image
      bufImage.getRaster().setDataElements(0, 0, dst.cols(), dst.rows(), data1);

      //Creating a WritableImage
      WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
      System.out.println("Converted to Grayscale");
      return writableImage;
   }
    public static void resize(String inputImagePath,String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
   
   
   public static void main(String args[]) throws Exception {
      launch(args);
   }
   
}