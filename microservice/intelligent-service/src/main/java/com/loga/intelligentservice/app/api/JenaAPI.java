package com.loga.intelligentservice.app.api;

import com.loga.intelligentservice.app.factory.Diagnosis;
import org.apache.jena.ontology.*;
import org.apache.jena.ontology.impl.OntologyImpl;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import com.loga.intelligentservice.IntelligentService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JenaAPI {
    private static JenaAPI jenaAPI;
    private final OntModel ONTOLOGY;

    private JenaAPI(){
        this.ONTOLOGY = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        URL resource = Objects.requireNonNull(getClass().getResource("/automaintongologie.owl"));

        File file = new File(resource.getFile());

        try {
            ONTOLOGY.read(new FileInputStream(file.getPath()),null,null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IntelligentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static JenaAPI getInstance(){
        if(jenaAPI == null)
            jenaAPI = new JenaAPI();
        return jenaAPI;
    }

    public String getURI(){
        Iterator<Ontology> iter = ONTOLOGY.listOntologies();
        OntologyImpl onto = (OntologyImpl) iter.next();
        return (onto.getURI()+"#");
    }

    public List<Diagnosis> query(String text){
        String queryString = "PREFIX ontology: <"+getURI()+">\n" +
                "SELECT ?Titre ?Action \n" +
                "WHERE { \n" +
                " ?dysfonctionnement ontology:resoluPar ?maintenance .\n" +
                " ?dysfonctionnement ontology:Titre ?Titre .\n" +
                " ?maintenance ontology:Action ?Action .\n" +
                " FILTER regex( ?Titre , \"^" + text + "\") \n}";

        Query query = QueryFactory.create(queryString);

        QueryExecution qe = QueryExecutionFactory.create(query, ONTOLOGY);
        ResultSet results =  qe.execSelect();

        List<Diagnosis> result = new ArrayList<>();

        while (results.hasNext()){
            QuerySolution qs = results.next();
            Iterator<String> iter = qs.varNames();
            while(iter.hasNext()) {
                result.add(new Diagnosis(
                        String.valueOf(qs.get(iter.next())),
                        String.valueOf(qs.get(iter.next()))
                ));
            }
        }
        qe.close();
        return result;
    }

    public Individual addDysfunction(String dys){
        OntClass dysfunction = ONTOLOGY.getOntClass( getURI()+"Dysfonctionnement" );
        DatatypeProperty symptome = ONTOLOGY.getDatatypeProperty(getURI()+"Symptome");
        return dysfunction.createIndividual(getURI()+dys);
    }

    public Individual addMaintenance(String maint){
        OntClass maintenance = ONTOLOGY.getOntClass( getURI()+"Maintenance");
        DatatypeProperty action = ONTOLOGY.getDatatypeProperty(getURI()+"Action");
        Individual individual = maintenance.createIndividual(getURI()+maint);
        individual.addLiteral(action,maint);
        return individual;
    }

    public void update(Individual indiv1, Individual indiv2){
        ObjectProperty resoluPar = ONTOLOGY.getObjectProperty(getURI()+"resoluPar");
    }
}

