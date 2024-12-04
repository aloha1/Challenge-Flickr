package yunwen.exhibition.challengeflickr

data class DataNetwork(
//    val title: String,
//    val link: String,
//    val description: String,
//    val modified: String,
//    val generator: String,
    val items: List<ItemNetwork>
)

data class ItemNetwork(
    val title: String,
    val media: Media,
    val description: String,
    val published: String,
    val author: String,
)

data class Media(
    val m: String,
)


