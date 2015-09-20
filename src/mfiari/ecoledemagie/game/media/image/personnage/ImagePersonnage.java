/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.media.image.personnage;

import mfiari.ecoledemagie.game.perso.Personnage;
import java.awt.image.BufferedImage;
import java.net.URL;
import mfiari.lib.game.image.Image;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class ImagePersonnage extends Image {
    
    public final static String combat = "combat/";
    public final static String DEHORS = "dehors/";
    
    public ImagePersonnage () {
        
    }
    
    public URL getUrlImagePerso (String nomPerso) {
        return this.getUrlImagePerso(nomPerso, defaut);
    }
    
    public URL getUrlImagePerso (String nomPerso, String type) {
        nomPerso = this.formatString(nomPerso);
        URL url = getClass().getResource(type+nomPerso+".png");
        if (url == null) {
            url = getClass().getResource(type+nomPerso+".jpg");
            if (url == null) {
                url = getClass().getResource(type+nomPerso+".gif");
            }
        }
        if (url == null && !defaut.equals(type)) {
            return this.getUrlImagePerso(nomPerso, defaut);
        }
        return url;
    }
    
    public boolean aImagePerso (String nomPerso) {
        return this.getUrlImagePerso(nomPerso) != null;
    }
    
    public boolean aImagePerso (String nomPerso, String type) {
        return this.getUrlImagePerso(nomPerso) != null;
    }
    
    public boolean aImagePerso (Personnage personnage, String type) {
        if (personnage == null) {
            return false;
        }
        Orientation orientation = personnage.getPosition().getOrientation();
        if (this.aImagePerso(personnage.getNom()+"_"+orientation.name(), type)) {
            return true;
        }
        if (this.aImagePerso(personnage.getNom(), type)) {
            return true;
        }
        return this.getUrlImagePerso("default_"+orientation.name(), type) != null;
    }
    
    public BufferedImage getImagePerso (String nomPerso) {
        return this.getImage(this.getUrlImagePerso(nomPerso));
    }
    
    public BufferedImage getImagePerso (String nomPerso, String type) {
        return this.getImage(this.getUrlImagePerso(nomPerso));
    }
    
    public BufferedImage getImagePerso (String nomPerso, int width, int height) {
        return this.getImage(this.getUrlImagePerso(nomPerso), width, height);
    }
    
    public BufferedImage getImagePerso (String nomPerso, int width, int height, String type) {
        return this.getImage(this.getUrlImagePerso(nomPerso), width, height);
    }
    
    public BufferedImage getImagePerso (Personnage personnage, int width, int height, String type) {
        if (personnage == null) {
            return null;
        }
        Orientation orientation = personnage.getPosition().getOrientation();
        if (this.aImagePerso(personnage.getNom()+"_"+orientation.name(), type)) {
            return this.getImage(this.getUrlImagePerso(personnage.getNom()+"_"+orientation.name(), type), width, height);
        }
        if (this.aImagePerso(personnage.getNom(), type)) {
            return this.getImage(this.getUrlImagePerso(personnage.getNom(), type), width, height);
        }
        return this.getImage(this.getUrlImagePerso("default_"+orientation.name(), type), width, height);
    }
    
    public BufferedImage getImagePersoWidth(String nomPerso, int width) {
        return this.getImageWidth(this.getUrlImagePerso(nomPerso), width);
    }
    
    public BufferedImage getImagePersoWidth(String nomPerso, int width, String type) {
        return this.getImageWidth(this.getUrlImagePerso(nomPerso), width);
    }
    
    public BufferedImage getImagePersoHeigth(String nomPerso, int height) {
        return this.getImageHeight(this.getUrlImagePerso(nomPerso), height);
    }
    
    public BufferedImage getImagePersoHeigth(String nomPerso, int height, String type) {
        return this.getImageHeight(this.getUrlImagePerso(nomPerso), height);
    }
    
    public BufferedImage getImagePersoWidthOrHeigth(String nomPerso, int width, int height) {
        return this.getImageWidthOrHeight(this.getUrlImagePerso(nomPerso), width, height);
    }
    
    public BufferedImage getImagePersoWidthOrHeigth(String nomPerso, int width, int height, String type) {
        return this.getImageWidthOrHeight(this.getUrlImagePerso(nomPerso), width, height);
    }
    
}