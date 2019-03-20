package Applications.Controllers;

import Applications.Gateways.HuskySchoolToKennelGatewayPrivate;
import Applications.Gateways.HuskySchoolToKennelGatewayPublic;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mix.model.*;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class SchoolController {

    /**
     * GUI Objects
     */

    @FXML
    private ListView listViewHuskies;

    private HuskySchoolToKennelGatewayPrivate kennelGatewayPrivate;
    private HuskySchoolToKennelGatewayPublic kennelGatewayPublic;

    private HuskySchool school =  new HuskySchool();

    public void initialize() {

        kennelGatewayPublic = new HuskySchoolToKennelGatewayPublic(this);
        kennelGatewayPrivate = new HuskySchoolToKennelGatewayPrivate(this);
        loadScreen();
    }

    private void loadScreen()
    {
        listViewHuskies.getItems().clear();
        listViewHuskies.getItems().addAll(school.getHuskyList());
    }

    //region GUI_interaction

    public void TrainHusky()
    {

    }

    //endregion

    //region receive_messages

    public void receiveTestRequestFromClient(HuskyTestRequest request)
    {
        //calculate score
        HuskyScore score = school.calculatePotential(); //moet de husky later in mee worden genomen

        //give back score
        HuskyTestReply reply = new HuskyTestReply(request.getHusky(), score, kennelGatewayPrivate.getReceiverGateway(), request.getAggregatorID());

        //send back reply score
        kennelGatewayPublic.sendMessage(reply);
    }

    //endregion
}
