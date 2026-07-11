package mx.utng.smarthealthmonitor.data

import mx.utng.smarthealthmonitor.data.db.LecturaFC

object MockData {
    val historialFC = listOf(
        LecturaFC(id = 1, bpm = 75, hora = "10:00"),
        LecturaFC(id = 2, bpm = 82, hora = "11:00"),
        LecturaFC(id = 3, bpm = 110, hora = "12:00"),
        LecturaFC(id = 4, bpm = 68, hora = "13:00")
    )
}
