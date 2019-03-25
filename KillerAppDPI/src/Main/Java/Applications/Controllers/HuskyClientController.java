package Applications.Controllers;

import Applications.Gateways.HuskyClientToKennelGateway;
import javafx.application.Platform;
import javafx.fxml.FXML;
import mix.model.Enums.TrainingStatus;
import mix.model.Husky;
import mix.model.Owner;
import javafx.scene.control.*;
import java.util.Date;


public class HuskyClientController {
    /**
     * GUI Objects
     */
    //region UI

    @FXML
    private ListView listViewHuskies;

    @FXML
    private ListView listViewTrainedHuskies;


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
            listViewHuskies.getItems().addAll(owner.getNotTrainedHuskyList());
            listViewTrainedHuskies.getItems().clear();
            listViewTrainedHuskies.getItems().addAll(owner.getTrainedHuskyList());
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
