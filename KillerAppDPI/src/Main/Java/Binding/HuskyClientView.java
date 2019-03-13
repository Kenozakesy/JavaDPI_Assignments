package Binding;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gebruiker on 12-3-2019.
 */
public class HuskyClientView implements FxmlView<HuskyClientViewModel>, Initializable{

    @FXML
    private Label helloLabel;

    @InjectViewModel
    private HuskyClientViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloLabel.textProperty().bindBidirectional(viewModel.helloMessageProperty());
    }
}
