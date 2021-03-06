import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RokuAPI {
    public static void setDiscoveryListener(Controller controller) {
        DiscoveryRequest networkStorageDevice = SsdpRequest.builder().serviceType("roku:ecp").build();
        SsdpClient.create().discoverServices(networkStorageDevice, buildDiscoveryListener(controller));
    }

    public static void sendCommand(String url) {
        apiCall(url);
    }

    public static String apiCall(String url) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDeviceInfo(String location) {
        return apiCall(location + "/query/device-info");
    }

    public static DiscoveryListener buildDiscoveryListener(Controller controller) {
        return new DiscoveryListener() {
            @Override
            public void onServiceDiscovered(SsdpService service) {
                controller.newDeviceFound(service.getLocation());
            }

            @Override
            public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {

            }

            @Override
            public void onFailed(Exception ex) {

            }
        };
    }
}
