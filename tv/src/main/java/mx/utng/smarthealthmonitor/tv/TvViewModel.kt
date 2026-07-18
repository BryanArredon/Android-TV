package mx.utng.smarthealthmonitor.tv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mx.utng.smarthealthmonitor.data.SmartHealthRepository
import mx.utng.smarthealthmonitor.data.db.LecturaFC
import mx.utng.smarthealthmonitor.mqtt.TvMessage
import mx.utng.smarthealthmonitor.tv.mqtt.MqttTvSubscriber

data class TvState(
    val lecturas: List<LecturaFC> = emptyList(),
    val fcActual: Int = 0,
    val fcEstado: String = "Desconocido",
    val ultimaHora: String = "--:--:--",
    val isLoading: Boolean = true
)

class TvViewModel(application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow(TvState())
    val state: StateFlow<TvState> = _state.asStateFlow()

    // Flow de mensajes MQTT entrantes
    private val mqttFlow = MutableStateFlow<TvMessage?>(null)
    private val mqttSubscriber = MqttTvSubscriber(application, mqttFlow)

    init {
        mqttSubscriber.connect()

        // Observar historial (Room/Mock)
        SmartHealthRepository.obtenerHistorial().onEach { list ->
            _state.update { it.copy(lecturas = list, isLoading = false) }
        }.launchIn(viewModelScope)

        // Observar mensajes MQTT y actualizar el estado de la UI
        viewModelScope.launch {
            mqttFlow.collect { tvMsg ->
                tvMsg ?: return@collect
                _state.update { it.copy(
                    fcActual = tvMsg.bpm,
                    fcEstado = tvMsg.estado,
                    ultimaHora = tvMsg.hora,
                    isLoading = false
                )}
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mqttSubscriber.disconnect()
    }
}
