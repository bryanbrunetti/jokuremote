import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setController(controller);

        RokuAPI.discover(controller);
        Parent root = loader.load();
        stage.setTitle("JokuRemote");
        stage.setScene(new Scene(root, 250, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void onShutdown() {
//        RokuAPI.stopDiscovery();
    }

}