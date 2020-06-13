package jp.ac.keio.sfc.srobots_lab.sabaniki.iot_family_chat_server.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.slf4j.LoggerFactory

class MqttPublisher(brokerIp: String, clientId: String):
    MqttClient(brokerIp, clientId, MemoryPersistence()){
    private val connectionOption = MqttConnectOptions().apply { isCleanSession=false }
    private val logger = LoggerFactory.getLogger(MqttPublisher::class.java)

    init {
        logger.info("connecting to broker: {}", brokerIp)
        try {
            connect(connectionOption)
            logger.info("succeed to connect!")
        }
        catch (mqttException: MqttException){
            logger.error("failed to connect broker: {}", brokerIp)
            printMqttException(mqttException)
        }
    }

    private fun printMqttException(mqttException: MqttException){
        logger.error("reason: {}", mqttException.reasonCode)
        logger.error("message: {}", mqttException.message)
        logger.error("localize: {}", mqttException.localizedMessage)
        logger.error("cause: {}", mqttException.cause)
        logger.error("exception: {}", mqttException)
    }

    fun publish(topic: String, messageText: String){
        val message = MqttMessage(messageText.toByteArray()).apply { qos = 1 }
        try {
            logger.info("publishing message:")
            logger.info("topic: {}", topic)
            logger.info("text: {}", messageText)
            publish(topic, message)
            logger.info("succeed to publish!")
        }
        catch (mqttException: MqttException){
            logger.info("failed to publish message")
            printMqttException(mqttException)
        }
    }

    override fun disconnect() {
        try {
            logger.info("disconnecting from broker...")
            super.disconnect()
            logger.info("succeed to disconnect!")
        }
        catch (mqttException: MqttException){
            logger.error("failed to disconnect")
            printMqttException(mqttException)
        }
    }
}

