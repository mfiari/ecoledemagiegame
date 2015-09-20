/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.perso;

import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.menu.Stat;
import mfiari.ecoledemagie.game.objet.ObjetCombat;
import mfiari.ecoledemagie.game.sort.Etat;
import mfiari.ecoledemagie.game.sort.Sort;
import mfiari.ecoledemagie.game.sort.Types;
import mfiari.ecoledemagie.game.strategie.Strategie;
import mfiari.ecoledemagie.game.ville.Endroit;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class Perso extends mfiari.lib.game.personnage.Personnage {

    private int niv;
    private double pv;
    private double pm;
    private double force;
    private double defense;
    private double magie;
    private double resistance;
    private double vitesse;
    private double precision;
    private double esquive;
    private double strategie;
    private double vas;
    private int experience;
    private Etat etat1;
    private Etat etat2;
    private Types[] fort;
    private Types[] faible;
    private int argent;
    private ListeDeSort tec;
    private Stat stat;
    private Strategie strategiePerso;
    private int comboMax;
    private int comboEnCour;
    private double vitesseLancementSort;
    
    {
        this.comboEnCour = 0;
        this.comboMax = 3;
        this.vitesseLancementSort = 3;
    }

    public Perso(String nom, int niv, double pv, double pm, double force, double def, double magie, double res, double vit, double prec, double agi, 
            double strat, double vas, int exp, int argent, int positionX, int positionY, Endroit ville, Orientation orientation) {

        super(nom, positionX, positionY, ville, orientation);
        this.niv = niv;
        this.pv = pv;
        this.pm = pm;
        this.force = force;
        this.defense = def;
        this.magie = magie;
        this.resistance = res;
        this.vitesse = vit;
        this.precision = prec;
        this.esquive = agi;
        this.strategie = strat;
        this.vas = vas;
        this.experience = exp;
        this.etat1 = Etat.aucun;
        this.etat2 = Etat.aucun;
        this.tec = new ListeDeSort();
        this.fort = new Types[Types.values().length];
        for (int i=0 ; i< this.fort.length ; i++) {
            this.fort[i] = Types.aucun;
        }
        this.faible = new Types[Types.values().length];
        for (int i=0 ; i< this.faible.length ; i++) {
            this.faible[i] = Types.aucun;
        }
        this.argent = argent;
    }

    public Perso(Perso adv) {
        super(adv.getNom(), adv.getPosition());
        this.niv = adv.niv;
        this.pv = adv.pv;
        this.pm = adv.pm;
        this.force = adv.force;
        this.defense = adv.defense;
        this.magie = adv.magie;
        this.resistance = adv.resistance;
        this.vitesse = adv.vitesse;
        this.precision = adv.precision;
        this.esquive = adv.esquive;
        this.strategie = adv.strategie;
        this.vas = adv.vas;
        this.experience = adv.experience;
        this.etat1 = adv.etat1;
        this.etat2 = adv.etat2;
        this.argent = adv.argent;
        this.fort = adv.fort;
        this.faible = adv.faible;
        this.tec = adv.tec;
        this.strategiePerso = adv.strategiePerso;
        this.stat = adv.stat;
    }

    //retourne les pv de l'individu
    public double getPv() {
        return this.pv;
    }

    //retourne les pv initiale de l'individu
    public double getPvInitial() {
        return this.stat.getPv(this.niv -1);
    }

    //remplace les pv de l'individu par ceux passé en paramètre
    public void setPv(double pv) {
        this.pv = pv;
    }

    //retourne les pm de l'individu
    public double getPm() {
        return this.pm;
    }

    //retourne les pm initiale de l'individu
    public double getPmInitial() {
        return this.stat.getPm(this.niv -1);
    }

    //remplace les pm de l'individu par ceux passé en paramètre
    public void setPm(double pm) {
        this.pm = pm;
    }

    //retourne la force physique de l'individu
    public double getForce() {
        return this.force;
    }

    //remplace la force physique de l'individu par celle passé en paramètre
    public void setForce(int force) {
        this.force = force;
    }
    
    //retourne la force physique de l'individu
    public double getForceInitial() {
        return this.stat.getForce(this.niv -1);
    }

    //retourne la defense physique de l'individu
    public double getDefense() {
        return this.defense;
    }

    //retourne la defense physique initiale de l'individu
    public double getDefenseInitial() {
        return this.stat.getDef(this.niv -1);
    }

    //remplace la défense physique de l'individu par celle passé en paramètre
    public void setDefense(double def) {
        this.defense = def;
    }

    //retourne la force magique de l'individu
    public double getMagie() {
        return this.magie;
    }

    //remplace la force magique de l'individu par celle passé en paramètre
    public void setMagie(int magie) {
        this.magie = magie;
    }
    
    //retourne la force magique de l'individu
    public double getMagieInitial() {
        return this.stat.getMagie(this.niv -1);
    }

    //retourne la defense magique de l'individu
    public double getResistance() {
        return this.resistance;
    }

    //remplace la défense magique de l'individu par celle passé en paramètre
    public void setResistance(int res) {
        this.resistance = res;
    }
    
    //retourne la defense magique de l'individu
    public double getResistanceInitial() {
        return this.stat.getRes(this.niv -1);
    }

    //retourne la vitesse de l'individu
    public double getVitesse() {
        return this.vitesse;
    }

    //remplace la vitesse de l'individu par celle passé en paramètre
    public void setVitesse(int vit) {
        this.vitesse = vit;
    }
    
    //retourne la vitesse de l'individu
    public double getVitesseInitial() {
        return this.stat.getVit(this.niv -1);
    }

    //retourne la precision de l'individu
    public double getPrecision() {
        return this.precision;
    }

    //remplace la précision de l'individu par celle passé en paramètre
    public void setPrecision(int prec) {
        this.precision = prec;
    }
    
    //retourne la precision de l'individu
    public double getPrecisionInitial() {
        return this.stat.getPrec(this.niv -1);
    }

    //retourne l'agilité de l'individu
    public double getEsquive() {
        return this.esquive;
    }

    //remplace l'agilité de l'individu par celle passé en paramètre
    public void setEsquive(int agi) {
        this.esquive = agi;
    }
    
    //retourne l'agilité de l'individu
    public double getEsquiveInitial() {
        return this.stat.getEsq(this.niv -1);
    }
    
    //retourne l'agilité de l'individu
    public double getStrategie() {
        return this.strategie;
    }

    //remplace l'agilité de l'individu par celle passé en paramètre
    public void setStrategie(int strat) {
        this.strategie = strat;
    }
    
    //retourne l'agilité de l'individu
    public double getStrategieInitial() {
        return this.stat.getStrat(this.niv -1);
    }
    
    //retourne l'agilité de l'individu
    public double getVAS() {
        return this.vas;
    }

    //remplace l'agilité de l'individu par celle passé en paramètre
    public void setVAS(int vas) {
        this.vas = vas;
    }
    
    //retourne l'agilité de l'individu
    public double getVASInitial() {
        return this.stat.getVas(this.niv -1);
    }

    //retourne l'expérience de l'individu
    public int getExperience() {
        return this.experience;
    }

    //ajoute à l'expérience de l'individu celle passé en paramètre
    public void setExperience(int exp) {
        this.experience = this.experience + exp;
    }
    
    public Stat getStat() {
        return this.stat;
    }
    
    public void setStat (Stat stat) {
        this.stat = stat;
    }

    //retourne l'argent de l'individu
    public int getArgent() {
        return this.argent;
    }

    //ajoute à l'argent de l'individu celui passé en paramètre
    public void setArgent(int arg) {
        this.argent = this.argent + arg;
    }

    //retourne l'état de l'individu
    public Etat getEtat() {
        return this.etat1;
    }

    //remplace l'état de l'individu par celui passé en paramètre
    public void setEtat(Etat etat) {
        this.etat1 = etat;
    }
    
    //retourne l'agilité de l'individu
    public Strategie getStrategiePerso() {
        return this.strategiePerso;
    }

    //remplace l'agilité de l'individu par celle passé en paramètre
    public void setStrategiePerso(Strategie strat) {
        this.strategiePerso = strat;
    }
    
    public void seMetEnGarde () {
        this.defense = this.defense + (this.defense/2);
    }
    
    public void finGarde () {
        this.defense = (2*this.defense)/3;
    }
    
    public void recoitDegat (double degat) {
        this.pv = this.pv - degat;
        if (this.pv < 0) {
            this.pv = 0;
        }
    }
    
    public boolean estKO () {
        return this.pv == 0;
    }
    
    public void ajouterForce (Types type) {
        int i = 0;
        int max = Types.values().length;
        while (i < max && this.fort[i] != null) {
            i++;
        }
        if (i < max) {
            this.fort[i] = type;
        }
    }
    
    public void ajouterFaible (Types type) {
        int i = 0;
        int max = Types.values().length;
        while (i < max && this.faible[i] != null) {
            i++;
        }
        if (i < max) {
            this.faible[i] = type;
        }
    }

    //retourne vrai si l'individu est faible au type passé en paramètre
    public boolean estFaible(Types type) {
        boolean estFaible = false;
        int j = 0;
        while (j < this.faible.length && !this.faible[j].equals(type)) {
            j++;
        }
        if (j < this.faible.length) {
            estFaible = true;
        }
        return estFaible;
    }

    //retourne vrai si l'individu est fort au type passé en paramètre
    public boolean estFort(Types type) {
        boolean estFort = false;
        int j = 0;
        while (j < this.fort.length && !this.fort[j].equals(type)) {
            j++;
        }
        if (j < this.fort.length) {
            estFort = true;
        }
        return estFort;
    }

    public ListeDeSort getTec() {
        return this.tec;
    }
    
    public void setListeDeTec(ListeDeSort tec) {
        this.tec = tec;
    }
    
    public int getComboEnCour () {
        return this.comboEnCour;
    }
    
    public void combos () {
        if (this.comboEnCour >= this.comboMax) {
            this.comboEnCour = 0;
        } else {
            this.comboEnCour++;
        }
    }
    
    public boolean finCombos () {
        return this.comboEnCour >= this.comboMax;
    }
    
    public void finirCombos () {
        this.comboEnCour = 0;
    }
    
    public int getMaxCombos () {
        return this.comboMax;
    }

    public double getVitesseLancementSort() {
        return vitesseLancementSort;
    }

    //permet de soigné toute les stats de l'individu
    public void soin() {
        this.pv = this.stat.getPv(this.niv -1);
        this.pm = this.stat.getPm(this.niv -1);
        this.force = this.stat.getForce(this.niv -1);
        this.defense = this.stat.getDef(this.niv -1);
        this.magie = this.stat.getMagie(this.niv -1);
        this.resistance = this.stat.getRes(this.niv -1);
        this.vitesse = this.stat.getVit(this.niv -1);
        this.precision = this.stat.getPrec(this.niv -1);
        this.esquive = this.stat.getEsq(this.niv -1);
    }

    //permet de reçevoir un sort de soin
    public void recoitSoin(Sort sort) {
        if (this.pv + sort.getPv() > this.stat.getPv(this.niv -1)) {
            this.pv = this.stat.getPv(this.niv -1);
        } else {
            this.pv = this.pv + sort.getPv();
        }
        if (this.pm + sort.getPm() > this.stat.getPm(this.niv -1)) {
            this.pm = this.stat.getPm(this.niv -1);
        } else {
            this.pm = this.pm + sort.getPm();
        }
        if (this.force + sort.getForce() > this.stat.getForce(this.niv -1)) {
            this.force = this.stat.getForce(this.niv -1);
        } else {
            this.force = this.force + sort.getForce();
        }
        if (this.defense + sort.getDef() > this.stat.getDef(this.niv -1)) {
            this.defense = this.stat.getDef(this.niv -1);
        } else {
            this.defense = this.defense + sort.getDef();
        }
        if (this.magie + sort.getMagie() > this.stat.getMagie(this.niv -1)) {
            this.magie = this.stat.getMagie(this.niv -1);
        } else {
            this.magie = this.magie + sort.getMagie();
        }
        if (this.resistance + sort.getRes() > this.stat.getRes(this.niv -1)) {
            this.resistance = this.stat.getRes(this.niv -1);
        } else {
            this.resistance = this.resistance + sort.getRes();
        }
        if (this.vitesse + sort.getVit() > this.stat.getVit(this.niv -1)) {
            this.vitesse = this.stat.getVit(this.niv -1);
        } else {
            this.vitesse = this.vitesse + sort.getVit();
        }
        if (this.precision + sort.getPrec() > this.stat.getPrec(this.niv -1)) {
            this.precision = this.stat.getPrec(this.niv -1);
        } else {
            this.precision = this.precision + sort.getPrec();
        }
        if (this.esquive + sort.getAgi() > this.stat.getEsq(this.niv -1)) {
            this.esquive = this.stat.getEsq(this.niv -1);
        } else {
            this.esquive = this.esquive + sort.getAgi();
        }
    }

    //permet de reçevoir un sort de soin d'un personnage
    public void recoitSoin(Sort sort, Personnage perso) {
        if (sort.getPv() != 0) {
            if (this.pv + (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getPv(this.niv -1)) {
                this.pv = this.stat.getPv(this.niv -1);
            } else {
                this.pv = this.pv + (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getPm() != 0) {
            if (this.pm + (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getPm(this.niv -1)) {
                this.pm = this.stat.getPm(this.niv -1);
            } else {
                this.pm = this.pm + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getForce() != 0) {
            if (this.force + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getForce(this.niv -1)) {
                this.force = this.stat.getForce(this.niv -1);
            } else {
                this.force = this.force + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getDef() != 0) {
            if (this.defense + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getDef(this.niv -1)) {
                this.defense = this.stat.getDef(this.niv -1);
            } else {
                this.defense = this.defense + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getMagie() != 0) {
            if (this.magie + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getMagie(this.niv -1)) {
                this.magie = this.stat.getMagie(this.niv -1);
            } else {
                this.magie = this.magie + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getRes() != 0) {
            if (this.resistance + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getRes(this.niv -1)) {
                this.resistance = this.stat.getRes(this.niv -1);
            } else {
                this.resistance = this.resistance + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getVit() != 0) {
            if (this.vitesse + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getVit(this.niv -1)) {
                this.vitesse = this.stat.getVit(this.niv -1);
            } else {
                this.vitesse = this.vitesse + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getPrec() != 0) {
            if (this.precision + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getPrec(this.niv -1)) {
                this.precision = this.stat.getPrec(this.niv -1);
            } else {
                this.precision = this.precision + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
        if (sort.getAgi() != 0) {
            if (this.esquive + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt())) > this.stat.getEsq(this.niv -1)) {
                this.esquive = this.stat.getEsq(this.niv -1);
            } else {
                this.esquive = this.esquive + (int) (perso.getMagie() + (perso.getMagie() * sort.getAtt()));
            }
        }
    }

    //retourne vrai si les stats de l'individu sont égaux aux stats initiaux et si il n'a pas d'altération d'état
    public boolean enForme() {
        return (this.enFormeStat() && this.enFormeEtat());
    }

    //retourne vrai si les stats de l'individu sont égaux aux stats initiaux
    public boolean enFormeStat() {
        return (this.pv == this.stat.getPv(this.niv -1) && this.pm == this.stat.getPm(this.niv -1) && this.force == this.stat.getForce(this.niv -1) && this.defense == this.stat.getDef(this.niv -1)
                && this.magie == this.stat.getMagie(this.niv -1) && this.resistance == this.stat.getRes(this.niv -1) && this.vitesse == this.stat.getVit(this.niv -1) && 
                this.precision == this.stat.getPrec(this.niv -1) && this.esquive == this.stat.getEsq(this.niv -1));
    }

    //retourne vrai si l'individu n'a pas d'altération d'état
    public boolean enFormeEtat() {
        return this.etat1.equals(Etat.aucun);
    }

    //permet d'utiliser un objet sur l'individu
    public void utilObjet(ObjetCombat o) {
        if (this.pv + o.getPv() > this.stat.getPv(this.niv -1)) {
            this.pv = this.stat.getPv(this.niv -1);
        } else {
            if (this.pv + o.getPv() < 0) {
                this.pv = 0;
            } else {
                this.pv = this.pv + o.getPv();
            }
        }
        if (this.pm + o.getPm() > this.stat.getPm(this.niv -1)) {
            this.pm = this.stat.getPm(this.niv -1);
        } else {
            this.pm = this.pm + o.getPm();
        }
        if (this.force + o.getForce() > this.stat.getForce(this.niv -1)) {
            this.force = this.stat.getForce(this.niv -1);
        } else {
            this.force = this.force + o.getForce();
        }
        if (this.defense + o.getDef() > this.stat.getDef(this.niv -1)) {
            this.defense = this.stat.getDef(this.niv -1);
        } else {
            this.defense = this.defense + o.getDef();
        }
        if (this.magie + o.getMagie() > this.stat.getMagie(this.niv -1)) {
            this.magie = this.stat.getMagie(this.niv -1);
        } else {
            this.magie = this.magie + o.getMagie();
        }
        if (this.resistance + o.getRes() > this.stat.getRes(this.niv -1)) {
            this.resistance = this.stat.getRes(this.niv -1);
        } else {
            this.resistance = this.resistance + o.getRes();
        }
        if (this.vitesse + o.getVit() > this.stat.getVit(this.niv -1)) {
            this.vitesse = this.stat.getVit(this.niv -1);
        } else {
            this.vitesse = this.vitesse + o.getVit();
        }
        if (this.precision + o.getPrec() > this.stat.getPrec(this.niv -1)) {
            this.precision = this.stat.getPrec(this.niv -1);
        } else {
            this.precision = this.precision + o.getPrec();
        }
        if (this.esquive + o.getAgi() > this.stat.getEsq(this.niv -1)) {
            this.esquive = this.stat.getEsq(this.niv -1);
        } else {
            this.esquive = this.esquive + o.getAgi();
        }
        o.utilObjet();
    }

    //retourne le niveau de l'individu
    public int getNiveau() {
        return this.niv;
    }
    
    public void setNiveau (int niveau) {
        this.niv = niveau;
        this.pv = this.pv + this.stat.getPv(niveau-1) - this.stat.getPv(niveau-2);
        this.pm = this.pm + this.stat.getPm(niveau-1) - this.stat.getPm(niveau-2);
        this.force = this.force + this.stat.getForce(niveau -1) - this.stat.getForce(niveau -2);
        this.defense = this.defense + this.stat.getDef(niveau -1) - this.stat.getDef(niveau -2);
        this.magie = this.magie + this.stat.getMagie(niveau -1) - this.stat.getMagie(niveau -2);
        this.resistance = this.resistance + this.stat.getRes(niveau -1) - this.stat.getRes(niveau -2);
        this.vitesse = this.vitesse + this.stat.getVit(niveau -1) - this.stat.getVit(niveau -2);
        this.precision = this.precision + this.stat.getPrec(niveau -1) - this.stat.getPrec(niveau -2);
        this.esquive = this.esquive + this.stat.getEsq(niveau -1) - this.stat.getEsq(niveau -2);
        this.strategie = this.strategie + this.stat.getStrat(niveau -1) - this.stat.getStrat(niveau -2);
        this.vas = this.vas + this.stat.getVas(niveau -1) - this.stat.getVas(niveau -2);
    }

    //retourne la faiblesse de l'individu
    public Types getFaiblesse(int i) {
        return this.faible[i];
    }

    //retourne la première force de l'individu
    public Types getFort(int i) {
        return this.fort[i];
    }

}
