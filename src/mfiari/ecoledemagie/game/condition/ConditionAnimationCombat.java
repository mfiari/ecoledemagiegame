/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.condition;

import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.sort.Sort;
import mfiari.lib.game.condition.Condition;

/**
 *
 * @author mike
 */
public class ConditionAnimationCombat extends Condition{
    
    private Perso attaquant;
    private Action actionAttaquant;
    private Sort sortAttaquant;
    private Perso attaquer;
    private Action actionAttaquer;
    private Sort sortAttaquer;
    
    
    public ConditionAnimationCombat () {
    }
    
    public void ajouterActionAttaquant (Perso attaquant, Action action) {
        this.attaquant = attaquant;
        this.actionAttaquant = action;
    }
    
    public void ajouterActionAttaquant (Perso attaquant, Action action, Sort sort) {
        this.attaquant = attaquant;
        this.actionAttaquant = action;
        this.sortAttaquant = sort;
    }
    
    public void ajouterActionAttaquer (Perso attaquer, Action action) {
        this.attaquer = attaquer;
        this.actionAttaquer = action;
    }
    
    public void ajouterActionAttaquer (Perso attaquer, Action action, Sort sort) {
        this.attaquer = attaquer;
        this.actionAttaquer = action;
        this.sortAttaquer = sort;
    }

    public Action getActionAttaquant() {
        return this.actionAttaquant;
    }

    public Action getActionAttaquer() {
        return this.actionAttaquer;
    }

    public Perso getAttaquant() {
        return this.attaquant;
    }

    public Perso getAttaquer() {
        return this.attaquer;
    }

    public Sort getSortAttaquant() {
        return this.sortAttaquant;
    }
    
    public Sort getSortAttaquer() {
        return this.sortAttaquer;
    }
    
}
