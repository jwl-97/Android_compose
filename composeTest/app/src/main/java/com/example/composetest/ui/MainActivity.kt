package com.example.composetest.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.example.composetest.ui.home.HomeScreen
import com.example.composetest.ui.theme.testTheme
import com.example.composetest.ui.article.ArticleScreen

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { //setContentView
            testTheme { //style.xml 개념
                Crossfade(navigationViewModel.currentScreen) { screen -> //switch layout component
                    Surface(color = MaterialTheme.colors.background) { //layout
                        when (screen) {
                            is Screen.Home -> HomeScreen(
                                navigateTo = navigationViewModel::navigateTo
                            )

                            is Screen.Article -> ArticleScreen(
                                postId = screen.postId,
                                onBack = { navigationViewModel.onBack() }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!navigationViewModel.onBack()) {
            super.onBackPressed()
        }
    }
}