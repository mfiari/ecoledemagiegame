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
public class TexteVueDemarrage extends Texte {
    
    public String nouvellePartie;
    public String continuer;
    public String extra;
    
    public static TexteVueDemarrage getInstance () {
        return (TexteVueDemarrage) TexteVueDemarrage.getInstance(TexteVueDemarrage.class);
    }
}
