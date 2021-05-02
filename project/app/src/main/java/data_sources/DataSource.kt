package data_sources

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import daos.AnotacaoDao
import models.Anotacao

class DataSource(resources: Resources) {
    private val dao = AnotacaoDao()

    private fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

    /* Adds anotacao to liveData and posts value. */
    fun create(anotacao: Anotacao) {
        dao.create(anotacao)
    }

    /* Removes anotacao from liveData and posts value. */
    fun removeAnotacao(anotacao: Anotacao) {
        // TODO: Not yet implemented
    }

    /* Returns anotacao given an ID. */
    fun getAnotacaoForId(id: String): Anotacao? {
        return dao.get(id)
    }

    fun list(): LiveData<List<Anotacao>> {
        return MutableLiveData<List<Anotacao>>().default(dao.list())
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