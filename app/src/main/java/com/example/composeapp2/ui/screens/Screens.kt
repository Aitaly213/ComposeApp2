package com.example.composeapp2.ui.screens

import com.example.composeapp2.data.model.BookModel

sealed class Screens {
    data object Main : Screens()
    data class Detail(val bookModel: BookModel) : Screens()
}