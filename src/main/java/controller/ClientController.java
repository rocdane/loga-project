package controller;

import app.model.Automobile;
import app.model.Client;
import app.service.manager.IManageClient;
import app.service.manager.IManageDossier;
import app.service.manager.ManageClientRequest;
import app.service.manager.ManageDossierRequest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.text.Text;
import view.AlertConfirm;
import view.AlertError;
import view.AlertInfo;
import view.AlertWarning;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable
{
    IManageClient manageClientService;

    IManageDossier manageDossierService;

    List<Client> allClients;

    Client currentClient;

    @FXML
    private Text nombre_client;

    @FXML
    private AnchorPane content;

    @FXML
    private TabPane clientTab;

    @FXML
    private Tab tabNew;

    @FXML
    private AnchorPane newTabContent;

    @FXML
    private GridPane newClientForm;

    @FXML
    private ComboBox<Client.Type> newTypeClient;

    @FXML
    private TextField newRaisonsociale;

    @FXML
    private TextField newMentionLegale;

    @FXML
    private TextField newAdresse;

    @FXML
    private TextField newContact;

    @FXML
    private TextField newVille;

    @FXML
    private GridPane newAutoForm;

    @FXML
    private TextField newImmatriculation;

    @FXML
    private TextField newMarque;

    @FXML
    private TextField newModele;

    @FXML
    private ComboBox<Automobile.Compteur> newTypeCompteur;

    @FXML
    private TextField newCompteur;

    @FXML
    private TextField newChassis;

    @FXML
    private HBox btnArea;

    @FXML
    private Button btnEnregistrer;

    @FXML
    private Button btnAnnuler;

    @FXML
    private Tab tabList;

    @FXML
    private AnchorPane listTabContent;

    @FXML
    private TextField filtre_client;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> typeClient;

    @FXML
    private TableColumn<Client, String> raisonSociale;

    @FXML
    private TableColumn<Client, String> mentionLegale;

    @FXML
    private TableColumn<Client, String> adresse;

    @FXML
    private TableColumn<Client, String> contact;

    @FXML
    private TableColumn<Client, String> ville;

    @FXML
    private TableColumn<Client, Long> id;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnImprimer;

    @FXML
    private Tab tabDetail;

    @FXML
    private AnchorPane detailTabContent;

    @FXML
    private GridPane newClientForm1;

    @FXML
    private ComboBox<Client.Type> clientType;

    @FXML
    private TextField clientRaisonsociale;

    @FXML
    private TextField clientMentionLegale;

    @FXML
    private TextField clientAdresse;

    @FXML
    private TextField clientContact;

    @FXML
    private TextField clientVille;

    @FXML
    private TextArea clientAutomobiles;

    @FXML
    private TextField clientId;

    @FXML
    private Button sauvegarderProfil;

    @FXML
    private Button archiverProfile;

    @FXML
    private Button fermerProfile;

    @FXML
    void apply(ActionEvent event) {
        try {
            ClientController.this.manageClientService.editClient(
                    new ManageClientRequest(
                            clientRaisonsociale.getText().trim(),
                            clientType.getValue().name(),
                            clientMentionLegale.getText().trim(),
                            clientAdresse.getText().trim(),
                            clientVille.getText().trim(),
                            clientContact.getText().trim()
                    ),
                    currentClient.getId()
            );
            AlertInfo.getInstance().setTitle("Modification réussie");
            AlertInfo.getInstance().setHeaderText("Succès de mise à jour");
            AlertInfo.getInstance().setContentText("Client mis à jour avec succès.");
            AlertInfo.getInstance().showAndWait();
            selectTab(2);
            readAll();
            emptyField();
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Echec de modification");
            AlertError.getInstance().setHeaderText("Echec de modification du client");
            AlertError.getInstance().setContentText("La modification est terminé avec échec\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
    }
    
    @FXML
    void archive(ActionEvent event) {
        AlertConfirm.getInstance().setTitle("Confirmation");
        AlertConfirm.getInstance().setHeaderText("Archivage de client");
        AlertConfirm.getInstance().setContentText("Cette action supprimera le client. Voulez-vous continuer ?");
        if(AlertConfirm.getInstance().showAndWait().get().equals(ButtonType.OK)){
            if (currentClient != null) {
                try {
                    manageClientService.deleteClient(currentClient.getId());
                    AlertInfo.getInstance().setTitle("Opération réussie");
                    AlertInfo.getInstance().setHeaderText("Succès d'archivage");
                    AlertInfo.getInstance().setContentText(currentClient.getRaisonSociale()+" archivé avec succès.");
                    AlertInfo.getInstance().show();
                    selectTab(2);
                    readAll();
                } catch (Exception e) {
                    AlertWarning.getInstance().setTitle("Attention à la suppression");
                    AlertWarning.getInstance().setHeaderText("Impossible de procéder à l'archivage");
                    AlertWarning.getInstance().setContentText("Le client possède au moins un automobile\n"+e.getMessage());
                    AlertWarning.getInstance().showAndWait();
                    e.printStackTrace();
                }
            }
        }else {
            event.consume();
        }
    }
    
    @FXML
    void delete(ActionEvent event) {
        AlertConfirm.getInstance().setTitle("Confirmation");
        AlertConfirm.getInstance().setHeaderText("Archivage de client");
        AlertConfirm.getInstance().setContentText("Cette action supprimera le client. Voulez-vous continuer ?");
        if(AlertConfirm.getInstance().showAndWait().get().equals(ButtonType.OK)){
            if (currentClient != null) {
                try {
                    manageClientService.deleteClient(currentClient.getId());
                    AlertInfo.getInstance().setTitle("Opération réussie");
                    AlertInfo.getInstance().setHeaderText("Succès d'archivage");
                    AlertInfo.getInstance().setContentText(currentClient.getRaisonSociale()+" archivé avec succès.");
                    AlertInfo.getInstance().show();
                    selectTab(2);
                    readAll();
                } catch (Exception e) {
                    AlertWarning.getInstance().setTitle("Attention à la suppression");
                    AlertWarning.getInstance().setHeaderText("Impossible de procéder à l'archivage");
                    AlertWarning.getInstance().setContentText("Le client possède au moins un automobile\n"+e.getMessage());
                    AlertWarning.getInstance().showAndWait();
                    e.printStackTrace();
                }
            }
        }else {
            event.consume();
        }
    }
    
    @FXML
    void edit(ActionEvent event) {
        clientType.getSelectionModel().select(Client.Type.valueOf(currentClient.getType().name()));
        clientRaisonsociale.setText(currentClient.getRaisonSociale());
        clientMentionLegale.setText(currentClient.getRaisonSociale());
        clientAdresse.setText(currentClient.getAdresse());
        clientVille.setText(currentClient.getVille());
        clientContact.setText(currentClient.getContact());
        clientId.setText(String.valueOf(currentClient.getId()));
        StringBuilder link = new StringBuilder();
        for (Automobile auto : currentClient.getAutomobiles()) {
            link.append(auto.getMarque() + " ");
            link.append(auto.getModele() + " | ");
            link.append(auto.getImmatriculation());
            link.append("\n");
        }
        clientAutomobiles.setText(link.toString());
        selectTab(3);
    }

    @FXML
    void filtrer_client(KeyEvent event) {
        FilteredList<Client> items = new FilteredList<>(FXCollections.observableArrayList(allClients));
        items.setPredicate(item ->{
            String lower = filtre_client.getText().toLowerCase();
            String upper = filtre_client.getText().toUpperCase();
            if(item.getRaisonSociale().contains(lower))
                return true;
            else
                return item.getRaisonSociale().contains(upper);
        });
        SortedList<Client> sorted = new SortedList<>(items);
        clientTable.setItems(sorted);
    }
    
    @FXML
    void print(ActionEvent event) {
        AlertConfirm.getInstance().setTitle("Confirmation");
        AlertConfirm.getInstance().setHeaderText("Impression de liste des clients");
        AlertConfirm.getInstance().setContentText("Voulez-vous imprimer la liste des clients?");
        AlertConfirm.getInstance().showAndWait();
        //print code
    }
    
    @FXML
    void quit(ActionEvent event) {
        emptyField();
        readAll();
        selectTab(2);
    }
    
    @FXML
    void reset(ActionEvent event) {
        emptyField();
    }

    @FXML
    void select_client(MouseEvent event) {
        currentClient = clientTable.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    void submit(ActionEvent event) {
        Automobile automobile = new Automobile();
        automobile.setImmatriculation(newImmatriculation.getText().trim());
        automobile.setChassis(newChassis.getText().trim());
        automobile.setMarque(newMarque.getText().trim());
        automobile.setModele(newModele.getText().trim());
        automobile.setTypeCompteur(newTypeCompteur.getSelectionModel().getSelectedItem().name());
        automobile.setCompteur(Integer.parseInt(newCompteur.getText().trim()));

        try {
            Client client = manageClientService.createClient(
                    new ManageClientRequest(
                            newRaisonsociale.getText().trim(),
                            newTypeClient.getValue().name(),
                            newMentionLegale.getText().trim(),
                            newAdresse.getText().trim(),
                            newVille.getText().trim(),
                            newContact.getText().trim()
                    )
            );

            manageDossierService.createDossier(
                    new ManageDossierRequest(
                            "GMCPLUS/"+automobile.getImmatriculation()+"/"+client.getRaisonSociale(),
                            client,
                            automobile
                    )
            );

            AlertInfo.getInstance().setTitle("Succès d'enregistrement");
            AlertInfo.getInstance().setHeaderText("Nouveau client enregistré");
            AlertInfo.getInstance().setContentText(automobile.getImmatriculation()+" de "+client.getRaisonSociale()+" enregistré avec succès.");
            AlertInfo.getInstance().show();
            selectTab(2);
            readAll();
            emptyField();
        } catch (Exception e) {
            AlertError.getInstance().setTitle("Echec de l'enregistrement");
            AlertError.getInstance().setHeaderText("Echec de l'enregistrement du nouveau client");
            AlertError.getInstance().setContentText("L'enregistrement est terminé avec échec\n"+e.getMessage());
            AlertError.getInstance().showAndWait();
            e.printStackTrace();
        }
    }
    
    public void selectTab(int i) {
        clientTab.getSelectionModel().select(getTab(i));
    }
    
    public Tab getTab(int i) {
        Tab tab = null;
        switch (i) {
            case 1: {
                tab = tabNew;
                break;
            }
            case 2: {
                tab = tabList;
                break;
            }
            case 3: {
                tab = tabDetail;
                break;
            }
        }
        return tab;
    }
    
    void emptyField() {
        newRaisonsociale.setText("");
        newMentionLegale.setText("");
        newAdresse.setText("");
        newVille.setText("");
        newContact.setText("");
        newImmatriculation.setText("");
        newChassis.setText("");
        newMarque.setText("");
        newModele.setText("");
        newCompteur.setText("");
        clientRaisonsociale.setText("");
        clientMentionLegale.setText("");
        clientAdresse.setText("");
        clientVille.setText("");
        clientContact.setText("");
        clientAutomobiles.setText("");
    }
    
    void readAll() {
        clientTable.getItems().clear();
        this.allClients = FXCollections.observableArrayList(ClientController.this.manageClientService.listClient());
        clientTable.setItems(FXCollections.observableArrayList(allClients));
        nombre_client.setText(String.valueOf(allClients.size()));
    }
    
    public void initialize(URL location, ResourceBundle resources) {
        newTypeClient.setItems(FXCollections.observableArrayList(Client.getTypes()));
        clientType.setItems(FXCollections.observableArrayList(Client.getTypes()));

        typeClient.setCellValueFactory(new PropertyValueFactory<>("type"));
        raisonSociale.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));
        mentionLegale.setCellValueFactory(new PropertyValueFactory<>("mentionLegale"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        newTypeCompteur.getItems().setAll(Automobile.getCompteurs());

        clientTab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                if (tab.getTabPane().getSelectionModel().getSelectedIndex() == 1) {
                    readAll();
                }
            }
        });
    }
}
