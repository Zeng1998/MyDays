package com.zxc.mydays.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTaskItem(
    text: String,
    disabled: Boolean = false,
    deleted: Boolean = false,
    isChecked: Boolean,
    onCheck: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    // TODO sub task item
    var checkState by remember { mutableStateOf(isChecked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Checkbox(
                checked = checkState,
                onCheckedChange = {
                    checkState = it
                    onCheck(it)
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = if (disabled) Color.LightGray else Color.Unspecified,
                    checkedColor = if (disabled) Color.Gray else Color.Unspecified,
                    uncheckedColor = if (deleted) Color.Gray else Color.Unspecified,
                )
            )
        }
        Row {
            Text(
                text = text,
                color = if (disabled || deleted) Color.Gray else Color.Unspecified,
                textDecoration = if (deleted) TextDecoration.LineThrough else null
            )
        }
    }
}