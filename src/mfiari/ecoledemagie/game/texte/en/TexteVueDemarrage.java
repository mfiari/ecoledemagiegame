/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte.en;

import mfiari.lib.game.texte.Texte_en;

/**
 *
 * @author mike
 */

public class TexteVueDemarrage extends mfiari.ecoledemagie.game.texte.TexteVueDemarrage {
    
    public TexteVueDemarrage () {
        this.suivant = Texte_en.suivant;
        this.nouvellePartie = Texte_en.nouvellePartie;
        this.continuer = Texte_en.continuer;
        this.extra = Texte_en.extra;
    }
}
