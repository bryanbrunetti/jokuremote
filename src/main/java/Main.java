import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private double windowXOffset = 0;
    private double windowYOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        loader.setController(controller);

        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.0);");

        stage.setTitle("JokuRemote");
        Scene scene = new Scene(root, 250, 800);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        setWindowDraggable(root, stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void stop() {
        System.out.println("Stopping JokuRemote");
    }

    public void setWindowDraggable(Parent root, Stage stage) {
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                windowXOffset = event.getSceneX();
                windowYOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - windowXOffset);
                stage.setY(event.getScreenY() - windowYOffset);
            }
        });
    }

}