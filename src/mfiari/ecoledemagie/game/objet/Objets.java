/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.objet;

import mfiari.lib.game.objet.ObjetEndroitClassique;

/**
 *
 * @author mike
 */
public class Objets {
    
    public static ObjetCombat potion = new ObjetCombat("potion", Type_objet.soin_stat, CategorieObjet.soin, 
            100, 0, 0, 0, 0, 0, 0, 0, 0, 75, 25, "redonne des pv", 0);
    public static ObjetCombat ether = new ObjetCombat("ether", Type_objet.soin_stat, CategorieObjet.soin, 
            0, 50, 0, 0, 0, 0, 0, 0, 0, 120, 40, "redonne des pm", 0);
    public static ObjetCombat antidote = new ObjetCombat("antidote", Type_objet.soin_etat, CategorieObjet.soin,
            100, 0, 0, 0, 0, 0, 0, 0, 0, 50, 15, "soigne poison", 0);
    public static ObjetCombat renais = new ObjetCombat("antidote", Type_objet.soin_stat, CategorieObjet.soin,
            100, 0, 0, 0, 0, 0, 0, 0, 0, 50, 15, "ressusite", 0);
    public static ObjetDivers listeDennemi = new ObjetDivers("livre ennemi", Type_objet.livre, CategorieObjet.rare, 
            "liste des ennemi rencontrer au cours du jeu", "","");
    
    //public static ObjetEndroitClassique rocher = new ObjetEndroitClassique("rocher", Type_objet.rocher, CategorieObjet.environnement, "rocher");
    public static ObjetEndroitClassique rocher = new ObjetEndroitClassique(Type_objet.rocher, 0, 0);
    
}
