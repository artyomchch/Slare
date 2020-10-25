package tennisi.borzot.slare.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tennisi.borzot.slare.onboarding.fragments.add.AddFragment
import tennisi.borzot.slare.onboarding.fragments.equalizer.EqualizerFragment
import tennisi.borzot.slare.onboarding.fragments.player.PlayerFragment
import tennisi.borzot.slare.onboarding.fragments.settings.SettingsFragment
import tennisi.borzot.slare.onboarding.fragments.speed.SpeedFragment

@Suppress("DEPRECATION")
internal class PagerViewAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
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
                PlayerFragment()
            }
            4 -> {
                SettingsFragment()
            }
            else -> SpeedFragment()

        }
    }

}