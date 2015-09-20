/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.swing;

import mfiari.ecoledemagie.game.ville.Endroit;
import java.awt.Color;

/**
 *
 * @author mike
 */
public class ColorEndroit {
    
    public static Color getColorByEndroit (Endroit endroit) {
        return Color.RED;
    }
    
    /*public static Color getColorByObjetEndroit (Objet_endroit objet_endroit) {
        Type_objet type = objet_endroit.getType();
        switch (type) {
            case chemin :
                return Color.getHSBColor(1, 1, 1);
            case mer :
                return Color.CYAN;
            case riviere :
                return Color.blue;
        }
        return Color.GREEN;
    }*/
    
    /*public static Color getColorByTerrain (Terrain terrain) {
        Color affichageTypeTerrain;
        switch (terrain.getType()) {
            case plante:
                affichageTypeTerrain = Color.GREEN;
                break;
            case eau:
                affichageTypeTerrain = Color.BLUE;
                break;
            case feu:
                affichageTypeTerrain = Color.RED;
                break;
            case roche:
                affichageTypeTerrain = Color.DARK_GRAY;
                break;
            case glace:
                affichageTypeTerrain = Color.CYAN;
                break;
            case sol:
                affichageTypeTerrain = Color.LIGHT_GRAY;
                break;
            case psy:
                affichageTypeTerrain = Color.MAGENTA;
                break;
            case acier:
                affichageTypeTerrain = Color.GRAY;
                break;
            case vol:
                affichageTypeTerrain = Color.WHITE;
                break;
            case insecte:
                affichageTypeTerrain = Color.GREEN;
                break;
            case poison:
                affichageTypeTerrain = Color.MAGENTA;
                break;
            case dragon:
                affichageTypeTerrain = Color.ORANGE;
                break;
            case tenebres:
                affichageTypeTerrain = Color.BLACK;
                break;
            case electrique:
                affichageTypeTerrain = Color.YELLOW;
                break;
            case combat:
                affichageTypeTerrain = Color.LIGHT_GRAY;
                break;
            case spectre:
                affichageTypeTerrain = Color.DARK_GRAY;
                break;
            default:
                affichageTypeTerrain = Color.WHITE;
                break;
        }
        return affichageTypeTerrain;
    }*/
}
