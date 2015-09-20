/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.reseau;

import mfiari.ecoledemagie.game.Global;
import mfiari.ecoledemagie.game.demarrage.CreationPerso;
import mfiari.ecoledemagie.game.extra.Duel;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.perso.Ennemie;
import mfiari.ecoledemagie.game.perso.Ennemies;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.perso.Personnages;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.lib.game.reseau.Client;
import mfiari.lib.game.reseau.EnvoiObjet;
import mfiari.lib.game.reseau.ReceveurObjet;
import mfiari.lib.game.reseau.Serveur;

/**
 *
 * @author mike
 */
public class MenuReseau extends ControlleurVue {
    
    public MenuReseau () {
        
    }
    
    public void menu() {
        try {
            CreationPerso creationPerso = new CreationPerso(new Duel());
            do {
                this.pcsControlleurVue.firePropertyChange("choixClientServeur", null, null);
                switch (choix) {
                    case 1 :
                        Serveur serveur = new Serveur();
                        serveur.lancerServeur(Global.port);
                        while (!serveur.clientArrive()) {
                            this.pcsControlleurVue.firePropertyChange("attenteJoueur", null, null);
                            this.attendre(1000);
                        }
                        Socket socket = serveur.getSocket();
                        ListeDePerso<Personnage> equipe = new ListeDePerso<>();
                        ListeDePerso<Ennemie> adversaire = new ListeDePerso<>();
                        Personnage p = Personnages.lex;
                        EnvoiObjet envoi = new EnvoiObjet(socket);
                        System.out.println("ok");
                        envoi.writeObject(p);
                        ReceveurObjet receveur = new ReceveurObjet(socket);
                        Personnage alie = (Personnage) receveur.readObject();
                        System.out.println("ok2");
                        equipe.ajouterPerso(p);
                        equipe.ajouterPerso(alie);
                        adversaire.ajouterPerso(Ennemies.bebeAraignee);
                        CombatServeur combat = new CombatServeur(socket, equipe, adversaire, p);
                        combat.commencerCombatDuelJ1VSOrdi();
                        try {
                            socket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(MenuReseau.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 2 :
                        Client client = new Client();
                        do {
                            this.pcsControlleurVue.firePropertyChange("rechercheHote", null, null);
                            this.attendre(1000);
                            client.lancerClient(Global.serveur, Global.port);
                        } while (!client.estConnecter());
                        Socket socket2 = client.getSocket();
                        ListeDePerso<Personnage> equipe2 = new ListeDePerso<>();
                        ListeDePerso<Ennemie> adversaire2 = new ListeDePerso<>();
                        Personnage p2 = Personnages.drake;
                        ReceveurObjet receveur2 = new ReceveurObjet(socket2);
                        System.out.println("ok");
                        Personnage alie2 = (Personnage) receveur2.readObject();
                        EnvoiObjet envoi2 = new EnvoiObjet(socket2);
                        envoi2.writeObject(p2);
                        System.out.println("ok2");
                        equipe2.ajouterPerso(alie2);
                        equipe2.ajouterPerso(p2);
                        adversaire2.ajouterPerso(Ennemies.bebeAraignee);
                        CombatClient combatClient = new CombatClient(socket2, equipe2, adversaire2, p2);
                        combatClient.commencerCombatDuelJ1VSOrdi();
                        try {
                            socket2.close();
                        } catch (IOException ex) {
                            Logger.getLogger(MenuReseau.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            } while (choix != 0);
        } catch (IOException ex) {
            Logger.getLogger(MenuReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
