/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.animation;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.condition.ConditionAnimation;
import mfiari.ecoledemagie.game.evenement.EvenementAnimation;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeObjetEndroit;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.texte.TexteVueAnimation;
import mfiari.ecoledemagie.game.ville.Endroit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;
import mfiari.lib.game.objet.ObjetEndroit;

/**
 *
 * @author mike
 */
public class Animation {
    
    private EcoleDeMagie jeu;
    private ListeDePerso<Personnage> equipe;
    private ListeDePerso<Ennemie> adversaire;
    private ListeObjetEndroit objets;
    private Endroit endroit;
    private String affichage;
    private char toucheEntree;
    private EvenementAnimation evenAni;
    private TexteVueAnimation textes;
    private PropertyChangeSupport pcsDeroulementAnimation = new PropertyChangeSupport(this);
    private Timer t;
    private int seconde;
    private int vitAnimation;
    
    class horloge extends TimerTask {
        @Override
        public void run() {
            if (seconde == vitAnimation) {
                Anime ();
                refresh();
                seconde = 0;
            } else {
                seconde++;
            }
        }
    }
    
    public Animation() {
        this.equipe = new ListeDePerso<>();
        this.adversaire = new ListeDePerso<>();
        this.objets = new ListeObjetEndroit();
        this.endroit = null;
    }

    public Animation(EcoleDeMagie jeu) {
        this.jeu = jeu;
        this.textes = TexteVueAnimation.getInstance();
        t = new Timer();
        //this.equipe = new ListeDePerso<Personnage>();
        this.equipe = this.jeu.getEquipe2();
        this.adversaire = new ListeDePerso<>();
        this.objets = new ListeObjetEndroit();
        this.endroit = (Endroit)this.jeu.getEndroit();
        this.vitAnimation = 5;
        this.seconde = 0;
    }
    
    public void setAffichage(String texte) {
        this.affichage = texte;
        this.pcsDeroulementAnimation.firePropertyChange("affichage", null, null);
    }
    
    private void setAffichageSansSuivant(String texte) {
        this.affichage = texte;
        this.pcsDeroulementAnimation.firePropertyChange("affichageSansSuivant", null, null);
    }
    
    public EcoleDeMagie getJeu() {
        return this.jeu;
    }
    
    public ListeDePerso<Personnage> getEquipe() {
        return this.equipe;
    }

    public ListeDePerso<Ennemie> getAdversaire() {
        return this.adversaire;
    }
    
    public ListeObjetEndroit getObjets() {
        return this.objets;
    }
    
    public Endroit getEndroit () {
        return this.endroit;
    }
    
    public String getAffichage() {
        return this.affichage;
    }
    
    private void setAction(String texte) {
        this.affichage = texte;
        this.pcsDeroulementAnimation.firePropertyChange("action", null, null);
    }
    
    public void setChoixAction (char action) {
        this.toucheEntree = action;
    }
    
    public void commencerAnimation() {
        this.evenAni = (EvenementAnimation) this.jeu.getEvenement();
        if (this.evenAni.aDialogueDebutAnimation()) {
            this.evenAni.getDialogueDebutAnimation().activeEvenement(this);
        }
        this.objets.ajoutObjet(this.evenAni.getAnimation().getObjet());
        while (!this.evenAni.estFini() && !this.jeu.getEquipe2().estKO()) {
            ConditionAnimation cdtAni = this.evenAni.getAnimation();
            if (cdtAni.getKey() != this.toucheEntree) {
                t.schedule(new horloge(), 0, 1 * 1000);
            }
            this.pcsDeroulementAnimation.firePropertyChange("afficherAnimation", null, null);
            this.setAction(cdtAni.getActionToDo());
            if (cdtAni.getKey() == this.toucheEntree) {
                this.deplacePerso();
            } else {
                this.setAffichage(cdtAni.getMessageFail());
                for (int i =0 ; i < this.jeu.getEquipe2().size() ; i++) {
                    this.jeu.getEquipe2().getPerso(i).recoitDegat(cdtAni.getPvPerdu());
                    this.setAffichage(this.textes.affichePvPerso(this.jeu.getEquipe2().getPerso(i)));
                }
            }
        }
        if (this.jeu.getEquipe2().estKO()) {
            jeu.gameOver(this.jeu.getEquipe2());
        }
        if (this.evenAni.aDialogueFinAnimation()) {
            //this.evenAni.getDialogueFinAnimation().activeEvenement(this.jeu);
        }
    }
    
    private void deplacePerso () {
        switch (this.toucheEntree) {
            case 'g':
                this.equipe.getPerso(0).getPosition().setPositionY(this.equipe.getPerso(0).getPosition().getPositionY()-1);
                break;
            case 'd':
                this.equipe.getPerso(0).getPosition().setPositionY(this.equipe.getPerso(0).getPosition().getPositionY()+1);
                break;
            case 'b':
                this.equipe.getPerso(0).getPosition().setPositionX(this.equipe.getPerso(0).getPosition().getPositionX()+1);
                break;
            case 's':
                this.equipe.getPerso(0).getPosition().setPositionZ(this.equipe.getPerso(0).getPosition().getPositionZ()+1);
                break;
            case 'a':
                //this.objets.enleverObjet(0);
                break;
        }
    }
    
    private void Anime () {
        ObjetEndroit obj = this.objets.getObjet(0);
        obj.getPosition().setPositionX(obj.getPosition().getPositionX() +1);
        if (this.equipe.aPerso(obj.getPosition()) != null) {
            this.setAffichageSansSuivant(this.evenAni.getAnimation().getMessageFail());
            this.equipe.aPerso(obj.getPosition()).recoitDegat(this.evenAni.getAnimation().getPvPerdu());
            this.setAffichageSansSuivant(this.textes.affichePvPerso(this.jeu.getEquipe2().getPerso(0)));
            obj.getPosition().setPositionX(obj.getPosition().getPositionX() -2);
        } else {
            if (obj.getPosition().equals(this.evenAni.getAnimation().getPos())) {
                this.setAffichageSansSuivant(this.evenAni.getAnimation().getMessageSuccess());
                this.evenAni.conditionReussi();
                this.objets.enleverObjet(obj);
                if (!this.evenAni.estFini()) {
                    if (this.evenAni.getAnimation().getObjet() != null) {
                        this.objets.ajoutObjet(this.evenAni.getAnimation().getObjet());
                    }
                }
            }
        }
        this.equipe.getPerso(0).getPosition().setPositionZ(0);
    }
    
    private void refresh () {
        this.pcsDeroulementAnimation.firePropertyChange("afficherAnimation", null, null);
    }
    
    public void ajouterEcouteurAnimation(PropertyChangeListener ecouteur) {
        this.pcsDeroulementAnimation.addPropertyChangeListener(ecouteur);
    }
    
}
