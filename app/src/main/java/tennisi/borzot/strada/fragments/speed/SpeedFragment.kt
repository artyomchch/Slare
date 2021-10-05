package tennisi.borzot.strada.fragments.speed

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.anastr.speedviewlib.Gauge
import com.github.anastr.speedviewlib.SpeedView
import com.github.anastr.speedviewlib.components.Style
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentSpeedBinding
import tennisi.borzot.strada.services.speedService.SpeedReceiver
import tennisi.borzot.strada.services.speedService.SpeedService


class SpeedFragment : Fragment(), SpeedInterface.View {
    val speedReceiver = SpeedReceiver()
    private val locationPermissionCode = 2
    private var presenter: SpeedPresenter? = null
    private var activeService = false
    private var stopService = false
    private var getSpeedFromService = false
    private var speedCurrentService: Int = 0

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(speedReceiver, IntentFilter("speed.broadcast.receiver"))

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentSpeedBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentSpeedBinding.inflate(inflater, container, false) }
        presenter = SpeedPresenter(this)

        binding.toolbarSpeedFragment.toolbar.title = getString(R.string.speed)

        setSectionTicks(binding.speedView)


        val receiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                if (getSpeedFromService) {
                    speedCurrentService = intent.getStringExtra("speed.broadcast.Message")!!.toInt()
                    intent.getStringExtra("speed.broadcast.Message")?.let { binding.speedView.speedTo(speedCurrentService.toFloat()) }
                    binding.speedView.speedTextColor = Color.parseColor("#77B2D8")
                    binding.speedView.unitTextColor = Color.parseColor("#000000")

                }
            }
        }
        activity?.registerReceiver(receiver, IntentFilter("speed.broadcast.receiver"))




        binding.speedView.onSpeedChangeListener = { _: Gauge, isSpeedUp: Boolean, _: Boolean ->
            startAnimationTicks(binding.speedView, isSpeedUp)
        }



        binding.startButton.setOnClickListener {

            val intent = Intent(context, SpeedService::class.java)
            if (context != null) {
                requireContext().startService(intent)
            }
            activeService = true
            stopService = false
            binding.speedView.speedPercentTo(100, 1200)
        }


        binding.stopButton.setOnClickListener {
            val intent = Intent(context, SpeedService::class.java)

            if (context != null) {
                requireContext().stopService(intent)
            }
            activeService = false
            stopService = true
            getSpeedFromService = false
            binding.speedView.speedPercentTo(100, 1200)

        }

        return binding.root
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setSectionTicks(speedView: SpeedView) {
        speedView.makeSections(15, Color.CYAN, Style.BUTT)
        speedView.sections[0].color = Color.BLUE
        speedView.sections[1].color = Color.BLUE
        speedView.sections[2].color = Color.BLUE
        speedView.sections[3].color = Color.BLUE
        speedView.sections[4].color = Color.BLUE
        speedView.sections[5].color = Color.BLUE
        speedView.sections[6].color = Color.BLUE
        speedView.sections[7].color = Color.BLUE
        speedView.sections[8].color = Color.BLUE
        speedView.sections[9].color = Color.BLUE
        speedView.sections[10].color = Color.BLUE
        speedView.sections[11].color = Color.RED
        speedView.sections[12].color = Color.RED
        speedView.sections[13].color = Color.RED
        speedView.sections[14].color = Color.RED


        speedView.ticks = arrayListOf(
            .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
            .0f, .0f, .0f, .0f, .0f, .0f
        )

    }

    private fun startAnimationTicks(speedView: SpeedView, isSpeedUp: Boolean) {
        if (activeService) {
            showTicksStart(speedView, isSpeedUp)
        }
        if (speedView.currentIntSpeed == 0 && activeService && !stopService
        ) {
            speedView.speedTextColor = Color.parseColor("#77B2D8")
            speedView.unitTextColor = Color.parseColor("#000000")
        }
        if (stopService) {
            clearTicksStop(speedView, isSpeedUp)
        } else if (stopService && !activeService) {
            speedView.speedTextColor = Color.parseColor("#FFFFFF")
            speedView.unitTextColor = Color.parseColor("#FFFFFF")
        }


    }

    private fun showTicksStart(speedView: SpeedView, isSpeedUp: Boolean) {
        if (speedView.currentIntSpeed == 0 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )

        } else if (speedView.currentIntSpeed == 10 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 20 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .0f, .0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 30 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, 0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 40 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 50 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 60 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 70 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 80 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 90 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 100 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 110 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 120 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 130 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .866f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 140 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .866f, .933f, .0f
            )
        } else if (speedView.currentIntSpeed == 150 && isSpeedUp && !getSpeedFromService) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .866f, .933f, .9999f
            )


        }
        if (speedView.currentIntSpeed == 150) {
            getSpeedFromService = true
            speedView.speedPercentTo(0, 1200)

        }


    }

    private fun clearTicksStop(speedView: SpeedView, isSpeedUp: Boolean) {
        if (speedView.currentIntSpeed == 0 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )

        } else if (speedView.currentIntSpeed == 10 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 20 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .0f, .0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 30 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, 0f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 40 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .0f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 50 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .0f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 60 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .0f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 70 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .0f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 80 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .0f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 90 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .0f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 100 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .0f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 110 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .0f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 120 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .0f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 130 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .866f, .0f, .0f
            )
        } else if (speedView.currentIntSpeed == 140 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .866f, .933f, .0f
            )
        } else if (speedView.currentIntSpeed == 150 && !isSpeedUp) {
            speedView.ticks = arrayListOf(
                .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                .6f, .666f, .733f, .8f, .866f, .933f, .9999f
            )


        }
        if (speedView.currentIntSpeed == 150) {
            speedView.speedPercentTo(0, 1200)
            speedView.speedTextColor = Color.parseColor("#FFFFFF")
            speedView.unitTextColor = Color.parseColor("#FFFFFF")


        }


    }


}

