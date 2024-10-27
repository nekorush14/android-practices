package com.example.dailygenaitips

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dailygenaitips.model.TipRepository
import com.example.dailygenaitips.ui.theme.DailyGenAITipsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val configuration = LocalConfiguration.current
            val isDarkMode =
                configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
            var currentIsDarkMode by remember { mutableStateOf(isDarkMode) }

            DailyGenAITipsTheme(
                darkTheme = currentIsDarkMode
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    DailyGenAITipsApp(
                        onThemeChangeButtonClicked = {
                            currentIsDarkMode = !currentIsDarkMode
                        },
                    )
                }
            }
        }
    }
}

/**
 * App that displays a list of tips for Daily Gen AI.
 */
@Composable
fun DailyGenAITipsApp(
    onThemeChangeButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            AppBar(
                onThemeChangeButtonClicked = onThemeChangeButtonClicked,
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        val tips = TipRepository.tips
        TipsList(
            tips = tips,
            contentPadding = innerPadding,
        )
    }
}

/**
 * App bar to display title and navigate up
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onThemeChangeButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(
                onClick = onThemeChangeButtonClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.BrightnessMedium,
                    contentDescription = stringResource(R.string.theme_change_icon),
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DailyGenAITipsAppPreview() {
    DailyGenAITipsTheme {
        DailyGenAITipsApp()
    }
}