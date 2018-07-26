/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 *
 * @author Xander
 */
public class ABCD1 {
    public static void main(String args[])throws IOException{
       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
       System.out.println("NAME   PART1   PART2   PART3");
       for(int jj=1;jj<22;jj++){
           String ax=String.valueOf(jj);
           System.out.print(ax+"::");
             String input = "E:/photo/ABBBB/SKELE/SKELE"+ax+".jpg";

      Mat src = Imgcodecs.imread(input);
      Mat dst=new Mat();
     Imgproc.cvtColor(src,dst,Imgproc.COLOR_RGB2GRAY);
     //System.out.println("ROWS :"+dst.rows());
     int m=dst.rows()/2;
     //System.out.println("m="+m);
     //System.out.println("COLOUMS :"+dst.cols());
     int n=dst.cols()/3;
     //System.out.println("n="+n);
     Mat re=dst.clone();
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
           int aa[][]=new int[2][4];
           for(int i=0;i<2;i++){
               for(int j=0;j<4;j++){
                   aa[i][j]=0;
               }
           }
           

           
           for(int i=0;i<re.rows();i++){
               for(int j=0;j<re.cols();j++){
                   double b[]=re.get(i, j);
                   int x;
                   if(b[0]==1){
                      if(j<=n)                 aa[0][0]++;
                      else if(j>n&&j<=2*n)     aa[0][1]++;
                      else if(j>2*n&&j<=3*n)     aa[0][2]++;
                      
                   }
                   else{
                      if(j<=n)                 aa[1][0]++;
                      else if(j>n&&j<=2*n)     aa[1][1]++;
                      else if(j>2*n&&j<=3*n)     aa[1][2]++;
                   }
               }
           }
           
           
                
               for(int j=0;j<3;j++){
                   if(aa[1][j]==0)  aa[1][j]=1;
                   if(aa[0][j]==0)  aa[0][j]=1;
                   double p=(double)aa[1][j]/aa[0][j];
                   //p=Math.round(p);
                   System.out.print(p+"    ");
               }
               System.out.println();
           
     
    }
}}
