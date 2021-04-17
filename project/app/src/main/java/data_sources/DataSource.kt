package data_sources

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import models.Anotacao

class DataSource(resources: Resources) {
    private val initialNotesList = loadAnotacoes(resources)
    private val notesLiveData = MutableLiveData<List<Anotacao>>()
    private fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

    val notesLiveData2 = MutableLiveData<List<Anotacao>>().default(initialNotesList)

    /* Adds anotacao to liveData and posts value. */
    fun addAnotacao(anotacao: Anotacao) {
        val currentList = notesLiveData2.value
        if (currentList == null) {
            notesLiveData2.postValue(listOf(anotacao))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, anotacao)
            notesLiveData2.postValue(updatedList)
        }
    }

    /* Removes anotacao from liveData and posts value. */
    fun removeAnotacao(anotacao: Anotacao) {
        val currentList = notesLiveData2.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(anotacao)
            notesLiveData2.postValue(updatedList)
        }
    }

    /* Returns anotacao given an ID. */
    fun getAnotacaoForId(id: String): Anotacao? {
        notesLiveData2.value?.let { anotacaos ->
            return anotacaos.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getAnotacaosList(): LiveData<List<Anotacao>> {
        return notesLiveData2
    }
    

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}