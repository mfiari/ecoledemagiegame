/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.terminal;

import mfiari.ecoledemagie.game.extra.MenuExtra;
import mfiari.ecoledemagie.game.texte.TexteVueMenuExtra;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author mike
 */
public class EcoleDeMagie_vueConsole_menuExtra extends EcoleDeMagie_vueConsole {
    
    private MenuExtra menuExtra;
    private TexteVueMenuExtra textes;
    
    public EcoleDeMagie_vueConsole_menuExtra (MenuExtra menuExtra){
        this.menuExtra = menuExtra;
        this.textes = TexteVueMenuExtra.getInstance();
        this.menuExtra.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("affichage")) {
                }
            }
        });
    }
    
}
