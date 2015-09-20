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
abstract public class TexteVueMenu  extends Texte{
    
    /**********     VARIABLE UTILISE DANS gmm.vueConsole_menu     **********/
   
    public String etat;
    public String technique;
    public String quete;
    public String equiper;
    public String cuisine;
    public String configuration;
    public String sauvegarder;
    public String objetDivers;
    public String arme;
    public String armure;
    public String chapeau;
    public String botte;
    public String accessoire;
    public String objetRare;
    public String quelPersoChoisir;
    public String force;
    public String def;
    public String magie;
    public String res;
    public String vit;
    public String prec;
    public String agi;
    public String exp;
    public String nivSuiv;
    public String niv;
    public String pv;
    public String pm;
    public String objet;
    public String quitter;
    public String pieceOr;
    public String tempsjeu;
    public String queFaire;
    
    public static TexteVueMenu getInstance() {
        return (TexteVueMenu) TexteVueMenu.getInstance(TexteVueMenu.class);
    }
    
}
