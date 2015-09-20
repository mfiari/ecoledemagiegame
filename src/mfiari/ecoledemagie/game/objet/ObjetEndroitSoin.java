/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.objet;

import mfiari.ecoledemagie.game.perso.Personnage;


/**
 *
 * @author mike
 */
public class ObjetEndroitSoin extends mfiari.lib.game.objet.ObjetEndroitSoin {

    public ObjetEndroitSoin (Type_objet nom, int positionX, int positionY, String texte) {
        super(nom, positionX, positionY, texte);
    }

    @Override
    public void soin(mfiari.lib.game.personnage.Personnage d) {
        Personnage p = (Personnage)d;
        p.soin();
    }
}