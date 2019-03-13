package Binding;

import Applications.Gateways.HuskyKennelGateway;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import de.saxsys.mvvmfx.utils.viewlist.ViewListCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mix.model.Husky;
import mix.model.Owner;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Gebruiker on 12-3-2019.
 */
public class HuskyClientView implements FxmlView<HuskyClientViewModel>, Initializable{

    @FXML
    private ListView<Husky> huskyList;

    @InjectViewModel
    private HuskyClientViewModel viewModel;



    private HuskyKennelGateway kennelGateway;
    private Owner owner;

    public HuskyClientView()
    {
        huskyList = new ListView<>();
        owner = new Owner("Henk", "Dam", new Date(1992,3,21));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kennelGateway = new HuskyKennelGateway(this);

        //helloLabel.textProperty().bindBidirectional(viewModel.helloMessageProperty());

        huskyList.setItems((ObservableList<Husky>) owner.getHuskyList());

//        ViewListCellFactory<HuskyClientViewModel> cellFactory =
//        CachedViewModelCellFactory.createForFxmlView(HuskyClientView.class);
//        huskyList.setCellFactory(cellFactory);


    }

    public void sendDog()
    {
        System.out.println("bark");
    }
}
