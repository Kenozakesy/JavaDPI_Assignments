package Applications.Controllers;

import Applications.Gateways.HuskyKennelToClientGateway;
import Applications.Gateways.HuskyKennelToSchoolGatewayPublic;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mix.Aggregator.Aggregator;
import mix.model.Husky;
import mix.model.HuskyTestReply;
import mix.model.HuskyTestRequest;
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


    //region receive_message

    //receives
    public void receiveHuskyFromClient(Husky husky)
    {
        kennel.addHusky(husky);
        loadScreen();

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

        //hier moet de daadwerkelijke husky verzonden worden set to check

        //husky krijgt school toegewezen
        //kennel drukt op versturen

        if(finalReply != null) { //zelf invulling geven
            AggregatorList.remove(aggr);
            broker.add(request, finalReply);
            LoanReply loanReply = new LoanReply(finalReply.getInterest(), finalReply.getQuoteId());
            JListLine jlistLine = broker.getRequestReply(request);
            LoanRequest loanRequest = jlistLine.getLoanRequest();
            loanClientGateway.sendLoanReply(loanRequest, loanReply);
        }

    }

    //endregion
}
