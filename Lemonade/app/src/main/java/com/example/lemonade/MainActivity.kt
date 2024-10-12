package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Lemonade",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LemonApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            LemonImageAndText()
        }
    }
}

@Composable
fun LemonImageAndText(modifier: Modifier = Modifier) {
    // Current state of view
    var state by remember { mutableIntStateOf(1) }
    // Tap count
    var tapCount by remember { mutableIntStateOf(0) }
    // Random max count between 2 and 4
    val maxTaps = (2..4).random()

    // lemon image selector
    val lemonImage = when (state) {
        1 -> painterResource(R.drawable.lemon_tree)
        2 -> painterResource(R.drawable.lemon_squeeze)
        3 -> painterResource(R.drawable.lemon_drink)
        else -> painterResource(R.drawable.lemon_restart)
    }

    // lemon image description selector
    val lemonImageDescription = when (state) {
        1 -> stringResource(R.string.lemon_tree)
        2 -> stringResource(R.string.lemon)
        3 -> stringResource(R.string.glass_of_lemonade)
        else -> stringResource(R.string.empty_glass)
    }

    // lemon description selector
    val lemonDescription = when (state) {
        1 -> stringResource(R.string.lemon_tree_descreption)
        2 -> stringResource(R.string.lemon_description)
        3 -> stringResource(R.string.grass_of_lemonade_description)
        else -> stringResource(R.string.empty_glass_description)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.wrapContentSize()
    ) {
        Image(
            painter = lemonImage,
            contentDescription = lemonImageDescription,
            modifier = modifier
                .width(128.dp)
                .height(160.dp)
                .clickable {
                    // When state is 2 and tap count is less than max taps, increment tap count
                    val (currentState, currentTapCount) = tapManger(state, tapCount, maxTaps)
                    // Update state and tap count
                    state = currentState
                    tapCount = currentTapCount
                }
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(15.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(15.dp)
                )
        )
        Text(
            text = lemonDescription,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp,
            ),
            modifier = Modifier
                .padding(top = 16.dp)
        )
    }
}

// Manage current state and tap loop
val tapManger: (Int, Int, Int) -> Pair<Int, Int> = { currentState, tapCount, maxTaps ->
    when (currentState) {
        2 -> {
            if (tapCount < maxTaps) {
                // When tap count is less than max taps, increment only tap count
                Pair(currentState, tapCount + 1)
            } else {
                // When tap count is equal to max taps, move to next state
                Pair(currentState + 1, 0)
            }
        }
        // When current state is 4, reset state to 1
        4 -> Pair(1, 0)
        // When current state is not 2, move to next state
        else -> Pair(currentState + 1, 0)
    }
}

@Preview(showBackground = true)
@Composable
private fun LemonImageAndTextPreview() {
    LemonadeTheme {
        LemonImageAndText()
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}