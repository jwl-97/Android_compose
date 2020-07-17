package com.example.composetest.envelope

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.contentColor
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.composetest.R
import com.example.composetest.data.*
import com.example.composetest.ui.Screen
import com.example.composetest.ui.article.PostContent

@Composable
fun BadgeEnvelope(count: Int) {
    Stack {
        Envelope(
            fire = count > 99,
            isOpen = count > 0
        )
        if (count > 0) {
            Badge(count = count)
        }
    }
}

@Composable
fun Envelope(fire: Boolean, isOpen: Boolean) {
    Image(
        asset = if (isOpen) imageResource(R.drawable.envelope_letter_icon) else imageResource(R.drawable.envelope_icon),
        contentScale = ContentScale.Crop,
        modifier = Modifier.preferredSize(140.dp, 100.dp)
    )

    if (fire) Fire()
}

@Composable
fun Fire() {
    Image(
        asset = imageResource(R.drawable.fire),
        contentScale = ContentScale.Crop,
        modifier = Modifier.preferredSize(90.dp).padding(45.dp, 20.dp, 0.dp, 0.dp)
    )
}

@Composable
fun Badge(count: Int) {
    Column(horizontalGravity = Alignment.End) {
        TextButton(
            backgroundColor = Color.Green,
            onClick = {},
            modifier = Modifier.preferredSize(35.dp, 35.dp),
            text = {
                Text(
                    text = if (count > 99) "99+" else "$count",
                    style = TextStyle(
                        fontSize = 11.sp,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}

@Preview("Envelope_count0")
@Composable
fun PreviewEnvelopeCount0() {
    BadgeEnvelope(0)
}

@Preview("Envelope_count1")
@Composable
fun PreviewEnvelopeCount1() {
    BadgeEnvelope(5)
}

@Preview("Envelope_count100")
@Composable
fun PreviewEnvelopeCount100() {
    BadgeEnvelope(100)
}