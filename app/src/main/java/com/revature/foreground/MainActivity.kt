package com.revature.foreground

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.foreground.ui.theme.ForegroundTheme
import com.revature.foreground.service.foregroundStartService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForegroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    mainScreen()

                }
            }
        }
    }
}

@Composable
fun mainScreen(){
    val context = LocalContext.current

    Scaffold (
        topBar = {
            topAppBar()
        }
            ){
        Column(
            verticalArrangement =Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = { context.foregroundStartService("Start") }) {
                Text(text = "Trigger")
            }

            Spacer(modifier = Modifier.height(50.dp))

            Button(onClick = { context.foregroundStartService("Exit") }) {
                Text(text = "Exit")
            }
        }
    }
}



@Composable
fun topAppBar(){
    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        
        Text(text = "Foreground Service", color = Color.White)
        
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ForegroundTheme {

        mainScreen()

    }
}