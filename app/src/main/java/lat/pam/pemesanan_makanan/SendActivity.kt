package lat.pam.pemesanan_makanan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val username = intent.getStringExtra("EXTRA_USERNAME")
        val orderSummary = intent.getStringExtra("EXTRA_ORDER_SUMMARY")


        val fullNameLayout = findViewById<TextInputLayout>(R.id.textFieldFullName)
        val addressLayout = findViewById<TextInputLayout>(R.id.textaddress)
        val addressDetailLayout = findViewById<TextInputLayout>(R.id.textaddressDetail)


        val fullNameEditText = fullNameLayout.editText
        val addressEditText = addressLayout.editText
        val addressDetailEditText = addressDetailLayout.editText

        val orderButton = findViewById<MaterialButton>(R.id.buttonsendorder)


        fullNameEditText?.setText(username)
        fullNameEditText?.isEnabled = false


        orderButton.setOnClickListener {

            val address = addressEditText?.text.toString().trim()
            val addressDetail = addressDetailEditText?.text.toString().trim()

            if (address.isEmpty() || addressDetail.isEmpty()) {
                Toast.makeText(this, "Semua kolom alamat harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pesanan '$orderSummary' akan dikirim!", Toast.LENGTH_LONG).show()

                val intent = Intent(this, DoneActivity::class.java)
                intent.putExtra("EXTRA_USERNAME", username)
                startActivity(intent)

                finish()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
