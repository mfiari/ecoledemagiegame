/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.liste.ListeDePerso;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
abstract public class Batiments extends Quartier {

    private int etage;
    private int etageMin;
    private int etageMax;
    private ArrayList<ArrayList<Salle>> salles;
    private int[] largeurs;
    private int[] longueur;

    public Batiments() {
    }

    public Batiments(String nom, int positionX, int positionY, int longueur, int largeur, int etageMin, int etageMax) {

        super(nom, positionX, positionY, longueur, largeur);

        this.salles = new ArrayList();
        for (int i = 0; i < etageMax - (etageMin - 1); i++) {
            this.salles.add(new ArrayList());
        }
        this.largeurs = new int[etageMax - etageMin + 1];
        this.longueur = new int[etageMax - etageMin + 1];
        this.largeurs[0] = largeur;
        this.longueur[0] = longueur;
        this.etage = 0;
        this.etageMin = etageMin;
        this.etageMax = etageMax;
    }

    public void ajouterSalle(Salle s, int etage) {
        this.salles.get(0).add(s);
    }

    public void setNbSalle(int etage, int longueur, int largeur) {
        this.largeurs[etage] = largeur;
        this.longueur[etage] = longueur;
    }

    @Override
    public void entrer(ListeDePerso equipe) {
        /*equipe.setEndroit(this.salles.get(0).get(0));
        if (this.salles.get(0).get(0).getObjet_endroit(0).getPosition().getPositionX() == 0) {
            equipe.setPositionX(this.salles.get(0).get(0).getObjet_endroit(0).getPosition().getPositionX() + 1);
            equipe.setPositionY(this.salles.get(0).get(0).getObjet_endroit(0).getPosition().getPositionY());
        } else {
            if (this.salles.get(0).get(0).getObjet_endroit(0).getPosition().getPositionY() == 0) {
                equipe.setPositionX(this.salles.get(0).get(0).getPosition().getPositionX());
                equipe.setPositionY(this.salles.get(0).get(0).getPosition().getPositionY() + 1);
            } else {
                if (this.salles.get(0).get(0).getObjet_endroit(0).getPosition().getPositionX()
                        == this.salles.get(0).get(0).getLongueur()) {
                    equipe.setPositionX(this.salles.get(0).get(0).getPosition().getPositionX() - 1);
                    equipe.setPositionY(this.salles.get(0).get(0).getPosition().getPositionY());
                } else {
                    equipe.setPositionX(this.salles.get(0).get(0).getPosition().getPositionX());
                    equipe.setPositionY(this.salles.get(0).get(0).getPosition().getPositionY() - 1);
                }
            }
        }*/
    }

    public void sortir() {
    }

    public void monter() {
        if (this.etage < this.etageMax) {
            this.etage++;
            this.sortir();
            //this.entrer();
        }
    }

    public void descendre() {
        if (this.etage > this.etageMin) {
            this.etage--;
            this.sortir();
            //this.entrer();
        }
    }
}
