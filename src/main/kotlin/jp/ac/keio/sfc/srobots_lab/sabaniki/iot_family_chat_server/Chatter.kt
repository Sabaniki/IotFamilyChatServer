package jp.ac.keio.sfc.srobots_lab.sabaniki.iot_family_chat_server

import com.slack.api.Slack
import com.slack.api.methods.SlackApiException
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import org.slf4j.LoggerFactory
import java.io.IOException

class Chatter(private val name: String) {
    private val slack = Slack.getInstance()
    private val methods = slack.methods(Token)
    private val logger = LoggerFactory.getLogger(Chatter::class.java)
    private val sendChannelName = "#debug"

    fun sendMessageToGroup(message: String) {
        try {
            val response = methods.chatPostMessage {
                it.channel(sendChannelName)
                    .username(name)
                    .text(message)
            }
            if (response.isOk) logger.info("success send message: {} --[{}]", response.message.username, response.message.text)
            else logger.error("failed to send message: {}", response.error)
        }
        catch (apiError: SlackApiException) {
            logger.error("Slack API returned bad status: $apiError")
        }
        catch (connectionError: IOException) {
            logger.error("connection error occurred: $connectionError")
        }
    }
}