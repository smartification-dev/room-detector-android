package pl.gmat.roomdetector.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.gmat.roomdetector.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Notifications @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pendingIntentBuilder: PendingIntentBuilder,
) {

    companion object {
        const val CHANNEL_ID = "pl.gmat.roomdetector"
    }

    fun buildNotificationChannel() =
        NotificationChannel(CHANNEL_ID, "Room Detector", NotificationManager.IMPORTANCE_LOW)

    fun buildNotification() =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Room Detector")
            .setSmallIcon(R.drawable.ic_home)
            .addAction(
                R.drawable.ic_home,
                "Stop",
                pendingIntentBuilder.buildForBroadcast()
            )
            .build()
}
