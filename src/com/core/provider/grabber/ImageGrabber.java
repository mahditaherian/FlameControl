/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.provider.grabber;

import com.core.common.Config;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peyman
 */
public class ImageGrabber {

    static CanvasFrame canvas = new CanvasFrame("Web Cam");

    public ImageGrabber() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        takePicture();
    }

    public static void takePicture() {
        try {
            FrameGrabber grabber = new VideoInputFrameGrabber(1);
            int i = 0;
            grabber.start();
            IplImage img;
            while (true) {
            img = grabber.grab();
            if (img != null) {
                cvSaveImage(Config.DEFAULT_IMAGES_PATH.getAbsolutePath() + "\\flame.jpg", img);
                // show image on window

                canvas.showImage(img);
            }
                Thread.sleep(3000);
            }
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(ImageGrabber.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageGrabber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
