package pl.gmat.roomdetector.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import pl.gmat.roomdetector.RoomDetector
import pl.gmat.roomdetector.model.toBleScanResults
import javax.inject.Inject

@AndroidEntryPoint
class RoomDetectorService : Service() {

    @Inject
    lateinit var notifications: Notifications

    @Inject
    lateinit var roomDetector: RoomDetector

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(1, notifications.buildNotification())
        roomDetector.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        roomDetector.onScanResults(intent.toBleScanResults())
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        roomDetector.stop()
        super.onDestroy()
    }
}
