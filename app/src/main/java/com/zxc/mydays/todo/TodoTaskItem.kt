package com.zxc.mydays.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zxc.mydays.R

@Composable
fun TodoTaskItem(
    text: String,
    disabled: Boolean = false,
    deleted: Boolean = false,
    hasSubItems: Boolean = false,
    isChecked: Boolean = false,
    checkEnabled: Boolean = true,
    onCheck: (Boolean) -> Unit = {},
    onClick: () -> Unit = {},
) {
    var checkState by remember { mutableStateOf(isChecked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (hasSubItems) {
            Icon(
                painter = painterResource(
                    id = if (isChecked) R.drawable.list_checks
                    else R.drawable.list_todo
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(24.dp),
                tint = if (disabled) Color.Gray else Color.Unspecified
            )
        } else {
            CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                Checkbox(
                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                    checked = checkState,
                    enabled = checkEnabled,
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