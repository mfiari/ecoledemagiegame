/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import mfiari.ecoledemagie.game.liste.ListeDeMission;
import mfiari.ecoledemagie.game.mission.Missions;
import static mfiari.ecoledemagie.game.perso.Equipe.blanc;
import static mfiari.ecoledemagie.game.perso.Equipe.jaune;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.sort.Sorts;

/**
 *
 * @author mike
 */
public class CreationMission {
    
    private Personnage capitaine;
    private ListeDeMission missions;

    public CreationMission() {
        this.CreationMission();
        this.missions = new ListeDeMission();
    }
    
    public CreationMission(Personnage capitaine) {
        this.capitaine = capitaine;
        this.CreationMission();
        this.missions = new ListeDeMission();
        
    }

    private void CreationMission() {
        switch (this.capitaine.getEquipe()) {
            case blanc :
                this.missions.ajoutMission(Missions.premiere_mission_blanc);
                break;
            case jaune :
                this.missions.ajoutMission(Missions.premiere_mission_jaune);
                break;

        }
        Missions.ramasse_feuille.setSortRequis(Sorts.vent);
        this.missions.ajoutMission(Missions.ramasse_feuille);
        
        Missions.entretient_jardin.setSortRequis(Sorts.vent);
        Missions.entretient_jardin.setSortRequis(Sorts.coupe);
        Missions.entretient_jardin.setSortRequis(Sorts.eau);
        this.missions.ajoutMission(Missions.ramasse_feuille);
    }
    
    public ListeDeMission getMissions () {
        return this.missions;
    }
    
}
