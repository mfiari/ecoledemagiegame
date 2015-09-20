/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.lib.game.texte.Texte;

/**
 *
 * @author mike
 */
public class CreationTechnique {
    
    private ListeDeSort listeDeTec = new ListeDeSort ();
    
    public CreationTechnique () {
        this.creeTechnique();
        switch (Texte.langue) {
            case fr :
                break;
            case en:
                break;
        }
    }

    private void creeTechnique () {
    }

    public ListeDeSort getListeTec () {
        return this.listeDeTec;
    }
    
}
