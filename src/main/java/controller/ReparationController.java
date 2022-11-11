package controller;

import app.model.*;
import app.service.manager.IManageDossier;
import app.service.manager.IManageGarage;
import app.service.manager.IManageResource;
import app.service.marketer.IMarketLogistic;
import app.service.marketer.IMarketStock;
import app.service.repairer.IRepairReparation;
import app.service.repairer.RepairReparationRequest;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import vendor.io.Money;
import vendor.io.NumberToWords;
import vendor.io.Report;
import view.AlertConfirm;
import view.AlertError;
import view.AlertInfo;
import view.AlertWarning;

import javax.inject.Inject;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReparationController implements Initializable {

    @Inject
    IManageGarage manageGarageService;

    @Inject
    IManageResource manageResourceService;

    @Inject
    IManageDossier manageDossierService;

    @Inject
    IRepairReparation repairReparationService;

    @Inject
    IMarketLogistic marketLogisticService;

    @Inject
    IMarketStock marketStockService;

    Dossier currentDossier;

    Repair currentReparation;

    List<Profile> allProfile;

    List<Dossier> allDossier;

    List<Repair> allReparation;

    List<Repair> allOrderedReparation;

    private List<Spare> temp_fourniture;

    private List<Reparation> temp_tache;

    private List<Spare> fournitures;

    private List<Article> articles;

    private List<Reparation> taches;

    private double totalTache;

    private double totalFourniture;

    @FXML
    private AnchorPane content;

    @FXML
    private TabPane reparationTab;

    @FXML
    private Tab tab_reparations;

    @FXML
    private TableView<Repair> table_reparations;

    @FXML
    private TableColumn<Repair, Long> table_reparations_id;

    @FXML
    private TableColumn<Repair, String> table_reparations_date;

    @FXML
    private TableColumn<Repair, String> table_repartions_automobile;

    @FXML
    private TableColumn<Repair, String> table_reparations_description;

    @FXML
    private TableColumn<Repair, Integer> table_reparations_compteur;

    @FXML
    private TableColumn<Repair, String> table_reparations_profile;

    @FXML
    private Tab tab_new_reparation;

    @FXML
    private AnchorPane newTabContent;

    @FXML
    private ComboBox<Dossier> dossier_automobile;

    @FXML
    private TextField profile_reparation;

    @FXML
    private TextField profile_ordre_reparation;

    @FXML
    private TextArea new_reparation_description;

    @FXML
    private TextField new_reparation_compteur;

    @FXML
    private GridPane new_fourniture_form;

    @FXML
    private ComboBox<Article> new_designation;

    @FXML
    private ComboBox<Profile> newReparationProfile;

    @FXML
    private TextField new_prix;

    @FXML
    private TextField new_quantite;

    @FXML
    private TextField new_montant;

    @FXML
    private TableView<Spare> new_table_fourniture;

    @FXML
    private TableColumn<Spare, String> new_fourniture_designation;

    @FXML
    private TableColumn<Spare, Double> new_fourniture_prix;

    @FXML
    private TableColumn<Spare, Integer> new_fourniture_quantite;

    @FXML
    private TableColumn<Spare, Double> new_fourniture_montant;

    @FXML
    private GridPane new_tache_form;

    @FXML
    private ComboBox<Reparation> new_description;

    @FXML
    private TextField new_temps;

    @FXML
    private TextField new_taux_horaire;

    @FXML
    private TextField new_cout;

    @FXML
    private TableView<Reparation> new_table_tache;

    @FXML
    private TableColumn<Reparation, String> new_tache_description;

    @FXML
    private TableColumn<Reparation, Float> new_tache_temps;

    @FXML
    private TableColumn<Reparation, Float> new_tache_taux_horaire;

    @FXML
    private TableColumn<Reparation, Float> new_tache_cout;

    @FXML
    private Tab tab_edit_reparation;

    @FXML
    private AnchorPane listTabContent;

    @FXML
    private ComboBox<Repair> reparation_automobile;

    @FXML
    private TextArea edit_reparation_description;

    @FXML
    private GridPane edit_fourniture_form;

    @FXML
    private TextField edit_designation;

    @FXML
    private TextField edit_prix;

    @FXML
    private TextField edit_quantite;

    @FXML
    private TextField edit_montant;

    @FXML
    private TableView<Spare> edit_table_fourniture;

    @FXML
    private TableColumn<Spare, Long> edit_fourniture_id;

    @FXML
    private TableColumn<Spare, String> edit_fourniture_designation;

    @FXML
    private TableColumn<Spare, Double> edit_fourniture_prix;

    @FXML
    private TableColumn<Spare, Integer> edit_fourniture_quantite;

    @FXML
    private TableColumn<Spare, Double> edit_fourniture_montant;

    @FXML
    private GridPane edit_tache_form;

    @FXML
    private TextField edit_description;

    @FXML
    private TextField edit_temps;

    @FXML
    private TextField edit_taux_horaire;

    @FXML
    private TextField edit_cout;

    @FXML
    private TableView<Reparation> edit_table_tache;

    @FXML
    private TableColumn<Reparation, Long> edit_tache_id;

    @FXML
    private TableColumn<Reparation, String> edit_tache_description;

    @FXML
    private TableColumn<Reparation, Float> edit_tache_temps;

    @FXML
    private TableColumn<Reparation, Float> edit_tache_taux_horaire;

    @FXML
    private TableColumn<Reparation, Float> edit_tache_cout;

    @FXML
    private Tab tab_ordre_reparation;

    @FXML
    private Tab tab_controle_qualite;

    @FXML
    private AnchorPane detailTabContent;

    @FXML
    private ComboBox<Repair> reparation_ordre;

    @FXML
    private TextArea ordre_reparation_description;

    @FXML
    private TextField numero_reparation;

    @FXML
    private HBox container_fourniture_form;

    @FXML
    private VBox fourniture_form;

   @FXML
    private ComboBox<Article> ordre_designation;

    @FXML
    private TextField ordre_prix;

    @FXML
    private TextField ordre_quantite;

    @FXML
    private TextField ordre_montant;

    @FXML
    private HBox container_fourniture;

    @FXML
    private TableView<Spare> ordre_list_fourniture;

    @FXML
    private TableColumn<Spare, Long> ordre_fourniture_id;

    @FXML
    private TableColumn<Spare, String> ordre_fourniture_designation;

    @FXML
    private TableColumn<Spare, Double> ordre_fourniture_prix;

    @FXML
    private TableColumn<Spare, Integer> ordre_fourniture_quantite;

    @FXML
    private TableColumn<Spare, Double> ordre_fourniture_montant;

    @FXML
    private TextArea total_fourniture;

    @FXML
    private VBox tache_form;

    @FXML
    private ComboBox<Reparation> ordre_description;

    @FXML
    private TextField ordre_temps;

    @FXML
    private TextField ordre_taux_horaire;

    @FXML
    private TextField ordre_cout;

    @FXML
    private HBox container_tache;

    @FXML
    private TableView<Reparation> ordre_list_tache;

    @FXML
    private TableColumn<Reparation, Long> ordre_tache_id;

    @FXML
    private TableColumn<Reparation, String> ordre_tache_description;

    @FXML
    private TableColumn<Reparation, Float> ordre_tache_temps;

    @FXML
    private TableColumn<Reparation, Float> ordre_tache_taux_horaire;

    @FXML
    private TableColumn<Reparation, Float> ordre_tache_cout;

    @FXML
    private TextArea total_tache;

    @FXML
    private VBox reception_form_content;

    @FXML
    private GridPane reception_auto_form;

    @FXML
    private ComboBox<Repair> ordre_reparation;

    @FXML
    void add_fourniture(ActionEvent event) {
        Spare fourniture = new Spare();
        fourniture.setDesignation(new_designation.getEditor().getText().trim());
        fourniture.setPrix(Double.parseDouble(new_prix.getText().trim()));
        fourniture.setQuantite(Integer.parseInt(new_quantite.getText()));
        fourniture.setMontant(Double.parseDouble(new_montant.getText()));
        this.temp_fourniture.add(fourniture);
        new_table_fourniture.setItems(FXCollections.observableArrayList(temp_fourniture));
        new_designation.getEditor().setText("");
        new_prix.setText("");
        new_quantite.setText("");
        new_montant.setText("");
    }

    @FXML
    void add_ordre_fourniture(ActionEvent event) {
        Spare fourniture = new Spare();
        fourniture.setDesignation(ordre_designation.getEditor().getText().trim());
        fourniture.setPrix(Double.parseDouble(ordre_prix.getText().trim()));
        fourniture.setMontant(Double.parseDouble(ordre_montant.getText().trim()));
        fourniture.setQuantite(Integer.parseInt(ordre_quantite.getText().trim()));
        fourniture.setReparation(currentReparation);

        try {
            marketLogisticService.modifierFourniture(fourniture);
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de l'enregistrement de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        read_ordre_reparation_fourniture(currentReparation);
        ordre_designation.getEditor().setText("");
        ordre_prix.setText("");
        ordre_montant.setText("");
        ordre_quantite.setText("");
    }

    @FXML
    void add_ordre_reparation(ActionEvent event) {
        Repair reparation = repairReparationService.findRepair(reparation_ordre.getValue().getId());
        try {
            repairReparationService.editRepair(
                    new RepairReparationRequest(
                            numero_reparation.getText().trim(),
                            ordre_reparation_description.getText().trim(),
                            reparation.getCompteur(),
                            reparation.getDateCreation(),
                            reparation.getReparations(),
                            reparation.getSpares(),
                            reparation.getDossier(),
                            reparation.getProfile(),
                            true,
                            null
                    ),reparation.getId()
            );
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Echec");
            AlertError.getInstance().setHeaderText("Ordre de réparation");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }

        if(!reparation.getApproved()){
            reparation.setTotalFourniture(reparation.getTotalFourniture());
            reparation.setTotalTache(reparation.getTotalTache());
            reparation.setTotal(reparation.getTotalTache()+reparation.getTotalFourniture());
            reparation.setTotalLettre(NumberToWords.convert(reparation.getTotal().longValue()));

            try {
                repairReparationService.editRepair(
                        new RepairReparationRequest(
                                "",
                                "",
                                0,
                                null,
                                null,
                                null,
                                null,
                                null,
                                true,
                                null
                        ),reparation.getId()
                );
                AlertInfo.getInstance().setTitle("Succès");
                AlertInfo.getInstance().setHeaderText("Ordre de réparation");
                AlertInfo.getInstance().setContentText("Ordre de réparation mis à jour avec succès");
                AlertInfo.getInstance().show();
                Report.getInstance().createReport("/jrxml/ordre.jrxml",(int) reparation.getId());
            } catch (Exception e2) {
                AlertError.getInstance().setTitle("Echec");
                AlertError.getInstance().setHeaderText("Ordre de réparation");
                AlertError.getInstance().setContentText("Echec lors de la mise à jour de l'ordre de réparation.\n"+e2.getMessage());
                AlertError.getInstance().showAndWait();
                e2.printStackTrace();
            }
        }
    }

    @FXML
    void add_ordre_tache(ActionEvent event) {

        Reparation sub = new Reparation(ordre_description.getEditor().getText().trim(), Float.parseFloat(ordre_cout.getText().trim()));
        sub.setTemps(Float.parseFloat(ordre_temps.getText().trim()));
        sub.setTauxHoraire(Float.parseFloat(ordre_taux_horaire.getText().trim()));
        sub.setRepair(currentReparation);

        try {
            repairReparationService.editRepair(currentReparation,sub);
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de l'enregistrement de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }

        read_ordre_reparation_tache(currentReparation);
        ordre_description.getEditor().setText("");
        ordre_cout.setText("");
        ordre_temps.setText("");
        ordre_taux_horaire.setText("");
    }

    @FXML
    void add_qualite(ActionEvent event) {

    }

    @FXML
    void add_reparation(ActionEvent event) {

        if (this.temp_fourniture.isEmpty() || this.temp_tache.isEmpty()) {
            AlertInfo.getInstance().setTitle("Attention");
            AlertInfo.getInstance().setHeaderText("Réparation automobile");
            AlertInfo.getInstance().setContentText("Impossible de créer une réparation sans des tâches ou fournitures.");
            AlertInfo.getInstance().show();
        } else {
            Repair repair = new Repair();
            repair.setDateCreation(new Date());
            repair.setApproved(false);
            repair.setDossier(dossier_automobile.getValue());
            repair.setDescription(new_reparation_description.getText().trim());
            repair.setCompteur(Integer.parseInt(new_reparation_compteur.getText().trim()));
            repair.setProfile(newReparationProfile.getValue());

            try {
                repair = repairReparationService.createRepair(
                        new RepairReparationRequest(
                                "",
                                "",
                                0,
                                null,
                                null,
                                null,
                                null,
                                null,
                                true,
                                null
                        ));

                for (Reparation tache : temp_tache) {
                    tache.setRepair(repair);
                    repairReparationService.editRepair(repair,tache);
                }

                for (Spare fourniture : temp_fourniture) {
                    fourniture.setReparation(repair);
                    repairReparationService.editRepair(repair, fourniture);
                }

                afficherReparation(repair);
                reset_new_reparation_form();
                AlertInfo.getInstance().setTitle("Succès");
                AlertInfo.getInstance().setHeaderText("Réparation automobile");
                AlertInfo.getInstance().setContentText("Réparation automobile enregistrée avec succès.");
                AlertInfo.getInstance().showAndWait();
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Réparation automobile");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement de la réparation.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
            read_reparation();
        }
    }

    @FXML
    void add_tache(ActionEvent event) {
        Reparation tache = new Reparation(new_description.getEditor().getText(), Float.parseFloat(new_cout.getText()));
        tache.setTauxHoraire(Float.parseFloat(new_taux_horaire.getText()));
        tache.setTemps(Float.parseFloat(new_temps.getText()));
        this.temp_tache.add(tache);
        new_table_tache.setItems(FXCollections.observableArrayList(temp_tache));
        new_temps.setText("");
        new_cout.setText("");
        new_taux_horaire.setText("");
        new_description.getEditor().setText("");
    }

    @FXML
    void cancel_ordre(ActionEvent event) {
        reset_ordre_reparation_form();
    }

    @FXML
    void cancel_qualite(ActionEvent event) {

    }

    @FXML
    void cancel_reparation(ActionEvent event) {
        reset_new_reparation_form();
    }

    @FXML
    void delete_fourniture(ActionEvent event) {

        try {
            repairReparationService.deleteRepair(
                    currentReparation, edit_table_fourniture.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        read_edit_reparation_fourniture();
    }

    @FXML
    void delete_ordre_fourniture(ActionEvent event) {
        try {
            repairReparationService.deleteRepair(currentReparation,ordre_list_fourniture.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        read_ordre_reparation_fourniture(ordre_list_fourniture.getSelectionModel().getSelectedItem().getReparation());
    }

    @FXML
    void delete_reparation(ActionEvent event) {
        AlertConfirm.getInstance().setTitle("Erreur");
        AlertConfirm.getInstance().setHeaderText("Réparation automobile");
        AlertConfirm.getInstance().setContentText("Voulez-vous supprimer la réparation ?");

        if(AlertConfirm.getInstance().showAndWait().get().equals(ButtonType.OK)){
            try {
                repairReparationService.deleteRepair(currentReparation.getId());
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Réparation automobile");
                AlertError.getInstance().setContentText("Echec lors de la suppression de la réparation.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
            reset_edit_reparation_form();
            read_reparation();
        }
    }

    @FXML
    void delete_tache(ActionEvent event) {
        try {
            repairReparationService.deleteRepair(currentReparation,edit_table_tache.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        read_edit_reparation_tache();
    }

    @FXML
    void delete_ordre_tache(ActionEvent event) {
        try {
            repairReparationService.deleteRepair(currentReparation,ordre_list_tache.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
        read_ordre_reparation_tache(ordre_list_tache.getSelectionModel().getSelectedItem().getRepair());
    }

    @FXML
    void dossier_automobile(KeyEvent event) {
        FilteredList<Dossier> items = new FilteredList<>(FXCollections.observableArrayList(allDossier));
        items.setPredicate(item ->{
            String lower = dossier_automobile.getEditor().getText().toLowerCase();
            String upper = dossier_automobile.getEditor().getText().toUpperCase();
            if(item.getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Dossier> sorted = new SortedList<>(items);
        dossier_automobile.setItems(sorted);
    }

    @FXML
    void edit_cout(KeyEvent event) {
        if(!edit_temps.getText().isEmpty())
            edit_taux_horaire.setText(String.valueOf(Float.parseFloat(edit_cout.getText().trim())/Float.parseFloat(edit_temps.getText().trim())));
    }

    @FXML
    void edit_fourniture(ActionEvent event) {
        Spare fourniture = edit_table_fourniture.getSelectionModel().getSelectedItem();

        if(fourniture!=null){
            fourniture.setDesignation(edit_designation.getText().trim());
            fourniture.setPrix(Double.parseDouble(edit_prix.getText().trim()));
            fourniture.setQuantite(Integer.parseInt(edit_quantite.getText().trim()));
            fourniture.setMontant(Double.parseDouble(edit_montant.getText().trim()));
        }else{
            fourniture = new Spare();
            fourniture.setDesignation(edit_designation.getText().trim());
            fourniture.setPrix(Double.parseDouble(edit_prix.getText().trim()));
            fourniture.setQuantite(Integer.parseInt(edit_quantite.getText().trim()));
            fourniture.setMontant(Double.parseDouble(edit_montant.getText().trim()));
        }

        try {
            repairReparationService.editRepair(currentReparation,fourniture);
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Modification fourniture");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }

        read_edit_reparation_fourniture();
        edit_designation.setText("");
        edit_quantite.setText("");
        edit_montant.setText("");
        edit_prix.setText("");
    }

    @FXML
    void edit_montant(KeyEvent event) {
        if(!edit_quantite.getText().isEmpty())
            edit_prix.setText(String.valueOf(Double.parseDouble(edit_montant.getText().trim()) / Integer.parseInt(edit_quantite.getText().trim())));
    }

    @FXML
    void edit_prix(KeyEvent event) {
        if(!edit_quantite.getText().isEmpty())
            edit_montant.setText(String.valueOf(Integer.parseInt(edit_quantite.getText().trim()) * Double.parseDouble(edit_prix.getText().trim())));
    }

    @FXML
    void edit_quantite(KeyEvent event) {
        if(!edit_prix.getText().isEmpty()) {
            edit_montant.setText(String.valueOf(Integer.parseInt(edit_quantite.getText().trim()) * Double.parseDouble(edit_prix.getText().trim())));
        }else if(!edit_montant.getText().isEmpty()){
            edit_prix.setText(String.valueOf(Double.parseDouble(edit_montant.getText().trim()) / Integer.parseInt(edit_quantite.getText().trim())));
        }
    }

    @FXML
    void edit_tache(ActionEvent event) {

        Reparation tache = edit_table_tache.getSelectionModel().getSelectedItem();

        if(tache!=null){
            tache.setDescription(edit_description.getText().trim());
            tache.setCout(Float.parseFloat(edit_cout.getText().trim()));
            tache.setTauxHoraire(Float.parseFloat(edit_taux_horaire.getText().trim()));
            tache.setTemps(Float.parseFloat(edit_temps.getText().trim()));
        }else{
            tache = new Reparation();
            tache.setDescription(edit_description.getText().trim());
            tache.setCout(Float.parseFloat(edit_cout.getText().trim()));
            tache.setTauxHoraire(Float.parseFloat(edit_taux_horaire.getText().trim()));
            tache.setTemps(Float.parseFloat(edit_temps.getText().trim()));
            tache.setRepair(currentReparation);
        }

        try {
            repairReparationService.editRepair(currentReparation,tache);
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Modification réparation");
            AlertError.getInstance().setHeaderText("Réparation automobile");
            AlertError.getInstance().setContentText("Echec lors de la mise à jour de la réparation.\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }

        read_edit_reparation_tache();
        edit_temps.setText("");
        edit_taux_horaire.setText("");
        edit_cout.setText("");
        edit_description.setText("");
    }

    @FXML
    void edit_taux_horaire(KeyEvent event) {
        if(!edit_taux_horaire.getText().isEmpty())
            edit_cout.setText(String.valueOf(Float.parseFloat(edit_taux_horaire.getText()) * Float.parseFloat(edit_temps.getText().trim())));
    }

    @FXML
    void edit_temps(KeyEvent event) {
        if(!edit_taux_horaire.getText().isEmpty()) {
            edit_cout.setText(String.valueOf(Float.parseFloat(edit_taux_horaire.getText().trim()) * Float.parseFloat(edit_temps.getText().trim())));
        }else if(!edit_temps.getText().isEmpty()){
            edit_taux_horaire.setText(String.valueOf(Float.parseFloat(edit_cout.getText().trim()) / Float.parseFloat(edit_temps.getText().trim())));
        }
    }

    @FXML
    void new_cout(KeyEvent event) {
        if(!new_temps.getText().isEmpty())
            new_taux_horaire.setText(String.valueOf(Float.parseFloat(new_cout.getText().trim())/Float.parseFloat(new_temps.getText().trim())));
    }

    @FXML
    void new_montant(KeyEvent event) {
        if(!new_quantite.getText().isEmpty())
            new_prix.setText(String.valueOf(Double.parseDouble(new_montant.getText().trim()) / Integer.parseInt(new_quantite.getText().trim())));
    }

    @FXML
    void new_prix(KeyEvent event) {
        if(!new_quantite.getText().isEmpty())
            new_montant.setText(String.valueOf(Integer.parseInt(new_quantite.getText().trim()) * Double.parseDouble(new_prix.getText().trim())));
    }

    @FXML
    void new_quantite(KeyEvent event) {
        if(!new_prix.getText().isEmpty()) {
            new_montant.setText(String.valueOf(Integer.parseInt(new_quantite.getText().trim()) * Double.parseDouble(new_prix.getText().trim())));
        }else if(!new_montant.getText().isEmpty()){
            new_prix.setText(String.valueOf(Double.parseDouble(new_montant.getText().trim()) / Integer.parseInt(new_quantite.getText().trim())));
        }
    }

    @FXML
    void new_search_article(KeyEvent event) {
        FilteredList<Article> items = new FilteredList<>(FXCollections.observableArrayList(articles));
        items.setPredicate(item ->{
            String lower = new_designation.getEditor().getText().toLowerCase();
            String upper = new_designation.getEditor().getText().toUpperCase();
            if(item.getDesignation().contains(lower))
                return true;
            else
                return item.getDesignation().contains(upper);
        });
        SortedList<Article> sorted = new SortedList<>(items);
        new_designation.setItems(sorted);
    }

    @FXML
    void new_search_tache(KeyEvent event) {
        FilteredList<Reparation> items = new FilteredList<>(FXCollections.observableArrayList(taches));
        items.setPredicate(item ->{
            String lower = new_description.getEditor().getText().toLowerCase();
            String upper = new_description.getEditor().getText().toUpperCase();
            if(item.getDescription().contains(lower))
                return true;
            else
                return item.getDescription().contains(upper);
        });
        SortedList<Reparation> sorted = new SortedList<>(items);
        new_description.setItems(sorted);
    }

    @FXML
    void new_taux(KeyEvent event) {
        if(!new_taux_horaire.getText().isEmpty())
            new_cout.setText(String.valueOf(Float.parseFloat(new_taux_horaire.getText()) * Float.parseFloat(new_temps.getText().trim())));
    }

    @FXML
    void new_temps(KeyEvent event) {
        if(!new_taux_horaire.getText().isEmpty()) {
            new_cout.setText(String.valueOf(Float.parseFloat(new_taux_horaire.getText().trim()) * Float.parseFloat(new_temps.getText().trim())));
        }else if(!new_temps.getText().isEmpty()){
            new_taux_horaire.setText(String.valueOf(Float.parseFloat(new_cout.getText().trim())/Float.parseFloat(new_temps.getText().trim())));
        }
    }

    @FXML
    void ordre_cout(KeyEvent event) {
        if(!ordre_temps.getText().isEmpty())
            ordre_taux_horaire.setText(String.valueOf(Float.parseFloat(ordre_cout.getText().trim())/Float.parseFloat(ordre_temps.getText().trim())));
    }

    @FXML
    void ordre_montant(KeyEvent event) {
        if(!ordre_quantite.getText().isEmpty())
            ordre_prix.setText(String.valueOf(Double.parseDouble(ordre_montant.getText().trim()) / Integer.parseInt(ordre_quantite.getText().trim())));
    }

    @FXML
    void ordre_prix(KeyEvent event) {
        if(!ordre_quantite.getText().isEmpty())
            ordre_montant.setText(String.valueOf(Integer.parseInt(ordre_quantite.getText().trim()) * Double.parseDouble(ordre_prix.getText().trim())));
    }

    @FXML
    void ordre_quantite(KeyEvent event) {
        if(!ordre_prix.getText().isEmpty()) {
            ordre_montant.setText(String.valueOf(Integer.parseInt(ordre_quantite.getText().trim()) * Double.parseDouble(ordre_prix.getText().trim())));
        }else if(!ordre_montant.getText().isEmpty()){
            ordre_prix.setText(String.valueOf(Double.parseDouble(ordre_montant.getText().trim()) / Integer.parseInt(ordre_quantite.getText().trim())));
        }
    }

    @FXML
    void ordre_reparation(KeyEvent event){
        ObservableList<String> results = FXCollections.observableArrayList();

        List<Repair> reparations = repairReparationService.listRepair(ordre_reparation.getEditor().getText());

        for (Repair reparation:reparations) {
            StringBuilder string = new StringBuilder();
            string.append(reparation.getId());
            string.append(" / ");
            string.append(reparation.getDossier().getAutomobile().getImmatriculation());
            string.append(" / ");
            string.append(reparation.getDossier().getAutomobile().getClient().getRaisonSociale());
            string.append(" / ");
            string.append(reparation.getDateCreation());
            results.add(string.toString());
        }
    }

    @FXML
    void ordre_search_article(KeyEvent event) {
        FilteredList<Article> items = new FilteredList<>(FXCollections.observableArrayList(articles));
        items.setPredicate(item ->{
            String lower = ordre_designation.getEditor().getText().toLowerCase();
            String upper = ordre_designation.getEditor().getText().toUpperCase();
            if(item.getDesignation().contains(lower))
                return true;
            else
                return item.getDesignation().contains(upper);
        });
        SortedList<Article> sorted = new SortedList<>(items);
        ordre_designation.setItems(sorted);
    }

    @FXML
    void ordre_search_tache(KeyEvent event) {
        FilteredList<Reparation> items = new FilteredList<>(FXCollections.observableArrayList(taches));
        items.setPredicate(item ->{
            String lower = ordre_description.getEditor().getText().toLowerCase();
            String upper = ordre_description.getEditor().getText().toUpperCase();
            if(item.getDescription().contains(lower))
                return true;
            else
                return item.getDescription().contains(upper);
        });
        SortedList<Reparation> sorted = new SortedList<>(items);
        ordre_description.setItems(sorted);
    }

    @FXML
    void ordre_taux_horaire(KeyEvent event) {
        if(!ordre_taux_horaire.getText().isEmpty())
            ordre_cout.setText(String.valueOf(Float.parseFloat(ordre_taux_horaire.getText()) * Float.parseFloat(ordre_temps.getText().trim())));
    }

    @FXML
    void ordre_temps(KeyEvent event) {
        if(!ordre_taux_horaire.getText().isEmpty()) {
            ordre_cout.setText(String.valueOf(Float.parseFloat(ordre_taux_horaire.getText().trim()) * Float.parseFloat(ordre_temps.getText().trim())));
        }else if(!ordre_temps.getText().isEmpty()){
            ordre_taux_horaire.setText(String.valueOf(Float.parseFloat(ordre_cout.getText().trim()) / Float.parseFloat(ordre_temps.getText().trim())));
        }
    }

    @FXML
    void printDevis(ActionEvent event) throws Exception {
        Report.getInstance().createReport("/jrxml/devis.jrxml",(int)currentReparation.getId());
    }

    @FXML
    void printFacture(ActionEvent event) throws Exception {
        Report.getInstance().createReport("/jrxml/facture.jrxml",(int)currentReparation.getId());
    }

    @FXML
    void printOrdre(ActionEvent event) throws Exception {
        Report.getInstance().createReport("/jrxml/ordre.jrxml",(int)currentReparation.getId());
    }

    @FXML
    void remove_fourniture(ActionEvent event) {
        Spare fourniture = new_table_fourniture.getSelectionModel().getSelectedItem();
        if (this.temp_fourniture.contains(fourniture)) {
            this.temp_fourniture.remove(fourniture);
            new_table_fourniture.setItems(FXCollections.observableArrayList(temp_fourniture));
        }
    }

    @FXML
    void remove_tache(ActionEvent event) {
        Reparation tache = new_table_tache.getSelectionModel().getSelectedItem();
        if (this.temp_tache.contains(tache)) {
            this.temp_tache.remove(tache);
            new_table_tache.setItems(FXCollections.observableArrayList(temp_tache));
        }
    }

    @FXML
    void reparation_automobile(KeyEvent event) {
        FilteredList<Repair> items = new FilteredList<>(FXCollections.observableArrayList(allReparation));
        items.setPredicate(item ->{
            String lower = reparation_automobile.getEditor().getText().toLowerCase();
            String upper = reparation_automobile.getEditor().getText().toUpperCase();
            if(item.getDossier().getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getDossier().getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Repair> sorted = new SortedList<>(items);
        reparation_automobile.setItems(sorted);
    }

    @FXML
    void reparation_ordre(KeyEvent event) {
        FilteredList<Repair> items = new FilteredList<>(FXCollections.observableArrayList(allOrderedReparation));
        items.setPredicate(item ->{
            String lower = reparation_automobile.getEditor().getText().toLowerCase();
            String upper = reparation_automobile.getEditor().getText().toUpperCase();
            if(item.getDossier().getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getDossier().getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Repair> sorted = new SortedList<>(items);
        reparation_ordre.setItems(sorted);
    }

    @FXML
    void search_profile_new_reparation(KeyEvent event){
        FilteredList<Profile> items = new FilteredList<>(FXCollections.observableArrayList(allProfile));
        items.setPredicate(item ->{
            String lower = newReparationProfile.getEditor().getText().toLowerCase();
            String upper = newReparationProfile.getEditor().getText().toUpperCase();
            if(item.getPrenom().contains(lower) || item.getNom().contains(lower)){
                return true;
            }else{
                return item.getNom().contains(upper) || item.getPrenom().contains(upper);
            }
        });
        SortedList<Profile> sorted = new SortedList<>(items);
        newReparationProfile.setItems(sorted);
    }

    @FXML
    void select_edit_fourniture(MouseEvent event) {
        Spare fourniture = edit_table_fourniture.getSelectionModel().getSelectedItem();
        edit_designation.setText(fourniture.getDesignation());
        edit_quantite.setText(String.valueOf(fourniture.getQuantite()));
        edit_montant.setText(String.valueOf(fourniture.getMontant()));
        edit_prix.setText(String.valueOf(fourniture.getPrix()));
    }

    @FXML
    void select_edit_tache(MouseEvent event) {
        Reparation tache = edit_table_tache.getSelectionModel().getSelectedItem();
        edit_description.setText(tache.getDescription());
        edit_cout.setText(String.valueOf(tache.getCout()));
        edit_taux_horaire.setText(String.valueOf(tache.getTauxHoraire()));
        edit_temps.setText(String.valueOf(tache.getTemps()));
    }

    @FXML
    void to_ordre_reparation(ActionEvent event) {

        if (reparation_automobile.getValue()!=null) {

            Repair reparation = repairReparationService.findRepair(reparation_automobile.getValue().getId());

            try {
                repairReparationService.editRepair(
                        new RepairReparationRequest(
                                "",
                                edit_reparation_description.getText().trim(),
                                0,
                                null,
                                null,
                                null,
                                null,
                                null,
                                true,
                                null
                        ), reparation_automobile.getValue().getId());

                AlertInfo.getInstance().setTitle("Succès");
                AlertInfo.getInstance().setHeaderText("Réparation automobile");
                AlertInfo.getInstance().setContentText("Réparation automobile mis à jour avec succès.");
                AlertInfo.getInstance().showAndWait();
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Ordre de réparation automobile");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement de l'ordre de réparation.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
            editerReparation(reparation);
        }
        else {
            AlertError.getInstance().setTitle("Erreur");
            AlertError.getInstance().setHeaderText("Ordre de réparation");
            AlertError.getInstance().setContentText("La réparation n'existe pas.");
            AlertError.getInstance().showAndWait();
            selectTab(3);
        }
        reset_edit_reparation_form();
    }

    @FXML
    void nouvelle_reparation(ActionEvent event){
        selectTab(2);
    }

    @FXML
    void afficher_reparation(ActionEvent event){
        if(currentReparation!=null){
            reparation_automobile.setValue(currentReparation);
            reparation_automobile.getEditor().setText(currentReparation.toString());
            profile_reparation.setText(currentReparation.getProfile().toString());
            edit_reparation_description.setText(currentReparation.getDescription());
            read_edit_reparation_tache();
            read_edit_reparation_fourniture();
            selectTab(3);
        }else{
            AlertWarning.getInstance().setTitle("Erreur");
            AlertWarning.getInstance().setHeaderText("Réparation");
            AlertWarning.getInstance().setContentText("Aucune ligne sélectionné");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void editer_ordre_reparation(ActionEvent event){
        if(currentReparation!=null){
            reparation_ordre.setValue(currentReparation);
            profile_ordre_reparation.setText(currentReparation.getProfile().toString());
            read_ordre_reparation_tache(currentReparation);
            read_ordre_reparation_fourniture(currentReparation);
            ordre_reparation_description.setText(currentReparation.getDescription());
            numero_reparation.setText(currentReparation.getReference());
            selectTab(4);
        }else{
            AlertWarning.getInstance().setTitle("Erreur");
            AlertWarning.getInstance().setHeaderText("Réparation");
            AlertWarning.getInstance().setContentText("Aucune ligne sélectionné");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void supprimer_reparation(ActionEvent event){
        AlertConfirm.getInstance().setTitle("Confirmation");
        AlertConfirm.getInstance().setHeaderText("Suppression réparation");
        AlertConfirm.getInstance().setContentText("Vous allez supprimer la réparation. Cliquer sur OK pour confirmer votre action ou ANNULER pour abandonner.");
        if (AlertConfirm.getInstance().showAndWait().get()==ButtonType.OK){
            try {
                repairReparationService.deleteRepair(currentReparation.getId());
                AlertInfo.getInstance().setTitle("Succès");
                AlertInfo.getInstance().setHeaderText("Suppression réparation");
                AlertInfo.getInstance().setContentText("Réparation supprimé avec succès");
                AlertInfo.getInstance().showAndWait();
                read_reparation();
            } catch (Exception e) {
                AlertError.getInstance().setTitle("Erreur");
                AlertError.getInstance().setHeaderText("Suppression réparation");
                AlertError.getInstance().setContentText("Erreur lors de la suppression de la réparation");
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
        }
    }

    @FXML
    void controler_reparation(ActionEvent event){
        if(table_reparations.getSelectionModel().getSelectedItem()!=null){
            selectTab(5);
        }else{
            AlertWarning.getInstance().setTitle("Erreur");
            AlertWarning.getInstance().setHeaderText("Réparation");
            AlertWarning.getInstance().setContentText("Aucune ligne sélectionné");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void select_reparation(MouseEvent event){
        this.currentReparation = table_reparations.getSelectionModel().getSelectedItem();
        AlertConfirm.getInstance().setHeaderText("Rapport de réparation");
        AlertConfirm.getInstance().setContentText("Voulez-afficher la réparation?");
        if(AlertConfirm.getInstance().showAndWait().get().equals(ButtonType.OK)){
            if(currentReparation.getApproved()){
                //todo:Report.getInstance().createReport("/jrxml/ordre.jrxml",(int)currentReparation.getId());
            }else {
                AlertWarning.getInstance().setHeaderText("Réparation non autorisée");
                AlertWarning.getInstance().setContentText("Cette réparation n'a pas été autorisée.");
                AlertWarning.getInstance().show();
                //todo:Report.getInstance().createReport("/jrxml/reparation.jrxml",(int)currentReparation.getId());
            }
        }else {
            event.consume();
        }
    }

    public void selectTab(int i) {
        reparationTab.getSelectionModel().select(getTab(i));
    }

    public Tab getTab(int i) {
        Tab tab = null;
        switch (i) {
            case 1: {
                tab = tab_reparations;
                break;
            }
            case 2: {
                tab = tab_new_reparation;
                break;
            }
            case 3: {
                tab = tab_edit_reparation;
                break;
            }
            case 4: {
                tab = tab_ordre_reparation;
                break;
            }
            case 5:{
                tab=tab_controle_qualite;
            }
        }
        return tab;
    }

    void read_ordre(){
        ordre_reparation.setItems(FXCollections.observableArrayList(repairReparationService.listRepair()));
    }

    void read_reparation(){
        this.allReparation = repairReparationService.listRepair();
        show_reparation(this.allReparation);
        reparation_automobile.setItems(FXCollections.observableArrayList(allReparation));
    }

    void read_ordered_reparation(){
        allOrderedReparation = new ArrayList<>();
        for (Repair reparation:allReparation) {
            if(reparation.getApproved()){
                allOrderedReparation.add(reparation);
            }
        }
        reparation_ordre.setItems(FXCollections.observableArrayList(allOrderedReparation));
    }

    void read_article(){
        this.articles = marketStockService.listerArticle();
        new_designation.setItems(FXCollections.observableArrayList(articles));
        ordre_designation.setItems(FXCollections.observableArrayList(articles));
    }

    void read_fourniture(){
        //todo:this.fournitures = fournitureRepository.findAll();
    }

    void read_tache(){
        //todo:this.taches = tacheRepository.find();
        new_description.setItems(FXCollections.observableArrayList(taches));
    }

    void read_profile(){
        //todo: this.allProfile = profileRepository.findOuvrier();
        newReparationProfile.setItems(FXCollections.observableArrayList(allProfile));
    }

    void read_dossier(){
        //todo : this.allDossier = dossierRepository.findAll();
        SortedList<Dossier> sorted = new SortedList<>(FXCollections.observableArrayList(allDossier));
        dossier_automobile.setItems(sorted);
    }

    void read_edit_reparation_fourniture(){
        //todo : Repair reparation = reparationRepository.findById(currentReparation.getId());
        // edit_table_fourniture.setItems(FXCollections.observableArrayList(reparation.getFournitures()));
    }

    void read_edit_reparation_tache(){
        //todo : Repair reparation = reparationRepository.findById(currentReparation.getId());
        // edit_table_tache.setItems(FXCollections.observableArrayList(reparation.getTaches()));
    }

    void read_ordre_reparation_fourniture(Repair repair){
        ordre_list_fourniture.setItems(FXCollections.observableArrayList(repair.getSpares()));
        this.totalFourniture = 0.0;
        for (Spare fourniture : repair.getSpares()) {
            this.totalFourniture += fourniture.getMontant();
        }
        total_fourniture.setText(Money.getInstance().format(this.totalFourniture) + " F(CFA)");
    }

    void read_ordre_reparation_tache(Repair repair){
        Repair reparation = repairReparationService.findRepair(repair.getId());
        ordre_list_tache.setItems(FXCollections.observableArrayList(reparation.getReparations()));
        this.totalTache = 0.0;
        for (Reparation tache : reparation.getReparations()) {
            this.totalTache += tache.getCout();
        }
        total_tache.setText(Money.getInstance().format(this.totalTache) + " F(CFA)");
    }

    void reset_new_reparation_form() {
        dossier_automobile.getEditor().setText("");
        new_reparation_description.setText("");
        new_reparation_compteur.setText("");

        new_designation.getEditor().setText("");
        new_quantite.setText("");
        new_prix.setText("");
        new_montant.setText("");
        new_table_fourniture.setItems(null);

        new_description.getEditor().setText("");
        new_temps.setText("");
        new_taux_horaire.setText("");
        new_cout.setText("");
        new_table_tache.setItems(null);
        temp_fourniture.clear();
        temp_tache.clear();
    }

    void reset_edit_reparation_form() {
        reparation_automobile.getEditor().getText();
        edit_reparation_description.setText("");
        edit_designation.setText("");
        edit_quantite.setText("");
        edit_prix.setText("");
        edit_montant.setText("");
        edit_table_fourniture.setItems(null);
        edit_description.setText("");
        edit_temps.setText("");
        edit_taux_horaire.setText("");
        edit_cout.setText("");
        edit_table_tache.setItems(null);
    }

    void reset_ordre_reparation_form() {
        reparation_ordre.getEditor().getText();
        ordre_reparation_description.setText("");
        numero_reparation.setText("");
        ordre_designation.getEditor().setText("");
        ordre_quantite.setText("");
        ordre_prix.setText("");
        ordre_montant.setText("");
        ordre_list_fourniture.setItems(null);
        ordre_description.getEditor().setText("");
        ordre_temps.setText("");
        ordre_taux_horaire.setText("");
        ordre_cout.setText("");
        ordre_list_tache.setItems(null);
        total_tache.setText("");
        total_fourniture.setText("");
    }

    void show_reparation(List<Repair> reparations){
        table_reparations.setItems(FXCollections.observableArrayList(reparations));
        table_reparations.refresh();
    }

    void reset_qualite_reparation_form() {
        currentReparation = null;
    }

    void afficherReparation(Repair repair){
        Repair reparation = repairReparationService.findRepair(repair.getId());
        reparation_automobile.setValue(reparation);
        profile_reparation.setText(reparation.getProfile().toString());
        edit_reparation_description.setText(reparation.getDescription());
        edit_table_fourniture.setItems(FXCollections.observableArrayList(reparation.getSpares()));
        edit_table_tache.setItems(FXCollections.observableArrayList(reparation.getReparations()));
        selectTab(3);
    }

    void editerReparation(Repair repair){
        Repair reparation = repairReparationService.findRepair(repair.getId());
        reparation_ordre.setValue(reparation);
        ordre_reparation_description.setText(reparation.getDescription());
        numero_reparation.setText(reparation.getReference());
        read_ordre_reparation_tache(reparation);
        read_ordre_reparation_fourniture(reparation);
        selectTab(4);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.temp_fourniture = FXCollections.observableArrayList();
        this.temp_tache = FXCollections.observableArrayList();
        this.totalFourniture = 0.0;
        this.totalTache = 0.0;

        read_dossier();

        read_profile();

        read_article();

        read_fourniture();

        read_tache();

        read_ordre();

        read_reparation();

        read_ordered_reparation();

        table_reparations_id.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        table_reparations_date.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd/MM/yyyy").format(r.getValue().getDateCreation())));
        table_reparations_description.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        table_repartions_automobile.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDossier().getAutomobile().getImmatriculation()));
        table_reparations_compteur.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getCompteur()));
        table_reparations_profile.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getProfile().getNom()+" "+r.getValue().getProfile().getPrenom()));

        new_fourniture_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        new_fourniture_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        new_fourniture_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        new_fourniture_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));

        new_tache_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        new_tache_temps.setCellValueFactory(new PropertyValueFactory<>("temps"));
        new_tache_taux_horaire.setCellValueFactory(new PropertyValueFactory<>("tauxHoraire"));
        new_tache_cout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        edit_fourniture_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        edit_fourniture_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        edit_fourniture_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        edit_fourniture_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        edit_fourniture_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));

        edit_tache_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        edit_tache_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        edit_tache_temps.setCellValueFactory(new PropertyValueFactory<>("temps"));
        edit_tache_taux_horaire.setCellValueFactory(new PropertyValueFactory<>("tauxHoraire"));
        edit_tache_cout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        ordre_fourniture_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ordre_fourniture_designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        ordre_fourniture_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ordre_fourniture_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        ordre_fourniture_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));

        ordre_tache_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ordre_tache_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        ordre_tache_temps.setCellValueFactory(new PropertyValueFactory<>("temps"));
        ordre_tache_taux_horaire.setCellValueFactory(new PropertyValueFactory<>("tauxHoraire"));
        ordre_tache_cout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        newReparationProfile.setConverter(new StringConverter<Profile>() {
            @Override
            public String toString(Profile object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Profile fromString(String string) {
                String[] data = string.split("\\s");
                String id = data[0].trim();
                return manageResourceService.findProfile(Long.parseLong(id));
            }
        });

        dossier_automobile.setConverter(new StringConverter<Dossier>() {
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

        reparation_automobile.setConverter(new StringConverter<Repair>() {
            @Override
            public String toString(Repair object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Repair fromString(String string) {
                String[] data = string.split("\\s");
                String id = data[0].trim();
                return repairReparationService.findRepair(Long.parseLong(id));
            }
        });

        reparation_ordre.setConverter(new StringConverter<Repair>() {
            @Override
            public String toString(Repair object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Repair fromString(String string) {
                String[] data = string.split("/");
                String id = data[0].trim();
                return repairReparationService.findRepair(Long.parseLong(id));
            }
        });

        ordre_reparation.setConverter(new StringConverter<Repair>() {
            @Override
            public String toString(Repair object) {
                if(object==null){
                    return null;
                }
                return object.toString();
            }

            @Override
            public Repair fromString(String string) {
                String[] data = string.split("/");
                String id = data[0].trim();
                return repairReparationService.findRepair(Long.parseLong(id));
            }
        });

        dossier_automobile.addEventHandler(ActionEvent.ACTION, event -> {
            if (dossier_automobile.getSelectionModel().getSelectedIndex() != -1) {
                this.currentDossier = dossier_automobile.getValue();
            }
        });

        reparation_automobile.addEventHandler(ActionEvent.ACTION, event -> {
            if (reparation_automobile.getSelectionModel().getSelectedIndex() != -1) {
                this.currentReparation = reparation_automobile.getValue();
                profile_reparation.setText(currentReparation.getProfile().toString());
                edit_reparation_description.setText(currentReparation.getDescription());
                edit_table_tache.setItems(FXCollections.observableArrayList(currentReparation.getReparations()));
                edit_table_fourniture.setItems(FXCollections.observableArrayList(currentReparation.getSpares()));
            }
        });

        reparation_ordre.addEventHandler(ActionEvent.ACTION, event -> {
            if (reparation_ordre.getSelectionModel().getSelectedIndex() != -1) {
                this.currentReparation = reparation_ordre.getValue();
                profile_ordre_reparation.setText(currentReparation.getProfile().toString());
                ordre_reparation_description.setText(currentReparation.getDescription());
                numero_reparation.setText(currentReparation.getReference());
                read_ordre_reparation_tache(currentReparation);
                read_ordre_reparation_fourniture(currentReparation);
            }
        });

        ordre_reparation.addEventHandler(ActionEvent.ACTION, event -> {
            if (ordre_reparation.getSelectionModel().getSelectedIndex() != -1) {
                this.currentReparation = ordre_reparation.getValue();
            }
        });

        new_designation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Article>() {
            @Override
            public void changed(ObservableValue<? extends Article> observable, Article oldValue, Article newValue) {
                new_prix.setText(String.valueOf(newValue.getPrix()));
            }
        });

        ordre_designation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Article>() {
            @Override
            public void changed(ObservableValue<? extends Article> observable, Article oldValue, Article newValue) {
                ordre_prix.setText(String.valueOf(newValue.getPrix()));
            }
        });

        reparationTab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                allReparation = repairReparationService.listRepair();
            }
        });
    }
}