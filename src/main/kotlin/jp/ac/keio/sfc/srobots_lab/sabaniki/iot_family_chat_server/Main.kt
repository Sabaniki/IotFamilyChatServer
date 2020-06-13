package jp.ac.keio.sfc.srobots_lab.sabaniki.iot_family_chat_server

import jp.ac.keio.sfc.srobots_lab.sabaniki.iot_family_chat_server.http.Chatter
import jp.ac.keio.sfc.srobots_lab.sabaniki.iot_family_chat_server.mqtt.MqttPublisher

fun main() {
    val mqttPublisher = MqttPublisher("tcp://192.168.1.104:1883", "test_client")
    mqttPublisher.publish("hoge/fuga/piyo", "this is test message")
    mqttPublisher.disconnect()
}