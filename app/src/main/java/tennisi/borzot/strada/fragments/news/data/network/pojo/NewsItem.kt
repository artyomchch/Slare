package tennisi.borzot.strada.fragments.news.data.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsItem(
    @SerializedName("articles")
    @Expose
    val articles: List<Article>,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int
)
