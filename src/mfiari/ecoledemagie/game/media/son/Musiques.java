/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.media.son;

import mfiari.lib.game.liste.ListeDeMusique;
import mfiari.lib.game.media.Musique;
import mfiari.lib.game.texte.Texte;



/**
 *
 * @author mike
 */
public class Musiques {
    
    /*public static Musique combat_arene = new Musique("Combat Arène", "Pokemon_combat_arene", null);*/
    public static Musique combat_arene = null;
    
    public static ListeDeMusique getListeDeMusique () {
        ListeDeMusique musiques = new ListeDeMusique();
        switch (Texte.langue) {
            case fr :
                musiques.ajouterMusique(Musiques.combat_arene);
                break;
            case en :
                musiques.ajouterMusique(Musiques.combat_arene);
                break;
        }
        return musiques;
    }
}
