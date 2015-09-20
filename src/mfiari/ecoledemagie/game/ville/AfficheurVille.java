/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.demarrage.CreationVille;
import mfiari.ecoledemagie.game.perso.Habitants;
import mfiari.ecoledemagie.game.perso.Personnages;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.lib.game.swing.Ecran;

/**
 *
 * @author mike
 */
public class AfficheurVille {
    
    public static void main(String args[]) {
        AfficheurVille afficheurVille = new AfficheurVille();
        afficheurVille.testAffichageEndroit();
    }
    
    public void testAffichageEndroit() {
        Ecran ecran = new Ecran();
        CreationVille creationVille = new CreationVille();
        Habitants habitants = new Habitants();
        Personnages personnages = new Personnages();
        Endroit endroit = Endroits.niveau0_10_foret_zoneEcole_zoneMagicoli_continent;
        EcoleDeMagie ecoleDeMagie  = new EcoleDeMagie();
        ecoleDeMagie.afficherEndroit(endroit);
        this.attendre();
    }
    
    public synchronized void attendre () {
        try {
            this.wait(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(AfficheurVille.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
