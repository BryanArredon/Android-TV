package mx.utng.smarthealthmonitor.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import mx.utng.smarthealthmonitor.data.db.LecturaFC

object SmartHealthRepository {
    private val _fcFlow = MutableStateFlow(0)
    val fcFlow: StateFlow<Int> = _fcFlow

    fun obtenerHistorial(): Flow<List<LecturaFC>> {
        return flowOf(MockData.historialFC)
    }

    fun updateFC(valor: Int) {
        _fcFlow.value = valor
    }
}
