import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public HashMap<String,String> deviceListMap = new HashMap<>();
    public ObservableList<String> devices = FXCollections.observableArrayList();
    @FXML private ComboBox<String> deviceListBox;
    @FXML private ImageView remoteControl;

    @FXML
    public void discoverDevices() {
        System.out.println("discover can see?: "+ deviceListBox + remoteControl);
        RokuAPI.setDiscoveryListener(this);
    }

    @FXML
    public void newDeviceFound(String address) throws IOException, InterruptedException {
        String deviceInfo = RokuAPI.getDeviceInfo(address);
        SimpleXMLParser parser = new SimpleXMLParser(deviceInfo);
        String deviceName = parser.findByTagName("user-device-name");
        String deviceLocation = parser.findByTagName("user-device-location");
        devices.removeAll("Select a Roku ...");
        devices.add(deviceName + " (" + deviceLocation + ")");
        deviceListMap.put(deviceName + " (" + deviceLocation + ")", address);
        deviceListBox.setItems(devices);
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
        deviceListBox.setValue("Select a Roku ...");
        discoverDevices();
    }

    public void changeDevices(ActionEvent actionEvent) {
        System.out.println("Device Change: " + actionEvent);
    }
}
