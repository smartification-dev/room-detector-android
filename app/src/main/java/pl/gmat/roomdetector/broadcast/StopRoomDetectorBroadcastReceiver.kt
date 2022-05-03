package pl.gmat.roomdetector.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import pl.gmat.roomdetector.service.RoomDetectorServiceManager
import javax.inject.Inject

@AndroidEntryPoint
class StopRoomDetectorBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var serviceManager: RoomDetectorServiceManager

    override fun onReceive(context: Context, intent: Intent) {
        serviceManager.stopService()
    }
}
