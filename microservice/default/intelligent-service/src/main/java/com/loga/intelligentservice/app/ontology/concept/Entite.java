package com.loga.intelligentservice.app.ontology.concept;

import jade.content.Concept;

public class Entite implements Concept {

    //Tout élément, composant, sous-système, système, dispositif, équipement,
    // unité fonctionnelle que l'on peut considérer individuellement.

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
