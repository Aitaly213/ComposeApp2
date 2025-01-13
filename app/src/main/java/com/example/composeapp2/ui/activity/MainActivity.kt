package com.example.composeapp2.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp2.ui.screens.detail.DetailScreen
import com.example.composeapp2.ui.screens.main.MainScreen
import com.example.composeapp2.ui.screens.Screens
import com.example.composeapp2.ui.theme.ComposeApp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApp2Theme {
               CustomNavHost()
            }
        }
    }
}


@Composable
fun CustomNavHost() {
    var currentScreenState by remember { mutableStateOf<Screens>(Screens.Main) }

    when (currentScreenState) {
        is Screens.Main -> {
            MainScreen(navigate = {
                currentScreenState = Screens.Detail(it)
            })
        }

        is Screens.Detail -> {
            val bookModel = (currentScreenState as Screens.Detail).bookModel
            DetailScreen(bookModel, onBack = {
                currentScreenState = Screens.Main
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeApp2Theme {
        CustomNavHost()
    }
}