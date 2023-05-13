package com.ajinkya.formula1.feature.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajinkya.formula1.core.designsystem.component.InfoCard
import com.ajinkya.formula1.core.designsystem.component.InfoItem
import com.ajinkya.formula1.core.designsystem.component.InfoTitle
import com.ajinkya.formula1.core.designsystem.theme.GrayLight
import com.ajinkya.formula1.core.model.Schedule

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