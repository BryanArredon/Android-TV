package mx.utng.smarthealthmonitor.data.db

data class LecturaFC(
    val id: Int = 0,
    val bpm: Int,
    val hora: String,
    val estado: String = if (bpm <= 100) "Normal" else "Elevado"
)
