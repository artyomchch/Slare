package tennisi.borzot.slare.onboarding

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import tennisi.borzot.slare.R

class OnBoardingViewPagerAdapter(private var context: Context, private var onBoardingDataList: List<OnBoardingData>): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       container.removeView(`object` as View)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, null)
        val imageView : ImageView
        val titleob: TextView
        val desc: TextView


        imageView = view.findViewById(R.id.imageView)
        titleob = view.findViewById(R.id.titleob)
        desc = view.findViewById(R.id.desc)

        imageView.setImageResource(onBoardingDataList[position].imageUrl)
        titleob.text = onBoardingDataList[position].title
        desc.text = onBoardingDataList[position].desc

        container.addView(view)
        return view
    }
}