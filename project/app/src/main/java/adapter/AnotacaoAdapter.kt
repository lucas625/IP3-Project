package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yourdiary.R
import models.Anotacao

class AnotacaoAdapter(private val onClick: (Anotacao) -> Unit) :
        ListAdapter<Anotacao, AnotacaoAdapter.AnotacaoViewHolder>(AnotacaoDiffCallback) {

    class AnotacaoViewHolder(private val view: View, val onClick: (Anotacao) -> Unit) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById<TextView>(R.id.title)
        val dateTextView: TextView = view.findViewById<TextView>(R.id.date)
        private var currentAnotacao: Anotacao? = null

        init {
            itemView.setOnClickListener {
                currentAnotacao?.let {
                    onClick(it)
                }
            }
        }

        fun bind(anotacao: Anotacao) {
            currentAnotacao = anotacao

            titleTextView.text = anotacao.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnotacaoViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.anotacao_item, parent, false)
        return AnotacaoViewHolder(adapterLayout, onClick)
    }

    override fun onBindViewHolder(holder: AnotacaoViewHolder, position: Int) {
        val item = getItem(position)
        holder.titleTextView.text = item.title
        holder.dateTextView.text = item.date
        holder.bind(item)
    }

}

object AnotacaoDiffCallback : DiffUtil.ItemCallback<Anotacao>() {
    override fun areItemsTheSame(oldItem: Anotacao, newItem: Anotacao): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Anotacao, newItem: Anotacao): Boolean {
        return oldItem.id == newItem.id
    }
}