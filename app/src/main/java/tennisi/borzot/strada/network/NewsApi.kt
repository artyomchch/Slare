package tennisi.borzot.strada.network

import retrofit2.http.GET
import retrofit2.http.Query
import tennisi.borzot.strada.network.pojo.NewsItem

interface NewsApi {

    @GET("everything")
    suspend fun getPost(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
       // @Query(QUERY_PARAM_DESC) desc: String = DESC,
        @Query(QUERY_PARAM_ARTICLE) desc: String = ARTICLE,
        @Query(QUERY_PARAM_LANG) lang: String = LANG,
        @Query(QUERY_PARAM_SORT) sort: String = SORT,
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: String = PAGE_SIZE,
        @Query(QUERY_PARAM_PAGE) page: String = PAGE,
    ): NewsItem


    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_DESC = "q"
        private const val QUERY_PARAM_ARTICLE = "qInTitle"
        private const val QUERY_PARAM_LANG = "language"
        private const val QUERY_PARAM_SORT = "sortBy"
        private const val QUERY_PARAM_PAGE_SIZE = "pageSize"
        private const val QUERY_PARAM_PAGE = "page"


        private const val API_KEY = "b7396f308f5c4d268396d4deee8b746e"
        private const val DESC = "\"audi\" OR \"auto\" OR \"car\" OR \"vehicle\""
        private const val ARTICLE = "car OR vehicle"
        private const val LANG = "en"
        private const val SORT = "publishedAt"
        private const val PAGE_SIZE = "100"
        private const val PAGE = "1"
    }
}