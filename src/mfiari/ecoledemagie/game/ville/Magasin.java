/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.lib.game.liste.ListeObjet;

/**
 *
 * @author mike
 */
public class Magasin extends Batiments {
    
    private ListeObjet magasin;

    public Magasin () {
        super();
        this.magasin = new ListeObjet();
    }
    
    public Magasin (String nom, int positionX, int positionY, int longueur, int largeur, int etageMax) {
        super(nom, positionX, positionY, longueur, largeur, 0, etageMax);
        this.magasin = new ListeObjet();
    }

    public void ajouterObjet (Objet o) {
        this.magasin.ajoutObjet(o);
    }

    public void enleverObjet (Objet o) {
        this.magasin.enleverObjet(o);
    }

    public void acheter (EcoleDeMagie jeu, ListeDePerso equipe) {
        /*jeu.menuObjetMagasin(this.magasin, "Que voulez vous acheter ?");
        int a = jeu.getChoix();
        if (a>0 && a<=this.magasin.size()) {
            jeu.choix("combien ?");
            if (jeu.getChoix()>0 && jeu.getChoix()*this.magasin.getObjet(a).getPrixAchat() <= equipe.getArgent()) {
                equipe.getSac().acheter(this.magasin.getObjet(a), jeu.getChoix());
            }
        }*/
    }

    public void vendre (EcoleDeMagie jeu, ListeDePerso equipe) {
        /*jeu.menuObjetMagasin(equipe.getSac(), "Que voulez vous acheter ?");
        int a = jeu.getChoix();
        if (a>0 && a<=equipe.getSac().size()) {
            jeu.choix("combien ?");
            if (jeu.getChoix()>0) {
                equipe.getSac().vendre(a, jeu.getChoix());
                equipe.setArgent(equipe.getSac().getObjet(a).getPrixVente()*jeu.getChoix());
            }
        }*/
    }
}
