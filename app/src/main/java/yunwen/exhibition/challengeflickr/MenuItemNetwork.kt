package yunwen.exhibition.challengeflickr

import com.google.gson.annotations.SerializedName


data class DataNetwork(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("generator")
    val generator: String,
    @SerializedName("item")
    val item: List<ItemNetwork>
)

data class ItemNetwork(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("media")
    val media: Media,
    @SerializedName("date_taken")
    val dateTaken: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("published")
    val published: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("tags")
    val tags: String
){
//    fun toMenuItemRoom() = MenuItemRoom(
//        id,
//        title,
//        description,
//        price,
//        image,
//        category
//    )
}

data class Media(
    @SerializedName("m")
    val m: String,
)


