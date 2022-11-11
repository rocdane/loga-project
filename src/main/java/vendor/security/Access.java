package vendor.security;

import controller.GuiController;
import view.AlertWarning;
import view.View;
import javafx.scene.control.ButtonType;

public class Access{

    public void limitAccess(){
        AlertWarning.getInstance().setHeaderText("Contrôle d'accès !!!");
        AlertWarning.getInstance().setContentText("Désolé, vous ne pouvez pas continuer. Votre niveau d'accès est limité.");
        if(AlertWarning.getInstance().showAndWait().get().equals(ButtonType.OK)){
            redirect("/fxml/home.fxml");
            View.getInstance().getGui().setTitle("GMC PLUS | Accueil");
            GuiController.getInstance().setNavigation("Accueil");
        }
    }

    public void redirect(String view){
        GuiController.getInstance().setContent(view);
    }
}
