/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.lib.game.liste.ListeDeGens;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.personnage.Habitant;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class Salle extends Batiments {

    private ListeDePerso<Personnage> personnages;
    private ListeDePerso<Ennemie> ennemies;
    private ListeDeGens<Habitant> habitants;

    public Salle () {
        super();
        this.personnages = new ListeDePerso<>();
        this.ennemies = new ListeDePerso<>();
    }

    public Salle (String nom, int positionX, int positionY,int longueur, int largeur) {
        super(nom, positionX, positionY, longueur, largeur, 0, 0);
        this.personnages = new ListeDePerso<>();
        this.ennemies = new ListeDePerso<>();
    }

    @Override
    public void ajouterPersonnage (Personnage p) {
        this.personnages.ajouterPerso(p);
    }

    @Override
    public void ajouterEnnemie (Ennemie e) {
        this.ennemies.ajouterPerso(e);
    }

    @Override
    public void ajouterHabitant (Habitant h) {
        this.habitants.ajouterPerso(h);
    }

    @Override
    public void enleverHabitant (Habitant h) {
        this.habitants.enleverPerso(h);
    }

    @Override
    public Gens aGens (Position p) {
        if (this.habitants.aPerso(p)!=null) {
            return this.habitants.aPerso(p);
        }
        if (this.personnages.aPerso(p)!=null) {
            return this.personnages.aPerso(p);
        }
        return null;
    }

}
