package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import com.loga.intelligentservice.app.ontology.concept.Defaillance;
import com.loga.intelligentservice.app.ontology.concept.Defaut;
import com.loga.intelligentservice.app.ontology.concept.Dysfonctionnement;

public class Cause implements Predicate {
    private Defaut defaut;
    private Defaillance defaillance;
    private Dysfonctionnement dysfonctionnement;

    public Defaut getDefaut() {
        return defaut;
    }

    public void setDefaut(Defaut defaut) {
        this.defaut = defaut;
    }

    public Defaillance getDefaillance() {
        return defaillance;
    }

    public void setDefaillance(Defaillance defaillance) {
        this.defaillance = defaillance;
    }

    public Dysfonctionnement getDysfonctionnement() {
        return dysfonctionnement;
    }

    public void setDysfonctionnement(Dysfonctionnement dysfonctionnement) {
        this.dysfonctionnement = dysfonctionnement;
    }
}
