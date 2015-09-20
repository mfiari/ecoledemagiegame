/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte.fr;

import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.texte.TexteVueAnimation;

/**
 *
 * @author mike
 */
public class TexteVueAnimation_fr extends TexteVueAnimation {
    
    public TexteVueAnimation_fr () {
        this.suivant = "suivant";
    }
    
    @Override
    public String affichePvPerso(Perso p) {
        return p.getNom() + " : PV : " + Math.round(p.getPv());
    }
    
}
