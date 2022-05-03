package pl.gmat.roomdetector.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.gmat.roomdetector.service.RoomDetectorServiceManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val serviceManager: RoomDetectorServiceManager
) : ViewModel() {

    fun onStartClick() {
        serviceManager.startService()
    }

    fun onStopClick() {
        serviceManager.stopService()
    }
}
