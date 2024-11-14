package com.zxc.mydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zxc.mydays.ui.theme.MyDaysTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyDaysTheme {
                val tabs = listOf("小记", "待办", "日常")
                val pagerState = rememberPagerState(pageCount = { 3 })
                val scope = rememberCoroutineScope()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {},
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.plus),
                                contentDescription = null,
                            )
                        }
                    }
                ) { innerPadding ->
                    // main contents
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(R.drawable.menu),
                                    contentDescription = null
                                )
                            }
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
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = null
                                )
                            }
                        }
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                        ) { page ->
                            Column(modifier = Modifier.fillMaxSize()) {
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .height(42.dp),
                                ) {
                                    FlowRow(
                                        modifier = Modifier
                                            .padding(end = 36.dp)
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .align(Alignment.CenterStart)
                                            .horizontalScroll(rememberScrollState())
                                    ) {
                                        for (i in 0..10) {
                                            Text(
                                                text = "#tag$i",
                                                modifier = Modifier.padding(8.dp),
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .width(36.dp)
                                            .background(MaterialTheme.colorScheme.surface),
                                        horizontalArrangement = Arrangement.Center,
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.chevron_down),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(
                                                onClick = {}
                                            )
                                        )
                                    }
                                }
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    items(100) { index ->
                                        Text(text = "Item $index")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}