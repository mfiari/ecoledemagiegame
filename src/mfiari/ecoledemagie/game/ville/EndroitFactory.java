/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.ville;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import mfiari.ecoledemagie.game.xml.ParserEndroitXML;

/**
 *
 * @author mike
 */
public class EndroitFactory {
    
    private String directory;
    private Endroit endroit;
    
    public EndroitFactory () {
        this.directory = "media/endroits/";
    }
    
    public Endroit createEndroit (String code) {
        this.loadFromXML(code);
        this.addPokemon(code);
        this.addDresseur(code);
        return endroit;
    }
    
    private void loadFromXML (String code) {
        String filename = this.directory + code + ".xml";
        System.out.println(filename);
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            ParserEndroitXML myHandler = new ParserEndroitXML();
            System.out.println("debut parse");
            sp.parse(inputStream, myHandler);
            System.out.println("fin parse");
            this.endroit = myHandler.getEndroit();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EndroitFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    private void addPokemon (String code) {
        /*PokemonFactory factory = new PokemonFactory();
        Pokemon pkmn = factory.createPokemon(1);
        for (int i = 2 ; i <= 5 ; i++) {
            PokemonClassique pk = new PokemonClassique((PokemonClassique) pkmn);
            pk.changeNiveau(i);
            this.endroit.ajouterPokemon(pk, Environnement.haute_herbe, 25);
        }*/
    }
    
    private void addDresseur (String code) {
        /*DresseurFactory factory = new DresseurFactory();
        Dresseur dresseur = factory.createDresseur(code);
        if (dresseur != null) {
            dresseur.ajouterParoleAvantCombat("Je vais remporter le tournoi.");
            dresseur.ajouterParoleFinCombat("Oh non!");
            dresseur.ajouterParole("Je n'abandonnerai pas");
            PokemonFactory pokemonFactory = new PokemonFactory();
            Pokemon pkmn = pokemonFactory.createPokemon(1);
            PokemonClassique pk = new PokemonClassique((PokemonClassique) pkmn);
            pk.changeNiveau(2);
            dresseur.ajouterPokemon(pkmn);
            dresseur.getPosition().setEndroit(this.endroit);
            this.endroit.ajouterGens(dresseur);
        }*/
    }
    
}