package tennisi.borzot.strada.homeScreen.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import com.google.firebase.auth.FirebaseAuth
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityMainBinding
import tennisi.borzot.strada.fragments.add.domain.entity.ScreenAddMode
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragmentDirections
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment


class MainActivity : AppCompatActivity(), CarItemFragment.OnSaveButtonClickListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavigationMenu.setupWithNavController(navController)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        bottomNavigationViewListening()
        observeViewModel()
        authentication()

        binding.carAddFab.setOnClickListener {
            findNavController(R.id.fragment_container).navigate(AddFragmentDirections.actionAddFragmentToCarItemFragment(ScreenAddMode.ADD, -1))
        }

    }

    private fun observeViewModel() {
        viewModel.currentNameFragment.observe(this) {
            Log.d("logFragment", "observeViewModel: $it ")
        }
    }

    private fun bottomNavigationViewListening() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            changingTabs(destination.label.toString())
        }
    }


    private fun authentication() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        binding.accountInfo.let {
            Glide.with(this)
                .load(currentUser?.photoUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_user_avatar)
                .into(it)
        }

    }


    private fun changingTabs(nameFragment: String) {
        with(binding) {
            val searchButtonMenu = mainToolbar.menu.findItem(R.id.search)
            val filterButtonMenu = mainToolbar.menu.findItem(R.id.filter)
            val sortingButtonMenu = mainToolbar.menu.findItem(R.id.sort)

            when (nameFragment) {
                FRAGMENT_ADD -> {
                    carAddFab.visibility = View.VISIBLE
                    mainCollapsing.title = getString(R.string.my_garage)
                    mainAppbar.setExpanded(true)
                    enableToolBarScrolling()
                    searchButtonMenu.isVisible = false
                    filterButtonMenu.isVisible = true
                    sortingButtonMenu.isVisible = true
                    accountInfo.visibility = View.VISIBLE
                }
                FRAGMENT_EQUALIZER -> {
                    carAddFab.visibility = View.GONE
                    mainCollapsing.title = getString(R.string.equalizer)
                    mainAppbar.setExpanded(false)
                    enableToolBarScrolling()
                    searchButtonMenu.isVisible = true
                    filterButtonMenu.isVisible = false
                    sortingButtonMenu.isVisible = false
                    accountInfo.visibility = View.VISIBLE
                }
                FRAGMENT_SPEED -> {
                    carAddFab.visibility = View.GONE
                    mainCollapsing.title = getString(R.string.speed)
                    mainAppbar.setExpanded(false)
                    enableToolBarScrolling()
                    searchButtonMenu.isVisible = false
                    filterButtonMenu.isVisible = false
                    sortingButtonMenu.isVisible = false
                    accountInfo.visibility = View.VISIBLE
                }
                FRAGMENT_NEWS -> {
                    carAddFab.visibility = View.GONE
                    mainCollapsing.title = getString(R.string.news)
                    mainAppbar.setExpanded(true)
                    enableToolBarScrolling()
                    searchButtonMenu.isVisible = false
                    filterButtonMenu.isVisible = false
                    sortingButtonMenu.isVisible = false
                    accountInfo.visibility = View.VISIBLE
                }
                FRAGMENT_SETTINGS -> {
                    carAddFab.visibility = View.GONE
                    mainCollapsing.title = getString(R.string.settings)
                    mainAppbar.setExpanded(true)
                    disableToolBarScrolling()
                    searchButtonMenu.isVisible = false
                    filterButtonMenu.isVisible = false
                    sortingButtonMenu.isVisible = false
                    accountInfo.visibility = View.GONE
                }
            }
        }

    }


    private fun disableToolBarScrolling() {
        val params = binding.mainCollapsing.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = 0
    }

    private fun enableToolBarScrolling() {
        val params = binding.mainCollapsing.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
    }


    private fun hideResource() {
        with(binding) {
            HEIGHT_APPBAR = mainAppbar.measuredHeight
            mainAppbar.layoutParams?.height = 0
            bottomNavigationMenu.visibility = View.GONE
        }
    }

    private fun showResource() {
        with(binding) {
            mainAppbar.layoutParams?.height = HEIGHT_APPBAR
            bottomNavigationMenu.visibility = View.VISIBLE
        }
    }


    override fun onItemSelected() {
        hideResource()
    }

    override fun onSaveButtonClick() {
        showResource()
    }


    companion object {
        private var HEIGHT_APPBAR = 0
        private const val FRAGMENT_ADD = "fragment_add"
        private const val FRAGMENT_EQUALIZER = "fragment_equalizer"
        private const val FRAGMENT_SPEED = "fragment_speed"
        private const val FRAGMENT_NEWS = "fragment_news"
        private const val FRAGMENT_SETTINGS = "fragment_settings"
    }

}