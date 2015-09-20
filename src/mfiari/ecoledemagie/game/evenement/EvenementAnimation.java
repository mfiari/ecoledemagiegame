/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.evenement;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.animation.Animation;
import mfiari.ecoledemagie.game.condition.ConditionAnimation;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeObjetEndroit;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.lib.game.position.Position;
import mfiari.ecoledemagie.game.terminal.EcoleDeMagie_vueConsole_animation;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.jeu.Jeu;
import mfiari.lib.game.liste.ListeDeCondition;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.personnage.Gens;

/**
 *
 * @author mike
 */
public class EvenementAnimation extends Evenement {
    
    private ListeDeCondition<ConditionAnimation> animations;
    private EvenementDialogue dialogueDebutCombat;
    private EvenementDialogue dialogueFinCombat;
    private ListeDePerso<Personnage> equipe;
    private ListeDePerso<Ennemie> adversaire;
    private ListeObjetEndroit objets;
    private int pointeur;
    
    public EvenementAnimation () {
        this.animations = new ListeDeCondition<>();
        this.pointeur=-1;
        this.equipe = new ListeDePerso<>();
        this.adversaire = new ListeDePerso<>();
        this.objets = new ListeObjetEndroit();
    }
    
    public EvenementAnimation (Position endroitDeLevenement, Gens personneAquiParler, Objet objet, String titre) {
        super(endroitDeLevenement, personneAquiParler, objet, titre);
        this.animations = new ListeDeCondition<> ();
        this.pointeur=-1;
        this.equipe = new ListeDePerso<>();
        this.adversaire = new ListeDePerso<>();
        this.objets = new ListeObjetEndroit();
    }
    
    @Override
    public void initialise () {
        super.initialise();
        this.animations = new ListeDeCondition<> ();
        this.pointeur=-1;
        this.equipe = new ListeDePerso<>();
        this.adversaire = new ListeDePerso<>();
        this.objets = new ListeObjetEndroit();
    }
    
    public void ajouterEvenementDialogueDebutAnimation (EvenementDialogue e) {
        this.dialogueDebutCombat = e;
    }
    
     public boolean aDialogueDebutAnimation () {
        return this.dialogueDebutCombat != null;
    }
    
    public EvenementDialogue getDialogueDebutAnimation () {
        return this.dialogueDebutCombat;
    }
    
    public void conditionReussi () {
        this.pointeur++;
    }
    
    public boolean aAnimation () {
        return !this.animations.isEmpty();
    }
    
    public ConditionAnimation getAnimation () {
        return this.animations.get(pointeur);
    }
    
    public void ajouterAnimation (ConditionAnimation ani) {
        this.animations.add(ani);
    }
    
    public void ajouterEvenementDialogueFinAnimation (EvenementDialogue e) {
        this.dialogueFinCombat = e;
    }
    
     public boolean aDialogueFinAnimation () {
        return this.dialogueFinCombat != null;
    }
    
    public EvenementDialogue getDialogueFinAnimation () {
        return this.dialogueFinCombat;
    }
    
    public void ajouterPerso(Personnage perso) {
        this.equipe.ajouterPerso(perso);
    }
    
    public ListeDePerso<Personnage> getEquipe() {
        return this.equipe;
    }
    
    public void ajouterEnnemie(Ennemie ennemie) {
        this.adversaire.ajouterPerso(ennemie);
    }

    public ListeDePerso<Ennemie> getAdversaire() {
        return this.adversaire;
    }
    
    public void ajouterObjet(ObjetEndroit objet) {
        this.objets.ajoutObjet(objet);
    }
    
    public ListeObjetEndroit getObjets() {
        return this.objets;
    }

    @Override
    public boolean estFini () {
        return this.pointeur == this.animations.size();
    }

    public void activeEvenement(EcoleDeMagie jeu) {
        this.pointeur++;
        Animation ani = new Animation(jeu);
        EcoleDeMagie_vueConsole_animation vueConsoleAnimation = new EcoleDeMagie_vueConsole_animation(ani);
        ani.commencerAnimation();
    }

    @Override
    public void activeEvenement(Jeu jeu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
