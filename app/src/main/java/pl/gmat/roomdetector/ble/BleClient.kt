package pl.gmat.roomdetector.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanSettings
import android.content.Context
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.gmat.roomdetector.service.PendingIntentBuilder
import javax.inject.Inject

class BleClient @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pendingIntentBuilder: PendingIntentBuilder,
) {

    private val bluetoothLeScanner =
        context.getSystemService<BluetoothManager>()!!.adapter.bluetoothLeScanner

    @SuppressLint("MissingPermission")
    fun startBackgroundScan() {
        bluetoothLeScanner.startScan(
            listOf(ScanFilter.Builder().setDeviceName("MJ_HT_V1").build()),
            ScanSettings.Builder()
                .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                .build(),
            pendingIntentBuilder.buildForService()
        )
    }

    @SuppressLint("MissingPermission")
    fun stopBackgroundScan() {
        bluetoothLeScanner.stopScan(pendingIntentBuilder.buildForService())
    }
}
