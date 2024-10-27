package com.example.dailygenaitips

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailygenaitips.model.Tip
import com.example.dailygenaitips.model.TipRepository
import com.example.dailygenaitips.ui.theme.DailyGenAITipsTheme

/**
 * Tips list with [LazyVerticalGrid].
 *
 * @param tips [List] of [Tip] to display
 * @param contentPadding the insets applied to the content
 * @param modifier the modifier to apply
 */
@Composable
fun TipsList(
    tips: List<Tip>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = contentPadding,
    ) {
        itemsIndexed(tips) { index, tip ->
            TipCard(
                tip = tip,
                day = index + 1,
                modifier = Modifier.padding(
                    dimensionResource(R.dimen.padding_small)
                )
            )
        }
    }
}

/**
 * A one card of the tip.
 *
 * @param tip the tip to display
 * @param day the day of the tip
 * @param modifier the modifier to apply
 */
@Composable
fun TipCard(
    tip: Tip,
    day: Int = 1,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))

        ) {
            TitleAndImage(tip = tip, day = day)
            Text(
                text = stringResource(tip.descriptionRes),
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

/**
 * [Tip] card title and related image with day number.
 */
@Composable
fun TitleAndImage(
    tip: Tip,
    day: Int = 1,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(tip.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
                .padding(
                    dimensionResource(R.dimen.padding_small)
                )
        )
        Column {
            Text(
                text = stringResource(tip.dayRes, day),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(tip.titleRes),
                style = MaterialTheme.typography.titleMedium,
            )

        }
        Spacer(Modifier.size(dimensionResource(R.dimen.padding_medium)))

    }
}

@Preview(showBackground = true)
@Composable
private fun TitleAndImagePreview() {
    DailyGenAITipsTheme {
        TitleAndImage(
            tip = Tip(
                dayRes = R.string.days_day,
                titleRes = R.string.day1_title,
                descriptionRes = R.string.day1_description,
                imageRes = R.drawable.gemini_generated_image_qdlbdnqdlbdnqdlb
            )
        )
    }
}

@Preview
@Composable
private fun TipCardPrev() {
    DailyGenAITipsTheme {
        TipCard(
            tip = Tip(
                dayRes = R.string.days_day,
                titleRes = R.string.day1_title,
                descriptionRes = R.string.day1_description,
                imageRes = R.drawable.gemini_generated_image_qdlbdnqdlbdnqdlb
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TipsListPreview() {
    DailyGenAITipsTheme {
        val tips = TipRepository.tips
        TipsList(tips)
    }
}