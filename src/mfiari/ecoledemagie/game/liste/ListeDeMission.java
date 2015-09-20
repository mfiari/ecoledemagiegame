/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.liste;

import mfiari.ecoledemagie.game.mission.Mission;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class ListeDeMission implements Serializable {
    
    private ArrayList<Mission> listeDeMission;

    public ListeDeMission() {
        this.listeDeMission = new ArrayList<>();
    }

    public void ajoutMission(Mission nom) {
        this.listeDeMission.add(nom);
    }

    public void remplaceMission(Mission nom, int i) {
        ArrayList<Mission> tecs = new ArrayList<>();
        while (i + 1 < this.listeDeMission.size()) {
            tecs.add(this.listeDeMission.get(i + 1));
            this.listeDeMission.remove(i + 1);
        }
        this.listeDeMission.remove(i);
        this.listeDeMission.add(nom);
        for (int j = 0; j < tecs.size(); j++) {
            this.listeDeMission.add(tecs.get(i));
        }
    }

    public void enleverSort(int num) {
        this.listeDeMission.remove(num);
    }

    public int size() {
        return this.listeDeMission.size();
    }

    public Mission getSort(int i) {
        return this.listeDeMission.get(i);
    }
}
