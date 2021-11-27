package tennisi.borzot.strada.fragments.speed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import tennisi.borzot.strada.databinding.FragmentSpeedBinding
import tennisi.borzot.strada.services.speedService.SpeedService


class SpeedFragment : Fragment() {

    private var _binding: FragmentSpeedBinding? = null
    private val binding: FragmentSpeedBinding
        get() = _binding ?: throw RuntimeException("FragmentSpeedBinding == null")

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

        return binding.root
    }


}

