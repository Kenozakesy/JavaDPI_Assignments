package Applications.ApplicationStartUp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HuskyClient_1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Forms/HuskyClient.fxml"));
        primaryStage.setTitle("HuskyClient_1");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
