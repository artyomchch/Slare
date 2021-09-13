package tennisi.borzot.strada.fragments.add.mvpAddFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import io.realm.Realm
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentAddBinding
import tennisi.borzot.strada.fragments.add.floatingButton.AddCarButtonFragment
import tennisi.borzot.strada.fragments.add.listviewcars.AdapterCar


class AddFragment : Fragment(), AddFragmentInterface.View {
    private var presenter: AddFragmentPresenter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAddBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentAddBinding.inflate(inflater, container, false) }

        presenter = AddFragmentPresenter(this)
        val realm: Realm = Realm.getDefaultInstance()
        binding.toolbarCarFragment.toolbar.title = getString(R.string.vehicle)

        if (presenter!!.checkAuto()) {
            binding.aboutListOfCar.visibility = View.VISIBLE
        } else
            binding.aboutListOfCar.visibility = View.INVISIBLE


        binding.listViewCars.adapter = context?.let { AdapterCar(it, R.layout.cars_list, presenter!!.getListCar()) }

        //listener on  new car
        realm.addChangeListener {
            presenter!!.notifyChanges()
            if (presenter!!.checkAuto()) {
                binding.aboutListOfCar.visibility = View.VISIBLE
            } else
                binding.aboutListOfCar.visibility = View.INVISIBLE


            (binding.listViewCars.adapter as AdapterCar?)?.notifyDataSetChanged()

        }

        //add car fragment
        binding.FABCar.setOnClickListener {
            if (!presenter!!.getFragment().isAdded) {
                presenter!!.clearFragment()

            }
        }

        //delete or remake choose car
        binding.listViewCars.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, id ->
            presenter!!.saveData(id)
        }

        return binding.root
    }

    override fun showFragment(carButtonFragment: AddCarButtonFragment) {
        carButtonFragment.show(childFragmentManager, "addButtom")
    }


}