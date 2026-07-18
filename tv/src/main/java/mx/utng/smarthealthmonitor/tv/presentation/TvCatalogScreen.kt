package mx.utng.smarthealthmonitor.tv.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.*
import mx.utng.smarthealthmonitor.tv.TvViewModel

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvCatalogScreen(
    onCardClick: (Int) -> Unit,
    viewModel: TvViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize().padding(32.dp)) {
        Text("SmartHealth TV Catalog", style = MaterialTheme.typography.headlineMedium)
        
        Text(
            text = if (state.isLoading) "⏳ Conectando a MQTT..." else "✅ Conectado a HiveMQ",
            color = if (state.isLoading) Color.Yellow else Color.Cyan,
            style = MaterialTheme.typography.bodySmall
        )

        if (state.fcActual > 0) {
            Text(
                "FC Real-time: ${state.fcActual} bpm (${state.fcEstado}) - ${state.ultimaHora}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Green
            )
        } else {
            Text("Esperando datos del reloj...", color = Color.Gray)
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.lecturas) { lectura ->
                Surface(
                    onClick = { onCardClick(lectura.id) },
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    colors = ClickableSurfaceDefaults.colors(
                        containerColor = Color(0xFF1B4F8A),
                        focusedContainerColor = Color(0xFF1565C0)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("${lectura.bpm} bpm", style = MaterialTheme.typography.titleLarge)
                        Text(lectura.hora, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
