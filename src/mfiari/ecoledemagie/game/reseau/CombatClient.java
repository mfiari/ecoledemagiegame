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
public class CombatClient extends CombatReseau {
    
    public CombatClient (Socket socket, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire, Perso p) {
        super(socket, equipe, adversaire, p);
    }

    @Override
    protected int attaqueReussi(Perso attaquant, Perso attaquer) {
        try {
            // cette fonction permet a l'ennemi d'attaquer un de vos perso
            boolean rate = (Boolean)this.receveur.readObject();
            if (rate) {
                this.setAffichage(this.textes.rateAttaque(attaquant.getNom()));
                return -1;
            }
            boolean esquive = (Boolean)this.receveur.readObject();
            if (esquive) {
                this.setAffichage(this.textes.esquiveAttaque(attaquer.getNom()));
                return -1;
            }
        } catch (IOException ex) {
            Logger.getLogger(CombatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public void commencerCombatDuelJ1VSOrdi() {
        try {
            int indice;
            do {
                this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                indice = (Integer) this.receveur.readObject();
                if (indice < equipe.size()) {
                    Personnage perso = equipe.getPerso(indice);
                    this.setAffichage(this.textes.auTourDe(perso.getNom()));
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

                        }
                    }
                } else {
                    if (adversaire.contains(ennemieDExp.getPerso(indice - equipe.size())) && (!equipe.estKO())) {
                        Ennemie adv = ennemieDExp.getPerso(indice - equipe.size());
                        this.setAffichage(this.textes.auTourDe(adv.getNom()));
                        this.choixActionEnnemi(adv, equipe, adversaire);
                        AttaqueEnnemi(adv, equipe, adversaire);
                    }
                }
            } while (adversaire.size() != 0 && !equipe.estKO() /*&& !tableau.isEmpty()*/);
        } catch (IOException ex) {
            Logger.getLogger(CombatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // cette fonction permet de savoir lequel de vos personnage l'ennemi va attaquer
    @Override
    protected void AttaqueEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        try {
            int aleaTec;
            int aleaEtat;
            int aleaEquipe;
            double degat;
            int nbPersoOK = 0;
            Personnage perso;
            Sort sort;
            this.choix = (Integer) this.receveur.readObject();
            this.choixPerso = (Integer) this.receveur.readObject();
            switch (this.choix) {
                case (1):
                    perso = equipe.getPerso(this.choixPerso);
                    this.setAffichage(this.textes.attaque(ennemie.getNom(), perso.getNom()));
                    if (this.attaqueReussi(ennemie, perso) == 0) {
                        degat = this.degatAtt(ennemie, perso);
                        this.degat(perso, degat);
                        this.verifieKO(perso);
                    }
                    break;
                case (2) :
                    this.choixTec = (Integer) this.receveur.readObject();
                    aleaTec = (Integer) this.receveur.readObject();
                    sort = ennemie.getTec().getSort(this.choixTec);
                    switch (aleaTec) {
                        case (1):
                            perso = equipe.getPerso(this.choixPerso);
                            this.setAffichage(this.textes.utiliseSur(ennemie.getNom(), sort.getNom(), perso.getNom()));
                            ennemie.setPm(ennemie.getPm() - sort.getPmTec());
                            if (this.attaqueReussi(ennemie, perso) == 0) {
                                degat = degatSort(ennemie, perso, sort);
                                this.degat(perso, degat);
                                if (sort.getCategorie().equals(Categories.specialU)) {
                                    aleaEtat = (Integer) this.receveur.readObject();
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
                                    if (this.attaqueReussi(ennemie, perso) == 0) {
                                        degat = degatSort(ennemie, perso, sort);
                                        degat = degat / nbPersoOK;
                                        this.degat(perso, degat);
                                        if (sort.getCategorie().equals(Categories.specialE)) {
                                            aleaEtat = (Integer) this.receveur.readObject();
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
                            aleaEquipe = (Integer) this.receveur.readObject();
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
                            if (this.attaqueReussi(ennemie, perso) == 0) {
                                degat = degatSort(ennemie, perso, sort);
                                this.degat(perso, degat);
                                this.verifieKO(perso);
                            }
                            break;
                    }
                    break;
                case (3):
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(CombatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}