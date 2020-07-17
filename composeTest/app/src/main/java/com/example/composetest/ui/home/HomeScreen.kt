package com.example.composetest.ui.home

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.*
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.composetest.data.Post
import com.example.composetest.data.posts
import com.example.composetest.envelope.BadgeEnvelope
import com.example.composetest.ui.Screen

sealed class Tag
private object TopTag : Tag()
private object HorizontalImageTag : Tag()
private object BadgeImageTag : Tag()

@Composable
fun HomeScreen(
    navigateTo: (Screen) -> Unit
) {
    Scaffold(
        topAppBar = {
            TopAppBar(title = { Text(text = "ComposeTest") })
        },
        bodyContent = {
            HomeScreenPopularSection(posts = posts, navigateTo = navigateTo)
        }
    )
}

@Composable
private fun HomeScreenPopularSection(
    posts: List<Post>,
    navigateTo: (Screen) -> Unit
) {
//    Column() {
//        ProvideEmphasis(EmphasisAmbient.current.high) {
//            Text(
//                modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth(),
//                text = "Popular on",
//                style = MaterialTheme.typography.subtitle1
//            )
//        }
//
//        HorizontalScroller(
//            modifier = Modifier.padding(end = 16.dp)
//        ) {
//            posts.forEach { post ->
//                PostCard(post, navigateTo, Modifier.padding(start = 16.dp, bottom = 16.dp))
//            }
//        }
//
////        BadgeEnvelope(100)
//    }

    ConstraintLayout(
        constraintSet = ConstraintSet {
            val topText = tag(TopTag).apply {
                top constrainTo parent.top
                left constrainTo parent.left
                right constrainTo parent.right
            }
            val horizontalImage = tag(HorizontalImageTag).apply {
                top constrainTo topText.bottom
                left constrainTo topText.left
                right constrainTo topText.right
            }
            val badgeImage = tag(BadgeImageTag).apply {
                top constrainTo horizontalImage.bottom
                right constrainTo parent.right
                bottom constrainTo horizontalImage.bottom
            }
        }) {


        ProvideEmphasis(EmphasisAmbient.current.high) {
            Text(
                modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth() + Modifier.tag(TopTag),
                text = "Popular on",
                style = MaterialTheme.typography.subtitle1
            )
        }

        HorizontalScroller(
            modifier = Modifier.padding(end = 16.dp) + Modifier.tag(HorizontalImageTag)
        ) {
            posts.forEach { post ->
                PostCard(post, navigateTo, Modifier.padding(start = 16.dp, bottom = 16.dp))
            }
        }

        Stack(modifier = Modifier.tag(BadgeImageTag)) {
            BadgeEnvelope(count = 5)
        }
    }
}


@Preview("Home screen")
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navigateTo = { })
}