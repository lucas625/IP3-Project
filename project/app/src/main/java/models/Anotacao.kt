package models

import java.io.Serializable

data class Anotacao(
        val id: Long,
        var owner: String,
        val title: String,
        val date: String,
        val text: String,
        val photo: ByteArray?) : Serializable {

}
