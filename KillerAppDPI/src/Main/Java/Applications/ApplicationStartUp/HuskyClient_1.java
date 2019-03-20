package Applications.ApplicationStartUp;

import Binding.HuskyClientView;
import Binding.HuskyClientViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HuskyClient_1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../../HuskyClientView.fxml"));
        primaryStage.setTitle("HuskyClient_1");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
//        primaryStage.setTitle("Hello World Application");
//        ViewTuple<HuskyClientView, HuskyClientViewModel> viewTuple = FluentViewLoader.fxmlView(HuskyClientView.class).load();
//
//        Parent root = viewTuple.getView();
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
