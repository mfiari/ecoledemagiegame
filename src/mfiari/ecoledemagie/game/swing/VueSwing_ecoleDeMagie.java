/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.swing;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.media.image.objetEndroit.ImageObjetEndroit;
import mfiari.ecoledemagie.game.media.image.personnage.ImagePersonnage;
import mfiari.ecoledemagie.game.media.image.sol.ImageSol;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.texte.TexteVueEcoleDeMagie;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.PanelDeTexteSuivant;
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.VueSwing;
import mfiari.lib.game.ville.Endroit;

/**
 *
 * @author mike
 */
public class VueSwing_ecoleDeMagie extends VueSwing {
    
    private final EcoleDeMagie jeu;
    private final TexteVueEcoleDeMagie textes;
    
    public VueSwing_ecoleDeMagie(EcoleDeMagie jeu) {
        super(TexteVueEcoleDeMagie.getInstance(), jeu);
        this.jeu = jeu;
        this.textes = TexteVueEcoleDeMagie.getInstance();
        Ecran.panel.redimenssionnerCentre(1200, 550);
        Ecran.panel.redimenssionnerSud(1200, 100);
        Ecran.panel.afficherCentre();
        Ecran.panel.afficherSud();
        Ecran.panel.cacherEst();
        Ecran.panel.cacherOuest();
        Ecran.panel.cacherNord();
        this.jeu.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("afficheEndroit")) {
                    afficheEndroit();
                } else if (evt.getPropertyName().equals("deplacerPerso")) {
                    afficheEndroit();
                } else {
                    if (evt.getPropertyName().equals("afficherObjetMagasin")) {
                        afficherObjetMagasin();
                    }
                    if (evt.getPropertyName().equals("gagnerCombat")) {
                        gagnerCombat();
                    }
                    if (evt.getPropertyName().equals("perdu")) {
                        Perdu();
                    }
                    if (evt.getPropertyName().equals("gagne")) {
                        Gagne();
                    }
                    if (evt.getPropertyName().equals("parole")) {
                        parole();
                    }
                    if (evt.getPropertyName().equals("affichage")) {
                        affichage();
                    }
                    if (evt.getPropertyName().equals("confirmation")) {
                        confirmation();
                    }
                    if (evt.getPropertyName().equals("choix")) {
                        choix();
                    }
                    if (evt.getPropertyName().equals("suivant")) {
                        suivant();
                    }
                    if (evt.getPropertyName().equals("actionJeu")) {
                        actionJeu();
                    }
                    attendre();
                }
            }
        });
    }

    public void actionJeu() {
        JPanel panel2 = new JPanel (new GridLayout(2, 3));
        JButton boutonHaut = new JButton(this.textes.haut);
        boutonHaut.addActionListener(new boutonChoix(1));
        JButton boutonBas = new JButton(this.textes.bas);
        boutonBas.addActionListener(new boutonChoix(2));
        JButton boutonDroite = new JButton(this.textes.droite);
        boutonDroite.addActionListener(new boutonChoix(3));
        JButton boutonGauche = new JButton(this.textes.gauche);
        boutonGauche.addActionListener(new boutonChoix(4));
        JButton boutonAction = new JButton(this.textes.action);
        boutonAction.addActionListener(new boutonChoix(5));
        JButton boutonMenu = new JButton(this.textes.menu);
        boutonMenu.addActionListener(new boutonChoix(6));
        panel2.add(boutonHaut);
        panel2.add(boutonBas);
        panel2.add(boutonDroite);
        panel2.add(boutonGauche);
        panel2.add(boutonAction);
        panel2.add(boutonMenu);
        Ecran.panel.ajouterSud(panel2);
    }

    public void Perdu() {
        /*
         * message lorsque l'on a perdu la partie (game over)
         */
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte("GAME OVER");
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }
    
    public void Gagne() {
        /*
         * message lorsque l'on a perdu la partie (game over)
         */
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte("Vous avez fini le jeu!");
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }

    public void gagnerCombat() {
        /*
         * message lorsque l'on gagne un combat
         */
    }

    public void parole() {
        /*
         * affichage lorsque qu'un personnage parle
         */
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte(this.jeu.getParole());
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }

    public void affichage() {
        /*
         * pour faire des affichages divers
         */
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte(this.jeu.getAffichage());
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }

    public void confirmation () {
        JPanel panel2 = new JPanel (new GridLayout(2, 1));
        PanelDeTexteAffichage panelTexteAffichage = new PanelDeTexteAffichage();
        panelTexteAffichage.ajouterTexte(this.jeu.getAffichage());
        JButton boutonOui = new JButton(this.textes.oui);
        boutonOui.addActionListener(new boutonConfirmation(true));
        JButton boutonNon = new JButton(this.textes.non);
        boutonNon.addActionListener(new boutonConfirmation(false));
        panel2.add(panelTexteAffichage);
        JPanel panelBouton = new JPanel ();
        panelBouton.add(boutonOui);
        panelBouton.add(boutonNon);
        panel2.add(panelBouton);
        Ecran.panel.ajouterSud(panel2);
    }

    public void choix () {
        System.out.println(this.jeu.getAffichage());
    }

    public void afficheEndroit() {
        Endroit e = (Endroit)this.jeu.getEndroit();
        Ecran.panel.changerCentre(e.getLargeur(), e.getLongueur(), 1250, 550);
        int width = (Ecran.panel.getPanelCentre().getWidth() / e.getLongueur());
        int height = (Ecran.panel.getPanelCentre().getHeight() / e.getLargeur());
        ImagePersonnage imagePersonnage = new ImagePersonnage();
        for (int i = 0; i < e.getLargeur(); i++) {
            for (int j = 0; j < e.getLongueur(); j++) {
                if (this.jeu.getEquipe2().getPersonnage(0).getPosition().getPositionX() == i
                        && this.jeu.getEquipe2().getPersonnage(0).getPosition().getPositionY() == j) {
                    if (imagePersonnage.aImagePerso(this.jeu.getEquipe2().getPersonnage(0), ImagePersonnage.DEHORS)) {
                        PanelImage image = new PanelImage(imagePersonnage.getImagePerso(this.jeu.getEquipe2().getPersonnage(0), width, height, ImagePersonnage.DEHORS));
                        Ecran.panel.getPanelCentre().ajouterContent(image, i, j, 1);
                    } else {
                        Ecran.panel.getPanelCentre().ajouterContent(new PanelDeTexteAffichage(this.jeu.getEquipe2().getPersonnage(0).getNom()), i, j, 1);
                    }
                } else if (e.aEndroit(new Position(i, j)) != null) {
                    Ecran.panel.getPanelCentre().ajouterContent(new PanelDeTexteAffichage(e.aEndroit(new Position(i, j)).getNom()), i, j, 1);
                } else if (e.aGens(new Position(i, j)) != null) {
                    Ecran.panel.getPanelCentre().ajouterContent(new PanelDeTexteAffichage(e.aGens(new Position(i, j)).getNom()), i, j, 1);
                } else if (e.aObjetEndroit(new Position(i, j)) != null) {
                    ObjetEndroit objet_endroit = e.aObjetEndroit(new Position(i, j));
                    if (ImageObjetEndroit.getNivImageObjet((Type_objet)objet_endroit.getType()) == 1) {
                        String nom = ImageObjetEndroit.getNomImageObjetByType((Type_objet)objet_endroit.getType());
                        ImageObjetEndroit imageObjet = new ImageObjetEndroit();
                        if (imageObjet.aImageObjet(nom)) {
                            PanelImage image;
                            if (ImageObjetEndroit.getImageObjetWidthEtHeight((Type_objet)objet_endroit.getType())) {
                                image = new PanelImage(imageObjet.getImageObjetWidthEtHeigth(nom, width, height));
                            } else {
                                image = new PanelImage(imageObjet.getImageObjetWidthOrHeigth(nom, width, height));
                            }
                            Ecran.panel.getPanelCentre().ajouterContent(image, i, j, 1);
                        } else {
                            Ecran.panel.getPanelCentre().ajouterContent(new PanelDeTexteAffichage(e.aObjetEndroit(new Position(i, j)).getNom()), i, j, 1);
                        }
                    }
                } else {
                    Ecran.panel.getPanelCentre().ajouterContent(null, i, j);
                }
                if (e.aObjetEndroit(new Position(i, j)) != null) {
                    ObjetEndroit objet_endroit = e.aObjetEndroit(new Position(i, j));
                    if (ImageObjetEndroit.getNivImageObjet((Type_objet)objet_endroit.getType()) == 0) {
                        String nom = ImageObjetEndroit.getNomImageObjetByType((Type_objet)objet_endroit.getType());
                        ImageObjetEndroit imageObjet = new ImageObjetEndroit();
                        if (imageObjet.aImageObjet(nom)) {
                            PanelImage image;
                            if (ImageObjetEndroit.getImageObjetWidthEtHeight((Type_objet)objet_endroit.getType())) {
                                image = new PanelImage(imageObjet.getImageObjetWidthEtHeigth(nom, width, height));
                            } else {
                                image = new PanelImage(imageObjet.getImageObjetWidthOrHeigth(nom, width, height));
                            }
                            Ecran.panel.getPanelCentre().ajouterContent(image, i, j, 0);
                        } else {
                            Ecran.panel.getPanelCentre().ajouterContent(new PanelDeTexteAffichage(e.aObjetEndroit(new Position(i, j)).getNom()), i, j, 1);
                        }
                    } else {
                        ObjetEndroit objet_endroit_sol = ImageObjetEndroit.getSolImageObjetByType((Type_objet)objet_endroit.getType());
                        if (objet_endroit_sol != null) {
                            ImageObjetEndroit imageObjet = new ImageObjetEndroit();
                            String nom = ImageObjetEndroit.getNomImageObjetByType((Type_objet)objet_endroit_sol.getType());
                            if (imageObjet.aImageObjet(nom)) {
                                PanelImage image;
                                if (ImageObjetEndroit.getImageObjetWidthEtHeight((Type_objet)objet_endroit_sol.getType())) {
                                    image = new PanelImage(imageObjet.getImageObjetWidthEtHeigth(nom, width, height));
                                } else {
                                    image = new PanelImage(imageObjet.getImageObjetWidthOrHeigth(nom, width, height));
                                }
                                Ecran.panel.getPanelCentre().ajouterContent(image, i, j, 0);
                            } else {
                                Ecran.panel.getPanelCentre().ajouterContent(new PanelDeTexteAffichage(e.aObjetEndroit(new Position(i, j)).getNom()), i, j, 1);
                            }
                        }
                    }
                } else {
                    Ecran.panel.getPanelCentre().ajouterContent(null, i, j);
                }
                ImageSol imageSol = new ImageSol();
                if (imageSol.aImageSol(ImageSol.getNomImageSolBySalle(e))) {
                    Ecran.panel.getPanelCentre().ajouterBackground(new PanelImage(
                            imageSol.getImageSolWidthEtHeigth(ImageSol.getNomImageSolBySalle(e), width, height)), i, j);
                } else {
                    JPanel panelFond = new JPanel ();
                    panelFond.setPreferredSize(new Dimension(width, height));
                    panelFond.setBackground(Color.BLACK);
                    Ecran.panel.getPanelCentre().ajouterBackground(panelFond, i, j);
                }
            }
        }
    }

    public void afficherObjetMagasin() {
    }
    
    private class boutonConfirmation implements ActionListener {
        boolean confirmation;
        
        public boutonConfirmation (boolean confirmation) {
            this.confirmation = confirmation;
        }
        @Override
        public void actionPerformed (ActionEvent event) {
            jeu.setConfirmation(this.confirmation);
            reprendre();
        }
    }

}