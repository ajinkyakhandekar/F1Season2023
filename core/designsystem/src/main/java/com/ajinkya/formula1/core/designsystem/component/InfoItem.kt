package com.ajinkya.formula1.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    textStart: String,
    textCenter: String,
    textEnd: String,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(weight = 0.15f)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = textStart,
                fontWeight = fontWeight,
                fontSize = 14.sp,
            )
        }

        Box(
            modifier = Modifier
                .weight(weight = 0.5f)
                .padding(start = 32.dp, end= 8.dp)
        ) {
            Text(
                modifier = Modifier,
                text = textCenter,
                fontWeight = fontWeight,
                fontSize = 14.sp,
            )
        }

        Box(
            modifier = Modifier
                .weight(weight = 0.35f)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = textEnd,
                fontWeight = fontWeight,
                fontSize = 14.sp,
            )
        }
    }
}
