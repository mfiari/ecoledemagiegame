/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.lib.game.interfaces.ISol;
import mfiari.lib.game.interfaces.ville.IQuartier;
import mfiari.lib.game.liste.ListeDeBatiment;
import mfiari.lib.game.liste.ListeDeGens;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.personnage.Habitant;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class Quartier extends Endroit implements IQuartier {
    
    private ListeDeBatiment batiments;
    private ListeDePerso<Personnage> personnages;
    private ListeDePerso<Ennemie> ennemies;
    private ListeDeGens<Habitant> habitants;
    private Sol sol;

    public Quartier () {
        super();
        this.batiments = new ListeDeBatiment ();
        this.personnages = new ListeDePerso<>();
        this.ennemies = new ListeDePerso<>();
        this.habitants = new ListeDeGens<>();
    }

    public Quartier (String nom, int positionX, int positionY,int longueur, int largeur) {
        super(nom, positionX, positionY, longueur, largeur);
        this.batiments = new ListeDeBatiment ();
        this.personnages = new ListeDePerso<>();
        this.ennemies = new ListeDePerso<>();
        this.habitants = new ListeDeGens<>();
    }

    @Override
    public void ajouterPersonnage (Personnage p) {
        this.personnages.ajouterPerso(p);
    }

    @Override
    public void enleverPersonnage (Personnage p) {
        this.personnages.enleverPerso(p);
    }

    @Override
    public void ajouterEnnemie (Ennemie e) {
        this.ennemies.ajouterPerso(e);
    }

    public void ajouterEnnemie (Ennemie e, Environnement environnement, int pourcentage) {
        this.ennemies.ajouterPerso(e, environnement, pourcentage);
    }
    
    public boolean aPerso (Environnement environnement) {
        return this.ennemies.getNbPersoByEnvironnement(environnement) > 0;
    }
    
    public Ennemie getEnnemis (Environnement environnement) {
        int pourcentage = (int) (Math.random() * 100);
        return (Ennemie) this.ennemies.getPersoByEnvironnementAndPourcentage(environnement, pourcentage);
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
    public void ajouterBatiment (Batiments b) {
        //this.batiments.ajouterBatiment(b);
    }

    @Override
    public ListeDeBatiment getBatiments() {
        return this.batiments;
    }

    @Override
    public Endroit aEndroit (int i, int j) {
        //return this.batiments.aBatiment(i, j);
        return null;
    }

    @Override
    public void ajouterObjetEndroit (ObjetEndroit o) {
        super.ajouterObjetEndroit(o, Environnement.aucun);
    }

    @Override
    public void entrer (ListeDePerso equipe) {
    }

    @Override
    public Gens aGens(Position p) {
        if (this.habitants.aPerso(p)!=null) {
            return this.habitants.aPerso(p);
        }
        if (this.personnages.aPerso(p)!=null) {
            return this.personnages.aPerso(p);
        }
        return null;
    }

    @Override
    public mfiari.lib.game.ville.Endroit aEndroit(Position p) {
        return null;
    }

    @Override
    public mfiari.lib.game.personnage.Personnage aPersonnage(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public mfiari.lib.game.personnage.Personnage aPersonnage(int i, int j, Orientation orientation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void entrer(mfiari.lib.game.personnage.Personnage equipe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSol(ISol sol) {
        this.sol = (Sol) sol;
    }

    @Override
    public Sol getSol() {
        return this.sol;
    }

    @Override
    public ListeDeGens getGens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
