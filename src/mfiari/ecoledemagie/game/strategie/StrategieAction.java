/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.strategie;

/**
 *
 * @author mike
 */
public enum StrategieAction {
    libre /* attaque librement selon le personnage */, ne_rien_faire /* ne fait aucune action */, 
    defense /* utilise des sort ou des objets pour se protéger */, 
    protection /* utilise des sorts ou des objets pour protéger l'allier avec le moins de pv */, attaque_proche /* attaque l'adv le plus près */, 
    attaque_loin /* attaque l'adv le plus loin */ , attaque_meme /* attaque le même adv */, attaque_different /* attaque un adv différent*/, 
    aerien /* attaque en priorité les adv volant */, bloque_magie /* attaque en priorité les adv utilisant la magie */, 
    reduire /* attaque l'adv ayant le moins de point de vie */, pacifique /* n'utilise aucun sort ou objet offensif */;
    
    public int getNiveau () {
        if (this == libre || this == ne_rien_faire) {
            return 1;
        } else if (this == defense || this == protection) {
            return 2;
        } else if (this == attaque_proche || this == attaque_loin || this == attaque_meme || this == attaque_different) {
            return 3;
        } else if (this == aerien || this == bloque_magie) {
            return 1;
        } else {
            return 5;
        }
    }
}
