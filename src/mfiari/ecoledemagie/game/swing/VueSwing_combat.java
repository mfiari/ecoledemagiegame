/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.swing;

import mfiari.ecoledemagie.game.Actions;
import mfiari.ecoledemagie.game.combat.AbstractCombat;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.media.image.personnage.ImagePersonnage;
import mfiari.ecoledemagie.game.media.image.sort.ImageSort;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.sort.Sort;
import mfiari.ecoledemagie.game.texte.TexteVueCombat;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.image.Sprite;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.swing.BoutonImage;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.GraphicalImage;
import mfiari.lib.game.swing.GraphicalPolygone;
import mfiari.lib.game.swing.GraphicalString;
import mfiari.lib.game.swing.PanelCentreUniforme;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.PanelPv;
import mfiari.lib.game.swing.ViewComponent;
import mfiari.lib.game.swing.VueSwing;

/**
 *
 * @author mike
 */
public class VueSwing_combat extends VueSwing {
    
    private AbstractCombat combat;
    private TexteVueCombat textes;
    private Map<Perso,Color> perso_color;
    private Map<Perso,Sprite> perso_sprite;
    private int ligne;
    private int colonne;
    private int width;
    private int height;
    
    public VueSwing_combat (AbstractCombat c){
        super(TexteVueCombat.getInstance(), c);
        this.combat = c;
        this.textes = TexteVueCombat.getInstance();
        this.perso_color = null;
        
        this.ligne = 20;
        this.colonne = 20;
        this.width = 1250 / this.ligne;
        this.height = 550 / this.colonne;
        this.combat.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("afficherTerrainCombat")) {
                    afficherTerrainCombat();
                } else if (evt.getPropertyName().equals("attenteAdv")) {
                    attenteAdv();
                } else if (evt.getPropertyName().equals("initImagePerso")) {
                    initImagePerso();
                } else if (evt.getPropertyName().equals("prepareSort")) {
                    prepareSort((Perso)evt.getOldValue());
                } else if (evt.getPropertyName().equals("finSort")) {
                    finSort((Perso)evt.getOldValue());
                } else if (evt.getPropertyName().equals("lanceSort")) {
                    lanceSort((Sort)evt.getOldValue(), (Position)evt.getNewValue());
                } else if (evt.getPropertyName().equals("sortAnnule")) {
                    sortAnnule((Position)evt.getOldValue());
                } else if (evt.getPropertyName().equals("affichageCombat")) {
                    setAffichageCombat();
                } else if (evt.getPropertyName().equals("protection")) {
                    protection((Perso)evt.getOldValue());
                } else if (evt.getPropertyName().equals("finProtection")) {
                    finProtection((Perso)evt.getOldValue());
                } else if (evt.getPropertyName().equals("deplacerPerso")) {
                    deplacerPerso((Perso)evt.getNewValue(), (Position)evt.getOldValue());
                } else if (evt.getPropertyName().equals("attaquePerso")) {
                    attaquePerso((Perso)evt.getOldValue(), (Integer)evt.getNewValue());
                } else if (evt.getPropertyName().equals("koPerso")) {
                    koPerso((Perso)evt.getOldValue());
                } else {
                    if (evt.getPropertyName().equals("afficherMenuJoueur")) {
                        afficherMenuJoueur();
                    } else if (evt.getPropertyName().equals("afficherTechniques")) {
                        afficherTechniques();
                    } else if (evt.getPropertyName().equals("affichage")) {
                        setAffichageCombat();
                    } else if (evt.getPropertyName().equals("afficherEnnemies")) {
                        afficherEnnemies();
                    } else if (evt.getPropertyName().equals("afficherEquipe")) {
                        afficherEquipe();
                    } else if (evt.getPropertyName().equals("afficherObjetCombat")) {
                        afficherObjetCombat();
                    } else if (evt.getPropertyName().equals("afficherMenuFinDeCombat")) {
                        afficherMenuFinDeCombat();
                    }
                    attendre();
                }
            }
        });
    }
    
    private void initImagePerso () {
        this.perso_sprite = new HashMap<>();
        this.perso_color = new HashMap<>();
        ListeDePerso<Personnage> equipe = this.combat.getEquipe();
        ListeDePerso<Ennemie> adversaire = this.combat.getAdversaire();
        ImagePersonnage imagePersonnage = new ImagePersonnage();
        Actions[] actions = Actions.values();
        Orientation[] orientations = Orientation.values();
        for (int i = 0 ; i < equipe.size() ; i++) {
            Personnage perso = equipe.getPerso(i);
            Sprite sprite = new Sprite();
            for (int j = 0 ; j < actions.length ; j++) {
                for (int k = 0 ; k < orientations.length ; k++) {
                    switch (actions[j]) {
                        case aucun:
                            if (imagePersonnage.aImage(perso.getPrenom()+"_"+orientations[k].name(), ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getPrenom()+"_"+orientations[k].name(), ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name(), ImagePersonnage.combat));
                            }
                            break;
                        case attaquer:
                            for (int l = 1 ; l <= perso.getMaxCombos() ; l++) {
                                if (imagePersonnage.aImage(perso.getPrenom()+"_"+orientations[k].name()+"_attaque_"+l, ImagePersonnage.combat)) {
                                    sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getPrenom()+"_"+orientations[k].name()+"_attaque_"+l, ImagePersonnage.combat));
                                } else {
                                    sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_attaque_"+l, ImagePersonnage.combat));
                                }
                            }
                            break;
                        case deplacement:
                            if (imagePersonnage.aImage(perso.getPrenom()+"_"+orientations[k].name()+"_mouv_droit", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getPrenom()+"_"+orientations[k].name()+"_mouv_droit", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_mouv_droit", ImagePersonnage.combat));
                            }
                            if (imagePersonnage.aImage(perso.getPrenom()+"_"+orientations[k].name()+"_mouv_gauche", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getPrenom()+"_"+orientations[k].name()+"_mouv_gauche", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_mouv_gauche", ImagePersonnage.combat));
                            }
                            break;
                        case defendre:
                            if (imagePersonnage.aImage(perso.getPrenom()+"_"+orientations[k].name()+"_defense", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getPrenom()+"_"+orientations[k].name()+"_defense", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_defense", ImagePersonnage.combat));
                            }
                            break;
                        case sort:
                            if (imagePersonnage.aImage(perso.getPrenom()+"_"+orientations[k].name()+"_sort", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getPrenom()+"_"+orientations[k].name()+"_sort", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_sort", ImagePersonnage.combat));
                            }
                            break;
                        case ko:
                            if (imagePersonnage.aImage(perso.getPrenom()+"_"+orientations[k].name()+"_ko", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getPrenom()+"_"+orientations[k].name()+"_ko", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_ko", ImagePersonnage.combat));
                            }
                            break;
                    }
                }
            }
            this.perso_sprite.put(perso, sprite);
            this.perso_color.put(perso, Color.BLACK);
        }
        for (int i = 0 ; i < adversaire.size() ; i++) {
            Ennemie perso = adversaire.getPerso(i);
            Sprite sprite = new Sprite();
            for (int j = 0 ; j < actions.length ; j++) {
                for (int k = 0 ; k < orientations.length ; k++) {
                    switch (actions[j]) {
                        case aucun:
                            if (imagePersonnage.aImage(perso.getNom()+"_"+orientations[k].name(), ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getNom()+"_"+orientations[k].name(), ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name(), ImagePersonnage.combat));
                            }
                            break;
                        case attaquer:
                            for (int l = 1 ; l <= perso.getMaxCombos() ; l++) {
                                if (imagePersonnage.aImage(perso.getNom()+"_"+orientations[k].name()+"_attaque_"+l, ImagePersonnage.combat)) {
                                    sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getNom()+"_"+orientations[k].name()+"_attaque_"+l, ImagePersonnage.combat));
                                } else {
                                    sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_attaque_"+l, ImagePersonnage.combat));
                                }
                            }
                            break;
                        case deplacement:
                            if (imagePersonnage.aImage(perso.getNom()+"_"+orientations[k].name()+"_mouv_droit", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getNom()+"_"+orientations[k].name()+"_mouv_droit", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_mouv_droit", ImagePersonnage.combat));
                            }
                            if (imagePersonnage.aImage(perso.getNom()+"_"+orientations[k].name()+"_mouv_gauche", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getNom()+"_"+orientations[k].name()+"_mouv_gauche", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_mouv_gauche", ImagePersonnage.combat));
                            }
                            break;
                        case defendre:
                            if (imagePersonnage.aImage(perso.getNom()+"_"+orientations[k].name()+"_defense", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getNom()+"_"+orientations[k].name()+"_defense", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_defense", ImagePersonnage.combat));
                            }
                            break;
                        case sort:
                            if (imagePersonnage.aImage(perso.getNom()+"_"+orientations[k].name()+"_sort", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getNom()+"_"+orientations[k].name()+"_sort", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_"+orientations[k].name()+"_sort", ImagePersonnage.combat));
                            }
                            break;
                        case ko:
                            if (imagePersonnage.aImage(perso.getNom()+"_"+orientations[k].name()+"_ko", ImagePersonnage.combat)) {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage(perso.getNom()+"_"+orientations[k].name()+"_ko", ImagePersonnage.combat));
                            } else {
                                sprite.ajouterImage(actions[j], orientations[k], imagePersonnage.getImage("perso_ko", ImagePersonnage.combat));
                            }
                            break;
                    }
                }
            }
            this.perso_sprite.put(perso, sprite);
            this.perso_color.put(perso, Color.BLACK);
        }
    }
    
    public void afficherTerrainCombat() {
        ListeDePerso<Personnage> equipe = this.combat.getEquipe();
        ListeDePerso<Ennemie> adversaire = this.combat.getAdversaire();
        ImagePersonnage imagePersonnage = new ImagePersonnage();
        Ecran.panel.changerCentre(new PanelCentreUniforme(ligne, colonne, width, height), ligne, colonne, 1250, 550);
        for (int i = 0 ; i < ligne ; i++) {
            for (int j = 0 ; j < colonne ; j++) {
                for (int k = 0 ; k < adversaire.size() ; k++) {
                    if (adversaire.getPerso(k).getPosition().equals(i, j, 0)) {
                        if (this.perso_sprite.get(adversaire.getPerso(k)).getImage(this.combat.getActionByPerso(adversaire.getPerso(k)), adversaire.getPerso(k).getPosition().getOrientation()) != null) {
                            Ecran.panel.getPanelCentre().ajouterViewContent(new ViewComponent(new GraphicalImage(
                                this.perso_sprite.get(adversaire.getPerso(k)).getImage(this.combat.getActionByPerso(adversaire.getPerso(k)), adversaire.getPerso(k).getPosition().getOrientation()), 
                                    (j+1)*width, (i+1)*height, width, height)), i, j, 1);
                        } else {
                            Ecran.panel.getPanelCentre().ajouterViewContent(new ViewComponent(new GraphicalString(
                                adversaire.getPerso(k).getNom(), (j+1)*width, (i+1)*height, this.perso_color.get(adversaire.getPerso(k)))), i, j, 1);
                        }
                        ViewComponent viewComponent = new ViewComponent();
                        viewComponent.setGraphicalObject(new PanelPv(adversaire.getPerso(k).getPv(), adversaire.getPerso(k).getPvInitial(), "PV", (j+1)*width, 0));
                        Ecran.panel.getPanelCentre().ajouterViewContent(viewComponent, i, j, 0);
                    }
                }
                for (int k = 0 ; k < equipe.size() ; k++) {
                    if (equipe.getPerso(k).getPosition().equals(i, j, 0)) {
                        if (this.perso_sprite.get(equipe.getPerso(k)).getImage(this.combat.getActionByPerso(equipe.getPerso(k)), equipe.getPerso(k).getPosition().getOrientation()) != null) {
                            Ecran.panel.getPanelCentre().ajouterViewContent(new ViewComponent(new GraphicalImage(
                                this.perso_sprite.get(equipe.getPerso(k)).getImage(this.combat.getActionByPerso(equipe.getPerso(k)), equipe.getPerso(k).getPosition().getOrientation()), 
                                    (j+1)*width, (i+1)*height, width, height)), i, j, 1);
                        } else {
                            Ecran.panel.getPanelCentre().ajouterViewContent(new ViewComponent(new GraphicalString(
                                    equipe.getPerso(k).getNom(), (j+1)*width, (i+1)*height, this.perso_color.get(equipe.getPerso(k)))), i, j, 1);
                        }
                        
                        ViewComponent viewComponent = new ViewComponent();
                        viewComponent.setGraphicalObject(new PanelPv(equipe.getPerso(k).getPv(), equipe.getPerso(k).getPvInitial(), "PV", (j+1)*width, (i+2)*height));
                        Ecran.panel.getPanelCentre().ajouterViewContent(viewComponent, i, j, 0);
                    }
                }
                /*if (this.combat.getSort(i, j, 0) != null) {
                    Ecran.panel.getPanelCentre().ajouterViewContent(new ViewComponent(
                                new GraphicalPolygone(GraphicalPolygone.RECTANGLE, (j+1)*width, (i+1)*height, width, height)), i, j, 1);
                }*/
            }
        }
        Ecran.panel.cacherNord();
        Ecran.panel.getPanelCentre().repaint();
        Ecran.panel.getPanelCentre().validate();
        Ecran.panel.actualise();
        Ecran.fenetreDuJeu.repaint();
        Ecran.fenetreDuJeu.validate();
    }
    
    private void actualisePerso (Perso p) {
        if (this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()) != null) {
            ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPosition().getPositionX(), p.getPosition().getPositionY());
            System.out.println("actualisePerso : px = " + p.getPosition().getPositionX() + " ; py = " + p.getPosition().getPositionY() + 
                    " ; component = " + component);
            if (component == null) {
                component = new ViewComponent();
                Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            }
            component.setGraphicalObject(new GraphicalImage(
                    this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()), (p.getPosition().getPositionY()+1)*width, 
                    ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        }
    }

    public void prepareSort(Perso p) {
        if (this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()) != null) {
            ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPosition().getPositionX(), p.getPosition().getPositionY());
            if (component == null) {
                component = new ViewComponent();
                Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            }
            component.setGraphicalObject(new GraphicalImage(
                    this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()), (p.getPosition().getPositionY()+1)*width, 
                    ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        } else {
            this.perso_color.put(p, Color.RED);
        }
    }

    public void finSort(Perso p) {
        if (this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()) != null) {
            ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPosition().getPositionX(), p.getPosition().getPositionY());
            if (component == null) {
                component = new ViewComponent();
                Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            }
            component.setGraphicalObject(new GraphicalImage(
                    this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()), (p.getPosition().getPositionY()+1)*width, 
                    ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        } else {
            this.perso_color.put(p, Color.BLACK);
        }
    }

    public void lanceSort(Sort sort, Position p) {
        ImageSort imageSort = new ImageSort();
        System.out.println("lanceSort : " + sort.getNom());
        ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(2, p.getPositionX(), p.getPositionY());
        if (component == null) {
            component = new ViewComponent();
            Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPositionX(), p.getPositionY(), 2);
        }
        if (imageSort.aImage(sort.getNom())) {
            component.setGraphicalObject(new GraphicalImage(imageSort.getImage(sort.getNom()), (p.getPositionY()+1)*width, 
                    ((p.getPositionX()+1)*height) , width, height));
        } else {
            component.setGraphicalObject(new GraphicalImage(imageSort.getImage("sort"), (p.getPositionY()+1)*width, 
                    ((p.getPositionX()+1)*height) , width, height));
        }
        Ecran.panel.getPanelCentre().refreshComponent(component);
        Ecran.panel.getPanelCentre().repaint();
        Ecran.panel.getPanelCentre().validate();
        Ecran.panel.actualise();
        Ecran.fenetreDuJeu.repaint();
        Ecran.fenetreDuJeu.validate();
    }

    public void sortAnnule(Position p) {
        /*ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(2, p.getPositionX(), p.getPositionY());
        if (component == null) {
            component = new ViewComponent();
            Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPositionX(), p.getPositionY(), 2);
        }
        component.setGraphicalObject(new GraphicalImage(null, (p.getPositionY()+1)*width, 
                    ((p.getPositionX()+1)*height) , width, height));
        Ecran.panel.getPanelCentre().refreshComponent(component);*/
        Ecran.panel.getPanelCentre().ajouterViewContent(null, p.getPositionX(), p.getPositionY(), 2);
        Ecran.panel.getPanelCentre().repaint();
        Ecran.panel.getPanelCentre().validate();
        Ecran.panel.actualise();
        Ecran.fenetreDuJeu.repaint();
        Ecran.fenetreDuJeu.validate();
        ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPositionX(), p.getPositionY());
        System.out.println("sortAnnule :component = " + component);
        if (component != null) {
            System.out.println("sortAnnule :component GraphicalObject = " + component.getGraphicalObject());
            Ecran.panel.getPanelCentre().ajouterViewContent(null, p.getPositionX(), p.getPositionY(), 2);
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        }
    }

    public void protection(Perso p) {
        if (this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()) != null) {
            ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPosition().getPositionX(), p.getPosition().getPositionY());
            System.out.println("protection : px = " + p.getPosition().getPositionX() + " ; py = " + p.getPosition().getPositionY() + 
                    " ; component = " + component);
            if (component == null) {
                component = new ViewComponent();
                Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            }
            component.setGraphicalObject(new GraphicalImage(
                    this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()), (p.getPosition().getPositionY()+1)*width, 
                    ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        } else {
            this.perso_color.put(p, Color.GREEN);
        }
    }

    public void finProtection(Perso p) {
        if (this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()) != null) {
            ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPosition().getPositionX(), p.getPosition().getPositionY());
            if (component == null) {
                component = new ViewComponent();
                Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            }
            component.setGraphicalObject(new GraphicalImage(
                    this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()), (p.getPosition().getPositionY()+1)*width, 
                    ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        } else {
            this.perso_color.put(p, Color.BLACK);
        }
    }

    public void koPerso(Perso p) {
        if (this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()) != null) {
            ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPosition().getPositionX(), p.getPosition().getPositionY());
            System.out.println("koPerso : px = " + p.getPosition().getPositionX() + " ; py = " + p.getPosition().getPositionY() + 
                    " ; component = " + component);
            if (component == null) {
                component = new ViewComponent();
                Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            }
            component.setGraphicalObject(new GraphicalImage(
                    this.perso_sprite.get(p).getImage(this.combat.getActionByPerso(p), p.getPosition().getOrientation()), (p.getPosition().getPositionY()+1)*width, 
                    ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        } else {
            this.perso_color.put(p, Color.GREEN);
        }
    }

    public void attaquePerso(Perso p, int combos) {
        ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, p.getPosition().getPositionX(), p.getPosition().getPositionY());
        if (component != null) {
            List<BufferedImage> images = this.perso_sprite.get(p).getImages(this.combat.getActionByPerso(p), p.getPosition().getOrientation());
            component.setGraphicalObject(new GraphicalImage(images.get(combos), (p.getPosition().getPositionY()+1)*width, 
                    ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
            this.attendre(300);
            component.setGraphicalObject(new GraphicalImage(this.perso_sprite.get(p).getImage(Actions.aucun, p.getPosition().getOrientation()), 
                    (p.getPosition().getPositionY()+1)*width, ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        }
    }

    public void deplacerPerso(Perso p, Position positionPrec) {
        ViewComponent component = Ecran.panel.getPanelCentre().getViewComponent(1, positionPrec.getPositionX(), positionPrec.getPositionY());
        System.out.println("deplacerPerso : x = " + positionPrec.getPositionX() + " ; y = " + positionPrec.getPositionY() + " ; px = " + 
            p.getPosition().getPositionX() + " ; py = " + p.getPosition().getPositionY() + " ; component = " + component + " equalsXY = " +
            positionPrec.equalsXY(p.getPosition()));
        if (component != null) {
            if (!positionPrec.equalsXY(p.getPosition())) {
                List<BufferedImage> images = this.perso_sprite.get(p).getImages(this.combat.getActionByPerso(p), p.getPosition().getOrientation());
                for (int i = 0 ; i < images.size() ; i++) {
                    ViewComponent nullComponent = new ViewComponent();
                    nullComponent.setGraphicalObject(component.getGraphicalObject());
                    ((GraphicalImage)nullComponent.getGraphicalObject()).setBufferedImage(null);
                    if (p.getPosition().getOrientation().equals(Orientation.dos)) {
                        component.setGraphicalObject(new GraphicalImage(images.get(i), (positionPrec.getPositionY()+1)*width, 
                            ((positionPrec.getPositionX()+1)*height) - ((((positionPrec.getPositionX()+1)*height) - 
                                ((p.getPosition().getPositionX()+1)*height)) / (images.size() - i)) , width, height));
                    } else if (p.getPosition().getOrientation().equals(Orientation.face)) {
                        component.setGraphicalObject(new GraphicalImage(images.get(i), (positionPrec.getPositionY()+1)*width, 
                            ((positionPrec.getPositionX()+1)*height) + ((((p.getPosition().getPositionX()+1)*height) - 
                                ((positionPrec.getPositionX()+1)*height)) / (images.size() - i)) , width, height));
                    } else if (p.getPosition().getOrientation().equals(Orientation.gauche)) {
                        component.setGraphicalObject(new GraphicalImage(images.get(i), ((positionPrec.getPositionY()+1)*width) - 
                                ((((positionPrec.getPositionY()+1)*width) - ((p.getPosition().getPositionY()+1)*width)) / (images.size() - i)), 
                            (positionPrec.getPositionX()+1)*height , width, height));
                    } else if (p.getPosition().getOrientation().equals(Orientation.droite)) {
                        component.setGraphicalObject(new GraphicalImage(images.get(i), ((positionPrec.getPositionY()+1)*width) + 
                                ((((p.getPosition().getPositionY()+1)*width) - ((positionPrec.getPositionY()+1)*width)) / (images.size() - i)), 
                            (positionPrec.getPositionX()+1)*height , width, height));
                    }
                    Ecran.panel.getPanelCentre().refreshComponent(nullComponent);
                    Ecran.panel.getPanelCentre().refreshComponent(component);
                    Ecran.panel.getPanelCentre().repaint();
                    Ecran.panel.getPanelCentre().validate();
                    Ecran.panel.actualise();
                    Ecran.fenetreDuJeu.repaint();
                    Ecran.fenetreDuJeu.validate();
                    this.attendre((int) (3000/p.getVitesse()));
                }
            }
            ViewComponent nullComponent = new ViewComponent();
            nullComponent.setGraphicalObject(component.getGraphicalObject());
            ((GraphicalImage)nullComponent.getGraphicalObject()).setBufferedImage(null);
            component.setGraphicalObject(new GraphicalImage(this.perso_sprite.get(p).getImage(Actions.aucun, p.getPosition().getOrientation()), 
                    (p.getPosition().getPositionY()+1)*width, ((p.getPosition().getPositionX()+1)*height) , width, height));
            Ecran.panel.getPanelCentre().ajouterViewContent(component, p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            Ecran.panel.getPanelCentre().refreshComponent(nullComponent);
            Ecran.panel.getPanelCentre().refreshComponent(component);
            Ecran.panel.getPanelCentre().repaint();
            Ecran.panel.getPanelCentre().validate();
            Ecran.panel.actualise();
            Ecran.fenetreDuJeu.repaint();
            Ecran.fenetreDuJeu.validate();
        }
    }
    
    
    
    private JPanel getPanelPerso (Perso perso, int width, int height) {
        JPanel panel = new JPanel();
        Box box = Box.createVerticalBox();
        ImagePersonnage imagePersonnage = new ImagePersonnage();
        if (imagePersonnage.aImagePerso(perso.getNom())) {
            box.add(new PanelImage(imagePersonnage.getImagePersoHeigth(perso.getNom(), height)));
        } else {
            box.add(new PanelDeTexteAffichage(perso.getNom()));
        }
        box.add(new PanelDeTexteAffichage(Math.round(perso.getPv()) + "/" + Math.round(perso.getPvInitial())));
        panel.add(box);
        return panel;
    }

    public void setAffichageCombat() {
        JPanel panel2 = new JPanel ();
        PanelDeTexteAffichage panelDeTexteAffichage = new PanelDeTexteAffichage(this.combat.getAffichage());
        panel2.add(panelDeTexteAffichage);
        Ecran.panel.ajouterSud(panel2);
    }
    
    public void afficherMenuJoueur() {
        Personnage perso = this.combat.getPerso();
        Box box = Box.createVerticalBox();
        StringBuilder texte = new StringBuilder();
        texte.append(this.textes.pv);
        texte.append(":");
        texte.append(Math.round(perso.getPv()));
        texte.append("/");
        texte.append(Math.round(perso.getPvInitial()));
        texte.append("\t");
        texte.append(this.textes.pm);
        texte.append(":");
        texte.append(perso.getPm());
        texte.append("/");
        texte.append(perso.getPmInitial());
        box.add(new PanelDeTexteAffichage(texte.toString()));
        JPanel panelBouton = new JPanel (new GridLayout(2, 3));
        JButton boutonAttaquer = new JButton(this.textes.attaquer);
        boutonAttaquer.addActionListener(new boutonChoix(1));
        JButton boutonSort = new JButton("Sort");
        boutonSort.addActionListener(new boutonChoix(2));
        JButton boutonExaminer = new JButton(this.textes.examiner);
        boutonExaminer.addActionListener(new boutonChoix(3));
        JButton boutonObjet = new JButton(this.textes.objet);
        boutonObjet.addActionListener(new boutonChoix(4));
        JButton boutonProteger = new JButton(this.textes.seProteger);
        boutonProteger.addActionListener(new boutonChoix(5));
        JButton boutonPerso = new JButton(this.textes.perso);
        boutonPerso.addActionListener(new boutonChoix(3));
        panelBouton.add(boutonAttaquer);
        panelBouton.add(boutonSort);
        panelBouton.add(boutonExaminer);
        panelBouton.add(boutonObjet);
        panelBouton.add(boutonProteger);
        panelBouton.add(boutonPerso);
        Ecran.panel.ajouterSud(panelBouton);
    }
    
    public void afficherEnnemies() {
        ListeDePerso adversaire = this.combat.getAdversaire();
        Box box = Box.createVerticalBox();
        box.add(new PanelDeTexteAffichage(this.textes.quiAttaquer));
        JPanel panel2 = new JPanel ();
        for (int i = 0; i < adversaire.size(); i++) {
            BoutonImage bouton = new BoutonImage(adversaire.getPerso(i).getNom());
            bouton.addActionListener(new boutonChoixPerso(i+1));
            panel2.add(bouton);
        }
        JButton retour = new JButton(this.textes.retour);
        retour.addActionListener(new boutonChoixPerso(6));
        panel2.add(retour);
        box.add(panel2);
        Ecran.panel.ajouterSud(box);
    }

    public void afficherEquipe() {
        ListeDePerso equipe = this.combat.getEquipe();
        String affichage = this.combat.getAffichage();
        Box box = Box.createVerticalBox();
        box.add(new PanelDeTexteAffichage(affichage));
        JPanel panel2 = new JPanel ();
        for (int i = 0; i < equipe.size(); i++) {
            BoutonImage bouton = new BoutonImage(equipe.getPerso(i).getNom());
            bouton.addActionListener(new boutonChoixPerso(i+1));
            panel2.add(bouton);
        }
        JButton retour = new JButton(this.textes.retour);
        retour.addActionListener(new boutonChoixPerso(0));
        panel2.add(retour);
        box.add(panel2);
        Ecran.panel.ajouterSud(box);
    }

    public void afficherTechniques() {
        ListeDeSort techniques = this.combat.getPerso().getTechniques();
        Box box = Box.createVerticalBox();
        box.add(new PanelDeTexteAffichage(this.textes.quelTecUtilise));
        JPanel panel2 = new JPanel ();
        for (int i = 0; i < techniques.size(); i++) {
            BoutonImage bouton = new BoutonImage(techniques.getSort(i).getNom());
            bouton.addActionListener(new boutonChoixPerso(i+1));
            panel2.add(bouton);
        }
        JButton retour = new JButton(this.textes.retour);
        retour.addActionListener(new boutonChoixPerso(0));
        panel2.add(retour);
        box.add(panel2);
        Ecran.panel.ajouterSud(box);
    }

    public void afficherObjetCombat() {
        JButton quitter = new JButton(this.textes.quitter);
        quitter.addActionListener(new boutonChoixPerso(0));
        Ecran.panel.ajouterSud(quitter);
    }
    
    public void afficherMenuFinDeCombat() {
        ListeDePerso<Personnage> equipe = this.combat.getEquipe();
        JPanel panel2 = new JPanel (new GridLayout(equipe.size(), 1));
        for (int i = 0; i < equipe.size(); i++) {
            Personnage perso = equipe.getPerso(i);
            JPanel panel = new JPanel (new GridLayout(3, 1));
            panel.add(new PanelDeTexteAffichage(perso.getNom() + "\t"+this.textes.niv+":" + perso.getNiveau()));
            panel.add(new PanelDeTexteAffichage(this.textes.pv+": " + perso.getPv() + "/" + perso.getPvInitial()));
            panel.add(new PanelDeTexteAffichage(this.textes.pm+": " + perso.getPm() + "/" + perso.getPmInitial()));
            panel2.add(panel);
        }
        Ecran.panel.ajouterCentre(panel2);
        JButton suivant = new JButton(this.textes.suivant);
        suivant.addActionListener(new boutonSuivant());
        Ecran.panel.ajouterSud(suivant);
    }

    private void attenteAdv() {
        Ecran.panel.ajouterSud(new PanelDeTexteAffichage(this.textes.attenteAdv));
    }
    
    protected class boutonChoixPerso implements ActionListener {
        private int choix;
        
        public boutonChoixPerso (int choix) {
            this.choix = choix;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            combat.setChoixPerso(this.choix);
            reprendre();
        }
    }
    
}
