package com.example.counterapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var counter = 0
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .border(10.dp, color = androidx.compose.ui.graphics.Color.Black)
                ) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Counter(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    //We see the variable counter as a state variable because this is the variable
    //that will be updated by the button click.
    var count by remember {
        mutableStateOf(0)
    }

    //Created a variable to hold the value of the number returned by the incrementCounter function.
    var counter = incrementCounter(count)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            //I passed the state count variable to the text variable.
            text = count.toString(),
            modifier = modifier.padding(5.dp)
        )
        Button(
            onClick = {
                //Whenever we click this button, we want to first call the incrementCounter function
                //passing in the value of the count state variable which increments that value
                //incrementCounter(count.intValue)
                //Then we want to update the value of the count state variable with the new value
                //count.intValue = counter
                //The reason why this was wrong is because I was trying to update the count.intValue
                // directly instead of updating the value of the count. I should update the whole state
                //object count instead.

                //Whenever we click this button, we want to update the value of the count state variable
                //with the new value returned by the incrementCounter function which is stored in the
                //counter variable
                count = counter

                      },
            modifier = Modifier.padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Increment")
        }
        Button(
            onClick = {
                count = 0
            },
            modifier = Modifier.padding(5.dp)){
                Text(text = "Reset")
            }
    }
    }


fun incrementCounter(count: Int): Int {
    var _counter = count
    //Ideally this function takes in an int as a parameter and returns the inc
    //counter value.
    //This is the proper way for this function to increment the variable before returning the value
    return ++_counter
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CounterAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Counter(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}