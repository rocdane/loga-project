package com.loga.intelligentservice.app.ontology.concept;

import jade.content.Concept;
import jade.util.leap.List;

public class Diagnostic implements Concept {
    private Automobile automobile;
    private List maintenances;

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public List getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List maintenances) {
        this.maintenances = maintenances;
    }
}
