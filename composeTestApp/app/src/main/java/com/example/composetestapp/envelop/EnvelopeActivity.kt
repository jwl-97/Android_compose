package com.example.composetestapp.envelop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.composetestapp.R

class EnvelopeActivity : AppCompatActivity() {
    val ivEnvelope: ImageView by lazy {
        findViewById<ImageView>(R.id.iv_envelope)
    }

    val tvBadge: TextView by lazy {
        findViewById<TextView>(R.id.tv_badge)
    }

    val ivFire: ImageView by lazy {
        findViewById<ImageView>(R.id.iv_fire)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envelope)

//        updateCount(0)
        updateCount(1)
//        updateCount(100)
    }

    @SuppressLint("SetTextI18n")
    private fun updateCount(count: Int) {
        if (count <= 99) {
            tvBadge.text = "$count"
        }

        if (count > 0 && !isVisible(tvBadge)) {
            setVisible(tvBadge, View.VISIBLE)
            setEnvelop(ivEnvelope, R.drawable.envelope_letter_icon)

        } else if (count == 0 && isVisible(tvBadge)) {
            setVisible(tvBadge, View.INVISIBLE)
            setEnvelop(ivEnvelope, R.drawable.envelope_icon)
        }

        if (count > 99 && !isVisible(ivFire)) {
            setVisible(ivFire, View.VISIBLE)
            tvBadge.text = "99+"
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