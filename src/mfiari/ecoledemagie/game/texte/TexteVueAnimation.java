/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte;

import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.lib.game.texte.Texte;

/**
 *
 * @author mike
 */
public abstract class TexteVueAnimation extends Texte {
    
    public static TexteVueAnimation getInstance() {
        return (TexteVueAnimation) TexteVueAnimation.getInstance(TexteVueAnimation.class);
    }

    public abstract String affichePvPerso(Perso perso);
}
