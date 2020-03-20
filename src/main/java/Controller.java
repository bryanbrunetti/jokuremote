import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public HashMap<String,String> deviceListMap = new HashMap<>();
    public ObservableList<String> devices = FXCollections.observableArrayList();

    private GridPane channelGrid = new GridPane();

    @FXML private ComboBox<String> deviceListBox;
    @FXML private ListView<GridPane> channelList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceListBox.setItems(devices);
        deviceListBox.setValue("Select a Roku ...");
        setupChannelGrid();

        discoverDevices();
    }

    public void setupChannelGrid() {
//        GridPane channelGrid = new GridPane();
        channelGrid.setHgap(0);
        channelGrid.setVgap(0);
        channelGrid.setStyle("-fx-background-color: transparent");
        channelList.getItems().add(channelGrid);
    }

    public void loadChannels() throws IOException, InterruptedException {
        String result = RokuAPI.apiCall(getSelectedDeviceAddress() + "query/apps");

        SimpleXMLParser parser = new SimpleXMLParser(result);

        int columnIndex = 0;
        int rowIndex = 0;

        for(Object appId : parser.parseTagAttribute("id")) {
            Button channelButton = buildChannelButton((String) appId);

            channelGrid.add(channelButton,columnIndex, rowIndex);
            if(columnIndex == 0) {
                columnIndex++;
            } else {
                rowIndex++;
                columnIndex--;
            }
        }
    }

    public Button buildChannelButton(String appId) {
        ImageView imageView = new ImageView(getSelectedDeviceAddress() + "query/icon/" + appId);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-background-color: transparent;");
        imageView.setFitWidth(75);

        Button channelButton = new Button();
        channelButton.setGraphic(imageView);
        channelButton.setId(appId);
        channelButton.setStyle("-fx-background-color: transparent;");
        channelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Button pressed = (Button) e.getSource();
                RokuAPI.sendCommand(getSelectedDeviceAddress() + "launch/" + pressed.getId());
            }
        });
        return channelButton;
    }

    @FXML
    public void discoverDevices() {
        RokuAPI.setDiscoveryListener(this);
    }

    @FXML
    public void newDeviceFound(String address) {
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
        RokuAPI.sendCommand(getSelectedDeviceAddress() + "keypress/" + eventSource.getId());
    }

    public String getSelectedDeviceAddress() {
        return deviceListMap.get(deviceListBox.getSelectionModel().getSelectedItem());
    }

    public void changeDevices(ActionEvent actionEvent) throws IOException, InterruptedException {
        loadChannels();
    }
}
