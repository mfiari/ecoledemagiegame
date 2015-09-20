/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.liste.ListeDeGens;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.personnage.Personnage;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class Pays extends Endroit{

    private ListeDEndroit<Zone> zones;

    public Pays () {
        super();
        this.zones = new ListeDEndroit<>();
    }
    
    public Pays (String nom, int positionX, int positionY, int longueur, int largeur) {
        super(nom, positionX, positionY, longueur, largeur);
        this.zones = new ListeDEndroit<>();
    }

    public ListeDEndroit<Zone> getZones () {
        return this.zones;
    }

    public void ajouterZone (Zone z) {
        //this.zones.add(z);
    }

    @Override
    public void entrer (ListeDePerso equipe) {
        
    }

    @Override
    public Gens aGens(Position p) {
        return null;
    }

    @Override
    public Endroit aEndroit(int i, int j) {
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
