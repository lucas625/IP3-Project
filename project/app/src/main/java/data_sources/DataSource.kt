package data_sources

import models.Anotacao

class DataSource {
    fun loadAnotacoes(): List<Anotacao> {
        return listOf<Anotacao>(
                Anotacao(
                        "lagc@cin.ufpe.br",
                        "Minha primeira anotação",
                        "2021-04-04",
                        "Lorem Ipsum"
                ),
                Anotacao(
                        "lagc@cin.ufpe.br",
                        "Minha segunda anotação",
                        "2021-04-05",
                        "Lorem Ipsum Dolor"
                ),
                Anotacao(
                        "lagc@cin.ufpe.br",
                        "Minha 3a anotação",
                        "2021-04-05",
                        "Lorem Ipsum Dolor Color"
                )
        )
    }
}