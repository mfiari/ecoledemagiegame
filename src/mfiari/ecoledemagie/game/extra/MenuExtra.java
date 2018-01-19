/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.extra;

import mfiari.ecoledemagie.game.reseau.MenuReseau;
import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.lib.game.texte.Langue;
import mfiari.lib.game.texte.Texte;

/**
 *
 * @author mike
 */
public class MenuExtra extends ControlleurVue {

    public MenuExtra() {
        super(true);
    }
    
    public void menu () {
        System.out.println("MenuExtra menu");
        do {
            this.pcsControlleurVue.firePropertyChange("afficherMenu", null, null);
            switch (choix) {
                case (1):
                    this.tutoriel();
                    break;
                case (2):
                    this.enLigne();
                    break;
                case (3):
                    this.multijoueur();
                    break;
                case (4):
                    this.combat();
                    break;
                case (5):
                    this.musiqueEtVideo();
                    break;
                case (6):
                    this.configuration();
                    break;

            }
        } while (choix != 0);
    }
    
    private void tutoriel() {
    }

    private void enLigne() {
        MenuReseau menu = new MenuReseau();
        menu.menu();
    }
    
    private void multijoueur() {
        Duel d = new Duel();
        d.menu();
    }

    private void combat() {
    }

    private void musiqueEtVideo() {
    }

    private void configuration() {
        do {
            this.pcsControlleurVue.firePropertyChange("configuration", null, null);
            switch (this.choix) {
                case 1:
                    do {
                        this.pcsControlleurVue.firePropertyChange("choixLangue", null, null);
                    } while (this.choix != 1 && this.choix != 2);
                    Texte.langue = Langue.values()[this.choix-1];
                    this.pcsControlleurVue.firePropertyChange("actualiseLangue", null, null);
                    break;
            }
        } while (this.choix != 0);
        this.choix = -1;
    }

}
