package com.loga.intelligentservice.app.ontology;

import com.loga.intelligentservice.app.ontology.concept.*;
import com.loga.intelligentservice.app.ontology.action.*;
import com.loga.intelligentservice.app.ontology.predicate.*;
import jade.content.onto.BasicOntology;
import jade.content.onto.CFReflectiveIntrospector;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.*;


public class AutoMaintenanceOntology extends Ontology {

    private static Ontology instance; /** @N2A */

    public static final String ONTOLOGY_NAME = "Auto-Maintenance-Ontology";

    public static final String AUTOMOBILE = "Automobile";
    public static final String AUTOMOBILE_VIN = "vin";
    public static final String AUTOMOBILE_MARQUE = "marque";
    public static final String AUTOMOBILE_MODELE = "modele";

    public static final String ENTITE = "Entite";
    public static final String ENTITE_NOM = "nom";

    public static final String SYSTEME = "Systeme";
    public static final String SYSTEME_FONCTION = "fonction";
    public static final String SYSTEME_COMPOSANTS = "composants";

    public static final String COMPOSANT = "Composant";
    public static final String COMPOSANT_ROLE = "role";

    public static final String DYSFONCTIONNEMENT = "Dysfonctionnement";
    public static final String DYSFONCTIONNEMENT_TITRE = "titre";
    public static final String DYSFONCTIONNEMENT_SYMPTOME = "symptome";
    public static final String DYSFONCTIONNEMENT_ENTITE = "entite";

    public static final String MAINTENANCE = "Maintenance";
    public static final String MAINTENANCE_REFERENCE = "reference";
    public static final String MAINTENANCE_ACTION = "action";
    public static final String MAINTENANCE_DYSFONCTIONNEMENT = "dysfonctionnement";

    public static final String DIAGNOSTIC = "Diagnostic";
    public static final String DIAGNOSTIC_AUTOMOBILE = "automobile";
    public static final String DIAGNOSTIC_MAINTENANCES = "maintenances";

    public static final String DEFAILLANCE = "Defaillance";
    public static final String DEFAILLANCE_PANNE = "panne";

    public static final String DEFAUT = "Defaut";
    public static final String DEFAUT_ANOMALIE = "anomalie";

    public static final String CONSTITUE = "Constitue";
    public static final String CONSTITUE_AUTOMOBILE = "automobile";
    public static final String CONSTITUE_SYSTEME = "systeme";

    public static final String COMPOSE = "Compose";
    public static final String COMPOSE_SYSTEME = "systeme";
    public static final String COMPOSE_COMPOSANT = "composant";

    public static final String PRESENTE = "Presente";
    public static final String PRESENTE_AUTOMOBILE = "automobile";
    public static final String PRESENTE_DYSFONCTIONNEMENT = "dysfonctionnement";

    public static final String RESOUT = "Resout";
    public static final String RESOUT_MAINTENANCE = "maintenance";
    public static final String RESOUT_DYSFONCTIONNEMENT = "dysfonctionnement";

    public static final String CAUSE = "Cause";
    public static final String CAUSE_DEFAUT = "defaut";
    public static final String CAUSE_DEFAILLANCE = "defaillance";
    public static final String CAUSE_DYSFONCTIONNEMENT = "dysfonctionnement";

    public static final String TROUBLE = "Trouble";
    public static final String TROUBLE_DEFAILLANCE = "defaillance";
    public static final String TROUBLE_SYSTEME = "systeme";

    public static final String AFFECTE = "Affecte";
    public static final String AFFECTE_DEFAUT = "defaut";
    public static final String AFFECTE_COMPOSANT = "composant";

    public static final String ELABORE = "Elabore";
    public static final String ELABORE_REPARATEUR = "reparateur";
    public static final String ELABORE_DIAGNOSTIC = "diagnostic";

    //Action
    public static final String MAKE_DIAGNOSTIQUE = "Diagnostique";
    public static final String MAKE_DIAGNOSTIQUE_AUTOMOBILE = "automobile";
    public static final String MAKE_DIAGNOSTIQUE_REPARATEUR = "reparateur";

    private AutoMaintenanceOntology() {
        super(ONTOLOGY_NAME,
                BasicOntology.getInstance(),
                new CFReflectiveIntrospector());

        try {
            add(new ConceptSchema(AUTOMOBILE), Automobile.class);
            add(new ConceptSchema(ENTITE), Entite.class);
            add(new ConceptSchema(SYSTEME), Systeme.class);
            add(new ConceptSchema(COMPOSANT), Composant.class);
            add(new ConceptSchema(DYSFONCTIONNEMENT), Dysfonctionnement.class);
            add(new ConceptSchema(DEFAILLANCE), Defaillance.class);
            add(new ConceptSchema(DEFAUT), Defaut.class);
            add(new ConceptSchema(MAINTENANCE), Maintenance.class);
            add(new ConceptSchema(DIAGNOSTIC), Diagnostic.class);

            add(new PredicateSchema(CONSTITUE), Constitue.class);
            add(new PredicateSchema(COMPOSE), Compose.class);
            add(new PredicateSchema(PRESENTE), Presente.class);
            add(new PredicateSchema(RESOUT), Resout.class);
            add(new PredicateSchema(CAUSE), Resout.class);
            add(new PredicateSchema(TROUBLE), Trouble.class);
            add(new PredicateSchema(AFFECTE), Affecte.class);
            add(new PredicateSchema(ELABORE), Elabore.class);

            add(new AgentActionSchema(MAKE_DIAGNOSTIQUE), Diagnostique.class);

            // Structure of the schema for the Automobile concept
            ConceptSchema conceptSchema = (ConceptSchema) getSchema(AUTOMOBILE);
            conceptSchema.add(AUTOMOBILE_VIN, (PrimitiveSchema) getSchema(BasicOntology.STRING));
            conceptSchema.add(AUTOMOBILE_MARQUE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
            conceptSchema.add(AUTOMOBILE_MODELE, (PrimitiveSchema) getSchema(BasicOntology.STRING));

            // Structure of the schema for the "Entite" concept
            conceptSchema = (ConceptSchema) getSchema(ENTITE);
            conceptSchema.add(ENTITE_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING));

            // Structure of the schema for the "Systeme" concept
            conceptSchema = (ConceptSchema) getSchema(SYSTEME);
            conceptSchema.addSuperSchema((ConceptSchema) getSchema(ENTITE));
            conceptSchema.add(SYSTEME_FONCTION, (PrimitiveSchema) getSchema(BasicOntology.STRING));
            conceptSchema.add(SYSTEME_COMPOSANTS, (ConceptSchema) getSchema(COMPOSANT), 1, ObjectSchema.UNLIMITED);

            // Structure of the schema for the "Composant" concept
            conceptSchema = (ConceptSchema) getSchema(COMPOSANT);
            conceptSchema.addSuperSchema((ConceptSchema) getSchema(ENTITE));
            conceptSchema.add(COMPOSANT_ROLE,(PrimitiveSchema) getSchema(BasicOntology.STRING));

            // Structure of the schema for the "Dysfonctionnement" concept
            conceptSchema = (ConceptSchema) getSchema(DYSFONCTIONNEMENT);
            conceptSchema.add(DYSFONCTIONNEMENT_TITRE,(PrimitiveSchema) getSchema(BasicOntology.STRING));
            conceptSchema.add(DYSFONCTIONNEMENT_SYMPTOME,(PrimitiveSchema) getSchema(BasicOntology.STRING));
            conceptSchema.add(DYSFONCTIONNEMENT_ENTITE,(ConceptSchema) getSchema(ENTITE));

            // Structure of the schema for the "Defaillance" concept
            conceptSchema = (ConceptSchema) getSchema(DEFAILLANCE);
            conceptSchema.addSuperSchema((ConceptSchema) getSchema(DYSFONCTIONNEMENT));
            conceptSchema.add(DEFAILLANCE_PANNE,(PrimitiveSchema) getSchema(BasicOntology.STRING));

            // Structure of the schema for the "Defaut" concept
            conceptSchema = (ConceptSchema) getSchema(DEFAUT);
            conceptSchema.addSuperSchema((ConceptSchema) getSchema(DYSFONCTIONNEMENT));
            conceptSchema.add(DEFAUT_ANOMALIE,(PrimitiveSchema) getSchema(BasicOntology.STRING));

            // Structure of the schema for the "Maintenance" concept
            conceptSchema = (ConceptSchema) getSchema(MAINTENANCE) ;
            conceptSchema.add(MAINTENANCE_REFERENCE,(PrimitiveSchema) getSchema(BasicOntology.STRING));
            conceptSchema.add(MAINTENANCE_ACTION,(PrimitiveSchema) getSchema(BasicOntology.STRING));
            conceptSchema.add(MAINTENANCE_DYSFONCTIONNEMENT,(ConceptSchema) getSchema(DYSFONCTIONNEMENT));

            // Structure of the schema for the "Diagnostic" concept
            conceptSchema = (ConceptSchema) getSchema(DIAGNOSTIC);
            conceptSchema.add(DIAGNOSTIC_AUTOMOBILE, (ConceptSchema) getSchema(AUTOMOBILE));
            conceptSchema.add(DIAGNOSTIC_MAINTENANCES, (ConceptSchema) getSchema(MAINTENANCE));

            // Structure of the schema for the "Constitue" predicate
            PredicateSchema predicateSchema = (PredicateSchema) getSchema(CONSTITUE);
            predicateSchema.add(CONSTITUE_AUTOMOBILE,getSchema(AUTOMOBILE));
            predicateSchema.add(CONSTITUE_SYSTEME,getSchema(SYSTEME));

            // Structure of the schema for the "Compose" predicate
            predicateSchema = (PredicateSchema) getSchema(COMPOSE);
            predicateSchema.add(COMPOSE_SYSTEME,getSchema(SYSTEME));
            predicateSchema.add(COMPOSE_COMPOSANT,getSchema(COMPOSANT));

            // Structure of the schema for the "Presente" predicate
            predicateSchema = (PredicateSchema) getSchema(PRESENTE);
            predicateSchema.add(PRESENTE_AUTOMOBILE,getSchema(AUTOMOBILE));
            predicateSchema.add(PRESENTE_DYSFONCTIONNEMENT,getSchema(DYSFONCTIONNEMENT));

            // Structure of the schema for the "Resout" predicate
            predicateSchema = (PredicateSchema) getSchema(RESOUT);
            predicateSchema.add(RESOUT_MAINTENANCE,getSchema(MAINTENANCE));
            predicateSchema.add(RESOUT_DYSFONCTIONNEMENT,getSchema(DYSFONCTIONNEMENT));

            // Structure of the schema for the "Cause" predicate
            predicateSchema = (PredicateSchema) getSchema(CAUSE);
            predicateSchema.add(CAUSE_DEFAUT,getSchema(DEFAUT));
            predicateSchema.add(CAUSE_DYSFONCTIONNEMENT,getSchema(DYSFONCTIONNEMENT));

            // Structure of the schema for the "Cause" predicate
            predicateSchema = (PredicateSchema) getSchema(CAUSE);
            predicateSchema.add(CAUSE_DEFAILLANCE,getSchema(DEFAILLANCE));
            predicateSchema.add(CAUSE_DYSFONCTIONNEMENT,getSchema(DYSFONCTIONNEMENT));

            // Structure of the schema for the "Trouble" predicate
            predicateSchema = (PredicateSchema) getSchema(TROUBLE);
            predicateSchema.add(TROUBLE_DEFAILLANCE,getSchema(DEFAILLANCE));
            predicateSchema.add(TROUBLE_SYSTEME,getSchema(SYSTEME));

            // Structure of the schema for the "Affecte" predicate
            predicateSchema = (PredicateSchema) getSchema(AFFECTE);
            predicateSchema.add(AFFECTE_DEFAUT,getSchema(DEFAUT));
            predicateSchema.add(AFFECTE_COMPOSANT,getSchema(COMPOSANT));

            // Structure of the schema for the "Elabore" predicate
            predicateSchema = (PredicateSchema) getSchema(ELABORE);
            predicateSchema.add(ELABORE_REPARATEUR,getSchema(BasicOntology.AID));
            predicateSchema.add(ELABORE_DIAGNOSTIC,getSchema(DIAGNOSTIC));

            // Structure of the schema for the "Diagnostique" agent's action
            AgentActionSchema agentActionSchema = (AgentActionSchema) getSchema(MAKE_DIAGNOSTIQUE);
            agentActionSchema.add(MAKE_DIAGNOSTIQUE_AUTOMOBILE, (ConceptSchema) getSchema(AUTOMOBILE));
            agentActionSchema.add(MAKE_DIAGNOSTIQUE_REPARATEUR, (ConceptSchema) getSchema(BasicOntology.AID));
        } catch (OntologyException e) {
            throw new RuntimeException(e);
        }
    }

    public static Ontology getInstance(){
        if(instance==null)
            instance = new AutoMaintenanceOntology();
        return instance;
    }
}
