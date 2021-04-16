package data_sources

import android.content.res.Resources
import models.Anotacao

    fun loadAnotacoes(resources: Resources): List<Anotacao> {
        return listOf<Anotacao>(
                Anotacao(
                        id = 1,
                        "lagc@cin.ufpe.br",
                        "Minha primeira anotação",
                        "2021-04-04",
                        "Lorem Ipsum",
                        null
                ),
                Anotacao(
                        id = 2,
                        "lagc@cin.ufpe.br",
                        "Minha segunda anotação",
                        "2021-04-05",
                        "Lorem Ipsum Dolor",
                        null
                ),
                Anotacao(
                        id = 3,
                        "lagc@cin.ufpe.br",
                        "Minha 3a anotação",
                        "2021-04-05",
                        "Lorem Ipsum Dolor Color",
                        null
                )
        )
    }