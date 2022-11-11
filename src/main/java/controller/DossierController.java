package controller;

import app.model.*;
import app.service.manager.IManageDossier;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DossierController implements Initializable
{
    IManageDossier manageDossierService;
    List<Dossier> allDossier;

    Dossier dossierCurrent;

    @FXML
    private ComboBox<Dossier> dossiers;

    @FXML
    private TextField immatriculation;

    @FXML
    private TextField marque;

    @FXML
    private TextField compteur;

    @FXML
    private TextField chassis;

    @FXML
    private TextField client;

    @FXML
    private TextField reference;

    @FXML
    private TextField pmec;

    @FXML
    private TextField puissance;

    @FXML
    private TextField cylindre;

    @FXML
    private TextField transmission;

    @FXML
    private TableView<Reception> table_reception;

    @FXML
    private TableColumn<Reception, Long> reception_id;

    @FXML
    private TableColumn<Reception, String> reception_date;

    @FXML
    private TableColumn<Reception, String> reception_observation;

    @FXML
    private TableColumn<Reception, String> reception_commentaire;

    @FXML
    private TableColumn<Reception, Integer> reception_compteur;

    @FXML
    private TableView<Diagnostic> table_diagnostic;

    @FXML
    private TableColumn<Diagnostic, Long> diagnostic_id;

    @FXML
    private TableColumn<Diagnostic, String> diagnostic_date;

    @FXML
    private TableColumn<Diagnostic, String> diagnostic_diagnostic;

    @FXML
    private TableColumn<Diagnostic, Integer> diagnostic_compteur;

    @FXML
    private TableView<Repair> table_reparation;

    @FXML
    private TableColumn<Repair, Long> reparation_id;

    @FXML
    private TableColumn<Repair, String> reparation_date;

    @FXML
    private TableColumn<Repair, String> reparation_numero;

    @FXML
    private TableColumn<Repair, String> reparation_description;

    @FXML
    private TableColumn<Repair, Integer> reparation_compteur;

    @FXML
    private TableView<Order> table_commande;

    @FXML
    private TableColumn<Order, Long> commande_id;

    @FXML
    private TableColumn<Order, String> commande_date;

    @FXML
    private TableColumn<Order, String> commande_fournisseur;

    @FXML
    private TableColumn<Order, Integer> commande_quantite;

    @FXML
    private TableColumn<Order, Double> commande_montant;

    @FXML
    private TableView<Quality> table_qualite;

    @FXML
    private TableColumn<Quality, Long> qualite_id;

    @FXML
    private TableColumn<Quality, Date> qualite_date;

    @FXML
    private TableColumn<Quality, Integer> qualite_compteur;

    @FXML
    private TableColumn<Quality, String> qualite_commentaire;

    @FXML
    private TableView<Repair> table_solde;

    @FXML
    private TableColumn<Repair, String> solde_reparation;

    @FXML
    private TableColumn<Repair, String> solde_description;

    @FXML
    private TableColumn<Repair, Double> solde_montant;

    @FXML
    private TableColumn<Repair, Double> solde_balance;

    @FXML
    void print_diagnostic(MouseEvent event) {
        //todo:Report.getInstance().createReport("/jrxml/diagnostic.jrxml", (int) table_diagnostic.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void print_commande(MouseEvent event) {
        //todo:Report.getInstance().createReport("/jrxml/commande.jrxml",(int)table_commande.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void print_qualite(MouseEvent event) {
        //todo:print quality
    }

    @FXML
    void print_reception(MouseEvent event) {
        //todo:Report.getInstance().createReport("/jrxml/reception.jrxml",(int) table_reception.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void print_reparation(MouseEvent event) {
        //todo:Report.getInstance().createReport("/jrxml/reparation.jrxml",(int) table_reparation.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void search_dossier(KeyEvent event) {
        FilteredList<Dossier> items = new FilteredList<>(FXCollections.observableArrayList(allDossier));
        items.setPredicate(item ->{
            String lower = dossiers.getEditor().getText().toLowerCase();
            String upper = dossiers.getEditor().getText().toUpperCase();
            if(item.getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Dossier> sorted = new SortedList<>(items);
        dossiers.setItems(sorted);
    }

    void readInformation(Dossier dossier) {
        client.setText(dossier.getAutomobile().getClient().getRaisonSociale()+" | "+dossier.getAutomobile().getClient().getContact());
        immatriculation.setText(dossier.getAutomobile().getImmatriculation());
        marque.setText(dossier.getAutomobile().getMarque() + " / " + dossier.getAutomobile().getModele());
        compteur.setText(dossier.getAutomobile().getCompteur() + " [" + dossier.getAutomobile().getTypeCompteur() + "]");
        chassis.setText(dossier.getAutomobile().getChassis());
        reference.setText(dossier.getReference());
        pmec.setText(dossier.getAutomobile().getPmec());
        transmission.setText(dossier.getAutomobile().getTransmission());
        puissance.setText(dossier.getAutomobile().getPuissance());
        cylindre.setText(dossier.getAutomobile().getCylindre());
    }

    void readReception(Dossier dossier) {
        List<Reception> receptions = dossier.getReceptions();
        table_reception.setItems(FXCollections.observableArrayList(receptions));
    }

    void readDiagnostic(Dossier dossier) {
        List<Diagnostic> diagnostics = dossier.getDiagnostics();
        table_diagnostic.setItems(FXCollections.observableArrayList(diagnostics));
    }

    void readReparation(Dossier dossier) {
        List<Repair> reparations = dossier.getRepairs();
        table_reparation.setItems(FXCollections.observableArrayList(reparations));
    }

    void readCommande(Dossier dossier) {
        List<Order> commandes = new ArrayList<>();
        List<Repair> reparations = dossier.getRepairs();
        for (Repair reparation : reparations) {
            if(!reparation.getSpares().isEmpty()){
                Spare fourniture = reparation.getSpares().get(0);
                Order commande = fourniture.getCommande();
                if(commande!=null){
                    commandes.add(commande);
                }
            }
        }
        table_commande.setItems(FXCollections.observableArrayList(commandes));
    }

    void readSolde(Dossier dossier){
        List<Repair> reparations = new ArrayList<>();
        for (Repair reparation : dossier.getRepairs()) {
            if(reparation.getSolde() < reparation.getTotal()){
                reparations.add(reparation);
            }
        }
        table_solde.setItems(FXCollections.observableArrayList(reparations));
    }

    void readQualite(Dossier dossier) {
        List<Quality> qualites = new ArrayList<>();
        List<Repair> reparations = dossier.getRepairs();
        for (Repair reparation:reparations) {
            Quality qualite = reparation.getQuality();
            if(qualite!=null){
                qualites.add(reparation.getQuality());
            }
        }
        table_qualite.setItems(FXCollections.observableArrayList(qualites));
    }

    void readDossiers(){
        this.allDossier = manageDossierService.listDossier();
        dossiers.setItems(FXCollections.observableArrayList(allDossier));
    }
    
    public void initialize(URL location, ResourceBundle resources) {
        dossiers.setConverter(new StringConverter<Dossier>() {
            @Override
            public String toString(Dossier object) {
                if(object==null) return null;
                return object.toString();
            }

            @Override
            public Dossier fromString(String string) {
                String[] data = string.split("/");
                String auto = data[0].trim();
                return manageDossierService.findDossier(auto);
            }
        });

        readDossiers();

        reception_id.setCellValueFactory((TableColumn.CellDataFeatures<Reception, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        reception_date.setCellValueFactory((TableColumn.CellDataFeatures<Reception, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        reception_commentaire.setCellValueFactory((TableColumn.CellDataFeatures<Reception, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCommentaire()));
        reception_observation.setCellValueFactory((TableColumn.CellDataFeatures<Reception, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getObservation()));
        reception_compteur.setCellValueFactory((TableColumn.CellDataFeatures<Reception, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCompteur()));

        diagnostic_id.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        diagnostic_date.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        diagnostic_diagnostic.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        diagnostic_compteur.setCellValueFactory((TableColumn.CellDataFeatures<Diagnostic, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCompteur()));

        reparation_id.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        reparation_date.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        reparation_description.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        reparation_numero.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getReference()));
        reparation_compteur.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCompteur()));

        commande_id.setCellValueFactory((TableColumn.CellDataFeatures<Order, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        commande_date.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        commande_fournisseur.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getFournisseur().getRaisonSociale()));
        commande_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        commande_montant.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        solde_reparation.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateCreation())));
        solde_description.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        solde_montant.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotal()));
        solde_balance.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getSolde()-r.getValue().getTotal()));

        qualite_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        qualite_commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        qualite_compteur.setCellValueFactory(new PropertyValueFactory<>("compteur"));
        qualite_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        dossiers.addEventHandler(ActionEvent.ACTION, event -> {
            if (dossiers.getSelectionModel().getSelectedIndex() != -1) {
                dossierCurrent = dossiers.getValue();
                readInformation(dossierCurrent);
                readReception(dossierCurrent);
                readDiagnostic(dossierCurrent);
                readReparation(dossierCurrent);
                readCommande(dossierCurrent);
                readSolde(dossierCurrent);
                readQualite(dossierCurrent);
            }
        });
    }
}
