package Applications.Controllers;

import Applications.Gateways.HuskyKennelGateway;
import Binding.HuskyClientViewModel;
import de.saxsys.mvvmfx.FxmlView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mix.model.Enums.TrainingStatus;
import mix.model.Husky;
import mix.model.Owner;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HuskyClientController implements FxmlView<HuskyClientViewModel>, Initializable {

    /**
     * GUI Objects
     */
    //region UI

    @FXML
    private ListView listViewHuskies;

    @FXML
    private TextField helloLabel;
    private StringProperty prop = new SimpleStringProperty("Hello World");


    //endregion
    private HuskyKennelGateway kennelGateway;
    private Owner owner;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //bind properties here
        helloLabel.textProperty().bindBidirectional(prop);





//        kennelGateway = new HuskyKennelGateway(this);
//        owner = new Owner("Henk", "Dam", new Date(1992,3,21));
//        loadScreen();
    }

    @FXML
    public void sendDog()
    {
        Husky husky = (Husky)listViewHuskies.getSelectionModel().getSelectedItem();
        if(husky != null) {
            husky.setStatus(TrainingStatus.InKennel);
            kennelGateway.sendHuskyToKennel(husky);
            loadScreen();
        }
        else
        {

        }
    }

    private void loadScreen()
    {
        listViewHuskies.getItems().clear();
        listViewHuskies.getItems().addAll(owner.getHuskyList());
    }
}
