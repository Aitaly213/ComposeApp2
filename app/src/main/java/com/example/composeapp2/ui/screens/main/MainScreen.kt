package com.example.composeapp2.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp2.data.model.BookModel
import com.example.composeapp2.ui.screens.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigate: (BookModel) -> Unit) {

    val bookListState by remember {
        mutableStateOf(
            listOf(
                BookModel("1984", "George Orwell", "description of 1984", "Dystopian"),
                BookModel(
                    "Fahrenheit 461",
                    "Ray Bradbury",
                    "description of Fahrenheit 461",
                    "Dystopian"
                ),
                BookModel(
                    "Harry Potter", "Juan Rolling", "description of Harry Potter", "Fantastic"
                ),
                BookModel(
                    "Crime and Punishment",
                    "Fedor Dostoevsky",
                    "description of Crime and Punishment",
                    "Philosophical fiction"
                )
            )
        )
    }

    val bookCategory by remember {
        mutableStateOf(
            listOf(
                "Dystopian",
                "Fantastic",
                "Philosophical fiction"
            )
        )
    }

    val searchQuery = remember { mutableStateOf("") }

    val selectedCategory = remember { mutableStateOf<String?>(null) }

    fun matchesSearch(book: BookModel, query: String): Boolean {
        return book.name.contains(query, ignoreCase = true) ||
                book.author.contains(query, ignoreCase = true)
    }

    fun matchesCategory(book: BookModel, selectedCategory: String?): Boolean {
        return selectedCategory == null || book.category == selectedCategory
    }

    val filteredBooks = bookListState.filter { book ->
        matchesSearch(book, searchQuery.value) && matchesCategory(book, selectedCategory.value)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = "Main") },
        )

        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text(text = "Search by name or by author") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            items(bookCategory.size) { index ->
                Row {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .clickable(onClick = {
                                selectedCategory.value = bookCategory[index]
                            }),
                        fontSize = 24.sp,
                        color = if (selectedCategory.value == bookCategory[index]) Color.Magenta else Color.Black,
                        text = bookCategory[index]
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            items(filteredBooks.size) { index ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .clickable(onClick = {
                            navigate(filteredBooks[index])
                        })
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp),
                        fontSize = 24.sp,
                        color = Color.Black,
                        text = filteredBooks[index].name
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = filteredBooks[index].author,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreen_Preview() {
    MainScreen(navigate = {
        Screens.Detail(it)
    })
}