/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.terminal;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.menu.Menu;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.texte.TexteVueMenu;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;
import mfiari.lib.game.liste.ListeObjet;

/**
 *
 * @author mike
 */
public class EcoleDeMagie_vueConsole_menu {
    
    private int choix;
    Scanner sc = new Scanner(System.in);
    private TexteVueMenu textes;
    private Menu menu;
    
    public EcoleDeMagie_vueConsole_menu() {
    }
    
    public EcoleDeMagie_vueConsole_menu(Menu menu) {
        this.menu = menu;
        this.textes = TexteVueMenu.getInstance();
        this.menu.ajouterEcouteurDeMenu(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            }
        });
    }
    
    public int testEntier() {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            System.out.println("entrez un entier");
            sc.next();
            return -1;
        }
    }

    public int affichePerso (ListeDePerso<Personnage> perso, String affichage) {
        for (int i = 0; i < perso.size(); i++) {
            Personnage p = perso.getPerso(i);
            System.out.println(p.getNom() + "\t"+this.textes.etat+" : " + p.getEtat() + "\n"+this.textes.niv+" : " + p.getNiveau() + 
                    "\t"+this.textes.pv+" : " + p.getPv() + "/" + p.getPvInitial() + "\t"+this.textes.pm+" : " + p.getPm() + "/" + p.getPmInitial());
        }
        System.out.println(affichage);
        this.choix = testEntier();
        return this.choix;
    }
    public int afficherMenu (ListeDePerso perso, EcoleDeMagie jeu) {

        System.out.print("1."+this.textes.objet+" \t 2."+this.textes.technique+" \t");
        System.out.print("3."+this.textes.quete+" \t");
        System.out.print("4."+this.textes.etat+" \n5."+this.textes.equiper+" \t");
        System.out.print("6."+this.textes.cuisine+" \t 7."+this.textes.configuration+" \t");
        System.out.println("8."+this.textes.sauvegarder+" \t 0."+this.textes.quitter);
        System.out.println();
        System.out.println(perso.getArgent() + this.textes.pieceOr+" \t "+this.textes.tempsjeu+": " + jeu.getHeureTempsJeu() + ":" +
                jeu.getMinuteTempsJeu());
        System.out.println();
        return this.affichePerso(perso, this.textes.queFaire);
    }

    public int afficherMenuObjet (ListeObjet sac, String affichage) {
        System.out.println("1."+this.textes.objetDivers+"     2."+this.textes.objet+"     3."+this.textes.arme+"     4."+this.textes.armure+
                "     5."+this.textes.chapeau);
        System.out.println("6."+this.textes.botte+"     7."+this.textes.accessoire+"     8."+this.textes.objetRare+"     0."+this.textes.quitter);
        System.out.println();
        System.out.println(affichage);
        for (int i =0 ; i<sac.size() ; i++) {
            System.out.println((i+9) + "." + sac.getObjet(i).getNom());
        }
        System.out.println(this.textes.queFaire);
        this.choix = testEntier();
        return this.choix;
    }

    public int afficherMenuTechnique (ListeDePerso perso) {
        System.out.println("0."+this.textes.quitter);
        return this.affichePerso(perso, this.textes.quelPersoChoisir);
    }

    public int afficherMenuTechnique_choixTec (ListeDeSort tec, String affichage) {

        for (int i=0 ; i<tec.size() ; i++) {
            System.out.println(i+1 + "." + tec.getSort(i).getNom());
        }
        System.out.println("0."+this.textes.quitter);
        System.out.println(affichage);
        this.choix = testEntier();
        return this.choix;
    }

    public int afficherMenuEtat (Personnage perso) {
        System.out.println(perso.getNom());
        System.out.println(this.textes.pv+": " + perso.getPv() + "\t" + "\t" + perso.getTec().getSort(0).getNom()
                + "\n"+this.textes.pm+": " + perso.getPm() + "\t" + "\t" + perso.getTec().getSort(1).getNom()
                + "\n"+this.textes.force+": " + perso.getForce() + "\t" + "\t" + perso.getTec().getSort(2).getNom()
                + "\n"+this.textes.def+": " + perso.getDefense() + "\t" + "\t" + perso.getTec().getSort(3).getNom()
                + "\n"+this.textes.magie+": " + perso.getMagie() + "\t" + "\t" + perso.getTec().getSort(4).getNom()
                + "\n"+this.textes.res+": " + perso.getResistance() + "\t" + "\t" + perso.getTec().getSort(5).getNom()
                + "\n"+this.textes.vit+": " + perso.getVitesse() + " \n"+this.textes.prec+": " + perso.getPrecision() 
                + " \n"+this.textes.agi+": " + perso.getEsquive()
                + "\n"+this.textes.exp+" : " + perso.getExperience() + "\n"+this.textes.nivSuiv+": " /*+ perso.getExpRestant()*/);
        System.out.println("0."+this.textes.quitter);
        this.choix = testEntier();
        return this.choix;
    }
    
}
