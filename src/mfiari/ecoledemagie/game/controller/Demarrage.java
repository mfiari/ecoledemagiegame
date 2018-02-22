/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.controller;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.demarrage.CreationEnnemie;
import mfiari.ecoledemagie.game.demarrage.CreationEvenement;
import mfiari.ecoledemagie.game.demarrage.CreationObjet;
import mfiari.ecoledemagie.game.demarrage.CreationPerso;
import mfiari.ecoledemagie.game.demarrage.CreationTechnique;
import mfiari.ecoledemagie.game.demarrage.CreationVille;
import mfiari.ecoledemagie.game.extra.MenuExtra;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.objet.Sac;
import mfiari.ecoledemagie.game.perso.Equipe;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.ville.Endroit;
import mfiari.ecoledemagie.game.ville.EndroitFactory;
import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.texte.Langue;
import mfiari.lib.game.texte.Texte;

/**
 *
 * @author mike
 */
public class Demarrage extends ControlleurVue {
    
    //private EcoleDeMagie jeu;
    private EvenementQuete quete_jeu;
    private Sac sac;
    private ListeDEndroit carte;
    private ListeDEndroit endroitVisite;
    private ListeDEndroit carte_actuel;
    private int partie;
    private ListeDePerso<Personnage> listePerso;
    private ListeDePerso<Personnage> equipe;
    private CreationPerso creationPerso;
    private CreationVille creationVille;
    private CreationEvenement creationEvenement;
    private CreationEnnemie creationEnnemie;
    private CreationObjet creationObjet;
    private CreationTechnique creationTechnique;
    private Equipe equipeDuPerso;

    public Demarrage() {
        super(true);
        
    }
    
    public Equipe getEquipe () {
        return this.equipeDuPerso;
    }
    
    public void setEquipe (Equipe equipe) {
        this.equipeDuPerso = equipe;
    }
    
    private void debutJeu() {
        do {
            this.pcsControlleurVue.firePropertyChange("choixLangue", null, null);
        } while (this.choix < 1 || this.choix > Langue.values().length);
        Texte.langue = Langue.values()[this.choix-1];
    }
    
    public void choixEquipe() {
        this.choix = 0;
        this.equipeDuPerso = null;
        do {
            do {
                this.pcsControlleurVue.firePropertyChange("choixEquipe", null, null);
            } while (this.choix < 1 || this.choix > 9);
            if (this.choix == 9) {
                this.pcsControlleurVue.firePropertyChange("info", null, null);
            } else {
                this.equipeDuPerso = Equipe.values()[this.choix -1];
                do {
                    this.pcsControlleurVue.firePropertyChange("confirmerChoix", null, null);
                    if (this.choix == 3) {
                        this.equipeDuPerso = null;
                    } else {
                        if (this.choix == 2) {
                            do {
                                do {
                                    this.pcsControlleurVue.firePropertyChange("menuStatDebut", null, null);
                                } while (this.choix < 1 || this.choix > 3);
                                if (this.choix != 3) {
                                    this.pcsControlleurVue.firePropertyChange("afficheStatDebut", null, null);
                                }
                            } while(this.choix != 3);
                        }
                    }
                } while (this.choix == 3 && this.equipeDuPerso != null);
            }
        } while (this.equipeDuPerso == null);
    }

    public void nouvellePartie() {
        //this.creationVille = new CreationVille();
        this.creationEnnemie = new CreationEnnemie();
        this.creationObjet = new CreationObjet();
        this.creationTechnique = new CreationTechnique ();
        this.choixEquipe();
        this.creationPerso = new CreationPerso(this.equipeDuPerso);
        //this.sac = new Sac();
        this.equipe = this.creationPerso.getEquipe();
        //this.carte = this.creationVille.getEndroit();
        this.creationEvenement = new CreationEvenement(this.equipe.getPerso(0));
        this.quete_jeu = this.creationEvenement.getEvenement();
        this.listePerso = this.creationPerso.getPersos();
        
        EndroitFactory endroitFactory = new EndroitFactory();
        Endroit endroit = endroitFactory.createEndroit("1");
        
        this.equipe.getPersonnage(0).getPosition().setEndroit(endroit);
        
        this.carte = new ListeDEndroit();
        this.carte.ajouterEndroit(endroit);
        /*Connexion c = new Connexion(this.jeu);
        this.carte_actuel = new ListeDEndroit();
        this.endroitVisite = new ListeDEndroit();
        if (c.connectionActive()) {
            this.partie = c.parties();
            //this.carte_actuel.add(Endroits.maisonMarco_quartierHabitant_medieville_zoneMedieville_zoneMedicoru_continent);
            c.CreerPartie(partie, equipe.getPerso(0));
            for (int i = 0; i < this.carte.size(); i++) {
                c.enregistrerVille(this.carte.getEndroit(i), this.partie);
            }
            c.enregistrerTechnique(this.creationTechnique.getListeTec(), this.partie);
            c.sauvegarderObjet(this.creationObjet.getListeDObjet(), this.partie);

            for (int i = 0; i < this.listePerso.size(); i++) {
                Personnage p = this.listePerso.getPerso(i);
                if (p!=null) {
                    c.enregistrerPerso(p, partie, this.equipe);
                }
            }
            //c.enregistrerVille(Endroits.maisonMarco_quartierHabitant_medieville_zoneMedieville_zoneMedicoru_continent,this.partie);
            c.sauvegarderEvenement(this.quete_jeu, this.partie);
        }*/
        EcoleDeMagie jeu = new EcoleDeMagie();
        jeu.jouer(this.quete_jeu, this.equipe, this.listePerso, this.carte, this.partie);
    }

    public void continuer() {
        /*Connexion c = new Connexion(this.jeu);
        this.partie = c.parties();
        String nomPersoPrincipal = c.getNonPersoPrincipale(this.partie);
        this.jeu.setNomPersoPrincipal(nomPersoPrincipal);
        ListeDePerso<Personnage> listeP = new ListeDePerso();
        this.creationPerso = new CreationPerso(this.jeu, true);
        this.listePerso = this.creationPerso.getPersos();
        for (int i=0 ; i < this.listePerso.size() ; i++) {
            listeP.ajouterPerso(this.listePerso.getPerso(i));
        }
        listeP.getPersonnage("marco").changerNom(nomPersoPrincipal);
        ListeDObjet listeObj = this.creationObjet.getListeDObjet();
        ListeDeSort listeTec = this.creationTechnique.getListeTec();
        this.carte = this.creationVille.getEndroit();
        ListeDEndroit e = this.carte;
        listeP = c.chargerPersonnages(this.partie, listeP, listeTec, listeObj, e);
        for (int i=0 ; i <listeP.size() ; i++) {
            this.listePerso.enleverPerso(this.listePerso.getPersonnage(listeP.getPerso(i).getNom()));
            this.listePerso.ajouterPerso(listeP.getPerso(i));
        }
        this.listePerso.getPersonnage(c.getNonPersoPrincipale(this.partie)).getPosition().getEndroit().enleverPersonnage(
        this.listePerso.getPersonnage(c.getNonPersoPrincipale(this.partie)));
        this.equipe = c.chargerEquipe(this.partie, listeP, listeTec, listeObj, e);
        this.carte = c.chargerEndroit(e,this.partie);
        this.sac = c.chargerObjet(listeObj, this.partie);
        int nbEvenementChapitre = c.chargerEvenementChapitre(this.partie);
        int nbQueteAnnex = c.chargerQueteAnnexe(nbEvenementChapitre, this.partie);
        this.creationEvenement = new CreationEvenement(this.equipe.getPerso(0), this.jeu);
        this.quete_jeu = this.creationEvenement.getEvenement();
        EvenementQuete chapitre = (EvenementQuete) this.quete_jeu.getEvenement();
        if (nbEvenementChapitre-1 > 0) {
            for (int i=0 ; i<nbEvenementChapitre ; i++) {
                chapitre.FinirEvenement();
                chapitre.enleverGens();
                this.quete_jeu.evenementSuivant();
                chapitre = (EvenementQuete) this.quete_jeu.getEvenement();
            }
        }
        int nbEvenementQuete = c.chargerEvenementQuete(nbEvenementChapitre, this.partie);
        EvenementQuete quete = (EvenementQuete) chapitre.getEvenement();
        if (nbEvenementQuete-1 > 0) {
            for (int i=0 ; i<nbEvenementQuete ; i++) {
                quete.FinirEvenement();
                quete.enleverGens();
                chapitre.evenementSuivant();
                quete = (EvenementQuete) chapitre.getEvenement();
            }
        }
        int nbQuete = c.chargerQuete(nbEvenementQuete, nbEvenementChapitre, this.partie);
        Evenement evenement = quete.getEvenement();
        if (nbQuete-1 > 0) {
            for (int i=0 ; i<nbQuete ; i++) {
                evenement.FinirEvenement();
                evenement.enleverGens();
                quete.evenementSuivant();
                evenement = quete.getEvenement();
            }
        }*/
    }

    public void extra() {
        System.out.println("extra dem");
        MenuExtra m = new MenuExtra ();
        m.menu();
    }

    public void jeu() {
        this.debutJeu();
        do {
            this.pcsControlleurVue.firePropertyChange("debutJeu", null, null);
            EcoleDeMagie jeu;
            switch (this.choix) {
                case (1):
                    nouvellePartie();
                    break;
                case (2):
                    continuer();
                    jeu = new EcoleDeMagie();
                    jeu.jouer(this.quete_jeu, this.equipe, this.listePerso, this.carte, this.partie);
                    break;
                case (3):
                    extra();
                    break;
            }
        } while (this.choix != 0);
    }

}