/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.ecoledemagie.game.condition;

import mfiari.lib.game.condition.Condition;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.position.Position;

/**
 *
 * @author mike
 */
public class ConditionAnimation extends Condition{
    
    private char key;
    private String actionToDo;
    private String messageSuccess;
    private String messageFail;
    private double pvPerdu;
    private Position pos;
    private ObjetEndroit objet;
    
    public ConditionAnimation () {
        this.pvPerdu = 2;
    }

    public char getKey() {
        return this.key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public String getActionToDo() {
        return this.actionToDo;
    }

    public void setActionToDo(String actionToDo) {
        this.actionToDo = actionToDo;
    }

    public Position getPos() {
        return this.pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public ObjetEndroit getObjet() {
        return this.objet;
    }

    public void setObjet(ObjetEndroit objet) {
        this.objet = objet;
    }

    public String getMessageFail() {
        return this.messageFail;
    }

    public void setMessageFail(String messageFail) {
        this.messageFail = messageFail;
    }

    public String getMessageSuccess() {
        return this.messageSuccess;
    }

    public void setMessageSuccess(String messageSuccess) {
        this.messageSuccess = messageSuccess;
    }
    
    public double getPvPerdu() {
        return this.pvPerdu;
    }

    public void setPvPerdu(double pvPerdu) {
        this.pvPerdu = pvPerdu;
    }
}
