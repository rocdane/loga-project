package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import com.loga.intelligentservice.app.ontology.concept.Composant;
import com.loga.intelligentservice.app.ontology.concept.Systeme;

public class Compose implements Predicate {
    private Systeme systeme;
    private Composant composant;

    public Systeme getSysteme() {
        return systeme;
    }

    public void setSysteme(Systeme systeme) {
        this.systeme = systeme;
    }

    public Composant getComposant() {
        return composant;
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }
}
