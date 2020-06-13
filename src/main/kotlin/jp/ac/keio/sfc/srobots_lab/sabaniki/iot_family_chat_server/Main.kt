package jp.ac.keio.sfc.srobots_lab.sabaniki.iot_family_chat_server

import com.slack.api.Slack

fun main() {
    val slack = Slack.getInstance()
    val response = slack.methods().apiTest { it.foo("bar") }
    println(response)
}