/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.media.image.sol;

import mfiari.ecoledemagie.game.ville.Endroit;
import mfiari.ecoledemagie.game.ville.Quartier;
import mfiari.ecoledemagie.game.ville.Salle;
import mfiari.ecoledemagie.game.ville.Sol;
import java.awt.image.BufferedImage;
import java.net.URL;
import mfiari.lib.game.image.Image;
import mfiari.lib.game.interfaces.ville.IEndroit;

/**
 *
 * @author mike
 */
public class ImageSol extends Image {
    
    public URL getUrlImageSol (String nom) {
        URL url = getClass().getResource(nom+".png");
        if (url == null) {
            url = getClass().getResource(nom+".jpg");
            if (url == null) {
                url = getClass().getResource(nom+".gif");
            }
        }
        return url;
    }
    
    public boolean aImageSol (String nom) {
        return this.getUrlImageSol(nom) != null;
    }
    
    public static String getNomImageSol (Sol sol) {
        String nom;
        switch (sol) {
            case bois:
                nom = "sol_maison";
                break;
            case herbe:
                nom = "herbe";
                break;
            case terre:
                nom = "terre";
                break;
            case bitume:
                nom = "bitume";
                break;
            default :
                nom = "";
                break;
        }
        return nom;
    }
    
    public static String getNomImageSolBySalle (IEndroit endroit) {
        String nom = "";
        if (endroit instanceof Salle) {
            Salle salle = (Salle) endroit;
            switch (salle.getSol()) {
                case bois:
                    nom = "sol_maison";
                    break;
                default :
                    nom = "";
                    break;
            }
        } else if (endroit instanceof Quartier) {
            Quartier quartier = (Quartier) endroit;
            switch (quartier.getSol()) {
                case herbe:
                    nom = "herbe";
                    break;
                case terre:
                    nom = "terre";
                    break;
                case bitume:
                    nom = "bitume";
                    break;
                default :
                    nom = "";
                    break;
            }
        }
        return nom;
    }
    
    public BufferedImage getImageSol(String nom) {
        return this.getImage(this.getUrlImageSol(nom));
    }
    
    public BufferedImage getImageSol (String nom, int width, int height) {
        return this.getImage(this.getUrlImageSol(nom), width, height);
    }
    
    public BufferedImage getImageSolWidth(String nom, int width) {
        return this.getImageWidth(this.getUrlImageSol(nom), width);
    }
    
    public BufferedImage getImageSolHeigth(String nom, int height) {
        return this.getImageHeight(this.getUrlImageSol(nom), height);
    }
    
    public BufferedImage getImageSolWidthOrHeigth(String nom, int width, int height) {
        return this.getImageWidthOrHeight(this.getUrlImageSol(nom), width, height);
    }
    
    public BufferedImage getImageSolWidthEtHeigth(String nom, int width, int height) {
        return this.getImageWidthEtHeight(this.getUrlImageSol(nom), width, height);
    }
}