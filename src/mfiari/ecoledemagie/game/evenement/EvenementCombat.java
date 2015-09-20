/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.evenement;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.combat.Combat;
import mfiari.ecoledemagie.game.condition.ConditionAnimationCombat;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.lib.game.position.Position;
import java.util.ArrayList;
import mfiari.lib.game.jeu.Jeu;
import mfiari.lib.game.personnage.Gens;

/**
 *
 * @author mike
 */
public class EvenementCombat extends mfiari.lib.game.evenements.EvenementCombat {
    
    private EvenementDialogue dialogueDebutCombat;
    private ArrayList<ConditionAnimationCombat> animations;
    private EvenementDialogue dialogueFinCombat;
    private ListeDePerso adversaire;

    public EvenementCombat () {
        super();
        this.animations = new ArrayList<> ();
        this.adversaire = new ListeDePerso ();
        this.dialogueDebutCombat = null;
        this.dialogueFinCombat = null;
    }

    public EvenementCombat (Position endroitDeLevenement, Gens personneAquiParler, Objet objet, String titre) {
        super(endroitDeLevenement, personneAquiParler, objet, titre, null);
        this.animations = new ArrayList<> ();
        this.adversaire = new ListeDePerso ();
        this.dialogueDebutCombat = null;
        this.dialogueFinCombat = null;
    }
    
    @Override
    public void initialise () {
        super.initialise();
        this.animations = new ArrayList<> ();
        this.adversaire = new ListeDePerso ();
        this.dialogueDebutCombat = null;
        this.dialogueFinCombat = null;
    }

    public ListeDePerso getAdversaire () {
        return this.adversaire;
    }

    public void ajouterAdversaire (Ennemie e) {
        this.adversaire.ajouterPerso(e);
    }
    
    public void ajouterEvenementDialogueDebutCombat (EvenementDialogue e) {
        this.dialogueDebutCombat = e;
    }
    
     public boolean aDialogueDebutCombat () {
        return this.dialogueDebutCombat != null;
    }
    
    public EvenementDialogue getDialogueDebutCombat () {
        return this.dialogueDebutCombat;
    }
    
    public void ajouterAnimations (ConditionAnimationCombat ani) {
        this.animations.add(ani);
    }
    
    public void ajouterEvenementDialogueFinCombat (EvenementDialogue e) {
        this.dialogueFinCombat = e;
    }
    
     public boolean aDialogueFinCombat () {
        return this.dialogueFinCombat != null;
    }
    
    public EvenementDialogue getDialogueFinCombat () {
        return this.dialogueFinCombat;
    }
    
    public boolean aConditionAnimation () {
        return !this.animations.isEmpty();
    }

    @Override
    public void activeEvenement(Jeu jeu2) {
        EcoleDeMagie jeu = (EcoleDeMagie)jeu2;
        Combat c = new Combat (jeu.getEquipe2(), this.getAdversaire(), this);
        if (this.aDialogueDebutCombat() || this.aDialogueFinCombat() || !this.conditions.isEmpty()) {
            c.commencerCombat(true);
        } else {
            c.commencerCombat(false);
        }
    }
    
}
