package com.zxc.mydays.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 8.dp)) {
        TodoGroupPanel("进行中", 5) {
            TodoTaskItem(
                text = "任务5",
                isChecked = false,
                onCheck = {}
            )
            TodoTaskItem(
                text = "任务6",
                isChecked = false,
                onCheck = {}
            )
            TodoTaskItem(
                text = "任务7",
                isChecked = false,
                onCheck = {}
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TodoGroupPanel("已完成", 4) {
            TodoTaskItem(
                text = "任务1",
                disabled = true,
                isChecked = true,
                onCheck = {}
            )
            TodoTaskItem(
                text = "任务3",
                disabled = true,
                isChecked = true,
                onCheck = {}
            )
            TodoTaskItem(
                text = "任务4",
                disabled = true,
                isChecked = true,
                onCheck = {}
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TodoGroupPanel("已废弃", 11) {
            TodoTaskItem(
                text = "任务2",
                disabled = true,
                deleted = true,
                isChecked = false,
                onCheck = {}
            )
            TodoTaskItem(
                text = "task",
                disabled = true,
                deleted = true,
                isChecked = false,
                onCheck = {}
            )
            TodoTaskItem(
                text = "删除",
                disabled = true,
                deleted = true,
                isChecked = false,
                onCheck = {}
            )
        }
    }
}