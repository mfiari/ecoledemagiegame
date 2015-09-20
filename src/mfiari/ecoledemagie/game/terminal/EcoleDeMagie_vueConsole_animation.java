/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.terminal;

import mfiari.ecoledemagie.game.animation.Animation;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeObjetEndroit;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.lib.game.position.Position;
import mfiari.ecoledemagie.game.texte.TexteVueAnimation;
import mfiari.ecoledemagie.game.ville.Endroit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class EcoleDeMagie_vueConsole_animation {
    
    Scanner sc = new Scanner(System.in);
    private Animation animation;
    private TexteVueAnimation textes;
    
    public EcoleDeMagie_vueConsole_animation (){
    }
    
    public EcoleDeMagie_vueConsole_animation (Animation ani){
        this.animation = ani;
        this.textes = TexteVueAnimation.getInstance();
        this.animation.ajouterEcouteurAnimation(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("affichage")) {
                    setAffichageAnimation();
                }
                if (evt.getPropertyName().equals("affichageSansSuivant")) {
                    setAffichageAnimationSansSuivant();
                }
                if (evt.getPropertyName().equals("action")) {
                    action();
                }
                if (evt.getPropertyName().equals("afficherAnimation")) {
                    afficherAnimation();
                }
            }
        });
    }
    
    public int testEntier () {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            System.out.println("entrez un entier");
            sc.next();
            return -1;
        }
    }
    
    public char testChar () {
        String s = this.sc.next();
        if (s.length() == 1) {
            return s.charAt(0);
        } else {
            return '0';
        }
    }
    
    public void suivant() {
        int a;
        do {
            System.out.println("1="+this.textes.suivant);
            a = this.testEntier();
        } while (a != 1);
    }
    
    public void setAffichageAnimation() {
        System.out.println(this.animation.getAffichage());
        this.suivant();
    }
    
    public void setAffichageAnimationSansSuivant() {
        System.out.println(this.animation.getAffichage());
    }
    
    public void action() {
        System.out.println(this.animation.getAffichage());
        this.animation.setChoixAction(this.testChar());
    }
    
    public void afficherAnimation() {
        ListeDePerso<Personnage> equipe = this.animation.getEquipe();
        ListeDePerso<Ennemie> adversaire = this.animation.getAdversaire();
        ListeObjetEndroit objets = this.animation.getObjets();
        Endroit e = this.animation.getEndroit();
        Position p;
        for (int i=0 ; i< 7 ; i++) {
            for (int j=0 ; j<14 ; j++) {
                p = new Position(i, j, 0, e, Orientation.face);
                if (objets.aObjetEndroit(p) != null) {
                    System.out.print(objets.aObjetEndroit(p).getNom().substring(0, 3));
                } else {
                    if (equipe.aPerso(p) != null) {
                        System.out.print(equipe.aPerso(p).getNom().substring(0, 3));
                    } else {
                        if (adversaire.aPerso(p) != null) {
                            System.out.print(adversaire.aPerso(p).getNom().substring(0, 3));
                        } else {
                            System.out.print("***");
                        }
                    }
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }
}
