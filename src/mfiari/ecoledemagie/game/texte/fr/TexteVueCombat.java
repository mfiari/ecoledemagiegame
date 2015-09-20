/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte.fr;

import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;

/**
 *
 * @author mike
 */
public class TexteVueCombat extends mfiari.ecoledemagie.game.texte.TexteVueCombat {
    
    public TexteVueCombat() {
        this.impossible = "impossible";
        this.pv = "pv";
        this.pm = "pm";
        this.pasObjet = "pas d'objet";
        this.etatAffecte = "état affecté";
        this.pasTechnique = "pas de technique";
        this.surQuiUtiliser = " sur qui l'utiliser ?";
        this.volRate = "vol raté";
        this.rienAvoler = "rien à voler";
        this.suivant = "suivant";
        this.queFaire = "Que faire?";
        this.quitter = "quitter";
        this.attaquer = "attaquer";
        this.examiner = "examiner";
        this.objet = "objet";
        this.seProteger = "se proteger";
        this.perso = "perso";
        this.retour = "retour";
        this.quiAttaquer = "qui attaquer?";
        this.quelTecUtilise = "quel technique utiliser?";
        this.expRecu = "exp reçu";
        this.argentRecu = " pièce d'or reçu";
        this.niv = "niv";
        this.attenteAdv = "En attente du joueur adverse...";
    }

    @Override
    public String auTourDe(String perso) {
        return "au tour de " + perso;
    }

    @Override
    public String attaque(String attaquant, String attaquer) {
        return attaquant + " attaque " + attaquer;
    }

    @Override
    public String rateAttaque(String perso) {
        return perso + " rate son attaque";
    }

    @Override
    public String esquiveAttaque(String perso) {
        return perso + " esquive l'attaque";
    }

    @Override
    public String estKO(String perso) {
        return perso + " est K.O";
    }

    @Override
    public String recoitDegat(String perso, int degat) {
        return perso + " reçoit " + degat + " degat";
    }

    @Override
    public String utilise(String attaquant, String technique) {
        return attaquant + " utilise" + technique;
    }

    @Override
    public String utiliseSur(String attaquant, String technique, String attaquer) {
        return attaquant + " utilise " + technique + " sur " + attaquer;
    }

    @Override
    public String examine(String examinateur, String examiner) {
        return examinateur + " examine " + examiner;
    }

    @Override
    public String seMetEnGarde(String perso) {
        return perso + " se met en garde";
    }

    @Override
    public String monteDeNiveau(String perso) {
        return perso + " monte de niveau";
    }

    @Override
    public String expEtArgent(int expRecu, int monai, int argent) {
        return this.expRecu + " : " + expRecu + this.argentRecu + " : " + monai + " piece d'or : " + argent;
    }

    @Override
    public String affichePvPerso(Perso p) {
        return p.getNom() + " : " + this.pv.toUpperCase() + " : " + Math.round(p.getPv());
    }

    @Override
    public String dejaEnForme(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String affichePvPerso(Personnage personnage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
