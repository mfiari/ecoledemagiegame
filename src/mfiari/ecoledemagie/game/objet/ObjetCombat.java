/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.objet;

import mfiari.ecoledemagie.game.interfaces.Observable;
import mfiari.lib.game.interfaces.Vendable;

/**
 *
 * @author mike
 */
public class ObjetCombat extends Objet implements Observable, Vendable{
    
    private int pv;
    private int pm;
    private int force;
    private int def;
    private int magie;
    private int res;
    private int vit;
    private int prec;
    private int agi;
    private int prixAchat;
    private int prixVente;
    private int quantite;
    

    public ObjetCombat() {
        super();
    }
    
    public ObjetCombat(String nom, Type_objet type, CategorieObjet categorie, int pv, int pm, int force, int def, int magie, int res, int vit, 
            int prec, int agi, int prixAchat, int prixVente, String description, int quantite) {
        super(nom, type, categorie, description);
        this.pv = pv;
        this.pm = pm;
        this.force = force;
        this.def = def;
        this.magie = magie;
        this.res = res;
        this.vit = vit;
        this.prec = prec;
        this.agi = agi;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.quantite = quantite;
    }

    public int getPv() {
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

    public int getPrixAchat() {
        return this.prixAchat;
    }

    public int getPrixVente() {
        return this.prixVente;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void acheter(int quantite) {
        this.quantite = this.quantite + quantite;
    }

    public void vendre(int quantite) {
        this.quantite = this.quantite - quantite;
    }

    public void jeter(int quantite) {
        for (int i = 0; i < quantite; i++) {
            this.quantite = this.quantite - 1;
        }
    }

    public void equiper() {
        this.quantite = (this.quantite - 1);
    }

    public void utilObjet() {
        this.vendre(1);
    }

    @Override
    public String toString () {
        return this.getNom() + "qte: " + this.quantite;
    }

    public String observer() {
        return this.getDescription();
    }
    
}
