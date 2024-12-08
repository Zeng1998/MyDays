package com.zxc.mydays.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zxc.mydays.common.TopTagPanel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
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
    var showBottomSheet by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        TopTagPanel(
            tags = tags,
            selectedTagIndex = selectedTagIndex,
            selectedTagIndexChange = { selectedTagIndex = it }
        )
        TodoGroupPanel("进行中", 5) {
            TodoTaskItem(
                text = "任务5",
                hasSubItems = true,
                onCheck = {},
                onClick = { showBottomSheet = true }
            )
            TodoTaskItem(
                text = "任务6",
                onCheck = {},
                onClick = { showBottomSheet = true }
            )
            TodoTaskItem(
                text = "任务7",
                onCheck = {},
                onClick = { showBottomSheet = true }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TodoGroupPanel("已完成", 4) {
            TodoTaskItem(
                text = "任务1",
                disabled = true,
                isChecked = true,
                checkEnabled = false,
                onClick = { showBottomSheet = true }
            )
            TodoTaskItem(
                text = "任务3",
                disabled = true,
                hasSubItems = true,
                isChecked = true,
                checkEnabled = false,
                onClick = { showBottomSheet = true }
            )
            TodoTaskItem(
                text = "任务4",
                disabled = true,
                isChecked = true,
                checkEnabled = false,
                onClick = { showBottomSheet = true }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TodoGroupPanel("已废弃", 11) {
            TodoTaskItem(
                text = "任务2",
                disabled = true,
                deleted = true,
                onCheck = {},
                onClick = { showBottomSheet = true }
            )
            TodoTaskItem(
                text = "task",
                disabled = true,
                deleted = true,
                onCheck = {},
                onClick = { showBottomSheet = true }
            )
            TodoTaskItem(
                text = "删除",
                disabled = true,
                deleted = true,
                onCheck = {},
                onClick = { showBottomSheet = true }
            )
        }
        if (showBottomSheet) {
            TodoDetailSheet(
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            )
        }
    }
}