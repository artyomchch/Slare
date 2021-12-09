package tennisi.borzot.strada.services.speedService

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class SpeedJobIntentService : JobIntentService() {


    override fun onCreate() {
        super.onCreate()
        log("OnCreate")


    }

    override fun onHandleWork(intent: Intent) {
        log("onHandleIntent")
        val page = intent.getIntExtra(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("timer $i  $page")
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        log("OnDestroy")
    }


    private fun log(mes: String) {
        Log.d("service tag", "SpeedJobIntentService $mes")
    }


    companion object {

        private const val PAGE = "page"
        private const val JOB_ID = 111


        fun enqueue(context: Context, page: Int) {
            enqueueWork(
                context,
                SpeedJobIntentService::class.java,
                JOB_ID,
                newIntent(context, page)
            )
        }

        private fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, SpeedJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }

}