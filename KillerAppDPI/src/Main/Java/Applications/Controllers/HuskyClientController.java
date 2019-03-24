package Applications.Controllers;

import Applications.Gateways.HuskyClientToKennelGateway;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Screen;
import mix.model.Enums.TrainingStatus;
import mix.model.Husky;
import mix.model.Owner;

import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HuskyClientController {
    /**
     * GUI Objects
     */
    //region UI

    @FXML
    private ListView listViewHuskies;


    //endregion
    private HuskyClientToKennelGateway kennelGateway;
    private Owner owner;

    public HuskyClientController()
    {

    }

    public void initialize() {
        kennelGateway = new HuskyClientToKennelGateway(this);
        owner = new Owner("Henk", "Dam", new Date(1992,3,21));
        loadScreen();
    }

    @FXML
    public void sendDog()
    {
        Husky husky = (Husky)listViewHuskies.getSelectionModel().getSelectedItem();
        if(husky != null && husky.getStatus() == TrainingStatus.NotTrained) {
            husky.setStatus(TrainingStatus.InKennel);
            kennelGateway.sendHuskyToKennel(husky);
            loadScreen();
        }
        else
        {
            System.out.println("please select a dog first that is not trained");
        }
    }

    private void loadScreen()
    {
        Platform.runLater(() -> {
            listViewHuskies.getItems().clear();
            listViewHuskies.getItems().addAll(owner.getHuskyList());
        });
    }

    //region receive_messages

    public void receiveTrainedHusky(Husky husky)
    {
        owner.updateHuskyStatus(husky);
        loadScreen();
    }

    //endregion
}
