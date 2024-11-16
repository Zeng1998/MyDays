package com.zxc.mydays.memo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun adaptiveTimeFormat(timestamp: Long): String {
    // 如果是几秒/几分钟/几小时前，显示几秒/几分钟/几小时前
    // 如果是今天，显示今天上午/下午+时间
    // 如果是昨天，显示昨天上午/下午+时间
    // 其他情况直接显示日期+时间
    val ins = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
    val now = LocalDateTime.now()
    if (ins.year == now.year) {
        return DateTimeFormatter.ofPattern("MM/dd hh:mm").format(ins)
    } else {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(ins)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MemoCard(
    title: String? = null,
    content: String? = null, // TODO 自动截断+省略号
    image: String? = null,
    tags: List<String>? = null,
    createTs: Long,
    updateTs: Long,
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            title?.let { Text(text = it, fontSize = 18.sp, fontWeight = FontWeight.Bold) }
            content?.let { Text(text = it, fontSize = 16.sp) }
            tags?.let {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    it.forEach {
                        Text(text = "#$it", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
            Text(
                text = if (updateTs == createTs) "创建于 ${adaptiveTimeFormat(createTs)}"
                else "更新于 ${adaptiveTimeFormat(updateTs)}",
                fontSize = 14.sp, color = MaterialTheme.colorScheme.primary
            )
        }
    }
}