package com.zxc.mydays.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zxc.mydays.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
) {
    var titleState by remember { mutableStateOf("任务xx") }
    var checkState by remember { mutableStateOf(false) }
    val random = (1..3).random()
    val titleFocusRequester = remember { FocusRequester() }
    val titleBoxHeight = with(LocalDensity.current) { 42.sp.toDp() }
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    modifier = Modifier
                        .focusRequester(titleFocusRequester)
                        .fillMaxWidth()
                        .height(titleBoxHeight),
                    value = titleState,
                    onValueChange = { titleState = it },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 18.sp,
                    ),
                    cursorBrush = SolidColor(Color.DarkGray),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            if (titleState.isEmpty()) {
                                Text(
                                    text = "请输入待办项",
                                    color = Color(0xFF868e96),
                                    fontSize = 18.sp,
                                )
                            }
                            innerTextField()
                        }
                    },
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when(random){
                        1->Icon(
                            painter = painterResource(id = R.drawable.meh),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFF228be6),
                        )
                        2->Icon(
                            painter = painterResource(id = R.drawable.smile),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFF51cf66),
                        )
                        3->Icon(
                            painter = painterResource(id = R.drawable.frown),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFFf03e3e),
                        )
                    }
                    when(random){
                        1->Text(text = "进行中 (1/5)", color = Color.Gray)
                        2->Text(text = "已完成 (5/5)", color = Color.Gray)
                        3->Text(text = "已废弃 (0/4)", color = Color.Gray)
                    }
                }
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.clickable {  }
                )
            }
            Column(
                modifier = Modifier.padding(start = 4.dp)
            ) {
                TodoEditableItem(
                    text = "任务5",
                    isChecked = false,
                    onCheck = {},
                    onContentChange = {}
                )
                TodoEditableItem(
                    text = "任务6",
                    isChecked = false,
                    onCheck = {},
                    onContentChange = {}
                )
                TodoEditableItem(
                    text = "任务7",
                    isChecked = false,
                    onCheck = {},
                    onContentChange = {}
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                ),
                onClick = {}) {
                Text("删除")
            }
        }
    }
}