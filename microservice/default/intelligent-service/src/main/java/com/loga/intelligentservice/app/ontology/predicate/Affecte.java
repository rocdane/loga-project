package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import com.loga.intelligentservice.app.ontology.concept.Composant;
import com.loga.intelligentservice.app.ontology.concept.Defaut;

public class Affecte implements Predicate {
    private Defaut defaut;
    private Composant composant;

    public Defaut getDefaut() {
        return defaut;
    }

    public void setDefaut(Defaut defaut) {
        this.defaut = defaut;
    }

    public Composant getComposant() {
        return composant;
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }
}
