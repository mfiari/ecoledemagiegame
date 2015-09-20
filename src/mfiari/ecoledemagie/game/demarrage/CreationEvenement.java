/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.demarrage;

import mfiari.ecoledemagie.game.perso.Habitants;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.perso.Personnages;
import mfiari.ecoledemagie.game.perso.Equipe;
import mfiari.ecoledemagie.game.perso.Ennemies;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.condition.Action;
import mfiari.ecoledemagie.game.condition.ConditionAnimation;
import mfiari.ecoledemagie.game.condition.ConditionAnimationCombat;
import mfiari.ecoledemagie.game.evenement.EvenementCombat;
import mfiari.ecoledemagie.game.evenement.EvenementDialogue;
import mfiari.ecoledemagie.game.evenement.Evenements;
import mfiari.ecoledemagie.game.sort.Sorts;
import mfiari.ecoledemagie.game.ville.Endroits;
import mfiari.lib.game.evenements.EvenementDeplacement;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class CreationEvenement {
    
    private Personnage capitaine;

    public CreationEvenement() {
        this.creationEvenementDialogue();
        this.creationEvenementCombat();
        this.creationEvenementChoix();
        this.creationEvenementAnimation();
        this.creationEvenementSpecial();
        this.creationEvenementDeplacement();
        this.creationEvenementQueteAnnexe();
        this.creationEvenementQuete();
    }
    
    public CreationEvenement(Personnage capitaine) {
        this.capitaine = capitaine;
        this.creationEvenementDialogue();
        this.creationEvenementCombat();
        this.creationEvenementChoix();
        this.creationEvenementAnimation();
        this.creationEvenementSpecial();
        this.creationEvenementDeplacement();
        this.creationEvenementQueteAnnexe();
        this.creationEvenementQuete();
    }

    private void creationEvenementQuete() {
        Evenements.jeu.initialise();
        Evenements.jeu.ajouterQuete(Evenements.chapitre1);
        Evenements.chapitre1.initialise();
        Evenements.chapitre1.ajouterQuete(Evenements.lepreuve);
        Evenements.lepreuve.removeAll();
        Evenements.lepreuve.initialise();
        //Evenements.lepreuve.ajouterQuete(Evenements.debut_lepreuve_chapitre1);
        Evenements.lepreuve.ajouterQuete(Evenements.discourDirecteur_lepreuve_chapitre1);
        Evenements.lepreuve.ajouterQuete(Evenements.sort_lepreuve_chapitre1);
        Evenements.lepreuve.ajouterQuete(Evenements.entreeDansForet);
        Evenements.lepreuve.ajouterQuete(Evenements.dialogue1_lepreuve_chapitre1);
        Evenements.lepreuve.ajouterQuete(Evenements.combat1_lepreuve_chapitre1);
        switch (this.capitaine.getEquipe()) {
            case blanc :
                Evenements.lepreuve.ajouterQuete(Evenements.dialogue2_lepreuve_chapitre1_blanc);
                Evenements.lepreuve.ajouterQuete(Evenements.combatAnim_lepreuve_chapitre1_blanc);
                Evenements.lepreuve.ajouterQuete(Evenements.dialogue3_lepreuve_chapitre1_blanc);
                Evenements.lepreuve.ajouterQuete(Evenements.combatAnim2_lepreuve_chapitre1_blanc);
                Evenements.lepreuve.ajouterQuete(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu);
                break;
            case jaune :
                Evenements.lepreuve.ajouterQuete(Evenements.dialogue2_lepreuve_chapitre1_jaune);
                Evenements.lepreuve.ajouterQuete(Evenements.rocher_lepreuve_chapitre1_jaune);
                Evenements.lepreuve.ajouterQuete(Evenements.dialogue3_lepreuve_chapitre1_jaune);
                break;

        }
        Evenements.lepreuve.ajouterQuete(Evenements.dialogueFin_lepreuve_chapitre1);
    }

    private void creationEvenementQueteAnnexe() {
        
    }

    private void creationEvenementDialogue() {
        switch (this.capitaine.getEquipe()) {
            case blanc :
                Evenements.debut_lepreuve_chapitre1.initialise();
                Evenements.debut_lepreuve_chapitre1.ajouterGens(Personnages.tidus);
                Evenements.debut_lepreuve_chapitre1.ajouterGens(Habitants.mamanDeTidus);
                Personnages.tidus.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "Maman, regarde ce qui est arrive. c'est la lettre de l'ecole.", 1);
                Habitants.mamanDeTidus.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "ah enfin, j'avais peur que les Hermesile oublie de l'apporter.", 2);
                Personnages.tidus.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "je vais tous faire pour etre accepter. Je sais que je suis loin d'etre", 3);
                Personnages.tidus.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "aussi bon que Marcus mais je vais faire mon maximun.", 4);
                Habitants.mamanDeTidus.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "ca serai tellement bien que tu y aille. Ton pere", 5);
                Habitants.mamanDeTidus.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "n'y avons jamais ete. Mais bon mon ecole n'etait pas si mal apres tout.", 6);
                Personnages.tidus.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "(je dois tout faire pour reussir a entrer dans cette ecole, pour mes parents.)", 7);

                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.tidus);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);

                Evenements.dialogue2_lepreuve_chapitre1_blanc.initialise();
                Evenements.dialogue2_lepreuve_chapitre1_blanc.ajouterGens(Personnages.tidus);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_blanc, 
                        "Cette foret est dangereuse, c'est quoi cette epreuve de malade.", 1);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_blanc, 
                        "Je sais que cette ecole est la meilleur d'europe du Nord", 2);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_blanc, 
                        "Mais cette epreuve est vraiment dangereuse!", 3);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_blanc, 
                        "Tien? c'etait quoi se bruit?", 4);

                Evenements.dialogue3_lepreuve_chapitre1_blanc.initialise();
                Evenements.dialogue3_lepreuve_chapitre1_blanc.ajouterGens(Personnages.tidus);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue3_lepreuve_chapitre1_blanc, 
                        "ouf c'est pas passer loin.", 1);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue3_lepreuve_chapitre1_blanc, 
                        "C'est quoi cette araignee! Elle fait la moitie de ma taille", 2);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue3_lepreuve_chapitre1_blanc, 
                        "Mince! elle repasse a l'attaque", 3);

                Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu.initialise();
                Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu.ajouterGens(Personnages.tidus);
                Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu.ajouterGens(Personnages.marcus);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        "aie! ce combat risque d'etre difficile", 1);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        "cette araignee est vraiment forte!", 2);
                Personnages.marcus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        Personnages.tidus.getNom() + "! ca va?", 3);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        Personnages.marcus.getNom() + "! Que fait tu la?", 4);
                Personnages.marcus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        "Je vien t'aider, battons cette araignee ensemble", 5);
                break;
            case bleu :
                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.marcus);
                Personnages.marcus.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.marcus.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.marcus.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);
                
                Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu.initialise();
                Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu.ajouterGens(Personnages.tidus);
                Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu.ajouterGens(Personnages.marcus);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        "aie! ce combat risque d'etre difficile", 1);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        "cette araignee est vraiment forte!", 2);
                Personnages.marcus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        Personnages.tidus.getNom() + "! ca va?", 3);
                Personnages.tidus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        Personnages.marcus.getNom() + "! Que fait tu la?", 4);
                Personnages.marcus.addListeDialogueEvenement(Evenements.dialogue4_lepreuve_chapitre1_blanc_bleu, 
                        "Je vien t'aider, battons cette araignee ensemble", 5);
                break;
            case jaune :
                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.debut_lepreuve_chapitre1.initialise();
                Evenements.debut_lepreuve_chapitre1.ajouterGens(Personnages.fred);
                Personnages.fred.addListeDialogueEvenement(Evenements.debut_lepreuve_chapitre1, 
                        "cool j'ai recu la lettre de magicoli. allons y.", 1);

                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.fred);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);

                Evenements.dialogue2_lepreuve_chapitre1_jaune.initialise();
                Evenements.dialogue2_lepreuve_chapitre1_jaune.ajouterGens(Personnages.fred);
                Evenements.dialogue2_lepreuve_chapitre1_jaune.ajouterGens(Habitants.narrateur);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_jaune, 
                        "ah! ah! Jusqu'ici c'etait assez facile.", 1);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_jaune, 
                        "Bon je vais courir un peu pour arriver plus vite.", 2);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_jaune, 
                        "Arrrggg! Qu'est-ce que...?", 3);
                Habitants.narrateur.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_jaune, 
                        "Piege declanche! Rochers pret a etre envoye sur la cible", 4);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue2_lepreuve_chapitre1_jaune, 
                        "Oh! Oh! Il faut que j'esquive tous ces rochers", 5);

                Evenements.dialogue3_lepreuve_chapitre1_jaune.initialise();
                Evenements.dialogue3_lepreuve_chapitre1_jaune.ajouterGens(Personnages.fred);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue3_lepreuve_chapitre1_blanc, 
                        "Bon je crois que je devrai etre un peu plus prudent", 1);
                Personnages.fred.addListeDialogueEvenement(Evenements.dialogue3_lepreuve_chapitre1_blanc, 
                        "Bon je ferai bien de me depecher pour ne pas arrive trop tard", 2);
                break;
            case marron :
                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.olivier);
                Personnages.olivier.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.olivier.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.olivier.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);
                break;
            case noir :
                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.lex);
                Personnages.lex.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.lex.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.lex.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);
                break;
            case orange :
                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.zack);
                Personnages.zack.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.zack.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.zack.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);
                break;
            case rouge :
                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.mark);
                Personnages.mark.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.mark.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.mark.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);
                break;
            case vert :
                Evenements.dialogue1_lepreuve_chapitre1 = new EvenementDialogue(
                        new Position (Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent),null, null, "l'epreuve_4");
                Evenements.dialogue1_lepreuve_chapitre1.initialise();
                Evenements.dialogue1_lepreuve_chapitre1.ajouterGens(Personnages.drake);
                Personnages.drake.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hum! il n'y a que 40 places, il faut que je me", 1);
                Personnages.drake.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "depeche pour pouvoir etre accepter dans l'ecole", 2);
                Personnages.drake.addListeDialogueEvenement(Evenements.dialogue1_lepreuve_chapitre1, 
                        "Hein? qu'est-ce que c'est?", 3);
                break;
        }
        Evenements.discourDirecteur_lepreuve_chapitre1.initialise();
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Habitants.profMaxwel);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.lex);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.tidus);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.marcus);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.drake);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.zack);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.olivier);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.fred);
        Evenements.discourDirecteur_lepreuve_chapitre1.ajouterGens(Personnages.mark);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "Bonjour et bienvenu a toute les personnes ici presente", 1);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "je suis le professeur Maxwell Lucas Peter, je suis le directeur de l'ecole Magicoli", 2);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "et voici les autres professeur de l'ecole. Mais seul ce qui seront admis a l'ecole auront le privilege de les connaitres", 3);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "bien, nous allons maintenant parler de l'epreuve d'admission a l'ecole", 4);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "pour pouvoir etre admis a l'ecole, vous devez traverser cette foret et arriver dans les 40 premiers.", 5);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "il vous faudra passer quelque piege et combattre deux ou trois monstre", 6);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "pour cela on va vous apprendre deux sort simple, le sort rayon et le sort enchantement.", 7);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "il vous faudra faire preuve d'intelligence pour traverser cette foret,", 8);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "il ne suffit pas seulement de jeter un sort par ci par la.", 9);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "et vous devrez etre rapide, le nombre de personne admise est limite a 40", 10);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "Maintenant, vous pouvez allez voir le jury pour apprendre ces deux sorts", 11);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.discourDirecteur_lepreuve_chapitre1, 
                    "bonne chance. je retrouve les 40 meilleurs au bout du parcour", 12);

        Evenements.dialogueFin_lepreuve_chapitre1.initialise();
        Evenements.dialogueFin_lepreuve_chapitre1.ajouterGens(Habitants.profMaxwel);
        Evenements.dialogueFin_lepreuve_chapitre1.ajouterGens(Personnages.lex);
        Evenements.dialogueFin_lepreuve_chapitre1.ajouterGens(Personnages.yuna);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "Bien. Maintenant que tous le monde est la, nous allons pouvoir", 1);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "Vous explique comment va se derouler la rentree.", 2);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "les cours commence lundi. Vous avez encore ce week end pour vous reposer.", 3);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "vous recevrez aussi votre liste de fourniture et 2000 wizard", 4);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "il y a un village la-bas, vous y arrivrez en suivant le sentier.", 5);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "dans ce village, il y a tous ce dont vous aurez besoin pour vos 4 année d'etude", 6);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "pour ce qui resterons les 4 annee. HA! HA!", 7);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "donc il est inutil d'acheter des choses dont vous n'avez pas besoin", 8);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "car si vous n'avez pas assez d'argent pour acheter ce que demande vos professeur", 9);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "c'est a vos risque et peril, car vos parent n'ont pas le droit de vous envoyer de l'argent", 10);
        Personnages.lex.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, "hein?! pourquoi?", 11);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "comme ca tous le monde est a egalite, et ca vous apprend a gerer votre argent.", 12);
        Personnages.yuna.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "comment fait-on pour gagner de l'argent.", 13);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "en travaillant ou en gagnant des tournoi. Mais tous cela sera expliquer", 14);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "plus en detail plus tard au cour de l'annee.", 15);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "Bien. avant de vous laisser, je voudrai vous dire deux ou trois chose.", 16);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "la foret en face du college vous est interdite d'acces pour le moment.", 17);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "et il est interdit de se battre dans l'enceinte de l'ecole", 18);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "c'est tous pour le moment. prenez le train la bas pour arriver a Magicoli.", 19);
        Habitants.profMaxwel.addListeDialogueEvenement(Evenements.dialogueFin_lepreuve_chapitre1, 
                    "Vous recevrez vos liste de fourniture demain. Bonne rentree a tous.", 20);
    }

    private void creationEvenementCombat() {
        EvenementDialogue e1 = new EvenementDialogue();
        switch (this.capitaine.getEquipe()) {
            case blanc :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.tidus);
                Personnages.tidus.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogue(null, null, null);
                
                Evenements.combatAnim_lepreuve_chapitre1_blanc.initialise();
                ConditionAnimationCombat conditionAni1 = new ConditionAnimationCombat();
                conditionAni1.ajouterActionAttaquant(Ennemies.bebeAraignee, Action.attaquer);
                conditionAni1.ajouterActionAttaquer(Personnages.tidus, Action.esquive);
                Evenements.combatAnim_lepreuve_chapitre1_blanc.ajouterAnimations(conditionAni1);
                
                Evenements.combatAnim2_lepreuve_chapitre1_blanc.initialise();
                ConditionAnimationCombat conditionAni2 = new ConditionAnimationCombat();
                conditionAni2.ajouterActionAttaquant(Personnages.tidus, Action.sort, Sorts.rayon);
                conditionAni2.ajouterActionAttaquer(Ennemies.bebeAraignee, Action.esquive);
                ConditionAnimationCombat conditionAni3 = new ConditionAnimationCombat();
                conditionAni3.ajouterActionAttaquant(Ennemies.bebeAraignee, Action.attaquer);
                conditionAni3.ajouterActionAttaquer(Personnages.tidus, Action.aucun);
                Evenements.combatAnim2_lepreuve_chapitre1_blanc.ajouterAnimations(conditionAni2);
                Evenements.combatAnim2_lepreuve_chapitre1_blanc.ajouterAnimations(conditionAni3);
                
                Evenements.combat2_lepreuve_chapitre1_blanc_bleu.initialise();
                Evenements.combat2_lepreuve_chapitre1_blanc_bleu.getAdversaire().ajouterPerso(new Ennemie(Ennemies.bebeAraignee));
                break;
            case bleu :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.marcus);
                Personnages.marcus.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                break;
            case jaune :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.fred);
                Personnages.fred.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                break;
            case marron :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.olivier);
                Personnages.olivier.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                break;
            case noir :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.lex);
                Personnages.lex.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                break;
            case orange :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.zack);
                Personnages.zack.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                break;
            case rouge :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.mark);
                Personnages.mark.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                break;
            case vert :
                Evenements.combat1_lepreuve_chapitre1 = new EvenementCombat(
                        new Position (Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent), null, null, "l'epreuve_3");
                Evenements.combat1_lepreuve_chapitre1.initialise();
                e1.ajouterGens(Personnages.drake);
                Personnages.drake.addListeDialogueEvenement(e1, "Hors de mon chemin!", 1);
                Evenements.combat1_lepreuve_chapitre1.ajouterEvenementDialogueDebutCombat(e1);
                Evenements.combat1_lepreuve_chapitre1.getAdversaire().ajouterPerso(new Ennemie(Ennemies.gnome));
                break;
        }
        if (this.capitaine.getEquipe().equals(Equipe.blanc)) {
            
        }
    }

    private void creationEvenementChoix() {
    }
    
    private void creationEvenementAnimation() {
        Evenements.rocher_lepreuve_chapitre1_jaune.initialise();
        EvenementDialogue e1 = new EvenementDialogue();
        e1.ajouterGens(Habitants.narrateur);
        Habitants.narrateur.addListeDialogueEvenement(e1, "Pour esquiver les rochers, appuiyer sur la touche indiquer.", 1);
        Habitants.narrateur.addListeDialogueEvenement(e1, "g=gauche, d=droite, s=saut, b=se baisser, a=sort", 2);
        ConditionAnimation cdtAni = new ConditionAnimation();
        //cdtAni.setObjet(new ObjetEndroitClassique(Objets.rocher, new Position(3,7,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face)));
        cdtAni.setPos(new Position(5,7,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face));
        cdtAni.setKey('g');
        cdtAni.setActionToDo("gauche");
        cdtAni.setMessageFail("les rochers vous ont eu");
        cdtAni.setMessageSuccess("Vous avez esquivez le rocher");
        ConditionAnimation cdtAni1 = new ConditionAnimation();
        //cdtAni1.setObjet(new ObjetEndroitClassique(Objets.rocher, new Position(3,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face)));
        cdtAni1.setPos(new Position(5,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face));
        cdtAni1.setKey('s');
        cdtAni1.setActionToDo("saut");
        cdtAni1.setMessageFail("les rochers vous ont eu");
        cdtAni1.setMessageSuccess("Vous avez esquivez le rocher");
        ConditionAnimation cdtAni2 = new ConditionAnimation();
        //cdtAni2.setObjet(new ObjetEndroitClassique(Objets.rocher, new Position(3,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face)));
        cdtAni2.setPos(new Position(5,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face));
        cdtAni2.setKey('a');
        cdtAni2.setActionToDo("sort");
        cdtAni2.setMessageFail("les rochers vous ont eu");
        cdtAni2.setMessageSuccess("Vous avez esquivez le rocher");
        ConditionAnimation cdtAni3 = new ConditionAnimation();
        //cdtAni3.setObjet(new ObjetEndroitClassique(Objets.rocher, new Position(3,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face)));
        cdtAni3.setPos(new Position(5,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face));
        cdtAni3.setKey('d');
        cdtAni3.setActionToDo("droite");
        cdtAni3.setMessageFail("les rochers vous ont eu");
        cdtAni3.setMessageSuccess("Vous avez esquivez le rocher");
        ConditionAnimation cdtAni4 = new ConditionAnimation();
        //cdtAni4.setObjet(new ObjetEndroitClassique(Objets.rocher, new Position(3,7,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face)));
        cdtAni4.setPos(new Position(5,7,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face));
        cdtAni4.setKey('g');
        cdtAni4.setActionToDo("gauche");
        cdtAni4.setMessageFail("les rochers vous ont eu");
        cdtAni4.setMessageSuccess("Vous avez esquivez le rocher");
        ConditionAnimation cdtAni5 = new ConditionAnimation();
        //cdtAni5.setObjet(new ObjetEndroitClassique(Objets.rocher, new Position(3,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face)));
        cdtAni5.setPos(new Position(5,6,0,Endroits.chateau_quartierEcole_magicoli_zoneEcole_zoneMagicoli_continent, Orientation.face));
        cdtAni5.setKey('d');
        cdtAni5.setActionToDo("droite");
        cdtAni5.setMessageFail("les rochers vous ont eu");
        cdtAni5.setMessageSuccess("Vous avez esquivez le rocher");
        Evenements.rocher_lepreuve_chapitre1_jaune.ajouterEvenementDialogueDebutAnimation(e1);
        Evenements.rocher_lepreuve_chapitre1_jaune.ajouterAnimation(cdtAni);
        Evenements.rocher_lepreuve_chapitre1_jaune.ajouterAnimation(cdtAni1);
        Evenements.rocher_lepreuve_chapitre1_jaune.ajouterAnimation(cdtAni2);
        Evenements.rocher_lepreuve_chapitre1_jaune.ajouterAnimation(cdtAni3);
        Evenements.rocher_lepreuve_chapitre1_jaune.ajouterAnimation(cdtAni4);
        Evenements.rocher_lepreuve_chapitre1_jaune.ajouterAnimation(cdtAni5);
    }

    private void creationEvenementSpecial() {
        Evenements.sort_lepreuve_chapitre1.initialise();
        Evenements.sort_lepreuve_chapitre1.ajouterSortAajouter(Sorts.enchantement);
        Evenements.sort_lepreuve_chapitre1.ajouterSortAajouter(Sorts.rayon);
    }

    private void creationEvenementDeplacement() {
        switch (this.capitaine.getEquipe()) {
            case blanc :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
            case bleu :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_15_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
            case jaune :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
            case marron :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
            case noir :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
            case orange :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_16_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
            case rouge :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_14_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
            case vert :
                Evenements.entreeDansForet = new EvenementDeplacement(Endroits.entree_foret_zoneEcole_zoneMagicoli_continent, null, null, null, 
                        new Position(4, 5, 0, Endroits.niveau0_13_foret_zoneEcole_zoneMagicoli_continent, Orientation.dos), false);
                break;
        }
    }

    public EvenementQuete getEvenement() {
        return Evenements.jeu;
    }
    
}
