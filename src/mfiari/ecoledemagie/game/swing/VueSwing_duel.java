/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.swing;

import mfiari.ecoledemagie.game.extra.Duel;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.media.image.personnage.ImagePersonnage;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.texte.TexteVueDuel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.swing.BoutonImage;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.PanelDeTexteSuivant;
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.VueSwing;

/**
 *
 * @author etudiant
 */
public class VueSwing_duel extends VueSwing {
    
    private Duel duel;
    private TexteVueDuel textes;
    
    public VueSwing_duel (Duel duel) {
        super(TexteVueDuel.getInstance(), duel);
        this.duel = duel;
        this.textes = TexteVueDuel.getInstance();
        this.duel.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "afficherPersoChoisit":
                        afficherPersoChoisit();
                        break;
                    default :
                        switch (evt.getPropertyName()) {
                            case "affichage":
                                setAffichage();
                                break;
                            case "menuPrincipal":
                                menuPrincipal();
                                break;
                            case "combatSolo":
                                combatSolo();
                                break;
                            case "unContreUn":
                                unContreUn();
                                break;
                            case "choixPersoJ1":
                                choixPersoJ1();
                                break;
                            case "choixPersoJ2":
                                choixPersoJ1();
                                break;
                            case "J1VSJ2":
                                J1VSJ2();
                                break;
                            case "OrdiVsOrdi":
                                OrdiVsOrdi();
                                break;
                            case "tournoi":
                                tournoi();
                                break;
                            case "mission":
                                mission();
                                break;
                            case "confirmation":
                                confirmation();
                                break;
                        }
                        attendre();
                        break;
                }
            }
        });
    }
    
    private void setAffichage() {
        JPanel panel2 = new JPanel ();
        PanelDeTexteSuivant panelDeTexteSuivant = new PanelDeTexteSuivant(this);
        panelDeTexteSuivant.ajouterTexte(this.duel.getAffichage());
        panel2.add(panelDeTexteSuivant);
        panelDeTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panel2);
    }
    
    private void menuPrincipal() {
        JPanel panelBouton = new JPanel ();
        JButton boutonCombatSolo = new JButton("combat solo");
        boutonCombatSolo.addActionListener(new boutonChoix(1));
        JButton boutonTournoi = new JButton("tournoi");
        boutonTournoi.addActionListener(new boutonChoix(2));
        JButton boutonMission = new JButton("mission");
        boutonMission.addActionListener(new boutonChoix(3));
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonRetour());
        panelBouton.add(boutonCombatSolo);
        panelBouton.add(boutonTournoi);
        panelBouton.add(boutonMission);
        panelBouton.add(boutonRetour);
        Ecran.panel.viderCentre();
        Ecran.panel.ajouterSud(panelBouton);
    }
    
    private void combatSolo() {
        JPanel panelBouton = new JPanel ();
        JButton bouton1VS1 = new JButton("1 VS 1");
        bouton1VS1.addActionListener(new boutonChoix(1));
        JButton bouton5VS5 = new JButton("5 VS 5");
        bouton5VS5.addActionListener(new boutonChoix(2));
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonRetour());
        panelBouton.add(bouton1VS1);
        panelBouton.add(bouton5VS5);
        panelBouton.add(boutonRetour);
        Ecran.panel.ajouterSud(panelBouton);
    }
    
    private void unContreUn() {
        JPanel panelBouton = new JPanel ();
        JButton boutonJ1VSOrdi = new JButton("J1 VS ORDI");
        boutonJ1VSOrdi.addActionListener(new boutonChoix(1));
        JButton boutonJ1VSJ2 = new JButton("J1 VS J2");
        boutonJ1VSJ2.addActionListener(new boutonChoix(2));
        JButton boutonOrdiVSOrdi = new JButton("ORDI VS ORDI");
        boutonOrdiVSOrdi.addActionListener(new boutonChoix(3));
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonRetour());
        panelBouton.add(boutonJ1VSOrdi);
        panelBouton.add(boutonJ1VSJ2);
        panelBouton.add(boutonOrdiVSOrdi);
        panelBouton.add(boutonRetour);
        Ecran.panel.ajouterSud(panelBouton);
    }
    
    private void afficherPersoChoisit() {
        JPanel panelHaut = new JPanel (new GridLayout(1, 2));
        String nom;
        ImagePersonnage imagePersonnage = new ImagePersonnage();
        Box boxPerso = Box.createVerticalBox();
        boxPerso.add(new PanelDeTexteAffichage("equipe"));
        JPanel panelPerso = new JPanel();
        ListeDePerso<Personnage> equipeJ1 = this.duel.getEquipeJ1();
        for (int i =0 ; i < equipeJ1.size() ; i++) {
            nom = equipeJ1.getPerso(i).getNom();
            PanelImage image;
            if (imagePersonnage.aImagePerso(nom)) {
                image = new PanelImage(imagePersonnage.getImagePersoHeigth(nom, 60));
            } else {
                image = new PanelImage();
                image.add(new PanelDeTexteAffichage(nom));
            }
            panelPerso.add(image);
        }
        boxPerso.add(panelPerso);
        panelHaut.add(boxPerso);
        Box boxAdv = Box.createVerticalBox();
        boxAdv.add(new PanelDeTexteAffichage("adv"));
        JPanel panelAdv = new JPanel();
        ListeDePerso<Ennemie> equipeJ2 = this.duel.getEquipeJ2();
        for (int i =0 ; i < equipeJ2.size() ; i++) {
            nom = equipeJ2.getPerso(i).getNom();
            PanelImage image;
            if (imagePersonnage.aImagePerso(nom)) {
                image = new PanelImage(imagePersonnage.getImagePersoHeigth(nom, 60));
            } else {
                image = new PanelImage();
                image.add(new PanelDeTexteAffichage(nom));
            }
            panelAdv.add(image);
        }
        boxAdv.add(panelAdv);
        panelHaut.add(boxAdv);
        Ecran.panel.ajouterNord(panelHaut);
    }
    
    private void choixPersoJ1() {
        String nom;
        ImagePersonnage imagePersonnage = new ImagePersonnage();
        JPanel panel2 = new JPanel (new GridLayout(5, 6));
        ListeDePerso<Personnage> personnages = this.duel.getPersonnages();
        for (int i = 0 ; i < personnages.size() ; i++) {
            nom = personnages.getPerso(i).getNom();
            BoutonImage bouton;
            if (imagePersonnage.aImagePerso(nom)) {
                bouton = new BoutonImage(imagePersonnage.getImagePersoHeigth(nom, 80));
            } else {
                bouton = new BoutonImage(nom);
            }
            bouton.addActionListener(new boutonChoix(i+1));
            panel2.add(bouton);
        }
        JButton terminer = new JButton("aleatoire");
        terminer.addActionListener(new boutonChoix(100));
        JButton retour = new JButton("retour");
        retour.addActionListener(new boutonRetour());
        panel2.add(terminer);
        panel2.add(retour);
        Ecran.panel.ajouterCentre(panel2);
        Ecran.panel.viderSud();
    }
    
    private void J1VSJ2() {
        JPanel panel2 = new JPanel ();
        PanelDeTexteSuivant panelDeTexteSuivant = new PanelDeTexteSuivant(this);
        panelDeTexteSuivant.ajouterTexte("Le mode combat en ligne n'est pas disponible pour le moment");
        panel2.add(panelDeTexteSuivant);
        panelDeTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panel2);
    }
    
    private void OrdiVsOrdi() {
        JPanel panel2 = new JPanel ();
        PanelDeTexteSuivant panelDeTexteSuivant = new PanelDeTexteSuivant(this);
        panelDeTexteSuivant.ajouterTexte("Le mode combat en ligne n'est pas disponible pour le moment");
        panel2.add(panelDeTexteSuivant);
        panelDeTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panel2);
    }
    
    private void tournoi() {
        JPanel panel2 = new JPanel ();
        PanelDeTexteSuivant panelDeTexteSuivant = new PanelDeTexteSuivant(this);
        panelDeTexteSuivant.ajouterTexte("Le mode combat en ligne n'est pas disponible pour le moment");
        panel2.add(panelDeTexteSuivant);
        panelDeTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panel2);
    }
    
    private void mission() {
        JPanel panel2 = new JPanel ();
        PanelDeTexteSuivant panelDeTexteSuivant = new PanelDeTexteSuivant(this);
        panelDeTexteSuivant.ajouterTexte("Le mode combat en ligne n'est pas disponible pour le moment");
        panel2.add(panelDeTexteSuivant);
        panelDeTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panel2);
    }

    private void confirmation () {
        JPanel panelBouton = new JPanel ();
        JButton boutonOui = new JButton("Oui");
        boutonOui.addActionListener(new boutonConfirmer(true));
        JButton boutonNon = new JButton("Non");
        boutonNon.addActionListener(new boutonConfirmer(false));
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonConfirmer(false));
        panelBouton.add(new PanelDeTexteAffichage(this.duel.getAffichage()));
        panelBouton.add(boutonOui);
        panelBouton.add(boutonNon);
        panelBouton.add(boutonRetour);
        Ecran.panel.ajouterSud(panelBouton);
    }
    
    protected class boutonConfirmer implements ActionListener {
        private boolean confirmation;
        
        public boutonConfirmer (boolean confirmation) {
            this.confirmation = confirmation;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            duel.confirmer(this.confirmation);
            reprendre();
        }
    }
    
    private class boutonRetour implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            duel.setChoix(0);
            reprendre();
        }
    }
}
