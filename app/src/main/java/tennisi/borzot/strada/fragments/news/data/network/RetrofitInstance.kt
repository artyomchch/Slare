package tennisi.borzot.strada.fragments.news.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tennisi.borzot.strada.utils.Constants

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}