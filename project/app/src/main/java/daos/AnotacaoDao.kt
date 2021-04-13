package daos

import com.google.firebase.database.FirebaseDatabase
import models.Anotacao

class AnotacaoDao {

    private val database = FirebaseDatabase.getInstance()
    private val anotacoesRef = this.database.getReference("anotacoes")

    fun create(anotacao: Anotacao) {
        var newAnotacao = this.anotacoesRef.push()
        newAnotacao.setValue(anotacao)
    }

    fun get(id: Number) {

    }

    fun list(owner: String) {
        // TODO: return something
        var returns = this.anotacoesRef.get()
    }
}