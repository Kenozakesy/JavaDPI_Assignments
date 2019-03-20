package Applications.Controllers;

import Applications.Gateways.HuskySchoolToKennelGatewayPrivate;
import Applications.Gateways.HuskySchoolToKennelGatewayPublic;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mix.model.Husky;
import mix.model.HuskySchool;
import mix.model.HuskyScore;
import mix.model.HuskyTestReply;

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

    public void receiveHuskyFromClient(Husky husky)
    {
        //calculate score
        HuskyScore score = school.calculatePotential(husky);
        System.out.println(score.getScore());

        //HuskyTestReply reply = new HuskyTestReply(husky, score, kennelGatewayPrivate.)


        //send back reply score
        //kennelGatewayPublic.sendMessage(husky);
    }

    //endregion
}
