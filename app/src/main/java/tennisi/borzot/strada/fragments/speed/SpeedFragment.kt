package tennisi.borzot.strada.fragments.speed

import android.Manifest
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import tennisi.borzot.strada.R
import tennisi.borzot.strada.StradaApplication
import tennisi.borzot.strada.databinding.FragmentSpeedBinding
import tennisi.borzot.strada.fragments.add.presentation.ViewModelFactory
import tennisi.borzot.strada.services.speedService.SpeedForegroundService
import javax.inject.Inject


class SpeedFragment : Fragment() {

    private var _binding: FragmentSpeedBinding? = null
    private val binding: FragmentSpeedBinding
        get() = _binding ?: throw RuntimeException("FragmentSpeedBinding == null")


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[(SpeedFragmentViewModel::class.java)]
    }

    private val locationPermissionRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::onGotPermissionsForLocation
    )

    private val component by lazy {
        (requireActivity().application as StradaApplication).component
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = (service as? SpeedForegroundService.LocalBinder) ?: return
            val foregroundService = binder.getService()
            foregroundService.onSpeedListenerChange = { speedValue ->
                viewModel.setSpeedValue(speedValue)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }


    override fun onStart() {
        super.onStart()
        requireActivity().bindService(
            SpeedForegroundService.newIntent(this.requireContext()),
            serviceConnection, 0
        )
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeedBinding.inflate(inflater, container, false)
        val audioManager = requireActivity().application.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //  val workManager = WorkManager.getInstance(requireContext().applicationContext)
        //   val locationDTO = LocationDTO("--")
        //    onMessageEvent(locationDTO)
        upVolume(audioManager)
        downVolume(audioManager)
        showCurrentPercent(audioManager)
        setupStartButton()
        setupStopButton()
        observeCurrentSpeed()

        return binding.root
    }


    private fun observeCurrentSpeed() {
        viewModel.observerSpeed.observe(viewLifecycleOwner) {
            binding.speedTextView.text = it.toString()
        }
    }

    private fun onGotPermissionsForLocation(grantResults: Map<String, Boolean>) {
        if (grantResults.entries.all { it.value }) {
            onLocationPermissionsGranted()
        } else {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                askUserForOpeningAppSettings()
            } else {
                Toast.makeText(context, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun askUserForOpeningAppSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", requireActivity().packageName, null)
        )
        if (requireActivity().packageManager.resolveActivity(appSettingsIntent, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            Toast.makeText(context, getString(R.string.forever_denied), Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(requireActivity())
                .setTitle(getString(R.string.permission_denied))
                .setMessage(getString(R.string.warning_permission_denied_dialog))
                .setPositiveButton(getString(R.string.open_app_settings)) { _, _ ->
                    startActivity(appSettingsIntent)
                }
                .create()
                .show()
        }
    }

    private fun onLocationPermissionsGranted() {
        Toast.makeText(context, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("ClickableViewAccessibility", "MissingPermission")
    private fun setupStartButton() {
        binding.startStopButton.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                Log.d("Click", " click start")
                locationPermissionRequestLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
//                workManager.enqueueUniqueWork(
//                    SpeedWorker.WORK_NAME,
//                    ExistingWorkPolicy.REPLACE,
//                    SpeedWorker.makeRequest(true)
//                )
                ContextCompat.startForegroundService(this.requireContext(), SpeedForegroundService.newIntent(this.requireContext()))

            }

            false
        }
    }


    override fun onStop() {
        super.onStop()
        requireActivity().unbindService(serviceConnection)

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupStopButton() {
        binding.stopButton.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {

                requireActivity().stopService(SpeedForegroundService.newIntent(requireContext()))
//                workManager.enqueueUniqueWork(
//                    SpeedWorker.WORK_NAME,
//                    ExistingWorkPolicy.REPLACE,
//                    SpeedWorker.makeRequest(false)
                //  )

            }
            false
        }
    }

    private fun upVolume(audioManager: AudioManager) {
        binding.navigationButton.setOnClickListener {
            binding.percentVolumeTextView.text = showCurrentPercent(audioManager)

            CoroutineScope(Dispatchers.Main).launch {
                for (i in 0 until 10) {
                    Log.d("Times", "$i")
                    audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI)
                    binding.percentVolumeTextView.text = showCurrentPercent(audioManager)
                    delay(20)
                }
            }

        }

    }

    private fun downVolume(audioManager: AudioManager) {
        binding.musicButton.setOnClickListener {
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI)
            binding.percentVolumeTextView.text = showCurrentPercent(audioManager)
        }
    }

    private fun showCurrentPercent(audioManager: AudioManager): String {
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        return (100 * currentVolume / maxVolume).toString()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"

    }


}
