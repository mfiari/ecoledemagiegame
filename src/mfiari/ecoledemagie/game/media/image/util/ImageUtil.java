/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.media.image.util;

import java.awt.image.BufferedImage;
import java.net.URL;
import mfiari.lib.game.image.Image;

/**
 *
 * @author mike
 */
public class ImageUtil extends Image{
    
    public ImageUtil () {
        
    }
    
    public BufferedImage getLogo () {
        URL url = getClass().getResource("logo.png");
        return this.getImage(url);
    }
    
    public BufferedImage getImageDuel () {
        URL url = getClass().getResource("logo.png");
        return this.getImage(url, 100, 80);
    }
    
    public BufferedImage getImageEnLigne () {
        URL url = getClass().getResource("logo.png");
        return this.getImage(url, 100, 80);
    }
    
    public BufferedImage getImageTournoi () {
        URL url = getClass().getResource("tournoi.jpg");
        return this.getImage(url, 100, 80);
    }
    
    public BufferedImage getImageMusiqueEtVideo () {
        URL url = getClass().getResource("rondoudou.jpg");
        return this.getImage(url, 100, 80);
    }
    
    public BufferedImage getImageConfiguration () {
        URL url = getClass().getResource("configuration.png");
        return this.getImage(url, 100, 80);
    }
    
    public BufferedImage getImageRetour () {
        URL url = getClass().getResource("fleche-retour.png");
        return this.getImage(url, 100, 80);
    }
}
