package pwr.studentpresencesystem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pwr.studentpresencesystem.service.AttendanceService;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MqttConfig {

    private static final String PUBLISHER_ID = "studentPresenceCheckerApp";
    private final AttendanceService attendanceService;
    @Value("${mqtt.url}")
    private String SERVER_URI;

    @Bean
    public IMqttClient mqttClient() throws MqttException {
        MqttClient mqttClient = new MqttClient(SERVER_URI, PUBLISHER_ID);
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(10);
            mqttClient.connect(options);
        } catch (MqttException e) {
            log.error(e.getMessage());
        }
        mqttClient.subscribe("test/rfid", (topic, message) -> {
            if (new String(message.getPayload()).equals("Client connected")) return;
            log.info("Received message: " + new String(message.getPayload()));
            attendanceService.saveAttendance(new String(message.getPayload()));
        });
        return mqttClient;
    }
}
