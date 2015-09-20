/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.terminal;

import java.util.Scanner;
import mfiari.lib.game.texte.Texte;

/**
 *
 * @author mike
 */
public abstract class EcoleDeMagie_vueConsole {
    
    protected Scanner sc = new Scanner(System.in);
    private Texte texte;
    
    public EcoleDeMagie_vueConsole () {
        
    }
    
    public EcoleDeMagie_vueConsole (Texte texte) {
        this.texte = texte;
    }
    
    public int testEntier () {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            System.out.println("entrez un entier");
            sc.next();
            return -1;
        }
    }
    
    public char testChar () {
        String s = this.sc.next();
        if (s.length() == 1) {
            return s.charAt(0);
        } else {
            return '0';
        }
    }
    
    public void suivant() {
        int a;
        do {
            System.out.println("1="+this.texte.suivant);
            a = this.testEntier();
        } while (a != 1);
    }
    
}
