package Applications.Controllers;

import Applications.Gateways.HuskySchoolToKennelGatewayPrivate;
import Applications.Gateways.HuskySchoolToKennelGatewayPublic;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mix.model.*;
import mix.model.Enums.TrainingStatus;
import mix.model.Replies.HuskyTestReply;
import mix.model.Replies.HuskyTrainReply;
import mix.model.Requests.HuskyTestRequest;
import mix.model.Requests.HuskyTrainingRequest;

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
        Platform.runLater(() -> {
            listViewHuskies.getItems().clear();
            listViewHuskies.getItems().addAll(school.getHuskyList());
        });
    }

    //region GUI_interaction

    public void TrainHusky()
    {
        Husky husky = (Husky)listViewHuskies.getSelectionModel().getSelectedItem(); //this must be changed to husky request
        if(husky != null && husky.getStatus() == TrainingStatus.InTraining) {

            husky.setStatus(TrainingStatus.Trained);
            HuskyTrainReply reply = new HuskyTrainReply(husky);
            kennelGatewayPrivate.sendMessage(reply);
            school.removeHusky(husky);
            loadScreen();
        }
        else
        {
            System.out.println("please select a dog first that can be trained");
        }
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

    public void receiveTrainRequestFromClient(HuskyTrainingRequest request)
    {
        school.addHusky(request.getHusky());
        loadScreen();
    }

    //endregion
}
