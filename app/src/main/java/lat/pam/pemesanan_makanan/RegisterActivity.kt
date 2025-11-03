package lat.pam.pemesanan_makanan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fullNameLayout = findViewById<TextInputLayout>(R.id.textFieldFullName)
        val usernameLayout = findViewById<TextInputLayout>(R.id.textFieldUsername)
        val passwordLayout = findViewById<TextInputLayout>(R.id.textFieldPassword)
        val registerButton = findViewById<MaterialButton>(R.id.buttonRegister)

        registerButton.setOnClickListener {

            val fullName = fullNameLayout.editText?.text.toString()
            val username = usernameLayout.editText?.text.toString()
            val password = passwordLayout.editText?.text.toString()


            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, LoginActivity::class.java)

                startActivity(intent)

                finish()
            }
        }
    }
}
