/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.evenement;

import mfiari.lib.game.position.Position;
import mfiari.ecoledemagie.game.ville.Endroits;
import mfiari.lib.game.evenements.EvenementDeplacement;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class Evenements {
    
    public static EvenementQuete jeu = new EvenementQuete("jeu");
    public static EvenementQuete chapitre1 = new EvenementQuete("chapitre1");
    public static EvenementQuete lepreuve = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementDialogue debut_lepreuve_chapitre1 = new EvenementDialogue(
            new Position (-1, -1, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face),null, null, "l'epreuve_1");
    
    public static EvenementDialogue discourDirecteur_lepreuve_chapitre1 = new EvenementDialogue(
            new Position (Endroits.entree_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_2");
    public static EvenementSpecial sort_lepreuve_chapitre1 = new EvenementSpecial(
            new Position (Endroits.entree_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
    
    
    public static EvenementDeplacement entreeDansForet;
    public static EvenementDialogue dialogue1_lepreuve_chapitre1;
    public static EvenementCombat combat1_lepreuve_chapitre1;
    
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_blanc = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    public static EvenementCombatAnimation combatAnim_lepreuve_chapitre1_blanc = new EvenementCombatAnimation(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_7");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_blanc = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_8");
    public static EvenementCombatAnimation combatAnim2_lepreuve_chapitre1_blanc = new EvenementCombatAnimation(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_9");
    public static EvenementDialogue dialogue4_lepreuve_chapitre1_blanc_bleu = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_10");
    public static EvenementCombat combat2_lepreuve_chapitre1_blanc_bleu = new EvenementCombat(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_11");
    public static EvenementDialogue dialogue5_lepreuve_chapitre1_blanc_bleu = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_12");
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_bleu = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_bleu = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_7");
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_jaune = new EvenementDialogue(
            new Position (Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    public static EvenementAnimation rocher_lepreuve_chapitre1_jaune = new EvenementAnimation(
            new Position (Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_7");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_jaune = new EvenementDialogue(
            new Position (Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_8");
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_marron = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    public static EvenementCombat combat_lepreuve_chapitre1_marron = new EvenementCombat(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_7");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_marron = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_8");
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_noir = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    public static EvenementSpecial rocher_lepreuve_chapitre1_noir = new EvenementSpecial(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_7");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_noir = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_8");
    public static EvenementCombat combat_lepreuve_chapitre1_noir = new EvenementCombat(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_9");
    public static EvenementDialogue dialogue4_lepreuve_chapitre1_noir = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_10");
    public static EvenementCombat combat2_lepreuve_chapitre1_noir = new EvenementCombat(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_11");
    public static EvenementDialogue dialogue5_lepreuve_chapitre1_noir = new EvenementDialogue(
            new Position (Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_12");
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_orange = new EvenementDialogue(
            new Position (Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    public static EvenementCombat combat_lepreuve_chapitre1_orange = new EvenementCombat(
            new Position (Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_7");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_orange = new EvenementDialogue(
            new Position (Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_8");
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_rouge = new EvenementDialogue(
            new Position (Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    public static EvenementSpecial rocher_lepreuve_chapitre1_rouge = new EvenementSpecial(
            new Position (Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_7");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_rouge = new EvenementDialogue(
            new Position (Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_8");
    
    public static EvenementDialogue dialogue2_lepreuve_chapitre1_vert = new EvenementDialogue(
            new Position (Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_6");
    //special avec des plantes vivantes
    public static EvenementSpecial rocher_lepreuve_chapitre1_vert = new EvenementSpecial(
            new Position (Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_7");
    public static EvenementDialogue dialogue3_lepreuve_chapitre1_vert = new EvenementDialogue(
            new Position (Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_8");
    
    public static EvenementDialogue dialogueFin_lepreuve_chapitre1 = new EvenementDialogue(
            new Position (Endroits.sortie_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_14");
    
    
    /* Evènement mission */
    
    public static EvenementQuete premiere_mission_noir = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete premiere_mission_blanc = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete premiere_mission_rouge = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete premiere_mission_jaune = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete premiere_mission_bleu = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete premiere_mission_vert = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete premiere_mission_marron = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete premiere_mission_orange = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete ramasse_feuille = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete entretient_jardin = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    public static EvenementQuete chasser_araignee = new EvenementQuete(true, 
            new Position (5, 7, 0, Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face), 
            null, null, "l'ereuve");
    
    
    /* Fin Evènement mission */
    
}
