/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.extra;

import mfiari.ecoledemagie.game.combat.Combat;
import mfiari.ecoledemagie.game.connexionBD.Connexion;
import mfiari.ecoledemagie.game.demarrage.CreationObjet;
import mfiari.ecoledemagie.game.demarrage.CreationPerso;
import mfiari.ecoledemagie.game.demarrage.CreationTechnique;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.lib.game.controlleur.ControlleurVue;

/**
 *
 * @author mike
 */
public class Duel extends ControlleurVue {
    
    //private EcoleDeMagie jeu;
    private ListeDePerso<Personnage> equipeJ1;
    private ListeDePerso<Ennemie> equipeJ2;
    private ListeDePerso<Personnage> personnages;
    private CreationPerso creationPerso;
    private CreationObjet creationObjet;
    private CreationTechnique creationTechnique;
    private String affichage;
    private boolean confirmation;

    public Duel() {
        this.creationPerso = new CreationPerso(this);
        this.creationObjet = new CreationObjet ();
        this.creationTechnique = new CreationTechnique ();
        this.personnages = this.creationPerso.getPersos();
        this.equipeJ1 = new ListeDePerso<>();
        this.equipeJ2 = new ListeDePerso<>();
    }
    
    public void menu () {
        do {
            this.pcsControlleurVue.firePropertyChange("menuPrincipal", null, null);
            switch (this.choix) {
                case (1):
                    this.combatSolo();
                    break;
                case (2):
                    this.tournoi();
                    break;
                case (3):
                    this.mission();
                    break;
            }
        } while (this.choix != 0);
    }
    
    public void setAffichage(String texte) {
        this.affichage = texte;
        this.pcsControlleurVue.firePropertyChange("affichage", null, null);
    }
    
    public String getAffichage() {
        return this.affichage;
    }

    public boolean confirmation(String texte) {
        this.affichage = texte;
        this.pcsControlleurVue.firePropertyChange("confirmation", null, null);
        return this.confirmation;
    }
    
    public void confirmer (boolean confirm) {
        this.confirmation = confirm;
    }
    
    public ListeDePerso<Personnage> getPersonnages () {
        return this.personnages;
    }
    
    public ListeDePerso<Personnage> getEquipeJ1 () {
        return this.equipeJ1;
    }
    
    public void ajouterPersoJ1 (Personnage perso) {
        this.equipeJ1.ajouterPerso(perso);
    }
    
    public void enleverPersoJ1 (Personnage perso) {
        this.equipeJ1.enleverPerso(perso);
    }
    
    public ListeDePerso<Ennemie> getEquipeJ2 () {
        return this.equipeJ2;
    }
    
    public void ajouterPersoJ2 (Ennemie perso) {
        this.equipeJ2.ajouterPerso(perso);
    }
    
    public void enleverPersoJ2 (Ennemie perso) {
        this.equipeJ2.enleverPerso(perso);
    }
    
    private void combatSolo() {
        do {
            this.pcsControlleurVue.firePropertyChange("combatSolo", null, null);
            switch (choix) {
                case (1):
                    this.unContreUn();
                    break;
                case (2):
                    this.combatEnEquipe();
                    break;
            }
        } while (choix != 0);
    }
    
    private void unContreUn() {
        do {
            this.pcsControlleurVue.firePropertyChange("unContreUn", null, null);
            switch (choix) {
                case (1):
                    this.J1VSOrdi(1);
                    break;
                case (2):
                    this.J1VSJ2(1);
                    break;
                case (3):
                    this.OrdiVsOrdi(1);
                    break;

            }
        } while (choix != 0);
    }
    
    private void J1VSOrdi(int nbPerso) {
        while (this.equipeJ1.size() < nbPerso) {
            this.pcsControlleurVue.firePropertyChange("choixPersoJ1", null, null);
            if (this.choix != 0) {
                if (this.choix == 100) {
                    this.ajouterPersoJ1(personnages.getPerso((int) (Math.random() * personnages.size() - 1)));
                } else {
                    if (this.choix > 0 && this.choix <= personnages.size()) {
                        this.ajouterPersoJ1(personnages.getPerso(this.choix -1));
                    }
                }
            }
            this.pcsControlleurVue.firePropertyChange("afficherPersoChoisit", null, null);
        }
        while (this.equipeJ2.size() < nbPerso) {
            this.pcsControlleurVue.firePropertyChange("choixPersoJ2", null, null);
            if (choix != 0) {
                if (choix == 100) {
                    this.ajouterPersoJ2(personnages.getPerso((int) (Math.random() * personnages.size() - 1)).toEnnemie());
                } else {
                    if (choix > 0 && choix <= personnages.size()) {
                        this.ajouterPersoJ2(personnages.getPerso(choix -1).toEnnemie());
                    }
                }
            }
            this.pcsControlleurVue.firePropertyChange("afficherPersoChoisit", null, null);
        }
        this.affichage = "confirmer ?";
        this.pcsControlleurVue.firePropertyChange("confirmation", null, null);
        if (this.confirmation) {
            Combat combat = new Combat(this.equipeJ1, this.equipeJ2, this);
            /*combat.commencerCombatDuelJ1VSOrdi();*/
            combat.combatDuelJ1VSOrdi();
        }
    }
    
    private void J1VSJ2(int nbPerso) {
        this.pcsControlleurVue.firePropertyChange("J1VSJ2", null, null);
    }
    
    private void OrdiVsOrdi(int nbPerso) {
        this.pcsControlleurVue.firePropertyChange("OrdiVsOrdi", null, null);
    }
    
    private void combatEnEquipe() {
        do {
            this.pcsControlleurVue.firePropertyChange("unContreUn", null, null);
            switch (this.choix) {
                case (1):
                    this.J1VSOrdi(5);
                    break;
                case (2):
                    this.J1VSJ2(5);
                    break;
                case (3):
                    this.OrdiVsOrdi(5);
                    break;

            }
        } while (this.choix != 0);
    }
    
    private void tournoi() {
        this.pcsControlleurVue.firePropertyChange("tournoi", null, null);
    }
    
    private void mission() {
        this.pcsControlleurVue.firePropertyChange("mission", null, null);
    }
    
    private void combatContrePC() {
        do {
            this.pcsControlleurVue.firePropertyChange("combatSolo", null, null);
            switch (this.choix) {
                case (1):
                    /* 1 contre 1 */
                    this.choixPerso(1);
                    break;
                case (2):
                    /* 3 contre 3 */
                    this.choixPerso(3);
                    break;
                case (3):
                    /* 5 contre 5*/
                    this.choixPerso(5);
                    break;
                case (4):
                    /* combat contre boss */
                    this.choixPerso(4);
                    break;

            }
        } while (this.choix != 0);

    }

    private void combatMultiJoueur() {
        do {
            this.pcsControlleurVue.firePropertyChange("combatSolo", null, null);
            switch (this.choix) {
                case (1):
                    /* 1 contre 1 */
                    this.choixPerso(1);
                    this.choixPerso(1);
                    break;
                case (2):
                    /* 3 contre 3 */
                    this.choixPerso(3);
                    this.choixPerso(3);
                    break;
                case (3):
                    /* 5 contre 5*/
                    this.choixPerso(5);
                    this.choixPerso(5);
                    break;

            }
        } while (this.choix != 0);
    }

    private void choixPerso(int nbPerso) {
        if (this.confirmation("voulez vous charger une partie ?")) {
            Connexion c = new Connexion();
            int partie = c.parties();
            ListeDePerso p = this.creationPerso.getPersos();
            ListeDePerso listP = new ListeDePerso();
            //this.equipeJ1 = c.chargerPersonnages(partie, p, this.creationTechnique.getListeTec(), this.creationObjet.getListeDObjet(), null);
        } else {
        }
        for (int i=0 ; i<nbPerso ; i++) {
            //this.jeu.afficherEquipe(null, "qui prendre ?");
            //this.equipeJ1.ajouterPerso(this.persos.getPerso(this.jeu.getChoix()));
        }
    }
    
}
