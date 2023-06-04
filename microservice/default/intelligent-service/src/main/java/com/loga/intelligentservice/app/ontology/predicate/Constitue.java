package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import com.loga.intelligentservice.app.ontology.concept.Automobile;
import com.loga.intelligentservice.app.ontology.concept.Entite;

public class Constitue implements Predicate {
    private Automobile automobile;
    private Entite entite;

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }
}
