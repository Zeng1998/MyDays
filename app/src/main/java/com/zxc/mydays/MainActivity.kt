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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.zxc.mydays.memo.MemoCard
import com.zxc.mydays.ui.theme.MyDaysTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(
        ExperimentalFoundationApi::class, ExperimentalLayoutApi::class,
        ExperimentalMaterial3Api::class
    )
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
                                .zIndex(1f)
                                .background(MaterialTheme.colorScheme.surface)
                                .height(42.dp),
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
                            var showFullTagPanel by remember { mutableStateOf(false) }
                            val tags = listOf(
                                "日记",
                                "菜谱",
                                "tag1",
                                "tag2",
                                "tag3",
                                "tag4",
                                "tag5",
                                "tag6",
                                "tag7",
                                "tag8",
                                "tag9",
                                "tag10"
                            )
                            var selectedTagIndex by remember { mutableIntStateOf(0) }
                            Column(modifier = Modifier.fillMaxSize()) {
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
                                            Text(
                                                text = "#${tag}",
                                                color = if (index == selectedTagIndex) {
                                                    MaterialTheme.colorScheme.primary
                                                } else {
                                                    Color.Unspecified
                                                },
                                                fontWeight = if (index == selectedTagIndex) {
                                                    FontWeight.Bold
                                                } else {
                                                    FontWeight.Normal
                                                },
                                                modifier = Modifier.clickable(
                                                    onClick = {
                                                        selectedTagIndex = index
                                                    }
                                                )
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier.width(36.dp),
                                        horizontalArrangement = Arrangement.Center,
                                    ) {
                                        Icon(
                                            painter = if (showFullTagPanel) painterResource(R.drawable.chevron_up)
                                            else painterResource(R.drawable.chevron_down),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(
                                                onClick = {
                                                    showFullTagPanel = !showFullTagPanel
                                                }
                                            )
                                        )
                                    }
                                }
                                val pullRefreshState = rememberPullToRefreshState()
                                if (pullRefreshState.isRefreshing) {
                                    LaunchedEffect(true) {
                                        // fetch something
                                        delay(1500)
                                        pullRefreshState.endRefresh()
                                    }
                                }
                                Box(
                                    Modifier
                                        .nestedScroll(pullRefreshState.nestedScrollConnection)
                                        .zIndex(-1f)
                                ) {
                                    PullToRefreshContainer(
                                        state = pullRefreshState,
                                        modifier = Modifier
                                            .align(Alignment.TopCenter)
                                            .zIndex(0.5f),
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary,
                                    )
                                    LazyVerticalStaggeredGrid(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = with(LocalDensity.current) { pullRefreshState.verticalOffset.toDp() })
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        columns = StaggeredGridCells.Fixed(2),
                                        verticalItemSpacing = 16.dp,
                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    ) {
                                        items(100) { index ->
                                            when (index % 7) {
                                                0 -> MemoCard(
                                                    title = "标题文本",
                                                    content = "正文小一点的文本",
                                                    tags = listOf("标签", "tag"),
                                                    createTs = 1731761442000,
                                                    updateTs = 1731761442000
                                                )

                                                1 -> MemoCard(
                                                    title = "标题文本",
                                                    content = "正文小一点的文本",
                                                    tags = listOf("标签", "tag"),
                                                    createTs = 1430761442000,
                                                    updateTs = 1430761442000
                                                )

                                                2 -> MemoCard(
                                                    title = "只有标题",
                                                    createTs = 1430761442000,
                                                    updateTs = 1430761442000
                                                )

                                                3 -> MemoCard(
                                                    content = "更新时间和创建时间不一样",
                                                    createTs = 1731761442000,
                                                    updateTs = 1731761443000
                                                )

                                                4 -> MemoCard(
                                                    title = "标题文本",
                                                    content = "长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本",
                                                    tags = listOf("标签", "tag"),
                                                    createTs = 1731761442000,
                                                    updateTs = 1731761442000
                                                )

                                                5 -> MemoCard(
                                                    title = "带图片",
                                                    content = "长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本",
                                                    image = "test",
                                                    tags = listOf("标签", "tag"),
                                                    createTs = 1731761442000,
                                                    updateTs = 1731761442000
                                                )

                                                else -> MemoCard(
                                                    content = "只有内容",
                                                    createTs = 1731761442000,
                                                    updateTs = 1731761442000
                                                )
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
    }
}