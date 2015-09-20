/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte.de;

import mfiari.lib.game.texte.Texte_de;

/**
 *
 * @author mike
 */
public class TexteVueDemarrage extends mfiari.ecoledemagie.game.texte.TexteVueDemarrage  {
    
    public TexteVueDemarrage () {
        this.suivant = Texte_de.suivant;
        this.nouvellePartie = Texte_de.nouvellePartie;
        this.continuer = Texte_de.continuer;
        this.extra = Texte_de.extra;
    }
}
