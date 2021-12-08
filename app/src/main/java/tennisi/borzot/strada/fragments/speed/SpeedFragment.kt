package tennisi.borzot.strada.fragments.speed

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import tennisi.borzot.strada.databinding.FragmentSpeedBinding
import tennisi.borzot.strada.services.speedService.*


class SpeedFragment : Fragment() {

    private var _binding: FragmentSpeedBinding? = null
    private val binding: FragmentSpeedBinding
        get() = _binding ?: throw RuntimeException("FragmentSpeedBinding == null")

    private var page = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeedBinding.inflate(inflater, container, false)


        binding.startButton.setOnClickListener {
            ContextCompat.startForegroundService(requireContext(), SpeedService.newIntent(requireContext()))
        }

        binding.stopButton.setOnClickListener {
            requireActivity().stopService(SpeedService.newIntent(requireContext()))
        }

        binding.intentService.setOnClickListener {
            ContextCompat.startForegroundService(requireContext(), SpeedIntentService.newIntent(requireContext()))
        }

        binding.jobScheduler.setOnClickListener {
            val componentName = ComponentName(requireContext(), SpeedJobService::class.java)

            val jobInfo = JobInfo.Builder(SpeedJobService.JOB_ID, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build()

            val jobScheduler = requireActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intent = SpeedJobService.newIntent(page++)
                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
            } else
                requireActivity().startService(SpeedIntentService2.newIntent(requireContext(), page++))

        }

        binding.jobIntentService.setOnClickListener {
            SpeedJobIntentService.enqueue(requireContext(), page++)
        }

        binding.workManager.setOnClickListener {
            val workManager = WorkManager.getInstance(requireContext().applicationContext)
            workManager.enqueueUniqueWork(
                SpeedWorker.WORK_NAME,
                ExistingWorkPolicy.APPEND,
                SpeedWorker.makeRequest(page++)
            )
        }


        return binding.root
    }


}

