package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import com.loga.intelligentservice.app.ontology.concept.Defaillance;
import com.loga.intelligentservice.app.ontology.concept.Systeme;

public class Trouble implements Predicate {
    private Defaillance defaillance;
    private Systeme systeme;

    public Defaillance getDefaillance() {
        return defaillance;
    }

    public void setDefaillance(Defaillance defaillance) {
        this.defaillance = defaillance;
    }

    public Systeme getSysteme() {
        return systeme;
    }

    public void setSysteme(Systeme systeme) {
        this.systeme = systeme;
    }
}
