package Applications.Controllers;

import Applications.Gateways.HuskyKennelGateway;
import Binding.ViewModels.HuskyClientViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mix.model.Enums.TrainingStatus;
import mix.model.Husky;
import mix.model.Owner;

import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HuskyClientController implements FxmlView<HuskyClientViewModel>, Initializable {

    @InjectViewModel //is provided by mvvmFX
    private HuskyClientViewModel viewModel = new HuskyClientViewModel();

    /**
     * GUI Objects
     */
    //region UI
    @FXML
    private TextField helloLabel;

    @FXML
    private ListView listViewHuskies;



    //endregion
    private HuskyKennelGateway kennelGateway;
    private Owner owner;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloLabel.textProperty().bind(viewModel.helloMessage());

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
