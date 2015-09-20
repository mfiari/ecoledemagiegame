/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.sort;

import java.io.Serializable;

/**
 *
 * @author mike
 */
public class Sort implements Serializable {
    
    private String nom;
    private Types type;
    private Categories categorie;
    private double att;
    private int pmTec;
    private double pv;
    private int pm;
    private int force;
    private int def;
    private int magie;
    private int res;
    private int vit;
    private int prec;
    private int agi;
    private double preparation;
    private Etat etat;
    private Types_sort capacite;
    private int nbUtil;
    private String description;

    public Sort(String nom, Types type, Categories categorie, double att, int pmTec, int niv, double pv, int pm, int force,
            int def, int magie, int res, int vit, int prec, int esq, int strat, int vas, int exp, int expNivSup, 
            Etat etat, Types_sort capacite, int nbUtil, String description) {
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.att = att;
        this.pmTec = pmTec;
        this.pv = pv;
        this.pm = pm;
        this.force = force;
        this.def = def;
        this.magie = magie;
        this.res = res;
        this.vit = vit;
        this.prec = prec;
        this.agi = esq;
        this.etat = etat;
        this.capacite = capacite;
        this.nbUtil = nbUtil;
        this.description = description;
        this.preparation = 1;
    }

    public String getNom() {
        return this.nom;
    }

    public Types getType() {
        return this.type;
    }

    public Categories getCategorie() {
        return this.categorie;
    }

    public double getAtt() {
        return this.att;
    }

    public int getPmTec() {
        return this.pmTec;
    }

    public double getPv() {
        return this.pv;
    }

    public int getPm() {
        return this.pm;
    }

    public int getForce() {
        return this.force;
    }

    public int getDef() {
        return this.def;
    }

    public int getMagie() {
        return this.magie;
    }

    public int getRes() {
        return this.res;
    }

    public int getVit() {
        return this.vit;
    }

    public int getPrec() {
        return this.prec;
    }

    public int getAgi() {
        return this.agi;
    }

    public double getPreparation() {
        return preparation;
    }

    public Types_sort getCapacite() {
        return this.capacite;
    }

    public int getNbUtil() {
        return this.nbUtil;
    }

    public String getDescription() {
        return this.description;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public void setNbUtil() {
        this.nbUtil = nbUtil + 1;
    }

    public double attaque(int att) {
        return (att + (att * this.att));
    }
    
}
