package pl.gmat.roomdetector.service

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RoomDetectorServiceManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun startService() {
        context.startForegroundService(Intent(context, RoomDetectorService::class.java))
    }

    fun stopService() {
        context.stopService(Intent(context, RoomDetectorService::class.java))
    }
}
