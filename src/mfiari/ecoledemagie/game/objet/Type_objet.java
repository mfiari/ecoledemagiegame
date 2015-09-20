/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.objet;

import mfiari.lib.game.interfaces.TypeObjet;

/**
 *
 * @author mike
 */
public enum Type_objet implements TypeObjet{
    
    /*objet*/
    soin_stat, soin_etat, soin_all,
    /*arme */
    epee, hache, livre, arc, botte, chapeau, robe, veste, armure, accessoire, casque, lance, balai, epee_livre, baton,
    parchemin, couteau,
    /*objet passage*/
    porte, escalier_montant, escalier_descendant, chemin,
    /*objet coffre */
    bureau, coffre,
    /*objet observable ou coffre*/
    etagere,
    /* objet observable */
    
    /*objet classique */
    table, arbre, lit, mur, grand_chene, place, fontaine, rocher, herbe;
    
    @Override
    public boolean estBloquant() {
        switch (this) {
            case chemin :
                return false;
            case herbe :
                return false;
        }
        return true;
    }

    @Override
    public boolean estPassageDirect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
