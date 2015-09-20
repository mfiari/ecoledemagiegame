/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.menu;

import mfiari.ecoledemagie.game.perso.Perso;
import java.io.Serializable;

/**
 *
 * @author mike
 */
public class Stat extends mfiari.lib.game.personnage.Stat implements Serializable {
    
    
    public Stat (Perso persoNiv1, Perso persoNiv100) {
        this.stat=new double[100][13];
        this.stat[0][0]=persoNiv1.getNiveau();
        this.stat[0][1]=persoNiv1.getPv();
        this.stat[0][2]=persoNiv1.getPm();
        this.stat[0][3]=persoNiv1.getForce();
        this.stat[0][4]=persoNiv1.getDefense();
        this.stat[0][5]=persoNiv1.getMagie();
        this.stat[0][6]=persoNiv1.getResistance();
        this.stat[0][7]=persoNiv1.getVitesse();
        this.stat[0][8]=persoNiv1.getPrecision();
        this.stat[0][9]=persoNiv1.getEsquive();
        this.stat[0][10]=persoNiv1.getStrategie();
        this.stat[0][11]=persoNiv1.getVAS();
        this.stat[0][12]=persoNiv1.getExperience();
        int niv = 2;
        int niveauEcart = persoNiv100.getNiveau() - persoNiv1.getNiveau();
        double pv = (persoNiv100.getPv() - persoNiv1.getPv()) / niveauEcart;
        double pm = (persoNiv100.getPm() - persoNiv1.getPm()) / niveauEcart;
        double force = (persoNiv100.getForce() - persoNiv1.getForce()) / niveauEcart;
        double def = (persoNiv100.getDefense() - persoNiv1.getDefense()) / niveauEcart;
        double magie = (persoNiv100.getMagie() - persoNiv1.getMagie()) / niveauEcart;
        double res = (persoNiv100.getResistance() - persoNiv1.getResistance()) / niveauEcart;
        double vit = (persoNiv100.getVitesse() - persoNiv1.getVitesse()) / niveauEcart;
        double prec = (persoNiv100.getPrecision() - persoNiv1.getPrecision()) / niveauEcart;
        double esq = (persoNiv100.getEsquive() - persoNiv1.getEsquive()) / niveauEcart;
        double strat = (persoNiv100.getStrategie() - persoNiv1.getStrategie()) / niveauEcart;
        double vas = (persoNiv100.getVAS() - persoNiv1.getVAS()) / niveauEcart;
        int exp = (persoNiv100.getExperience() - persoNiv1.getExperience()) / niveauEcart;
        for (int i=niv-1 ; i<100 ; i++) {
            this.stat[i][0]= i+1;
            this.stat[i][1]= this.stat[i-1][1] + pv;
            this.stat[i][2]= this.stat[i-1][2] + pm;
            this.stat[i][3]= this.stat[i-1][3] + force;
            this.stat[i][4]= this.stat[i-1][4] + def;
            this.stat[i][5]= this.stat[i-1][5] + magie;
            this.stat[i][6]= this.stat[i-1][6] + res;
            this.stat[i][7]= this.stat[i-1][7] + vit;
            this.stat[i][8]= this.stat[i-1][8] + prec;
            this.stat[i][9]= this.stat[i-1][9] + esq;
            this.stat[i][10]= this.stat[i-1][10] + strat;
            this.stat[i][11]= this.stat[i-1][11] + vas;
            this.stat[i][12]= calculeExp(persoNiv1.getExperience(), persoNiv100.getExperience(), i+1);
        }
    }
    
    public double getNiv(int i) {
        return this.stat[i][0];
    }
    public double getPv(int i) {
        return this.stat[i][1];
    }
    public double getPm(int i) {
        return this.stat[i][2];
    }
    public double getForce(int i) {
        return this.stat[i][3];
    }
    public double getDef(int i) {
        return this.stat[i][4];
    }
    public double getMagie(int i) {
        return this.stat[i][5];
    }
    public double getRes(int i) {
        return this.stat[i][6];
    }
    public double getVit(int i) {
        return this.stat[i][7];
    }
    public double getPrec(int i) {
        return this.stat[i][8];
    }
    public double getEsq(int i) {
        return this.stat[i][9];
    }
    public double getStrat(int i) {
        return this.stat[i][10];
    }
    public double getVas(int i) {
        return this.stat[i][11];
    }
    public double getExp(int i) {
        return this.stat[i][12];
    }
}
