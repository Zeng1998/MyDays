package com.zxc.mydays.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MyScrollableTabRow(
    tabs: List<String>,
    pagerState: PagerState,
) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        for ((index, tab) in tabs.withIndex()) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val activeTab = index == pagerState.currentPage
                val nextActiveTab =
                    pagerState.currentPageOffsetFraction >= 0.25
                            && pagerState.currentPageOffsetFraction < 0.5
                            && index == pagerState.currentPage + 1
                            ||
                            pagerState.currentPageOffsetFraction <= -0.25
                            && pagerState.currentPageOffsetFraction > -0.5
                            && index == pagerState.currentPage - 1
                Text(
                    text = tab,
                    color = if (activeTab) {
                        MaterialTheme.colorScheme.primary
                    } else if (nextActiveTab) {
                        Color(0xFF776c96)
                    } else {
                        Color.Gray
                    },
                    fontWeight = if (index == pagerState.currentPage) FontWeight.Bold else FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(
                    modifier = Modifier.width(48.dp),
                    thickness = if (activeTab) 2.dp else 1.dp,
                    color = if (activeTab) {
                        MaterialTheme.colorScheme.primary
                    } else if (nextActiveTab) {
                        Color(0xFFbbb6cb)
                    } else {
                        Color.Transparent
                    }
                )

            }
        }
    }
}