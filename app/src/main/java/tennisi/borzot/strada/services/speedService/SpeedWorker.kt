package tennisi.borzot.strada.services.speedService

import android.content.Context
import android.util.Log
import androidx.work.*

class SpeedWorker(
    context: Context,
    private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 until 10) {
            Thread.sleep(1000)
            log("timer $i  $page")
        }
        return Result.success()

    }



    private fun log(mes: String) {
        Log.d("service tag", "SpeedWorker $mes")
    }

    companion object {

        private const val PAGE = "page"
        const val WORK_NAME = "work name"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<SpeedWorker>()
                .setInputData(workDataOf(PAGE to page))
                .setConstraints(makeConstraints())
                .build()

        }

        private fun makeConstraints() = Constraints.Builder()
            .build()
    }


}

