/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.evenement;

import mfiari.ecoledemagie.game.animation.Animation;
import mfiari.ecoledemagie.game.combat.AbstractCombat;
import mfiari.ecoledemagie.game.combat.Combat;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class EvenementDialogue extends mfiari.lib.game.evenements.EvenementDialogue {

    public EvenementDialogue() {
    }

    public EvenementDialogue(Position endroitDeLevenement, Gens personneAquiParler, Objet objet, String titre) {
        super(endroitDeLevenement, personneAquiParler, objet, titre);
    }
    
    public void activeEvenement(AbstractCombat c) {
        /*int jetonPerso;
        Parole p;
        for (int i = 1; i <= this.longueurDialogue; i++) {
            jetonPerso = 0;
            p = null;
            while(p == null && jetonPerso < this.gens.size()) {
                p = this.gens.getPerso(jetonPerso).Parler(this, i);
                jetonPerso++;
            }
            c.setAffichage(p.toString());
        }*/
    }
    
    public void activeEvenement(Animation ani) {
        /*int jetonPerso;
        Parole p;
        for (int i = 1; i <= this.longueurDialogue; i++) {
            jetonPerso = 0;
            p = null;
            while(p == null && jetonPerso < this.gens.size()) {
                p = this.gens.getPerso(jetonPerso).Parler(this, i);
                jetonPerso++;
            }
            ani.setAffichage(p.toString());
        }*/
    }
    
}
