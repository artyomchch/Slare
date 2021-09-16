package tennisi.borzot.strada.services.firebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.SignInButton
import tennisi.borzot.strada.MainActivity
import tennisi.borzot.strada.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {
    private val binding: ActivitySignInBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.signInButton.apply {
//            setSize(SignInButton.SIZE_STANDARD)
//
//        }
        binding.scipButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

}