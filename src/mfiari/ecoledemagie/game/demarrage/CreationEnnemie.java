/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import mfiari.ecoledemagie.game.menu.Stat;
import mfiari.ecoledemagie.game.perso.Ennemies;
import mfiari.ecoledemagie.game.strategie.Strategie;
import mfiari.ecoledemagie.game.strategie.StrategieAction;
import mfiari.ecoledemagie.game.strategie.StrategieDeplacement;
import mfiari.ecoledemagie.game.strategie.StrategieObjet;
import mfiari.ecoledemagie.game.strategie.StrategieSort;
import mfiari.ecoledemagie.game.ville.Endroits;
import mfiari.ecoledemagie.game.ville.Environnement;

/**
 *
 * @author mike
 */
public class CreationEnnemie {
    
    public CreationEnnemie() {
        this.CreationEnnemies();
    }
    
    private void CreationEnnemies () {
        Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent.ajouterEnnemie(Ennemies.gnome, Environnement.herbe, 100);
        Ennemies.gnome.setStat(new Stat(Ennemies.gnome, Ennemies.gnome_niv_max));
        Ennemies.gnome.setStrategiePerso(new Strategie(StrategieAction.attaque_proche, StrategieDeplacement.proche, StrategieSort.garder_reserve, 
                StrategieObjet.ne_pas_utiliser));
        Ennemies.fantome.setStat(new Stat(Ennemies.fantome, Ennemies.fantome_niv_max));
        Ennemies.fantome.setStrategiePerso(new Strategie(StrategieAction.attaque_proche, StrategieDeplacement.proche, StrategieSort.garder_reserve, 
                StrategieObjet.ne_pas_utiliser));
        Ennemies.bebeAraignee.setStat(new Stat(Ennemies.bebeAraignee, Ennemies.bebeAraignee_niv_max));
        Ennemies.bebeAraignee.setStrategiePerso(new Strategie(StrategieAction.attaque_proche, StrategieDeplacement.proche, StrategieSort.garder_reserve, 
                StrategieObjet.ne_pas_utiliser));
    }
    
}
