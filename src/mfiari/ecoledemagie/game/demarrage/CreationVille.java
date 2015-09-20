/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.objet.ObjetEndroitSoin;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.ville.Endroits;
import mfiari.ecoledemagie.game.ville.Environnement;
import mfiari.ecoledemagie.game.ville.Sol;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.objet.ObjetEndroitClassique;
import mfiari.lib.game.objet.ObjetEndroitPassage;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class CreationVille {
    
    private ListeDEndroit carte;

    public CreationVille () {
        this.carte = new ListeDEndroit();
        this.modificationSalle();
        this.modificationBatiment();
        this.modificationQuartier();
        this.modificationQuartierForet();
        this.modificationVille();
        this.modificationSousZone();
        this.modificationZone();
        this.modificationPays();
    }
    
    public CreationVille (EcoleDeMagie jeu) {
        this.carte = new ListeDEndroit();
        this.modificationSalle();
        this.modificationBatiment();
        this.modificationQuartier();
        this.modificationVille();
        this.modificationSousZone();
        this.modificationZone();
        this.modificationPays();
        this.carte.ajouterEndroit(Endroits.continent);
    }

    private void modificationSalle () {

    }

    private void modificationBatiment () {

   }

   private void modificationQuartier () {
       for (int i = 0 ; i < Endroits.entree_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.entree_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.entree_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       Endroits.entree_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.bitume);
   }

   private void modificationQuartierForet () {
       /* niveau0_1_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
       Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3, 0, 
                new Position(3, 1, 0, Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent, Orientation.droite)));
       Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 5, 5, 
                new Position(1, 5, 0, Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_1_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_2_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 2, 5, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(4, 5, 0, Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
       Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,0, 
                new Position(3, 8, 0, Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_2_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_3_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < 5 ; j++) {
           Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
       Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(4, 5, 0, Endroits.sortie_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
       Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 1, 0, Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent, Orientation.droite)));
       Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 5, 5, 
                new Position(0, 5, 0, Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_3_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_4_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
       Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 5, 5, 
                new Position(1, 5, 0, Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 8, 0, Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent, Orientation.gauche)));
       Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_4_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_5_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 0 ; j < Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 2, 5, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 5, 5, 
                new Position(0, 5, 0, Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_5_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_6_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
        Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(4, 5, 0, Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
        Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,0, 
                new Position(3, 8, 0, Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent, Orientation.gauche)));
        Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 0, 0, Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent, Orientation.gauche)));
       Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_6_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_7_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
        Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(5, 5, 0, Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
        Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,0, 
                new Position(3, 9, 0, Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent, Orientation.droite)));
        Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 0, 0, Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent, Orientation.gauche)));
       Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_7_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_8_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 2, 5, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3, 0, 
                new Position(3, 9, 0, Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent, Orientation.gauche)));
       Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 5, 5, 
                new Position(1, 5, 0, Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_8_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_9_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
       Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(5, 5, 0, Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
       Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 0, 0, Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent, Orientation.droite)));
       Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_9_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_10_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
       Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(4, 5, 0, Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
       Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,0, 
                new Position(3, 8, 0, Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_10_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_11_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 3, 4, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,0, 
                new Position(3, 8, 0, Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent, Orientation.gauche)));
       Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 0, 0, Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent, Orientation.droite)));
       Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_11_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_12_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       for (int i = 1 ; i < 5 ; i++) {
           for (int j = 1 ; j < 9 ; j++) {
               Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.herbe, 
                   i, j), Environnement.herbe);
           }
       }
       Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3, 0, 
                new Position(3, 9, 0, Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent, Orientation.gauche)));
       Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 5, 5, 
                new Position(1, 5, 0, Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_12_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_13_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 3, 4, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 0, 0, Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_13_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_14_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 3, 4, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(4, 5, 0, Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
       Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,0, 
                new Position(3, 8, 0, Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_14_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_15_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int i = 4 ; i < Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 3, 4, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(4, 5, 0, Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
       Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,9, 
                new Position(3, 0, 0, Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent, Orientation.droite)));
       Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_2_foret_zoneEcole_zoneMagicoli_continent */
       /* niveau0_16_foret_zoneEcole_zoneMagicoli_continent */
       for (int j = 1 ; j < 5 ; j++) {
           Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int j = 6 ; j < Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.getLongueur() ; j++) {
           Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 0, j));
       }
       for (int i = 0 ; i < 3 ; i++) {
           Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 4 ; i < Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 0));
       }
       for (int i = 0 ; i < Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.getLargeur() ; i++) {
           Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, i, 
                   Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1));
       }
       for (int j = 0 ; j < Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.getLongueur()-1 ; j++) {
           Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitClassique(Type_objet.arbre, 
                   Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.getLargeur()-1, j));
       }
       Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitSoin(Type_objet.fontaine, 3, 4, 
               "voulez-vous vous soignez?"));
       Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 0, 5, 
                new Position(4, 5, 0, Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos)));
       Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.ajouterObjetEndroit(new ObjetEndroitPassage(Type_objet.herbe, 3,0, 
                new Position(3, 8, 0, Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent, Orientation.face)));
       Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin niveau0_16_foret_zoneEcole_zoneMagicoli_continent */
       /* sortie_foret_zoneEcole_zoneMagicoli_continent */
       Endroits.sortie_foret_zoneEcole_zoneMagicoli_continent.setSol(Sol.herbe);
       /* fin sortie_foret_zoneEcole_zoneMagicoli_continent */
   }

    private void modificationVille() {
    }

    private void modificationSousZone () {
    }

    private void modificationZone () {
        
    }

    private void modificationPays () {
    }

    public ListeDEndroit getEndroit () {
        return this.carte;
    }
    
}
