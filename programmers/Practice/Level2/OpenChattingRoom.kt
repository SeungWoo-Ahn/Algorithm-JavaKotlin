package programmers.Practice.Level2

class OpenChattingRoom {
    private sealed interface Command {
        data object Change : Command
    }

    private sealed interface MessageAbleCommand : Command {
        data object Enter : MessageAbleCommand
        data object Leave : MessageAbleCommand
    }

    private data class Message(val command: MessageAbleCommand, val uid: String)

    private fun String.toCommand(): Command {
        return when (this) {
            "Enter" -> MessageAbleCommand.Enter
            "Leave" -> MessageAbleCommand.Leave
            "Change" -> Command.Change
            else -> throw IllegalArgumentException()
        }
    }

    private fun MutableList<Message>.toAnnounce(
        nickNameMapper: MutableMap<String, String>
    ): Array<String> {
        return Array(size) { idx ->
            val (command, uid) = this[idx]
            val nickName = nickNameMapper[uid]!!
            when (command) {
                MessageAbleCommand.Enter -> "${nickName}님이 들어왔습니다."
                MessageAbleCommand.Leave -> "${nickName}님이 나갔습니다."
            }
        }
    }

    fun solution(records: Array<String>): Array<String> {
        val nickNameMapper = mutableMapOf<String, String>()
        val messages = mutableListOf<Message>()
        for (record in records) {
            val splitter = record.split(" ")
            when (val command = splitter[0].toCommand()) {
                MessageAbleCommand.Enter -> {
                    nickNameMapper[splitter[1]] = splitter[2]
                    messages += Message(command as MessageAbleCommand, splitter[1])
                }
                MessageAbleCommand.Leave -> {
                    messages += Message(command as MessageAbleCommand, splitter[1])
                }
                Command.Change -> {
                    nickNameMapper[splitter[1]] = splitter[2]
                }
            }
        }
        return messages.toAnnounce(nickNameMapper)
    }
}

fun main() {
    val records = arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan")
    val answer = OpenChattingRoom().solution(records)
    print(answer.joinToString())
}