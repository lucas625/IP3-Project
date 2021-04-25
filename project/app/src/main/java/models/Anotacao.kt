package models

import java.io.Serializable

data class Anotacao(
        val id: String,
        var owner: String,
        val title: String,
        val date: String,
        val text: String) : Serializable
