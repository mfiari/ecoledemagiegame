/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.objet;

/**
 *
 * @author mike
 */
public class Objet extends mfiari.lib.game.objet.Objet {
    
    private String nom;
    private Type_objet type;
    private CategorieObjet categorie;
    private String description;

    public Objet() {
        this.nom = "";
        this.type = Type_objet.accessoire;
        this.description = "";
        this.categorie = CategorieObjet.accessoire;
    }
    
    public Objet(String nom, Type_objet type, CategorieObjet categorie, String description) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.categorie = categorie;
    }

    public String getNom() {
        return this.nom;
    }

    public Type_objet getType() {
        return this.type;
    }
    
    public CategorieObjet getCategorie() {
        return this.categorie;
    }
    
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString () {
        return this.nom;
    }

}
