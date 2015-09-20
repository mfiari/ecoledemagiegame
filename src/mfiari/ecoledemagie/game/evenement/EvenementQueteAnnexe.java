/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.evenement;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class EvenementQueteAnnexe extends EvenementQuete {
    
    private boolean commence;
    
    public EvenementQueteAnnexe() {
        super("");
    }
    
    public EvenementQueteAnnexe(Position endroitDeLevenement, Gens personneAquiParler, Objet objet, String titre, boolean sortir) {
        super(sortir, endroitDeLevenement, personneAquiParler, objet, titre);
        this.commence=false;
    }
     
    @Override
    public void initialise () {
        super.initialise();
        this.commence=false;
    }

    public void commence () {
        this.commence=true;
    }

    public boolean estActiver (EcoleDeMagie jeu) {
        return this.commence;
    }
    
}
