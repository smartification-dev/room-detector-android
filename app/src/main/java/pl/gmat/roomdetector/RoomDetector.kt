package pl.gmat.roomdetector

import pl.gmat.roomdetector.ble.BleClient
import pl.gmat.roomdetector.config.RoomsConfig
import pl.gmat.roomdetector.model.BleScanResult
import pl.gmat.roomdetector.mqtt.MqttClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDetector @Inject constructor(
    private val bleClient: BleClient,
    private val mqttClient: MqttClient,
) {

    private val cachedScanResults = mutableMapOf<String, Int>()
    private var currentRoom = ""

    fun start() {
        mqttClient.connect()
        bleClient.startBackgroundScan()
    }

    fun onScanResults(scanResults: List<BleScanResult>) {
        scanResults.forEach { cachedScanResults[it.macAddress] = it.rssi }
        val room = cachedScanResults.maxByOrNull { it.value }
            ?.let { RoomsConfig.rooms[it.key] }
        if (room != null && room != currentRoom) {
            currentRoom = room
            mqttClient.publish(currentRoom)
        }
    }

    fun stop() {
        bleClient.stopBackgroundScan()
        mqttClient.disconnect()
    }
}
