/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte.en;

import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;

/**
 *
 * @author mike
 */
public class TexteVueCombat extends mfiari.ecoledemagie.game.texte.TexteVueCombat {

    public TexteVueCombat() {
        this.impossible = "impossible";
        this.pv = "lp";
        this.pm = "mp";
        this.pasObjet = "no object";
        this.etatAffecte = "état affecté";
        this.pasTechnique = "no technique";
        this.surQuiUtiliser = "on which teamate do you want to use it ?";
        this.volRate = "steal missed";
        this.rienAvoler = "nothing to steal";
        this.suivant = "next";
        this.queFaire = "What do you want to do?";
        this.quitter = "quit";
        this.attaquer = "attack";
        this.examiner = "analyse";
        this.objet = "object";
        this.seProteger = "protect yourself";
        this.perso = "perso";
        this.retour = "return";
        this.quiAttaquer = "who do you want to attack";
        this.quelTecUtilise = "which skills do you want to use";
        this.expRecu = "exp receive";
        this.argentRecu = " pièce d'or receive";
        this.niv = "level";
        this.attenteAdv = "waiting for the opponent...";
    }

    @Override
    public String auTourDe(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String attaque(String attaquant, String attaquer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String rateAttaque(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String esquiveAttaque(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String estKO(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String recoitDegat(String perso, int degat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String utilise(String attaquant, String technique) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String utiliseSur(String attaquant, String technique, String attaquer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String examine(String examinateur, String examiner) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String seMetEnGarde(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String monteDeNiveau(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String dejaEnForme(String perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String affichePvPerso(Personnage personnage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String expEtArgent(int expRecu, int monai, int argent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String affichePvPerso(Perso p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
