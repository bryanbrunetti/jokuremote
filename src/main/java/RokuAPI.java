import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RokuAPI {
    public static void discover(Controller controller) {
        DiscoveryRequest networkStorageDevice = SsdpRequest.builder().serviceType("roku:ecp").build();
        SsdpClient.create().discoverServices(networkStorageDevice, buildDiscoveryListener(controller));
    }

    public static void newDeviceFound(String location) {

    }

    public static String getDeviceInfo(String location) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(location + "/query/device-info"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return response.body();
        }
        return "";
    }

    public static DiscoveryListener buildDiscoveryListener(Controller controller) {
        return new DiscoveryListener() {
            @Override
            public void onServiceDiscovered(SsdpService service) {
                try {
                    controller.newDeviceFound(service.getLocation());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
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
