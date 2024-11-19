package yunwen.exhibition.challengeflickr

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import yunwen.exhibition.challengeflickr.ui.theme.LittleLemonColor
import java.util.Timer
import java.util.TimerTask

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onImageClick: (ItemNetwork)->Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var item: DataNetwork? by remember { mutableStateOf(null) }
    when (uiState) {
        is UiState.Success -> {
            Log.d("MyTag", "data: " + (uiState as UiState.Success).data)// Access the name property
            item = (uiState as UiState.Success).data
        }
        is UiState.Error -> {
            Log.d("MyTag", "error:" + (uiState as UiState.Error).message)
        }
        is UiState.Loading -> {
            Log.d("MyTag", "Loading")

        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        var searchPhrase by remember { mutableStateOf("") }
        var lastUpdateTime by remember { mutableLongStateOf(0L) }

        Column(
            modifier = Modifier
                .background(color = LittleLemonColor.green)
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
        )  {
            Text(
                text = "Android Expo",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = LittleLemonColor.yellow
            )
            OutlinedTextField(
                value = searchPhrase,
                onValueChange = { searchQuey ->
                    searchPhrase = searchQuey
                    lastUpdateTime = System.currentTimeMillis()
                },
                placeholder = { Text(text = "Enter to Search", modifier = Modifier.background(Color.White)) },
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 16.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(Color.White)
            )
            DisposableEffect(lastUpdateTime) {
                val timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastUpdateTime > 1000) {
                            viewModel.fetchData(searchPhrase)
                        }
                    }
                }, 1000)

                onDispose {
                    timer.cancel()
                }
            }
            item?.let{
                MenuItemsList (
                    it,
                    onImageClick = onImageClick
                )
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(item: DataNetwork, onImageClick: (ItemNetwork)->Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight().padding(top = 12.dp)
    ) {
        items(
            items = item.items,
            itemContent = { item ->
                Divider(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    thickness = 1.dp,
                    color = LittleLemonColor.yellow
                )
                Card {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        Column {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "Author: " + item.author,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier
                                    .fillMaxWidth(fraction = 0.75F)
                                    .padding(top = 5.dp, bottom = 5.dp)
                            )
                        }
                        GlideImage(
                            model = item.media.m,
                            modifier = Modifier.fillMaxHeight()
                                .clip(shape = RoundedCornerShape(12.dp))
                                .clickable(onClick = { onImageClick(item) }),
                            contentDescription = "Dish Image",
                        )
                    }
                }
            }
        )
    }
}
