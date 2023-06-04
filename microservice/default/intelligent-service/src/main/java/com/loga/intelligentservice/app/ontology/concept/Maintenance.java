package com.loga.intelligentservice.app.ontology.concept;

import jade.content.Concept;

public class Maintenance implements Concept {
    private String reference;
    private String action;
    private Dysfonctionnement dysfonctionnement;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Dysfonctionnement getDysfonctionnement() {
        return dysfonctionnement;
    }

    public void setDysfonctionnement(Dysfonctionnement dysfonctionnement) {
        this.dysfonctionnement = dysfonctionnement;
    }
}
