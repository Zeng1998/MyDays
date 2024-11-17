package com.zxc.mydays.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.zxc.mydays.common.TopTagPanel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoScreen(){
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
        TopTagPanel(
            tags = tags,
            selectedTagIndex = selectedTagIndex,
            selectedTagIndexChange = { selectedTagIndex = it }
        )
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