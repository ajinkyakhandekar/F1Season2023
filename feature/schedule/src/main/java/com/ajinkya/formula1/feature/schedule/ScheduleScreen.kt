package com.ajinkya.formula1.feature.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ajinkya.formula1.data.local.entity.Schedule
import com.ajinkya.formula1.presentation.composable.InfoCard
import com.ajinkya.formula1.presentation.composable.InfoItem
import com.ajinkya.formula1.presentation.composable.InfoTitle
import com.ajinkya.formula1.presentation.theme.GrayLight

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    schedule: List<Schedule>
) {
    InfoCard(
        modifier = modifier,
    ) {
        Column {
            InfoTitle(
                modifier = Modifier.padding(top = 12.dp),
                textStart = "Round",
                textCenter = "Race",
                textEnd = "Date/Time"
            )

            LazyColumn {
                itemsIndexed(schedule) { index, item ->
                    InfoItem(
                        modifier = Modifier.padding(vertical = 12.dp),
                        textStart = index.plus(1).toString(),
                        textCenter = "${item.raceName}\n${item.country}",
                        textEnd = "${item.date}\n${item.time}",
                    )
                    if (index < schedule.lastIndex)
                        Divider(
                            color = GrayLight,
                            thickness = 1.dp
                        )
                }
            }
        }
    }
}