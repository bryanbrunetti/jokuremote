import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;



public class Controller {
    @FXML
    public ChoiceBox<String> deviceListBox;
    public ObservableList<String> deviceList = FXCollections.observableArrayList("Searching ...");


    public Controller() {
        RokuAPI.discover(this);
    }
    public void homeButton() {
        System.out.println("Home Button");
    }

    public void backButton() {
        System.out.println("Back Button");
    }

    public void beginSearching() {
        deviceListBox.setItems(deviceList);
    }

    public void newDeviceFound(String deviceLocation) throws IOException, InterruptedException {
        String deviceInfo = RokuAPI.getDeviceInfo(deviceLocation);
        SimpleXMLParser parser = new SimpleXMLParser(deviceInfo);
        String deviceName = parser.findByTagName("user-device-name");
        deviceList.add(deviceName);
    }
}
