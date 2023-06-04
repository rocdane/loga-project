package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import com.loga.intelligentservice.app.ontology.concept.Dysfonctionnement;
import com.loga.intelligentservice.app.ontology.concept.Maintenance;

public class Resout implements Predicate {
    private Dysfonctionnement dysfonctionnement;
    private Maintenance maintenance;

    public Dysfonctionnement getDysfonctionnement() {
        return dysfonctionnement;
    }

    public void setDysfonctionnement(Dysfonctionnement dysfonctionnement) {
        this.dysfonctionnement = dysfonctionnement;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }
}
