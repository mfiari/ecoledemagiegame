/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.terminal;

import mfiari.ecoledemagie.game.demarrage.Demarrage;
import mfiari.ecoledemagie.game.perso.Equipe;
import mfiari.ecoledemagie.game.perso.Personnages;
import mfiari.ecoledemagie.game.texte.TexteVueDemarrage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author mike
 */
public class EcoleDeMagie_vueConsole_demarrage extends EcoleDeMagie_vueConsole {
    
    private Demarrage demarrage;
    private TexteVueDemarrage textes;
    
    public EcoleDeMagie_vueConsole_demarrage (){
    }
    
    public EcoleDeMagie_vueConsole_demarrage (Demarrage demarage) {
        super(TexteVueDemarrage.getInstance());
        this.demarrage = demarage;
        this.textes = TexteVueDemarrage.getInstance();
        this.demarrage.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
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
                    case "info":
                        info();
                        break;
                }
            }
        });
    }

    public void choixLangue() {
        System.out.println("choisissez une langue");
        System.out.println("1.français     2.english");
        this.demarrage.setChoix(testEntier());
    }

    public void debutJeu() {
        System.out.println("1."+this.textes.nouvellePartie+"    2."+this.textes.continuer+"     3."+this.textes.extra);
        this.demarrage.setChoix(testEntier());
    }
    
    //cette fonction permet de choisir une equipe
    public void choisirSonEquipe() {
        int a;
        do {
            System.out.println("quel equipe choisisez vous?:");
            System.out.println("1.noir         2.blanc");
            System.out.println("3.bleu         4.vert");
            System.out.println("5.orange         6.marron");
            System.out.println("7.jaune         8.rouge");
            System.out.println("9.Info sur le choix");
            a = testEntier();
        } while (a < 1 || a > 9);
        this.demarrage.setChoix(a);
        switch (a) {
            case (1):
                this.demarrage.setEquipe(Equipe.noir);
                break;
            case (2):
                this.demarrage.setEquipe(Equipe.blanc);
                break;
            case (3):
                this.demarrage.setEquipe(Equipe.bleu);
                break;
            case (4):
                this.demarrage.setEquipe(Equipe.vert);
                break;
            case (5):
                this.demarrage.setEquipe(Equipe.orange);
                break;
            case (6):
                this.demarrage.setEquipe(Equipe.marron);
                break;
            case (7):
                this.demarrage.setEquipe(Equipe.jaune);
                break;
            case (8):
                this.demarrage.setEquipe(Equipe.rouge);
                break;
            default:
                this.demarrage.setEquipe(null);
                break;
        }
    }

    //information lors du choix de l'quipe
    public void info() {
        System.out.println("vous devez choisir une des 8 equipes propose pour jouer au jeu.");
        suivant();
        System.out.println("Mais une fois que vous avez choisi votre equipe, vous ne pourrez pas la modifier en cour de jeu.");
        suivant();
        System.out.println("Chaque equipe est composee de 5 eleves dont 1 capitaine. Et chaque equipe possede ses propres stat.");
        suivant();
        System.out.println("le capitaine de l'equipe que vous choisirez sera votre personnage principale,");
        suivant();
        System.out.println("c'est lui que vous suivrez tout au long du jeu ainsi que les membres de votre equipe.");
        suivant();
        System.out.println("Chaque equipe est differente, il faut donc bien choisir son equipe avant de commencer.");
        suivant();
        System.out.println("pour vous aidez a bien choisir votre equipe, les stats generale de chaque equipe et");
        suivant();
        System.out.println("de leur capitain sont evaluer avec des *. plus il y a de * et plus la stat sera meilleur.");
        suivant();
        System.out.println("Equipe correspond aux stats generale de l'equipe que vous choisirez.");
        suivant();
        System.out.println("Capitaine correspond aux stats generale que possede le capitaine de l'equipe.");
        suivant();
        System.out.println("les differentes stats qui sont montree sont les suivantes:");
        suivant();
        System.out.println("PV: correspond aux point de vie du personnage.");
        suivant();
        System.out.println("force: correspond a la force physique du personnage.");
        suivant();
        System.out.println("def: correspond a la defense physique du personnage.");
        suivant();
        System.out.println("PM: correspond aux point de magie du personnage, cela determine donc le nombre d'attaque magique que peut faire un personnage.");
        suivant();
        System.out.println("Magie: correspond  a la puissance magique du personnage.");
        suivant();
        System.out.println("Res: correspond a la resistance magique du personnage.");
        suivant();
        System.out.println("Vit: correspond a la vitesse du personnage, ca determine la vitesse de deplacemeent sur le terrain et la vitesse d'enchainement des attaques.");
        suivant();
        System.out.println("Esq: correspond a la capacite d'esquive du personnage.");
        suivant();
        System.out.println("Strat: c'est la strategie du personnage et de l'equipe, plus cela sera elevee et plus le personnage pourra travailler en equipe et faire des combos avec les membres de son equipe.");
        suivant();
        System.out.println("pre: c'est la precision du personnage. plus une attaque est precise, plus elle inflige de degat.");
        suivant();
        System.out.println("VAS: correspond a la Vitesse d'Apprentissage des Sorts. Au cour de l'avanture, vous aurez a apprendre des sorts et a les ameliorer, et plus vite vous apprenez ces sorts, plus tot vous aurez des sorts puissant.");
        suivant();
        System.out.println("L'explication est terminer, bonne chance et amusez vous bien.");
        suivant();
    }
    
    public void confirmerChoix () {
        System.out.println(this.demarrage.getEquipe().toString());
        System.out.println("1.choisir       2.voir stat      3.retour");
        this.demarrage.setChoix(testEntier());
    }
    
    public void menuStatDebut () {
        switch (this.demarrage.getEquipe()) {
            case noir:
                 System.out.println("Equipe: "+ Equipe.noir +"	capitain: " + Personnages.lex.getNom());
                break;
            case blanc:
                System.out.println("Equipe: "+ Equipe.blanc +"	capitain: " + Personnages.tidus.getNom());
                break;
            case bleu:
                System.out.println("Equipe: "+ Equipe.bleu +"	capitain: " + Personnages.marcus.getNom());
                break;
            case vert:
                System.out.println("Equipe: "+ Equipe.vert +"	capitain: " + Personnages.drake.getNom());
                break;
            case orange:
                System.out.println("Equipe: "+ Equipe.orange +"	capitain: " + Personnages.zack.getNom());
                break;
            case marron:
                System.out.println("Equipe: "+ Equipe.marron +"	capitain: " + Personnages.olivier.getNom());
                break;
            case jaune:
                System.out.println("Equipe: "+ Equipe.jaune +"	capitain: " + Personnages.fred.getNom());
                break;
            case rouge:
                System.out.println("Equipe: "+ Equipe.rouge +"	capitain: " + Personnages.mark.getNom());
                break;
        }
        System.out.println("1.Equipe		2.capitain		3.retour");
        this.demarrage.setChoix(testEntier());
    }
    
    //cette procedure permet de voir les stats des equipes au debut du jeu
    public void afficheStatDebut() {
        switch (this.demarrage.getEquipe()) {
            case noir:
                 switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |*******   |");
                        System.out.println("Force: |*******   |");
                        System.out.println("Def:   |*******   |");
                        System.out.println("PM:    |******    |");
                        System.out.println("Magie: |********  |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |******    |");
                        System.out.println("Esq:   |******    |");
                        System.out.println("Strat: |******    |");
                        System.out.println("Pre:   |******    |");
                        System.out.println("VAS:   |******    |");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.lex.getNom());
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |*******   |");
                        System.out.println("Def:   |*******   |");
                        System.out.println("PM:    |******    |");
                        System.out.println("Magie: |********* |");
                        System.out.println("Res:   |********  |");
                        System.out.println("Vit:   |*****     |");
                        System.out.println("Esq:   |******    |");
                        System.out.println("Strat: |******    |");
                        System.out.println("Pre:   |*****     |");
                        System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                 }
                break;
            case blanc:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |*****     |");
                        System.out.println("Force: |*****     |");
                        System.out.println("Def:   |*****     |");
                        System.out.println("PM:    |*******   |");
                        System.out.println("Magie: |*******   |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |*******   |");
                        System.out.println("Esq:   |*******   |");
                        System.out.println("Strat: |*****     |");
                        System.out.println("Pre:   |*******   |");
                        System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.tidus.getNom());
                            System.out.println("PV:    |*****     |");
                            System.out.println("Force: |*****     |");
                            System.out.println("Def:   |*****     |");
                            System.out.println("PM:    |*******   |");
                            System.out.println("Magie: |*******   |");
                            System.out.println("Res:   |*******   |");
                            System.out.println("Vit:   |*******   |");
                            System.out.println("Esq:   |*******   |");
                            System.out.println("Strat: |*****     |");
                            System.out.println("Pre:   |*******   |");
                            System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                 }
                break;
            case bleu:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |*****     |");
                        System.out.println("Force: |*****     |");
                        System.out.println("Def:   |*****     |");
                        System.out.println("PM:    |********  |");
                        System.out.println("Magie: |*******   |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |*******   |");
                        System.out.println("Esq:   |********  |");
                        System.out.println("Strat: |**********|");
                        System.out.println("Pre:   |**********|");
                        System.out.println("VAS:   |******    |");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.marcus.getNom());
                        System.out.println("PV:    |*****     |");
                        System.out.println("Force: |*****     |");
                        System.out.println("Def:   |*****     |");
                        System.out.println("PM:    |********* |");
                        System.out.println("Magie: |*******   |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |*******   |");
                        System.out.println("Esq:   |********  |");
                        System.out.println("Strat: |**********|");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |******    |");
                        suivant();
                        break;
                 }
                break;
            case vert:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |********  |");
                        System.out.println("Def:   |********  |");
                        System.out.println("PM:    |********  |");
                        System.out.println("Magie: |*******   |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |******    |");
                        System.out.println("Esq:   |******    |");
                        System.out.println("Strat: |******    |");
                        System.out.println("Pre:   |*******   |");
                        System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.drake.getNom());
                        System.out.println("PV:    |********* |");
                        System.out.println("Force: |********  |");
                        System.out.println("Def:   |********  |");
                        System.out.println("PM:    |********* |");
                        System.out.println("Magie: |*******   |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |******    |");
                        System.out.println("Esq:   |******    |");
                        System.out.println("Strat: |******    |");
                        System.out.println("Pre:   |*******   |");
                        System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                 }
                break;
            case orange:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |*****     |");
                        System.out.println("Def:   |**********|");
                        System.out.println("PM:    |********  |");
                        System.out.println("Magie: |*****     |");
                        System.out.println("Res:   |**********|");
                        System.out.println("Vit:   |****      |");
                        System.out.println("Esq:   |****      |");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.zack.getNom());
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |*****     |");
                        System.out.println("Def:   |**********|");
                        System.out.println("PM:    |********  |");
                        System.out.println("Magie: |*****     |");
                        System.out.println("Res:   |**********|");
                        System.out.println("Vit:   |****      |");
                        System.out.println("Esq:   |****      |");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                 }
                break;
            case marron:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |******    |");
                        System.out.println("Def:   |********  |");
                        System.out.println("PM:    |**********|");
                        System.out.println("Magie: |**********|");
                        System.out.println("Res:   |********  |");
                        System.out.println("Vit:   |********  |");
                        System.out.println("Esq:   |********  |");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |********  |");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.olivier.getNom());
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |******    |");
                        System.out.println("Def:   |********  |");
                        System.out.println("PM:    |**********|");
                        System.out.println("Magie: |**********|");
                        System.out.println("Res:   |********  |");
                        System.out.println("Vit:   |********  |");
                        System.out.println("Esq:   |********  |");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |********  |");
                        suivant();
                        break;
                 }
                break;
            case jaune:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |*******   |");
                        System.out.println("Force: |*******   |");
                        System.out.println("Def:   |*******   |");
                        System.out.println("PM:    |*******   |");
                        System.out.println("Magie: |********  |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |********  |");
                        System.out.println("Esq:   |********  |");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |*******   |");
                        System.out.println("VAS:   |*****     |");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.fred.getNom());
                        System.out.println("PV:    |*******   |");
                        System.out.println("Force: |***       |");
                        System.out.println("Def:   |***       |");
                        System.out.println("PM:    |********* |");
                        System.out.println("Magie: |********  |");
                        System.out.println("Res:   |*******   |");
                        System.out.println("Vit:   |**********|");
                        System.out.println("Esq:   |**********|");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |*******   |");
                        suivant();
                        break;
                 }
                break;
            case rouge:
                switch (this.demarrage.getChoix()) {
                    case (1):
                        System.out.println("stat de l'equipe");
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |*****     |");
                        System.out.println("Def:   |*****     |");
                        System.out.println("PM:    |********  |");
                        System.out.println("Magie: |*******   |");
                        System.out.println("Res:   |******    |");
                        System.out.println("Vit:   |*******   |");
                        System.out.println("Esq:   |******    |");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |**********|");
                        suivant();
                        break;
                     //affiche les stats générale du capitaine de l'équipe au début de jeu
                     case (2):
                        System.out.println("stat de "+Personnages.mark.getNom());
                        System.out.println("PV:    |********  |");
                        System.out.println("Force: |*****     |");
                        System.out.println("Def:   |*****     |");
                        System.out.println("PM:    |********  |");
                        System.out.println("Magie: |*******   |");
                        System.out.println("Res:   |******    |");
                        System.out.println("Vit:   |*******   |");
                        System.out.println("Esq:   |******    |");
                        System.out.println("Strat: |********  |");
                        System.out.println("Pre:   |********  |");
                        System.out.println("VAS:   |**********|");
                        suivant();
                        break;
                }
                break;
        }
    }
}
