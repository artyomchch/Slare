package tennisi.borzot.strada.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment
import tennisi.borzot.strada.fragments.equalizer.EqualizerFragment
import tennisi.borzot.strada.fragments.news.presentation.NewsFragment
import tennisi.borzot.strada.fragments.settings.SettingsFragment
import tennisi.borzot.strada.fragments.speed.SpeedFragment


class ScreenSlidePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = FRAGMENT_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AddFragment()
            }
            1 -> {
                EqualizerFragment()
            }
            2 -> {
                SpeedFragment()
            }
            3 -> {
                NewsFragment()
            }
            4 -> {
                SettingsFragment()
            }
            else -> SpeedFragment()
        }
    }

    companion object {
        const val FRAGMENT_SIZE = 5
    }
}