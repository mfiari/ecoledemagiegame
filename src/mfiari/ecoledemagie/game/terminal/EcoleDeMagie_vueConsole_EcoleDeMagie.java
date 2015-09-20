/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.terminal;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.lib.game.position.Position;
import mfiari.ecoledemagie.game.texte.TexteVueEcoleDeMagie;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class EcoleDeMagie_vueConsole_EcoleDeMagie extends EcoleDeMagie_vueConsole {
    
    private EcoleDeMagie jeu;
    private int choix;
    private TexteVueEcoleDeMagie textes;

    public EcoleDeMagie_vueConsole_EcoleDeMagie() {
    }
    
    public EcoleDeMagie_vueConsole_EcoleDeMagie(EcoleDeMagie jeu) {
        super(TexteVueEcoleDeMagie.getInstance());
        this.jeu = jeu;
        this.textes = TexteVueEcoleDeMagie.getInstance();
        this.jeu.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("continuer")) {
                    continuer();
                }
                if (evt.getPropertyName().equals("gagnerCombat")) {
                    gagnerCombat();
                }
                if (evt.getPropertyName().equals("perdu")) {
                    Perdu();
                }
                if (evt.getPropertyName().equals("gagne")) {
                    Gagne();
                }
                if (evt.getPropertyName().equals("parole")) {
                    parole();
                }
                if (evt.getPropertyName().equals("affichage")) {
                    affichage();
                }
                if (evt.getPropertyName().equals("confirmation")) {
                    confirmation();
                }
                if (evt.getPropertyName().equals("choix")) {
                    choix();
                }
                if (evt.getPropertyName().equals("suivant")) {
                    suivant();
                }
                if (evt.getPropertyName().equals("actionJeu")) {
                    actionJeu();
                }
                if (evt.getPropertyName().equals("afficheEndroit")) {
                    afficheEndroit();
                }
                if (evt.getPropertyName().equals("afficherObjetMagasin")) {
                    afficherObjetMagasin();
                }
            }
        });
    }

    public void continuer() {
        /*
        on affiche la ville, le batiment... dans laquelle se trouve le personnage
         */
    }

    public void actionJeu() {
        System.out.println("1."+this.textes.haut+"      2."+this.textes.bas+"      3."+this.textes.droite);
        System.out.println("4."+this.textes.gauche+"    5."+this.textes.action+"   6."+this.textes.menu);
        System.out.println(this.textes.queFaire);
        this.choix = testEntier();
        this.jeu.setChoixAction(this.choix);
    }

    public void Perdu() {
        /*
         * message lorsque l'on a perdu la partie (game over)
         */
        System.out.println("GAME OVER");
        this.suivant();
    }
    
    public void Gagne() {
        /*
         * message lorsque l'on a perdu la partie (game over)
         */
        System.out.println("Vous avez fini le jeu!");
        this.suivant();
    }

    public void gagnerCombat() {
        /*
         * message lorsque l'on gagne un combat
         */
    }

    public void parole() {
        /*
         * affichage lorsque qu'un personnage parle
         */
        System.out.println(this.jeu.getParole());
        this.suivant();
    }

    public void affichage() {
        /*
         * pour faire des affichages divers
         */
        System.out.println(this.jeu.getAffichage());
        this.suivant();
    }

    public void confirmation () {
        System.out.println(this.jeu.getAffichage());
        System.out.println("1."+this.textes.oui+"     2."+this.textes.non);
        this.choix = testEntier();
        if (this.choix == 1) {
            this.jeu.setConfirmation(true);
        } else {
            this.jeu.setConfirmation(false);
        }
    }

    public void choix () {
        System.out.println(this.jeu.getAffichage());
        this.choix = testEntier();
    }

    public void afficheEndroit() {
        mfiari.lib.game.ville.Endroit e = this.jeu.getEndroit();
        System.out.print(e.getNom());
        Position p;
        for (int i = 0; i < e.getLargeur(); i++) {
            System.out.println();
            for (int j = 0; j < e.getLongueur(); j++) {
                p = new Position(i, j, 0, e, Orientation.face);
                if (this.jeu.getEquipe2().getPerso(0).getPosition().equals(p)) {
                    System.out.print(this.jeu.getEquipe2().getPerso(0).getNom().substring(0, 3));
                } else {
                    if (e.aEndroit(new Position(i, j)) != null) {
                        if (e.aEndroit(new Position(i, j)).getNom().length() >3) {
                            System.out.print(e.aEndroit(new Position(i, j)).getNom().substring(0, 3));
                        } else {
                            System.out.print(e.aEndroit(new Position(i, j)).getNom());
                        }
                    } else {
                        if (e.aGens(p) != null) {
                            if (e.aGens(p).getNom().length() >3) {
                                System.out.print(e.aGens(p).getNom().substring(0, 3));
                            } else {
                                System.out.print(e.aGens(p).getNom());
                            }
                        } else {
                            if (e.aObjetEndroit(p) != null) {
                                if (e.aObjetEndroit(p).getNom().length() >3) {
                                    System.out.print(e.aObjetEndroit(p).getNom().substring(0, 3));
                                } else {
                                    System.out.print(e.aObjetEndroit(p).getNom());
                                }
                            } else {
                                System.out.print("+++");
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    public void afficherObjetMagasin() {
        /*System.out.println(this.jeu.getAffichage());
        for (int i =0 ; i<this.jeu.getSac().size() ; i++) {
            System.out.println((i) + "." + this.jeu.getSac().getObjet(i).getNom());
        }
        System.out.println("0."+ this.textes.quitter);
        this.jeu.setChoix(testEntier());*/
    }

}
