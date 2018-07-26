
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
public class Skele2 {
    public static void main(String args[])throws Exception{
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        String input = "E:/photo/DAT'A'/CHAR47.jpg";
      //Reading the image
      Mat src = Imgcodecs.imread(input);      //Creating the empty destination matrix
      Mat dst = new Mat();
      //Converting the image to gray sacle and saving it in the dst matrix
      Imgproc.cvtColor(src, dst,Imgproc.COLOR_RGB2GRAY);
      
        Mat re= dst.clone();
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
          }}
          System.out.println();
      }
              int change=1;
              Mat flag=dst.clone();
              
              while(change<4){
              int f=0,g=0;
              for(int i=0;i<dst.rows();i++){
                  for(int j=0;j<dst.cols();j++){
                      flag.put(i, j, 0);
                  }
              }
              
              // Step 1------------------------------
              
              for(int i=1;i<dst.rows()-1;i++){
                  for(int j=1;j<dst.cols()-1;j++){
                     int h1=0,h2=0,h3=0;
                   
                     double a1[]=re.get(i, j);
                     double a2[]=re.get(i-1, j);
                     double a3[]=re.get(i-1, j+1);
                     double a4[]=re.get(i, j+1);
                     double a5[]=re.get(i+1, j+1);
                     double a6[]=re.get(i+1, j);
                     double a7[]=re.get(i+1, j-1);
                     double a8[]=re.get(i, j-1);
                     double a9[]=re.get(i-1, j-1);
                     
                     double b=a2[0]+a3[0]+a4[0]+a5[0]+a6[0]+a7[0]+a8[0]+a9[0];
                     if(b>=2&&b<=6) h1=1;
                     
                     double a=0;
                     if(a2[0]-a3[0]==-1)  a++;
                     if(a3[0]-a4[0]==-1)  a++;
                     if(a4[0]-a5[0]==-1)  a++;
                     if(a5[0]-a6[0]==-1)  a++;
                     if(a6[0]-a7[0]==-1)  a++;
                     if(a7[0]-a8[0]==-1)  a++;
                     if(a8[0]-a9[0]==-1)  a++;
                     
                     if(a==1) h2=1;
                     
                     if((a2[0]*a4[0]*a6[0]==0) && (a4[0]*a6[0]*a8[0]==0))
                         h3=1;
                        
                     if(h1*h2*h3==1){
                         f++;
                         flag.put(i, j, 1);
                     }
                             
                  }              
              }
              
              // Step 2---------------------------------------
              for(int i=1;i<dst.rows()-1;i++){
                  for(int j=1;j<dst.cols()-1;j++){
                      double a[]=flag.get(i, j);
                      if(a[0]==1){
                          re.put(i, j, 0);
                      }
                  }
                  }
              
              //Step 3--------------------------------------------
                            
              for(int i=0;i<dst.rows();i++){
                  for(int j=0;j<dst.cols();j++){
                      flag.put(i, j, 0);
                  }}
              
              for(int i=1;i<dst.rows()-1;i++){
                  for(int j=1;j<dst.cols()-1;j++){
                     int h1=0,h2=0,h3=0;
                   
                     double a1[]=re.get(i, j);
                     double a2[]=re.get(i-1, j);
                     double a3[]=re.get(i-1, j+1);
                     double a4[]=re.get(i, j+1);
                     double a5[]=re.get(i+1, j+1);
                     double a6[]=re.get(i+1, j);
                     double a7[]=re.get(i+1, j-1);
                     double a8[]=re.get(i, j-1);
                     double a9[]=re.get(i-1, j-1);
                     
                     double b=a2[0]+a3[0]+a4[0]+a5[0]+a6[0]+a7[0]+a8[0]+a9[0];
                     if(b>=2&&b<=6) h1=1;
                     
                     double a=0;
                     if(a2[0]-a3[0]==-1)  a++;
                     if(a3[0]-a4[0]==-1)  a++;
                     if(a4[0]-a5[0]==-1)  a++;
                     if(a5[0]-a6[0]==-1)  a++;
                     if(a6[0]-a7[0]==-1)  a++;
                     if(a7[0]-a8[0]==-1)  a++;
                     if(a8[0]-a9[0]==-1)  a++;
                     
                     if(a==1) h2=1;
                     
                     if((a2[0]*a4[0]*a8[0]==0) && (a2[0]*a6[0]*a8[0]==0))
                         h3=1;
                        
                     if(h1*h2*h3==1){
                         g++;
                         flag.put(i, j, 1);
                     }
                             
                  }              
              }
              //Step 4--------------------------------------------------------
                  for(int i=1;i<dst.rows()-1;i++){
                  for(int j=1;j<dst.cols()-1;j++){
                      double a[]=flag.get(i, j);
                      if(a[0]==1){
                          re.put(i, j, 0);
                      }
                  }
                  }
                  
                  //Show Matrix
                  for(int i=0;i<dst.rows();i++){
                  for(int j=0;j<dst.cols();j++){
                      double a[]=flag.get(i, j);
                      if(a[0]==1){
                          System.out.print("1");
                          }
                      else{
                          System.out.print(" ");
                      }
                  }
                  System.out.println();
                  }
                  
                  
                 /* if(f>0 || g>0){
                      change=1;
                      f=0;
                      g=0;
                  }
                  else{
                      change=0;
                  }
                  */
                 
                 String st="E:/photo/CHAR"+(52+change)+".jpg";
                 Imgcodecs.imwrite(st, re);
                  change++;
              }
                            
                          
    }
}
