/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.objet;

import java.io.Serializable;
import mfiari.lib.game.liste.ListeObjet;
import mfiari.lib.game.liste.ListeObjetVendable;


/**
 *
 * @author mike
 */
public class Sac implements Serializable {
    
    private Objet[] objets_divers;
    private ListeObjetVendable<ObjetDivers> objets;
    private ListeObjetVendable<ObjetCombat> objetsSoin;
    private ListeObjetVendable<ObjetCombat> armes;
    private ListeObjetVendable<ObjetCombat> armures;
    private ListeObjetVendable<ObjetCombat> chapeaux;
    private ListeObjetVendable<ObjetCombat> bottes;
    private ListeObjet<Objet> objetsRare;
    
    public Sac () {
        this.objets_divers = new Objet[20];
        this.objets = new ListeObjetVendable<>();
        this.armes = new ListeObjetVendable<>();
        this.armures = new ListeObjetVendable<>();
        this.chapeaux = new ListeObjetVendable<>();
        this.bottes = new ListeObjetVendable<>();
        this.objetsRare = new ListeObjet<>();
    }

    public Objet[] getObjets_divers() {
        return objets_divers;
    }

    public ListeObjetVendable<ObjetDivers> getObjets() {
        return objets;
    }

    public ListeObjetVendable<ObjetCombat> getObjetsSoin() {
        return objetsSoin;
    }

    public ListeObjetVendable<ObjetCombat> getArmes() {
        return armes;
    }

    public ListeObjetVendable<ObjetCombat> getArmures() {
        return armures;
    }

    public ListeObjetVendable<ObjetCombat> getBottes() {
        return bottes;
    }

    public ListeObjetVendable<ObjetCombat> getChapeaux() {
        return chapeaux;
    }

    public ListeObjet<Objet> getObjetsRare() {
        return objetsRare;
    }
    
    public ListeObjet getSac (int sac) {
        ListeObjet listeObjet = new ListeObjet();
        switch (sac) {
            case 1 :
                for (int i = 0 ; i < this.objets_divers.length ; i++) {
                    listeObjet.ajoutObjet(this.objets_divers[i]);
                }
            case 2 :
                for (int i = 0 ; i < this.objets.size() ; i++) {
                    listeObjet.ajoutObjet(this.objets.getObjet(i));
                }
            case 3 :
                for (int i = 0 ; i < this.objetsSoin.size() ; i++) {
                    listeObjet.ajoutObjet(this.objetsSoin.getObjet(i));
                }
            case 4 :
                for (int i = 0 ; i < this.armes.size() ; i++) {
                    listeObjet.ajoutObjet(this.armes.getObjet(i));
                }
            case 5 :
                for (int i = 0 ; i < this.armures.size() ; i++) {
                    listeObjet.ajoutObjet(this.armures.getObjet(i));
                }
            case 6 :
                for (int i = 0 ; i < this.bottes.size() ; i++) {
                    listeObjet.ajoutObjet(this.bottes.getObjet(i));
                }
            case 7 :
                for (int i = 0 ; i < this.chapeaux.size() ; i++) {
                    listeObjet.ajoutObjet(this.chapeaux.getObjet(i));
                }
            case 8 :
                for (int i = 0 ; i < this.objetsRare.size() ; i++) {
                    listeObjet.ajoutObjet(this.objetsRare.getObjet(i));
                }
        }
        return listeObjet;
    }
    
    public void acheter (Objet obj, int quantite) {
        if (obj instanceof ObjetDivers) {
            this.objets.acheter((ObjetDivers)obj, quantite);
        } else {
            if (obj instanceof ObjetCombat) {
                switch (obj.getCategorie()) {
                    case soin :
                        this.objetsSoin.acheter((ObjetCombat)obj, quantite);
                        break;
                    case arme :
                        this.armes.acheter((ObjetCombat)obj, quantite);
                        break;
                    case armure :
                        this.armures.acheter((ObjetCombat)obj, quantite);
                        break;
                    case botte :
                        this.bottes.acheter((ObjetCombat)obj, quantite);
                        break;
                    case chapeau :
                        this.chapeaux.acheter((ObjetCombat)obj, quantite);
                        break;
                }
            } else {
                this.objetsRare.ajoutObjet(obj);
            }
        }
    }

    public Objet getObjet(int sac, int i) {
        switch (sac) {
            case 1 :
                return this.objets_divers[i];
            case 2 :
                return this.objets.getObjet(i);
            case 3 :
                return this.objetsSoin.getObjet(i);
            case 4 :
                return this.armes.getObjet(i);
            case 5 :
                return this.armures.getObjet(i);
            case 6 :
                return this.bottes.getObjet(i);
            case 7 :
                return this.chapeaux.getObjet(i);
            case 8 :
                return this.objetsRare.getObjet(i);
            default :
                return null;
        }
    }

    public void utilObjet(int sac, int i) {
        switch (sac) {
            case 1 :
                Objet obj = this.objets_divers[i];
                if (obj instanceof ObjetDivers) {
                    ObjetDivers objD = (ObjetDivers) obj;
                    objD.vendre(1);
                } else {
                    if (obj instanceof ObjetCombat) {
                        ObjetCombat objC = (ObjetCombat) obj;
                        objC.vendre(1);
                    }
                }
            case 2 :
                this.objets.utilObjet(i);
            case 3 :
                this.objetsSoin.utilObjet(i);
            case 4 :
                this.armes.utilObjet(i);
            case 5 :
                this.armures.utilObjet(i);
            case 6 :
                this.bottes.utilObjet(i);
            case 7 :
                this.chapeaux.utilObjet(i);
            case 8 :
            default :
        }
    }
}
