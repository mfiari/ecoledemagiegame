/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.evenement;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.lib.game.position.Position;
import mfiari.ecoledemagie.game.sort.Sort;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.jeu.Jeu;
import mfiari.lib.game.liste.ListeObjet;
import mfiari.lib.game.personnage.Gens;

/**
 *
 * @author mike
 */
public class EvenementSpecial extends Evenement {

    private ListeObjet objets;
    private ListeDePerso<Personnage> persosAajouter;
    private ListeDePerso<Personnage> persosAenlever;
    private ListeDeSort sortAajouter;
    private boolean soin;

    public EvenementSpecial () {
        this.objets = new ListeObjet ();
        this.persosAajouter = new ListeDePerso<>();
        this.persosAenlever = new ListeDePerso<>();
        this.sortAajouter = new ListeDeSort();
        this.soin = false;
    }

    public EvenementSpecial (Position endroitDeLevenement, Gens personneAquiParler, Objet objet, String titre) {
        super(endroitDeLevenement, personneAquiParler, objet, titre);
        this.objets = new ListeObjet();
        this.persosAajouter = new ListeDePerso ();
        this.persosAenlever = new ListeDePerso ();
        this.sortAajouter = new ListeDeSort();
        this.soin = false;
    }
     
    @Override
    public void initialise () {
        super.initialise();
        this.objets = new ListeObjet();
        this.persosAajouter = new ListeDePerso ();
        this.persosAenlever = new ListeDePerso ();
        this.sortAajouter = new ListeDeSort();
        this.soin = false;
    }

    public void ajouterPersoAajouter (Personnage p) {
        this.persosAajouter.ajouterPerso(p);
    }
    
    public void ajouterSortAajouter (Sort sort) {
        this.sortAajouter.ajoutSort(sort);
    }

    public void ajouterPersoAenlever (Personnage p) {
        this.persosAenlever.ajouterPerso(p);
    }

    public void ajouterObjet (Objet o) {
        this.objets.ajoutObjet(o);
    }

    public void soignerEquipe () {
        this.soin = true;
    }

    @Override
    public void activeEvenement(Jeu jeu2) {
        EcoleDeMagie jeu = (EcoleDeMagie)jeu2;
        for (int i=0 ; i< this.objets.size() ; i++) {
            //jeu.getPerso().acheter(this.objets.getObjet(i), 1);
            jeu.setAffichage("vous recevez " /*+ this.objets.getObjet(i).getQuantite()*/ + " " + this.objets.getObjet(i).getNom());
        }
        if (this.soin) {
            jeu.getEquipe2().soin();
            jeu.setAffichage("vos PV et vos PM ont été restaurer.");
        }
        for (int i=0 ; i< this.persosAajouter.size() ; i++) {
            jeu.getEquipe2().ajouterPerso(this.persosAajouter.getPerso(i));
            jeu.setAffichage(this.persosAajouter.getPerso(i).getNom() + " a rejoin le groupe");
        }
        for (int i=0 ; i<this.persosAenlever.size() ; i++) {
            jeu.getEquipe2().ajouterPerso(jeu.getEquipe2().getPersonnage(this.persosAenlever.getPerso(i).getNom()));
            jeu.setAffichage(this.persosAenlever.getPerso(i).getNom() + " a quitter le groupe");
        }
        for (int i=0 ; i< this.sortAajouter.size() ; i++) {
            jeu.getPersonnage().getTechniques().ajoutSort(this.sortAajouter.getSort(i));
            jeu.getPersonnage().getTec().ajoutSort(this.sortAajouter.getSort(i));
            jeu.setAffichage("Vous avez appris " + this.sortAajouter.getSort(i).getNom());
        }
    }
    
}
