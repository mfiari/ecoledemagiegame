/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.perso;

import mfiari.ecoledemagie.game.objet.ObjetCombat;
import mfiari.ecoledemagie.game.objet.ObjetDivers;
import mfiari.ecoledemagie.game.sort.Types;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class Ennemie extends Perso {

    private int numero;
    private Types type1;
    private Types type2;
    private ObjetDivers objet1;
    private ObjetDivers objet2;
    private ObjetCombat objet3;
    private ObjetDivers objet4;
    private ObjetCombat objet5;
    private ObjetDivers objeti1;
    private ObjetDivers objeti2;
    private ObjetCombat objeti3;
    private ObjetDivers objeti4;
    private ObjetCombat objeti5;
    
    public Ennemie(int numero, String nom, int niv, double pv, double pm, double force, double def, double magie, double res,
            double vit, double prec, double esq, double strat, double vas, int exp, int argent, Types type1, Types type2) {
        super(nom, niv, pv, pm, force, def, magie, res, vit, prec, esq, strat, vas, exp, argent, 0, 0,null, Orientation.face);
        this.numero = numero;
        this.type1 = type1;
        this.type2 = type2;
        this.objet1 = null;
        this.objet2 = null;
        this.objet3 = null;
        this.objet4 = null;
        this.objet5 = null;
        this.objeti1 = objet1;
        this.objeti2 = objet2;
        this.objeti3 = objet3;
        this.objeti4 = objet4;
        this.objeti5 = objet5;
    }
    
    public Ennemie(Ennemie adv) {
        super(adv);
        this.numero = adv.numero;
        this.type1 = adv.type1;
        this.type2 = adv.type2;
        this.objet1 = adv.objet1;
        this.objet2 = adv.objet2;
        this.objet3 = adv.objet3;
        this.objet4 = adv.objet4;
        this.objet5 = adv.objet5;
        this.objeti1 = adv.objet1;
        this.objeti2 = adv.objet2;
        this.objeti3 = adv.objet3;
        this.objeti4 = adv.objet4;
        this.objeti5 = adv.objet5;
    }

    public int getNum() {
        return this.numero;
    }

    public Types getType1() {
        return type1;
    }

    public void setType1(Types type1) {
        this.type1 = type1;
    }

    public Types getType2() {
        return type2;
    }

    public void setType2(Types type2) {
        this.type2 = type2;
    }

    public ObjetDivers getObjet1() {
        return this.objet1;
    }

    public void setObjet1() {
        this.objet1 = null;
    }

    public ObjetDivers getObjet2() {
        return this.objet2;
    }

    public void setObjet2() {
        this.objet2 = null;
    }

    public ObjetCombat getObjet3() {
        return this.objet3;
    }

    public void setObjet3() {
        this.objet3 = null;
    }

    public ObjetDivers getObjet4() {
        return this.objet4;
    }

    public void setObjet4() {
        this.objet4 = null;
    }

    public ObjetCombat getObjet5() {
        return this.objet5;
    }

    public void setObjet5() {
        this.objet5 = null;
    }

    @Override
    public void soin() {
        super.soin();
        this.objet1 = this.objeti1;
        this.objet2 = this.objeti2;
        this.objet3 = this.objeti3;
        this.objet4 = this.objeti4;
        this.objet5 = this.objeti5;
    }

    public boolean vole() {
        boolean vole = false;
        if (this.objet1 != null || this.objet2 != null || this.objet3 != null || this.objet4 != null || this.objet5 != null) {
            vole = true;
        }
        return vole;
    }

    public ObjetDivers volObjetNul(int i) {
        ObjetDivers objet = null;
        if (i == 1) {
            objet = this.getObjet1();
        }
        if (i == 2) {
            objet = this.getObjet2();
        }
        if (i == 4) {
            objet = this.getObjet4();
        }
        return objet;
    }

    public ObjetCombat volEquipNul(int i) {
        ObjetCombat objet = null;
        if (i == 3) {
            objet = this.getObjet3();
        }
        if (i == 5) {
            objet = this.getObjet5();
        }
        return objet;
    }

    public boolean elemental() {
        boolean elemental = false;
        if (this.type1.equals(Types.feu) || this.type1.equals(Types.eau) || this.type1.equals(Types.electrique) || this.type1.equals(Types.vent)
                || this.type1.equals(Types.lumiere) || this.type1.equals(Types.tenebre) || this.type1.equals(Types.espace) 
                || this.type1.equals(Types.terre) || this.type1.equals(Types.glace)) {
            elemental = true;
        } else {
            if (this.type2.equals(Types.feu) || this.type2.equals(Types.eau) || this.type2.equals(Types.electrique) || this.type2.equals(Types.vent)
                    || this.type2.equals(Types.lumiere) || this.type2.equals(Types.tenebre) || this.type2.equals(Types.espace) 
                    || this.type2.equals(Types.terre) || this.type2.equals(Types.glace)) {
                elemental = true;
            }
        }
        return elemental;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Ennemie other = (Ennemie) obj;
            if (this.numero != other.numero) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
}
