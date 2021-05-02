package daos

import android.content.res.Resources
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import data_sources.DataSource
import models.Anotacao
import java.util.ArrayList

private val anotacoes = arrayListOf<Anotacao>()

class AnotacaoDao {

    private val database = FirebaseDatabase.getInstance()
    private val anotacoesRef = this.database.getReference("anotacoes")

    init {
        this.anotacoesRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children
                anotacoes.clear()
                children.forEach {
                    anotacoes.add(
                        Anotacao(
                            id=it.key as String,
                            owner=it.child("owner").value as String,
                            title=it.child("title").value as String,
                            date=it.child("date").value as String,
                            text=it.child("text").value as String,
                           // photo=it.child("photo").value as ByteArray?
                        )
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error!!.message)
            }
        })
    }

    fun create(anotacao: Anotacao) {
        var newAnotacao = this.anotacoesRef.push()
        newAnotacao.setValue(anotacao)
    }

    fun get(id: String) : Anotacao? {
        for (anotacao in this.list()) {
            if (anotacao.id == id) {
                return anotacao
            }
        }
        return null
    }

    fun list() : ArrayList<Anotacao> {
        return anotacoes
    }

    companion object {
        private var INSTANCE: AnotacaoDao? = null

        fun getDataSource(resources: Resources): AnotacaoDao {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: AnotacaoDao()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}