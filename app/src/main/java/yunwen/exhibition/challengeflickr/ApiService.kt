package yunwen.exhibition.challengeflickr

import retrofit2.http.GET

interface ApiService {
    @GET("services/feeds/photos_public")
    suspend fun fetchData(): DataNetwork
}