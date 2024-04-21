package com.example.notificationmanagement

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notificationmanagement.datamodel.Counter
import com.example.notificationmanagement.notification.CounterNotifiationService
import com.example.notificationmanagement.ui.theme.NotificationManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(applicationContext)
                }
            }
        }
    }
}

@Composable
fun HomeScreen( context: Context){
    val service= CounterNotifiationService(context = context)
    var value by remember {
        mutableStateOf("0")
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                Modifier
                    .background(Color.Cyan)
                    .height(70.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Notification Application",
                    fontSize = 32.sp,
                    fontWeight = FontWeight(800)
                )
            }
        }) {paddingValues ->
        Column(modifier= Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            OutlinedTextField(value = value, onValueChange = {
                value=it
            },
                placeholder = {
                    Text(text = "Enter your starting count value: ")
                },

            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(onClick = {
                Counter.value=value.toInt()
                service.showNotification(Counter.value)
            }) {
                Text(text = "Save Number")
            }
            
        }
    }
}