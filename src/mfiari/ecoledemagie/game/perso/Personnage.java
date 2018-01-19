/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.perso;

import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.objet.CategorieObjet;
import mfiari.ecoledemagie.game.objet.ObjetCombat;
import mfiari.ecoledemagie.game.objet.ObjetDivers;
import mfiari.ecoledemagie.game.objet.Sac;
import mfiari.ecoledemagie.game.sort.Types;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class Personnage extends Perso {

    private String prenom;
    private String surnom;
    private Equipe equipe;
    private int annee;
    private ObjetCombat arme;
    private ObjetCombat armure;
    private ObjetCombat chapeau;
    private ObjetCombat botte;
    private ObjetCombat accessoire1;
    private ObjetCombat accessoire2;
    private ListeDeSort techniques;
    private Sac sac;
    
    public Personnage(String nom, String prenom, String surnom, Equipe equipe, int annee, int niv, double pv, int pm, int force, int def, int magie, 
            int res, int vit, int prec, int agi, int strat, int vas, int exp, int expNivSup, int expRes) {

        super(nom, niv, pv, pm, force, def, magie, res, vit, prec, agi, strat, vas, exp, 0, 0, 0,null, Orientation.face);
        this.prenom = prenom;
        this.surnom = surnom;
        this.equipe = equipe;
        this.annee = annee;
        this.arme = null;
        this.armure = null;
        this.chapeau = null;
        this.botte = null;
        this.accessoire1 = null;
        this.accessoire2 = null;
        this.techniques=new ListeDeSort();
        //this.sac = new Sac();
    }
    
    public Personnage (Personnage perso) {
       super(perso);
        this.prenom = perso.prenom;
        this.surnom = perso.surnom;
        this.equipe = perso.equipe;
        this.annee = perso.annee;
        this.arme = perso.arme;
        this.armure = perso.armure;
        this.chapeau = perso.chapeau;
        this.botte = perso.botte;
        this.accessoire1 = perso.accessoire1;
        this.accessoire2 = perso.accessoire2;
        this.techniques=perso.techniques;
        this.techniques = perso.techniques;
        this.sac = perso.sac;
    }
    
    

    @Override
    public void setPosition (Position p) {
        super.setPosition(p);
        if (p.getEndroit() != null) {
            p.getEndroit().ajouterGens(this);
        }
    }
    
    @Override
    public String getNom() {
        if (!this.surnom.isEmpty()) {
            return this.surnom;
        }
        if (!this.prenom.isEmpty()) {
            return this.prenom;
        }
        return this.getNom();
    }
    
    public String getNomDeFamille () {
        return super.getNom();
    }
    
    public String getPrenom () {
        return this.prenom;
    }
    
    public String getSurnom () {
        return this.surnom;
    }
    
    public Equipe getEquipe () {
        return this.equipe;
    }

    public ListeDeSort getTechniques () {
        return this.techniques;
    }

    public Sac getSac() {
        return this.sac;
    }

    public ObjetCombat getArme () {
        return this.arme;
    }

    public ObjetCombat getArmure () {
        return this.armure;
    }

    public ObjetCombat getChapeau () {
        return this.chapeau;
    }

    public ObjetCombat getBotte () {
        return this.botte;
    }

    public ObjetCombat getAcc1 () {
        return this.accessoire1;
    }

    public ObjetCombat getAcc2 () {
        return this.accessoire2;
    }

    public boolean estEquipMain () {
        return this.arme!=null;
    }
    
    public ObjetCombat equiper(ObjetCombat equipement) {
        ObjetCombat equip;
        if (equipement.getCategorie().equals(CategorieObjet.arme)) {
            equip = this.arme;
        } else {
            equip = this.armure;
        }
        this.arme = equipement;
        return equip;
    }
    
    public boolean estEquipCorps () {
        return this.armure!=null;
    }
    
    public boolean estEquipTete () {
        return this.chapeau!=null;
    }
    
    public boolean estEquipPied () {
        return this.botte!=null;
    }
    
    public boolean estEquipAcc1 () {
        return this.accessoire1!=null;
    }
   
    public boolean estEquipAcc2 () {
        return this.accessoire2!=null;
    }
    
    public ObjetDivers voleObjet (Perso adv, int i) {
        if (adv instanceof Ennemie) {
            Ennemie ennemie = (Ennemie) adv;
            ObjetDivers objet=null;
            if (i==1) {
                objet=ennemie.getObjet1();
                ennemie.setObjet1();
            }
            if (i==2) {
                objet=ennemie.getObjet2();
                ennemie.setObjet2();
            }
            if (i==4) {
                objet=ennemie.getObjet4();
                ennemie.setObjet4();
            }
            return objet;
        }
        return null;
    }
    
    public ObjetCombat voleEquip (Perso adv, int i) {
        if (adv instanceof Ennemie) {
            Ennemie ennemie = (Ennemie) adv;
            ObjetCombat objet=null;
            if (i==3) {
                objet=ennemie.getObjet3();
                ennemie.setObjet3();
            }
            if (i==5) {
                objet=ennemie.getObjet5();
                ennemie.setObjet5();
            }
            return objet;
        }
        return null;
    }
    
    public Ennemie toEnnemie () {
        Ennemie ennemie = new Ennemie(0, this.getNom(), this.getNiveau(), this.getPv(), this.getPm(), this.getForce(), this.getDefense(), this.getMagie(), 
                this.getResistance(), this.getVitesse(), this.getPrecision(), this.getEsquive(), this.getStrategie(), this.getVAS(), 
                this.getExperience(), 0, Types.humain, Types.aucun);
        ennemie.setListeDeTec(this.techniques);
        ennemie.setStat(this.getStat());
        ennemie.setStrategiePerso(this.getStrategiePerso());
        return ennemie;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Personnage other = (Personnage) obj;
            if ((this.prenom == null) ? (other.prenom != null) : !this.prenom.equals(other.prenom)) {
                return false;
            }
            if ((this.surnom == null) ? (other.surnom != null) : !this.surnom.equals(other.surnom)) {
                return false;
            }
            if (this.equipe != other.equipe) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
