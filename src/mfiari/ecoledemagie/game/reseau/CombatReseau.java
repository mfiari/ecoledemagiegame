/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.reseau;

import mfiari.ecoledemagie.game.combat.AbstractCombat;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.objet.Objet;
import mfiari.ecoledemagie.game.objet.ObjetCombat;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.sort.Categories;
import mfiari.ecoledemagie.game.sort.Sort;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.lib.game.reseau.EnvoiObjet;
import mfiari.lib.game.reseau.ReceveurObjet;

/**
 *
 * @author mike
 */
public abstract class CombatReseau extends AbstractCombat {
    
    protected ReceveurObjet receveur;
    protected EnvoiObjet envoi;
    protected Perso votrePerso;
    
    public CombatReseau (Socket socket, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire, Perso p) {
        super(equipe, adversaire);
        this.envoi = new EnvoiObjet(socket);
        this.receveur = new ReceveurObjet(socket);
        this.votrePerso = p;
    }
    
    protected Object recoitObjet() {
        try {
            this.pcsControlleurVue.firePropertyChange("attenteAdv", null, null);
            return this.receveur.readObject();
        } catch (IOException ex) {
            Logger.getLogger(CombatReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public abstract void commencerCombatDuelJ1VSOrdi();

    
    protected abstract int attaqueReussi(Perso attaquant, Perso attaquer);

    @Override
    protected void choixActionEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire) {
        int alea;
        boolean att = false;
        while (!att) {
            alea = (int) (Math.random() * equipe.size() - 1);
            if (equipe.getPerso(alea).getPv() != 0) {
                att = true;
                this.choixPerso = alea;
            }
        }
        if (ennemie.elemental()) {
            this.choix = (int) (Math.random() * 10 + 1);
        } else {
            this.choix = (int) (Math.random() * 3 + 1);
        }
    }

    // cette fonction permet de savoir lequel de vos personnage l'ennemi va attaquer
    @Override
    protected abstract void AttaqueEnnemi(Ennemie ennemie, ListeDePerso<Personnage> equipe, ListeDePerso<Ennemie> adversaire);

    @Override
    protected void combatPerso(Personnage perso, ListeDePerso<Ennemie> adversaire, ListeDePerso<Personnage> equipe) {
        try {
            int b = 0;
            int c = 0;
            double degat = -1;
            int rate = 0;
            int esq = 0;
            int obs = 0;
            int def = 0;
            int aleaEtat;
            int aleaTec = 0;
            int aleaVole;
            boolean action = false;
            Ennemie adv;
            Sort sort;
            perso.setDefense(perso.getDefenseInitial());
            do {
                switch (this.choix) {
                    case (1):
                        do {
                            do {
                                this.pcsControlleurVue.firePropertyChange("afficherEnnemies", null, null);
                                b = this.choixPerso;
                            } while ((b <= 0 || b > adversaire.size()) && b != 6);
                            adv = adversaire.getPerso(b - 1);
                            if (b != 6 && b <= adversaire.size() && adv.getPv() != 0 && perso.getPv() != 0) {
                                this.envoi.writeObject(this.choix);
                                this.envoi.writeObject(b);
                                this.actionEffectue = true;
                                this.setAffichage(this.textes.attaque(perso.getNom(), adv.getNom()));
                                if (this.attaqueReussi(perso, adv) == 0) {
                                    action = true;
                                    degat = this.degatAtt(perso, adv);
                                    this.degat(adv, degat);
                                    if (this.verifieKO(adv)) {
                                        adv.soin();
                                        adversaire.enleverPerso(b - 1);
                                    }
                                }
                            }
                        } while (rate == -1 && degat == -1 && b != 6 && !action);
                        break;
                    case (2):
                        do {
                            do {
                                do {
                                    this.pcsControlleurVue.firePropertyChange("afficherTechniques", null, null);
                                    b = this.choixPerso;
                                } while (b < 0 || b > perso.getTec().size());
                                if (b != 0) {
                                    this.choixTec = b-1;
                                    aleaTec = this.choixTec(perso);
                                }
                            } while (aleaTec == 0 && b != 0);
                            sort = perso.getTec().getSort(b - 1);
                            switch (aleaTec) {
                                case (1):
                                    do {
                                        this.pcsControlleurVue.firePropertyChange("afficherEnnemies", null, null);
                                        c = this.choixPerso;
                                    } while ((c <= 0 || c > adversaire.size()) && c != 6);
                                    if (c!= 6) {
                                        this.envoi.writeObject(this.choix);
                                        this.envoi.writeObject(b);
                                        this.envoi.writeObject(aleaTec);
                                        this.envoi.writeObject(c);
                                        adv = adversaire.getPerso(c - 1);
                                        this.actionEffectue = true;
                                        this.setAffichage(this.textes.utiliseSur(perso.getNom(), sort.getNom(), adv.getNom()));
                                        perso.setPm(perso.getPm() - sort.getPmTec());
                                        if (this.attaqueReussi(perso, adv) == 0) {
                                            degat = degatSort(perso, adv, sort);
                                            this.degat(adv, degat);
                                            if (sort.getCategorie().equals(Categories.specialU)) {
                                                aleaEtat = (int) (Math.random() * 20 + 1);
                                                this.envoi.writeObject(aleaEtat);
                                                if (aleaEtat == 1) {
                                                    this.setAffichage(this.textes.etatAffecte);
                                                    adv.setEtat(sort.getEtat());
                                                }
                                            }
                                            if (this.verifieKO(adv)) {
                                                adv.soin();
                                                adversaire.enleverPerso(c - 1);
                                            }
                                        }
                                        sort.setNbUtil();
                                        action = true;
                                    }
                                    break;
                                case (2):
                                    do {
                                        this.pcsControlleurVue.firePropertyChange("afficherEnnemies", null, null);
                                        c = this.choixPerso;
                                    } while ((c <= 0 || c > adversaire.size()) && c != 6);
                                    if (c!= 6) {
                                        this.envoi.writeObject(this.choix);
                                        this.envoi.writeObject(b);
                                        this.envoi.writeObject(aleaTec);
                                        this.envoi.writeObject(c);
                                        this.actionEffectue = true;
                                        this.setAffichage(this.textes.utilise(perso.getNom(), sort.getNom()));
                                        perso.setPm(perso.getPm() - sort.getPmTec());
                                        for (int i = 0; i < adversaire.size(); i++) {
                                            adv = adversaire.getPerso(i);
                                            if (this.attaqueReussi(perso, adv) == 0) {
                                                degat = degatSort(perso, adv, sort);
                                                degat = degat / adversaire.size();
                                                this.degat(adv, degat);
                                                if (sort.getCategorie().equals(Categories.specialE)) {
                                                    aleaEtat = (int) (Math.random() * 20 + 1);
                                                    this.envoi.writeObject(aleaEtat);
                                                    if (aleaEtat == 1) {
                                                        this.setAffichage(this.textes.etatAffecte);
                                                        adv.setEtat(sort.getEtat());
                                                    }
                                                }
                                                if (this.verifieKO(adv)) {
                                                    adv.soin();
                                                    adversaire.enleverPerso(i);
                                                }
                                            }
                                        }
                                        sort.setNbUtil();
                                        action = true;
                                    }
                                    break;
                                case (3):
                                    do {
                                        this.affichage = this.textes.surQuiUtiliser;
                                        this.pcsControlleurVue.firePropertyChange("afficherEquipe", null, null);
                                        c = this.choixPerso;
                                    } while ((c <= 0 || c > equipe.size()) && c != 6);
                                    if (c!= 6) {
                                        this.envoi.writeObject(this.choix);
                                        this.envoi.writeObject(b);
                                        this.envoi.writeObject(aleaTec);
                                        this.envoi.writeObject(c);
                                        this.actionEffectue = true;
                                        this.setAffichage(this.textes.utiliseSur(perso.getNom(), sort.getNom(), equipe.getPerso(c - 1).getNom()));
                                        perso.setPm(perso.getPm() - sort.getPmTec());
                                        equipe.getPerso(c - 1).recoitSoin(sort, perso);
                                        sort.setNbUtil();
                                        action = true;
                                    }
                                    break;
                                case (4):
                                    this.envoi.writeObject(this.choix);
                                    this.envoi.writeObject(b);
                                    this.envoi.writeObject(aleaTec);
                                    this.envoi.writeObject(c);
                                    this.actionEffectue = true;
                                    this.setAffichage(this.textes.utilise(perso.getNom(), sort.getNom()));
                                    perso.setPm(perso.getPm() - sort.getPmTec());
                                    for (int i = 0; i < equipe.size(); i++) {
                                        equipe.getPerso(i).recoitSoin(sort, perso);
                                    }          
                                    sort.setNbUtil();
                                    action = true;
                                    break;
                                case (5):
                                    this.envoi.writeObject(this.choix);
                                    this.envoi.writeObject(b);
                                    this.envoi.writeObject(aleaTec);
                                    this.envoi.writeObject(c);
                                    this.actionEffectue = true;
                                    this.setAffichage(this.textes.utilise(perso.getNom(), sort.getNom()));
                                    perso.setPm(perso.getPm() - sort.getPmTec());
                                    perso.recoitSoin(sort, perso);          
                                    sort.setNbUtil();
                                    action = true;
                                    break;
                                case (6):
                                    do {
                                        this.pcsControlleurVue.firePropertyChange("afficherEnnemies", null, null);
                                        c = this.choixPerso;
                                    } while ((c <= 0 || c > adversaire.size()) && c != 6);
                                    if (c!= 6) {
                                        this.envoi.writeObject(this.choix);
                                        this.envoi.writeObject(b);
                                        this.envoi.writeObject(aleaTec);
                                        this.envoi.writeObject(c);
                                        this.actionEffectue = true;
                                        adv = adversaire.getPerso(c-1);
                                        this.setAffichage(this.textes.utiliseSur(perso.getNom(), sort.getNom(), adv.getNom()));
                                        perso.setPm(perso.getPm() - sort.getPmTec());
                                        if (this.attaqueReussi(perso, adv) == 0) {
                                            degat = degatSort(perso, adv, sort);
                                            this.degat(adv, degat);
                                            if (adv.vole()) {
                                                aleaVole = (int) (Math.random() * 10 + 1);
                                                this.envoi.writeObject(aleaVole);
                                                if (aleaVole > 5) {
                                                    this.setAffichage(this.textes.volRate);
                                                } else {
                                                    if (aleaVole == 1 || aleaVole == 2 || aleaVole == 4) {
                                                        if (adv.volObjetNul(aleaVole) == null) {
                                                            this.setAffichage(this.textes.volRate);
                                                        } else {
                                                            perso.getSac().acheter(perso.voleObjet(adv, aleaVole), 1);
                                                        }
                                                    } else {
                                                        if (adv.volEquipNul(aleaVole) == null) {
                                                            this.setAffichage(this.textes.volRate);
                                                        } else {
                                                            perso.voleEquip(adv,aleaVole);
                                                        }
                                                    }
                                                }
                                            } else {
                                                this.setAffichage(this.textes.rienAvoler);
                                            }
                                            if (this.verifieKO(adv)) {
                                                adv.soin();
                                                adversaire.enleverPerso(c - 1);
                                            }          
                                            sort.setNbUtil();
                                            action = true;
                                        }
                                    }
                                    break;
                            }
                        } while (b != 0 && rate == 0 && esq == 0 && degat == -1 && !action);
                        break;
                    case (3):
                        //cette fonction permet d'examiner un ennemi pendant un combat
                        do {
                           this.pcsControlleurVue.firePropertyChange("afficherEnnemies", null, null);
                           b = this.choixPerso;
                        } while (b <= 0 || b > 6);
                        adv = adversaire.getPerso(b-1);
                        if (b <= adversaire.size() && adv.getPv() != 0 && perso.getPv() != 0) {
                            this.envoi.writeObject(this.choix);
                            this.envoi.writeObject(b);
                            this.actionEffectue = true;
                            obs = 1;
                            action = true;
                            this.setAffichage(this.textes.examine(perso.getNom(), adv.getNom()));
                            /*this.setAffichage(adv.getNom() + "\n " + this.textes.pv + ":" + adv.getPv() + "/" + adv.getPvInitial()
                                    + "\n " + this.textes.pm + ":" + adv.getPm() + "/" + adv.getPmInitial());*/
                        }
                        break;
                    case (4):
                        do {
                            this.pcsControlleurVue.firePropertyChange("afficherObjetCombat", null, null);
                            b = this.choixPerso;
                            if (b > -1 && b <= perso.getSac().getSac(this.choixSac).size() && b != 0) {
                                Objet obj = perso.getSac().getObjet(this.choixSac, b - 1);
                                this.setAffichage(obj.toString());
                                do {
                                    this.affichage = this.textes.surQuiUtiliser;
                                    this.pcsControlleurVue.firePropertyChange("afficherEquipe", null, null);
                                    c = this.choixPerso;
                                } while (c > equipe.size() || c < 0);
                                if (c != 0) {
                                    this.envoi.writeObject(this.choix);
                                    this.envoi.writeObject(b);
                                    this.envoi.writeObject(c);
                                    this.envoi.writeObject(this.choixSac);
                                    this.actionEffectue = true;
                                    action = true;
                                    Personnage p = equipe.getPerso(c - 1);
                                    if (obj.getType().equals(Type_objet.soin_all)) {
                                        if (!p.enForme()) {
                                            this.setAffichage(this.textes.utiliseSur(perso.getNom(), obj.getNom(), p.getNom()));
                                            p.utilObjet((ObjetCombat)obj);
                                            perso.getSac().utilObjet(this.choixSac, b - 1);
                                        } else {
                                            this.setAffichage(this.textes.dejaEnForme(p.getNom()));
                                        }
                                    } else {
                                        if (obj.getType().equals(Type_objet.soin_stat)) {
                                            if (!p.enFormeStat()) {
                                                p.utilObjet((ObjetCombat)obj);
                                                perso.getSac().utilObjet(this.choixSac, b - 1);
                                                this.setAffichage(this.textes.utiliseSur(perso.getNom(), obj.getNom(), p.getNom()));
                                            } else {
                                                this.setAffichage(this.textes.dejaEnForme(p.getNom()));
                                            }
                                        }
                                        if (obj.getType().equals(Type_objet.soin_etat)) {
                                            if (!p.enFormeEtat()) {
                                                p.utilObjet((ObjetCombat)obj);
                                                perso.getSac().utilObjet(this.choixSac, b - 1);
                                                this.setAffichage(this.textes.utiliseSur(perso.getNom(), obj.getNom(), p.getNom()));
                                            } else {
                                                this.setAffichage(this.textes.dejaEnForme(p.getNom()));
                                            }
                                        }
                                    }
                                }
                            }
                            if (b==0) {
                                action=true;
                            }
                        } while (b != 0 && !action);
                        if (b==0) {
                            action=false;
                        }
                        break;
                    case (5):
                        this.envoi.writeObject(this.choix);
                        this.actionEffectue = true;
                        perso.seMetEnGarde();
                        this.setAffichage(this.textes.seMetEnGarde(perso.getNom()));
                        def = 1;
                        action = true;
                        break;
                    case (6):
                        do {
                            /*System.out.println(perso.getPerso());
                            System.out.println("0.quitter");
                            b = sc.nextInt();*/
                            b = 0;
                        } while (b != 0);
                        break;
                }
            } while (rate == 0 && obs == 0 && esq == 0 && degat == -1 && def == 0 && !action && this.actionEffectue);
        } catch (IOException ex) {
            Logger.getLogger(CombatReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void combatAllie (Personnage perso, ListeDePerso<Ennemie> adversaire, ListeDePerso<Personnage> equipe) {
        try {
            this.choix = (Integer) this.recoitObjet();
            Ennemie adv;
            double degat;
            int b;
            int c;
            Sort sort;
            int aleaTec;
            int aleaEtat;
            switch (this.choix) {
                case (1):
                    b = (Integer) this.recoitObjet();
                    adv = adversaire.getPerso(b - 1);
                    this.setAffichage(this.textes.attaque(perso.getNom(), adv.getNom()));
                    if (this.attaqueReussi(perso, adv) == 0) {
                        degat = this.degatAtt(perso, adv);
                        this.degat(adv, degat);
                        if (this.verifieKO(adv)) {
                            adv.soin();
                            adversaire.enleverPerso(b - 1);
                        }
                    }
                    break;
                case (2):
                    b = (Integer) this.receveur.readObject();
                    aleaTec = (Integer) this.receveur.readObject();
                    c = (Integer) this.receveur.readObject();
                    sort = perso.getTec().getSort(b - 1);
                    switch (aleaTec) {
                        case (1):
                            adv = adversaire.getPerso(c - 1);
                            this.setAffichage(this.textes.utiliseSur(perso.getNom(), sort.getNom(), adv.getNom()));
                            perso.setPm(perso.getPm() - sort.getPmTec());
                            if (this.attaqueReussi(perso, adv) == 0) {
                                degat = degatSort(perso, adv, sort);
                                this.degat(adv, degat);
                                if (sort.getCategorie().equals(Categories.specialU)) {
                                    aleaEtat = (Integer) this.receveur.readObject();
                                    if (aleaEtat == 1) {
                                        this.setAffichage(this.textes.etatAffecte);
                                        adv.setEtat(sort.getEtat());
                                    }
                                }
                                if (this.verifieKO(adv)) {
                                    adv.soin();
                                    adversaire.enleverPerso(c - 1);
                                }
                            }
                            sort.setNbUtil();
                            break;
                        case (2):
                            this.setAffichage(this.textes.utilise(perso.getNom(), sort.getNom()));
                            perso.setPm(perso.getPm() - sort.getPmTec());
                            for (int i = 0; i < adversaire.size(); i++) {
                                adv = adversaire.getPerso(i);
                                if (this.attaqueReussi(perso, adv) == 0) {
                                    degat = degatSort(perso, adv, sort);
                                    degat = degat / adversaire.size();
                                    this.degat(adv, degat);
                                    if (sort.getCategorie().equals(Categories.specialE)) {
                                        aleaEtat = (Integer) this.receveur.readObject();
                                        if (aleaEtat == 1) {
                                            this.setAffichage(this.textes.etatAffecte);
                                            adv.setEtat(sort.getEtat());
                                        }
                                    }
                                    if (this.verifieKO(adv)) {
                                        adv.soin();
                                        adversaire.enleverPerso(i);
                                    }
                                }
                            }
                            sort.setNbUtil();
                            break;
                        case (3):
                            this.setAffichage(this.textes.utiliseSur(perso.getNom(), sort.getNom(), equipe.getPerso(c - 1).getNom()));
                            perso.setPm(perso.getPm() - sort.getPmTec());
                            equipe.getPerso(c - 1).recoitSoin(sort, perso);
                            sort.setNbUtil();
                            break;
                        case (4):
                            this.setAffichage(this.textes.utilise(perso.getNom(), sort.getNom()));
                            perso.setPm(perso.getPm() - sort.getPmTec());
                            for (int i = 0; i < equipe.size(); i++) {
                                equipe.getPerso(i).recoitSoin(sort, perso);
                            }          
                            sort.setNbUtil();
                            break;
                        case (5):
                            this.setAffichage(this.textes.utilise(perso.getNom(), sort.getNom()));
                            perso.setPm(perso.getPm() - sort.getPmTec());
                            perso.recoitSoin(sort, perso);          
                            sort.setNbUtil();
                            break;
                        case (6):
                            adv = adversaire.getPerso(c-1);
                            this.setAffichage(this.textes.utiliseSur(perso.getNom(), sort.getNom(), adv.getNom()));
                            perso.setPm(perso.getPm() - sort.getPmTec());
                            if (this.attaqueReussi(perso, adv) == 0) {
                                degat = degatSort(perso, adv, sort);
                                this.degat(adv, degat);
                                if (adv.vole()) {
                                    int aleaVole = (Integer) this.receveur.readObject();
                                    if (aleaVole > 5) {
                                        this.setAffichage(this.textes.volRate);
                                    } else {
                                        if (aleaVole == 1 || aleaVole == 2 || aleaVole == 4) {
                                            if (adv.volObjetNul(aleaVole) == null) {
                                                this.setAffichage(this.textes.volRate);
                                            } else {
                                                perso.getSac().acheter(perso.voleObjet(adv, aleaVole), 1);
                                            }
                                        } else {
                                            if (adv.volEquipNul(aleaVole) == null) {
                                                this.setAffichage(this.textes.volRate);
                                            } else {
                                                perso.voleEquip(adv,aleaVole);
                                            }
                                        }
                                    }
                                } else {
                                    this.setAffichage(this.textes.rienAvoler);
                                }
                                if (this.verifieKO(adv)) {
                                    adv.soin();
                                    adversaire.enleverPerso(c - 1);
                                }          
                                sort.setNbUtil();
                            }
                            break;
                    }
                    break;
                case (3):
                    //cette fonction permet d'examiner un ennemi pendant un combat
                    b = (Integer) this.receveur.readObject();
                    adv = adversaire.getPerso(b-1);
                    this.setAffichage(this.textes.examine(perso.getNom(), adv.getNom()));
                    /*this.setAffichage(adv.getNom() + "\n " + this.textes.pv + ":" + adv.getPv() + "/" + adv.getPvInitial()
                            + "\n " + this.textes.pm + ":" + adv.getPm() + "/" + adv.getPmInitial());*/
                    break;
                case (4):
                    b = (Integer) this.receveur.readObject();
                    c = (Integer) this.receveur.readObject();
                    this.choixSac = (Integer) this.receveur.readObject();
                    Objet obj = perso.getSac().getObjet(this.choixSac, b - 1);
                    this.setAffichage(obj.toString());
                    Personnage p = equipe.getPerso(c - 1);
                    if (obj.getType().equals(Type_objet.soin_all)) {
                        if (!p.enForme()) {
                            this.setAffichage(this.textes.utiliseSur(perso.getNom(), obj.getNom(), p.getNom()));
                            p.utilObjet((ObjetCombat)obj);
                            perso.getSac().utilObjet(this.choixSac, b - 1);
                        } else {
                            this.setAffichage(this.textes.dejaEnForme(p.getNom()));
                        }
                    } else {
                        if (obj.getType().equals(Type_objet.soin_stat)) {
                            if (!p.enFormeStat()) {
                                p.utilObjet((ObjetCombat)obj);
                                perso.getSac().utilObjet(this.choixSac, b - 1);
                                this.setAffichage(this.textes.utiliseSur(perso.getNom(), obj.getNom(), p.getNom()));
                            } else {
                                this.setAffichage(this.textes.dejaEnForme(p.getNom()));
                            }
                        }
                        if (obj.getType().equals(Type_objet.soin_etat)) {
                            if (!p.enFormeEtat()) {
                                p.utilObjet((ObjetCombat)obj);
                                perso.getSac().utilObjet(this.choixSac, b - 1);
                                this.setAffichage(this.textes.utiliseSur(perso.getNom(), obj.getNom(), p.getNom()));
                            } else {
                                this.setAffichage(this.textes.dejaEnForme(p.getNom()));
                            }
                        }
                    }
                    break;
                case (5):
                    perso.seMetEnGarde();
                    this.setAffichage(this.textes.seMetEnGarde(perso.getNom()));
                    break;
                case (6):
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(CombatReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
