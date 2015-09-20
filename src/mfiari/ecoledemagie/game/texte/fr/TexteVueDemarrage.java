/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte.fr;

import mfiari.lib.game.texte.Texte_fr;

/**
 *
 * @author mike
 */

public class TexteVueDemarrage extends mfiari.ecoledemagie.game.texte.TexteVueDemarrage  {
    
    public TexteVueDemarrage () {
        this.suivant = Texte_fr.suivant;
        this.nouvellePartie = Texte_fr.nouvellePartie;
        this.continuer = Texte_fr.continuer;
        this.extra = Texte_fr.extra;
    }
}
