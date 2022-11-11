package controller;

import app.model.*;
import app.service.manager.IManageAutomobile;
import app.service.manager.IManageCashflow;
import app.service.manager.IManageClient;
import app.service.manager.IManageResource;
import app.service.marketer.IMarketPurchase;
import app.service.marketer.IMarketSale;
import app.service.marketer.IMarketStock;
import app.service.repairer.IRepairReparation;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.text.Text;
import view.Spinner;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{

    IManageResource manageResourceService;
    IManageCashflow manageCashflowService;
    IManageClient manageClientService;
    IManageAutomobile manageAutomobileService;
    IRepairReparation repairReparationService;
    IMarketStock marketStockService;
    IMarketPurchase marketPurchaseService;
    IMarketSale marketSaleService;
    DateFormat df = new SimpleDateFormat("MM-yyyy");

    @FXML
    private Text count_profil;

    @FXML
    private Text count_client;

    @FXML
    private Text count_automobile;

    @FXML
    private Text count_reparation;

    @FXML
    private Text count_article;

    @FXML
    private Text count_commande;

    @FXML
    private Text count_vente;

    @FXML
    private Text count_versement;

    @FXML
    private Text count_salaire;

    @FXML
    private Text count_depense;

    @FXML
    private BarChart<String, Double> chart_reparation;

    @FXML
    private CategoryAxis chart_reparation_x;

    @FXML
    private NumberAxis chart_reparation_y;

    @FXML
    private BarChart<String, Double> chart_vente;

    @FXML
    private CategoryAxis chart_vente_x;

    @FXML
    private NumberAxis chart_vente_y;

    @FXML
    private AreaChart<String, Double> chart_cashflow;

    @FXML
    private CategoryAxis chart_cashflow_x;

    @FXML
    private NumberAxis chart_cashflow_y;

    public void setCount_profil(Text count_profil) {
        this.count_profil=count_profil;
    }

    public void setCount_client(Text count_client) {
        this.count_client=count_client;
    }

    public void setCount_automobile(Text count_automobile) {
        this.count_automobile=count_automobile;
    }

    public void setCount_reparation(Text count_reparation) {
        this.count_reparation=count_reparation;
    }

    public void setCount_article(Text count_article) {
        this.count_article=count_article;
    }

    public BarChart<String, Double> getChart_reparation() {
        return chart_reparation;
    }

    public BarChart<String, Double> getChart_vente() {
        return chart_vente;
    }

    public AreaChart<String, Double> getChart_cashflow() {
        return chart_cashflow;
    }

    public void processStatistic(){
        List<Profile> profiles = manageResourceService.listProfile();
        count_profil.setText(String.valueOf(profiles.size()));

        List<Client> clients = manageClientService.listClient();
        count_client.setText(String.valueOf(clients.size()));

        List<Automobile> automobiles = manageAutomobileService.listAutomobile();
        count_automobile.setText(String.valueOf(automobiles.size()));

        List<Repair> reparations = repairReparationService.listRepair();
        count_reparation.setText(String.valueOf(reparations.size()));

        List<Article> articles = marketStockService.listerArticle();
        count_article.setText(String.valueOf(articles.size()));

        List<Billing> versements = manageCashflowService.listBilling();
        count_versement.setText(String.valueOf(versements.size()));
    }

    public void processReparationParPeriode(){

        List<String> periodes = new ArrayList<>();

        List<Repair> reparations = new SortedList<Repair>(FXCollections.observableArrayList(repairReparationService.listRepair()), new Comparator<Repair>() {
            @Override
            public int compare(Repair r1, Repair r2) {
                return r1.getDateCreation().compareTo(r2.getDateCreation());
            }
        });

        //Séries des prestations
        XYChart.Series<String,Double> tacheParPeriode = new XYChart.Series<>();
        tacheParPeriode.setName("Prestations");

        //Séries des fournitures
        XYChart.Series<String,Double> fournitureParPeriode = new XYChart.Series<>();
        fournitureParPeriode.setName("Fournitures");

        for (Repair reparation : reparations) {
            do {
                periodes.add(df.format(reparation.getDateCreation()));
            }while(!periodes.contains(df.format(reparation.getDateCreation())));
        }

        for (String periode:periodes) {
            double montantTache = 0;
            double montantFourniture = 0;
            for (Repair reparation : reparations) {
                montantFourniture+=reparation.getTotalFourniture();
                montantTache+=reparation.getTotalTache();
            }
            tacheParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantTache));
            fournitureParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantFourniture));
        }

        getChart_reparation().getData().add(fournitureParPeriode);
        getChart_reparation().getData().add(tacheParPeriode);
    }

    public void processVenteParPeriode(){

        List<String> periodes = new ArrayList<>();

        List<Sale> ventes = new SortedList<Sale>(FXCollections.observableArrayList(marketSaleService.listerVente()), new Comparator<Sale>() {
            @Override
            public int compare(Sale o1, Sale o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        List<Purchase> achats = new SortedList<Purchase>(FXCollections.observableArrayList(marketPurchaseService.listerAchat()), new Comparator<Purchase>() {
            @Override
            public int compare(Purchase o1, Purchase o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        //Séries des prestations
        XYChart.Series<String,Double> venteParPeriode = new XYChart.Series<>();
        venteParPeriode.setName("Ventes");

        //Séries des fournitures
        XYChart.Series<String,Double> achatParPeriode = new XYChart.Series<>();
        achatParPeriode.setName("Achats");

        for (Purchase achat : achats) {
            do {
                periodes.add(df.format(achat.getDate()));
            }while(!periodes.contains(df.format(achat.getDate())));
        }

        for (Sale vente : ventes) {
            do {
                periodes.add(df.format(vente.getDate()));
            }while(!periodes.contains(df.format(vente.getDate())));
        }

        for (String periode:periodes) {
            double montantAchat = 0;
            double montantVente = 0;
            for (Purchase achat : achats) {
                montantAchat+=achat.getMontant();
            }
            for (Sale vente : ventes) {
                montantVente+=vente.getMontant();
            }
            venteParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantVente));
            achatParPeriode.getData().add(new XYChart.Data<String,Double>(periode,montantAchat));
        }

        getChart_vente().getData().add(venteParPeriode);
        getChart_vente().getData().add(achatParPeriode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Spinner.getInstance().show();

        javafx.concurrent.Service<Void> load = new javafx.concurrent.Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        processStatistic();

                        processVenteParPeriode();

                        processReparationParPeriode();

                        return null;
                    }
                };
            }
        };

        load.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue){
                case FAILED:
                case CANCELLED:
                case SUCCEEDED:
                    Spinner.getInstance().hide();
            }
        });

        load.start();
    }
}

