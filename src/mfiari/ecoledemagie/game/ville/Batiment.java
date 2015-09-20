/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

/**
 *
 * @author mike
 */
public class Batiment extends Batiments{

    public Batiment () {
        super();
    }
    
    public Batiment (String nom, int positionX, int positionY,int longueur, int largeur, int etageMin, int etageMax) {
        super(nom, positionX, positionY, longueur, largeur, etageMin, etageMax);
    }
}
