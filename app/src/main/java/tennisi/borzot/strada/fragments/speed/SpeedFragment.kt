package tennisi.borzot.strada.fragments.speed

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import tennisi.borzot.strada.databinding.FragmentSpeedBinding


class SpeedFragment : Fragment() {

    private var _binding: FragmentSpeedBinding? = null
    private val binding: FragmentSpeedBinding
        get() = _binding ?: throw RuntimeException("FragmentSpeedBinding == null")


    private lateinit var animationCircle : ObjectAnimator


    private var statusAnimation = true

    private var page = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeedBinding.inflate(inflater, container, false)
        animate()
        listeners()





        return binding.root
    }




    private fun listeners() {

        binding.startStopButton.setOnClickListener {
            if (statusAnimation) {
                animationCircle.apply {
                    end()
                    cancel()
                }
                binding.startStopButton.text = "Stop"
            } else {
                animationCircle.start()
                binding.startStopButton.text = "Start"
            }
            statusAnimation = !statusAnimation
        }
    }

//        binding.startButton.setOnClickListener {
//            ContextCompat.startForegroundService(requireContext(), SpeedService.newIntent(requireContext()))
//        }
//
//        binding.stopButton.setOnClickListener {
//            requireActivity().stopService(SpeedService.newIntent(requireContext()))
//        }
//
//        binding.intentService.setOnClickListener {
//            ContextCompat.startForegroundService(requireContext(), SpeedIntentService.newIntent(requireContext()))
//        }
//
//        binding.jobScheduler.setOnClickListener {
//            val componentName = ComponentName(requireContext(), SpeedJobService::class.java)
//
//            val jobInfo = JobInfo.Builder(SpeedJobService.JOB_ID, componentName)
//                .setRequiresCharging(true)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .build()
//
//            val jobScheduler = requireActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val intent = SpeedJobService.newIntent(page++)
//                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
//            } else
//                requireActivity().startService(SpeedIntentService2.newIntent(requireContext(), page++))
//
//        }
//
//        binding.jobIntentService.setOnClickListener {
//            SpeedJobIntentService.enqueue(requireContext(), page++)
//        }
//
//        binding.workManager.setOnClickListener {
//            val workManager = WorkManager.getInstance(requireContext().applicationContext)
//            workManager.enqueueUniqueWork(
//                SpeedWorker.WORK_NAME,
//                ExistingWorkPolicy.APPEND,
//                SpeedWorker.makeRequest(page++)
//            )
//        }



    private fun animate() {

        animationCircle = ObjectAnimator.ofPropertyValuesHolder(
            binding.animationCircle,
            PropertyValuesHolder.ofFloat("scaleX", 2.5f),
            PropertyValuesHolder.ofFloat("scaleY", 2.5f),
            PropertyValuesHolder.ofFloat("alpha", 1f, 0f),
            PropertyValuesHolder.ofFloat("rotation", 0f, 360f),

        )
        with (animationCircle) {
            duration = 2000
            interpolator = LinearInterpolator()
            repeatCount = INFINITE
            start()

        }



    }


    override fun onDestroyView() {
        super.onDestroyView()
        animationCircle.cancel()

        _binding = null
    }

}
