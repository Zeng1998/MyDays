package com.zxc.mydays.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zxc.mydays.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditableItem(
    text: String,
    isChecked: Boolean,
    onCheck: (Boolean) -> Unit,
    onContentChange: (String) -> Unit,
) {
    var content by remember { mutableStateOf(text) }
    val focusRequester = remember { FocusRequester() }
    var focusState by remember { mutableStateOf(false) }
    var checkState by remember { mutableStateOf(isChecked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
            Checkbox(
                checked = checkState,
                onCheckedChange = {
                    checkState = it
                    onCheck(it)
                },
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start=8.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        focusState = it.isFocused
                        if (focusState) {
//                            onFocusChange(key)
                        }
                    }
                    .onKeyEvent {
                        if (it.key == Key.Backspace && content.isEmpty()) {
//                            onDelete()
                            true
                        } else {
                            false
                        }
                    }
                    .fillMaxSize(),
                value = TextFieldValue(text = content, selection = TextRange(content.length)),
                onValueChange = { onContentChange(it.text) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
//                        onEnter()
                    }
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                ),
                cursorBrush = SolidColor(Color.DarkGray),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        innerTextField()
                    }
                },
            )
            if(focusState) {
                IconButton(modifier = Modifier.width(36.dp), onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash_2),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}