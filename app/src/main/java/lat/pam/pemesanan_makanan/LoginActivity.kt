
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
import kotlin.jvm.java

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usernameLayout = findViewById<TextInputLayout>(R.id.textFieldUsername)
        val passwordLayout = findViewById<TextInputLayout>(R.id.textFieldPassword)
        val loginButton = findViewById<MaterialButton>(R.id.buttonLogin)


        loginButton.setOnClickListener {
            val username = usernameLayout.editText?.text.toString()
            val password = passwordLayout.editText?.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {

                val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("EXTRA_USERNAME", username)

                startActivity(intent)
                finish()
            }
        }
        }
}
