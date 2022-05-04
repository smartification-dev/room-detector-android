# Room Detector Android

Room presence detection app for Android, based on discovering nearby BLE devices.

This project is part of a blog post: [https://smartification.dev/room-presence-detection-for-android](https://smartification.dev/room-presence-detection-for-android)

## How it works
App uses [BluetoothLeScanner](https://developer.android.com/reference/android/bluetooth/le/BluetoothLeScanner) from Android framework to scan specified BLE devices in the background. Based on the scan results and RSSI of each result, whichever is the highest, it is considered to be closest. Room's name then is published via MQTT.

## Setup

Before you start using the app, please configure:
- `RoomsConfig.kt` - map of beacon's MAC addresses to your room's names
- `MqttConfig.kt` - host and credentials of your MQTT broker
- Optional: `BleClient.kt` - if you are using devices different than Xiaomi Mi Temperature & Humidity Monitor as beacons, then please setup your own `ScanFilter`
