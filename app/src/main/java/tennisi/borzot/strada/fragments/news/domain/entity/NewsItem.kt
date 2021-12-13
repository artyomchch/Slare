package tennisi.borzot.strada.fragments.news.domain.entity


data class NewsItem(
    val url: String,
    val imageUrl: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val time: String?,
    val source: String?,
    val date: String?,
    var id: Int = UNDEFINED_ID
) {


    companion object {
        const val UNDEFINED_ID = 0
    }
}


