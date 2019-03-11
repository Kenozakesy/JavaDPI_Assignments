package Applications.Controllers;

import Applications.Gateways.HuskyKennelGateway;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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

//    @InjectViewModel //is provided by mvvmFX
//    private LoginViewModel viewModel;

    //endregion

    private HuskyKennelGateway kennelGateway;
    private Owner owner;

    public void initialize() {
        kennelGateway = new HuskyKennelGateway();
        owner = new Owner("Henk", "Dam", new Date(1992,3,21));
        loadScreen();
    }

    @FXML
    public void sendDog()
    {
        Husky husky = (Husky)listViewHuskies.getSelectionModel().getSelectedItem();
        //kennelGateway.sendHuskyToKennel(husky);
        husky.setStatus(TrainingStatus.InKennel);
        loadScreen();
    }

    private void loadScreen()
    {
        listViewHuskies.getItems().removeAll();
        listViewHuskies.getItems().addAll(owner.getHuskyList());
    }
}
