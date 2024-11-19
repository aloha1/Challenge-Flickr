package yunwen.exhibition.challengeflickr

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import yunwen.exhibition.challengeflickr.DetailActivity.Companion.KEY_DESCRIPTION
import yunwen.exhibition.challengeflickr.DetailActivity.Companion.KEY_IMAGE
import yunwen.exhibition.challengeflickr.DetailActivity.Companion.KEY_AUTHOR
import yunwen.exhibition.challengeflickr.DetailActivity.Companion.KEY_PUBLISHED
import yunwen.exhibition.challengeflickr.DetailActivity.Companion.KEY_TITLE
import yunwen.exhibition.challengeflickr.ui.theme.ChallengeFlickrTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val navigate = this::startProductActivity
        setContent {
            ChallengeFlickrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(viewModel, onImageClick = navigate)
                }
            }
        }
    }

    private fun startProductActivity(itemsNetwork: ItemNetwork) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(KEY_TITLE, itemsNetwork.title)
        intent.putExtra(KEY_IMAGE, itemsNetwork.media.m)
        intent.putExtra(KEY_AUTHOR, itemsNetwork.author)
        intent.putExtra(KEY_DESCRIPTION, itemsNetwork.description)
        intent.putExtra(KEY_PUBLISHED, itemsNetwork.published)
        startActivity(intent)
    }
}
