package com.ajinkya.formula1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ajinkya.formula1.presentation.screen.F1Screen
import com.ajinkya.formula1.presentation.theme.F1Theme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity that will contain the compose screens
 */
@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            F1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    F1Screen()
                }
            }
        }
    }
}
