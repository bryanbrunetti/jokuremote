import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public HashMap<String,String> deviceListMap = new HashMap<>();
    public ObservableList<String> devices = FXCollections.observableArrayList("Select a Roku Device");
    @FXML private ChoiceBox<String> deviceListBox;
    @FXML private ImageView remoteControl;

    @FXML
    public void discoverDevices() {
        System.out.println("discover can see?: "+ deviceListBox + remoteControl);
        RokuAPI.setDiscoveryListener(this);
    }

    @FXML
    public void newDeviceFound(String address) throws IOException, InterruptedException {
        System.out.println("Roku Address: " + address);
        System.out.println("deviceListBox: " + deviceListBox);
        System.out.println("imageBox: " + remoteControl);
        String deviceInfo = RokuAPI.getDeviceInfo(address);
        System.out.println(deviceInfo);
        SimpleXMLParser parser = new SimpleXMLParser(deviceInfo);
        String deviceName = parser.findByTagName("user-device-name");
        String deviceLocation = parser.findByTagName("user-device-location");
        devices.add(deviceName + " (" + deviceLocation + ")");
        deviceListMap.put(deviceName + " (" + deviceLocation + ")", address);
    }

    @FXML
    public void handleAction(ActionEvent actionEvent) {
        Button eventSource = (Button) actionEvent.getSource();
        String apiCall = "keypress/" + eventSource.getId();
        String selectedDevice = deviceListMap.get(deviceListBox.getSelectionModel().getSelectedItem());
        System.out.println("APICALL: " + selectedDevice + apiCall);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize: " + deviceListBox);
        deviceListBox.setItems(devices);
        deviceListBox.setValue(devices.get(0));
        discoverDevices();
    }
}
