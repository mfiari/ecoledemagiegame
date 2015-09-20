/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.combat;

import mfiari.ecoledemagie.game.condition.ConditionAnimationCombat;
import mfiari.ecoledemagie.game.Actions;
import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.ecoledemagie.game.evenement.EvenementCombat;
import mfiari.ecoledemagie.game.evenement.EvenementCombatAnimation;
import mfiari.ecoledemagie.game.extra.Duel;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.sort.Categories;
import mfiari.ecoledemagie.game.sort.Sort;
import mfiari.ecoledemagie.game.sort.Types;
import mfiari.ecoledemagie.game.sort.Types_sort;
import mfiari.ecoledemagie.game.texte.TexteVueCombat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.lib.game.clavier.ActionClass;
import mfiari.lib.game.clavier.ActionPerso;
import mfiari.lib.game.clavier.CodeBouton;
import mfiari.lib.game.clavier.KeyDispatcher;
import mfiari.lib.game.clavier.ListKeyAction;
import mfiari.lib.game.condition.Condition;
import mfiari.lib.game.condition.ConditionAttaque;
import mfiari.lib.game.condition.ConditionDefense;
import mfiari.lib.game.condition.ConditionDegat;
import mfiari.lib.game.condition.ConditionObjet;
import mfiari.lib.game.condition.ConditionSecondaire;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.liste.ListeEntier;
import mfiari.lib.game.objet.ObjetEndroitClassique;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.swing.Ecran;

/**
 *
 * @author mike
 */
public abstract class AbstractCombat extends ControlleurVue {
    
    protected Evenement evenement;
    protected ListeDePerso<Personnage> equipe;
    protected ListeDePerso<Personnage> equipeJ2;
    protected ListeDePerso<Ennemie> adversaire;
    protected ListeDePerso<Ennemie> ennemieDExp;
    protected Personnage joueur1;
    protected ListeEntier tableau;
    protected String affichage;
    protected int choixPerso;
    protected int choixTec;
    protected int choixSac;
    protected int choixAlie;
    protected boolean actionEffectue;
    protected TexteVueCombat textes;
    protected boolean peutBouger;
    protected Map<Perso, Map<String, Object>> attribut_perso;
    protected Map<Perso, Actions> actions_perso;
    
    protected final static String THREAD = "thread";
    protected final static String CHOIX_ACTION = "action";
    protected final static String CHOIX_TEC = "tec";
    protected final static String CHOIX_PERSO = "perso";
    protected final static String CHOIX_OBJECT = "object";
    protected final static String DEPLACEMENT = "deplacement";
    protected final static String THREAD_SORT = "thread_sort";
    protected final static String THREAD_ACTION = "thread_action";
    protected final static String SORTS = "sorts";
    protected final static String PREPARE_SORT = "prepare_sort";
    
    {
        this.peutBouger = true;
    }

    public AbstractCombat() {
        super(true);
        this.adversaire = new ListeDePerso<>();
        this.ennemieDExp = new ListeDePerso<>();
        this.equipe = new ListeDePerso<>();
        this.tableau = new ListeEntier();
    }

    public AbstractCombat(ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire, Evenement evenement) {
        super(true);
        this.evenement = evenement;
        this.textes = TexteVueCombat.getInstance();
        this.joueur1 = equipe.getPersonnage(0);
        this.adversaire = adversaire;
        this.equipe = equipe;
        this.ennemieDExp = new ListeDePerso<>();
        for (int i = 0; i < this.adversaire.size(); i++) {
            this.ennemieDExp.ajouterPerso(this.adversaire.getPerso(i));
        }
        tableau = new ListeEntier();
    }
    
    public AbstractCombat(ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire, Duel duel) {
        super(true);
        //this.jeu = duel.getJeu();
        this.textes = TexteVueCombat.getInstance();
        this.adversaire = adversaire;
        this.joueur1 = equipe.getPersonnage(0);
        this.equipe = equipe;
        this.ennemieDExp = new ListeDePerso<>();
        for (int i = 0; i < this.adversaire.size(); i++) {
            this.ennemieDExp.ajouterPerso(this.adversaire.getPerso(i));
        }
        tableau = new ListeEntier();
    }
    
    public AbstractCombat(ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        //this.jeu = duel.getJeu();
        this.textes = TexteVueCombat.getInstance();
        this.joueur1 = equipe.getPersonnage(0);
        this.adversaire = adversaire;
        this.equipe = equipe;
        this.ennemieDExp = new ListeDePerso<>();
        for (int i = 0; i < this.adversaire.size(); i++) {
            this.ennemieDExp.ajouterPerso(this.adversaire.getPerso(i));
        }
        tableau = new ListeEntier();
    }
    
    public void setAffichage(String texte) {
        this.affichage = texte;
        this.pcsControlleurVue.firePropertyChange("affichage", null, null);
    }
    
    public void setAffichageCombat(String texte) {
        this.affichage = texte;
        this.pcsControlleurVue.firePropertyChange("affichageCombat", null, null);
    }
    
    public Evenement getJeu() {
        return this.evenement;
    }
    
    public String getAffichage() {
        return this.affichage;
    }
    
    public ListeDePerso getEquipe() {
        return this.equipe;
    }

    public ListeDePerso getAdversaire() {
        return this.adversaire;
    }
    
    public Personnage getPerso () {
        return this.joueur1;
    }
    
    public void setChoixPerso (int action) {
        this.choixPerso = action;
    }
    
    public int getChoixPerso () {
        return this.choixPerso;
    }
    
    public void setChoixSac (int sac) {
        this.choixSac = sac;
    }
    
    public int getChoixSac () {
        return this.choixSac;
    }
    
    public Actions getActionByPerso (Perso perso) {
        return this.actions_perso.get(perso);
    }
    
    public ObjetEndroitClassique getSort (int x, int y, int z) {
        List<ObjetEndroitClassique> list = new ArrayList<>();
        for (int i = 0 ; i < this.equipe.size() ; i++) {
            if (this.attribut_perso.get(this.equipe.getPerso(i)).get(SORTS) != null) {
                list.add((ObjetEndroitClassique)this.attribut_perso.get(this.equipe.getPerso(i)).get(SORTS));
            }
        }
        for (int i = 0 ; i < this.adversaire.size() ; i++) {
            if (this.attribut_perso.get(this.adversaire.getPerso(i)).get(SORTS) != null) {
                list.add((ObjetEndroitClassique)this.attribut_perso.get(this.adversaire.getPerso(i)).get(SORTS));
            }
        }
        for (ObjetEndroitClassique oec : list) {
            if (oec.getPosition().equals(x, y, z)) {
                return oec;
            }
        }
        return null;
    }
    
    class ActionAllieThread extends Thread {
        
        Perso perso;
        
        public ActionAllieThread (Perso perso) {
            this.perso = perso;
        }
        
        @Override
        public void run() {
            do {
                AbstractCombat.this.choixAction(this.perso, equipe, adversaire);
                AbstractCombat.this.AttaqueAllie(this.perso, equipe, adversaire);
                AbstractCombat.this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            } while (!this.perso.estKO());
        }
    }
    protected void choixAction(Perso perso, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        
    }
    
    protected void AttaqueAllie(Perso perso, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        
    }
    
    class ActionAdvThread extends Thread {
        
        Ennemie ennemie;
        
        public ActionAdvThread (Ennemie ennemie) {
            this.ennemie = ennemie;
        }
        
        @Override
        public void run() {
            do {
                AbstractCombat.this.choixActionEnnemi(this.ennemie, equipe, adversaire);
                AbstractCombat.this.AttaqueEnnemi(this.ennemie, equipe, adversaire);
                AbstractCombat.this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (!this.ennemie.estKO());
        }
    }
    
    public void commencerCombat(boolean evenement) {
        /* preparer les perso et autres objets */
        /* Afficher le terrain */
        /* Si il y a un dialogue au début du combat, faire parler les perso */
        /* commencer le combat */
        /* Si il y a des évènement particulier pendant le combat faire : 
         * 1) Si certaine actions sont interdite, bloquer 
         */
        int indice;
        Perso p;
        EvenementCombat evenCbt = null;
        Condition derniereConditionEffectuer = null;
        if (evenement) {
            EvenementCombat evenCbtTest = (EvenementCombat) this.evenement;
            if (evenCbtTest.aDialogueDebutCombat()) {
                evenCbtTest.getDialogueDebutCombat().activeEvenement(this);
            }
        }
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
        this.actions_perso.put(this.joueur1, Actions.aucun);
        for (int i = 1 ; i < this.equipe.size() ; i++) {
            ActionAllieThread actionAllieThread = new ActionAllieThread(this.equipe.getPerso(i));
            actionAllieThread.start();
            this.attribut_perso.put(this.equipe.getPerso(i), new HashMap<String, Object>());
            this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD, actionAllieThread);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_ACTION, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_OBJECT, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_PERSO, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(CHOIX_TEC, -1);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(DEPLACEMENT, true);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD_SORT, null);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(THREAD_ACTION, null);
            this.attribut_perso.get(this.equipe.getPerso(i)).put(SORTS, null);
            this.actions_perso.put(this.equipe.getPerso(i), Actions.aucun);
        }
        for (int i = 0 ; i < this.adversaire.size() ; i++) {
            ActionAdvThread actionAdvThread = new ActionAdvThread(this.adversaire.getPerso(i));
            actionAdvThread.start();
            this.attribut_perso.put(this.adversaire.getPerso(i), new HashMap<String, Object>());
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD, actionAdvThread);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_ACTION, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_OBJECT, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_PERSO, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(CHOIX_TEC, -1);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(DEPLACEMENT, true);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD_SORT, null);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(THREAD_ACTION, null);
            this.attribut_perso.get(this.adversaire.getPerso(i)).put(SORTS, null);
            this.actions_perso.put(this.adversaire.getPerso(i), Actions.aucun);
        }
        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
        do {
            this.attendre(1000);
            for (int i = 1 ; i < this.equipe.size() ; i++) {
                if (this.equipe.getPerso(i).estKO()) {
                    ((Thread)this.attribut_perso.get(this.equipe.getPerso(i)).get(THREAD)).interrupt();
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
                    ((Thread)this.attribut_perso.get(this.adversaire.getPerso(i)).get(THREAD)).interrupt();
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
        } while (!this.adversaire.estKO() && !this.equipe.estKO());
        /*do {
            if (evenement) {
                EvenementCombat evenCbtTest = (EvenementCombat) this.jeu.getEvenement();
                if (!evenCbtTest.equals(evenCbt)) {
                    evenCbt = evenCbtTest;
                    if (evenCbt.aCondition()) {
                        derniereConditionEffectuer = evenCbt.getCondition();
                        if (evenCbt.aCondition()) {
                            if (evenCbt.getDialogue() != null) {
                                evenCbt.getDialogue().activeEvenement(null);
                            }
                        }
                    }
                }
            }
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            p = this.combat();
            this.setAffichage(this.textes.auTourDe(p.getNom()));
            indice = tableau.get(tableau.size() - 1);
            if (indice < equipe.size()) {
                Personnage perso = equipe.getPerso(indice);
                if (perso.getPv() != 0 && adversaire.size() != 0) {
                    if (perso.equals(this.joueur1)) {
                        do {
                            this.actionEffectue = false;
                            do {
                                this.pcsControlleurVue.firePropertyChange("afficherMenuJoueur", null, null);
                            } while (this.choix < 1 || this.choix > 6);
                            if (evenement) {
                                if (evenCbt.aCondition()) {
                                    if (evenCbt.getCondition() instanceof ConditionPrimaire) {
                                        if (evenCbt.getCondition().equals(this.getConditionAction(this.choix))) {
                                            combatPerso(perso, adversaire, equipe);
                                            evenCbt.conditionReussi();
                                        } else {
                                            evenCbt.getWarning().activeEvenement(null);
                                        }
                                    } else {
                                        combatPerso(perso, adversaire, equipe);
                                    }
                                } else {
                                    combatPerso(perso, adversaire, equipe);
                                }
                            } else {
                                combatPerso(perso, adversaire, equipe);
                            }
                        } while(!this.actionEffectue || (evenement && !evenCbt.estFini() && evenCbt.aCondition() && 
                                derniereConditionEffectuer instanceof ConditionPrimaire && derniereConditionEffectuer.equals(evenCbt.getCondition())));
                    } else {
                        
                    }
                }
            } else {
                if (adversaire.contains(ennemieDExp.getPerso(indice - equipe.size())) && (!equipe.estKO())) {
                    Ennemie adv = ennemieDExp.getPerso(indice - equipe.size());
                    if (evenement) {
                        this.choixActionEnnemi(adv, equipe, adversaire);
                        AttaqueEnnemi(adv, equipe, adversaire);
                    } else {
                        this.choixActionEnnemi(adv, equipe, adversaire);
                        AttaqueEnnemi(adv, equipe, adversaire);
                    }
                }
            }
            if (this.tableau.size() == this.equipe.size() + this.ennemieDExp.size()) {
                while (!tableau.isEmpty()) {
                    tableau.remove(0);
                }
            }
            if (evenement && evenCbt.aCondition() && !evenCbt.estFini()) {
                if (evenCbt.getCondition() instanceof ConditionSecondaire) {
                    evenCbt.conditionReussi();
                }
            }
        } while (adversaire.size() != 0 && !equipe.estKO() && (!evenCbt.estFini() || !evenCbt.aCondition() ||
                (derniereConditionEffectuer instanceof ConditionFinDeTour && !tableau.isEmpty())));*/
        this.gameOver(equipe);
        if (!evenement || (evenement && evenCbt!= null && evenCbt.aCondition() && derniereConditionEffectuer instanceof ConditionSecondaire)) {
            equipe.setArgent(expEtArgent(equipe, ennemieDExp));
        }
    }
    
    private boolean gameOver (ListeDePerso<Personnage> equipe) {
        for (int i = 0; i < equipe.size() ; i++) {
            if (!equipe.getPerso(i).estKO()) {
                return false;
            }
        }
        return true;
    }
    
    
    
    private void annuleSort (Perso perso) {
        this.attribut_perso.get(perso).put(Combat.DEPLACEMENT, true);
        this.pcsControlleurVue.firePropertyChange("lanceSort", perso, null);
    }
    
    private void positionnePerso() {
        for (int i = 0 ; i < this.equipe.size() ; i++) {
            this.equipe.getPerso(i).setPosition(new Position(6, i));
        }
        for (int i = 0 ; i < this.adversaire.size() ; i++) {
            this.adversaire.getPerso(i).setPosition(new Position(0, i));
        }
    }
    
    public abstract void commencerCombatDuelJ1VSOrdi();
    
    public void combatAnime() {
        EvenementCombatAnimation evenCbtAnim = (EvenementCombatAnimation) this.evenement;
        ConditionAnimationCombat animation;
        if (evenCbtAnim.aDialogueDebutCombat()) {
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            evenCbtAnim.getDialogueDebutCombat().activeEvenement(this);
        }
        while (evenCbtAnim.aConditionAnimation() && !evenCbtAnim.estFini()) {
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            animation = evenCbtAnim.getConditionAnimation();
            combatAnime(animation);
            evenCbtAnim.conditionReussi();
        }
        if (evenCbtAnim.aDialogueFinCombat()) {
            evenCbtAnim.getDialogueFinCombat().activeEvenement(this);
        }
    }
    
    public void combatAnime(ConditionAnimationCombat animation) {
        double degat;
        Perso attaquant = animation.getAttaquant();
        Perso attaquer = animation.getAttaquer();
        switch (animation.getActionAttaquant()) {
            case attaquer:
                this.setAffichage(this.textes.attaque(attaquant.getNom(), attaquer.getNom()));
                switch (animation.getActionAttaquer()) {
                    case sort:
                        this.setAffichage(this.textes.utilise(attaquer.getNom(), animation.getSortAttaquer().getNom()));
                        attaquer.setPm(attaquer.getPm() - animation.getSortAttaquer().getPmTec());
                        attaquer.recoitSoin(animation.getSortAttaquer(), (Personnage)attaquer);          
                        animation.getSortAttaquer().setNbUtil();
                        degat = this.degatAtt(attaquant, attaquer);
                        this.degat(attaquer, degat);
                        break;
                    case defendre:
                        attaquer.seMetEnGarde();
                        this.setAffichage(this.textes.seMetEnGarde(attaquer.getNom()));
                        degat = this.degatAtt(attaquant, attaquer);
                        this.degat(attaquer, degat);
                        break;
                    case esquive:
                        this.setAffichage(this.textes.esquiveAttaque(attaquer.getNom()));
                        break;
                    case rate:
                        this.setAffichage(this.textes.rateAttaque(attaquant.getNom()));
                        break;
                    default:
                        degat = this.degatAtt(attaquant, attaquer);
                        this.degat(attaquer, degat);
                        break;
                }
                break;
            case sort:
                this.setAffichage(this.textes.utiliseSur(attaquant.getNom(), animation.getSortAttaquant().getNom(), attaquer.getNom()));
                switch (animation.getActionAttaquer()) {
                    case sort:
                        this.setAffichage(this.textes.utilise(attaquer.getNom(), animation.getSortAttaquer().getNom()));
                        attaquer.setPm(attaquer.getPm() - animation.getSortAttaquer().getPmTec());
                        attaquer.recoitSoin(animation.getSortAttaquer(), (Personnage)attaquer);          
                        animation.getSortAttaquer().setNbUtil();
                        degat = this.degatAtt(attaquant, attaquer);
                        this.degat(attaquer, degat);
                        break;
                    case defendre:
                        attaquer.seMetEnGarde();
                        this.setAffichage(this.textes.seMetEnGarde(attaquer.getNom()));
                        degat = this.degatSort(attaquant, attaquer, animation.getSortAttaquant());
                        this.degat(attaquer, degat);
                        break;
                    case esquive:
                        this.setAffichage(this.textes.esquiveAttaque(attaquer.getNom()));
                        break;
                    case rate:
                        this.setAffichage(this.textes.rateAttaque(attaquant.getNom()));
                        break;
                    default:
                        degat = this.degatSort(attaquant, attaquer, animation.getSortAttaquant());
                        this.degat(attaquer, degat);
                        break;
                }
                break;
            default:
                break;
        }
    }

    protected double arrondiDegat(Perso p, double degat) {
        if (degat <= (-p.getDefense())) {
            degat = 0;
        } else {
            degat = 1;
        }
        return degat;
    }
    
    protected void degat (Perso attaquer, double degat) {
        if (degat <= 1) {
            degat = arrondiDegat(attaquer, degat);
        }
        this.setAffichageCombat(this.textes.recoitDegat(attaquer.getNom(), (int) Math.round(degat)));
        attaquer.recoitDegat(degat);
    }

    protected boolean verifieKO(Perso p) {
        this.setAffichageCombat(this.textes.affichePvPerso(p));
        if (p.estKO()) {
            this.setAffichageCombat(this.textes.estKO(p.getNom()));
            return true;
        }
        return false;
    }

    protected double degatAtt(Perso attaquant, Perso attaquer) {
        double degat;
        if (attaquer.estFaible(Types.normal)) {
            degat = ((attaquant.getForce() + (attaquant.getForce() / 2)) - attaquer.getDefense());
        } else {
            if (attaquer.estFort(Types.normal)) {
                degat = (attaquant.getForce() - (attaquer.getDefense() + (attaquer.getDefense() / 2)));
            } else {
                degat = (attaquant.getForce() - attaquer.getDefense());
            }
        }
        return degat;
    }

    protected double degatSort(Perso attaquant, Perso attaquer, Sort sort) {
        double degat;
        if (sort.getCapacite().equals(Types_sort.magie)) {
            if (attaquer.estFaible(sort.getType())) {
                degat = (((attaquant.getMagie() + (attaquant.getMagie() * sort.getAtt()))
                        + ((attaquant.getMagie() + attaquant.getMagie() * sort.getAtt()) / 2)) - attaquer.getResistance());
            } else {
                if (attaquer.estFort(sort.getType())) {
                    degat = ((attaquant.getMagie() + (attaquant.getMagie() * sort.getAtt())) -
                            (attaquer.getResistance() + (attaquer.getResistance() / 2)));
                } else {
                    degat = ((attaquant.getMagie() + (attaquant.getMagie() * sort.getAtt())) - attaquer.getResistance());
                }
            }
        } else {
            if (sort.getCategorie().equals(Categories.volU)) {
                degat = ((attaquant.getForce() * sort.getAtt()) - attaquer.getDefense());
            } else {
                if (attaquer.estFaible(sort.getType())) {
                    degat = (((attaquant.getForce() + attaquant.getForce() * sort.getAtt()) +
                            ((attaquant.getForce() + attaquant.getForce() * sort.getAtt()) / 2)) - attaquer.getDefense());
                } else {
                    if (attaquer.estFort(sort.getType())) {
                        degat = ((attaquant.getForce() + attaquant.getForce() * sort.getAtt()) - (attaquer.getDefense() + (attaquer.getDefense() / 2)));
                    } else {
                        degat = ((attaquant.getForce() + (attaquant.getForce() * sort.getAtt())) - attaquer.getDefense());
                    }
                }
            }
        }
        return degat;
    }

    protected abstract void choixActionEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire);

    protected int choixTec(Perso perso) {
        // cette fonction permet a l'ennemi d'utiliser une technique sur un perso
        if (choixTec < perso.getTec().size() && perso.getTec().size() != 0 && perso.getPm() >= perso.getTec().getSort(choixTec).getPmTec()) {
            Sort sort = perso.getTec().getSort(choixTec);
            if (sort.getCategorie().equals(Categories.attaqueU) || sort.getCategorie().equals(Categories.specialU)) {
                return 1;
            }
            if (sort.getCategorie().equals(Categories.attaqueE) || sort.getCategorie().equals(Categories.specialE)) {
                return 2;
            }
            if (sort.getCategorie().equals(Categories.boostU) || sort.getCategorie().equals(Categories.soinU)) {
                return 3;
            }
            if (sort.getCategorie().equals(Categories.boostE) || sort.getCategorie().equals(Categories.soinE)) {
                return 4;
            }
            if (sort.getCategorie().equals(Categories.boostM)) {
                return 5;
            }
            return 6;
        }
        return 0;
    }

    // cette fonction permet de savoir lequel de vos personnage l'ennemi va attaquer
    protected abstract void AttaqueEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire);

    protected abstract void combatPerso(Personnage perso, ListeDePerso<Ennemie> adversaire, ListeDePerso<Personnage> equipe);

    protected int expEtArgent(ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        int exp = 0;
        int expRecu;
        int monai = 0;
        int nbPerso = 0;
        for (int i = 0; i < adversaire.size(); i++) {
            exp = exp + adversaire.getPerso(i).getExperience();
            monai = monai + adversaire.getPerso(i).getArgent();
        }
        for (int i = 0; i < equipe.size(); i++) {
            if (equipe.getPerso(i).getPv() != 0) {
                nbPerso++;
            }
        }
        expRecu = (exp / (nbPerso));
        this.setAffichage(this.textes.expEtArgent(expRecu,monai,equipe.getArgent()));
        this.pcsControlleurVue.firePropertyChange("afficherMenuFinDeCombat", null, null);
        for (int i = 0; i < equipe.size(); i++) {
            Personnage perso = equipe.getPerso(i);
            perso.setExperience(expRecu);
            /*if (perso.getExperience() > perso.getExpNivSup()) {
                this.setAffichage(this.textes.monteDeNiveau(perso.getNom()));
                perso.nivSuiv(perso.getStat());
            }
            perso.setExpRestant((perso.getExpNivSup() - perso.getExperience()));*/
        }
        equipe.setArgent(monai);
        this.setAffichage(this.textes.expEtArgent(0,0,equipe.getArgent()));
        this.pcsControlleurVue.firePropertyChange("afficherMenuFinDeCombat", null, null);
        while (adversaire.size() != 0) {
            adversaire.enleverPerso(0);
        }
        return monai;
    }

    protected Condition getConditionAction(int action) {
        switch (action) {
            case (1):
                return new ConditionAttaque();
            case (2):
                return new ConditionDegat();
            case (3):
                return new ConditionDegat();
            case (4):
                return new ConditionObjet();
            case (5):
                return new ConditionDefense();
            case (6):
                return new ConditionDegat();
        }
        return null;
    }

}