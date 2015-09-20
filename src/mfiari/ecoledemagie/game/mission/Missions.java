/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.mission;

import mfiari.ecoledemagie.game.evenement.Evenements;

/**
 *
 * @author mike
 */
public class Missions {
    
    public static Mission premiere_mission_noir = new Mission(Evenements.premiere_mission_noir, 0, 0, 5, 5, 3, "");
    public static Mission premiere_mission_blanc = new Mission(Evenements.premiere_mission_blanc, 0, 0, 5, 5, 3, "");
    public static Mission premiere_mission_rouge = new Mission(Evenements.premiere_mission_rouge, 0, 0, 5, 5, 3, "");
    public static Mission premiere_mission_jaune = new Mission(Evenements.premiere_mission_jaune, 0, 0, 5, 5, 3, "");
    public static Mission premiere_mission_bleu = new Mission(Evenements.premiere_mission_bleu, 0, 0, 5, 5, 3, "");
    public static Mission premiere_mission_vert = new Mission(Evenements.premiere_mission_vert, 0, 0, 5, 5, 3, "");
    public static Mission premiere_mission_marron = new Mission(Evenements.premiere_mission_marron, 0, 0, 5, 5, 3, "");
    public static Mission premiere_mission_orange = new Mission(Evenements.premiere_mission_orange, 0, 0, 5, 5, 3, "");
    public static Mission ramasse_feuille = new Mission(Evenements.ramasse_feuille, 100, 50, 1, 5, 1, "");
    public static Mission entretient_jardin = new Mission(Evenements.entretient_jardin, 200, 125, 1, 5, 1, "");
    public static Mission chasser_les_araignee = new Mission(Evenements.chasser_araignee, 800, 700, 3, 5, 3, "");
    
}
