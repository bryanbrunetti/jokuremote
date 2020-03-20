import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public HashMap<String,String> deviceListMap = new HashMap<>();
    public ObservableList<String> devices = FXCollections.observableArrayList();

    @FXML private ComboBox<String> deviceListBox;
    @FXML private ListView<ImageView> channelList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceListBox.setItems(devices);
        deviceListBox.setValue("Select a Roku ...");
        discoverDevices();
    }

    public void loadChannels() throws IOException, InterruptedException {
        String result = RokuAPI.apiCall(getSelectedDeviceAddress() + "query/apps");

        SimpleXMLParser parser = new SimpleXMLParser(result);
        for(Object appId : parser.parseTagAttribute("id")) {
            Image image = new Image(getSelectedDeviceAddress() + "query/icon/" + appId);
            ImageView imageView = new ImageView(image);
            imageView.setId((String) appId);
            imageView.setPreserveRatio(true);

            imageView.setFitWidth(152);
            channelList.getItems().add(imageView);
        }
    }
    @FXML
    public void discoverDevices() {
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

    public String getSelectedDeviceAddress() {
        return deviceListMap.get(deviceListBox.getSelectionModel().getSelectedItem());
    }


    public void changeDevices(ActionEvent actionEvent) throws IOException, InterruptedException {
//        ComboBox cb = (ComboBox) actionEvent.getSource();
//        System.out.println(cb.getSelectionModel().getSelectedItem());
//        System.out.println("Device Change: " + actionEvent);
//        System.out.println(this.getSelectedDeviceAddress());
        loadChannels();
    }

    public void changeChannel(MouseEvent mouseEvent) {
        ImageView channel = channelList.getSelectionModel().getSelectedItem();
        System.out.println("API CALL: " + getSelectedDeviceAddress() + "launch/" + channel.getId());
    }
}
