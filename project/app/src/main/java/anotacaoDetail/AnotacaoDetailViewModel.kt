package anotacaoDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data_sources.DataSource
import models.Anotacao

class AnotacaoDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a note that corresponds to an id. */
    fun getAnotacaoForId(id: Long) : Anotacao? {
        return datasource.getAnotacaoForId(id)
    }

    /* Queries datasource to remove a note. */
    fun removeAnotacao(anotacao: Anotacao) {
        datasource.removeAnotacao(anotacao)
    }
}

class AnotacaoDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnotacaoDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnotacaoDetailViewModel(
                    datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}