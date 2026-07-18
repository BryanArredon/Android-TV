package mx.utng.smarthealthmonitor.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory
import mx.utng.smarthealthmonitor.presentation.dashboard.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(title: String) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            // CastButton: AndroidView que envuelve MediaRouteButton
            AndroidView(
                factory = { context ->
                    MediaRouteButton(context).apply {
                        CastButtonFactory.setUpMediaRouteButton(context, this)
                    }
                },
                modifier = Modifier.size(48.dp)
            )
        }
    )
}

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel()
) {
    val fcActual by viewModel.fcActual.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { DashboardTopBar("SmartHealth Dashboard") }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Ritmo Cardíaco (MQTT)", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "$fcActual BPM",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
