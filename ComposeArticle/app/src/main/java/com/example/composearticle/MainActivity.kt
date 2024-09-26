package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArticleContents(
                        title = stringResource(R.string.article_title),
                        summary = stringResource(R.string.article_summary),
                        details = stringResource(R.string.article_detail),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    val headerImage = painterResource(R.drawable.bg_compose_background)

    Image(
        painter = headerImage,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ArticleContents(
    title: String,
    summary: String,
    details: String,
    modifier: Modifier = Modifier
) {
    val normalPaddingSize = 16.dp

    Column(modifier = modifier) {
        HeaderImage()
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = modifier.padding(
                start = normalPaddingSize,
                end = normalPaddingSize,
                bottom = normalPaddingSize,
                top =  normalPaddingSize
            )
        )
        Text(
            text = summary,
            modifier = modifier.padding(start = normalPaddingSize, end = normalPaddingSize),
            textAlign = TextAlign.Justify
        )
        Text(
            text = details,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(
                start = normalPaddingSize,
                end = normalPaddingSize,
                bottom = normalPaddingSize,
                top =  normalPaddingSize
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeArticleTheme {
        HeaderImage()
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleContentPreview() {
    ComposeArticleTheme {
        ArticleContents(
            title = "sample title", details = "detaile sample", summary = "summary sample",
        )
    }
}

@Preview
@Composable
private fun ArticlePreview() {
    ComposeArticleTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ArticleContents(
                title = stringResource(R.string.article_title),
                summary = stringResource(R.string.article_summary),
                details = stringResource(R.string.article_detail),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}