/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.media.image.objetEndroit;

import mfiari.ecoledemagie.game.objet.Type_objet;
import java.awt.image.BufferedImage;
import java.net.URL;
import mfiari.lib.game.image.Image;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.objet.ObjetEndroitClassique;

/**
 *
 * @author mike
 */
public class ImageObjetEndroit extends Image {
    
    public final static String defaut = "classique/";
    public final static String combat = "combat/";
    
    public ImageObjetEndroit () {
        
    }
    
    public URL getUrlImageObjet (String nomObjet) {
        return this.getUrlImageObjet(nomObjet, defaut);
    }
    
    public URL getUrlImageObjet (String nomObjet, String type) {
        nomObjet = this.formatString(nomObjet);
        URL url = getClass().getResource(type+nomObjet+".png");
        if (url == null) {
            url = getClass().getResource(type+nomObjet+".jpg");
            if (url == null) {
                url = getClass().getResource(type+nomObjet+".gif");
            }
        }
        if (url == null && !defaut.equals(type)) {
            return this.getUrlImageObjet(nomObjet, defaut);
        }
        return url;
    }
    
    public boolean aImageObjet (String nomObjet) {
        return this.getUrlImageObjet(nomObjet) != null;
    }
    
    public boolean aImageObjet (String nomObjet, String type) {
        return this.getUrlImageObjet(nomObjet) != null;
    }
    
    public BufferedImage getImageObjet (String nomObjet) {
        return this.getImage(this.getUrlImageObjet(nomObjet));
    }
    
    public BufferedImage getImageObjet (String nomObjet, String type) {
        return this.getImage(this.getUrlImageObjet(nomObjet));
    }
    
    public BufferedImage getImageObjet (String nomObjet, int width, int height) {
        return this.getImage(this.getUrlImageObjet(nomObjet), width, height);
    }
    
    public BufferedImage getImageObjet (String nomObjet, int width, int height, String type) {
        return this.getImage(this.getUrlImageObjet(nomObjet), width, height);
    }
    
    public BufferedImage getImageObjetWidth(String nomObjet, int width) {
        return this.getImageWidth(this.getUrlImageObjet(nomObjet), width);
    }
    
    public BufferedImage getImageObjetWidth(String nomObjet, int width, String type) {
        return this.getImageWidth(this.getUrlImageObjet(nomObjet), width);
    }
    
    public BufferedImage getImageObjetHeigth(String nomObjet, int height) {
        return this.getImageHeight(this.getUrlImageObjet(nomObjet), height);
    }
    
    public BufferedImage getImageObjetHeigth(String nomObjet, int height, String type) {
        return this.getImageHeight(this.getUrlImageObjet(nomObjet), height);
    }
    
    public BufferedImage getImageObjetWidthOrHeigth(String nomObjet, int width, int height) {
        return this.getImageWidthOrHeight(this.getUrlImageObjet(nomObjet), width, height);
    }
    
    public BufferedImage getImageObjetWidthOrHeigth(String nomObjet, int width, int height, String type) {
        return this.getImageWidthOrHeight(this.getUrlImageObjet(nomObjet), width, height);
    }
    
    public BufferedImage getImageObjetWidthEtHeigth(String nomObjet, int width, int height) {
        return this.getImageWidthEtHeight(this.getUrlImageObjet(nomObjet), width, height);
    }
    
    public BufferedImage getImageObjetWidthEtHeigth(String nomObjet, int width, int height, String type) {
        return this.getImageWidthEtHeight(this.getUrlImageObjet(nomObjet), width, height);
    }
    
    public static String getNomImageObjetByType (Type_objet type) {
        String nom = "";
        switch (type) {
            case arbre :
                nom = "arbre";
                break;
            case escalier_montant :
                nom = "ecsalier_montant";
                break;
            case etagere :
                nom = "meuble";
                break;
            case table :
                nom = "table";
                break;
            case coffre :
                nom = "coffre_pokeball";
                break;
            case porte :
                nom = "porte_sortie";
                break;
            case lit:
                nom = "lit";
                break;
            case escalier_descendant:
                nom = "ecsalier_descendant";
                break;
            case chemin :
                nom = "chemin";
                break;
            case herbe :
                nom = "herbe";
                break;
            case fontaine :
                nom = "riviere";
                break;
        }
        return nom;
    }
    
    public static boolean getImageObjetWidthEtHeight (Type_objet type) {
        boolean nom = false;
        switch (type) {
            case escalier_montant :
                nom = true;
                break;
            case chemin :
                nom = true;
                break;
            case herbe :
                nom = true;
                break;
            case fontaine :
                nom = true;
                break;
        }
        return nom;
    }
    
    public static int getNivImageObjet (Type_objet type) {
        int nom = 0;
        switch (type) {
            case arbre :
                nom = 1;
                break;
            case etagere :
                nom = 1;
                break;
            case table :
                nom = 1;
                break;
            case coffre :
                nom = 1;
                break;
            case fontaine :
                nom = 1;
                break;
        }
        return nom;
    }
    
    public static ObjetEndroit getSolImageObjetByType (Type_objet type) {
        switch (type) {
            case arbre :
                return new ObjetEndroitClassique(Type_objet.herbe, 0, 0);
        }
        return null;
    }
}