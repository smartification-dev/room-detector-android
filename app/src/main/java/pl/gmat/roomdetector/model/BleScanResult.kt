package pl.gmat.roomdetector.model

import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanResult
import android.content.Intent

data class BleScanResult(
    val macAddress: String,
    val rssi: Int,
)

fun Intent?.toBleScanResults() =
    this?.getParcelableArrayListExtra<ScanResult>(BluetoothLeScanner.EXTRA_LIST_SCAN_RESULT)
        ?.map { BleScanResult(it.device.address, it.rssi) }
        ?: emptyList()

