package com.example.composeapp2.ui.screens.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp2.data.model.BookModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(bookModel: BookModel, onBack: () ->Unit) {
    BackHandler {
        onBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Detail") })
        
        Text(text = bookModel.name,
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontSize = 24.sp)

        Text(text = bookModel.author,
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontSize = 24.sp)

        Text(text = bookModel.description,
            modifier = Modifier
                .padding(top = 24.dp, start = 12.dp),
            fontSize = 14.sp)
    }
}

