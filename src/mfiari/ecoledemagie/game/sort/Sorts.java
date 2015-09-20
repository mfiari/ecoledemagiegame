/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.sort;

/**
 *
 * @author mike
 */
public class Sorts {
    public static Sort malediction = new Sort("malediction", Types.normal, Categories.attaqueU, 0.20, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "sort de fantome");
    public static Sort toile = new Sort("toile", Types.animal, Categories.attaqueU, 0.10, 2, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "reduit la vitesse");
    public static Sort rayon = new Sort("rayon", Types.normal, Categories.attaqueU, 0.10, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "sort faible");
    public static Sort enchantement = new Sort("enchantement", Types.normal, Categories.attaqueU, 0.10, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "charme faible");
    public static Sort flamme = new Sort("flamme", Types.feu, Categories.attaqueU, 0.1, 2, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "sort de feu novice");
    public static Sort vent = new Sort("vent", Types.vent, Categories.attaqueU, 0.1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "sort de vent novice");
    public static Sort coupe = new Sort("coupe", Types.normal, Categories.attaqueU, 0.1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "sort de cu novice");
    public static Sort eau = new Sort("vent", Types.vent, Categories.attaqueU, 0.1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            Etat.aucun, Types_sort.magie, 0, "sort d'eau novice");

}
