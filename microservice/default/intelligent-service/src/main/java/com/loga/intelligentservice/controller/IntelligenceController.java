package com.loga.intelligentservice.controller;

import com.loga.intelligentservice.app.agent.UserAgent;
import com.loga.intelligentservice.app.ontology.concept.Diagnostic;
import com.loga.intelligentservice.app.ontology.concept.Maintenance;
import com.loga.intelligentservice.app.ontology.predicate.Elabore;
import com.loga.intelligentservice.service.Diagnosis;
import com.loga.intelligentservice.service.IIntelligenceService;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.onto.OntologyException;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/intelligent-service")
public class IntelligenceController {

    @Autowired
    private IIntelligenceService intelligenceService;

    public static UserAgent agent;

    public IntelligenceController() {
        agent = new UserAgent();
    }

    public static void setAgent(UserAgent userAgent){
        agent = userAgent;
    }

    @GetMapping(path = "/diagnose/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diagnosis>> diagnose(@PathVariable String param){

        // Notify gui event
        GuiEvent guiEvent = new GuiEvent(this, 2);
        guiEvent.addParameter(param);
        agent.onGuiEvent(guiEvent);

        // Waiting UserAgent proposal
        ACLMessage proposal = agent.blockingReceive();

        ContentElement contentElement;

        try {
            contentElement = agent.getContentManager().extractContent(proposal);
        } catch (Codec.CodecException | OntologyException e) {
            throw new RuntimeException(e);
        }

        List<Diagnosis> diagnoses = null;

        if (contentElement instanceof Elabore elabore) {

            diagnoses = new ArrayList<>();

            Diagnostic diagnostic = elabore.getDiagnostic();

            Maintenance[] maintenances = (Maintenance[]) diagnostic.getMaintenances().toArray();

            for (Maintenance maintenance:maintenances) {
                diagnoses.add(new Diagnosis(
                        maintenance.getDysfonctionnement().getTitre(),
                        maintenance.getAction())
                );
            }
        }

        return ResponseEntity.ok().body(diagnoses);
    }

    @GetMapping(path = "/resolve/{words}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Diagnosis> resolve(@PathVariable String words){
        return intelligenceService.resolve(words);
    }
}
