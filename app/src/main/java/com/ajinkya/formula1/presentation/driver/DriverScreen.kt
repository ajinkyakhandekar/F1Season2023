package com.ajinkya.formula1.presentation.driver

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajinkya.formula1.data.local.entity.Driver
import com.ajinkya.formula1.presentation.composable.InfoCard
import com.ajinkya.formula1.presentation.composable.InfoItem
import com.ajinkya.formula1.presentation.composable.InfoTitle
import com.ajinkya.formula1.presentation.theme.GrayLight

@Composable
fun DriverScreen(
    modifier: Modifier = Modifier,
    drivers: List<Driver>
) {
    InfoCard(
        modifier = modifier,
    ) {
        Column {
            InfoTitle(
                modifier = Modifier.padding(top = 12.dp),
                textStart = "No.",
                textCenter = "Driver",
                textEnd = "Points"
            )

            LazyColumn {
                itemsIndexed(drivers) { index, driver ->
                    InfoItem(
                        modifier = Modifier.padding(vertical = 12.dp),
                        textStart = index.plus(1).toString(),
                        textCenter = "${driver.driverName}\n${driver.constructorName}",
                        textEnd = driver.points,
                    )
                    if (index < drivers.lastIndex)
                        Divider(
                            color = GrayLight,
                            thickness = 1.dp
                        )
                }
            }
        }
    }
}
