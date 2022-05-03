package pl.gmat.roomdetector

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.getSystemService
import dagger.hilt.android.HiltAndroidApp
import pl.gmat.roomdetector.service.Notifications
import javax.inject.Inject

@HiltAndroidApp
class RoomDetectorApplication : Application() {

    @Inject
    lateinit var notifications: Notifications

    override fun onCreate() {
        super.onCreate()
        getSystemService<NotificationManager>()!!
            .createNotificationChannel(notifications.buildNotificationChannel())
    }
}
