/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.strategie;

/**
 *
 * @author mike
 */
public enum StrategieDeplacement {
    libre /* se deplace librement sur le terrain */, immobile /* ne bouge pas */, proche /* reste près de l'adv */, eloigne /*reste eloigné de l'adv*/;
    
    public int getNiveau () {
        if (this == libre || this == immobile) {
            return 1;
        } else if (this == proche || this == eloigne) {
            return 2;
        } else {
            return 5;
        }
    }
}
