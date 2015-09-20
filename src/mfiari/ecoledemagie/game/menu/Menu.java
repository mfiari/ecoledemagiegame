/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.menu;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.connexionBD.Connexion;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.objet.ObjetCombat;
import mfiari.ecoledemagie.game.objet.ObjetDivers;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.perso.Personnage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import mfiari.lib.game.liste.ListeObjet;
import mfiari.lib.game.liste.ListeObjetVendable;

/**
 *
 * @author mike
 */
public class Menu {
    
    private ListeDePerso<Personnage> equipe;
    private EcoleDeMagie jeu;
    private int choix;
    private String affichage;
    private PropertyChangeSupport pcsDeroulementMenu = new PropertyChangeSupport(this);

    public Menu() {
    }
    
    public Menu(ListeDePerso equipe, EcoleDeMagie jeu) {
        this.equipe = equipe;
        this.jeu = jeu;
    }
    
    public void setAffichage(String texte) {
        this.affichage = texte;
        this.pcsDeroulementMenu.firePropertyChange("affichage", null, null);
    }

    public void affichePersoMenu(String texte) {
        do {
            this.pcsDeroulementMenu.firePropertyChange("affichePersoMenu", null, null);
        } while (this.choix < 0 || this.choix > this.equipe.size());
    }

    public void utiliserObjet(Objet objets) {
        if (objets.getType().equals(Type_objet.soin_stat)) {
            if (this.equipe.getPerso(this.choix).enFormeStat()) {
                this.setAffichage("stat deja au max");
            } else {
                this.equipe.getPerso(this.choix).utilObjet((ObjetCombat)objets);
            }
        } else {
            if (objets.getType().equals(Type_objet.soin_etat)) {
                if (this.equipe.getPerso(this.choix).enFormeEtat()) {
                    this.setAffichage("aucune alteration d'etat");
                } else {
                    this.equipe.getPerso(this.choix).utilObjet((ObjetCombat)objets);
                }
            } else {
                if (objets.getType().equals(Type_objet.soin_all)) {
                    if (this.equipe.getPerso(this.choix).enForme()) {
                        this.setAffichage("stat deja au max et aucune alteration d'etat");
                    } else {
                        this.equipe.getPerso(this.choix).utilObjet((ObjetCombat)objets);
                    }
                }
            }
        }
    }

    public void equiperObjet(ObjetCombat objets) {
        this.equipe.getPerso(this.choix).getSac().acheter(objets, 1);
    }

    public void Menu() {
        this.pcsDeroulementMenu.firePropertyChange("afficherMenu", null, null);
        switch (this.choix) {
            case (0):
                break;
            case (1):
                this.menuObjet();
                break;
            case (2):
                this.menuTechnique();
                break;
            case (3):
                this.menuQuete();
                break;
            case (4):
                this.menuEtat();
                break;
            case (5):
                this.menuEquiper();
                break;
            case (6):
                this.menuCuisine();
                break;
            case (7):
                this.menuConfiguration();
                break;
            case (8):
                this.menuSauvegarder();
                break;
            default:
                this.Menu();
                break;
        }
    }

    public void menuObjet_objetDivers() {
        Objet[] objetsDivers;
        objetsDivers = this.equipe.getPerso(this.choix).getSac().getObjets_divers();
        this.affichage = "Objet divers";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
        if (this.choix < 0) {
            //this.choix = this.menuObjet_objetDivers();
        } else {
            if (this.choix > 8) {
                Objet obj = objetsDivers[this.choix - 9];
                if (obj.getType().equals(Type_objet.soin_all) || obj.getType().equals(Type_objet.soin_etat) || obj.getType().equals(Type_objet.soin_stat)) {
                    this.affichePersoMenu("l'utiliser sur quel perso ?");
                    if (this.choix != 0) {
                        this.utiliserObjet(obj);
                    }
                } else {
                    if (objetsDivers[this.choix - 9] instanceof ObjetCombat) {
                        this.affichePersoMenu("quel perso voulez vous equiper ?");
                        if (this.choix != 0) {
                            if (this.equipe.getPerso(this.choix).getArme().getType().equals(objetsDivers[this.choix].getType())) {
                                this.equiperObjet((ObjetCombat)objetsDivers[this.choix]);
                            }
                        }
                    } else {
                        /*objet rare */
                    }
                }
                //this.choix = this.menuObjet_objetDivers();
            }
        }
    }

    public void menuObjet_objet() {
        ListeObjetVendable<ObjetDivers> objets = this.equipe.getPerso(this.choix).getSac().getObjets();
        this.affichage = "Objets";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
        if (this.choix > 8) {
            this.affichePersoMenu("l'utiliser sur quel perso ?");
            if (this.choix != 0) {
                this.utiliserObjet(objets.getObjet(choix));
            }
            //this.choix = this.menuObjet_objet();
        }
    }

    public void menuObjet_arme() {
        ListeObjetVendable<ObjetCombat> objetsArme = this.equipe.getPerso(this.choix).getSac().getArmes();
        this.affichage = "Arme";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
        if (this.choix > 8) {
            this.affichePersoMenu("qui equiper ?");
            if (this.choix != 0) {
                if (this.equipe.getPerso(this.choix).getArme().getType().equals(objetsArme.getObjet(this.choix).getType())) {
                    this.equiperObjet(objetsArme.getObjet(choix));
                }
            }
            //this.choix = this.menuObjet_arme();
        }
    }

    public void menuObjet_armure() {
        ListeObjetVendable<ObjetCombat> objetsArmure = this.equipe.getPerso(this.choix).getSac().getArmures();
        this.affichage = "Armure";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
        if (this.choix > 8) {
            this.affichePersoMenu("qui equiper ?");
            if (this.choix != 0) {
                if (this.equipe.getPerso(this.choix).getArmure().getType().equals(
                        objetsArmure.getObjet(this.choix).getType())) {
                    this.equiperObjet(objetsArmure.getObjet(choix));
                }
            }
            //this.choix = this.menuObjet_armure();
        }
    }

    public void menuObjet_accessoire() {
        ListeObjetVendable<ObjetCombat> objetsAcc = this.equipe.getPerso(this.choix).getSac().getChapeaux();
        this.affichage = "Accessoire";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
        if (this.choix > 8) {
            this.affichePersoMenu("qui equiper ?");
            if (this.choix != 0) {
                if (this.equipe.getPerso(this.choix).getAcc1().getType().equals(objetsAcc.getObjet(this.choix).getType())) {
                    this.equiperObjet(objetsAcc.getObjet(choix));
                }
            }
            //this.choix = this.menuObjet_accessoire();
        }
    }

    public void menuObjet_botte() {
        ListeObjetVendable<ObjetCombat> objetsBotte = this.equipe.getPerso(this.choix).getSac().getBottes();
        this.affichage = "botte";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
        if (this.choix > 8) {
            this.affichePersoMenu("qui equiper ?");
            if (this.choix != 0) {
                if (this.equipe.getPerso(this.choix).getBotte().getType().equals(objetsBotte.getObjet(this.choix).getType())) {
                    this.equiperObjet(objetsBotte.getObjet(choix));
                }
            }
            //this.choix = this.menuObjet_botte();
        }
    }

    public void menuObjet_chapeau() {
        ListeObjetVendable<ObjetCombat> objetsChapeau = this.equipe.getPerso(this.choix).getSac().getChapeaux();
        this.affichage = "chapeau";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
        if (this.choix > 8) {
            this.affichePersoMenu("qui equiper ?");
            if (this.choix != 0) {
                if (this.equipe.getPerso(this.choix).getChapeau().getType().equals(objetsChapeau.getObjet(this.choix).getType())) {
                    this.equiperObjet(objetsChapeau.getObjet(choix));
                }
            }
            //this.choix = this.menuObjet_arme();
        }
    }

    public void menuObjet_objetRare() {
        ListeObjet<Objet> objets = this.equipe.getPerso(this.choix).getSac().getObjetsRare();
        this.affichage = "rare";
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
    }

    //ce programme affiche ce qu'il y a dans le menu objet
    public void menuObjet() {
        switch (this.choix) {
            case (1):
                this.menuObjet_objetDivers();
                break;
            case (2):
                this.menuObjet_objet();
                break;
            case (3):
                this.menuObjet_arme();
                break;
            case (4):
                this.menuObjet_armure();
                break;
            case (5):
                this.menuObjet_chapeau();
                break;
            case (6):
                this.menuObjet_botte();
                break;
            case (7):
                this.menuObjet_accessoire();
                break;
            case (8):
                this.menuObjet_objetRare();
                break;
            case (0):
                this.Menu();
                break;
            default:
                this.menuObjet_objetDivers();
                break;
        }
    }

    // ce programme permet de voir les stats et les techniques des personnage
    public void menuTechnique() {
        int a, b;
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuSort", null, null);
        /*this.jeu.menuTechnique_choisirTec(this.equipe.getPersonnage(this.choix).getTec(), "Quel technique remplacer ?");
        a = this.jeu.getChoix();
        this.jeu.menuTechnique_choisirTec(this.equipe.getPersonnage(this.choix).getTechniques(), "Quel technique placer ?");
        b = this.jeu.getChoix();
        this.equipe.getPersonnage(this.choix).getTec().remplaceSort(this.equipe.getPersonnage(this.choix).getTechniques().getSort(b), a);*/
    }

    //ce programme permet de voir les quete en cour ou effectuer
    public void menuQuete() {
    }

    //ce programme permet de voir les stats et les equipement des perso
    public void menuEtat() {
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuEtat", null, null);
    }

    //ce programme permet de modifier l'equipement de vos perso
    public void menuEquiper() {
        /*int a, b;
        ListeDObjets objets = new ListeDObjets();
        this.pcsDeroulementMenu.firePropertyChange("afficherMenuPerso", null, null);
        a = this.choix;
        switch (a) {
            case (1):
                for (int i = 0; i < this.equipe.getSac().size(); i++) {
                    if (this.equipe.getSac().getObjet(i) instanceof Equip
                            && this.equipe.getSac().getObjet(i).getType().equals(Type_objet.baton)) {
                        objets.ajoutObjet(this.equipe.getSac().getObjet(i));
                    }
                }
                this.affichage = "arme";
                this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
                break;
            case (2):
                for (int i = 0; i < this.equipe.getSac().size(); i++) {
                    if (this.equipe.getSac().getObjet(i) instanceof Equip
                            && this.equipe.getSac().getObjet(i).getType().equals(Type_objet.armure)) {
                        objets.ajoutObjet(this.equipe.getSac().getObjet(i));
                    }
                }
                this.affichage = "armure";
                this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
                break;
            case (3):
                for (int i = 0; i < this.equipe.getSac().size(); i++) {
                    if (this.equipe.getSac().getObjet(i) instanceof Equip
                            && this.equipe.getSac().getObjet(i).getType().equals(Type_objet.chapeau)) {
                        objets.ajoutObjet(this.equipe.getSac().getObjet(i));
                    }
                }
                this.affichage = "chapeau";
                this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
                break;
            case (4):
                for (int i = 0; i < this.equipe.getSac().size(); i++) {
                    if (this.equipe.getSac().getObjet(i) instanceof Equip
                            && this.equipe.getSac().getObjet(i).getType().equals(Type_objet.botte)) {
                        objets.ajoutObjet(this.equipe.getSac().getObjet(i));
                    }
                }
                this.affichage = "botte";
                this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
                break;
            case (5):
                for (int i = 0; i < this.equipe.getSac().size(); i++) {
                    if (this.equipe.getSac().getObjet(i) instanceof Equip
                            && this.equipe.getSac().getObjet(i).getType().equals(Type_objet.accessoire)) {
                        objets.ajoutObjet(this.equipe.getSac().getObjet(i));
                    }
                }
                this.affichage = "accessoire";
                this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
                break;
            case (6):
                for (int i = 0; i < this.equipe.getSac().size(); i++) {
                    if (this.equipe.getSac().getObjet(i) instanceof Equip
                            && this.equipe.getSac().getObjet(i).getType().equals(Type_objet.accessoire)) {
                        objets.ajoutObjet(this.equipe.getSac().getObjet(i));
                    }
                }
                this.affichage = "accessoire";
                this.pcsDeroulementMenu.firePropertyChange("afficherMenuObjet", null, null);
                break;

        }
        b = this.choix;
        if (b > 8) {
            this.equiperObjet(objets);
        }*/
    }

    //ce programme permet de voir vos recette de cuisine
    public void menuCuisine() {
        this.setAffichage("vous n'avez pas de cuisine");
        this.Menu();
    }

    //ce programme permet de modifier la configuration du jeu
    public void menuConfiguration() {
        this.setAffichage("vous ne pouvez pas modifier les configurations");
        this.Menu();
    }

    public void menuSauvegarder () {
        Connexion c = new Connexion(this.jeu);
        int partie = c.parties();
        this.setAffichage("sauvegarde en cour, ne pas eteindre");
        c.sauvegarderPartie(partie, this.equipe.getPerso(0), this.jeu.getHeureTempsJeu(), this.jeu.getMinuteTempsJeu(), this.jeu.getSecondeTempsJeu());
        for (int i=0 ; i<this.jeu.getPersos().size() ; i++) {
            /*if (this.jeu.getPersos().getD_UNI(i) != null) {
                c.sauvegarderD_UNI(this.jeu.getPersos().getD_UNI(i), partie, equipe);
            }*/
            if (this.jeu.getPersos().getPerso(i) != null) {
                c.sauvegarderPerso(this.jeu.getPersos().getPerso(i), partie, equipe);
            }
        }
        //c.sauvegarderVille(this.jeu.getCarte_gmm(), partie);

        c.sauvegarderEvenement(this.jeu.getEvenementJeu(), partie);
        //c.sauvegarderObjet(this.equipe.getSac(), partie);
        this.setAffichage("sauvegarde reussi");
        this.Menu();
    }

    public void ajouterEcouteurDeMenu(PropertyChangeListener propertyChangeListener) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
