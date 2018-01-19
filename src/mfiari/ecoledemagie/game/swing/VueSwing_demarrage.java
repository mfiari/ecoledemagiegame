/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.swing;

import mfiari.ecoledemagie.game.demarrage.Demarrage;
import mfiari.ecoledemagie.game.media.image.util.ImageUtil;
import mfiari.ecoledemagie.game.perso.Equipe;
import mfiari.ecoledemagie.game.perso.Personnages;
import mfiari.ecoledemagie.game.texte.TexteVueDemarrage;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.video.Video;
import mfiari.lib.game.clavier.ActionPerso;
import mfiari.lib.game.clavier.CodeBouton;
import mfiari.lib.game.clavier.KeyDispatcher;
import mfiari.lib.game.clavier.ListComponentNumber;
import mfiari.lib.game.clavier.ListKeyAction;
import mfiari.lib.game.swing.BoutonImage;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.PanelDeTexteSuivant;
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.VueSwing;
import mfiari.lib.game.texte.Langue;

/**
 *
 * @author mike
 */
public class VueSwing_demarrage extends VueSwing {
    
    private final Demarrage demarrage;
    private final TexteVueDemarrage textes;
    private final ImageUtil imageUtil;
    
    public VueSwing_demarrage (Demarrage demarage) {
        super(TexteVueDemarrage.getInstance(), demarage);
        System.out.println("VueSwing_demarrage construct");
        this.demarrage = demarage;
        this.imageUtil = new ImageUtil();
        this.textes = TexteVueDemarrage.getInstance();
        Ecran.panel.redimenssionnerCentre(1200, 400);
        Ecran.panel.redimenssionnerNord(1200, 100);
        Ecran.panel.redimenssionnerSud(1200, 100);
        Ecran.panel.afficherNord();
        Ecran.panel.afficherCentre();
        Ecran.panel.afficherSud();
        Ecran.panel.cacherEst();
        Ecran.panel.cacherOuest();
        this.demarrage.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("info".equals(evt.getPropertyName())) {
                    info();
                } else {
                    switch (evt.getPropertyName()) {
                        case "choixLangue":
                            choixLangue();
                            break;
                        case "debutJeu":
                            debutJeu();
                            break;
                        case "choixEquipe":
                            choisirSonEquipe();
                            break;
                        case "confirmerChoix":
                            confirmerChoix();
                            break;
                        case "menuStatDebut":
                            menuStatDebut();
                            break;
                        case "afficheStatDebut":
                            afficheStatDebut();
                            break;
                    }
                    attendre();
                }
            }
        });
    }

    public void choixLangue() {
        System.out.println("choixLangue");
        JPanel panel2 = new JPanel ();
        ListKeyAction actions = new ListKeyAction();
        actions.addKeyAction(CodeBouton.GAUCHE, ActionPerso.DECREMENTE);
        actions.addKeyAction(CodeBouton.DROITE, ActionPerso.INCREMENTE);
        actions.addKeyAction(CodeBouton.ACTION, ActionPerso.ACTION);
        ListComponentNumber componentNumber = new ListComponentNumber();
        for (int i = 0 ; i < Langue.values().length ; i++) {
            BoutonImage boutonDrapeau = new BoutonImage(this.imageUtil.getDrapeau(Langue.values()[i]));
            boutonDrapeau.addActionListener(new boutonChoix((i+1)));
            componentNumber.addKeyAction(boutonDrapeau, (i+1));
            panel2.add(boutonDrapeau);
        }
        Ecran.panel.ajouterCentre(panel2);
        Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(this.demarrage, this, actions, componentNumber)));
    }

    public void debutJeu() {
        /*this.textes = TexteVueDemarrage.getInstance();
        URL url = null;
        try {
            url =new URL("file:///c:/Users/mike/prive/jeu/librairie/video_java/videoV1/src/media/video.mp4");
        } catch (MalformedURLException ex) {
            Logger.getLogger(VueSwing_demarrage.class.getName()).log(Level.SEVERE, null, ex);
        }
        Video video = new Video("video", url);
        JPanel jPanel = new JPanel();
        Component component = video.getVideo();
        component.setSize(new Dimension(1050, 400));
        System.out.println("component : " + component);
        System.out.println("component size : " + component.getSize().toString());
        System.out.println("video : " + video.getVideo());
        jPanel.add(component);
        //jPanel.add(new PanelImage(this.imageUtil.getLogo()));
        jPanel.setSize(new Dimension(1050, 400));
        Ecran.panel.ajouterCentre(video.getVideo());
        video.start();
        this.attendre(200000);*/
        JPanel panelBouton = new JPanel ();
        JButton boutonNouvellePartie = new JButton(this.textes.nouvellePartie);
        boutonNouvellePartie.addActionListener(new boutonChoix(1));
        JButton boutonContinuer=new JButton(this.textes.continuer);
        boutonContinuer.addActionListener(new boutonChoix(2));
        JButton boutonExtra=new JButton(this.textes.extra);
        boutonExtra.addActionListener(new boutonChoix(3));
        panelBouton.add(boutonNouvellePartie);
        panelBouton.add(boutonContinuer);
        panelBouton.add(boutonExtra);
        //Ecran.panel.ajouterCentre(new PanelImage(this.imageUtil.getLogo()));
        Ecran.panel.viderCentre();
        Ecran.panel.ajouterSud(panelBouton);
        ListKeyAction actions = new ListKeyAction();
        actions.addKeyAction(CodeBouton.GAUCHE, ActionPerso.DECREMENTE);
        actions.addKeyAction(CodeBouton.DROITE, ActionPerso.INCREMENTE);
        actions.addKeyAction(CodeBouton.ACTION, ActionPerso.ACTION);
        ListComponentNumber componentNumber = new ListComponentNumber();
        componentNumber.addKeyAction(boutonNouvellePartie, 1);
        componentNumber.addKeyAction(boutonContinuer, 2);
        componentNumber.addKeyAction(boutonExtra, 3);
        Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(this.demarrage, this, actions, componentNumber)));
    }
    
    //cette fonction permet de choisir une equipe
    public void choisirSonEquipe() {
        Box box = Box.createVerticalBox();
        box.add(new PanelDeTexteAffichage("quel equipe choisisez vous?:"));
        JPanel panelEquipe = new JPanel (new GridLayout(4, 2));
        for (int i = 0 ; i < Equipe.values().length ; i++) {
            JButton equipe = new JButton(Equipe.values()[i].name());
            equipe.addActionListener(new boutonChoix((i+1)));
            panelEquipe.add(equipe);
        }
        box.add(panelEquipe);
        JButton boutonInfo = new JButton("Info sur le choix");
        boutonInfo.addActionListener(new boutonChoix((9)));
        box.add(boutonInfo);
        Ecran.panel.ajouterCentre(box);
        Ecran.panel.viderSud();
    }

    //information lors du choix de l'quipe
    public void info() {
        PanelDeTexteSuivant suivant = new PanelDeTexteSuivant(this);
        suivant.ajouterTexte("Vous devez choisir une des 8 équipes proposées pour jouer au jeu.");
        suivant.ajouterTexte("Mais une fois que vous avez choisi votre équipe, vous ne pourrez pas la modifier en cours de jeu.");
        suivant.ajouterTexte("Chaque equipe est composée de 5 eleves dont 1 capitaine. Et chaque équipe possède ses propres stat.");
        suivant.ajouterTexte("Le capitaine de l'équipe que vous choisirez sera votre personnage principale,");
        suivant.ajouterTexte("c'est lui que vous suivrez tout au long du jeu ainsi que les membres de son équipe.");
        suivant.ajouterTexte("Chaque équipe est differente, il faut donc bien choisir son equipe avant de commencer.");
        suivant.ajouterTexte("Pour vous aidez a bien choisir votre equipe, les stats générale de chaque équipe et");
        suivant.ajouterTexte("de leur capitain sont évaluer avec des *. Plus il y a de * et plus la stat sera meilleur.");
        suivant.ajouterTexte("Equipe correspond aux stats générale de l'équipe que vous choisirez.");
        suivant.ajouterTexte("Capitaine correspond aux stats générale que possède le capitaine de l'équipe.");
        suivant.ajouterTexte("Les différentes stats qui sont montrée sont les suivantes:");
        suivant.ajouterTexte("PV: correspond aux point de vie du personnage.");
        suivant.ajouterTexte("force: correspond à la force physique du personnage.");
        suivant.ajouterTexte("def: correspond à la défense physique du personnage.");
        suivant.ajouterTexte("PM: correspond aux point de magie du personnage, cela détermine donc le nombre d'attaque magique que peut faire un personnage.");
        suivant.ajouterTexte("Magie: correspond à la puissance magique du personnage.");
        suivant.ajouterTexte("Res: correspond à la resistance magique du personnage.");
        suivant.ajouterTexte("Vit: correspond à la vitesse du personnage, ça détermine la vitesse de deplacemeent sur le terrain et la vitesse d'enchainement des attaques.");
        suivant.ajouterTexte("Esq: correspond à la capacité d'esquive du personnage.");
        suivant.ajouterTexte("Strat: c'est la strategie du personnage et de l'équipe, plus cela sera élevée et plus le personnage pourra travailler en equipe et faire des combos avec les membres de son equipe.");
        suivant.ajouterTexte("pre: c'est la précision du personnage. Plus une attaque est precise, plus elle inflige de dégat.");
        suivant.ajouterTexte("VAS: correspond à la Vitesse d'Apprentissage des Sorts. Au cour de l'aventure, vous aurez à apprendre des sorts et à les améliorer, et plus vite vous apprenez ces sorts, plus tôt vous aurez des sorts puissant.");
        suivant.ajouterTexte("L'explication est terminer, bonne chance et amusez vous bien.");
        Ecran.panel.ajouterSud(suivant);
        Ecran.panel.viderCentre();
        suivant.lancerAffichage();
    }
    
    public void confirmerChoix () {
        Box box = Box.createVerticalBox();
        box.add(new PanelDeTexteAffichage(this.demarrage.getEquipe().toString()));
        JPanel panelBouton = new JPanel();
        JButton boutonChoix = new JButton("choisir");
        boutonChoix.addActionListener(new boutonChoix((1)));
        JButton boutonStat = new JButton("voir stat");
        boutonStat.addActionListener(new boutonChoix((2)));
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonChoix((3)));
        panelBouton.add(boutonChoix);
        panelBouton.add(boutonStat);
        panelBouton.add(boutonRetour);
        box.add(panelBouton);
        Ecran.panel.ajouterSud(box);
        Ecran.panel.viderCentre();
    }
    
    public void menuStatDebut () {
        Box box = Box.createVerticalBox();
        switch (this.demarrage.getEquipe()) {
            case noir:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.noir +"	capitain: " + Personnages.lex.getNom()));
                break;
            case blanc:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.blanc +"	capitain: " + Personnages.tidus.getNom()));
                break;
            case bleu:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.bleu +"	capitain: " + Personnages.marcus.getNom()));
                break;
            case vert:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.vert +"	capitain: " + Personnages.drake.getNom()));
                break;
            case orange:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.orange +"	capitain: " + Personnages.zack.getNom()));
                break;
            case marron:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.marron +"	capitain: " + Personnages.olivier.getNom()));
                break;
            case jaune:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.jaune +"	capitain: " + Personnages.fred.getNom()));
                break;
            case rouge:
                box.add(new PanelDeTexteAffichage("Equipe: "+ Equipe.rouge +"	capitain: " + Personnages.mark.getNom()));
                break;
        }
        JPanel panelBouton = new JPanel();
        JButton boutonEquipe = new JButton("Equipe");
        boutonEquipe.addActionListener(new boutonChoix((1)));
        JButton boutonCapitain = new JButton("capitain");
        boutonCapitain.addActionListener(new boutonChoix((2)));
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonChoix((3)));
        panelBouton.add(boutonEquipe);
        panelBouton.add(boutonCapitain);
        panelBouton.add(boutonRetour);
        box.add(panelBouton);
        Ecran.panel.ajouterSud(box);
        Ecran.panel.viderCentre();
    }
    
    //cette procedure permet de voir les stats des equipes au debut du jeu
    public void afficheStatDebut() {
        Box box = Box.createVerticalBox();
        switch (this.demarrage.getEquipe()) {
            case noir:
                 switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |*******   |"));
                        box.add(new PanelDeTexteAffichage("Force: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("PM:    |******    |"));
                        box.add(new PanelDeTexteAffichage("Magie: |********  |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Strat: |******    |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |******    |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |******    |"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.lex.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("PM:    |******    |"));
                        box.add(new PanelDeTexteAffichage("Magie: |********* |"));
                        box.add(new PanelDeTexteAffichage("Res:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Strat: |******    |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                 }
                break;
            case blanc:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |*****     |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("PM:    |*******   |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Strat: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.tidus.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |*****     |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("PM:    |*******   |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Strat: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                 }
                break;
            case bleu:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |*****     |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("PM:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Strat: |**********|"));
                        box.add(new PanelDeTexteAffichage("Pre:   |**********|"));
                        box.add(new PanelDeTexteAffichage("VAS:   |******    |"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.marcus.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |*****     |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("PM:    |********* |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Strat: |**********|"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |******    |"));
                        break;
                 }
                break;
            case vert:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |********  |"));
                        box.add(new PanelDeTexteAffichage("Def:   |********  |"));
                        box.add(new PanelDeTexteAffichage("PM:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Strat: |******    |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.drake.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |********* |"));
                        box.add(new PanelDeTexteAffichage("Force: |********  |"));
                        box.add(new PanelDeTexteAffichage("Def:   |********  |"));
                        box.add(new PanelDeTexteAffichage("PM:    |********* |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Strat: |******    |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                 }
                break;
            case orange:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |**********|"));
                        box.add(new PanelDeTexteAffichage("PM:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Res:   |**********|"));
                        box.add(new PanelDeTexteAffichage("Vit:   |****      |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |****      |"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.zack.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |**********|"));
                        box.add(new PanelDeTexteAffichage("PM:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Res:   |**********|"));
                        box.add(new PanelDeTexteAffichage("Vit:   |****      |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |****      |"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                 }
                break;
            case marron:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |******    |"));
                        box.add(new PanelDeTexteAffichage("Def:   |********  |"));
                        box.add(new PanelDeTexteAffichage("PM:    |**********|"));
                        box.add(new PanelDeTexteAffichage("Magie: |**********|"));
                        box.add(new PanelDeTexteAffichage("Res:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |********  |"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.olivier.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |******    |"));
                        box.add(new PanelDeTexteAffichage("Def:   |********  |"));
                        box.add(new PanelDeTexteAffichage("PM:    |**********|"));
                        box.add(new PanelDeTexteAffichage("Magie: |**********|"));
                        box.add(new PanelDeTexteAffichage("Res:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |********  |"));
                        break;
                 }
                break;
            case jaune:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |*******   |"));
                        box.add(new PanelDeTexteAffichage("Force: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("PM:    |*******   |"));
                        box.add(new PanelDeTexteAffichage("Magie: |********  |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |********  |"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*****     |"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.fred.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |*******   |"));
                        box.add(new PanelDeTexteAffichage("Force: |***       |"));
                        box.add(new PanelDeTexteAffichage("Def:   |***       |"));
                        box.add(new PanelDeTexteAffichage("PM:    |********* |"));
                        box.add(new PanelDeTexteAffichage("Magie: |********  |"));
                        box.add(new PanelDeTexteAffichage("Res:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |**********|"));
                        box.add(new PanelDeTexteAffichage("Esq:   |**********|"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |*******   |"));
                        break;
                 }
                break;
            case rouge:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        box.add(new PanelDeTexteAffichage("stat de l'equipe"));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("PM:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |**********|"));
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        box.add(new PanelDeTexteAffichage("stat de "+Personnages.mark.getNom()));
                        box.add(new PanelDeTexteAffichage("PV:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Force: |*****     |"));
                        box.add(new PanelDeTexteAffichage("Def:   |*****     |"));
                        box.add(new PanelDeTexteAffichage("PM:    |********  |"));
                        box.add(new PanelDeTexteAffichage("Magie: |*******   |"));
                        box.add(new PanelDeTexteAffichage("Res:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Vit:   |*******   |"));
                        box.add(new PanelDeTexteAffichage("Esq:   |******    |"));
                        box.add(new PanelDeTexteAffichage("Strat: |********  |"));
                        box.add(new PanelDeTexteAffichage("Pre:   |********  |"));
                        box.add(new PanelDeTexteAffichage("VAS:   |**********|"));
                        break;
                }
                break;
        }
        Ecran.panel.ajouterCentre(box);
        Ecran.panel.ajouterSud(boutonSuivant);
    }
}
