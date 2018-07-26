/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.opencv.core.*;
import org.opencv.imgproc.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;



/**
 *
 * @author Xander
 */
public class VideoCap {
    public static void main(String args[]){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera=new VideoCapture(0);
        
        if(!camera.isOpened()){
            System.out.println("Error");
          }
        else{
            Mat matrix=new Mat();
            while(true){
                if(camera.read(matrix)){
                    System.out.println("Frame Obtained");
                    System.out.println("Captured Frame Width "+matrix.width()+" Height "+matrix.height());
                    Imgcodecs.imwrite("camera.jpg", matrix);
                    System.out.println("ok");
                    break;
                }
            }
        }
        camera.release();
    }
}