/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeObjetEndroit;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.personnage.Habitant;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
abstract public class Endroit extends mfiari.lib.game.ville.Endroit<mfiari.lib.game.personnage.Personnage> {
    private String nom;
    private Position position;
    private int longueur;
    private int largeur;
    private ListeObjetEndroit objets;
    
    public Endroit () {
        this.position = new Position();
    }

    public Endroit (String nom, int positionX, int positionY,int longueur, int largeur) {
        this.nom = nom;
        this.largeur=largeur;
        this.longueur=longueur;
        this.objets = new ListeObjetEndroit();
        this.position = new Position(positionX, positionY, 0, this, Orientation.face);
    }

    @Override
    public String getNom () {
        return this.nom;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public int getLongueur () {
        return this.longueur;
    }

    @Override
    public int getLargeur () {
        return this.largeur;
    }

    public ObjetEndroit getObjet_endroit (int i) {
        return this.objets.getObjet(i);
    }

    public void ajouterObjet_endroit (ObjetEndroit o) {
        this.objets.ajoutObjet(o);
    }

    public abstract Endroit aEndroit (int i, int j);

    @Override
    public abstract Gens aGens (Position p);

    public void ajouterPersonnage (Personnage p) {
    }

    public void enleverPersonnage (Personnage p) {
    }

    public void ajouterEnnemie (Ennemie e) {
    }

    public void ajouterHabitant (Habitant h) {
    }

    public void enleverHabitant (Habitant h) {
    }

    public void ajouterBatiment (Batiments b) {
    }

    public ObjetEndroit aObjet_endroit (Position p) {
        for (int k=0 ; k<this.objets.size(); k++) {
            if (this.objets.getObjet(k).getPosition().equals(p)) {
                return this.getObjet_endroit(k);
            }
        }
        return null;
    }

    public abstract void entrer (ListeDePerso equipe);

}
