package com.loga.intelligentservice.app.ontology.concept;

import jade.content.Concept;

public class Dysfonctionnement implements Concept {
    private String titre;
    private String symptome;
    private Entite entite;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }
}
