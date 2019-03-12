package Applications.Controllers;

import Applications.Gateways.HuskyClientGateway;
import Applications.Gateways.HuskyKennelGateway;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mix.model.Enums.TrainingStatus;
import mix.model.Husky;
import mix.model.Kennel;
import mix.model.Owner;

import java.util.Date;

/**
 * Created by Gebruiker on 12-3-2019.
 */
public class KennelController {

    /**
     * GUI Objects
     */
    //region UI

    @FXML
    private ListView listViewHuskies;

    //endregion

    private HuskyClientGateway clientGateway;
    private Kennel kennel;

    public void initialize() {
        kennel = new Kennel();
        clientGateway = new HuskyClientGateway(kennel, this);
        loadScreen();
    }

    public void loadScreen()
    {
        listViewHuskies.getItems().clear();
        listViewHuskies.getItems().addAll(kennel.getHuskyList());
    }
}
