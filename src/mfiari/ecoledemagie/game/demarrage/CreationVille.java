/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.objet.ObjetEndroitSoin;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.ville.Endroits;
import mfiari.ecoledemagie.game.ville.Environnement;
import mfiari.ecoledemagie.game.ville.Sol;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.objet.ObjetEndroitClassique;
import mfiari.lib.game.objet.ObjetEndroitPassage;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    
    public void createXMLFile () {
        ListeDEndroit endroits = new ListeDEndroit();
        endroits.ajouterEndroit(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_1_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_2_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_3_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_4_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_5_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_6_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_7_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_8_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_9_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_11_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_12_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.sortie_foret_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.quartierParc_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.sous_sol_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.rdc_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.salle_des_prof_rdc_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.salle_a_manger_rdc_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.salle_des_fete_rdc_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.etage1_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.etage2_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.etage3_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.etage4_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.etage5_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.tour_astronomie_chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent);
        endroits.ajouterEndroit(Endroits.quartierVillage_magicoli_zoneEcole_zoneMagicoli_continent);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            
            for (int k = 0 ; k < endroits.size() ; k++) {
                mfiari.lib.game.ville.Endroit e = (mfiari.lib.game.ville.Endroit) endroits.getEndroit(k);
                Document doc = db.newDocument();
                Element endroitElement = doc.createElement("endroit");
                endroitElement.setAttribute("nom", e.getNom());
                endroitElement.setAttribute("longueur", String.valueOf(e.getLongueur()));
                endroitElement.setAttribute("largeur", String.valueOf(e.getLongueur()));
                endroitElement.setAttribute("positionx", String.valueOf(e.getPosition().getPositionX()));
                endroitElement.setAttribute("positiony", String.valueOf(e.getPosition().getPositionY()));
                endroitElement.setAttribute("type", "route");
                /*endroitElement.setAttribute("terrain", e.getTerrain().getType().toString());*/
                
                Element solElement = doc.createElement("sol");
                solElement.setAttribute("type", "herbe");
                endroitElement.appendChild(solElement);
                
                Element objetEndroitsElement = doc.createElement("objet_endroits");
                Element batimentsElement = doc.createElement("batiments");
                Element gensElement = doc.createElement("gens");
                for (int i = 0; i < e.getLargeur(); i++) {
                    for (int j = 0; j < e.getLongueur(); j++) {
                        Position p = new Position(i, j);
                        if (e.aEndroit(p) != null) {
                            mfiari.lib.game.ville.Endroit batiment = (mfiari.lib.game.ville.Endroit)e.aEndroit(p);
                            Element batimentElement = doc.createElement("batiment");
                            /*batimentElement.setAttribute("type", this.getNomImageBatimentByClass(batiment));*/
                            batimentElement.setAttribute("positionx", String.valueOf(p.getPositionX()));
                            batimentElement.setAttribute("positiony", String.valueOf(p.getPositionY()));
                            batimentsElement.appendChild(batimentElement);
                        } else if (e.aGens(p) != null) {
                            Gens gens = e.aGens(p);
                            Element habitantElement = doc.createElement("habitant");
                            habitantElement.setAttribute("nom", gens.getNom());
                            habitantElement.setAttribute("positionx", String.valueOf(p.getPositionX()));
                            habitantElement.setAttribute("positiony", String.valueOf(p.getPositionY()));
                            gensElement.appendChild(habitantElement);
                        } else if (e.aObjetEndroit(p) != null) {
                            ObjetEndroit objet_endroit = e.aObjetEndroit(p);
                            Element objetEndroitElement = doc.createElement("objet_endroit");
                            objetEndroitElement.setAttribute("type", objet_endroit.getType().toString());
                            objetEndroitElement.setAttribute("positionx", String.valueOf(p.getPositionX()));
                            objetEndroitElement.setAttribute("positiony", String.valueOf(p.getPositionY()));
                            objetEndroitsElement.appendChild(objetEndroitElement);
                        }
                    }
                }
                endroitElement.appendChild(objetEndroitsElement);
                endroitElement.appendChild(batimentsElement);
                endroitElement.appendChild(gensElement);
                doc.appendChild(endroitElement);
                
                FileWriter fileWriter = new FileWriter(new File("media/endroits/"+k+".xml"));
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                transformer.transform(new DOMSource(doc), new StreamResult(fileWriter));
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreationVille.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | TransformerException ex) {
            throw new RuntimeException("Error converting to String", ex);
        } catch (IOException ex) {
            Logger.getLogger(CreationVille.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
