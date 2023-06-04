package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import com.loga.intelligentservice.app.ontology.concept.Automobile;
import com.loga.intelligentservice.app.ontology.concept.Dysfonctionnement;

public class Presente implements Predicate {
    private Automobile automobile;
    private Dysfonctionnement dysfonctionnement;

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Dysfonctionnement getDysfonctionnement() {
        return dysfonctionnement;
    }

    public void setDysfonctionnement(Dysfonctionnement dysfonctionnement) {
        this.dysfonctionnement = dysfonctionnement;
    }
}
