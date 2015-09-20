/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.liste;

import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.ville.Environnement;
import java.util.ArrayList;
import mfiari.lib.game.liste.ListeDeGens;

/**
 *
 * @author mike
 */
public class ListeDePerso <E extends Perso> extends ListeDeGens<E> {
    
    private ArrayList<Environnement> environnements;
    private ArrayList<Integer> pourcentage;

    public ListeDePerso() {
        super();
        this.environnements = new ArrayList<>();
        this.pourcentage = new ArrayList<>();
    }
    
    @Override
    public void ajouterPerso (E e) {
        this.ajouterPerso(e, Environnement.herbe, 0);
    }
    
    public void ajouterPerso (E e, Environnement environnement, int pourcentage) {
        super.ajouterPerso(e);
        this.environnements.add(environnement);
        this.pourcentage.add(pourcentage);
    }
    
    public Perso getPersoByEnvironnementAndPourcentage (Environnement environnement, int pourcentage) {
        if (pourcentage >= 50) {
            int indice = 0;
            int newPourcentage = 50;
            while (indice < this.listeDePerso.size()) {
                if (this.environnements.get(indice).equals(environnement)) {
                    if (pourcentage <= ((this.pourcentage.get(indice) * 50) / 100) + newPourcentage) {
                        return this.listeDePerso.get(indice); 
                    }
                    newPourcentage += (this.pourcentage.get(indice) * 50) / 100;
                }
                indice++;
            }
        }
        return null;
    }
    
    public int getNbPersoByEnvironnement (Environnement environnement) {
        int nbPokemon = 0;
        int indice = 0;
        while (indice < this.listeDePerso.size()) {
            if (this.environnements.get(indice).equals(environnement)) {
                nbPokemon++;
            }
            indice++;
        }
        return nbPokemon;
    }

    public void soin() {
        for (int i = 0; i < this.size(); i++) {
            this.getPerso(i).soin();
        }
    }

    public boolean estKO() {
        boolean ko = false;
        int nbKO = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.getPerso(i).getPv() == 0) {
                nbKO = nbKO + 1;
            }
        }
        if (nbKO == this.size()) {
            ko = true;
        }
        return ko;
    }
    
    public void ajouterObjet (Objet obj) {
    }

    public boolean contains(Objet objetAavoir) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void acheter(Objet objet, int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
