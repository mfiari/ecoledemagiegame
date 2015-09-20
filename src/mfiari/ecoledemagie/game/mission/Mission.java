/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.mission;

import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.sort.Sort;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.liste.ListeObjet;
import mfiari.lib.game.objet.Objet;

/**
 *
 * @author mike
 */
public class Mission {
    
    protected EvenementQuete evenement;
    private int argent;
    private ListeObjet objetsGagner;
    private int expGagner;
    private int minParticipant;
    private int maxParticipant;
    private String description;
    private int difficulte;
    private ListeDeSort sortsRequis;
    private ListeObjet objetsRequis;
    
    public Mission (EvenementQuete evenement, int argent, int expGagner, int minParticipant, int maxParticipant, int difficulte, String description) {
        this.evenement = evenement;
        this.argent = argent;
        this.expGagner = expGagner;
        this.minParticipant = minParticipant;
        this.maxParticipant = maxParticipant;
        this.difficulte = difficulte;
        this.description = description;
    }

    public EvenementQuete getEvenement() {
        return evenement;
    }

    public void setEvenement(EvenementQuete evenement) {
        this.evenement = evenement;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public ListeObjet getObjetsGagner() {
        return objetsGagner;
    }

    public void setObjetGagner(Objet objetGagner) {
        this.objetsGagner.ajoutObjet(objetGagner);
    }

    public void setObjetGagner(Objet objetGagner, int quantite) {
        this.objetsGagner.acheter(objetGagner, quantite);
    }

    public void setObjetsGagner(ListeObjet objetsGagner) {
        this.objetsGagner = objetsGagner;
    }

    public int getExpGagner() {
        return expGagner;
    }

    public void setExpGagner(int expGagner) {
        this.expGagner = expGagner;
    }

    public int getMinParticipant() {
        return minParticipant;
    }

    public void setMinParticipant(int minParticipant) {
        this.minParticipant = minParticipant;
    }

    public int getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(int maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public ListeDeSort getSortsRequis() {
        return sortsRequis;
    }

    public void setSortRequis(Sort sortRequis) {
        this.sortsRequis.ajoutSort(sortRequis);
    }

    public void setSortsRequis(ListeDeSort sortsRequis) {
        this.sortsRequis = sortsRequis;
    }

    public ListeObjet getObjetsRequis() {
        return objetsRequis;
    }

    public void setObjetsRequis(ListeObjet objetsRequis) {
        this.objetsRequis = objetsRequis;
    }

    public void setObjetRequis(Objet objetsRequis) {
        this.objetsRequis.ajoutObjet(objetsRequis);
    }

    public void setObjetRequis(Objet objetsRequis, int quantite) {
        this.objetsRequis.acheter(objetsRequis, quantite);
    }
    
}
