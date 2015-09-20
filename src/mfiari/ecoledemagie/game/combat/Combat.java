/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.combat;

import mfiari.ecoledemagie.game.Actions;
import mfiari.ecoledemagie.game.extra.Duel;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.sort.Categories;
import mfiari.ecoledemagie.game.sort.Sort;
import mfiari.ecoledemagie.game.sort.Types;
import mfiari.ecoledemagie.game.strategie.Strategie;
import mfiari.ecoledemagie.game.strategie.StrategieAction;
import mfiari.ecoledemagie.game.strategie.StrategieDeplacement;
import mfiari.ecoledemagie.game.strategie.StrategieObjet;
import mfiari.ecoledemagie.game.strategie.StrategieSort;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.lib.game.clavier.ActionClass;
import mfiari.lib.game.clavier.ActionPerso;
import mfiari.lib.game.clavier.CodeBouton;
import mfiari.lib.game.clavier.KeyDispatcher;
import mfiari.lib.game.clavier.ListKeyAction;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.objet.ObjetEndroitClassique;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.swing.Ecran;


/**
 *
 * @author mike
 */
public class Combat extends AbstractCombat {

    public Combat() {
        super();
    }

    public Combat(ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire, Evenement jeu) {
        super(equipe, adversaire, jeu);
    }
    
    public Combat(ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire, Duel duel) {
        super(equipe, adversaire, duel);
    }
    
    class ActionAllieThread extends Thread {
        
        Perso perso;
        
        public ActionAllieThread (Perso perso) {
            this.perso = perso;
        }
        
        @Override
        public void run() {
            do {
                Combat.this.choixAction(this.perso, equipe, adversaire);
                Combat.this.AttaqueAllie(this.perso, equipe, adversaire);
                //Combat.this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                System.out.println("ActionAllieThread => " + this.perso.getNom() + " : PV = " + this.perso.getPv());
                try {
                    Thread.sleep((int) (3000/this.perso.getVitesse()));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ActionAllieThread => " + this.perso.getNom() + " InterruptedException");
                }
            } while (!this.perso.estKO());
            System.out.println("ActionAllieThread => " + this.perso.getNom() + " est ko");
            Combat.this.actions_perso.put(this.perso, Actions.ko);
            Combat.this.pcsControlleurVue.firePropertyChange("koPerso", this.perso, null);
        }
    }
    
    class ActionAdvThread extends Thread {
        
        Ennemie ennemie;
        
        public ActionAdvThread (Ennemie ennemie) {
            this.ennemie = ennemie;
        }
        
        @Override
        public void run() {
            do {
                Combat.this.choixActionEnnemi(this.ennemie, equipe, adversaire);
                Combat.this.AttaqueEnnemi(this.ennemie, equipe, adversaire);
                //Combat.this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                System.out.println("ActionAdvThread => " + this.ennemie.getNom() + " : PV = " + this.ennemie.getPv());
                try {
                    Thread.sleep((int) (3000/this.ennemie.getVitesse()));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("ActionAdvThread => " + this.ennemie.getNom() + " InterruptedException");
                }
            } while (!this.ennemie.estKO());
            System.out.println("ActionAdvThread => " + this.ennemie.getNom() + " est ko");
            Combat.this.actions_perso.put(this.ennemie, Actions.ko);
            Combat.this.pcsControlleurVue.firePropertyChange("koPerso", this.ennemie, null);
        }
    }
    
    class ComboThread extends Thread {
        
        Perso perso;
        
        public ComboThread (Perso perso) {
            this.perso = perso;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println("fin combos 3s ecoulé");
                Combat.this.attribut_perso.get(this.perso).put(THREAD_ACTION, null);
            } catch (InterruptedException ex) {
                Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class SortThread extends Thread {
        
        private final Perso perso;
        private final int indice;

        public SortThread(Perso perso, int indice) {
            this.perso = perso;
            this.indice = indice;
        }

        @Override
        public void interrupt() {
            super.interrupt();
        }
        
        @Override
        public void run() {
            if (this.perso.getTec().getSort(this.indice) != null && (!(boolean)Combat.this.attribut_perso.get(this.perso).get(PREPARE_SORT))) {
                Combat.this.attribut_perso.get(this.perso).put(Combat.DEPLACEMENT, false);
                Combat.this.attribut_perso.get(this.perso).put(Combat.PREPARE_SORT, true);
                Combat.this.actions_perso.put(this.perso, Actions.sort);
                Combat.this.pcsControlleurVue.firePropertyChange("prepareSort", perso, null);
                try {
                    Thread.sleep((int)(this.perso.getVitesseLancementSort() * this.perso.getTec().getSort(this.indice).getPreparation() * 1000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
                }
                Combat.this.attribut_perso.get(this.perso).put(Combat.DEPLACEMENT, true);
                Combat.this.attribut_perso.get(this.perso).put(Combat.PREPARE_SORT, false);
                Combat.this.actions_perso.put(this.perso, Actions.aucun);
                int choixPerso = (int) Combat.this.attribut_perso.get(this.perso).get(CHOIX_PERSO);
                Position positionAdv;
                if (choixPerso == -1) {
                    if (this.perso instanceof Ennemie) {
                        positionAdv = Combat.this.equipe.getPerso(0).getPosition();
                    } else {
                        positionAdv = Combat.this.adversaire.getPerso(0).getPosition();
                    }
                } else {
                    if (this.perso instanceof Ennemie) {
                        positionAdv = new Position(Combat.this.equipe.getPerso(choixPerso).getPosition());
                    } else {
                        positionAdv = new Position(Combat.this.adversaire.getPerso(choixPerso).getPosition());
                    }
                }
                Combat.this.pcsControlleurVue.firePropertyChange("finSort", this.perso, null);
                this.perso.setPm(this.perso.getPm() - this.perso.getTec().getSort(this.indice).getPmTec());
                ObjetEndroitClassique oec;
                if (this.perso.getPosition().getOrientation() == Orientation.dos) {
                    oec = new ObjetEndroitClassique(Type_objet.couteau, this.perso.getPosition().getPositionX()-1, this.perso.getPosition().getPositionY());
                } else if (this.perso.getPosition().getOrientation() == Orientation.face) {
                    oec = new ObjetEndroitClassique(Type_objet.couteau, this.perso.getPosition().getPositionX()+1, this.perso.getPosition().getPositionY());
                } else if (this.perso.getPosition().getOrientation() == Orientation.gauche) {
                    oec = new ObjetEndroitClassique(Type_objet.couteau, this.perso.getPosition().getPositionX(), this.perso.getPosition().getPositionY()-1);
                } else {
                    oec = new ObjetEndroitClassique(Type_objet.couteau, this.perso.getPosition().getPositionX(), this.perso.getPosition().getPositionY()+1);
                }
                
                if (this.perso.getTec().getSort(this.indice).getVit() == 0) {
                    Combat.this.pcsControlleurVue.firePropertyChange("lanceSort", this.perso.getTec().getSort(this.indice), positionAdv);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Combat.this.pcsControlleurVue.firePropertyChange("sortAnnule", positionAdv, null);
                    Perso p;
                    if (this.perso instanceof Ennemie) {
                        p = Combat.this.equipe.aPerso(positionAdv);
                    } else {
                        p = Combat.this.adversaire.aPerso(positionAdv);
                    }
                    Combat.this.reçoitSort(this.perso, p, this.perso.getTec().getSort(this.indice));
                    Combat.this.attribut_perso.get(this.perso).put(SORTS, null);
                } else {
                    oec.getPosition().setOrientation(this.perso.getPosition().getOrientation());
                    Combat.this.attribut_perso.get(this.perso).put(SORTS, oec);
                    Combat.this.pcsControlleurVue.firePropertyChange("lanceSort", this.perso.getTec().getSort(this.indice), oec.getPosition());
                }
                while (Combat.this.attribut_perso.get(this.perso).get(SORTS) != null) {
                    if (this.perso instanceof Ennemie) {
                        for (int i = 0 ; i < Combat.this.equipe.size() ; i++) {
                            Perso p = Combat.this.equipe.getPerso(i);
                            ObjetEndroitClassique oecPerso = (ObjetEndroitClassique)Combat.this.attribut_perso.get(p).get(SORTS);
                            if (oecPerso != null && oecPerso.getPosition().equalsXY(oec.getPosition())) {
                                ((Thread)Combat.this.attribut_perso.get(p).get(THREAD_SORT)).interrupt();
                                Combat.this.attribut_perso.get(this.perso).put(SORTS, null);
                                Combat.this.pcsControlleurVue.firePropertyChange("sortAnnule", oec.getPosition(), null);
                            } else if (oec.getPosition().equalsXY(p.getPosition())) {
                                Combat.this.reçoitSort(this.perso, p, this.perso.getTec().getSort(this.indice));
                                Combat.this.attribut_perso.get(this.perso).put(SORTS, null);
                                Combat.this.pcsControlleurVue.firePropertyChange("sortAnnule", oec.getPosition(), null);
                                if (Combat.this.attribut_perso.get(p).get(THREAD_SORT) != null) {
                                    ((Thread)Combat.this.attribut_perso.get(p).get(THREAD_SORT)).interrupt();
                                    Combat.this.attribut_perso.get(p).put(THREAD_SORT, null);
                                    Combat.this.annuleSort(p);
                                }
                            } else if (oec.getPosition().getPositionX() < 0 || oec.getPosition().getPositionX() > 10 || 
                                    oec.getPosition().getPositionY() < 0 || oec.getPosition().getPositionY() > 10) {
                                Combat.this.attribut_perso.get(this.perso).put(SORTS, null);
                            }
                        }
                    } else {
                        for (int i = 0 ; i < Combat.this.adversaire.size() ; i++) {
                            Perso p = Combat.this.adversaire.getPerso(i);
                            ObjetEndroitClassique oecPerso = (ObjetEndroitClassique)Combat.this.attribut_perso.get(p).get(SORTS);
                            if (oecPerso != null && oecPerso.getPosition().equalsXY(oec.getPosition())) {
                                ((Thread)Combat.this.attribut_perso.get(p).get(THREAD_SORT)).interrupt();
                                Combat.this.attribut_perso.get(this.perso).put(SORTS, null);
                                Combat.this.pcsControlleurVue.firePropertyChange("sortAnnule", oec.getPosition(), null);
                            } else if (oec.getPosition().equalsXY(p.getPosition())) {
                                Combat.this.reçoitSort(this.perso, p, this.perso.getTec().getSort(this.indice));
                                Combat.this.attribut_perso.get(this.perso).put(SORTS, null);
                                Combat.this.pcsControlleurVue.firePropertyChange("sortAnnule", oec.getPosition(), null);
                                if (Combat.this.attribut_perso.get(p).get(THREAD_SORT) != null) {
                                    ((Thread)Combat.this.attribut_perso.get(p).get(THREAD_SORT)).interrupt();
                                    Combat.this.attribut_perso.get(p).put(THREAD_SORT, null);
                                    Combat.this.annuleSort(p);
                                }
                            } else if (oec.getPosition().getPositionX() < 0 || oec.getPosition().getPositionX() > 20 || 
                                    oec.getPosition().getPositionY() < 0 || oec.getPosition().getPositionY() > 20) {
                                Combat.this.attribut_perso.get(this.perso).put(SORTS, null);
                            }
                        }
                    }
                    if (Combat.this.attribut_perso.get(this.perso).get(SORTS) != null) {
                        try {
                            Thread.sleep(this.perso.getTec().getSort(this.indice).getVit() * 100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        oec = (ObjetEndroitClassique)Combat.this.attribut_perso.get(this.perso).get(SORTS);
                        if (oec != null) {
                            Combat.this.pcsControlleurVue.firePropertyChange("sortAnnule", oec.getPosition(), null);
                            if (oec.getPosition().getOrientation() == Orientation.dos) {
                                oec.getPosition().avancer();
                            } else if (oec.getPosition().getOrientation() == Orientation.face) {
                                oec.getPosition().reculer();
                            } else if (oec.getPosition().getOrientation() == Orientation.gauche) {
                                oec.getPosition().gauche();
                            } else {
                                oec.getPosition().droite();
                            }
                            Combat.this.pcsControlleurVue.firePropertyChange("lanceSort", this.perso.getTec().getSort(this.indice), oec.getPosition());
                        }
                    }
                }
                //Combat.this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            }
        }
    }
    
    private void annuleSort (Perso perso) {
        Combat.this.attribut_perso.get(perso).put(Combat.DEPLACEMENT, true);
        Combat.this.pcsControlleurVue.firePropertyChange("lanceSort", perso, null);
    }
    
    public void combatDuelJ1VSOrdi () {
        this.positionnePerso();
        ListKeyAction actions = new ListKeyAction();
        actions.addKeyAction(CodeBouton.HAUT, new ActionClass(this, "deplacePerso", new Object[] {this.joueur1, 1}));
        actions.addKeyAction(CodeBouton.BAS, new ActionClass(this, "deplacePerso", new Object[] {this.joueur1, 2}));
        actions.addKeyAction(CodeBouton.DROITE, new ActionClass(this, "deplacePerso", new Object[] {this.joueur1, 3}));
        actions.addKeyAction(CodeBouton.GAUCHE, new ActionClass(this, "deplacePerso", new Object[] {this.joueur1, 4}));
        actions.addKeyAction(CodeBouton.ACTION, new ActionClass(this, "attaquer", new Object[] {this.joueur1}));
        actions.addKeyAction(CodeBouton.ANNULATION, new ActionClass(this, "protection", new Object[] {this.joueur1}));
        actions.addKeyAction(CodeBouton.F1, new ActionClass(this, "lanceSort", new Object[] {this.joueur1, 0}));
        actions.addKeyAction(CodeBouton.F2, new ActionClass(this, "lanceSort", new Object[] {this.joueur1, 1}));
        actions.addKeyAction(CodeBouton.F3, new ActionClass(this, "lanceSort", new Object[] {this.joueur1, 2}));
        ListKeyAction actionsAnnulation = new ListKeyAction();
        actionsAnnulation.addKeyAction(CodeBouton.ANNULATION, new ActionClass(this, "finProtection", new Object[] {this.joueur1}));
        Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(actions, actionsAnnulation)));
        this.pcsControlleurVue.firePropertyChange("initImagePerso", null, null);
        this.actions_perso = new HashMap<>();
        this.attribut_perso = new HashMap<>();
        this.attribut_perso.put(this.joueur1, new HashMap<String, Object>());
        this.attribut_perso.get(this.joueur1).put(CHOIX_ACTION, -1);
        this.attribut_perso.get(this.joueur1).put(CHOIX_OBJECT, -1);
        this.attribut_perso.get(this.joueur1).put(CHOIX_PERSO, -1);
        this.attribut_perso.get(this.joueur1).put(CHOIX_TEC, -1);
        this.attribut_perso.get(this.joueur1).put(DEPLACEMENT, true);
        this.attribut_perso.get(this.joueur1).put(THREAD_SORT, null);
        this.attribut_perso.get(this.joueur1).put(THREAD_ACTION, null);
        this.attribut_perso.get(this.joueur1).put(SORTS, null);
        this.attribut_perso.get(this.joueur1).put(PREPARE_SORT, false);
        this.actions_perso.put(this.joueur1, Actions.aucun);
        for (int i = 1 ; i < this.equipe.size() ; i++) {
            this.attribut_perso.put(this.equipe.getPerso(i), new HashMap<String, Object>());
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_ACTION, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_OBJECT, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_PERSO, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_TEC, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(DEPLACEMENT, true);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD_SORT, null);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD_ACTION, null);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(SORTS, null);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(PREPARE_SORT, false);
            this.actions_perso.put(this.equipe.getPerso(i), Actions.aucun);
        }
        for (int i = 0 ; i < this.adversaire.size() ; i++) {
            this.attribut_perso.put(this.adversaire.getPerso(i), new HashMap<String, Object>());
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_ACTION, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_OBJECT, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_PERSO, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_TEC, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(DEPLACEMENT, true);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD_SORT, null);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD_ACTION, null);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(SORTS, null);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(PREPARE_SORT, false);
            this.actions_perso.put(this.adversaire.getPerso(i), Actions.aucun);
        }
        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
        for (int i = 1 ; i < this.equipe.size() ; i++) {
            ActionAllieThread actionAllieThread = new ActionAllieThread(this.equipe.getPerso(i));
            actionAllieThread.start();
            this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD, actionAllieThread);
        }
        for (int i = 0 ; i < this.adversaire.size() ; i++) {
            ActionAdvThread actionAdvThread = new ActionAdvThread(this.adversaire.getPerso(i));
            actionAdvThread.start();
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD, actionAdvThread);
        }
        do {
            this.attendre(1000);
            for (int i = 1 ; i < this.equipe.size() ; i++) {
                if (this.equipe.getPerso(i).estKO()) {
                    System.out.println("combatDuelJ1VSOrdi => " + this.equipe.getPerso(i).getSurnom() + " est ko");
                    //this.pcsControlleurVue.firePropertyChange("koPerso", this.equipe.getPerso(i), null);
                    //((Thread)this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD)).interrupt();
                    if (this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD_SORT) != null) {
                        ((Thread)this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD_SORT)).interrupt();
                        this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD_SORT, null);
                        this.annuleSort(this.equipe.getPerso(i));
                    }
                } else {
                    if (((Thread)this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD)).isInterrupted()) {
                        ((Thread)this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD)).start();
                    }
                }
            }
            for (int i = 0 ; i < this.adversaire.size() ; i++) {
                if (this.adversaire.getPerso(i).estKO()) {
                    System.out.println("combatDuelJ1VSOrdi => " + this.adversaire.getPerso(i).getNom() + " est ko");
                    //this.pcsControlleurVue.firePropertyChange("koPerso", this.adversaire.getPerso(i), null);
                    //((Thread)this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD)).interrupt();
                    if (this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD_SORT) != null) {
                        ((Thread)this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD_SORT)).interrupt();
                        this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD_SORT, null);
                        this.annuleSort(this.adversaire.getPerso(i));
                    }
                } else {
                    if (((Thread)this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD)).isInterrupted()) {
                        ((Thread)this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD)).start();
                    }
                }
            }
        } while (this.adversaire.size() != 0 && !this.equipe.estKO());
    }
    
    private void positionnePerso() {
        for (int i = 0 ; i < this.equipe.size() ; i++) {
            this.equipe.getPerso(i).setPosition(new Position(15, i*3));
        }
        for (int i = 0 ; i < this.adversaire.size() ; i++) {
            this.adversaire.getPerso(i).setPosition(new Position(0, i*3));
        }
    }
    
    public void deplacePerso (Perso p, Integer choix) {
        if ((Boolean)this.attribut_perso.get(p).get(DEPLACEMENT)) {
            this.attribut_perso.get(p).put(DEPLACEMENT, false);
            Position position = new Position(p.getPosition());
            switch (choix) {
                case 1:
                    System.out.println("avancer");
                    if (p.getPosition().getOrientation() == Orientation.dos && p.getPosition().getPositionX() > 0) {
                        position.avancer();
                        if ((p instanceof Ennemie && this.equipe.aPerso(position) == null) || 
                                (p instanceof Personnage && this.adversaire.aPerso(position) == null)) {
                            p.getPosition().avancer();
                        }
                        position.reculer();
                    } else {
                        p.getPosition().setOrientation(Orientation.dos);
                    }
                    break;
                case 2:
                    System.out.println("reculer");
                    if (p.getPosition().getOrientation() == Orientation.face && p.getPosition().getPositionX() < 18) {
                        position.reculer();
                        if ((p instanceof Ennemie && this.equipe.aPerso(position) == null) || 
                                (p instanceof Personnage && this.adversaire.aPerso(position) == null)) {
                            p.getPosition().reculer();
                        }
                        position.avancer();
                    } else {
                        p.getPosition().setOrientation(Orientation.face);
                    }
                    break;
                case 3:
                    System.out.println("droite");
                    if (p.getPosition().getOrientation() == Orientation.droite && p.getPosition().getPositionY() < 18) {
                        position.droite();
                        if ((p instanceof Ennemie && this.equipe.aPerso(position) == null) || 
                                (p instanceof Personnage && this.adversaire.aPerso(position) == null)) {
                            p.getPosition().droite();
                        }
                        position.gauche();
                    } else {
                        p.getPosition().setOrientation(Orientation.droite);
                    }
                    break;
                case 4:
                    System.out.println("gauche");
                    if (p.getPosition().getOrientation() == Orientation.gauche && p.getPosition().getPositionY() > 0) {
                        position.gauche();
                        if ((p instanceof Ennemie && this.equipe.aPerso(position) == null) || 
                                (p instanceof Personnage && this.adversaire.aPerso(position) == null)) {
                            p.getPosition().gauche();
                        }
                        position.droite();
                    } else {
                        p.getPosition().setOrientation(Orientation.gauche);
                    }
                    break;
            }
            this.actions_perso.put(p, Actions.deplacement);
            //if (!position.equalsXY(p.getPosition()))
            this.pcsControlleurVue.firePropertyChange("deplacerPerso", position, p);
            this.actions_perso.put(p, Actions.aucun);
            //this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            this.attribut_perso.get(p).put(DEPLACEMENT, true);
        }
    }
    
    public void attaquer (Personnage attaquant) {
        this.attaquer((Perso)attaquant);
    }
    
    public void attaquer (Perso attaquant) {
        boolean attaquer = true;
        if (this.attribut_perso.get(attaquant).get(THREAD_ACTION) == null) {
            attaquant.finirCombos();
            System.out.println("debut combos");
            ComboThread comboThread = new ComboThread(attaquant);
            this.attribut_perso.get(attaquant).put(THREAD_ACTION, comboThread);
            comboThread.start();
            attaquant.combos();
        } else {
            ((Thread)this.attribut_perso.get(attaquant).get(THREAD_ACTION)).interrupt();
            System.out.println("combos : " + attaquant.getComboEnCour());
            if (!attaquant.finCombos()) {
                ComboThread comboThread = new ComboThread(attaquant);
                this.attribut_perso.get(attaquant).put(THREAD_ACTION, comboThread);
                comboThread.start();
                attaquant.combos();
            } else {
                attaquant.finirCombos();
                if (this.attribut_perso.get(attaquant).get(THREAD_ACTION) == null) {
                    ComboThread comboThread = new ComboThread(attaquant);
                    this.attribut_perso.get(attaquant).put(THREAD_ACTION, comboThread);
                    comboThread.start();
                    attaquant.combos();
                } else {
                    this.attribut_perso.get(attaquant).put(THREAD_ACTION, null);
                    System.out.println("fin combos");
                    attaquer = false;
                }
            }
        }
        if (attaquer) {
            this.actions_perso.put(attaquant, Actions.attaquer);
            this.pcsControlleurVue.firePropertyChange("attaquePerso", attaquant, attaquant.getComboEnCour()-1);
            if (attaquant instanceof Ennemie) {
                for (int i = 0 ;  i < this.equipe.size() ; i++) {
                    if (this.equipe.getPerso(i).getPosition().aCote(attaquant.getPosition())) {
                        if (this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD_SORT) != null) {
                            ((Thread)this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD_SORT)).interrupt();
                            this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD_SORT, null);
                            this.annuleSort(this.equipe.getPerso(i));
                        }
                        double degat = this.degatAtt(attaquant, this.equipe.getPerso(i));
                        this.degat(this.equipe.getPerso(i), degat);
                        this.verifieKO(this.equipe.getPerso(i));
                    }
                }
            } else {
                for (int i = 0 ;  i < this.adversaire.size() ; i++) {
                    if (this.adversaire.getPerso(i).getPosition().aCote(attaquant.getPosition())) {
                        if (this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD_SORT) != null) {
                            ((Thread)this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD_SORT)).interrupt();
                            this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD_SORT, null);
                            this.annuleSort(this.adversaire.getPerso(i));
                        }
                        double degat = this.degatAtt(attaquant, this.adversaire.getPerso(i));
                        this.degat(this.adversaire.getPerso(i), degat);
                        this.verifieKO(this.adversaire.getPerso(i));
                    }
                }
            }
            this.actions_perso.put(attaquant, Actions.aucun);
        }
    }
    
    public void protection (Perso p) {
        this.actions_perso.put(p, Actions.defendre);
        this.pcsControlleurVue.firePropertyChange("protection", p, null);
        p.seMetEnGarde();
    }
    
    public void finProtection (Perso p) {
        this.actions_perso.put(p, Actions.aucun);
        this.pcsControlleurVue.firePropertyChange("finProtection", p, null);
        p.finGarde();
    }
    
    public void lanceSort (Perso perso, Integer indice) {
        SortThread sortThread = new SortThread(perso, indice);
        sortThread.start();
    }
    
    private void reçoitSort (Perso attaquant, Perso attaquer, Sort sort) {
        this.setAffichageCombat(this.textes.utiliseSur(attaquant.getNom(), sort.getNom(), attaquer.getNom()));
        attaquant.setPm(attaquant.getPm() - sort.getPmTec());
        double degat = degatSort(attaquant, attaquer, sort);
        this.degat(attaquer, degat);
        if (sort.getCategorie().equals(Categories.specialU)) {
            int aleaEtat = (int) (Math.random() * 20 + 1);
            if (aleaEtat == 1) {
                this.setAffichage(this.textes.etatAffecte);
                attaquer.setEtat(sort.getEtat());
            }
        }
        this.verifieKO(attaquer);
    }
    
    @Override
    public void commencerCombatDuelJ1VSOrdi() {
    }
    
    @Override
    protected void choixAction(Perso perso, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        Strategie strat = perso.getStrategiePerso();
        this.actionEffectue = false;
        this.choixAlie = -1;
        this.choixTec = -1;
        this.choixSac = -1;
        boolean attaqueDispo = true;
        boolean sortDispo = true;
        boolean examineDispo = true;
        boolean objetDispo = true;
        boolean denfenseDispo = true;
        if (strat.getAction1() != StrategieAction.ne_rien_faire) {
            if (strat.getAction1() == StrategieAction.defense) {
                attaqueDispo = false;
                sortDispo = false;
                examineDispo = false;
                objetDispo = false;
            } else if (strat.getAction1() == StrategieAction.attaque_meme) {
                this.choixAlie = this.choixPerso;
            } else if (strat.getAction1() == StrategieAction.attaque_different) {
                this.choixAlie = this.choixPerso;
                while (adversaire.size() > 1 && this.choixPerso == this.choixAlie) {
                    this.choixAlie = (int) (Math.random() * adversaire.size() - 1);
                }
            } else if (strat.getAction1() == StrategieAction.aerien) {
                for (int i = 0 ; i < adversaire.size() ; i++) {
                    if (adversaire.getPerso(i).getType1() == Types.vol || adversaire.getPerso(i).getType2() == Types.vol) {
                        this.choixAlie = i;
                        break;
                    }
                }
            } else if (strat.getAction1() == StrategieAction.bloque_magie) {
                for (int i = 0 ; i < adversaire.size() ; i++) {
                    if (adversaire.getPerso(i).getTec().size() > 0) {
                        this.choixAlie = i;
                        break;
                    }
                }
            } else if (strat.getAction1() == StrategieAction.reduire) {
                double pvMin = adversaire.getPerso(0).getPv();
                for (int i = 1 ; i < adversaire.size() ; i++) {
                    if (adversaire.getPerso(i).getPv() < pvMin) {
                        this.choixAlie = i;
                        pvMin = adversaire.getPerso(i).getPv();
                    }
                }
            }
            if (strat.getSort1() == StrategieSort.ne_pas_utiliser) {
                sortDispo = false;
            } else if (perso.getTec().size() == 0) {
                sortDispo = false;
            } else if (strat.getSort1() == StrategieSort.garder_reserve) {
                if (perso.getPm() <= (perso.getPmInitial() * 0.25)) {
                    sortDispo = false;
                }
            } else if (strat.getSort1() == StrategieSort.retenir) {
                if (perso.getPm() <= (perso.getPmInitial() * 0.55)) {
                    sortDispo = false;
                }
            } else if (strat.getSort1() == StrategieSort.sauvegarder) {
                if (perso.getPm() <= (perso.getPmInitial() * 0.5)) {
                    sortDispo = false;
                }
            } else if (strat.getSort1() == StrategieSort.soin) {
                for (int i = 1 ; i < equipe.size() ; i++) {
                    if (equipe.getPerso(i).getPv() < (equipe.getPerso(i).getPvInitial()*0.75)) {
                        this.choixAlie = i;
                    }
                }
                if (this.choixAlie == -1) {
                    for (int i = 0 ; i < perso.getTec().size() ; i++) {
                        if (perso.getTec().getSort(i).getCategorie() == Categories.soinU || 
                                perso.getTec().getSort(i).getCategorie() == Categories.soinE) {
                            this.choixTec = i;
                        }
                    }
                }
            }
            if (equipe.getSac().isEmpty()) {
                objetDispo = false;
            } else if (strat.getObjet1() == StrategieObjet.ne_pas_utiliser) {
                objetDispo = false;
            }
            if (/*this.attribut_perso.get(perso).get(CHOIX_PERSO) == -1 || */adversaire.getPerso((int)this.attribut_perso.get(perso).get(CHOIX_PERSO)).estKO()) {
                do {
                    int choixPerso = (int) (Math.random() * adversaire.size() - 1);
                    this.attribut_perso.get(perso).put(CHOIX_PERSO, choixPerso);
                } while (adversaire.getPerso(choixPerso).estKO());
            }
            int nbActionDispo = 5;
            int pourcentageApartager = 0;
            int pourcentageAttaque = 20;
            int pourcentageSort = 20;
            int pourcentageExamine = 20;
            int pourcentageObjet = 20;
            int pourcentageDefense = 20;
            if (!attaqueDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageAttaque;
                pourcentageAttaque = 0;
            }
            if (!sortDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageSort;
                pourcentageSort = 0;
            }
            if (!examineDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageExamine;
                pourcentageExamine = 0;
            }
            if (!objetDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageObjet;
                pourcentageObjet = 0;
            }
            if (!denfenseDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageDefense;
            }
            int pourcentageBonus = (pourcentageApartager/nbActionDispo);
            if (attaqueDispo) {
                pourcentageAttaque += pourcentageBonus;
            }
            if (sortDispo) {
                pourcentageSort += pourcentageBonus;
            }
            if (examineDispo) {
                pourcentageExamine += pourcentageBonus;
            }
            if (objetDispo) {
                pourcentageObjet += pourcentageBonus;
            }
            pourcentageSort = pourcentageSort + pourcentageAttaque;
            pourcentageExamine = pourcentageExamine + pourcentageSort;
            pourcentageObjet = pourcentageObjet + pourcentageExamine;
            if (!this.actionEffectue && nbActionDispo > 0) {
                int alea = (int) (Math.random() * 100 + 1);
                if (attaqueDispo && alea <= pourcentageAttaque) {
                    this.choix = 1;
                } else if (sortDispo && alea > pourcentageAttaque && alea <= pourcentageSort) {
                    this.choix = 2;
                    if (this.choixTec == -1) {
                        this.choixTec = (int) (Math.random() * perso.getTec().size() - 1);
                    }
                } else if (examineDispo && alea > pourcentageSort && alea <= pourcentageExamine) {
                    this.choix = 3;
                } else if (objetDispo && alea > pourcentageExamine && alea <= pourcentageObjet) {
                    this.choix = 4;
                    if (this.choixSac == -1) {
                        this.choixSac = (int) (Math.random() * equipe.getSac().size() - 1);
                    }
                } else if (denfenseDispo && alea > pourcentageObjet) {
                    this.choix = 5;
                }
                this.actionEffectue = true;
            }
        } else {
            this.actionEffectue = true;
        }
    }

    // cette fonction permet de savoir lequel de vos personnage l'ennemi va attaquer
    @Override
    protected void AttaqueAllie(Perso perso, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        switch (this.choix) {
            case (1):
                this.attaquer(perso);
                break;
            case (2) :
                int choixTec = (int) (Math.random() * perso.getTec().size());
                this.attribut_perso.get(perso).put(CHOIX_TEC, choixTec);
                this.lanceSort(perso, (int)this.attribut_perso.get(perso).get(CHOIX_TEC));
                break;
            case (3):
                Position position = perso.getPosition();
                int choixPerso = (int)this.attribut_perso.get(perso).get(CHOIX_PERSO);
                if (choixPerso != -1) {
                    Position positionAdv = equipe.getPerso(choixPerso).getPosition();
                    if (!positionAdv.aCote(position)) {
                        if (positionAdv.getPositionX() == position.getPositionX()) {
                            if (positionAdv.getPositionY() > position.getPositionY()) {
                                this.deplacePerso(perso, 3);
                            } else {
                                this.deplacePerso(perso, 4);
                            }
                        } else if (positionAdv.getPositionY() == position.getPositionY()) {
                            if (positionAdv.getPositionX() > position.getPositionX()) {
                                this.deplacePerso(perso, 2);
                            } else {
                                this.deplacePerso(perso, 1);
                            }
                        } else {
                            if (positionAdv.getPositionX() > position.getPositionX()) {
                                this.deplacePerso(perso, 2);
                            } else {
                                this.deplacePerso(perso, 1);
                            }
                        }
                    } else {
                        
                    }
                }
                break;
            case (4):
                this.setAffichageCombat(this.textes.pasObjet);
                break;
            case (5):
                this.protection(perso);
                break;
        }
    }

    @Override
    protected void choixActionEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        Strategie strat = ennemie.getStrategiePerso();
        boolean attaqueDispo = true;
        boolean sortDispo = true;
        boolean deplacementDispo = true;
        boolean objetDispo = true;
        boolean denfenseDispo = false;
        this.actionEffectue = false;
        if (strat.getAction1() != StrategieAction.ne_rien_faire) {
            if (strat.getAction1() == StrategieAction.defense) {
                if (equipe.getPerso(0).getPosition().estProche(2, ennemie.getPosition())) {
                    attaqueDispo = false;
                    sortDispo = false;
                    deplacementDispo = false;
                    objetDispo = false;
                    denfenseDispo = true;
                }
            } else if (strat.getAction1() == StrategieAction.attaque_meme) {
            } else if (strat.getAction1() == StrategieAction.attaque_different) {
            } else if (strat.getAction1() == StrategieAction.aerien) {
            } else if (strat.getAction1() == StrategieAction.bloque_magie) {
            } else if (strat.getAction1() == StrategieAction.reduire) {
            }
            if (strat.getDeplacement1() == StrategieDeplacement.proche) {
                System.out.println("StrategieDeplacement proche");
                if (!equipe.getPerso(0).getPosition().estProche(2, ennemie.getPosition())) {
                    System.out.println("perso pas proche");
                    attaqueDispo = false;
                    sortDispo = false;
                    deplacementDispo = true;
                    objetDispo = false;
                    denfenseDispo = false;
                }
            } else if (strat.getDeplacement1() == StrategieDeplacement.immobile) {
                deplacementDispo = false;
            }
            if (strat.getSort1() == StrategieSort.ne_pas_utiliser || ennemie.getTec().size() == 0) {
                sortDispo = false;
            } else if (strat.getSort1() == StrategieSort.garder_reserve) {
                if (ennemie.getPm() <= (ennemie.getPmInitial() * 0.25)) {
                    sortDispo = false;
                }
            } else if (strat.getSort1() == StrategieSort.retenir) {
                if (ennemie.getPm() <= (ennemie.getPmInitial() * 0.55)) {
                    sortDispo = false;
                }
            } else if (strat.getSort1() == StrategieSort.sauvegarder) {
                if (ennemie.getPm() <= (ennemie.getPmInitial() * 0.5)) {
                    sortDispo = false;
                }
            } else if (strat.getSort1() == StrategieSort.soin) {
            }
            if (attaqueDispo && !equipe.getPerso(0).getPosition().estProche(1, ennemie.getPosition())) {
                attaqueDispo = false;
            }
            if (sortDispo) {
                sortDispo = false;
                for (int i = 0 ; i < ennemie.getTec().size() ; i++) {
                    if (ennemie.getTec().getSort(0).getPm() <= ennemie.getPm()) {
                        sortDispo = true;
                        break;
                    }
                }
            }
            if (adversaire.getSac().isEmpty()) {
                objetDispo = false;
            } else if (strat.getObjet1() == StrategieObjet.ne_pas_utiliser) {
                objetDispo = false;
            }
            if (((int)this.attribut_perso.get(ennemie).get(CHOIX_PERSO)) == -1 || equipe.getPerso((int)this.attribut_perso.get(ennemie).get(CHOIX_PERSO)).estKO()) {
                do {
                    int choixPerso = (int) (Math.random() * equipe.size() - 1);
                    this.attribut_perso.get(ennemie).put(CHOIX_PERSO, choixPerso);
                } while (equipe.getPerso(choixPerso).estKO());
            }
            System.out.println("attaqueDispo : " + attaqueDispo);
            System.out.println("sortDispo : " + sortDispo);
            System.out.println("deplacementDispo : " + deplacementDispo);
            System.out.println("objetDispo : " + objetDispo);
            System.out.println("denfenseDispo : " + denfenseDispo);
            int nbActionDispo = 5;
            int pourcentageApartager = 0;
            int pourcentageAttaque = 20;
            int pourcentageSort = 20;
            int pourcentageDeplacement = 20;
            int pourcentageObjet = 20;
            int pourcentageDefense = 20;
            if (!attaqueDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageAttaque;
                pourcentageAttaque = 0;
            }
            if (!sortDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageSort;
                pourcentageSort = 0;
            }
            if (!deplacementDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageDeplacement;
                pourcentageDeplacement = 0;
            }
            if (!objetDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageObjet;
                pourcentageObjet = 0;
            }
            if (!denfenseDispo) {
                nbActionDispo--;
                pourcentageApartager += pourcentageDefense;
            }
            int pourcentageBonus = (pourcentageApartager/nbActionDispo);
            if (attaqueDispo) {
                pourcentageAttaque += pourcentageBonus;
            }
            if (sortDispo) {
                pourcentageSort += pourcentageBonus;
            }
            if (deplacementDispo) {
                pourcentageDeplacement += pourcentageBonus;
            }
            if (objetDispo) {
                pourcentageObjet += pourcentageBonus;
            }
            pourcentageSort = pourcentageSort + pourcentageAttaque;
            pourcentageDeplacement = pourcentageDeplacement + pourcentageSort;
            pourcentageObjet = pourcentageObjet + pourcentageDeplacement;
            int choixPrec = this.choix;
            if (!this.actionEffectue && nbActionDispo > 0) {
                int alea = (int) (Math.random() * 100 + 1);
                if (attaqueDispo && alea <= pourcentageAttaque) {
                    this.choix = 1;
                } else if (sortDispo && alea > pourcentageAttaque && alea <= pourcentageSort) {
                    this.choix = 2;
                    if (this.choixTec == -1) {
                        this.choixTec = (int) (Math.random() * ennemie.getTec().size() - 1);
                    }
                } else if (deplacementDispo && alea > pourcentageSort && alea <= pourcentageDeplacement) {
                    this.choix = 3;
                } else if (objetDispo && alea > pourcentageDeplacement && alea <= pourcentageObjet) {
                    this.choix = 4;
                    if (this.choixSac == -1) {
                        this.choixSac = (int) (Math.random() * adversaire.getSac().size() - 1);
                    }
                } else if (denfenseDispo && alea > pourcentageObjet) {
                    this.choix = 5;
                }
                this.actionEffectue = true;
            }
            if (choixPrec == 5 && this.choix != 5) {
                this.pcsControlleurVue.firePropertyChange("finProtection", ennemie, null);
                ennemie.finGarde();
            }
        } else {
            this.actionEffectue = true;
            this.choixAlie = -1;
        }
    }

    // cette fonction permet de savoir lequel de vos personnage l'ennemi va attaquer
    @Override
    protected void AttaqueEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        switch (this.choix) {
            case (1):
                this.attaquer(ennemie);
                break;
            case (2) :
                int choixTec = (int) (Math.random() * ennemie.getTec().size());
                this.attribut_perso.get(ennemie).put(CHOIX_TEC, choixTec);
                this.lanceSort(ennemie, (int)this.attribut_perso.get(ennemie).get(CHOIX_TEC));
                break;
            case (3):
                Position position = ennemie.getPosition();
                int choixPerso = (int)this.attribut_perso.get(ennemie).get(CHOIX_PERSO);
                System.out.println("deplacement");
                if (choixPerso != -1) {
                    Position positionAdv = equipe.getPerso(choixPerso).getPosition();
                    if (!positionAdv.aCote(position)) {
                        if (positionAdv.getPositionX() == position.getPositionX()) {
                            if (positionAdv.getPositionY() > position.getPositionY()) {
                                this.deplacePerso(ennemie, 3);
                            } else {
                                this.deplacePerso(ennemie, 4);
                            }
                        } else if (positionAdv.getPositionY() == position.getPositionY()) {
                            if (positionAdv.getPositionX() > position.getPositionX()) {
                                this.deplacePerso(ennemie, 2);
                            } else {
                                this.deplacePerso(ennemie, 1);
                            }
                        } else {
                            if (positionAdv.getPositionX() > position.getPositionX()) {
                                this.deplacePerso(ennemie, 2);
                            } else {
                                this.deplacePerso(ennemie, 1);
                            }
                        }
                    } else {
                        
                    }
                }
                break;
            case (4):
                this.setAffichageCombat(this.textes.pasObjet);
                break;
            case (5):
                this.protection(ennemie);
                break;
        }
    }

    @Override
    protected void combatPerso(Personnage perso, ListeDePerso<Ennemie> adversaire, ListeDePerso<Personnage> equipe) {
    }
    
}
