package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        color = Color(color = 0xFF233b7a), modifier = Modifier.fillMaxSize()
                    ) {
                        BusinessCard(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NameInformation(name: String, jobTitle: String, modifier: Modifier = Modifier) {
    val userIcon = painterResource(R.drawable.octcat_main_with_goring)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
    ) {
        Image(
            painter = userIcon,
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .padding(bottom = 5.dp)
        )
        Text(
            text = name,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            modifier = Modifier.padding(start = 25.dp, end = 25.dp)
        )
        Text(
            text = jobTitle,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 25.dp, end = 25.dp)
        )
    }
}

@Composable
fun ContactInformation(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = stringResource(R.string.content_description_phone_number),
                tint = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 36.dp, end = 8.dp)
            )
            Text(
                text = stringResource(R.string.phone_number),
                color = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
        }
        Row {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.content_description_social_media_account),
                tint = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 36.dp, end = 8.dp)
            )
            Text(
                text = stringResource(R.string.account_name),
                color = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
        }
        Row {
            Icon(
                imageVector = Icons.Filled.Place,
                contentDescription = stringResource(R.string.content_description_place_at),
                tint = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 36.dp, end = 8.dp)
            )
            Text(
                text = "Japan",
                color = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
        }
        Row {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = stringResource(R.string.content_description_email_address),
                tint = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 36.dp, end = 8.dp)
            )
            Text(
                text = stringResource(R.string.mail_address),
                color = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Spacer(
            modifier = Modifier
                .size(240.dp)
                .width(32.dp)
        )
        NameInformation(
            name = stringResource(R.string.human_name),
            jobTitle = stringResource(R.string.job_title),
            modifier = Modifier
        )
        Spacer(
            modifier = Modifier
                .size(240.dp)
                .width(32.dp)
        )
        ContactInformation(
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NameInformationPreview() {
    BusinessCardTheme {
        NameInformation(
            name = stringResource(R.string.human_name),
            jobTitle = stringResource(R.string.job_title)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactInformationPreview() {
    BusinessCardTheme {
        ContactInformation()
    }
}

@Preview(showBackground = true)
@Composable
private fun BusinessCardPreview() {
    BusinessCardTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Surface(
                color = Color(color = 0xFF233b7a), modifier = Modifier.fillMaxSize()
            ) {
                BusinessCard(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}