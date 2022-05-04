package tennisi.borzot.strada.fragments.equalizer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import tennisi.borzot.strada.databinding.FragmentEqualizerBinding


class EqualizerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEqualizerBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentEqualizerBinding.inflate(inflater, container, false) }


        return binding.root
    }

    private fun doCrash() {
        Toast.makeText(requireContext(), "Google Analytics is watching you 0_0", Toast.LENGTH_LONG).show()
        throw RuntimeException("Test Crash")
    }

    private fun rx(counter: TextView) {
        val observable = Observable.just(1, 2, 3)

        val dispose = dataSource()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                counter.text = "$it"
            }, {
                Log.e("rx", "rx: ${it.localizedMessage}")
            }, {
                Toast.makeText(requireContext(), "Complete", Toast.LENGTH_LONG).show()
            })
    }

    private fun dataSource(): Observable<Int> {
        return Observable.create { subscriber ->
            for (i in 0..100) {
                Thread.sleep(100)
                subscriber.onNext(i)
            }
            subscriber.onComplete()
        }
    }


}