/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.texte;

import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.lib.game.texte.Texte;

/**
 *
 * @author mike
 */
abstract public class TexteVueCombat extends Texte {
    
    public String impossible;
    public String pasObjet;
    public String etatAffecte;
    public String pasTechnique;
    public String surQuiUtiliser;
    public String volRate;
    public String rienAvoler;
    public String attaquer;
    public String examiner;
    public String seProteger;
    public String perso;
    public String retour;
    public String quiAttaquer;
    public String quelTecUtilise;
    public String expRecu;
    public String argentRecu;
    public String pv;
    public String pm;
    public String objet;
    public String quitter;
    public String queFaire;
    public String niv;
    public String attenteAdv;


    public static TexteVueCombat getInstance() {
        return (TexteVueCombat) TexteVueCombat.getInstance(TexteVueCombat.class);
    }


    public abstract String auTourDe (String perso);

    public abstract String attaque (String attaquant, String attaquer);

    public abstract String rateAttaque (String perso);

    public abstract String esquiveAttaque (String perso);

    public abstract String estKO (String perso);

    public abstract String recoitDegat (String perso, int degat);

    public abstract String utilise (String attaquant, String technique);

    public abstract String utiliseSur (String attaquant, String technique, String attaquer);

    public abstract String examine (String examinateur, String examiner);
    
    public abstract String seMetEnGarde (String perso);

    public abstract String monteDeNiveau (String perso);
    
    public abstract String dejaEnForme(String perso);

    public abstract String affichePvPerso(Personnage personnage);

    public abstract String expEtArgent(int expRecu, int monai, int argent);

    public abstract String affichePvPerso(Perso p);
}
