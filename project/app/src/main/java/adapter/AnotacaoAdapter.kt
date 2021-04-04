package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yourdiary.R
import models.Anotacao

class AnotacaoAdapter(private val context: Context, private val dataset: List<Anotacao>) :
    RecyclerView.Adapter<AnotacaoAdapter.AnotacaoViewHolder>() {

    class AnotacaoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.findViewById<TextView>(R.id.title)
        val dateTextView = view.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnotacaoViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.anotacao_item, parent, false)
        return AnotacaoViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AnotacaoViewHolder, position: Int) {
        val item = dataset[position]
        holder.titleTextView.text = item.title
        holder.dateTextView.text = item.date
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}