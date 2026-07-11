package mx.utng.smarthealthmonitor.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import mx.utng.smarthealthmonitor.data.SmartHealthRepository
import mx.utng.smarthealthmonitor.data.db.LecturaFC

data class TvState(
    val lecturas: List<LecturaFC> = emptyList()
)

class TvViewModel : ViewModel() {
    private val _state = MutableStateFlow(TvState())
    val state: StateFlow<TvState> = _state.asStateFlow()

    init {
        SmartHealthRepository.obtenerHistorial().onEach { list ->
            _state.update { it.copy(lecturas = list) }
        }.launchIn(viewModelScope)
    }
}
