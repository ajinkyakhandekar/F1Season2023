package com.ajinkya.formula1.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ajinkya.formula1.core.designsystem.theme.DarkOrange

@Composable
fun InfoTitle(
    modifier: Modifier = Modifier,
    textStart: String,
    textCenter: String,
    textEnd: String
) {
    Column(modifier = modifier) {
        InfoItem(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 12.dp),
            textStart = textStart,
            textCenter = textCenter,
            textEnd = textEnd,
            fontWeight = FontWeight.Bold
        )

        Divider(
            color = DarkOrange,
            thickness = 2.dp
        )
    }
}
