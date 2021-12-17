package tennisi.borzot.strada.fragments.news.data.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("articles")
    @Expose
    val article: List<ArticleDto>,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int
)
