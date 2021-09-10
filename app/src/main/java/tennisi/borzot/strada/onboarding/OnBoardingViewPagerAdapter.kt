package tennisi.borzot.strada.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import tennisi.borzot.strada.R
import tennisi.borzot.strada.onboarding.mvpOnBoarding.OnBoardingData
import tennisi.borzot.strada.onboarding.mvpOnBoarding.OnBoardingMain

class OnBoardingViewPagerAdapter(private var context: OnBoardingMain, private var onBoardingDataList: List<OnBoardingData>) : PagerAdapter() {

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

        val onBoardingImageView: ImageView = view.findViewById(R.id.onBoardingImageView)
        val firstChar: TextView = view.findViewById(R.id.firstChar)
        val mainText: TextView = view.findViewById(R.id.onBoardingMainText)
        val desc: TextView = view.findViewById(R.id.onBoardingDescription)

        onBoardingImageView.setImageResource(onBoardingDataList[position].imageUrl)
        firstChar.text = onBoardingDataList[position].firstChar
        mainText.text = onBoardingDataList[position].title
        desc.text = onBoardingDataList[position].desc

        container.addView(view)
        return view
    }
}