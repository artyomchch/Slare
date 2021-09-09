package tennisi.borzot.strada.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tennisi.borzot.strada.fragments.add.MVPAddFragment.AddFragment
import tennisi.borzot.strada.fragments.equalizer.EqualizerFragment
import tennisi.borzot.strada.fragments.news.NewsFragment
import tennisi.borzot.strada.fragments.news.NewsFragmentViewPager
import tennisi.borzot.strada.fragments.news.UserDetailsFragment
import tennisi.borzot.strada.fragments.settings.SettingsFragment
import tennisi.borzot.strada.fragments.speed.SpeedFragment

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
                NewsFragmentViewPager()
            }
            4 -> {
                SettingsFragment()
            }
            else -> SpeedFragment()

        }
    }

}