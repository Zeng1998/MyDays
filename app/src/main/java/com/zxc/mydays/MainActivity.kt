package com.zxc.mydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.zxc.mydays.ui.theme.MyDaysTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyDaysTheme {
                val tabs = listOf("小记", "待办", "日常")
                val pagerState = rememberPagerState(pageCount = { 3 })
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                            },
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
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
//                                Text(pagerState.currentPageOffsetFraction.toString())
                                // TODO +0.25以上 右边的tab文字颜色变化 -0.25以下，左边的tab文字颜色变化
                                for ((index, tab) in tabs.withIndex()) {
                                    Column(
                                        modifier = Modifier
                                            .wrapContentWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Text(
                                            text = tab,
                                            color = if (index == pagerState.currentPage) {
                                                MaterialTheme.colorScheme.primary
                                            } else if (pagerState.currentPageOffsetFraction >= 0.25
                                                && pagerState.currentPageOffsetFraction < 0.5
                                                && index == pagerState.currentPage + 1
                                                ||
                                                pagerState.currentPageOffsetFraction <= -0.25
                                                && pagerState.currentPageOffsetFraction > -0.5
                                                && index == pagerState.currentPage - 1
                                            ) {
                                                Color(0xFF776c96)
                                            } else {
                                                Color.Gray
                                            },
                                            fontWeight = if (index == pagerState.currentPage) FontWeight.Bold else FontWeight.Normal,
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        HorizontalDivider(
                                            modifier = Modifier
                                                .width(48.dp),
                                            thickness = if (index == pagerState.currentPage) 2.dp else 1.dp,
                                            color = if (index == pagerState.currentPage) {
                                                MaterialTheme.colorScheme.primary
                                            } else if (pagerState.currentPageOffsetFraction >= 0.25
                                                && pagerState.currentPageOffsetFraction < 0.5
                                                && index == pagerState.currentPage + 1
                                                ||
                                                pagerState.currentPageOffsetFraction <= -0.25
                                                && pagerState.currentPageOffsetFraction > -0.5
                                                && index == pagerState.currentPage - 1
                                            ) {
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
                            modifier = Modifier
                                .fillMaxSize()
                        ) { page ->
                            // Our page content
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}