/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.strategie;

/**
 *
 * @author mike
 */
public enum StrategieSort {
    libre /* utilise sort librement */, ne_pas_utiliser /* n'utilise pas de sort */, sans_compter /* utilise sort sans se soucier des pm */, 
    garder_reserve /* utilise sort si pm > 25% */, retenir /* utilise sort si pm > 50% */, sauvegarder /* utilise sort si pm > 75% */, 
    moderation /* utilise moins sort si pm < 50% */, soin /* utilise sort soin uniquement */, support /* utilise sort support uniquement */, 
    sort_uniquement /* utilise que des sorts */, sort_magic /* n'utilise que des sorts infligeant des dégat magique */, 
    sort_physique /* n'utilise que des sorts infligeant des dégat physique */, affaiblir  /* n'utilise que des sorts qui affaiblisse adv */, 
    magie_indiv /* n'utilise que des sorts visant 1 adv */, magie_groupe /* n'utilise que des sorts visant plusieurs adv */;
    
    public int getNiveau () {
        if (this == libre || this == ne_pas_utiliser) {
            return 1;
        } else if (this == sans_compter || this == garder_reserve) {
            return 2;
        } else if (this == retenir || this == sauvegarder) {
            return 3;
        } else if (this == moderation) {
            return 1;
        } else {
            return 5;
        }
    }
}
