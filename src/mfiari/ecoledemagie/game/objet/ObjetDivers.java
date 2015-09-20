/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.objet;

import mfiari.lib.game.interfaces.Vendable;


/**
 *
 * @author mike
 */
public class ObjetDivers extends Objet implements Vendable{
    
    private String effet1;
    private String effet2;
    private int quantite;

    public ObjetDivers() {
        super();
    }
    
    public ObjetDivers(String nom, Type_objet type, CategorieObjet categorie, String description, String effet1, String effet2) {
        super(nom, type, categorie, description);
        this.effet1 = effet1;
        this.effet2 = effet2;
    }

    public String getEffet1() {
        return this.effet1;
    }

    public String getEffet2() {
        return this.effet2;
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
    
}
