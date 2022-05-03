package pl.gmat.roomdetector.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.gmat.roomdetector.broadcast.StopRoomDetectorBroadcastReceiver
import javax.inject.Inject

class PendingIntentBuilder @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun buildForService(): PendingIntent =
        PendingIntent.getForegroundService(
            context,
            100,
            Intent(context, RoomDetectorService::class.java),
            buildFlags()
        )

    fun buildForBroadcast(): PendingIntent =
        PendingIntent.getBroadcast(
            context,
            101,
            Intent(context, StopRoomDetectorBroadcastReceiver::class.java),
            buildFlags()
        )

    private fun buildFlags() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
}
