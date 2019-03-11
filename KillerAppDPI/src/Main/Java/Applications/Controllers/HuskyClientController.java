package Applications.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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

    //endregion


    private Owner owner;

    public void initialize() {
        owner = new Owner("Henk", "Dam", new Date(1992,3,21));
        listViewHuskies.getItems().addAll(owner.getHuskyList());
    }

    @FXML
    public void sendDog()
    {
        System.out.println("test");
    }
}
