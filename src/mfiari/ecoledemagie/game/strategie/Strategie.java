/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.strategie;

/**
 *
 * @author mike
 */
public class Strategie {
    
    private StrategieAction actionDefaut;
    private StrategieDeplacement deplacementDefaut;
    private StrategieSort sortDefaut;
    private StrategieObjet objetDefaut;
    private StrategieAction action1;
    private StrategieDeplacement deplacement1;
    private StrategieSort sort1;
    private StrategieObjet objet1;
    private StrategieAction action2;
    private StrategieDeplacement deplacement2;
    private StrategieSort sort2;
    private StrategieObjet objet2;
    
    public Strategie (StrategieAction actionDefaut, StrategieDeplacement deplacementDefaut, StrategieSort sortDefaut, StrategieObjet objetDefaut) {
        this.actionDefaut = actionDefaut;
        this.deplacementDefaut = deplacementDefaut;
        this.sortDefaut = sortDefaut;
        this.objetDefaut = objetDefaut;
        this.action1 = StrategieAction.libre;
        this.action2 = StrategieAction.libre;
        this.deplacement1 = StrategieDeplacement.libre;
        this.deplacement2 = StrategieDeplacement.libre;
        this.sort1 = StrategieSort.libre;
        this.sort2 = StrategieSort.libre;
        this.objet1 = StrategieObjet.libre;
        this.objet2 = StrategieObjet.libre;
    }

    public StrategieAction getActionDefaut() {
        return this.actionDefaut;
    }

    public StrategieDeplacement getDeplacementDefaut() {
        return this.deplacementDefaut;
    }

    public StrategieSort getSortDefaut() {
        return this.sortDefaut;
    }

    public StrategieObjet getObjetDefaut() {
        return this.objetDefaut;
    }

    public StrategieAction getAction1() {
        if (this.action1 != StrategieAction.libre) {
            return this.action1;
        }
        return this.actionDefaut;
    }

    public void setAction1(StrategieAction action1) {
        this.action1 = action1;
    }

    public StrategieDeplacement getDeplacement1() {
        if (this.deplacement1 != StrategieDeplacement.libre) {
            return this.deplacement1;
        }
        return this.deplacementDefaut;
    }

    public void setDeplacement1(StrategieDeplacement deplacement1) {
        this.deplacement1 = deplacement1;
    }

    public StrategieSort getSort1() {
        if (this.sort1 != StrategieSort.libre) {
            return this.sort1;
        }
        return this.sortDefaut;
    }

    public void setSort1(StrategieSort sort1) {
        this.sort1 = sort1;
    }

    public StrategieObjet getObjet1() {
        if (this.objet1 != StrategieObjet.libre) {
            return this.objet1;
        }
        return this.objetDefaut;
    }

    public void setObjet1(StrategieObjet objet1) {
        this.objet1 = objet1;
    }

    public StrategieAction getAction2() {
        if (this.action2 != StrategieAction.libre) {
            return this.action2;
        }
        return this.actionDefaut;
    }

    public void setAction2(StrategieAction action2) {
        this.action2 = action2;
    }

    public StrategieDeplacement getDeplacement2() {
        if (this.deplacement2 != StrategieDeplacement.libre) {
            return this.deplacement2;
        }
        return this.deplacementDefaut;
    }

    public void setDeplacement2(StrategieDeplacement deplacement2) {
        this.deplacement2 = deplacement2;
    }

    public StrategieSort getSort2() {
        if (this.sort2 != StrategieSort.libre) {
            return this.sort2;
        }
        return this.sortDefaut;
    }

    public void setSort2(StrategieSort sort2) {
        this.sort2 = sort2;
    }

    public StrategieObjet getObjet2() {
        if (this.objet2 != StrategieObjet.libre) {
            return this.objet2;
        }
        return this.objetDefaut;
    }

    public void setObjet2(StrategieObjet objet2) {
        this.objet2 = objet2;
    }
    
}
