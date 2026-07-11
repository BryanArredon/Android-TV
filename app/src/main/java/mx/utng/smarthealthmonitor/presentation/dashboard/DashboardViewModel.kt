package mx.utng.smarthealthmonitor.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import mx.utng.smarthealthmonitor.data.SmartHealthRepository

class DashboardViewModel : ViewModel() {
    val fcActual: StateFlow<Int> = SmartHealthRepository.fcFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}
