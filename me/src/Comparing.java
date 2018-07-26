
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
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

public class Comparing {
    
    public static void resize(String inputImagePath,String outputImagePath, int scaledWidth, int scaledHeight)throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
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
    
    
      public static void main(String args[])throws IOException{
       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
       String in="E:/photo/test/CHAR49.jpg";
       String out="E:/photo/test/check.jpg";
   
       resize(in,out,400,800);
       Mat src1=Imgcodecs.imread(out);
       Mat dst1=new Mat();
       Imgproc.cvtColor(src1, dst1, Imgproc.COLOR_BGR2GRAY);
       Mat pe=dst1.clone();
           for(int i=0;i<dst1.rows();i++){
              for(int j=0;j<dst1.cols();j++){
                    double a[]=dst1.get(i, j);      
                            if(a[0]<110){          
                                     a[0]=1;
                                     pe.put(i, j, a);
                                        }
                             else{
                                  a[0]=0;
                                  pe.put(i, j, a);
          
      }
          }
          
           }
           
           double a1=0, black1=0;
           for(int i=0;i<pe.rows();i++){
               for(int j=0;j<pe.cols();j++){
                   double m[]=pe.get(i, j);
                   double n[]=pe.get(i, j);
                   if(m[0]==n[0])   a1++;
                   
                   if(m[0]==1 && n[0]==1)   black1++;
                   
               }
           }
       
       
       for(int jj=49;jj<56;jj++){
           String ax="CHAR"+String.valueOf(jj);
           String bx="CHAR"+String.valueOf(jj+10);
           System.out.print(ax+"::");
             String input = "E:/photo/test/"+ax+".jpg";
             String output="E:/photo/test/"+bx+".jpg";
             
             resize(input,output,400,800);
             
             Mat src = Imgcodecs.imread(output);
             Mat dst=new Mat();
             Imgproc.cvtColor(src,dst,Imgproc.COLOR_RGB2GRAY);
             
             
              Mat re=dst.clone();
     
           for(int i=0;i<dst.rows();i++){
              for(int j=0;j<dst.cols();j++){
                    double a[]=dst.get(i, j);      
                            if(a[0]<110){          
                                     a[0]=1;
                                     re.put(i, j, a);
                                        }
                             else{
                                  a[0]=0;
                                  re.put(i, j, a);
          
      }
          }
           }
           double a=0, black=0;
           for(int i=0;i<dst.rows();i++){
               for(int j=0;j<dst.cols();j++){
                   double m[]=re.get(i, j);
                   double n[]=pe.get(i, j);
                   if(m[0]==n[0])   a++;
                   
                   if(m[0]==1 && n[0]==1)   black++;
                   
               }
           }
           
           System.out.println("For image "+jj+" :Similarity :"+((a/a1)*100)+"%  Black Difference :"+((black/black1)*100)+"%");
           
             
           
             
       }

       
        }
}
