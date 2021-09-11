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
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.SpannableString

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
        val mainText: TextView = view.findViewById(R.id.onBoardingMainText)
        val desc: TextView = view.findViewById(R.id.onBoardingDescription)


        onBoardingImageView.setImageResource(onBoardingDataList[position].imageUrl)
        mainText.text = SpannableString(onBoardingDataList[position].title).apply {
            setSpan(ForegroundColorSpan(context.getColor(R.color.mainOnBoardingColor)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        desc.text = onBoardingDataList[position].desc

        container.addView(view)
        return view
    }
}