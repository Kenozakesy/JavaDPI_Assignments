package Applications.Controllers;

import Applications.Gateways.HuskyKennelToClientGateway;
import Applications.Gateways.HuskyKennelToSchoolGatewayPublic;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mix.Aggregator.Aggregator;
import mix.model.Husky;
import mix.model.Kennel;

import java.util.ArrayList;
import java.util.List;

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

    private List<Aggregator> AggregatorList = new ArrayList<>();

    private HuskyKennelToSchoolGatewayPublic schoolGatewayPublic;
    private HuskyKennelToClientGateway clientGateway;
    private Kennel kennel;

    public void initialize() {
        kennel = new Kennel();
        clientGateway = new HuskyKennelToClientGateway(this);
        schoolGatewayPublic = new HuskyKennelToSchoolGatewayPublic(this);
        loadScreen();
    }

    public void loadScreen()
    {
        listViewHuskies.getItems().clear();
        listViewHuskies.getItems().addAll(kennel.getHuskyList());
    }


    //region recieve_message

    //receives
    public void receiveHuskyFromClient(Husky husky)
    {
        kennel.addHusky(husky);
        loadScreen();

        Aggregator aggregator = new Aggregator(husky, 1);
        AggregatorList.add(aggregator);

        //here send to different schools
        schoolGatewayPublic.sendMessage(husky); //moeten meerdere opvangen
    }

    //endregion
}
