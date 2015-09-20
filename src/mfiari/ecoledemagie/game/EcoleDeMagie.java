/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game;

import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.perso.Personnages;
import mfiari.ecoledemagie.game.perso.Equipe;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.combat.Combat;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.menu.Menu;
import mfiari.ecoledemagie.game.ville.Environnement;
import mfiari.ecoledemagie.game.ville.Quartier;
import java.sql.ResultSet;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.jeu.Jeu;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.objet.ObjetEndroitClassique;
import mfiari.lib.game.objet.ObjetEndroitCoffre;
import mfiari.lib.game.objet.ObjetEndroitObservable;
import mfiari.lib.game.objet.ObjetEndroitPassage;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.texte.Langue;

/**
 *
 * @author mike
 */
public class EcoleDeMagie extends Jeu {
    
    private boolean termmineJeu;
    private ResultSet result;
    private ListeDePerso<Personnage> equipe;
    private ListeDePerso<Personnage> listePerso;
    private ListeDeSort sorts;
    private final ListeDePerso<Ennemie> adversaire;
    private Evenement e;
    private ListeDEndroit carte_gmm;
    private int partie;
    private Langue langue;
    private String nomPersoPrincipal;
    private Equipe equipeDuPerso;

    public EcoleDeMagie() {
        super(true);
        this.langue = Langue.fr;
        this.adversaire = new ListeDePerso<>();
    }
    
    public void setNomPersoPrincipal(String nom) {
        this.nomPersoPrincipal = nom;
    }
    
    public String getNomPersoPrincipal() {
        return this.nomPersoPrincipal;
    }
    
    @Override
    public void afficherEndroit (mfiari.lib.game.ville.Endroit endroit) {
        this.endroit = endroit;
        this.equipe = new ListeDePerso();
        this.equipe.ajouterPerso(Personnages.tidus);
        this.pcsControlleurVue.firePropertyChange("afficheEndroit", null, null);
    }

    public void jouer(EvenementQuete jeu, ListeDePerso equipe, ListeDePerso listePerso, ListeDEndroit carte_gmm, int partie) {
        super.jouer(jeu, (Personnage)equipe.getPerso(0));
        this.jeu = jeu;
        this.equipe = equipe;
        this.carte_gmm = carte_gmm;
        this.listePerso = listePerso;
        this.partie = partie;
        this.queteAnnexe = null;
        this.termmineJeu = false;
        this.endroit = this.equipe.getPerso(0).getPosition().getEndroit();
        do {
            this.continuer();
        } while(!this.termmineJeu);
        this.termineJeu();
    }

    public ListeDePerso<Personnage> getPersos() {
        return this.listePerso;
    }

    public ListeDEndroit getCarte_gmm() {
        return this.carte_gmm;
    }

    public int getPartie () {
        return this.partie;
    }
    
    public Equipe getEquipe () {
        return this.equipeDuPerso;
    }
    
    public void setEquipe (Equipe equipe) {
        this.equipeDuPerso = equipe;
    }

    public ResultSet getResult() {
        return this.result;
    }

    public void setResultSet(ResultSet result) {
        this.result = result;
        this.pcsControlleurVue.firePropertyChange("voirParties", null, null);
    }

    public void choix(String texte) {
        this.affichage = texte;
        this.pcsControlleurVue.firePropertyChange("choix", null, null);
    }

    @Override
    public void setChoixAction(int choix) {
        this.choix = choix;
        int positionX;
        int positionY;
        switch (this.choix) {
            case (1):
                positionY = this.equipe.getPerso(0).getPosition().getPositionY();
                positionX = this.equipe.getPerso(0).getPosition().getPositionX() - 1;
                this.bouger(positionX, positionY);
                break;
            case (2):
                positionY = this.equipe.getPerso(0).getPosition().getPositionY();
                positionX = this.equipe.getPerso(0).getPosition().getPositionX() + 1;
                this.bouger(positionX, positionY);
                break;
            case (3):
                positionY = this.equipe.getPerso(0).getPosition().getPositionY() + 1;
                positionX = this.equipe.getPerso(0).getPosition().getPositionX();
                this.bouger(positionX, positionY);
                break;
            case (4):
                positionY = this.equipe.getPerso(0).getPosition().getPositionY() - 1;
                positionX = this.equipe.getPerso(0).getPosition().getPositionX();
                this.bouger(positionX, positionY);
                break;
            case (5):
                this.action();
                break;
            case (6):
                Menu menu = new Menu(this.equipe, this);
                menu.Menu();
                break;
        }
    }

    public void bouger(int positionX, int positionY) {
        if (this.endroit.aObjetEndroit(this.equipe.getPerso(0).getPosition()) instanceof ObjetEndroitPassage) {
            ObjetEndroitPassage obj = (ObjetEndroitPassage) this.endroit.aObjetEndroit(this.equipe.getPerso(0).getPosition());
            if (positionX < 0 || positionX == this.endroit.getLargeur() || positionY < 0 || positionY == this.endroit.getLongueur()) {
                if (!this.quete.verifieEndroitAccessible(obj.getEndroitDarive())) {
                    obj.prendre(this.equipe.getPerso(0));
                    this.endroit = this.equipe.getPerso(0).getPosition().getEndroit();
                } else {
                }
            } else {
                this.equipe.setPosition(new Position(positionX, positionY, endroit));
            }
        } else {
            if (positionX >= 0 && positionX < this.endroit.getLargeur() && positionY >= 0 && positionY < this.endroit.getLongueur()) {
                Position p = new Position (positionX, positionY, 0, this.endroit, Orientation.face);
                if (this.endroit.aObjetEndroit(p) != null) {
                    if (this.endroit.aObjetEndroit(p) instanceof ObjetEndroitPassage) {
                        ObjetEndroitPassage obj = (ObjetEndroitPassage) this.endroit.aObjetEndroit(p);
                        if (!obj.getType().equals(Type_objet.chemin)) {
                            obj.prendre(this.equipe.getPerso(0));
                            this.endroit = this.equipe.getPerso(0).getPosition().getEndroit();
                        } else {
                            this.equipe.setPosition(new Position(positionX, positionY, endroit));
                        }
                    } else if (this.endroit.aObjetEndroit(p) instanceof ObjetEndroitClassique) {
                        ObjetEndroitClassique obj = (ObjetEndroitClassique) this.endroit.aObjetEndroit(p);
                        if (obj.getType().estBloquant()) {
                            this.pcsControlleurVue.firePropertyChange("afficheEndroitInnaccessible", null, null);
                        } else if (!this.endroit.aEnvironnement(p).equals(Environnement.aucun)) {
                            Environnement environnement = (Environnement) this.endroit.aEnvironnement(p);
                            this.perso.setPosition(p);
                            Quartier q = (Quartier) this.endroit;
                            if (q.aPerso(environnement)) {
                                Ennemie pk = q.getEnnemis(environnement);
                                if (pk != null) {
                                    this.adversaire.supprimeTous();
                                    this.adversaire.ajouterPerso(pk);
                                    Combat c = new Combat(equipe, adversaire, this.getEvenement());
                                    c.commencerCombat(false);
                                    this.pcsControlleurVue.firePropertyChange("afficheEndroit", null, null);
                                }
                            }
                        } else {
                            this.perso.setPosition(p);
                        }
                    } else if (!this.endroit.aEnvironnement(p).equals(Environnement.aucun)) {
                        Environnement environnement = (Environnement) this.endroit.aEnvironnement(p);
                        this.perso.setPosition(p);
                        Quartier q = (Quartier) this.endroit;
                        if (q.aPerso(environnement)) {
                            Ennemie pk = q.getEnnemis(environnement);
                            if (pk != null) {
                                this.adversaire.supprimeTous();
                                this.adversaire.ajouterPerso(pk);
                                Combat c = new Combat(equipe, adversaire, this.getEvenement());
                                c.commencerCombat(false);
                                this.pcsControlleurVue.firePropertyChange("afficheEndroit", null, null);
                            }
                        }
                    }
                } else {
                    if (this.endroit.aGens(p) != null) {
                        this.setAffichage("impossible");
                    } else {
                        if (this.endroit.aEndroit(new Position (positionX, positionY)) != null) {
                            //this.endroit.aEndroit(new Position (positionX, positionY)).entrer(this.equipe);
                            this.endroit = this.equipe.getPerso(0).getPosition().getEndroit();
                        } else {
                            this.equipe.setPosition(new Position(positionX, positionY, endroit));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void action() {
        mfiari.lib.game.personnage.Gens gensAquiParler = this.verifieGensAquiParler();
        if (gensAquiParler != null) {
            String str = gensAquiParler.Parler();
            while (!str.isEmpty()) {
                this.setParole(str);
                str = gensAquiParler.Parler();
            }
        } else {
            mfiari.lib.game.objet.ObjetEndroit objetAobserver = this.verifieObjetAObserver();
            if (objetAobserver != null) {
                if (objetAobserver instanceof ObjetEndroitObservable) {
                    ObjetEndroitObservable Objet_endroit_observable = (ObjetEndroitObservable) objetAobserver;
                    this.setAffichage(Objet_endroit_observable.observer());
                } else {
                    if (objetAobserver instanceof ObjetEndroitCoffre) {
                        ObjetEndroitCoffre Objet_endroit_coffre = (ObjetEndroitCoffre) objetAobserver;
                        Objet objet = (Objet)Objet_endroit_coffre.ouvrir();
                        if (objet == null) {
                            this.setAffichage(Objet_endroit_coffre.observer());
                        } else {
                            this.setAffichage("this.textes.vousRecevez(objet.getNom())");
                            this.equipe.ajouterObjet(objet);
                        }
                    }
                }
            }
        }
    }

    public ListeDePerso<Personnage> getEquipe2() {
        return this.equipe;
    }

    public ListeDeSort getTec() {
        return this.sorts;
    }

    public Personnage getPersonnage() {
        return this.equipe.getPerso(0);
    }

    public ListeDePerso getAdversaires() {
        return this.adversaire;
    }

    public void nouvellePartie() {
        this.pcsControlleurVue.firePropertyChange("nouvellePartie", null, null);
    }
    
    public void choixEquipe() {
        this.choix = 0;
        do {
            do {
                this.pcsControlleurVue.firePropertyChange("choixEquipe", null, null);
            } while (this.choix < 1 || this.choix > 9);
            if (this.choix == 9) {
                this.pcsControlleurVue.firePropertyChange("info", null, null);
            } else {
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

    public void extra() {
        this.pcsControlleurVue.firePropertyChange("extra", null, null);
    }

    public void menuExtra () {
        this.pcsControlleurVue.firePropertyChange("menuExtra", null, null);
    }

    public void suivant() {
        this.pcsControlleurVue.firePropertyChange("suivant", null, null);
    }

    public Langue getLangue () {
        return this.langue;
    }

    public void debutJeu() {
        this.pcsControlleurVue.firePropertyChange("choixLangue", null, null);
        this.langue = Langue.values()[this.choix-1];
        this.pcsControlleurVue.firePropertyChange("debutJeu", null, null);
    }

    public void changerNom(String nom) {
        this.affichage = nom;
        this.pcsControlleurVue.firePropertyChange("changerNom", null, null);
    }

    public void gameOver(ListeDePerso equipe) {
        if (equipe.estKO()) {
            this.pcsControlleurVue.firePropertyChange("perdu", null, null);
            /*Demarrage d = new Demarrage(this);
            d.jeu();*/
        }
    }
    
    public void termineJeu() {
        this.pcsControlleurVue.firePropertyChange("gagne", null, null);
    }

    /*public void menuObjetMagasin(ListeDObjets sac, String affichage) {
        this.sac = sac;
        this.affichage = affichage;
        this.pcsDeroulementJeu.firePropertyChange("afficherObjetMagasin", null, null);
    }*/

    @Override
    public void bouger(Position p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gameOver() {
        return false;
    }

    @Override
    public int getArgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public mfiari.lib.game.objet.Sac getSac() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
