/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.liste;

import mfiari.ecoledemagie.game.sort.Sort;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class ListeDeSort implements Serializable {
    
    private ArrayList<Sort> listeDeTec;

    public ListeDeSort() {
        this.listeDeTec = new ArrayList<>();
    }

    public void ajoutSort(Sort nom) {
        this.listeDeTec.add(nom);
    }

    public void remplaceSort(Sort nom, int i) {
        ArrayList<Sort> tecs = new ArrayList<>();
        while (i + 1 < this.listeDeTec.size()) {
            tecs.add(this.listeDeTec.get(i + 1));
            this.listeDeTec.remove(i + 1);
        }
        this.listeDeTec.remove(i);
        this.listeDeTec.add(nom);
        for (int j = 0; j < tecs.size(); j++) {
            this.listeDeTec.add(tecs.get(i));
        }
    }

    public void enleverSort(int num) {
        this.listeDeTec.remove(num);
    }

    public int size() {
        return this.listeDeTec.size();
    }

    public Sort getSort(int i) {
        return this.listeDeTec.get(i);
    }

    public Sort getSort(String nom) {
        for (int i = 0; i < this.listeDeTec.size(); i++) {
            if (this.listeDeTec.get(i).getNom().equals(nom)) {
                return this.listeDeTec.get(i);
            }
        }
        return null;
    }
    
}
