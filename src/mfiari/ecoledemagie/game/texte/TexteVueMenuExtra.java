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
public class TexteVueMenuExtra extends Texte {
    
    public String ligne;
    public String duel;
    public String configuration;
    public String retour;
    public String choixLangue;
    
    public static TexteVueMenuExtra getInstance() {
        return (TexteVueMenuExtra) TexteVueMenuExtra.getInstance(TexteVueMenuExtra.class);
    }
}
