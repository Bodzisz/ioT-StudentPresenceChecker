package pwr.studentpresencesystem;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqqtTest {

    private IMqttClient mqttClient;

    public MqqtTest(IMqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @GetMapping("/connect")
    public String connect() {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            mqttClient.connect(options);
            return "CONNECTED";
        } catch (MqttException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/disconnect")
    public String disconnect() {
        try {
            mqttClient.disconnect();
            return "DISCONNECTED";
        } catch (MqttException e) {
            return e.getMessage();
        }
    }
}
