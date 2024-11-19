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
fun TodoScreen(){
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 8.dp)) {
        TodoGroupPanel("进行中",5){
            Text("text")
        }
        Spacer(modifier=Modifier.height(8.dp))
        TodoGroupPanel("已完成",4){
            Text("text")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TodoGroupPanel("已废弃",11){
            Text("text")
        }
    }
}