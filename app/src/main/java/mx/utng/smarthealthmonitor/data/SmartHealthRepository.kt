package mx.utng.smarthealthmonitor.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import mx.utng.smarthealthmonitor.data.db.LecturaFC

object SmartHealthRepository {
    // Expuesto como MutableStateFlow para que el MqttAppService lo actualice
    val fcFlow = MutableStateFlow(0)

    fun obtenerHistorial(): Flow<List<LecturaFC>> {
        return flowOf(MockData.historialFC)
    }

    fun updateFC(valor: Int) {
        fcFlow.value = valor
    }
}
