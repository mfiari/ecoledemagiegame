/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.reseau;

import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.sort.Categories;
import mfiari.ecoledemagie.game.sort.Sort;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class CombatServeur extends CombatReseau {
    
    public CombatServeur (Socket socket, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire, Perso p) {
        super(socket, equipe, adversaire, p);
    }

    @Override
    protected int attaqueReussi(Perso attaquant, Perso attaquer) {
        try {
            // cette fonction permet a l'ennemi d'attaquer un de vos perso
            /*if (this.rate(attaquant)) {
                this.envoi.writeObject(true);
                this.setAffichage(this.textes.rateAttaque(attaquant.getNom()));
                return -1;
            }*/
            this.envoi.writeObject(false);
            /*if (this.esquive(attaquant, attaquer)) {
                this.envoi.writeObject(true);
                this.setAffichage(this.textes.esquiveAttaque(attaquer.getNom()));
                return -1;
            }*/
            this.envoi.writeObject(false);
        } catch (IOException ex) {
            Logger.getLogger(CombatServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public void commencerCombatDuelJ1VSOrdi() {
        try {
            int indice;
            Perso p = null;
            do {
                this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                //p = this.combat();
                //this.setAffichage(this.textes.auTourDe(p.getNom()));
                indice = tableau.get(tableau.size() - 1);
                this.envoi.writeObject(indice);
                if (indice < equipe.size()) {
                    Personnage perso = equipe.getPerso(indice);
                    if (perso.getPv() != 0 && adversaire.size() != 0) {
                        if (perso.equals(this.votrePerso)) {
                            do {
                                this.actionEffectue = false;
                                do {
                                    this.pcsControlleurVue.firePropertyChange("afficherMenuJoueur", null, null);
                                } while (this.choix < 1 || this.choix > 6);
                                combatPerso(perso, adversaire, equipe);
                            } while(!this.actionEffectue);
                        } else {
                            this.combatAllie(perso, adversaire, equipe);
                        }
                    }
                } else {
                    if (adversaire.contains(ennemieDExp.getPerso(indice - equipe.size())) && (!equipe.estKO())) {
                        Ennemie adv = ennemieDExp.getPerso(indice - equipe.size());
                        this.choixActionEnnemi(adv, equipe, adversaire);
                        AttaqueEnnemi(adv, equipe, adversaire);
                    }
                }
                if (this.tableau.size() == this.equipe.size() + this.ennemieDExp.size()) {
                    while (!tableau.isEmpty()) {
                        tableau.remove(0);
                    }
                }
            } while (adversaire.size() != 0 && !equipe.estKO() /*&& !tableau.isEmpty()*/);
        } catch (IOException ex) {
            Logger.getLogger(CombatServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // cette fonction permet de savoir lequel de vos personnage l'ennemi va attaquer
    @Override
    protected void AttaqueEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        try {
            int aleaTec = 0;
            int aleaEtat;
            int aleaEquipe;
            double degat = -1;
            int rate = -1;
            int nbPersoOK = 0;
            Personnage perso;
            Sort sort;
            do {
                switch (this.choix) {
                    case (1):
                        this.envoi.writeObject(this.choix);
                        this.envoi.writeObject(this.choixPerso);
                        perso = equipe.getPerso(this.choixPerso);
                        this.setAffichage(this.textes.attaque(ennemie.getNom(), perso.getNom()));
                        rate = this.attaqueReussi(ennemie, perso);
                        if (rate == 0) {
                            degat = this.degatAtt(ennemie, perso);
                            this.degat(perso, degat);
                            this.verifieKO(perso);
                        } else {
                            degat = 0;
                        }
                        break;
                    case (2) :
                        do {
                            choixTec = (int) (Math.random() * ennemie.getTec().size() + 1);
                            aleaTec = this.choixTec(ennemie);
                        } while (aleaTec == 0 && ennemie.getTec().size()!=0);
                        if (ennemie.getTec().size() !=0 ) { 
                            sort = ennemie.getTec().getSort(this.choixTec);
                            this.envoi.writeObject(this.choix);
                            this.envoi.writeObject(this.choixPerso);
                            this.envoi.writeObject(this.choixTec);
                            this.envoi.writeObject(aleaTec);
                            switch (aleaTec) {
                                case (1):
                                    perso = equipe.getPerso(this.choixPerso);
                                    this.setAffichage(this.textes.utiliseSur(ennemie.getNom(), sort.getNom(), perso.getNom()));
                                    ennemie.setPm(ennemie.getPm() - sort.getPmTec());
                                    rate = this.attaqueReussi(ennemie, perso);
                                    if (rate == 0) {
                                        degat = degatSort(ennemie, perso, sort);
                                        this.degat(perso, degat);
                                        if (sort.getCategorie().equals(Categories.specialU)) {
                                            aleaEtat = (int) (Math.random() * 20 + 1);
                                            this.envoi.writeObject(aleaEtat);
                                            if (aleaEtat == 1) {
                                                this.setAffichage(this.textes.etatAffecte);
                                                perso.setEtat(sort.getEtat());
                                            }
                                        }
                                        this.verifieKO(perso);
                                    }
                                    break;
                                case (2):
                                    this.setAffichage(this.textes.utilise(ennemie.getNom(), sort.getNom()));
                                    ennemie.setPm(ennemie.getPm() - sort.getPmTec());
                                    for (int i = 0; i < equipe.size(); i++) {
                                        if (equipe.getPerso(i).getPv() != 0) {
                                            nbPersoOK++;
                                        }
                                    }
                                    for (int j = 0; j < equipe.size(); j++) {
                                        perso = equipe.getPerso(j);
                                        if (!perso.estKO()) {
                                            rate = this.attaqueReussi(ennemie, perso);
                                            if (rate == 0) {
                                                degat = degatSort(ennemie, perso, sort);
                                                degat = degat / nbPersoOK;
                                                this.degat(perso, degat);
                                                if (sort.getCategorie().equals(Categories.specialE)) {
                                                    aleaEtat = (int) (Math.random() * 20 + 1);
                                                    this.envoi.writeObject(aleaEtat);
                                                    if (aleaEtat == 1) {
                                                        this.setAffichage(this.textes.etatAffecte);
                                                        perso.setEtat(sort.getEtat());
                                                    }
                                                }
                                                this.verifieKO(perso);
                                            }
                                        }
                                    }
                                    break;
                                case (3):
                                    aleaEquipe = (int) (Math.random() * adversaire.size() + 1);
                                    this.envoi.writeObject(aleaEquipe);
                                    this.setAffichage(this.textes.utiliseSur(ennemie.getNom(), sort.getNom(), adversaire.getPerso(aleaEquipe).getNom()));
                                    ennemie.setPm(ennemie.getPm() - sort.getPmTec());
                                    adversaire.getPerso(aleaEquipe - 1).recoitSoin(sort);
                                    break;
                                case (4):
                                    this.setAffichage(this.textes.utilise(ennemie.getNom(), sort.getNom()));
                                    ennemie.setPm(ennemie.getPm() - sort.getPmTec());
                                    for (int i = 0; i < adversaire.size(); i++) {
                                        adversaire.getPerso(i).recoitSoin(sort);
                                    }
                                    break;
                                case (5):
                                    this.setAffichage(this.textes.utilise(ennemie.getNom(), sort.getNom()));
                                    ennemie.setPm(ennemie.getPm() - sort.getPmTec());
                                    ennemie.recoitSoin(sort);
                                    break;
                                case (6):
                                    perso = equipe.getPerso(this.choixPerso);
                                    this.setAffichage(this.textes.utiliseSur(ennemie.getNom(), sort.getNom(), perso.getNom()));
                                    ennemie.setPm(ennemie.getPm() - sort.getPmTec());
                                    rate = this.attaqueReussi(ennemie, perso);
                                    if (rate == 0) {
                                        degat = degatSort(ennemie, perso, sort);
                                        this.degat(perso, degat);
                                        this.verifieKO(perso);
                                    }
                                    break;
                                default:
                                    this.setAffichage(this.textes.pasTechnique);
                                    this.choixActionEnnemi(ennemie, equipe, adversaire);
                                    break;
                            }
                        } else {
                            this.setAffichage(this.textes.pasTechnique);
                            this.choixActionEnnemi(ennemie, equipe, adversaire);
                        }
                        break;
                    case (3):
                        this.setAffichage(this.textes.pasObjet);
                        this.choixActionEnnemi(ennemie, equipe, adversaire);
                        /*l'ennemie utilise un objet*/
                        break;
                }
            } while (rate == -1 && degat == -1);
        } catch (IOException ex) {
            Logger.getLogger(CombatServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}