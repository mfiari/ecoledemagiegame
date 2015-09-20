/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author mike
 */
public class PanelPvPerso extends JPanel {
    
    private double pv;
    private double pvi;
    
    public PanelPvPerso () {
        this.pv = 100;
        this.pvi = 100;
    }
    
    public PanelPvPerso (double pv, double pvi) {
        this.pv = pv;
        this.pvi = pvi;
    }
    
    @Override
    public void paintComponent(Graphics g){
        //x1, y1, width, height, arcWidth, arcHeight
        g.drawRoundRect(0, 0, 100, 15, 10, 10);
        Font font = new Font("Arial", Font.BOLD, 12);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("PV", 5, 12);
        if (this.pv > this.pvi / 2) {
            g.setColor(Color.GREEN);
        } else if (this.pv < this.pvi / 5) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.ORANGE);
        }
        int pourcentage = 75;
        if (this.pv != this.pvi) {
            pourcentage = (int)((75 * this.pv) / this.pvi); 
        }
        g.fillRoundRect(25, 0, pourcentage, 15, 10, 10);
    }
}
