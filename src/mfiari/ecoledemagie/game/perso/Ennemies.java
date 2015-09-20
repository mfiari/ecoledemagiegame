/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.perso;

import mfiari.ecoledemagie.game.sort.Types;


/**
 *
 * @author mike
 */
public class Ennemies {
    public static Ennemie gnome = new Ennemie(1, "Gnome", 1, 10, 2, 7, 3, 3, 4, 6, 12, 4, 2, 1, 20, 0, Types.normal, Types.aucun);
    public static Ennemie gnome_niv_max = new Ennemie(1, "Gnome", 3, 20, 4, 14, 6, 6, 8, 12, 24, 8, 4, 2, 40, 0, Types.normal, Types.aucun);
    public static Ennemie fantome = new Ennemie(2, "Fantome", 1, 8, 14, 1, 20, 8, 2, 14, 18, 12, 2, 1, 20, 0, Types.normal, Types.aucun);
    public static Ennemie fantome_niv_max = new Ennemie(2, "Fantome", 3, 16, 18, 2, 40, 16, 4, 18, 36, 24, 4, 2, 40, 0, Types.normal, Types.aucun);
    public static Ennemie bebeAraignee = new Ennemie(3, "Araignee", 1, 14, 10, 13, 8, 11, 7, 18, 16, 4, 5, 2, 40, 0, Types.normal, Types.aucun);
    public static Ennemie bebeAraignee_niv_max = new Ennemie(3, "Araignee", 3, 28, 20, 26, 16, 22, 14, 36, 32, 8, 10, 4, 80, 0, Types.normal, Types.aucun);
}
