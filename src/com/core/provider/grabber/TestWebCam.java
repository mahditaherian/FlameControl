package com.core.provider.grabber;

import com.lti.civil.CaptureDeviceInfo;
import com.lti.civil.CaptureException;
import com.lti.civil.CaptureObserver;
import com.lti.civil.CaptureStream;
import com.lti.civil.CaptureSystem;
import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.DefaultCaptureSystemFactorySingleton;
import com.lti.civil.Image;
import com.lti.civil.awt.AWTImageConverter;
import com.sun.media.jai.codecimpl.JPEGImageEncoder;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TestWebCam implements CaptureObserver {

    JButton start = null;

    JButton shot = null;

    JButton stop = null;

    CaptureStream captureStream = null;

    boolean takeShot = false;

    public TestWebCam() {

        CaptureSystemFactory factory = DefaultCaptureSystemFactorySingleton.instance();

        CaptureSystem system;

        try {

            system = factory.createCaptureSystem();

            system.init();

            List list = system.getCaptureDeviceInfoList();

            int i = 0;

            if (i < list.size()) {

                CaptureDeviceInfo info = (CaptureDeviceInfo) list.get(i);

                System.out.println((new StringBuilder()).append(
                        "Device ID ").append(i).append(
                                ": ").append(info.getDeviceID()).toString()
                );

                System.out.println((new StringBuilder()).append("Description ").append(i).append(
                        ": ").append(info.getDescription()).toString()
                );

                captureStream = system.openCaptureDeviceStream(info.getDeviceID());

                captureStream.setObserver(TestWebCam.this);

            }

        } catch (CaptureException ex) {

            ex.printStackTrace();

        }

//UI work of the program 
        JFrame frame = new JFrame();

        frame.setSize(7000, 800);

        JPanel panel = new JPanel();

        frame.setContentPane(panel);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        start = new JButton("Start");

        stop = new JButton("Stop");

        shot = new JButton("Shot");

        panel.add(start);

        panel.add(stop);

        panel.add(shot);

        panel.revalidate();

        start.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    captureStream.start();

                } catch (CaptureException ex) {

                    ex.printStackTrace();

                }

            }

        });

        stop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    captureStream.stop();

                } catch (CaptureException ex) {

                    ex.printStackTrace();

                }

            }

        });

        shot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                takeShot = true;

            }

        });

    }

    public void onNewImage(CaptureStream stream, Image image) {

        if (!takeShot) {
            return;
        }

        takeShot = false;

        System.out.println(
                "New Image Captured");

        byte bytes[] = null;

        try {

            if (image == null) {

                bytes = null;

                return;

            }

            try {

                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                BufferedImage img = createRGBImage(bytes, width, height);
                JPEGImageEncoder jpeg = new JPEGImageEncoder(os, null);/////////////////////////////////////////////////////

                jpeg.encode(AWTImageConverter.toBufferedImage(image));

                os.close();

                bytes = os.toByteArray();

            } catch (IOException e) {

                e.printStackTrace();

                bytes = null;

            } catch (Throwable t) {

                t.printStackTrace();

                bytes = null;

            }

            if (bytes == null) {

                return;

            }

            ByteArrayInputStream is = new ByteArrayInputStream(bytes);

            File file = new File("/img" + Calendar.getInstance().getTimeInMillis() + ".jpg");

            FileOutputStream fos = new FileOutputStream(file);

            fos.write(bytes);

            fos.close();

            BufferedImage myImage = ImageIO.read(file);

            shot.setText(
                    "");

            shot.setIcon(new ImageIcon(myImage));

            shot.revalidate();

        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

    private static BufferedImage createRGBImage(byte[] bytes, int width, int height) {
        DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
        ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8}, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, Raster.createInterleavedRaster(buffer, width, height, width * 3, 3, new int[]{0, 1, 2}, null), false, null);
    }

    public void onError(CaptureStream arg0, CaptureException arg1) {

        throw new UnsupportedOperationException("Error is coming");

    }

    public static void main(String args[])
            throws Exception {

        TestWebCam test = new TestWebCam();

    }
}
/*Code Ends here*/
