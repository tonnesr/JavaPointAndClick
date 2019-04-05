/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occult;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Tonnes
 */
public class Utility {
    
    Dimension screenSize;
    Dimension windowSize;
    
    Image image;
    Image gif;
    
    public Utility() {   
    }

    //Image Utilities
    public Image getImage(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }    
    
    public Image resizeImage(Image source, int w, int h, Graphics2D g) {
        BufferedImage resizedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, w, h, null);
        g.dispose();
        
        return resizedImage;
    } 
    
    public Image getAnimatedGif(String filepath) {
        image = new ImageIcon(filepath).getImage();
        return image;
    }

    //Screen and Window Utility
    public Dimension getScreenSize() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
        return screenSize;
    }
    public Dimension getWindowSize(JFrame frame) {
        windowSize = frame.getBounds().getSize();        
        return windowSize;
    }
    //Doesn't work with many components. Do not use!
    public void fullScreen(JFrame frame) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);       
    }    
    
}
