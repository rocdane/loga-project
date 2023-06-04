package com.loga.intelligentservice.app.ontology.predicate;

import jade.content.Predicate;
import jade.core.AID;
import com.loga.intelligentservice.app.ontology.concept.Diagnostic;

public class Elabore implements Predicate {
    private AID reparateur;
    private Diagnostic diagnostic;

    public AID getReparateur() {
        return reparateur;
    }

    public void setReparateur(AID reparateur) {
        this.reparateur = reparateur;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }
}
