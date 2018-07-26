
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xander
 */
public class Skele1 {
    public static void main(String args[])throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
 int op=0;
 String input = "E:/photo/CHAR1.jpg";
      while(true){
      //Reading the image
      Mat src = Imgcodecs.imread(input);

      //Creating the empty destination matrix
      Mat dst = new Mat();
      //Converting the image to gray sacle and saving it in the dst matrix
      Imgproc.cvtColor(src, dst,Imgproc.COLOR_RGB2GRAY);
      //Extracting data from the transformed image (dst)
      Mat re= dst.clone();
     //System.out.println("After Denoising :");
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
    
      op++;
      String output="E:/photo/pop8"
              + ""
              + "/CHAR"+op+".jpg";
      Mat pe=re.clone();
      for(int i=0;i<re.rows();i++){
          for(int j=0;j<re.cols();j++){
              double r[]=re.get(i, j);
              try{
                  
                  double a[]=dst.get(i, j);
              double r1[]=re.get(i, j-1);
              double r2[]=re.get(i, j+1);
              double r3[]=re.get(i-1, j);
              double r4[]=re.get(i+1, j);
             double r5[]=re.get(i-1, j-1);
             double r6[]=re.get(i-1, j+1);
             double r7[]=re.get(i+1, j-1);
             double r8[]=re.get(i+1, j+1);
              if(r[0]==1 && r1[0]*r2[0]*r3[0]*r4[0]==0){
                  System.out.print("1");
                pe.put(i, j, (a[0]*1));
                  if(r1[0]+r2[0]>0 && r1[0]+r2[0]<=2 && r3[0]==0 && r4[0]==0){
                      pe.put(i, j, 255);}
                  if(r3[0]+r4[0]>0 && r3[0]+r4[0]<=2 && r1[0]==0 && r2[0]==0){
                      pe.put(i,j,255);}
                  if(r1[0]==0 && r2[0]==0 && r3[0]==0 && r4[0]==0){
                      if((r5[0]+r8[0]>0 && r5[0]+r8[0]<=2) || (r6[0]+r7[0]>0 && r6[0]+r7[0]<=2) )
                          pe.put(i,j,255);
                  }
   
              }
              else{
                System.out.print(" ");
                pe.put(i, j, 255);
              }
          }
              catch(Exception e){}
          }
          System.out.println();
      }
      Mat d=re.clone();
      for(int i=0;i<re.rows();i++){
          for(int j=0;j<re.cols();j++){
              double r[]=dst.get(i, j);
              double t[]=pe.get(i, j);
              if(t[0]==255){
              d.put(i, j, r[0]);}
              else{
                  d.put(i,j,235);
              }
               
          }
      }
      
      
     input=output;
     
     //----Restoring Image-------
     
     for(int i=2;i<re.rows()-2;i++){
         for(int j=2;j<re.cols()-2;j++){
             double r[]=d.get(i, j);
           if(r[0]==50){
               double a1[]=d.get(i-2, j-2);
               double a2[]=d.get(i-2, j-1);
               double a3[]=d.get(i-2, j);
               double a4[]=d.get(i-2, j+1);
               double a5[]=d.get(i-2, j+2);
               double a6[]=d.get(i-1, j+2);
               double a7[]=d.get(i, j+2);
               double a8[]=d.get(i+1, j+2);
               double a9[]=d.get(i+2, j+2);
               double a10[]=d.get(i+2, j+1);
               double a11[]=d.get(i+2, j);
               double a12[]=d.get(i+2, j-1);
               double a13[]=d.get(i+2, j-2);
               double a14[]=d.get(i+1, j-2);
               double a15[]=d.get(i, j-2);
               double a16[]=d.get(i-1, j-2);
               
               double x1[]=d.get(i-1, j-1);
               double x2[]=d.get(i-1, j);
               double x3[]=d.get(i-1, j+1);
               double x4[]=d.get(i, j+1);
               double x5[]=d.get(i+1, j+1);
               double x6[]=d.get(i+1, j);
               double x7[]=d.get(i+1, j-1);
               double x8[]=d.get(i, j-1);
               if((a1[0]<=120 ||a16[0]<=120 ||a2[0]<=120) && x1[0]==235 )   d.put(i-1,j-1,50);
               if(a3[0]<=120 && x2[0]==235)                            d.put(i-1,j,50);
               if(a4[0]<=120 ||a5[0]<=120 ||a6[0]<=120 && x3[0]==235)    d.put(i-1, j+1, 50);
               if(a7[0]<=120 && x4[0]==235)                            d.put(i,j+1,50);
               if(a8[0]<=120 ||a9[0]<=120 ||a10[0]<=120 && x5[0]==235)   d.put(i+1,j+1,50);
               if(a11[0]<=120 && x6[0]==235)                           d.put(i+1,j,50);
               if(a12[0]<=120 ||a13[0]<=120 ||a14[0]<=120 && x7[0]==235) d.put(i+1, j-1, 50);
               if(a15[0]<=120 && x8[0]==235)                           d.put(i,j-1,50);               
               
           }  
           
         }
     }
     
     
     
      Imgcodecs.imwrite(output, d);
      System.out.println("Image formed: Press enter");
      br.readLine();
      }
    }
    
}
