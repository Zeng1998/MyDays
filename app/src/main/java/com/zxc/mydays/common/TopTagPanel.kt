package com.zxc.mydays.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zxc.mydays.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopTagPanel(
    tags: List<String>,
    selectedTagIndex: Int,
    selectedTagIndexChange: (Int) -> Unit
) {
    var showFullTagPanel by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surface)
            .padding(start = 16.dp, end = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        FlowRow(
            modifier = Modifier
                .weight(1f)
                .let {
                    if (showFullTagPanel) {
                        it
                    } else {
                        it.horizontalScroll(rememberScrollState())
                    }
                },
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            tags.forEachIndexed { index, tag ->
                val selected = index == selectedTagIndex
                Text(
                    text = "#${tag}",
                    color = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.clickable {
                        selectedTagIndexChange(index)
                    }
                )
            }
        }
        Row(
            modifier = Modifier.width(36.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val iconId = if (showFullTagPanel) R.drawable.chevron_up else R.drawable.chevron_down
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.clickable(
                    onClick = {
                        showFullTagPanel = !showFullTagPanel
                    }
                )
            )
        }
    }
}