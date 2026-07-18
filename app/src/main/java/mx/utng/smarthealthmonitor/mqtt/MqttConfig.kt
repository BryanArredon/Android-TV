package mx.utng.smarthealthmonitor.mqtt
 
object MqttConfig {
    const val BROKER_URL  = "ssl://335b3dfb6b5441938db0c1f3a0f81c24.s1.eu.hivemq.cloud:8883"
    const val USERNAME    = "bryan_utng"  // del Access Management
    const val PASSWORD    = "thebryan05"
 
    // Topics del proyecto
    const val TOPIC_FC    = "utng/smarthealthmonitor/fc"
    const val TOPIC_TV    = "utng/smarthealthmonitor/tv"
    const val TOPIC_ALERT = "utng/smarthealthmonitor/alerta"
 
    // QoS: 0=best effort, 1=at least once, 2=exactly once
    const val QOS = 1
 
    // Client IDs únicos por dispositivo
    const val CLIENT_WEAR = "smarthealthmonitor-wear"
    const val CLIENT_APP  = "smarthealthmonitor-app"
    const val CLIENT_TV   = "smarthealthmonitor-tv"
}
