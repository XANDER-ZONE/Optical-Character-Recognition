
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Size;
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
public class Skele4 {
    public static void main(String args[])throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
 int op=0;
 for(int kpp=1;kpp<5;kpp++){
 String input = "E:/photo/ADDDD/CHAR"+kpp+".jpg";
        Mat src = Imgcodecs.imread(input);
        Mat img=src.clone();
        Imgproc.cvtColor(src, img,Imgproc.COLOR_RGB2GRAY);
        Mat s=img.clone();
        Mat y;
        int n=img.rows();
        int m=img.cols();
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                double a[]=img.get(i, j);
                if(a[0]<110){
                    //System.out.print("1");                   
                    s.put(i, j, 1);
                }
                else{
                    //System.out.print(" ");
                    s.put(i, j, 0);
            }}
            //System.out.println();
        }
        
       
        int pp=2;
        while(true){
            int p=0;
            
            // Implementation of B1 thinning slot..
            for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                        //System.out.println("Checking B1");
                    double x1[]=s.get(i-1,j-1);
                    double x2[]=s.get(i-1,j);
                    double x3[]=s.get(i-1,j+1);
                    double y1[]=s.get(i+1,j-1);
                    double y2[]=s.get(i+1,j);
                    double y3[]=s.get(i+1,j+1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }
            }
            
            //Implementation of B2 thinning slot..
            for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                        //System.out.println("Checking B2");
                    double x1[]=s.get(i,j+1);
                    double x2[]=s.get(i-1,j);
                    double x3[]=s.get(i-1,j+1);
                    double y1[]=s.get(i+1,j-1);
                    double y2[]=s.get(i+1,j);
                    double y3[]=s.get(i,j-1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }
            }
            
           //Implementation of B3 thinning slot..
              for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                   
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                         //System.out.println("Checking B3");
                    double x1[]=s.get(i,j+1);
                    double x2[]=s.get(i+1,j+1);
                    double x3[]=s.get(i-1,j+1);
                    double y1[]=s.get(i+1,j-1);
                    double y2[]=s.get(i-1,j-1);
                    double y3[]=s.get(i,j-1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }
            }
           
            //Implementation of B4 thinning slot..
              for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                        //System.out.println("Checking B4");
                    double x1[]=s.get(i,j+1);
                    double x2[]=s.get(i+1,j+1);
                    double x3[]=s.get(i+1,j);
                    double y1[]=s.get(i-1,j);
                    double y2[]=s.get(i-1,j-1);
                    double y3[]=s.get(i,j-1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }
            }
            
            //Implementation of B5 thinning slot..
              for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                        //System.out.println("Checking B5");
                    double x1[]=s.get(i+1,j-1);
                    double x2[]=s.get(i+1,j+1);
                    double x3[]=s.get(i+1,j);
                    double y1[]=s.get(i-1,j);
                    double y2[]=s.get(i-1,j-1);
                    double y3[]=s.get(i-1,j+1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }
            }  
           
            //Implementation of B6 thinning slot..
              for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                        //System.out.println("Checking B6");
                    double x1[]=s.get(i+1,j-1);
                    double x2[]=s.get(i+1,j);
                    double x3[]=s.get(i,j-1);
                    double y1[]=s.get(i-1,j);
                    double y2[]=s.get(i,j+1);
                    double y3[]=s.get(i-1,j+1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }
              } 
            //Implementation of B7 thinning slot..
              for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                        //System.out.println("Checking B7");
                    double x1[]=s.get(i+1,j-1);
                    double x2[]=s.get(i-1,j-1);
                    double x3[]=s.get(i,j-1);
                    double y1[]=s.get(i-1,j+1);
                    double y2[]=s.get(i,j+1);
                    double y3[]=s.get(i+1,j+1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }    
            }
              
           //Implementation of B8 thinning slot..
              for(int i=1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    
                    double a[]=s.get(i, j);
                    if(a[0]==1){
                        //System.out.println("Checking B8");
                    double x1[]=s.get(i-1,j);
                    double x2[]=s.get(i-1,j-1);
                    double x3[]=s.get(i,j-1);
                    double y1[]=s.get(i+1,j);
                    double y2[]=s.get(i,j+1);
                    double y3[]=s.get(i+1,j+1);
                   
                    if(x1[0]+x2[0]+x3[0]==0 && y1[0]*y2[0]*y3[0]==1){
                        s.put(i, j, 0);
                        p++;
                    }
                    }                  
                }    
            }   
             
              
              y=s.clone();
              for(int i=0;i<n;i++){
                  for(int j=0;j<m;j++){
                      double r[]=s.get(i, j);
                      double f[]=img.get(i, j);
                      
                      if(r[0]==1) y.put(i, j, f[0]);
                      else if(r[0]==0) y.put(i,j,255);
                      else             y.put(i, j, 255);
                  }
              }
              
              pp++;
              System.out.println(p);
            if(p==0) break;
        }
       Imgcodecs.imwrite("E:/photo/ADDDD/SKELE/SKELE"+kpp+".jpg", y);
 }
    }
}
