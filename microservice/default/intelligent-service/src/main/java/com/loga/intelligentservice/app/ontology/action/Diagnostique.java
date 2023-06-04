package com.loga.intelligentservice.app.ontology.action;

import com.loga.intelligentservice.app.ontology.concept.Automobile;
import jade.content.AgentAction;
import jade.core.AID;

public class Diagnostique implements AgentAction {
    private Automobile automobile;
    private AID reparateur;

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public AID getReparateur() {
        return reparateur;
    }

    public void setReparateur(AID reparateur) {
        this.reparateur = reparateur;
    }
}
