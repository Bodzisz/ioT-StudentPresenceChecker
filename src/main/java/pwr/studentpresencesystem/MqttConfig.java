package pwr.studentpresencesystem;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final String PUBLISHER_ID = "studentPresenceCheckerApp";
    @Value("${mqtt.url}")
    private String SERVER_URI;

    @Bean
    public IMqttClient mqttClient() throws MqttException {
        return new MqttClient(SERVER_URI, PUBLISHER_ID);
    }
}
