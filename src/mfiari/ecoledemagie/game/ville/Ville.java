/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.liste.ListeDeGens;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.personnage.Personnage;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class Ville extends Endroit{
    
    private ListeDEndroit<Quartier> quartiers;
    
    public Ville () {
        super ();
        this.quartiers = new ListeDEndroit<>();
    }
    
    public Ville (String nom, int positionX, int positionY, int longueur, int largeur) {
        super (nom, positionX, positionY, longueur, largeur);
        this.quartiers = new ListeDEndroit<>();
    }

    public void ajouterQuartier (Quartier q) {
        //this.quartiers.add(q);
    }

    public ListeDEndroit<Quartier> getQuartiers () {
        return this.quartiers;
    }

    @Override
    public void entrer(ListeDePerso equipe) {
        Quartier quartier = this.quartiers.getEndroit(0);
        ObjetEndroit obj = quartier.getObjet_endroit(0);
        /*equipe.setEndroit(quartier);
        if (obj.getPosition().getPositionX() == 0) {
            equipe.setPositionX(obj.getPosition().getPositionX() + 1);
            equipe.setPositionY(obj.getPosition().getPositionY());
        } else {
            if (obj.getPosition().getPositionY() == 0) {
                equipe.setPositionX(quartier.getPosition().getPositionX());
                equipe.setPositionY(quartier.getPosition().getPositionY() + 1);
            } else {
                if (obj.getPosition().getPositionX() == quartier.getLongueur()) {
                    equipe.setPositionX(quartier.getPosition().getPositionX() - 1);
                    equipe.setPositionY(quartier.getPosition().getPositionY());
                } else {
                    equipe.setPositionX(quartier.getPosition().getPositionX());
                    equipe.setPositionY(quartier.getPosition().getPositionY() - 1);
                }
            }
        }*/
    }

    public void sortir () {
        /*this.remove(this.ville);
        this.ville.removeAll();
        this.ville.setVisible(false);*/
    }

    @Override
    public Endroit aEndroit (int i, int j) {
        return null;
    }

    @Override
    public Gens aGens(Position p) {
        return null;
    }

    @Override
    public mfiari.lib.game.ville.Endroit aEndroit(Position p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personnage aPersonnage(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personnage aPersonnage(int i, int j, Orientation orientation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void entrer(Personnage equipe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListeDeGens getGens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
