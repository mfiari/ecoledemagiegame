/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.extra.Duel;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.menu.Stat;
import mfiari.ecoledemagie.game.perso.Equipe;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.perso.Personnages;
import mfiari.lib.game.position.Position;
import mfiari.ecoledemagie.game.sort.Sorts;
import mfiari.ecoledemagie.game.strategie.Strategie;
import mfiari.ecoledemagie.game.strategie.StrategieAction;
import mfiari.ecoledemagie.game.strategie.StrategieDeplacement;
import mfiari.ecoledemagie.game.strategie.StrategieObjet;
import mfiari.ecoledemagie.game.strategie.StrategieSort;
import mfiari.ecoledemagie.game.ville.Endroits;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class CreationPerso {
    
    private ListeDePerso<Personnage> listePerso;
    private ListeDePerso<Personnage> equipe;
    private Equipe equipeDuPerso;

    public CreationPerso () {
        this.listePerso = new ListeDePerso<>() ;
        this.equipe = new ListeDePerso<>();
        this.creationPersoPrincipal();
        this.creationPersonnages();
        this.creationHabitant();
    }
    
    public CreationPerso (Equipe equipe) {
        this.equipeDuPerso = equipe;
        this.listePerso = new ListeDePerso<>() ;
        this.equipe = new ListeDePerso<>();
        this.creationPersoPrincipal();
        this.creationPersonnages();
        this.creationHabitant();
    }
    
    public CreationPerso (Duel duel) {
        this.listePerso = new ListeDePerso<>();
        this.creationDuel();
        this.listePerso.ajouterPerso(Personnages.lex);
        this.listePerso.ajouterPerso(Personnages.mark);
        this.listePerso.ajouterPerso(Personnages.tidus);
        this.listePerso.ajouterPerso(Personnages.marcus);
        this.listePerso.ajouterPerso(Personnages.drake);
        this.listePerso.ajouterPerso(Personnages.zack);
        this.listePerso.ajouterPerso(Personnages.olivier);
        this.listePerso.ajouterPerso(Personnages.fred);
    }
    
    public CreationPerso (EcoleDeMagie jeu, boolean partie) {
        this.creationHabitant();
    }

    private void creationPersoPrincipal () {
        switch (this.equipeDuPerso) {
            case noir:
                 this.equipe.ajouterPerso(Personnages.lex);
                break;
            case blanc:
                this.equipe.ajouterPerso(Personnages.tidus);
                break;
            case bleu:
                this.equipe.ajouterPerso(Personnages.marcus);
                break;
            case vert:
                this.equipe.ajouterPerso(Personnages.drake);
                break;
            case orange:
                this.equipe.ajouterPerso(Personnages.zack);
                break;
            case marron:
                this.equipe.ajouterPerso(Personnages.olivier);
                break;
            case jaune:
                this.equipe.ajouterPerso(Personnages.fred);
                break;
            case rouge:
                this.equipe.ajouterPerso(Personnages.mark);
                break;
        }
        Personnages.lex.setPosition(new Position(2, 0, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.lex.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.tidus.setPosition(new Position(2, 1, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.tidus.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.marcus.setPosition(new Position(2, 2, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.marcus.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.drake.setPosition(new Position(2, 3, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.drake.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.zack.setPosition(new Position(2, 4, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.zack.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.olivier.setPosition(new Position(2, 5, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.olivier.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.fred.setPosition(new Position(2, 6, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.fred.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.mark.setPosition(new Position(2, 7, 0, Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos));
        Personnages.mark.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
    }
    
    private void creationPersonnages() {
        Personnages.al.setStat(new Stat(Personnages.al, Personnages.alNiv100));
        Personnages.al.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.alan.setStat(new Stat(Personnages.alan, Personnages.alanNiv100));
        Personnages.alan.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.ashley.setStat(new Stat(Personnages.ashley, Personnages.ashleyNiv100));
        Personnages.ashley.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.capitaine.setStat(new Stat(Personnages.capitaine, Personnages.capitaineNiv100));
        Personnages.capitaine.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.crane.setStat(new Stat(Personnages.crane, Personnages.craneNiv100));
        Personnages.crane.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.dennis.setStat(new Stat(Personnages.dennis, Personnages.dennisNiv100));
        Personnages.dennis.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.dj.setStat(new Stat(Personnages.dj, Personnages.djNiv100));
        Personnages.dj.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.drake.setStat(new Stat(Personnages.drake, Personnages.drakeNiv100));
        Personnages.drake.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.emile.setStat(new Stat(Personnages.emile, Personnages.emileNiv100));
        Personnages.emile.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.felix.setStat(new Stat(Personnages.felix, Personnages.felixNiv100));
        Personnages.felix.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.fred.setStat(new Stat(Personnages.fred, Personnages.fredNiv100));
        Personnages.fred.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.gamard.setStat(new Stat(Personnages.gamard, Personnages.gamardNiv100));
        Personnages.gamard.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.gorille.setStat(new Stat(Personnages.gorille, Personnages.gorilleNiv100));
        Personnages.gorille.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.jack.setStat(new Stat(Personnages.jack, Personnages.jackNiv100));
        Personnages.jack.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.jane.setStat(new Stat(Personnages.jane, Personnages.janeNiv100));
        Personnages.jane.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.karateka.setStat(new Stat(Personnages.karateka, Personnages.karatekaNiv100));
        Personnages.karateka.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.kevin.setStat(new Stat(Personnages.kevin, Personnages.kevinNiv100));
        Personnages.kevin.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.lasso.setStat(new Stat(Personnages.lasso, Personnages.lassoNiv100));
        Personnages.lasso.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.lex.setStat(new Stat(Personnages.lex, Personnages.lexNiv100));
        Personnages.lex.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.lutin.setStat(new Stat(Personnages.lutin, Personnages.lutinNiv100));
        Personnages.lutin.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.marcus.setStat(new Stat(Personnages.marcus, Personnages.marcusNiv100));
        Personnages.marcus.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.mark.setStat(new Stat(Personnages.mark, Personnages.markNiv100));
        Personnages.mark.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.maurice.setStat(new Stat(Personnages.maurice, Personnages.mauriceNiv100));
        Personnages.maurice.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.maxime.setStat(new Stat(Personnages.maxime, Personnages.maximeNiv100));
        Personnages.maxime.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.mimi.setStat(new Stat(Personnages.mimi, Personnages.mimiNiv100));
        Personnages.mimi.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.mortard.setStat(new Stat(Personnages.mortard, Personnages.mortardNiv100));
        Personnages.mortard.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.olivier.setStat(new Stat(Personnages.olivier, Personnages.olivierNiv100));
        Personnages.olivier.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.pistolet.setStat(new Stat(Personnages.pistolet, Personnages.pistoletNiv100));
        Personnages.pistolet.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.poussy.setStat(new Stat(Personnages.poussy, Personnages.poussyNiv100));
        Personnages.poussy.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.richard.setStat(new Stat(Personnages.richard, Personnages.richardNiv100));
        Personnages.richard.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.rosa.setStat(new Stat(Personnages.rosa, Personnages.rosaNiv100));
        Personnages.rosa.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.sala.setStat(new Stat(Personnages.sala, Personnages.salaNiv100));
        Personnages.sala.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.sony.setStat(new Stat(Personnages.sony, Personnages.sonyNiv100));
        Personnages.sony.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.tidus.setStat(new Stat(Personnages.tidus, Personnages.tidusNiv100));
        Personnages.tidus.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.velo.setStat(new Stat(Personnages.velo, Personnages.veloNiv100));
        Personnages.velo.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.vincent.setStat(new Stat(Personnages.vincent, Personnages.vincentNiv100));
        Personnages.vincent.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.voleur.setStat(new Stat(Personnages.voleur, Personnages.voleurNiv100));
        Personnages.voleur.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.will.setStat(new Stat(Personnages.will, Personnages.willNiv100));
        Personnages.will.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.yuna.setStat(new Stat(Personnages.yuna, Personnages.yunaNiv100));
        Personnages.yuna.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
        Personnages.zack.setStat(new Stat(Personnages.zack, Personnages.zackNiv100));
        Personnages.zack.setStrategiePerso(new Strategie(StrategieAction.attaque_meme, StrategieDeplacement.proche, StrategieSort.sans_compter, 
                StrategieObjet.libre));
    }
    
    private void creationPersonnagesDuel() {
        Personnages.al.setNiveau(50);
        Personnages.al.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.al.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.al.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.al.getTec().ajoutSort(Sorts.enchantement);
        Personnages.al.getTec().ajoutSort(Sorts.flamme);
        Personnages.al.getTec().ajoutSort(Sorts.rayon);
        /*Personnages.alan.setNiveau(50);
        Personnages.alan.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.alan.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.alan.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.ashley.setNiveau(50);
        Personnages.ashley.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.ashley.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.ashley.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.capitaine.setNiveau(50);
        Personnages.capitaine.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.capitaine.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.capitaine.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.crane.setNiveau(50);
        Personnages.crane.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.crane.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.crane.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.jack.setNiveau(50);
        Personnages.jack.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.jack.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.jack.getTechniques().ajoutSort(Sorts.rayon);*/
        Personnages.lex.setNiveau(50);
        Personnages.lex.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.lex.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.lex.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.lex.getTec().ajoutSort(Sorts.enchantement);
        Personnages.lex.getTec().ajoutSort(Sorts.flamme);
        Personnages.lex.getTec().ajoutSort(Sorts.rayon);
        Personnages.marcus.setNiveau(50);
        Personnages.marcus.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.marcus.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.marcus.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.marcus.getTec().ajoutSort(Sorts.enchantement);
        Personnages.marcus.getTec().ajoutSort(Sorts.flamme);
        Personnages.marcus.getTec().ajoutSort(Sorts.rayon);
        Personnages.mark.setNiveau(50);
        Personnages.mark.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.mark.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.mark.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.mark.getTec().ajoutSort(Sorts.enchantement);
        Personnages.mark.getTec().ajoutSort(Sorts.flamme);
        Personnages.mark.getTec().ajoutSort(Sorts.rayon);
        Personnages.olivier.setNiveau(50);
        Personnages.olivier.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.olivier.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.olivier.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.olivier.getTec().ajoutSort(Sorts.enchantement);
        Personnages.olivier.getTec().ajoutSort(Sorts.flamme);
        Personnages.olivier.getTec().ajoutSort(Sorts.rayon);
        Personnages.tidus.setNiveau(50);
        Personnages.tidus.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.tidus.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.tidus.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.tidus.getTec().ajoutSort(Sorts.enchantement);
        Personnages.tidus.getTec().ajoutSort(Sorts.flamme);
        Personnages.tidus.getTec().ajoutSort(Sorts.rayon);
        Personnages.zack.setNiveau(50);
        Personnages.zack.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.zack.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.zack.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.zack.getTec().ajoutSort(Sorts.enchantement);
        Personnages.zack.getTec().ajoutSort(Sorts.flamme);
        Personnages.zack.getTec().ajoutSort(Sorts.rayon);
        Personnages.drake.setNiveau(50);
        Personnages.drake.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.drake.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.drake.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.drake.getTec().ajoutSort(Sorts.enchantement);
        Personnages.drake.getTec().ajoutSort(Sorts.flamme);
        Personnages.drake.getTec().ajoutSort(Sorts.rayon);
        Personnages.fred.setNiveau(50);
        Personnages.fred.getTechniques().ajoutSort(Sorts.enchantement);
        Personnages.fred.getTechniques().ajoutSort(Sorts.flamme);
        Personnages.fred.getTechniques().ajoutSort(Sorts.rayon);
        Personnages.fred.getTec().ajoutSort(Sorts.enchantement);
        Personnages.fred.getTec().ajoutSort(Sorts.flamme);
        Personnages.fred.getTec().ajoutSort(Sorts.rayon);
    }
    
    private void creationDuel () {
        this.creationPersonnages();
        this.creationPersonnagesDuel();
    }

    private void creationHabitant () {
    }

    public ListeDePerso<Personnage> getPersos () {
        return this.listePerso;
    }

    public ListeDePerso<Personnage> getEquipe() {
        return this.equipe;
    }
    
}
