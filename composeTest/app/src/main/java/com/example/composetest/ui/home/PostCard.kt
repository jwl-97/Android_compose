package com.example.composetest.ui.home

import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredSize
import androidx.ui.material.Card
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.composetest.R
import com.example.composetest.data.Post
import com.example.composetest.data.*
import com.example.composetest.ui.Screen

@Composable
fun PostCard(
    post: Post,
    navigateTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.preferredSize(280.dp, 240.dp)
    ) {
        Column(modifier = Modifier.clickable(onClick = { navigateTo(Screen.Article(post.id)) })) {

            val image = when (post.id.substring(4).toInt()) {
                0, 5 -> R.drawable.post_1
                1, 6 -> R.drawable.post_2
                2, 7 -> R.drawable.post_3
                3, 8 -> R.drawable.post_4
                else -> R.drawable.post_5
            }

            Image(
                asset = imageResource(image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .preferredHeight(100.dp)
                    .fillMaxWidth()
            )

            Column(modifier = Modifier.padding(16.dp)) {
                val emphasisLevels = EmphasisAmbient.current
                ProvideEmphasis(emphasisLevels.high) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = post.metadata.author.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "${post.metadata.date} - " +
                                "${post.metadata.readTimeMinutes} min read",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Preview("Regular colors")
@Composable
fun PreviewPostCardPopular() {
    PostCard(post1, {})
}