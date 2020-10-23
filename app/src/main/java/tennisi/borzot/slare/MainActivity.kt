package tennisi.borzot.slare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideSystemUI()
        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
    }



    private fun hideSystemUI() {
        val decorView: View = window.decorView
        val uiOptions = decorView.systemUiVisibility
        var newUiOptions = uiOptions
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        //   newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_VISIBLE
        //  newUiOptions = newUiOptions or View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
        decorView.systemUiVisibility = newUiOptions
    }
}