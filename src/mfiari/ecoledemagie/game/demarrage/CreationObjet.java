/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import mfiari.ecoledemagie.game.objet.Objets;
import mfiari.lib.game.liste.ListeObjet;

/**
 *
 * @author mike
 */
public class CreationObjet {
    
    protected ListeObjet listeDObjet = new ListeObjet ();
    
    public CreationObjet () {
        super();
        this.creerObjet();
        this.creerArme();
        this.listeDObjet.ajoutObjet(Objets.antidote);
        this.listeDObjet.ajoutObjet(Objets.ether);
        this.listeDObjet.ajoutObjet(Objets.listeDennemi);
        this.listeDObjet.ajoutObjet(Objets.potion);
        this.listeDObjet.ajoutObjet(Objets.renais);
    }

    private void creerObjet () {

    }

    private void creerArme () {

    }

    public ListeObjet getListeDObjet () {
        return this.listeDObjet;
    }
    
}
