package com.example.photospace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photospace.ui.theme.PhotoSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PhotoSpaceLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoSpaceLayout(modifier: Modifier = Modifier) {
    var imageResourceId by rememberSaveable { mutableIntStateOf(0) }
    var imageTitleId by rememberSaveable { mutableIntStateOf(0) }
    val isVertical = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    // List of image resources
    val imageSources = listOf(
        R.drawable.pxl_20240107_061351121,
        R.drawable.pxl_20240107_061441561,
        R.drawable.pxl_20240107_062228977_night,
    )

    // List of image titles
    val imageNames = listOf(
        R.string.photo_1_title,
        R.string.photo_2_title,
        R.string.photo_3_title,
    )

    // Create the layout based on the orientation
    if (isVertical) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ImageBoard(
                image = imageSources[imageResourceId],
            )
            Spacer(modifier = Modifier.size(40.dp))
            ImageDescription(
                title = imageNames[imageTitleId],
                description = R.string.photo_description,
                photographerName = R.string.photographer_name,
                date = R.string.date
            )
            Spacer(modifier = Modifier.size(64.dp))
            ActionButtons(
                onPreviousClick = {
                    val previousIndex = onPreviousClick(imageResourceId, imageSources.size)
                    imageResourceId = previousIndex
                    imageTitleId = previousIndex
                },
                onNextClick = {
                    val nextIndex = onNextClick(imageResourceId, imageSources.size)
                    imageResourceId = nextIndex
                    imageTitleId = nextIndex
                }
            )
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 80.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageBoard(
                image = imageSources[imageResourceId],
            )
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ImageDescription(
                    title = imageNames[imageTitleId],
                    description = R.string.photo_description,
                    photographerName = R.string.photographer_name,
                    date = R.string.date
                )
                Spacer(modifier = Modifier.size(64.dp))
                ActionButtons(
                    onPreviousClick = {
                        val previousIndex = onPreviousClick(imageResourceId, imageSources.size)
                        imageResourceId = previousIndex
                        imageTitleId = previousIndex
                    },
                    onNextClick = {
                        val nextIndex = onNextClick(imageResourceId, imageSources.size)
                        imageResourceId = nextIndex
                        imageTitleId = nextIndex
                    }
                )
            }
        }
    }
}

/**
 * On previous button click
 *
 * This lambda returns the index of the previous image index.
 * When index 0, it returns the last image index.
 */
val onPreviousClick: (Int, Int) -> Int = { currentIndex, dataLength ->
    when (currentIndex) {
        0 -> dataLength - 1
        else -> currentIndex - 1
    }
}

/**
 * On next button click
 *
 * This lambda returns the index of the next image index.
 * When index is the last image list index, it returns the first image list index.
 */
val onNextClick: (Int, Int) -> Int = { currentIndex, dataLength ->
    when (currentIndex) {
        dataLength - 1 -> 0
        else -> currentIndex + 1
    }
}

/**
 * Create image board
 *
 * @param image the image to display
 */
@Composable
fun ImageBoard(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .size(320.dp, 400.dp)
            .shadow(elevation = 10.dp),
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "My Image",
            modifier = modifier
                .size(320.dp, 400.dp)
                .padding(all = 16.dp)
        )
    }
}

/**
 * Create image description
 *
 * This composable contains the title of the image
 * and the description of it.
 *
 * @param title the title of the image
 * @param description the description of the image
 * @param photographerName the name of the photographer
 * @param date the date when the image was taken
 * @param modifier the modifier to apply to this composable
 */
@Composable
fun ImageDescription(
    @StringRes title: Int,
    @StringRes description: Int,
    @StringRes photographerName: Int,
    @StringRes date: Int,
    modifier: Modifier = Modifier
) {
    val annotatedString = annotatedString(description, photographerName, date)

    Surface(
        color = Color.LightGray,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
            Text(
                text = annotatedString,
                style = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    lineBreak = LineBreak.Paragraph
                ),
                textAlign = TextAlign.Start,
                modifier = modifier
                    .padding(
                        start = 1.dp,
                        end = 1.dp,
                        top = 8.dp,
                    )
                    .width(200.dp)
            )
        }
    }
}

/**
 * Create annotated string
 *
 * @param description the description of the image
 * @param photographerName the name of the photographer
 * @param date the date when the image was taken
 */
@Composable
@VisibleForTesting
internal fun annotatedString(
    description: Int,
    photographerName: Int,
    date: Int
): AnnotatedString {
    val descriptionText = stringResource(id = description)
    val namePlaceholder = stringResource(id = photographerName)
    val datePlaceholder = stringResource(id = date)

    // Build the annotated string with the placeholders
    val annotatedString = buildAnnotatedString {
        append(descriptionText)
        append(stringResource(R.string.single_space))

        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold),
        ) {
            append(namePlaceholder)
            append(stringResource(R.string.single_space))
        }
        append(datePlaceholder)
    }
    return annotatedString
}

/**
 * Create action buttons
 *
 * This composable contains two buttons:
 * Previews and Next.
 *
 * @param onPreviousClick will be called when the user clicks the "Previous" button
 * @param onNextClick will be called when the user clicks the "Next" button
 */
@Composable
fun ActionButtons(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        Button(
            onClick = onPreviousClick,
            modifier = modifier.padding(start = 4.dp),
        ) {
            Text(text = stringResource(R.string.btn_previous))
        }
        Button(
            onClick = onNextClick,
            modifier = modifier.padding(start = 16.dp, end = 4.dp),
        ) {
            Text(text = stringResource(R.string.btn_next))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionButtonsPreview() {
    PhotoSpaceTheme {
        ActionButtons({}, {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageDescriptionPreview() {
    PhotoSpaceTheme {
        ImageDescription(
            title = R.string.photo_1_title,
            description = R.string.photo_description,
            photographerName = R.string.photographer_name,
            date = R.string.date
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageBoardPreview() {
    PhotoSpaceTheme {
        ImageBoard(
            image = R.drawable.pxl_20240107_061351121
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoSpaceLayoutPreview() {
    PhotoSpaceTheme {
        PhotoSpaceLayout()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun HorizontalPhotoSpacePreview() {
    PhotoSpaceTheme {
        PhotoSpaceLayout()
    }
}

