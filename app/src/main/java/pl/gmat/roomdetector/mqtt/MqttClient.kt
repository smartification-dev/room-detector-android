package pl.gmat.roomdetector.mqtt

import com.hivemq.client.mqtt.MqttClient
import pl.gmat.roomdetector.config.MqttConfig
import javax.inject.Inject

class MqttClient @Inject constructor() {

    private val mqttClient = MqttClient.builder()
        .serverHost(MqttConfig.host)
        .useMqttVersion5()
        .buildBlocking()

    fun connect() {
        mqttClient.connectWith()
            .simpleAuth()
            .username(MqttConfig.username)
            .password(MqttConfig.password.toByteArray())
            .applySimpleAuth()
            .send()
    }

    fun publish(payload: String) {
        mqttClient.publishWith()
            .topic(MqttConfig.topic)
            .payload(payload.toByteArray())
            .send()
    }

    fun disconnect() {
        mqttClient.disconnect()
    }
}