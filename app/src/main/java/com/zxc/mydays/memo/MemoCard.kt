package com.zxc.mydays.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.util.DebugLogger
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
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

fun ellipseText(text: String, length: Int): String {
    return if (text.length > length) text.substring(0, length) + "..."
    else text
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MemoCard(
    title: String? = null,
    content: String? = null,
    contentLengthLimit: Int = 30, // TODO 可配置
    image: String? = null,
    tags: List<String>? = null,
    createTs: Long,
    updateTs: Long,
    onClick: () -> Unit = {},
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            title?.let {
                Text(text = it, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
            }
            image?.let {
                // TODO temp image from online
                val imageLoader = LocalContext.current.imageLoader.newBuilder()
                    .logger(DebugLogger())
                    .build()
                AsyncImage(
                    imageLoader = imageLoader,
                    model = "https://i.ibb.co/RP1N4Qs/temp.png",
                    contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            content?.let {
                Text(text = ellipseText(it, contentLengthLimit), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
            }
            tags?.let {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    it.forEach {
                        Text(
                            text = "#$it",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = if (updateTs == createTs) "创建于 ${adaptiveTimeFormat(createTs)}"
                else "更新于 ${adaptiveTimeFormat(updateTs)}",
                fontSize = 13.sp, color = Color.Gray
            )
        }
    }
}