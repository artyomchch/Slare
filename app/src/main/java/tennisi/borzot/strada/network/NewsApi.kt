package tennisi.borzot.strada.network

import retrofit2.http.GET
import retrofit2.http.Query
import tennisi.borzot.strada.network.pojo.NewsItem

interface NewsApi {

    @GET("everything")
    suspend fun getPost(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_DESC) desc: String = DESC,
        @Query(QUERY_PARAM_LANG) lang: String = LANG,
        @Query(QUERY_PARAM_SORT) sort: String = SORT,
    ): NewsItem

    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_DESC = "q"
        private const val QUERY_PARAM_LANG = "language"
        private const val QUERY_PARAM_SORT = "sortBy"

        private const val API_KEY = "b7396f308f5c4d268396d4deee8b746e"
        private const val DESC = "transportation"
        private const val LANG = "en"
        private const val SORT = "new"


    }
}