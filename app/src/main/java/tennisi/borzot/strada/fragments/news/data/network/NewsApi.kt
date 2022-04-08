package tennisi.borzot.strada.fragments.news.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import tennisi.borzot.strada.fragments.news.data.network.pojo.NewsDto

interface NewsApi {

    @GET("everything")
    suspend fun getPost(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_ARTICLE) desc: String = ARTICLE,
        @Query(QUERY_PARAM_LANG) lang: String = LANG,
        @Query(QUERY_PARAM_SORT) sort: String = SORT,
        @Query(QUERY_PARAM_FROM) from: String = FROM,
        @Query(QUERY_PARAM_TO) to: String = TO,
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: String = PAGE_SIZE,
        ): NewsDto

    @GET("everything")
    suspend fun getPostPaging(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_ARTICLE) desc: String = ARTICLE,
        @Query(QUERY_PARAM_LANG) lang: String = LANG,
        @Query(QUERY_PARAM_SORT) sort: String = SORT,
        @Query(QUERY_PARAM_FROM) from: String = FROM,
        @Query(QUERY_PARAM_TO) to: String = TO,
        @Query(QUERY_PARAM_PAGE) page: Int = PAGE,
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: Int = PAGE_SIZE_PAGE,
    ): NewsDto


    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_ARTICLE = "qInTitle"
        private const val QUERY_PARAM_LANG = "language"
        private const val QUERY_PARAM_SORT = "sortBy"
        private const val QUERY_PARAM_FROM = "from"
        private const val QUERY_PARAM_TO = "to"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PAGE_SIZE = "pageSize"


        private const val API_KEY = "b7396f308f5c4d268396d4deee8b746e"
        private const val ARTICLE = "car OR vehicle"
        private const val LANG = "en"
        private const val SORT = "publishedAt"
        private const val FROM = ""
        private const val TO = ""
        private const val PAGE_SIZE = "100"
        private const val PAGE_SIZE_PAGE = 20
        private const val PAGE = 1

    }
}