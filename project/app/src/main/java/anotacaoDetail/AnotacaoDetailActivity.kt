package anotacaoDetail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.yourdiary.ANOTACAO_ID
import com.example.yourdiary.R

class AnotacaoDetailActivity : AppCompatActivity() {

    private val anotacaoDetailViewModel by viewModels<AnotacaoDetailViewModel> {
        AnotacaoDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anotacao_detail_activity)

        var currentAnotacaoId: String? = null

        /* Connect variables to UI elements. */
        val anotacaoTitle: TextView = findViewById(R.id.anotacao_detail_title)
        val anotacaoText: TextView = findViewById(R.id.anotacao_detail_text)
        val anotacaoDate: TextView = findViewById(R.id.anotacao_detail_date)
        val removeAnotacaoButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentAnotacaoId = bundle.getString(ANOTACAO_ID)
        }

        currentAnotacaoId?.let {
            val currentAnotacao = anotacaoDetailViewModel.getAnotacaoForId(it)
            anotacaoTitle.text = currentAnotacao?.title
            anotacaoText.text = currentAnotacao?.text

            removeAnotacaoButton.setOnClickListener {
                if (currentAnotacao != null) {
                    anotacaoDetailViewModel.removeAnotacao(currentAnotacao)
                }
                finish()
            }
        }

    }
}