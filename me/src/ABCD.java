/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 *
 * @author Xander
 */
public class ABCD {
    
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
       System.out.println("NAME   PART1   PART2   PART3   PART4");
       for(int jj=1;jj<=4;jj++){
           String ax="SKELE"+String.valueOf(jj);
           System.out.print(ax+"::");
             String input = "E:/photo/ADDDD/SKELE/"+ax+".jpg";
             resize(input,input,400,800);

      Mat src = Imgcodecs.imread(input);
      Mat dst=new Mat();
     Imgproc.cvtColor(src,dst,Imgproc.COLOR_RGB2GRAY);
     //System.out.println("ROWS :"+dst.rows());
     int m=dst.rows()/2;
     //System.out.println("m="+m);
     //System.out.println("COLOUMS :"+dst.cols());
     int n=dst.cols()/2;
     //System.out.println("n="+n);
     Mat re=dst.clone();
     Mat pe=dst.clone();
     
           for(int i=0;i<dst.rows();i++){
          for(int j=0;j<dst.cols();j++){
      double a[]=dst.get(i, j);      
      if(a[0]<110){
          //System.out.print("1");
          a[0]=1;
          re.put(i, j, a);
      }
      else{
          a[0]=0;
          re.put(i, j, a);
          //System.out.print(" ");
      }
          }
          //System.out.println();
           }
         /*  
          for(int i=0;i<re.rows();i++){
          for(int j=0;j<re.cols();j++){
          double p[]=re.get(i,j);
          if(p[0]==1)   System.out.print("1");
          else          System.out.print(" ");
          }
          System.out.println();
         }
           */
           //---------------------------------------------------
          for(int i=0;i<re.rows();i++){
          for(int j=0;j<re.cols();j++){
              double r[]=re.get(i, j);
              try{
              double r1[]=re.get(i, j-1);
              double r2[]=re.get(i, j+1);
              double r3[]=re.get(i-1, j);
              double r4[]=re.get(i+1, j);
              if(r[0]==1 && r1[0]*r2[0]*r3[0]*r4[0]==0){
                r[0]=1;
              pe.put(i,j,r);   }
              else{
                r[0]=0;
                pe.put(i,j,r);
              }
          }
              catch(Exception e){}
          }
          //System.out.println();
      }
           
         //-------------------------------------------------------------------
         /*
         for(int i=0;i<pe.rows();i++){
          for(int j=0;j<pe.cols();j++){
          double p[]=pe.get(i,j);
          if(p[0]==1)   System.out.print("1");
          else          System.out.print(" ");
          }
          System.out.println();
         }
         */
           double aa[][]=new double[2][4];
           for(int i=0;i<2;i++){
               for(int j=0;j<4;j++){
                   aa[i][j]=0;
               }
           }       

           
           for(int i=0;i<pe.rows();i++){
               for(int j=0;j<pe.cols();j++){
                   double b[]=re.get(i, j);
                   int x;
                   if(b[0]==1){
                      if(i<=m&&j<=n)      aa[0][0]++;
                      else if(i>m&&j<=n)  aa[0][1]++;
                      else if(i<=m&&j>n)  aa[0][2]++;
                      else if(i>m&&j>n)   aa[0][3]++;
                   }
                   else{
                      if(i<=m&&j<=n)      aa[1][0]++;
                      else if(i>m&&j<=n)  aa[1][1]++;
                      else if(i<=m&&j>n)  aa[1][2]++;
                      else if(i>m&&j>n)   aa[1][3]++;
                   }
               }
           }
           
           
                
               for(int j=0;j<4;j++){
                   if(aa[1][j]==0)  aa[1][j]=0.5;
                   if(aa[0][j]==0)  aa[0][j]=0.5;
                   double p=(double)aa[1][j]/aa[0][j];
                   //p=Math.round(p);
                   System.out.print(p+"    ");
               }
               System.out.println();
           
     
    }
}}
