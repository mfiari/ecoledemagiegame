/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.connexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import mfiari.lib.game.connexionBD.Methode;

/**
 *
 * @author mike
 */
public class CreationBase extends Methode {
    
    private Connection connection;
    
    public CreationBase () {
        super(null);
    }
    
    public CreationBase (Connection connection) {
        super(connection);
        this.connection = connection;
        if (this.estConnecter()) {
            if (!this.tableExist() || (this.getDateTableMiseAJour().before(this.getDateDerniereMiseAJour()))) {
                System.out.println("reconstruction de la base");
                this.dropAllTable();
                this.creationTablePartie();
                this.creationTableFolioEndroitVisite();
                this.creationTableEndroitVisite();
                this.creationTableEquipe();
                this.creationTableJoueurs();
                this.creationTableObjet();
                this.creationTableMission();
                this.creationTableMonstres();
                this.creationTableSorts();
                this.creationTableEvenementsChapitre();
                this.creationTableEvenementsQuete();
                this.creationTableQuetes();
                this.creationTableQuetesAnnexe();
                this.creationTableMiseAJour();
            }
        }
    }
    
    private void dropAllTable () {
        this.exexuteUpdate("DROP TABLE IF EXISTS mise_a_jour CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS quetes_annexe CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS quetes CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS evenements_quete CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS evenements_chapitre CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS sorts CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS monstres CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS missions CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS objets CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS joueurs CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS equipe CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS endroit_visite CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS folio_endroit_visite CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS partie CASCADE");
    }
    
    private boolean tableExist () {
        boolean exist = true;
        try {
            Statement statement = this.connection.createStatement() ;
            statement.executeQuery("SELECT * FROM partie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            exist = false;
        }
        return exist;
    }
    
    private Date getDateTableMiseAJour () {
        Calendar dateMiseAJour = Calendar.getInstance();
        dateMiseAJour.set(2014, Calendar.FEBRUARY, 14, 8, 30);
        return dateMiseAJour.getTime();
    }
    
    private Date getDateDerniereMiseAJour () {
        Calendar dateMiseAJour = Calendar.getInstance();
        dateMiseAJour.set(2014, Calendar.FEBRUARY, 14, 8, 25);
        return dateMiseAJour.getTime();
    }
    
    private void creationTablePartie () {
        String requete = "CREATE TABLE partie ("
                + "id INT PRIMARY KEY,"
                + "temps_heure INT,"
                + "temps_min INT,"
                + "temps_seconde INT"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneePartie();
        }
    }
    
    private void insertionDonneePartie () {
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (1)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (2)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (3)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (4)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (5)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (6)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (7)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (8)");
    }
    
    private void creationTableFolioEndroitVisite() {
        String requete = "CREATE TABLE folio_endroit_visite ("
                + "id INT,"
                + "folio_endroit_visite INT,"
                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (id) REFERENCES partie(id)"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneeFolioEndroitVisite();
        }
    }
    
    private void insertionDonneeFolioEndroitVisite () {
        this.exexuteUpdate("INSERT INTO folio_endroit_visite (id, folio_endroit_visite) VALUES "
                + "(1, 0),"
                + "(2, 0),"
                + "(3, 0),"
                + "(4, 0),"
                + "(5, 0),"
                + "(6, 0),"
                + "(7, 0),"
                + "(8, 0)");
    }
    
    private void creationTableEndroitVisite() {
        String requete = "CREATE TABLE endroit_visite ("
                + "id INT,"
                + "id_partie INT,"
                + "libelle_endroit VARCHAR(100),"
                + "PRIMARY KEY (id, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableEquipe () {
        String requete = "CREATE TABLE equipe ("
                + "id INT,"
                + "id_partie INT,"
                + "nom varchar(32),"
                + "point INT,"
                + "choisit BOOLEAN,"
                + "PRIMARY KEY (id, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableJoueurs () {
        String requete = "CREATE TABLE joueurs ("
                + "id INT,"
                + "id_partie INT,"
                + "nom varchar(32),"
                + "equipe_id INT,"
                + "capitaine BOOLEAN,"
                + "position_equipe INT,"
                + "argent INT,"
                + "positionx INT,"
                + "positiony INT,"
                + "orientation VARCHAR(10),"
                + "id_endroit INT,"
                + "annee INT,"
                + "arme varchar(250),"
                + "armure varchar(250),"
                + "chapeau varchar(250),"
                + "botte varchar(250),"
                + "accessoire1 varchar(250),"
                + "accessoire2 varchar(250),"
                + "niv INT,"
                + "pv DOUBLE,"
                + "pm DOUBLE,"
                + "experience DOUBLE,"
                + "etat1 varchar(50),"
                + "etat2 varchar(50),"
                + "PRIMARY KEY (id, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id),"
                + "FOREIGN KEY (id_endroit, id_partie) REFERENCES endroit_visite(id, id_partie),"
                + "FOREIGN KEY (equipe_id, id_partie) REFERENCES equipe(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableObjet() {
        String requete = "CREATE TABLE objets ("
                + "id_joueur INT,"
                + "id_partie INT,"
                + "libelle_objet VARCHAR(32),"
                + "type_objet VARCHAR(32),"
                + "quantite INT,"
                + "PRIMARY KEY (id_joueur, id_partie, libelle_objet, type_objet),"
                + "FOREIGN KEY (id_joueur, id_partie) REFERENCES joueurs(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableMission() {
        String requete = "CREATE TABLE missions ("
                + "id_joueur INT,"
                + "id_partie INT,"
                + "titre VARCHAR(50),"
                + "fini BOOLEAN,"
                + "PRIMARY KEY (id_joueur, id_partie, titre),"
                + "FOREIGN KEY (id_joueur, id_partie) REFERENCES joueurs(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableMonstres() {
        String requete = "CREATE TABLE monstres ("
                + "id_joueur INT,"
                + "id_partie INT,"
                + "id_monstre INT,"
                + "vue BOOLEAN,"
                + "analyser BOOLEAN,"
                + "PRIMARY KEY (id_joueur, id_partie, id_monstre),"
                + "FOREIGN KEY (id_joueur, id_partie) REFERENCES joueurs(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableSorts() {
        String requete = "CREATE TABLE sorts ("
                + "id_joueur INT,"
                + "id_partie INT,"
                + "nom VARCHAR(50),"
                + "nb_utilise INT,"
                + "place INT,"
                + "PRIMARY KEY (id_joueur, id_partie, nom),"
                + "FOREIGN KEY (id_joueur, id_partie) REFERENCES joueurs(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableEvenementsChapitre() {
        String requete = "CREATE TABLE evenements_chapitre ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "fini BOOLEAN,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableEvenementsQuete() {
        String requete = "CREATE TABLE evenements_quete ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "fini BOOLEAN,"
                + "id_evenement_chapitre INT,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_evenement_chapitre, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableQuetes() {
        String requete = "CREATE TABLE quetes ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "fini BOOLEAN,"
                + "id_evenement_quete INT,"
                + "id_evenement_chapitre INT,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_evenement_quete, id_evenement_chapitre, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableQuetesAnnexe() {
        String requete = "CREATE TABLE quetes_annexe ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "actif BOOLEAN,"
                + "fini BOOLEAN,"
                + "id_evenement_chapitre INT,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_evenement_chapitre, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableMiseAJour() {
        String requete = "CREATE TABLE mise_a_jour ("
                + "date_mise_a_jour TIMESTAMP"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneeTableMiseAJour();
        }
    }
    
    private void insertionDonneeTableMiseAJour () {
        this.exexuteUpdate("INSERT INTO mise_a_jour (date_mise_a_jour) VALUES ('2013-12-21 00:00:00')");
    }
}
