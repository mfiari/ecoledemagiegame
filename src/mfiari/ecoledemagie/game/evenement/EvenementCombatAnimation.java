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
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.lib.game.position.Position;
import mfiari.ecoledemagie.game.terminal.EcoleDeMagie_vueConsole_combat;
import java.util.ArrayList;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.jeu.Jeu;
import mfiari.lib.game.personnage.Gens;

/**
 *
 * @author mike
 */
public class EvenementCombatAnimation extends Evenement {
    
    private EvenementDialogue dialogueDebutCombat;
    private EvenementDialogue dialogueFinCombat;
    private ArrayList<ConditionAnimationCombat> animations;
    private ListeDePerso adversaire;
    private ListeDePerso persos;
    private int pointeur;

    public EvenementCombatAnimation () {
        this.animations = new ArrayList<> ();
        this.dialogueDebutCombat = null;
        this.dialogueFinCombat = null;
        this.adversaire = new ListeDePerso ();
        this.persos = new ListeDePerso ();
        this.pointeur=-1;
    }

    public EvenementCombatAnimation (Position endroitDeLevenement, Gens personneAquiParler, Objet objet, String titre) {
        super(endroitDeLevenement, personneAquiParler, objet, titre);
        this.animations = new ArrayList<> ();
        this.dialogueDebutCombat = null;
        this.dialogueFinCombat = null;
        this.adversaire = new ListeDePerso ();
        this.persos = new ListeDePerso ();
        this.pointeur=-1;
    }
    
    @Override
    public void initialise () {
        super.initialise();
        this.animations = new ArrayList<> ();
        this.dialogueDebutCombat = null;
        this.dialogueFinCombat = null;
        this.adversaire = new ListeDePerso ();
        this.persos = new ListeDePerso ();
        this.pointeur=-1;
    }

    public ListeDePerso getAdversaire () {
        return this.adversaire;
    }

    public void ajouterAdversaire (Ennemie e) {
        this.adversaire.ajouterPerso(e);
    }
    
    public ListeDePerso getPerso () {
        return this.persos;
    }

    public void ajouterPerso (Personnage p) {
        this.persos.ajouterPerso(p);
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
        if (ani.getAttaquant() != null) {
            if (ani.getAttaquant() instanceof Personnage) {
                Personnage p = (Personnage)ani.getAttaquant();
                if (!this.persos.contains(p)) {
                    this.ajouterPerso(p);
                }
            } else {
                if (ani.getAttaquant() instanceof Ennemie) {
                    Ennemie e = (Ennemie)ani.getAttaquant();
                    if (!this.adversaire.contains(e)) {
                        this.ajouterAdversaire(e);
                    }
                }
            }
        }
        if (ani.getAttaquer() != null) {
            if (ani.getAttaquer() instanceof Personnage) {
                Personnage p = (Personnage)ani.getAttaquer();
                if (!this.persos.contains(p)) {
                    this.ajouterPerso(p);
                }
            } else {
                if (ani.getAttaquer() instanceof Ennemie) {
                    Ennemie e = (Ennemie)ani.getAttaquer();
                    if (!this.adversaire.contains(e)) {
                        this.ajouterAdversaire(e);
                    }
                }
            }
        }
    }
    
    public ConditionAnimationCombat getConditionAnimation () {
        return this.animations.get(this.pointeur);
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

    public void conditionReussi () {
        this.pointeur++;
    }
    
    public boolean aConditionAnimation () {
        return !this.animations.isEmpty();
    }

    @Override
    public boolean estFini () {
        return this.pointeur == this.animations.size();
    }

    public void activeEvenement(EcoleDeMagie jeu) {
        this.pointeur++;
        Combat c = new Combat (this.persos, this.adversaire, this);
        EcoleDeMagie_vueConsole_combat vueConsoleCombat = new EcoleDeMagie_vueConsole_combat(c);
        c.combatAnime();
    }

    @Override
    public void activeEvenement(Jeu jeu) {
        this.pointeur++;
        Combat c = new Combat (this.persos, this.adversaire, this);
        EcoleDeMagie_vueConsole_combat vueConsoleCombat = new EcoleDeMagie_vueConsole_combat(c);
        c.combatAnime();
    }
    
}
