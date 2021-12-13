package tennisi.borzot.strada.fragments.news.data.mapper

import tennisi.borzot.strada.fragments.news.data.network.pojo.Article
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem
import tennisi.borzot.strada.utils.DateUtils

class NewsItemMapper {


    private fun mapNetworkModelToEntityNews(article: Article) = NewsItem(
        url = article.url,
        imageUrl = article.urlToImage,
        author = article.author,
        title = article.title,
        description = article.description,
        source = article.source.name,
        time = DateUtils.dateToTimeFormat(article.publishedAt),
        date = DateUtils.dateFormat(article.publishedAt)
    )

    fun mapListNetworkModelToListEntityNews(list: List<Article>) =
        list.map {
            mapNetworkModelToEntityNews(it)
        }


}