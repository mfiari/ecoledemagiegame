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
public class TexteVueMenuReseau extends Texte {
    
    public String retour;
    
    public static TexteVueMenuReseau getInstance() {
        return (TexteVueMenuReseau) TexteVueMenuReseau.getInstance(TexteVueMenuReseau.class);
    }
}
