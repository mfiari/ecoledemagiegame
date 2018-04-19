/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.connexionBD;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import mfiari.ecoledemagie.core.network.User;
import mfiari.ecoledemagie.game.Global;
import mfiari.lib.game.connexionBD.Methode;
import mfiari.lib.game.util.Config;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.CharacterSet;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author mike
 */
public class TransfertDonnee extends Methode {
    
    private String WS_URL;
    private String WS_LOGIN;
    private String WS_PASSWORD;
    private User user;
    
    public TransfertDonnee (Connection connectionHSQL) {
        super(connectionHSQL);
    }
    
    private void init () {
        String wsUrl = Config.getPropertie("ws.url");
        String login = Config.getPropertie("ws.login");
        String password = Config.getPropertie("ws.password");
        WS_URL = wsUrl;
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            this.pcsControlleurVue.firePropertyChange("problemeConnexion", null, null);
        } else {
            WS_LOGIN = login;
            WS_PASSWORD = password;
        }
    }
    
    public User connexion (String login, String password) {
        String wsUrl = Config.getPropertie("ws.url");
        String url = wsUrl + "ws/login";
        System.out.println(url);
        Form form = new Form();
        form.add("login", login);
        form.add("password", password);
        ClientResource resource = this.sendPostData(url, form);
        if (resource != null && resource.getStatus().isSuccess()) {
            try {
                String xml = resource.getResponseEntity().getText();
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    /* 
                     * String to input stream 
                     * Read xml from string
                     * */
                    InputStream is = new ByteArrayInputStream(xml.getBytes());
                    Document document = builder.parse(is);
                    Element userElement = (Element) document.getElementsByTagName("user").item(0);
                    String id = userElement.getAttribute("id");
                    user = new User(Integer.parseInt(id));
                    user.setLogin(login);
                } catch (SAXException | ParserConfigurationException | IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                Logger.getLogger(TransfertDonnee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }
    
    public void getAccount () {
        this.pcsControlleurVue.firePropertyChange("login", false, new mfiari.lib.game.reseau.Connexion("", ""));
    }
    
    public void transfertDonnees () {
        this.pcsControlleurVue.firePropertyChange("transfertEnCours", null, null);
        this.init();
        this.pcsControlleurVue.firePropertyChange("transfertTerminer", null, null);
    }
    
    public void recuperationDonnees () {
        this.pcsControlleurVue.firePropertyChange("transfertEnCours", null, null);
        this.init();
        this.connexion(WS_LOGIN, WS_PASSWORD);
        this.pcsControlleurVue.firePropertyChange("transfertTerminer", null, null);
    }
    
    public void majDonnees () {
        this.init();
    }
    
    private ClientResource sendPostData (String url, Form form) {
        ClientResource resource = new ClientResource(url);
        try {
            form.encode(CharacterSet.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(TransfertDonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
        Representation rep = form.getWebRepresentation();
        /*ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC,Global.webserviceLogin, Global.webserviceMdp);
        resource.setChallengeResponse(authentication);*/
        // Test du POST
        Representation result = resource.post(rep);
        /*try {
            System.out.println(result.getText());
        } catch (IOException ex) {
            Logger.getLogger(TransfertDonnee.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return resource;
        // Verifier que la ressource est mise a jour
        /*DomRepresentation representation = new DomRepresentation(resource.get());
        try {
            System.out.println(representation.getText());
        } catch (IOException ex) {
            Logger.getLogger(TransfertDonnee.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    private void sendPutData (String url, Form form) {
        ClientResource resource = new ClientResource(url);
        try {
            form.encode(CharacterSet.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(TransfertDonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
        Representation rep = form.getWebRepresentation();
        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC,Global.webserviceLogin, Global.webserviceMdp);
        resource.setChallengeResponse(authentication);
        // Test du PUT
        resource.put(rep);
    }
    
}
