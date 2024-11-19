package yunwen.exhibition.challengeflickr

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1&tags=porcupine")
    suspend fun fetchData(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("tags") tags: String = ""
    ): DataNetwork
}