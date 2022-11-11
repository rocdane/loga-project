package controller;

import api.CashflowBuilder;
import app.model.*;
import app.service.manager.IManageCashflow;
import app.service.manager.IManageDossier;
import app.service.manager.IManageGarage;
import app.service.manager.IManageResource;
import app.service.marketer.IMarketPurchase;
import app.service.marketer.IMarketSale;
import app.service.repairer.IRepairReparation;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import vendor.io.Money;
import view.AlertError;
import view.AlertInfo;
import view.AlertWarning;

import javax.inject.Inject;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TresorController implements Initializable {

    @Inject
    IManageGarage garageService;
    @Inject
    IManageCashflow cashflowService;

    CashflowBuilder cashflowBuilder;

    @Inject
    IManageDossier dossierService;

    @Inject
    IManageResource resourceService;

    @Inject
    IRepairReparation repairReparationService;

    @Inject
    IMarketSale marketSaleService;

    @Inject
    IMarketPurchase marketPurchaseService;

    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Date debut = null;
    public Date fin = null;
    public Finance finance = null;
    public Date exercice = null;
    Repair repair;
    Profile profile;
    List<Profile> allProfile;
    List<Repair> allReparation;

    @FXML
    private TabPane tresor_tab;

    @FXML
    private Tab tab_compte;

    @FXML
    private DatePicker compte_date;

    @FXML
    private TextField recette_description;

    @FXML
    private TextField recette_montant;

    @FXML
    private Button add_recette;

    @FXML
    private TableView<Recipe> table_recette;

    @FXML
    private TableColumn<Recipe, Long> t_recette_id;

    @FXML
    private TableColumn<Recipe, String> t_recette_date;

    @FXML
    private TableColumn<Recipe, String> t_recette_description;

    @FXML
    private TableColumn<Recipe, Double> t_recette_montant;

    @FXML
    private Button remove_recette;

    @FXML
    private TextField total_recette_compte;

    @FXML
    private TextField depense_description;

    @FXML
    private TextField depense_montant;

    @FXML
    private Button add_depense;

    @FXML
    private TableView<Spent> table_depense;

    @FXML
    private TableColumn<Spent, Long> t_depense_id;

    @FXML
    private TableColumn<Spent, String> t_depense_date;

    @FXML
    private TableColumn<Spent, String> t_depense_description;

    @FXML
    private TableColumn<Spent, Double> t_depense_montant;

    @FXML
    private Button remove_depense;

    @FXML
    private TextField total_depense_compte;

    @FXML
    private Tab tab_tresor;

    @FXML
    private DatePicker compte_date_debut;

    @FXML
    private DatePicker compte_date_fin;

    @FXML
    private TableView<Recipe> table_recette_tresor;

    @FXML
    private TableColumn<Recipe, Long> tresor_recette_id;

    @FXML
    private TableColumn<Recipe, String> tresor_recette_date;

    @FXML
    private TableColumn<Recipe, String> tresor_recette_description;

    @FXML
    private TableColumn<Recipe, Double> tresor_recette_montant;

    @FXML
    private TextField total_recette_tresor;

    @FXML
    private TableView<Spent> table_depense_tresor;

    @FXML
    private TableColumn<Spent, Long> tresor_depense_id;

    @FXML
    private TableColumn<Spent, String> tresor_depense_date;

    @FXML
    private TableColumn<Spent, String> tresor_depense_description;

    @FXML
    private TableColumn<Spent, Double> tresor_depense_montant;

    @FXML
    private TextField total_depense_tresor;

    @FXML
    private DatePicker bilan_date_debut;

    @FXML
    private DatePicker bilan_date_fin;

    @FXML
    private TableView<Repair> table_bilan;

    @FXML
    private TableColumn<Repair, String> bilan_date;

    @FXML
    private TableColumn<Repair, String> bilan_client;

    @FXML
    private TableColumn<Repair, String> bilan_automobile;

    @FXML
    private TableColumn<Repair, Double> bilan_fourniture;

    @FXML
    private TableColumn<Repair, Double> bilan_prestation;

    @FXML
    private TableColumn<Repair, Double> bilan_total;

    @FXML
    private TextField bilan_total_fourniture;

    @FXML
    private TextField bilan_total_reparation;

    @FXML
    private TextField bilan_total_tache;

    @FXML
    private Tab tab_commerce;

    @FXML
    private DatePicker commerce_date_debut;

    @FXML
    private DatePicker commerce_date_fin;

    @FXML
    private TableView<Purchase> table_achats;

    @FXML
    private TableColumn<Purchase, Long> achat_id;

    @FXML
    private TableColumn<Purchase, Article> achat_article;

    @FXML
    private TableColumn<Purchase, Integer> achat_quantite;

    @FXML
    private TableColumn<Purchase, Double> achat_montant;

    @FXML
    private TextField total_achat;

    @FXML
    private TableView<Sale> table_ventes;

    @FXML
    private TableColumn<Sale, Long> vente_id;

    @FXML
    private TableColumn<Sale, Article> vente_article;

    @FXML
    private TableColumn<Sale, Integer> vente_quantite;

    @FXML
    private TableColumn<Sale, Double> vente_montant;

    @FXML
    private TextField total_vente;

    @FXML
    private ComboBox<Repair> reglement_reparation;

    @FXML
    private TextField reglement_montant;

    @FXML
    private TextField reglement_solde;

    @FXML
    private TextField reglement_versement;

    @FXML
    private ComboBox<Billing.Mode> reglement_mode;

    @FXML
    private TextField reglement_reference;

    @FXML
    private TableView<Billing> table_solde;

    @FXML
    private TableColumn<Billing, String> solde_date;

    @FXML
    private TableColumn<Billing, String> solde_mode;

    @FXML
    private TableColumn<Billing, Double> solde_versement;

    @FXML
    private Tab tab_salaire;

    @FXML
    private ComboBox<Profile> profile_salaire;

    @FXML
    private ComboBox<Salary.Mode> mode_salaire;

    @FXML
    private TextField montant_salaire;

    @FXML
    private TextField base_salaire;

    @FXML
    private TextField prime_salaire;

    @FXML
    private TextField ipts_salaire;

    @FXML
    private TextField indemnite_salaire;

    @FXML
    private TextField sup_salaire;

    @FXML
    private TextField po_salaire;

    @FXML
    private TextField reference_salaire;

    @FXML
    private DatePicker date_salaire;

    @FXML
    private TableColumn<Salary, Profile> salaire_profile;

    @FXML
    private TableColumn<Salary, String> salaire_mode;

    @FXML
    private TableColumn<Salary, Integer> salaire_montant;

    @FXML
    private TableColumn<Salary,String> salaire_date;

    @FXML
    private TableView<Salary> table_salaire;

    @FXML
    void add_depense(ActionEvent event){
        if(!(exercice==null)){
            Spent depense = new Spent();
            depense.setDate(exercice);
            depense.setFinance(finance);
            depense.setDescription(depense_description.getText());
            depense.setMontant(Double.parseDouble(depense_montant.getText()));

            try {
                cashflowService.editCashflow(depense,finance);
                read_compte(finance);
                depense_description.setText("");
                depense_montant.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void add_recette(ActionEvent event){
        if(!(exercice==null)){
            Recipe recette = new Recipe();
            recette.setDate(exercice);
            recette.setFinance(finance);
            recette.setDescription(recette_description.getText());
            recette.setMontant(Double.parseDouble(recette_montant.getText()));
            try {
                cashflowService.editCashflow(recette,finance);
                read_compte(finance);
                recette_description.setText("");
                recette_montant.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void remove_depense(ActionEvent event){
        try {
            cashflowService.deleteSpent(table_depense.getSelectionModel().getSelectedItem().getId());
            read_compte(finance);
        } catch (Exception e) {
            AlertWarning.getInstance().setHeaderText("Compte de trésorerie");
            AlertWarning.getInstance().setContentText("Impossible de supprimer une dépense vérouillée.");
            AlertWarning.getInstance().showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void remove_recette(ActionEvent event){
        try {
            cashflowService.deleteRecipe(table_recette.getSelectionModel().getSelectedItem().getId());
            finance = cashflowService.readCashflow(exercice);
            read_compte(finance);
        } catch (Exception e) {
            AlertWarning.getInstance().setHeaderText("Compte de trésorerie");
            AlertWarning.getInstance().setContentText("Impossible de supprimer une recette vérouillée.");
            AlertWarning.getInstance().showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void annuler_reglement(ActionEvent event) {
        reset_reglement_form();
    }

    @FXML
    void annuler_salaire(ActionEvent event) {
        reset_salaire_form();
    }

    private void reset_salaire_form() {
        table_salaire.setItems(null);
        profile_salaire.setItems(null);
        montant_salaire.setText("");
    }

    @FXML
    void cancel(ActionEvent event) {
        reset_compte();
        compte_date.getEditor().setText("");
    }

    @FXML
    void closeBilan(ActionEvent event){
        LocalDate localDate = bilan_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || debut.equals(fin)){
            List<Repair> reparations = repairReparationService.listRepair(debut,fin);
            read_bilan(reparations);
        }else{
            AlertWarning.getInstance().setHeaderText("Rapport périodique");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates.\n Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void closeCommerce(ActionEvent event) {
        LocalDate localDate = compte_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || debut.equals(fin)){
            List<Sale> ventes = marketSaleService.listerVente(debut,fin);

            List<Purchase> achats = marketPurchaseService.listerAchat(debut,fin);

            read_commerce(ventes,achats);
        }else{
            AlertWarning.getInstance().setHeaderText("Rapport périodique");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates.\n Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void closeTresor(ActionEvent event){
        LocalDate localDate = compte_date_fin.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        fin = Date.from(zonedDateTime.toInstant());

        if(!(debut==null) && debut.before(fin) || debut.equals(fin)){
            List<Finance> tresors = cashflowService.listCashflow(debut,fin);
            read_tresor(tresors);
        }else{
            AlertWarning.getInstance().setHeaderText("Compte de trésorerie");
            AlertWarning.getInstance().setContentText("Une erreur s'est produite lors de la sélection des dates. Vérifiez et reessayez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void enregistrer_reglement(ActionEvent event) {
        if(repair !=null && Double.parseDouble(reglement_versement.getText())>0 && Double.parseDouble(reglement_solde.getText())>=Double.parseDouble(reglement_versement.getText()) && reglement_mode.getSelectionModel().getSelectedItem()!=null){
            Billing versement = new Billing();
            versement.setDateVersement(new Date());
            versement.setRepair(repair);
            versement.setMode(reglement_mode.getSelectionModel().getSelectedItem());
            versement.setVersement(Double.parseDouble(reglement_versement.getText()));
            versement.setReference(reglement_reference.getText().trim());

            try {
                cashflowService.createBilling(versement);
                reglement_versement.setText("");
            } catch (Exception e) {
                AlertError.getInstance().setHeaderText("Billing");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement du versement.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }

            read_solde();
        }else{
            AlertWarning.getInstance().setHeaderText("Billing");
            AlertWarning.getInstance().setContentText("Vérifiez les informations que vous entrez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void enregistrer_salaire(ActionEvent event) {
        LocalDate localDate = date_salaire.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        Date date = Date.from(zonedDateTime.toInstant());

        if(Integer.parseInt(montant_salaire.getText())>0){
            Salary salaire = new Salary();
            salaire.setMontant(Integer.parseInt(montant_salaire.getText()));
            salaire.setHeureSup(Integer.parseInt(sup_salaire.getText()));
            salaire.setIndemnite(Integer.parseInt(indemnite_salaire.getText()));
            salaire.setIpts(Integer.parseInt(ipts_salaire.getText()));
            salaire.setPartOuvriere(Integer.parseInt(po_salaire.getText()));
            salaire.setMode(mode_salaire.getEditor().getText());
            salaire.setMontant(Integer.parseInt(montant_salaire.getText()));
            salaire.setReference(reference_salaire.getText());
            salaire.setDate(date);

            try {
                cashflowService.createSalary(salaire);
            } catch (Exception e) {
                AlertError.getInstance().setHeaderText("Salary");
                AlertError.getInstance().setContentText("Echec lors de l'enregistrement du salaire.\n"+e.getMessage());
                AlertError.getInstance().showAndWait();
                e.printStackTrace();
            }
            read_salaire();
        }else{
            AlertWarning.getInstance().setHeaderText("Salary");
            AlertWarning.getInstance().setContentText("Vérifiez les informations que vous entrez.");
            AlertWarning.getInstance().showAndWait();
        }
    }

    @FXML
    void fermer(ActionEvent event) {
        reset_tresor();
    }

    @FXML
    void fermerCommerce(ActionEvent event) {
        reset_commerce();
    }

    @FXML
    void fermerBilan(ActionEvent event) {
        reset_bilan();
    }

    @FXML
    void openBilan(ActionEvent event){
        LocalDate localDate = bilan_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());
    }

    @FXML
    void openCommerce(ActionEvent event) {
        LocalDate localDate = commerce_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());
    }

    @FXML
    void openCompte(ActionEvent event){

        reset_compte();

        LocalDate localDate = compte_date.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        exercice = Date.from(zonedDateTime.toInstant());

        try {
            finance = cashflowService.readCashflow(exercice);
        } catch (Exception e) {
            AlertError.getInstance().setHeaderText("Compte journalier");
            AlertError.getInstance().setContentText("Impossible de créer un compte de trésorerie.");
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }

        if(finance ==null){
            if(!exercice.after(new Date())){
                finance = new Finance();
                finance.setDate(exercice);
                finance.setAtelier(garageService.readAtelier(1L));

                try {
                    finance = cashflowService.createCashflow(finance);

                    AlertInfo.getInstance().setHeaderText("Compte journalier");
                    AlertInfo.getInstance().setContentText("Nouveau compte journalier ("+(new SimpleDateFormat("dd/MM/yyyy")).format(finance.getDate())+") créé avec succès.");
                    AlertInfo.getInstance().showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                AlertWarning.getInstance().setHeaderText("Compte journalier");
                AlertWarning.getInstance().setContentText("Impossible de créer un compte de trésorerie à l'avance.");
                AlertWarning.getInstance().showAndWait();
            }
        }else {
            AlertWarning.getInstance().setHeaderText("Compte journalier");
            AlertWarning.getInstance().setContentText("Un compte de trésorerie existe pour la date : "+new SimpleDateFormat("dd/MM/yyyy").format(exercice));
            AlertWarning.getInstance().show();
            read_compte(finance);
        }
    }

    @FXML
    void openTresor(ActionEvent event){
        LocalDate localDate = compte_date_debut.getValue();

        ZoneId systemTimeZone = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(systemTimeZone);

        debut = Date.from(zonedDateTime.toInstant());
    }

    @FXML
    void print(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            //todo:Report.getInstance().createReport("/jrxml/tresor.jrxml",debut,fin);
        }
    }

    @FXML
    void printBilan(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            //todo:Report.getInstance().createReport("/jrxml/bilan.jrxml",debut,fin);
        }
    }

    @FXML
    void printCommerce(ActionEvent event) {
        if(!(debut==null) && !(fin==null)){
            //todo:Report.getInstance().createReport("/jrxml/commerce.jrxml",debut,fin);
        }
    }

    @FXML
    void printCompte(ActionEvent event) {
        if(!(exercice==null)){
            //todo:Report.getInstance().createReport("/jrxml/compte.jrxml",exercice);
        }
    }

    @FXML
    void search_ordre(KeyEvent event) {
        FilteredList<Repair> items = new FilteredList<>(FXCollections.observableArrayList(allReparation));
        items.setPredicate(item ->{
            String lower = reglement_reparation.getEditor().getText().toLowerCase();
            String upper = reglement_reparation.getEditor().getText().toUpperCase();
            if(item.getDossier().getAutomobile().getImmatriculation().contains(lower))
                return true;
            else
                return item.getDossier().getAutomobile().getImmatriculation().contains(upper);
        });
        SortedList<Repair> sorted = new SortedList<>(items);
        reglement_reparation.setItems(sorted);
    }

    @FXML
    void search_profile(KeyEvent event) {
        FilteredList<Profile> items = new FilteredList<>(FXCollections.observableArrayList(allProfile));
        items.setPredicate(item ->{
            String lower = profile_salaire.getEditor().getText().toLowerCase();
            String upper = profile_salaire.getEditor().getText().toUpperCase();
            if(item.getNom().contains(lower) || item.getPrenom().contains(lower))
                return true;
            else
                return item.getNom().contains(upper) || item.getPrenom().contains(upper);
        });
        SortedList<Profile> sorted = new SortedList<>(items);
        profile_salaire.setItems(FXCollections.observableArrayList(sorted));
    }

    void read_bilan(List<Repair> reparations){

        table_bilan.setItems(FXCollections.observableArrayList(reparations));

        List<Spare> fournitures = new ArrayList<>();

        List<Reparation> taches = new ArrayList<>();

        for (Repair reparation:reparations) {
            fournitures.addAll(reparation.getSpares());
            taches.addAll(reparation.getReparations());
        }

        double total_fourniture=0;
        for (Spare fourniture:fournitures) {
            total_fourniture+=fourniture.getMontant();
        }
        bilan_total_fourniture.setText(Money.getInstance().format(total_fourniture)+" Fcfa");

        double total_tache=0;
        for (Reparation tache:taches){
            total_tache+=tache.getCout();
        }
        bilan_total_tache.setText(total_tache+" Fcfa");

        double total_reparation = total_tache + total_fourniture;
        bilan_total_reparation.setText(Money.getInstance().format(total_reparation)+" Fcfa");
    }

    void read_commerce(List<Sale> ventes,List<Purchase> achats){
        table_ventes.setItems(FXCollections.observableArrayList(ventes));
        double vente_total = 0;
        for (Sale vente:ventes) {
            vente_total+=vente.getMontant();
        }
        total_vente.setText(Money.getInstance().format(vente_total)+" F (CFA)");

        table_achats.setItems(FXCollections.observableArrayList(achats));
        double achat_total = 0;
        for (Purchase achat:achats) {
            achat_total+=achat.getMontant();
        }
        total_achat.setText(Money.getInstance().format(achat_total)+" F (CFA)");
    }

    void read_compte(Finance tresor){

        table_recette.setItems(FXCollections.observableArrayList(tresor.getRecipes()));

        table_recette.refresh();

        double total_recette=0;

        for (Recipe recette:tresor.getRecipes()){
            AlertInfo.getInstance().setContentText(recette.getDescription()+" | "+recette.getMontant());
            total_recette+=recette.getMontant();
        }

        total_recette_compte.setText(Money.getInstance().format(total_recette)+" Fcfa");

        table_depense.setItems(FXCollections.observableArrayList(tresor.getSpents()));

        double total_depense=0;

        for (Spent depense:tresor.getSpents()) {
            total_depense+=depense.getMontant();
        }

        total_depense_compte.setText(Money.getInstance().format(total_depense)+" Fcfa");
    }

    void read_tresor(List<Finance> tresors){

        List<Spent> depenses = new ArrayList<>();

        List<Recipe> recettes = new ArrayList<>();

        for (Finance tresor:tresors) {
            depenses.addAll(tresor.getSpents());
            recettes.addAll(tresor.getRecipes());
        }

        table_depense_tresor.setItems(FXCollections.observableArrayList(depenses));

        table_recette_tresor.setItems(FXCollections.observableArrayList(recettes));

        double total_depense=0;
        for (Spent depense:depenses) {
            total_depense+=depense.getMontant();
        }
        total_depense_tresor.setText(Money.getInstance().format(total_depense)+" Fcfa");

        double total_recette=0;
        for (Recipe recette:recettes){
            total_recette+=recette.getMontant();
        }
        total_recette_tresor.setText(Money.getInstance().format(total_recette)+" Fcfa");
    }

    void read_solde(){
        repair = reglement_reparation.getSelectionModel().getSelectedItem();
        double solde=0;
        for (Billing versement: repair.getBillings()) {
            solde+=versement.getVersement();
        }
        reglement_montant.setText(String.valueOf(repair.getTotal()));
        reglement_solde.setText(String.valueOf(repair.getTotal()-solde));
        table_solde.setItems(FXCollections.observableArrayList(repair.getBillings()));
    }

    void read_salaire(){
        table_salaire.setItems(FXCollections.observableArrayList(cashflowService.listSalary()));
    }

    void reset_reglement_form(){
        table_solde.setItems(null);
        reglement_reparation.setItems(null);
        reglement_reparation.getEditor().setText("");
        reglement_montant.setText("");
        reglement_solde.setText("");
        reglement_versement.setText("");
    }

    void reset_compte(){
        recette_description.setText("");
        recette_montant.setText("");
        total_recette_compte.setText("");
        table_recette.setItems(null);
        depense_description.setText("");
        total_depense_compte.setText("");
        table_depense.setItems(null);
        depense_montant.setText("");
    }

    void reset_tresor(){
        compte_date_debut.getEditor().setText("");
        compte_date_fin.getEditor().setText("");
        table_recette_tresor.setItems(null);
        table_recette_tresor.refresh();
        table_depense_tresor.setItems(null);
        table_depense_tresor.refresh();
        total_recette_tresor.setText("");
        total_depense_tresor.setText("");
    }

    void reset_bilan(){
        bilan_total_reparation.setText("");
        bilan_total_fourniture.setText("");
        bilan_total_tache.setText("");
        bilan_date_debut.getEditor().setText("");
        bilan_date_fin.getEditor().setText("");
        table_bilan.setItems(null);
        table_bilan.refresh();
    }

    void reset_commerce(){
        commerce_date_debut.getEditor().setText("");
        compte_date_fin.getEditor().setText("");
        table_achats.setItems(null);
        table_achats.refresh();
        table_ventes.setItems(null);
        table_ventes.refresh();
        total_achat.setText("");
        total_vente.setText("");
    }

    void read_profile(){
        allProfile = resourceService.listProfile();
        profile_salaire.setItems(FXCollections.observableArrayList(allProfile));
    }

    void read_reparation(){
        allReparation = repairReparationService.listRepair();
        reglement_reparation.setItems(FXCollections.observableArrayList(allReparation));
    }

    public void initialize(URL location, ResourceBundle resources) {

        read_profile();

        read_reparation();

        reglement_reparation.setConverter(new StringConverter<Repair>() {
            @Override
            public String toString(Repair object) {
                if(object==null)
                    return null;
                return object.toString();
            }

            @Override
            public Repair fromString(String string) {
                String line = reglement_reparation.getEditor().getText();
                String[] dossier = line.split("\\s");
                return repairReparationService.findRepair(Long.parseLong(dossier[0].trim()));
            }
        });

        profile_salaire.setConverter(new StringConverter<Profile>() {
            @Override
            public String toString(Profile object) {
                if(object==null)
                    return null;
                return object.toString();
            }

            @Override
            public Profile fromString(String string) {
                String line = profile_salaire.getEditor().getText();
                String[] id = line.split("\\s");
                return resourceService.findProfile(Long.parseLong(id[0].trim()));
            }
        });

        reglement_reparation.addEventHandler(ActionEvent.ACTION, event -> {
            if (reglement_reparation.getSelectionModel().getSelectedIndex() != -1) {
                read_solde();
            }
        });

        profile_salaire.addEventHandler(ActionEvent.ACTION, event -> {
            if (profile_salaire.getSelectionModel().getSelectedIndex() != -1) {
                profile = profile_salaire.getSelectionModel().getSelectedItem();
            }
        });

        reglement_mode.getItems().setAll(Billing.Mode.values());

        mode_salaire.getItems().setAll(Salary.getModes());

        salaire_date.setCellValueFactory((TableColumn.CellDataFeatures<Salary, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDate())));
        salaire_mode.setCellValueFactory((TableColumn.CellDataFeatures<Salary, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMode()));
        salaire_montant.setCellValueFactory((TableColumn.CellDataFeatures<Salary, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));
        salaire_profile.setCellValueFactory((TableColumn.CellDataFeatures<Salary, Profile> r)->new ReadOnlyObjectWrapper<>(r.getValue().getProfile()));

        solde_date.setCellValueFactory((TableColumn.CellDataFeatures<Billing, String> r)->new ReadOnlyObjectWrapper<>(new SimpleDateFormat("dd-MM-YYYY").format(r.getValue().getDateVersement())));
        solde_mode.setCellValueFactory((TableColumn.CellDataFeatures<Billing, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMode().name()));
        solde_versement.setCellValueFactory((TableColumn.CellDataFeatures<Billing, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getVersement()));

        t_recette_id.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        t_recette_date.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDate())));
        t_recette_description.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        t_recette_montant.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        t_depense_id.setCellValueFactory((TableColumn.CellDataFeatures<Spent, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        t_depense_date.setCellValueFactory((TableColumn.CellDataFeatures<Spent, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDate())));
        t_depense_description.setCellValueFactory((TableColumn.CellDataFeatures<Spent, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        t_depense_montant.setCellValueFactory((TableColumn.CellDataFeatures<Spent, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        tresor_recette_id.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        tresor_recette_date.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDate())));
        tresor_recette_description.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        tresor_recette_montant.setCellValueFactory((TableColumn.CellDataFeatures<Recipe, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        tresor_depense_id.setCellValueFactory((TableColumn.CellDataFeatures<Spent, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        tresor_depense_date.setCellValueFactory((TableColumn.CellDataFeatures<Spent, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDate())));
        tresor_depense_description.setCellValueFactory((TableColumn.CellDataFeatures<Spent, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDescription()));
        tresor_depense_montant.setCellValueFactory((TableColumn.CellDataFeatures<Spent, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        bilan_date.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>((new SimpleDateFormat("dd-MM-yyyy")).format(r.getValue().getDateCreation())));
        bilan_automobile.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDossier().getAutomobile().getImmatriculation()));
        bilan_client.setCellValueFactory((TableColumn.CellDataFeatures<Repair, String> r)->new ReadOnlyObjectWrapper<>(r.getValue().getDossier().getAutomobile().getClient().getRaisonSociale()));
        bilan_fourniture.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotalFourniture()));
        bilan_prestation.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotalTache()));
        bilan_total.setCellValueFactory((TableColumn.CellDataFeatures<Repair, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getTotal()));

        achat_id.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        achat_article.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, Article> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle()));
        achat_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        achat_montant.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));

        vente_id.setCellValueFactory((TableColumn.CellDataFeatures<Sale, Long> r)->new ReadOnlyObjectWrapper<>(r.getValue().getId()));
        vente_article.setCellValueFactory((TableColumn.CellDataFeatures<Sale, Article> r)->new ReadOnlyObjectWrapper<>(r.getValue().getArticle()));
        vente_quantite.setCellValueFactory((TableColumn.CellDataFeatures<Sale, Integer> r)->new ReadOnlyObjectWrapper<>(r.getValue().getQuantite()));
        vente_montant.setCellValueFactory((TableColumn.CellDataFeatures<Sale, Double> r)->new ReadOnlyObjectWrapper<>(r.getValue().getMontant()));
    }
}
