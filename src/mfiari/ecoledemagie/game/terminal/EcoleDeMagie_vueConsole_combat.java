/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.terminal;

import mfiari.ecoledemagie.game.combat.AbstractCombat;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.objet.ObjetCombat;
import mfiari.ecoledemagie.game.objet.ObjetDivers;
import mfiari.ecoledemagie.game.objet.Sac;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.texte.TexteVueCombat;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class EcoleDeMagie_vueConsole_combat {
    
    Scanner sc = new Scanner(System.in);
    private AbstractCombat combat;
    private TexteVueCombat textes;
    
    public EcoleDeMagie_vueConsole_combat (){
    }
    
    public EcoleDeMagie_vueConsole_combat (AbstractCombat c){
        this.combat = c;
        this.textes = TexteVueCombat.getInstance();
        this.combat.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("afficherTerrainCombat")) {
                    afficherTerrainCombat();
                }
                if (evt.getPropertyName().equals("afficherMenuJoueur")) {
                    afficherMenuJoueur();
                }
                if (evt.getPropertyName().equals("afficherTechniques")) {
                    afficherTechniques();
                }
                if (evt.getPropertyName().equals("affichage")) {
                    setAffichageCombat();
                }
                if (evt.getPropertyName().equals("afficherEnnemies")) {
                    afficherEnnemies();
                }
                if (evt.getPropertyName().equals("afficherEquipe")) {
                    afficherEquipe();
                }
                if (evt.getPropertyName().equals("afficherObjetCombat")) {
                    afficherObjetCombat();
                }
                if (evt.getPropertyName().equals("afficherMenuFinDeCombat")) {
                    afficherMenuFinDeCombat();
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
    
    public void suivant() {
        int a;
        do {
            System.out.println("1="+this.textes.suivant);
            a = this.testEntier();
        } while (a != 1);
    }
    
    public void afficherTerrainCombat() {
        ListeDePerso<Personnage> equipe = this.combat.getEquipe();
        ListeDePerso<Ennemie> adversaire = this.combat.getAdversaire();
        for (int i=0 ; i< 7 ; i++) {
            for (int j=0 ; j<14 ; j++) {
                switch (i) {
                    default:
                        System.out.print("***");
                        break;
                    case (1):
                        switch (adversaire.size()) {
                            case (1):
                                if (j!=7) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(adversaire.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (2):
                                if (j!=5 && j!=9) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(adversaire.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (3):
                                if (j!=5 && j!=7 && j!=9) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(adversaire.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (4):
                                if (j!=3 && j!=5 && j!=7 && j!=9) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(adversaire.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (5):
                                if (j!=3 && j!=5 && j!=7 && j!=9 && j!=11) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(adversaire.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                        }
                        break;
                    case (5):
                        switch (equipe.size()) {
                            case (1):
                                if (j!=7) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(equipe.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (2):
                                if (j!=5 && j!=9) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(equipe.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (3):
                                if (j!=5 && j!=7 && j!=9) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(equipe.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (4):
                                if (j!=3 && j!=5 && j!=7 && j!=9) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(equipe.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                            case (5):
                                if (j!=3 && j!=5 && j!=7 && j!=9 && j!=11) {
                                    System.out.print("***");
                                } else {
                                   System.out.print(equipe.getPerso(0).getNom().substring(0, 3));
                                }
                                break;
                        }
                        break;
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public void setAffichageCombat() {
        System.out.println(this.combat.getAffichage());
        this.suivant();
    }

    public void afficherMenuJoueur() {
        Personnage perso = this.combat.getPerso();
        StringBuilder texte = new StringBuilder();
        texte.append(this.textes.pv);
        texte.append(":");
        texte.append(Math.round(perso.getPv()));
        texte.append("/");
        texte.append(Math.round(perso.getPvInitial()));
        texte.append("\t");
        texte.append(this.textes.pm);
        texte.append(":");
        texte.append(perso.getPm());
        texte.append("/");
        texte.append(perso.getPmInitial());
        System.out.println(texte.toString());
        System.out.println("1."+this.textes.attaquer+"	   2.Sort");
        System.out.println("3."+this.textes.examiner+"     4."+this.textes.objet);
        System.out.println("5."+this.textes.seProteger+"   6."+this.textes.perso);
        this.combat.setChoix(this.testEntier());
    }

    public void afficherEnnemies() {
        ListeDePerso adversaire = this.combat.getAdversaire();
        for (int i = 0; i < adversaire.size(); i++) {
            System.out.println((i + 1) + "." + adversaire.getPerso(i).getNom());
        }
        System.out.println("6."+this.textes.retour);
        System.out.println(this.textes.quiAttaquer);
        this.combat.setChoixPerso(this.testEntier());
    }

    public void afficherEquipe() {
        ListeDePerso equipe = this.combat.getEquipe();
        String affichage = this.combat.getAffichage();
        for (int i = 0; i < equipe.size(); i++) {
            System.out.println((i + 1) + "." + equipe.getPerso(i).getNom());
        }
        System.out.println("0."+this.textes.retour);
        System.out.println(affichage);
        this.combat.setChoixPerso(this.testEntier());
    }

    public void afficherTechniques() {
        ListeDeSort techniques = this.combat.getPerso().getTechniques();
        for (int j = 0; j < techniques.size(); j++) {
            System.out.println((j + 1) + "." + techniques.getSort(j).getNom());
        }
        System.out.println("0."+this.textes.retour);
        System.out.println(this.textes.quelTecUtilise);
        this.combat.setChoixPerso(this.testEntier());
    }

    public void afficherObjetCombat() {
        Sac sac = this.combat.getPerso().getSac();
        for (int i = 0; i < sac.getSac(this.combat.getChoixSac()).size(); i++) {
            Objet obj = sac.getObjet(this.combat.getChoixSac(), i);
            if (obj instanceof ObjetDivers) {
                ObjetDivers objD = (ObjetDivers) obj;
                System.out.println(objD.getNom() + " " + objD.getQuantite());
            } else {
                if (obj instanceof ObjetCombat) {
                    ObjetCombat objC = (ObjetCombat) obj;
                    System.out.println(objC.getNom() + " " + objC.getQuantite());
                } else {
                    System.out.println(sac.getObjet(this.combat.getChoixSac(), i).getNom());
                }
            }
        }
        System.out.println();
        System.out.println("0."+this.textes.quitter);
        System.out.println(this.textes.queFaire);
        this.combat.setChoixPerso(this.testEntier());
    }
    
    public void afficherMenuFinDeCombat() {
        ListeDePerso<Personnage> equipe = this.combat.getEquipe();
        for (int i = 0; i < equipe.size(); i++) {
            Personnage perso = equipe.getPerso(i);
            System.out.println(perso.getNom() + "\t"+this.textes.niv+":" + perso.getNiveau());
            System.out.println(this.textes.pv+": " + perso.getPv() + "/" + perso.getPvInitial());
            System.out.println(this.textes.pm+": " + perso.getPm() + "/" + perso.getPmInitial());
        }
        this.suivant();
    }
}
