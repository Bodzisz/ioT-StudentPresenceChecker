package pwr.studentpresencesystem;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttTest {

    private final IMqttClient mqttClient;

    public MqttTest(IMqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @GetMapping("/connect")
    public String connect() {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(10000);
            options.setKeepAliveInterval(10000);
            mqttClient.connect(options);
            return "CONNECTED";
        } catch (MqttException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/send")
    public String sendMessage() {
//        String message = "954038355942";
        String message = "56414964923";
        byte[] payload = message.getBytes();
        MqttMessage msg = new MqttMessage(payload);
        msg.setQos(0);
        msg.setRetained(true);
        try {
            mqttClient.publish("test/rfid", msg);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        return "SEND";
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
