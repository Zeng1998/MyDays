package com.zxc.mydays.memo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.zxc.mydays.R
import com.zxc.mydays.common.MyTopAppBar

@Composable
fun MemoDetailScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        // main contents
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            MyTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.menu),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = null
                        )
                    }
                }
            )
            Text("detail")
        }
    }
}