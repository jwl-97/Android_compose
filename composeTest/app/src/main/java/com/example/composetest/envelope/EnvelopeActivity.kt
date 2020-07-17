package com.example.composetest.envelope

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.composetest.R

class EnvelopeActivity : AppCompatActivity() {
    lateinit var ivEnvelope: ImageView
    lateinit var ivBadge: TextView
    lateinit var ivFire: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envelope)

        ivEnvelope = findViewById(R.id.iv_envelope)
        ivBadge = findViewById(R.id.iv_badge)
        ivFire = findViewById(R.id.iv_fire)

//        updateCount(0)
        updateCount(1)
//        updateCount(100)
    }

    private fun updateCount(count: Int) {
        if (count <= 99) {
            ivBadge.text = "$count"
        }

        if (count > 0 && !isVisible(ivBadge)) {
            setVisible(ivBadge, View.VISIBLE)
            setEnvelop(ivEnvelope, R.drawable.envelope_letter_icon)

        } else if (count == 0 && isVisible(ivBadge)) {
            setVisible(ivBadge, View.INVISIBLE)
            setEnvelop(ivEnvelope, R.drawable.envelope_icon)
        }

        if (count > 99 && !isVisible(ivFire)) {
            setVisible(ivFire, View.VISIBLE)
            ivBadge.text = "99+"
        } else if (count == 0 && isVisible(ivFire)) {
            setVisible(ivFire, View.INVISIBLE)
        }
    }



    private fun isVisible(view: View): Boolean {
        return view.visibility == View.VISIBLE
    }

    private fun setVisible(view: View, visible: Int) {
        view.visibility = visible
    }

    private fun setEnvelop(view: ImageView, drawable: Int) {
        view.setImageResource(drawable)
    }
}