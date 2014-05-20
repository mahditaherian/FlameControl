/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.provider;

import com.core.common.Config;
import com.core.provider.grabber.ImageGrabber;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peyman
 */
public class CameraImageProvider extends ImageProvider {

    static CanvasFrame canvas = new CanvasFrame("Web Cam");

    public CameraImageProvider() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public BufferedImage next() {
        BufferedImage image = null;
        FrameGrabber grabber = null;
        try {
            grabber = new VideoInputFrameGrabber(Config.CAMERA_IN_USE);
            grabber.start();
            opencv_core.IplImage img;
//            Thread.sleep(1500);
            int c=1000;
            while(c>0 && !grabber.isDeinterlace()){
                Thread.sleep(1);
                c--;
            }
                img = grabber.grab();
//            for(int i=0;i<100;i++){
//                
//            }
            img = grabber.grab();
            if (img != null) {
                image = img.getBufferedImage();
                
                cvSaveImage(Config.DEFAULT_IMAGES_PATH.getAbsolutePath() + "\\flame.jpg", img);
                // show image on window

                canvas.showImage(img);
            }

        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(ImageGrabber.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CameraImageProvider.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (grabber != null) {
//                    grabber.flush();
                    grabber.stop();
                }
            } catch (FrameGrabber.Exception ex) {
            }
        }
        return image;
    }

}
