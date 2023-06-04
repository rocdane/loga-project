package com.loga.intelligentservice.app.ontology.concept;

import jade.content.onto.annotations.AggregateSlot;
import jade.util.leap.List;

public class Systeme extends Entite {
    //ensemble déterminé de composants interconnectés, en interaction ou coopérant entre eux.
    private String fonction;
    private List composants;

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @AggregateSlot(type = Composant.class,cardMin = 1)
    public List getComposants() {
        return composants;
    }

    public void setComposants(List composants) {
        this.composants = composants;
    }
}
