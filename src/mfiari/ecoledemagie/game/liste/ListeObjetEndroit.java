/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.liste;

import mfiari.lib.game.liste.ListeObjet;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class ListeObjetEndroit extends ListeObjet<ObjetEndroit> {
    
    public ObjetEndroit aObjetEndroit (Position p) {
        for (int k =0 ; k < this.size() ; k++) {
            if (this.getObjet(k).getPosition().equals(p)) {
                return this.getObjet(k);
            }
        }
        return null;
    }
}
