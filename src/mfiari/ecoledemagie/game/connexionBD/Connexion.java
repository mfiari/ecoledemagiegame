/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mfiari.ecoledemagie.game.connexionBD;

import mfiari.ecoledemagie.game.EcoleDeMagie;
import mfiari.ecoledemagie.game.liste.ListeDePerso;
import mfiari.ecoledemagie.game.liste.ListeDeSort;
import mfiari.ecoledemagie.game.menu.Stat;
import mfiari.ecoledemagie.game.objet.Sac;
import mfiari.ecoledemagie.game.objet.Type_objet;
import mfiari.ecoledemagie.game.perso.Perso;
import mfiari.ecoledemagie.game.perso.Personnage;
import mfiari.ecoledemagie.game.ville.Endroit;
import java.sql.*;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.liste.ListeObjet;

/**
 *
 * @author mike
 */
public class Connexion {
    
    private Connection conn;
    private EcoleDeMagie jeu;
    private boolean connectionActive;

    public Connexion() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String passwd = "sDKD8j43";
            conn = DriverManager.getConnection(url, user, passwd);
            this.connectionActive = true;
        } catch (ClassNotFoundException | SQLException e) {
            this.connectionActive = false;
            //e.printStackTrace();
            System.out.println("probleme connexion");
        }
    }

    public Connexion(EcoleDeMagie jeu) {
        this.jeu = jeu;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String passwd = "sDKD8j43";
            conn = DriverManager.getConnection(url, user, passwd);
            this.connectionActive = true;
        } catch (ClassNotFoundException | SQLException e) {
            this.connectionActive = false;
            //e.printStackTrace();
            System.out.println("probleme connexion");
        }
    }

    public int parties() {
        try {
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM ecoledemagie.parties order by partie");
            this.jeu.setResultSet(result);
            result.close();
            state.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("probleme affichage partie");
        }
        return this.jeu.getChoix();
    }

    public String getNonPersoPrincipale (int partie) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.personnages where id_partie=? order by id_personnage");
            prepare.setInt(1, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            if (result.next()) {
                return result.getString("nom");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("probleme get nom perso principale");
        }
        return "";
    }

    public void CreerPartie(int partie, Personnage persoPrincipale) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT COUNT(*) AS rowcount FROM ecoledemagie.parties where id_partie=?");
            prepare.setInt(1, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            if (result.next()) {
                System.out.println("suppression de la partie cree");
                Statement state1 = conn.createStatement();
                PreparedStatement prepare1 = conn.prepareStatement("SELECT * FROM ecoledemagie.evenements_chapitre where id_partie=?");
                prepare1.setInt(1, partie);
                ResultSet result1 = state1.executeQuery(prepare1.toString());
                while (result1.next()) {
                    Statement state2 = conn.createStatement();
                    PreparedStatement prepare2 = conn.prepareStatement("DELETE FROM ecoledemagie.quetes_annexe where id_evenement_chapitre=?");
                    prepare2.setInt(1, result1.getInt("id_evenement"));
                    state2.executeUpdate(prepare2.toString());
                    state2.close();
                    Statement state3 = conn.createStatement();
                    PreparedStatement prepare3 = conn.prepareStatement("SELECT * FROM ecoledemagie.evenements_quete where id_evenement_chapitre=?");
                    prepare3.setInt(1, result1.getInt("id_evenement"));
                    ResultSet result2 = state3.executeQuery(prepare1.toString());
                    while (result2.next()) {
                        Statement state4 = conn.createStatement();
                        PreparedStatement prepare4 = conn.prepareStatement("DELETE FROM ecoledemagie.quetes where id_evenement_quete=?");
                        prepare4.setInt(1, result2.getInt("id_evenement"));
                        state4.executeUpdate(prepare4.toString());
                        state4.close();
                    }
                    result2.close();
                    state3.close();
                    Statement state5 = conn.createStatement();
                    PreparedStatement prepare5 = conn.prepareStatement("DELETE FROM ecoledemagie.evenements_quete where id_evenement_chapitre=?");
                    prepare5.setInt(1, result1.getInt("id_evenement"));
                    state5.executeUpdate(prepare5.toString());
                    state5.close();
                }
                result1.close();
                state1.close();
                Statement state6 = conn.createStatement();
                PreparedStatement prepare6 = conn.prepareStatement("DELETE FROM ecoledemagie.evenements_chapitre where id_partie=?");
                prepare6.setInt(1, partie);
                state6.executeUpdate(prepare6.toString());
                state6.close();
                Statement state7 = conn.createStatement();
                PreparedStatement prepare7 = conn.prepareStatement("SELECT * FROM ecoledemagie.techniques where id_partie=?");
                prepare7.setInt(1, partie);
                ResultSet result3 = state7.executeQuery(prepare7.toString());
                while (result3.next()) {
                    Statement state8 = conn.createStatement();
                    PreparedStatement prepare8 = conn.prepareStatement("DELETE FROM ecoledemagie.technique_d_uni where id_technique=?");
                    prepare8.setInt(1, result3.getInt("id_technique"));
                    state8.executeUpdate(prepare8.toString());
                    state8.close();
                    Statement state9 = conn.createStatement();
                    PreparedStatement prepare9 = conn.prepareStatement("DELETE FROM ecoledemagie.technique_perso where id_technique=?");
                    prepare9.setInt(1, result3.getInt("id_technique"));
                    state9.executeUpdate(prepare9.toString());
                    state9.close();
                }
                result3.close();
                state7.close();
                Statement state10 = conn.createStatement();
                PreparedStatement prepare10 = conn.prepareStatement("DELETE FROM ecoledemagie.objets where id_partie=?");
                prepare10.setInt(1, partie);
                state10.executeUpdate(prepare10.toString());
                state10.close();
                Statement state11 = conn.createStatement();
                PreparedStatement prepare11 = conn.prepareStatement("DELETE FROM ecoledemagie.techniques where id_partie=?");
                prepare11.setInt(1, partie);
                state11.executeUpdate(prepare11.toString());
                state11.close();
                Statement state12 = conn.createStatement();
                PreparedStatement prepare12 = conn.prepareStatement("DELETE FROM ecoledemagie.d_uni where id_partie=?");
                prepare12.setInt(1, partie);
                state12.executeUpdate(prepare12.toString());
                state12.close();
                Statement state13 = conn.createStatement();
                PreparedStatement prepare13 = conn.prepareStatement("DELETE FROM ecoledemagie.personnages where id_partie=?");
                prepare13.setInt(1, partie);
                state13.executeUpdate(prepare13.toString());
                state13.close();
                Statement state14 = conn.createStatement();
                PreparedStatement prepare14 = conn.prepareStatement("DELETE FROM ecoledemagie.villes where id_partie=?");
                prepare14.setInt(1, partie);
                state14.executeUpdate(prepare14.toString());
                state14.close();
                Statement state15 = conn.createStatement();
                PreparedStatement prepare15 = conn.prepareStatement("DELETE FROM ecoledemagie.parties where id_partie=?");
                prepare15.setInt(1, partie);
                state15.executeUpdate(prepare15.toString());
                state15.close();
                Statement state16 = conn.createStatement();
                PreparedStatement prepare16 = conn.prepareStatement("insert into ecoledemagie.parties values(?,?,?,?,?,?)");
                prepare16.setInt(1, partie);
                prepare16.setString(2, "partie " + partie);
                prepare16.setString(3, persoPrincipale.getNom());
                prepare16.setInt(4, 0);
                prepare16.setInt(5, 0);
                prepare16.setInt(6, 0);
                state16.executeUpdate(prepare16.toString());
                prepare16.close();
                state16.close();
                result.close();
                state.close();
            } else {
                result.close();
                state.close();
                state = conn.createStatement();
                prepare = conn.prepareStatement("update ecoledemagie.parties set nom=?, tempsjeu_heure=?, tempsjeu_minute=?, tempsjeu_seconde=? "
                        + "where id_partie=?");
                prepare.setString(1, persoPrincipale.getNom());
                prepare.setInt(2, 0);
                prepare.setInt(3, 0);
                prepare.setInt(4, 0);
                prepare.setInt(5, partie);
                state.executeUpdate(prepare.toString());
                prepare.close();
                state.close();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("probleme creation partie");
        }
    }

    public void sauvegarderPartie(int partie, Personnage p, int temps_jeu_heure, int temps_jeu_minute, int temps_jeu_seconde) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement(
                    "update ecoledemagie.parties set nom=?, tempsjeu_heure=?, tempsjeu_minute=?, tempsjeu_seconde=? where id_partie=?");
            prepare.setString(1, p.getNom());
            prepare.setInt(2, temps_jeu_heure);
            prepare.setInt(3, temps_jeu_minute);
            prepare.setInt(4, temps_jeu_seconde);
            prepare.setInt(5, partie);
            state.executeUpdate(prepare.toString());
            prepare.close();
            state.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("probleme sauvegarde partie");
        }
    }

    public void enregistrerPerso(Personnage p, int partie, ListeDePerso equipe) {
        boolean estDansLEquipe = equipe.aPerso(p);
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("insert into ecoledemagie.personnages values( "
                    + "default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prepare.setString(1, p.getNom());
            prepare.setInt(4, p.getNiveau());
            prepare.setDouble(5, p.getPv());
            prepare.setDouble(6, p.getPm());
            prepare.setDouble(7, p.getForce());
            prepare.setDouble(8, p.getDefense());
            prepare.setDouble(9, p.getMagie());
            prepare.setInt(14, p.getExperience());
            /*prepare.setInt(15, p.getExpNivSup());
            prepare.setInt(16, p.getExpRestant());*/
            prepare.setInt(17, p.getPosition().getPositionX());
            prepare.setInt(18, p.getPosition().getPositionY());
            prepare.setInt(19, this.getIdVille(p.getPosition().getEndroit().getNom(), partie));
            prepare.setBoolean(20, estDansLEquipe);
            prepare.setInt(21, partie);
            state.executeUpdate(prepare.toString());
            prepare.close();
            state.close();
            this.sauvegarderTechnique(p, partie);
            this.sauvegarderEquipement(p, partie);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("probleme enregistement perso");
        }
    }

    public void sauvegarderPerso(Personnage p, int partie, ListeDePerso equipe) {
        boolean estDansLEquipe = equipe.aPerso(p);
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("update ecoledemagie.personnages set classe=?, niv=?, pv=?, pm=?, force=?, def=?, magie=?, "
                    + "res=?, vit=?, prec=?, agi=?, experience=?, exp_niv_suiv=?, exp_res=?, id_ville=?, equipe=?, positionx=?, positiony=? "
                    + "where nom=? and id_partie=?");
            prepare.setInt(2, p.getNiveau());
            prepare.setDouble(3, p.getPv());
            prepare.setDouble(4, p.getPm());
            prepare.setDouble(5, p.getForce());
            prepare.setDouble(6, p.getDefense());
            prepare.setDouble(7, p.getMagie());
            prepare.setInt(12, p.getExperience());
            /*prepare.setInt(13, p.getExpNivSup());
            prepare.setInt(14, p.getExpRestant());*/
            prepare.setInt(15, this.getIdVille(p.getPosition().getEndroit().getNom(), partie));
            prepare.setBoolean(16, estDansLEquipe);
            prepare.setInt(17, p.getPosition().getPositionX());
            prepare.setInt(18, p.getPosition().getPositionY());
            prepare.setString(19, p.getNom());
            prepare.setInt(20, partie);
            state.executeUpdate(prepare.toString());
            prepare.close();
            state.close();
            this.sauvegarderTechnique(p, partie);
            this.sauvegarderEquipement(p, partie);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("probleme sauvegarde perso");
        }
    }

    public Personnage chargerPerso(int partie, int id, ListeDePerso listeDePerso, ListeDeSort listeTec, ListeObjet sac, ListeDEndroit villes) {
        Personnage p = null;
        try {
            String nom;
            String capacite;
            String classe;
            int niv;
            double pv;
            int pm;
            int force;
            int def;
            int magie;
            int res;
            int vit;
            int prec;
            int agi;
            int exp;
            int expNivSup;
            int expRes;
            Type_objet main;
            Type_objet corps;
            Type_objet tete;
            Type_objet pied;
            Type_objet acc1;
            Type_objet acc2;
            ListeDeSort tec;
            ListeDeSort att;
            Stat stat;
            int positionX;
            int positionY;
            Endroit e;
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.personnages where id_partie=? and id_personnage=?");
            prepare.setInt(1, partie);
            prepare.setInt(2, id);
            ResultSet result = state.executeQuery(prepare.toString());
            while (result.next()) {
                nom = result.getString("nom");
                capacite = result.getString("capacite");
                classe = result.getString("classe");
                niv = result.getInt("niv");
                pv = result.getDouble("pv");
                pm = result.getInt("pm");
                force = result.getInt("force");
                def = result.getInt("def");
                magie = result.getInt("magie");
                res = result.getInt("res");
                vit = result.getInt("vit");
                prec = result.getInt("prec");
                agi = result.getInt("agi");
                exp = result.getInt("experience");
                expNivSup = result.getInt("exp_niv_suiv");
                expRes = result.getInt("exp_res");
                //stat = listeDePerso.getPersonnage(nom).getStat();
                tec = new ListeDeSort();
                att = new ListeDeSort();
                positionX = result.getInt("positionx");
                positionY = result.getInt("positiony");
                e = (Endroit)villes.getEndroit(this.getNomVille(result.getInt("id_ville"), partie));
                Statement state1 = conn.createStatement();
                PreparedStatement prepare1 = conn.prepareStatement("SELECT * FROM ecoledemagie.technique_perso where id_perso=?");
                prepare1.setInt(1, id);
                ResultSet result1 = state1.executeQuery(prepare1.toString());
                while (result1.next()) {
                    tec.ajoutSort(listeTec.getSort(this.getNomTechnique(result1.getInt("id_technique"), partie)));
                }
                p.getPosition().setPositionX(positionX);
                p.getPosition().setPositionY(positionY);
                p.getPosition().setEndroit(e);
                Statement state2 = conn.createStatement();
                PreparedStatement prepare2 = conn.prepareStatement("SELECT * FROM ecoledemagie.objet_perso where id_perso=?");
                prepare2.setInt(1, id);
                ResultSet result2 = state2.executeQuery(prepare2.toString());
                while (result2.next()) {
                    //p.equiper((Equip) sac.getObjet(this.getNomObjet(result2.getInt("id_objet"), partie)));
                }
            }
            result.close();
            state.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("echec chargement personnage");
        }
        return p;
    }
    
    public ListeDePerso chargerPersonnages(int partie, ListeDePerso listeDePerso, ListeDeSort listeTec, ListeObjet sac, ListeDEndroit villes) {
        ListeDePerso personnages = new ListeDePerso();
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.personnages where id_partie=?");
            prepare.setInt(1, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            while (result.next()) {
                personnages.ajouterPerso(this.chargerPerso(partie, result.getInt("id_personnage"), listeDePerso, listeTec, sac, villes));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("echec chargement personnages");
        }

        return personnages;
    }

    public ListeDePerso chargerEquipe(int partie, ListeDePerso listeDePerso, ListeDeSort listeTec, ListeObjet sac, ListeDEndroit villes) {
         ListeDePerso personnages = new ListeDePerso();
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.personnages where id_partie=? and equipe=TRUE");
            prepare.setInt(1, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            while (result.next()) {
                personnages.ajouterPerso(this.chargerPerso(partie, result.getInt("id_personnage"), listeDePerso, listeTec, sac, villes));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("echec chargement equipe");
        }
        return personnages;
    }

    public int getIdPerso (String nom, int partie) {
        return 0;
    }

    public void sauvegarderEvenement(EvenementQuete e, int partie) {
        int nbEvenement_chapitre;
        int i=0;
        int j=0;
        EvenementQuete even_chapitre = null;
        EvenementQuete even_quetes = null;
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT count(*) as rowcount, id_evenement  "
                    + "FROM ecoledemagie.evenements_chapitre where id_partie=? group by id_evenement");
            prepare.setInt(1, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            if (result.next()) {
                nbEvenement_chapitre = result.getInt("rowcount") -1;
            } else {
                nbEvenement_chapitre = -1;
            }
            if (nbEvenement_chapitre == -1) {
                Statement state1 = conn.createStatement();
                PreparedStatement prepare1 = conn.prepareStatement("insert into ecoledemagie.evenements_chapitre values(?,?,?,?)");
                prepare1.setInt(1, 1);
                prepare1.setString(2, e.getEvenement(nbEvenement_chapitre +1).toString());
                prepare1.setBoolean(3, e.estFini());
                prepare1.setInt(4, partie);
                state1.executeUpdate(prepare1.toString());
                prepare1.close();
                state1.close();
                nbEvenement_chapitre ++;
            }
            while (e.getEvenement(nbEvenement_chapitre).estFini()) {
                this.sauvegarderEvenementChapitre(nbEvenement_chapitre +1, partie, e);
                even_chapitre = (EvenementQuete ) e.getEvenement(nbEvenement_chapitre);
                while (even_chapitre.getEvenement(i) != null) {
                    this.sauvegarderEvenementQuete(i+1, nbEvenement_chapitre +1, partie, even_chapitre);
                    if (even_chapitre.getQueteAnnexe().estFini()) {
                        this.sauvegarderQueteAnnexe(i+1, nbEvenement_chapitre +1, partie, even_chapitre);
                    }
                    even_quetes = (EvenementQuete) even_chapitre.getEvenement(i);
                    while (even_quetes.getEvenement(j) != null) {
                        this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, partie, even_quetes.getEvenement(j));
                        j++;
                    }
                    i++;
                }
                nbEvenement_chapitre++;
            }
            this.sauvegarderEvenementChapitre(nbEvenement_chapitre +1, partie, e);
            if (even_chapitre == null) {
                even_chapitre = (EvenementQuete ) e.getEvenement(nbEvenement_chapitre);
                while (even_chapitre.getEvenement(i).estFini()) {
                    this.sauvegarderEvenementQuete(i+1, nbEvenement_chapitre +1, partie, even_chapitre);
                    if (even_chapitre.getQueteAnnexe().estFini()) {
                        this.sauvegarderQueteAnnexe(i+1, nbEvenement_chapitre +1, partie, even_chapitre);
                    }
                    even_quetes = (EvenementQuete ) even_chapitre.getEvenement(i);
                    while (even_quetes.getEvenement(j) != null) {
                        this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, partie, even_quetes.getEvenement(j));
                        j++;
                    }
                    i++;
                }
                this.sauvegarderEvenementQuete(i+1, nbEvenement_chapitre +1, partie, even_chapitre);
                if (even_quetes == null) {
                    even_quetes = (EvenementQuete ) even_chapitre.getEvenement(i);
                    while (even_quetes.getEvenement(j).estFini()) {
                        this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, partie, even_quetes.getEvenement(j));
                        j++;
                    }
                    this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, partie, even_quetes.getEvenement(j));
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme sauvegarde evenement");
        }
    }

    public void sauvegarderEvenementChapitre (int id, int partie, EvenementQuete e) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.evenements_chapitre where id_evenement=?");
            prepare.setInt(1, id);
            ResultSet result = state.executeQuery(prepare.toString());
            if (!result.next()) {
                Statement state1 = conn.createStatement();
                PreparedStatement prepare1 = conn.prepareStatement("insert into ecoledemagie.evenements_chapitre values(?,?,?,?)");
                prepare1.setInt(1, id);
                prepare1.setString(2, e.getEvenement(id-1).toString());
                prepare1.setBoolean(3, e.estFini());
                prepare1.setInt(4, partie);
                state1.executeUpdate(prepare1.toString());
                prepare1.close();
                state1.close();
            } else {
                if (!result.getBoolean("fini") && e.estFini()) {
                    Statement state2 = conn.createStatement();
                    PreparedStatement prepare2 = conn.prepareStatement("update ecoledemagie.evenements_chapitre set fini=? where id_evenement=?");
                    prepare2.setBoolean(1, true);
                    prepare2.setInt(2, id);
                    state2.executeUpdate(prepare2.toString());
                    prepare2.close();
                    state2.close();
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme sauvegarde evenement chapitre");
        }
    }

    public void sauvegarderEvenementQuete (int id, int id_chapitre, int partie, EvenementQuete e) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.evenements_quete where id_evenement=? "
                    + "and id_evenement_chapitre=? and id_partie=?");
            prepare.setInt(1, id);
            prepare.setInt(2, id_chapitre);
            prepare.setInt(3, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            if (!result.next()) {
                Statement state1 = conn.createStatement();
                PreparedStatement prepare1 = conn.prepareStatement("insert into ecoledemagie.evenements_quete values(?,?,?,?,?)");
                prepare1.setInt(1, id);
                prepare1.setString(2, e.getEvenement(id-1).toString());
                prepare1.setBoolean(3, e.estFini());
                prepare1.setInt(4, id_chapitre);
                prepare1.setInt(5, partie);
                state1.executeUpdate(prepare1.toString());
                prepare1.close();
                state1.close();
            } else {
                if (!result.getBoolean("fini") && e.estFini()) {
                    Statement state2 = conn.createStatement();
                    PreparedStatement prepare2 = conn.prepareStatement("update ecoledemagie.evenements_quete set fini=? where id_evenement=? "
                            + "and id_evenement_chapitre=? and id_partie=?");
                    prepare2.setBoolean(1, true);
                    prepare2.setInt(2, id);
                    prepare2.setInt(3, id_chapitre);
                    prepare2.setInt(4, partie);
                    state2.executeUpdate(prepare2.toString());
                    prepare2.close();
                    state2.close();
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme sauvegarde evenement quete");
        }
    }

    public void sauvegarderQueteAnnexe (int id, int id_chapitre, int partie, EvenementQuete e) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.quetes_annexe where id_evenement=? and id_chapitre=? and"
                    + " id_partie=?");
            prepare.setInt(1, id);
            prepare.setInt(2, id_chapitre);
            prepare.setInt(3, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            if (!result.next()) {
                Statement state1 = conn.createStatement();
                PreparedStatement prepare1 = conn.prepareStatement("insert into ecoledemagie.quetes_annexe values(?,?,?,?,?,?)");
                prepare1.setInt(1, id);
                prepare1.setString(2, e.getQueteAnnexe().toString());
                prepare1.setBoolean(3, e.estActiver(null));
                prepare1.setBoolean(4, e.estFini());
                prepare1.setInt(5, id_chapitre);
                prepare1.setInt(6, partie);
                state1.executeUpdate(prepare1.toString());
                prepare1.close();
                state1.close();
            } else {
                if ((!result.getBoolean("fini") && e.estFini()) || (!result.getBoolean("actif") && e.estActiver(null))) {
                    Statement state2 = conn.createStatement();
                    PreparedStatement prepare2 = conn.prepareStatement("update ecoledemagie.quetes_annexe set fini=?, actif=? where id_evenement=? and "
                            + "id_chapitre=? and id_partie=?");
                    prepare2.setBoolean(1, e.estFini());
                    prepare2.setBoolean(2, e.estActiver(null));
                    prepare2.setInt(3, id);
                    prepare2.setInt(4, id_chapitre);
                    prepare2.setInt(5, partie);
                    state2.executeUpdate(prepare2.toString());
                    prepare2.close();
                    state2.close();
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme sauvegarde quete annexe");
        }
    }

    public void sauvegarderQuetes (int id, int id_quete, int id_chapitre, int partie, Evenement e) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.quetes where id_quete=? and id_evenement_quete=? and "
                    + "id_evenement_chapitre=? and id_partie=?");
            prepare.setInt(1, id);
            prepare.setInt(2, id_quete);
            prepare.setInt(3, id_chapitre);
            prepare.setInt(4, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            if (!result.next()) {
                Statement state1 = conn.createStatement();
                PreparedStatement prepare1 = conn.prepareStatement("insert into ecoledemagie.quetes values(?,?,?,?,?,?)");
                prepare1.setInt(1, id);
                prepare1.setString(2, e.toString());
                prepare1.setBoolean(3, e.estFini());
                prepare1.setInt(4, id_quete);
                prepare1.setInt(5, id_chapitre);
                prepare1.setInt(6, partie);
                state1.executeUpdate(prepare1.toString());
                prepare1.close();
                state1.close();
            } else {
                if (!result.getBoolean("fini") && e.estFini()) {
                    Statement state2 = conn.createStatement();
                    PreparedStatement prepare2 = conn.prepareStatement("update ecoledemagie.quetes set fini=? where id_quete=? and "
                            + "id_evenement_quete=? and id_evenement_chapitre=? and id_partie=?");
                    prepare2.setBoolean(1, e.estFini());
                    prepare2.setInt(2, id);
                    prepare2.setInt(3, id_quete);
                    prepare2.setInt(4, id_chapitre);
                    prepare2.setInt(5, partie);
                    state2.executeUpdate(prepare2.toString());
                    prepare2.close();
                    state2.close();
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme sauvegarde quetes");
        }
    }

    public int chargerEvenementChapitre(int partie) {
        ResultSet result;
        try {
            Statement state2 = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.evenements_chapitre where id_partie=? and fini=?");
            prepare.setInt(1, partie);
            prepare.setBoolean(2, false);
            result = state2.executeQuery(prepare.toString());
            if (result.next()) {
                return result.getInt("id_evenement");
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme chargement evenement chapitre");
        }
        return 0;
    }

    public int chargerEvenementQuete(int id_chapitre, int partie) {
        ResultSet result;
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.evenements_quete where id_evenement_chapitre=? and id_partie=? "
                    + "and fini=?");
            prepare.setInt(1, id_chapitre);
            prepare.setInt(2, partie);
            prepare.setBoolean(3, false);
            result = state.executeQuery(prepare.toString());
            if(result.next()) {
                return result.getInt("id_evenement");
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme chargement evenement quete");
        }
        return 0;
    }

    public int chargerQuete(int id_quete, int id_chapitre, int partie) {
        ResultSet result;
        try {
            Statement state2 = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.quetes where id_evenement_quete =? and id_evenement_chapitre=? "
                    + "and id_partie=? and fini=?");
            prepare.setInt(1, id_quete);
            prepare.setInt(2, id_chapitre);
            prepare.setInt(3, partie);
            prepare.setBoolean(4, false);
            result = state2.executeQuery(prepare.toString());
            if(result.next()) {
                return result.getInt("id_quete");
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme chargement quete");
        }
        return 0;
    }

    public int chargerQueteAnnexe(int id_chapitre, int partie) {
        ResultSet result;
        try {
            Statement state2 = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.quetes_annexe where id_evenement_chapitre =? and id_partie=? "
                    + "and fini=? and actif=?");
            prepare.setInt(1, id_chapitre);
            prepare.setInt(2, partie);
            prepare.setBoolean(3, false);
            prepare.setBoolean(4, true);
            result = state2.executeQuery(prepare.toString());
            if(result.next()) {
                return result.getInt("id_evenement");
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme chargement quete annexe");
        }
        return 0;
    }

    public void enregistrerVille(Endroit e, int partie) {
        /*on enregistre les villes déja visité*/
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("insert into ecoledemagie.villes values(default,?,?,?)");
            prepare.setString(1, e.getNom());
            prepare.setBoolean(2, false);
            prepare.setInt(3, partie);
            state.executeUpdate(prepare.toString());
            prepare.close();
            state.close();
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme enregistrement ville");
        }
    }

    public void sauvegarderVille(ListeDEndroit carte, int partie) {
        /*on sauvegarde les villes déja visité*/
        try {
            for (int i = 0; i < carte.size(); i++) {
                Statement state = conn.createStatement();
                PreparedStatement prepare = conn.prepareStatement("SELECT * from ecoledemagie.villes where nom=? and id_partie=? and visite=?");
                prepare.setString(1, carte.getEndroit(i).getNom());
                prepare.setInt(2, partie);
                prepare.setBoolean(3, true);
                ResultSet result = state.executeQuery(prepare.toString());
                if (!result.next()) {
                    this.enregistrerVille(carte.getEndroit(i), partie);
                } else {
                    Statement state2 = conn.createStatement();
                    PreparedStatement prepare2 = conn.prepareStatement("update ecoledemagie.villes set visite=? where nom=? and id_partie=?");
                    prepare2.setBoolean(1, true);
                    prepare2.setString(2, carte.getEndroit(i).getNom());
                    prepare2.setInt(3, partie);
                    state2.executeUpdate(prepare2.toString());
                    prepare2.close();
                    state2.close();
                }
                prepare.close();
                state.close();
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme sauvegarde ville");
        }
    }

    public int getIdVille (String nom, int partie) {
        ResultSet result;
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.villes where nom=? and id_partie=?");
            prepare.setString(1, nom);
            prepare.setInt(2, partie);
            result = state.executeQuery(prepare.toString());
            if (result.next()) {
                return result.getInt("id_ville");
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme get id ville");
        }
        return 0;
    }

    public String getNomVille (int id, int partie) {
        ResultSet result;
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.villes where id_ville=? and id_partie=?");
            prepare.setInt(1, id);
            prepare.setInt(2, partie);
            result = state.executeQuery(prepare.toString());
            if(result.next()) {
                return result.getString("nom");
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme get nom ville");
        }
        return "";
    }

    public ListeDEndroit chargerEndroit(ListeDEndroit villes, int partie) {
        ListeDEndroit endroits = new ListeDEndroit();
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT *from ecoledemagie.villes where id_partie=? and visite=?");
            prepare.setInt(1, partie);
            prepare.setBoolean(2, true);
            ResultSet result = state.executeQuery(prepare.toString());
            while (result.next()) {
                //endroits.add(villes.getEndroit(result.getString("nom")));
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("probleme chargement endroit");
        }
        return endroits;
    }

    public void enregistrerTechnique(ListeDeSort tecs, int partie) {
        for (int i = 0; i < tecs.size(); i++) {
            try {
                Statement state = conn.createStatement();
                PreparedStatement prepare = conn.prepareStatement("insert into ecoledemagie.techniques values(default,?,?)");
                prepare.setString(1, tecs.getSort(i).getNom());
                prepare.setInt(2, partie);
                state.executeUpdate(prepare.toString());
                prepare.close();
                state.close();
            } catch (Exception ex) {
                //e.printStackTrace();
                System.out.println("probleme enregistrement technique");
            }
        }
    }

    public void enregistrerTechniquePerso(int id_tec, int id_perso) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("insert into ecoledemagie.technique_perso values(?,?)");
            prepare.setInt(1, id_tec);
            prepare.setInt(2, id_perso);
            state.executeUpdate(prepare.toString());
            prepare.close();
            state.close();
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme enregistrement technique perso");
        }
    }

    public void enregistrerTechniqueD_UNI(int id_tec, int id_perso) {
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("insert into ecoledemagie.technique_d_uni values(?,?)");
            prepare.setInt(1, id_tec);
            prepare.setInt(2, id_perso);
            state.executeUpdate(prepare.toString());
            prepare.close();
            state.close();
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme enregistrement technique d_uni");
        }
    }

    public void sauvegarderTechnique(Perso p, int partie) {
        for (int i = 0; i < p.getTec().size(); i++) {
            try {
                Statement state = conn.createStatement();
                PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.techniques where nom=? and id_partie =?");
                prepare.setString(1, p.getTec().getSort(i).getNom());
                prepare.setInt(2, partie);
                ResultSet result = state.executeQuery(prepare.toString());
                if (result.next()) {
                    Statement state1 = conn.createStatement();
                    PreparedStatement prepare1 = conn.prepareStatement("SELECT * FROM ecoledemagie.personnages where nom=? and id_partie =?");
                    prepare1.setString(1, p.getNom());
                    prepare1.setInt(2, partie);
                    ResultSet result1 = state1.executeQuery(prepare1.toString());
                    if (result1.next()) {
                        Statement state2 = conn.createStatement();
                        PreparedStatement prepare2 = conn.prepareStatement("SELECT * FROM ecoledemagie.technique_perso where id_technique=? "
                                + "and id_perso =?");
                        prepare2.setInt(1, result.getInt("id_technique"));
                        prepare2.setInt(2, result1.getInt("id_personnage"));
                        ResultSet result2 = state2.executeQuery(prepare2.toString());
                        if (!result2.next()) {
                            this.enregistrerTechniquePerso(result.getInt("id_technique"), result1.getInt("id_personnage"));
                        }
                    } else {
                        Statement state3 = conn.createStatement();
                        PreparedStatement prepare3 = conn.prepareStatement("SELECT * FROM ecoledemagie.d_uni where nom=? and id_partie =?");
                        prepare3.setString(1, p.getNom());
                        prepare3.setInt(2, partie);
                        ResultSet result3 = state3.executeQuery(prepare3.toString());
                        if (result3.next()) {
                            Statement state4 = conn.createStatement();
                            PreparedStatement prepare4 = conn.prepareStatement("SELECT * FROM ecoledemagie.technique_d_uni where id_technique=? "
                                    + "and id_perso=?");
                            prepare4.setInt(1, result.getInt("id_technique"));
                            prepare4.setInt(2, result3.getInt("id_d_uni"));
                            ResultSet result4 = state4.executeQuery(prepare4.toString());
                            if (!result4.next()) {
                                this.enregistrerTechniqueD_UNI(result.getInt("id_technique"), result3.getInt("id_d_uni"));
                            }
                        }
                    }
                }
                prepare.close();
                result.close();
                state.close();
            } catch (Exception ex) {
                //ex.printStackTrace();
                System.out.println("probleme sauvegarde technique");
            }
        }
    }

    public String getNomTechnique (int id, int partie) {
        ResultSet result;
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.techniques where id_technique=? and id_partie=?");
            prepare.setInt(1, id);
            prepare.setInt(2, partie);
            result = state.executeQuery(prepare.toString());
            if (result.next()) {
                return result.getString("nom");
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme get nom technique");
        }
        return "";
    }

    public void enregistrerEquipement(Personnage p, int partie/*, Equip e*/) {
        Statement state;
        PreparedStatement prepare;
        try {
            state = conn.createStatement();
            prepare = conn.prepareStatement("select * from ecoledemagie.objets where nom=? and id_partie=?");
            //prepare.setString(1, e.getNom());
            prepare.setInt(1, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            if (result.next()) {
                state = conn.createStatement();
                prepare = conn.prepareStatement("select * from ecoledemagie.personnages where nom=? and id_partie=?");
                prepare.setString(1, p.getNom());
                prepare.setInt(2, partie);
                ResultSet result1 = state.executeQuery(prepare.toString());
                if (result1.next()) {
                    state = conn.createStatement();
                    prepare = conn.prepareStatement("insert into ecoledemagie.objet_perso values(?,?)");
                    prepare.setInt(1, result.getInt("id_objet"));
                    prepare.setInt(2, result1.getInt("id_personnage"));
                    state.executeUpdate(prepare.toString());
                }
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            //System.out.println("probleme enregistrement equipement " + e.getCategorie().name());
        }
    }

    public void sauvegarderEquipement(Personnage p, int partie) {
        Statement state;
        PreparedStatement prepare;
        try {
            state = conn.createStatement();
            prepare = conn.prepareStatement("select * from ecoledemagie.personnages where nom=? and id_partie=?");
            prepare.setString(1, p.getNom());
            prepare.setInt(2, partie);
            ResultSet result1 = state.executeQuery(prepare.toString());
            if (result1.next()) {
                state = conn.createStatement();
                prepare = conn.prepareStatement("delete from ecoledemagie.objet_perso where id_perso=?");
                prepare.setInt(1, result1.getInt("id_personnage"));
                state.executeUpdate(prepare.toString());
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme sauvegarde equipement");
        }
        /*if (p.estEquipMain()) {
            this.enregistrerEquipement(p, partie, p.getArme());
        }
        if (p.estEquipCorps()) {
            this.enregistrerEquipement(p, partie, p.getArmure());
        }
        if (p.estEquipTete()) {
            this.enregistrerEquipement(p, partie, p.getChapeau());
        }
        if (p.estEquipPied()) {
            this.enregistrerEquipement(p, partie, p.getBotte());
        }
        if (p.estEquipAcc1()) {
            this.enregistrerEquipement(p, partie, p.getAcc1());
        }
        if (p.estEquipAcc2()) {
            this.enregistrerEquipement(p, partie, p.getAcc2());
        }*/
    }

    public void sauvegarderObjet(ListeObjet sac, int partie) {
        for (int i = 0; i < sac.size(); i++) {
            try {
                Statement state = conn.createStatement();
                PreparedStatement prepare = conn.prepareStatement("update ecoledemagie.objets set quantite=? where id_partie=?");
                prepare.setInt(1, 0);
                prepare.setInt(2, partie);
                state.executeUpdate(prepare.toString());
                state = conn.createStatement();
                prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.objets where nom= ? and id_partie=?");
                prepare.setString(1, sac.getObjet(i).getNom());
                prepare.setInt(2, partie);
                ResultSet result = state.executeQuery(prepare.toString());
                if (!result.next()) {
                    state = conn.createStatement();
                    prepare = conn.prepareStatement("insert into ecoledemagie.objets values(default,?,?,?)");
                    prepare.setString(1, sac.getObjet(i).getNom());
                    //prepare.setInt(2, sac.getObjet(i).getQuantite());
                    prepare.setInt(3, partie);
                    state.executeUpdate(prepare.toString());
                } else {
                    state = conn.createStatement();
                    prepare = conn.prepareStatement("update ecoledemagie.objets set quantite=? where id_partie=? and nom=?");
                    //prepare.setInt(1, sac.getObjet(i).getQuantite());
                    prepare.setInt(2, partie);
                    prepare.setString(3, sac.getObjet(i).getNom());
                    state.executeUpdate(prepare.toString());
                }
            } catch (Exception ex) {
                //ex.printStackTrace();
                System.out.println("probleme sauvegarde objet");
            }
        }
    }

    public Sac chargerObjet(ListeObjet objets, int partie) {
        Sac sac = new Sac();
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM ecoledemagie.objets where id_partie=? and quantite>0");
            prepare.setInt(1, partie);
            ResultSet result = state.executeQuery(prepare.toString());
            while (result.next()) {
                //sac.ajoutObjet(objets.getObjet(result.getString("nom")));
            }
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme chargement objet");
        }
        return sac;
    }

    public String getNomObjet (int id, int partie) {
        ResultSet result;
        try {
            Statement state = conn.createStatement();
            PreparedStatement prepare = conn.prepareStatement("select * from ecoledemagie.objets where id_objet=? and id_partie=?");
            prepare.setInt(1, id);
            prepare.setInt(2, partie);
            result = state.executeQuery(prepare.toString());
            prepare.close();
            state.close();
            return result.getString("nom");
        } catch (Exception ex) {
            //e.printStackTrace();
            System.out.println("probleme get nom objet");
        }
        return "";
    }

    private void enregistrerVille(Object get, int partie) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public boolean connectionActive () {
        return this.connectionActive;
    }
}
