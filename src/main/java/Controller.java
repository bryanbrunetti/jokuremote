import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

import java.io.IOException;



public class Controller {
    @FXML
    private ChoiceBox deviceListBox;
    @FXML
    private ImageView remoteControl;

//    public ObservableList<String> deviceList = FXCollections.observableArrayList("Searching ...");

    public void homeButton() {
        System.out.println("Home Button Pressed");
        System.out.println(deviceListBox);
//        deviceListBox.getItems().add("Rokue!");
    }

    public void backButton() {
        System.out.println("Back Button");
    }

    public void newDeviceFound(String deviceLocation) throws IOException, InterruptedException {
        String deviceInfo = RokuAPI.getDeviceInfo(deviceLocation);
        SimpleXMLParser parser = new SimpleXMLParser(deviceInfo);
        String deviceName = parser.findByTagName("user-device-name");
//      deviceList.add(deviceName);
    }
}
