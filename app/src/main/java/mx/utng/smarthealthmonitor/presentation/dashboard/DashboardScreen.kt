package mx.utng.smarthealthmonitor.presentation.dashboard

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory

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
fun DashboardScreen() {
    Scaffold(
        topBar = { DashboardTopBar("SmartHealth Dashboard") }
    ) { padding ->
        // Contenido del dashboard
    }
}
