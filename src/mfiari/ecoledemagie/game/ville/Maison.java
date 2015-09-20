/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

/**
 *
 * @author mike
 */
public class Maison extends Batiments{

    public Maison () {
        super();
    }
    
    public Maison (String nom, int positionX, int positionY,int longueur, int largeur, int etageMax) {
        super(nom, positionX, positionY, longueur, largeur, 0, etageMax);
    }

}
