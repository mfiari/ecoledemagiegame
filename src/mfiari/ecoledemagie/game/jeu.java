/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game;

import mfiari.ecoledemagie.game.connexionBD.CreationBase;
import mfiari.ecoledemagie.game.controller.Demarrage;
import mfiari.lib.game.connexionBD.ConnexionBD;
import mfiari.lib.game.swing.Ecran;

/**
 *
 * @author mike
 */
public class jeu {
    
    //programme principale du jeu, permet de jouer au jeu
    public static void main(String args[]) {
        Ecran ecran = new Ecran("Ecole de magie");
        ConnexionBD connexionBD = new ConnexionBD();
        CreationBase creationBase = new CreationBase(connexionBD.getConnexionHSQL(Global.hsqlLocation, Global.hsqlUser, Global.hsqlMdp));
        connexionBD.fermerConnexionHSQL();
        Demarrage d = new Demarrage ();
        d.jeu();
    }

}
