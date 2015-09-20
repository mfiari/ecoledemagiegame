/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte;

import mfiari.lib.game.texte.Texte;

/**
 *
 * @author mike
 */
public class TexteVueEcoleDeMagie extends Texte {
    
    public String haut;
    public String bas;
    public String droite;
    public String gauche;
    public String action;
    public String menu;
    public String queFaire;
    public String oui;
    public String non;
    
    public static TexteVueEcoleDeMagie getInstance () {
        return (TexteVueEcoleDeMagie) TexteVueEcoleDeMagie.getInstance(TexteVueEcoleDeMagie.class);
    }
}
