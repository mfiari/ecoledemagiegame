/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.strategie;

/**
 *
 * @author mike
 */
public enum StrategieObjet {
    libre /* utilise librement les objets */, ne_pas_utiliser /* n'utilise aucun objet */, objet_uniquement /* utilise uniquement des objets */, 
    soutient /* n'utilise que des objets de soutients */, soin /* n'utilise que des objets de soin */, 
    affaiblir /* n'utilise que des objets pour affaiblir adv*/, sans_compter /* utilise des objets sans se soucier du nombre restant */, 
    garder_reserve /* n'utilise pas d'objet si inférieure à 10 */, retenir  /* n'utilise pas d'objet si inférieure à 30 */, 
    sauvegarder  /* n'utilise pas d'objet si inférieure à 50 */;
    
    public int getNiveau () {
        if (this == libre || this == ne_pas_utiliser) {
            return 1;
        } else {
            return 5;
        }
    }
}
