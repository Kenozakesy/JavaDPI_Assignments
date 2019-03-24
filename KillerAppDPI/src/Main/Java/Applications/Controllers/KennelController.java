package Applications.Controllers;

import Applications.Gateways.HuskyKennelToClientGateway;
import Applications.Gateways.HuskyKennelToSchoolGatewayPrivate;
import Applications.Gateways.HuskyKennelToSchoolGatewayPublic;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mix.Aggregator.Aggregator;
import mix.model.Enums.TrainingStatus;
import mix.model.Husky;
import mix.model.Replies.HuskyTestReply;
import mix.model.Replies.HuskyTrainReply;
import mix.model.Requests.HuskyTestRequest;
import mix.model.Kennel;
import mix.model.Requests.HuskyTrainingRequest;

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
    private HuskyKennelToSchoolGatewayPrivate schoolGatewayPrivate;
    private HuskyKennelToClientGateway clientGateway;
    private Kennel kennel;

    public void initialize() {
        kennel = new Kennel();
        clientGateway = new HuskyKennelToClientGateway(this);
        schoolGatewayPublic = new HuskyKennelToSchoolGatewayPublic(this);
        schoolGatewayPrivate = new HuskyKennelToSchoolGatewayPrivate(this);
        loadScreen();
    }

    public void loadScreen()
    {
        Platform.runLater(() -> {
            listViewHuskies.getItems().clear();
            listViewHuskies.getItems().addAll(kennel.getHuskyList());
        });
    }

    //region UI_interaction

    public void sendToTraining()
    {
        Husky husky = (Husky)listViewHuskies.getSelectionModel().getSelectedItem(); //this must be changed to husky request
        if(husky != null && husky.getStatus() == TrainingStatus.UpForTraining) {

            husky.setStatus(TrainingStatus.InTraining);
            HuskyTrainingRequest request = new HuskyTrainingRequest(husky, husky.getSchoolAddress());
            schoolGatewayPrivate.sendMessage(request);
            loadScreen();
        }
        else
        {
            System.out.println("please select a dog first that can be trained");
        }
    }

    //endregion

    //region receive_message

    public void receiveHuskyFromClient(Husky husky)
    {
        kennel.addHusky(husky);
        Platform.runLater(() -> loadScreen());

        Aggregator aggregator = new Aggregator(husky, 1);
        AggregatorList.add(aggregator);

        HuskyTestRequest testRequest = new HuskyTestRequest(husky, aggregator.getAggregationID().getID());

        //here send to different schools
        schoolGatewayPublic.sendMessage(testRequest); //moeten meerdere opvangen
    }

    public void receiveHuskyTestReply(HuskyTestReply reply)
    {
        HuskyTestReply finalReply = null;
        Aggregator aggr = null;
        for (Aggregator A : AggregatorList)
        {
            if(A.getAggregationID().getID() == reply.getAggregatorID())
            {
                finalReply = A.addReply(reply);
                if(finalReply != null)
                {
                    aggr = A;
                }
                break;
            }
        }

        if(finalReply != null) { //zelf invulling geven
            AggregatorList.remove(aggr);

            //husky terug krijgen
            kennel.updateHuskyStatus(finalReply); //update status kan getrained worden

            Platform.runLater(() -> loadScreen());
        }

    }

    public void receiveHuskyTrainReply(HuskyTrainReply reply)
    {
        Husky husky = kennel.updateHuskyToTrained(reply.getHusky());

        //send husky back to client
        clientGateway.sendMessage(husky);

        //remove from kennel
        kennel.removeHusky(husky);

        loadScreen();
    }

    //endregion
}
