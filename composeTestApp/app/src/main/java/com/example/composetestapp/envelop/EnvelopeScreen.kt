package com.example.composetestapp.envelop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.composetestapp.R

class EnvelopeScreen {
    @Composable
    fun Envelope(fire: Boolean, isOpen: Boolean) {
        Image(
            painter = painterResource(id = if (isOpen) R.drawable.envelope_letter_icon else R.drawable.envelope_icon),
            contentDescription = null,
            modifier = Modifier.size(140.dp, 100.dp),
            contentScale = ContentScale.Crop
        )

        if (fire) Fire()
    }

    @Composable
    fun Fire() {
        Image(
            painter = painterResource(id = R.drawable.envelope_fire),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp, 60.dp)
                .layoutId("fire"),
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    fun Badge(count: Int) {
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier
                .size(42.dp, 35.dp)
                .layoutId("badgeButton")
        ) {
            Text(
                text = if (count > 99) "99+" else "$count",
                style = TextStyle(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun BadgeEnvelope(count: Int) {
        ConstraintLayout(decoupledConstraints()) {
            Envelope(
                fire = count > 99,
                isOpen = count > 0
            )

            if (count > 0) {
                Badge(count = count)
            }
        }
    }

    private fun decoupledConstraints(): ConstraintSet {
        return ConstraintSet {
            val badgeButton = createRefFor("badgeButton")
            val fire = createRefFor("fire")

            constrain(badgeButton) {
                end.linkTo(parent.end)
            }

            constrain(fire) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
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
}