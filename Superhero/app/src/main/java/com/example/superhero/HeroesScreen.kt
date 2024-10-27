package com.example.superhero

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superhero.model.Hero
import com.example.superhero.model.HeroesRepository.heroes
import com.example.superhero.ui.theme.SuperheroTheme

/**
 * Composable that displays a list of character cards in a [LazyColumn].
 *
 * @param heroes List of [Hero] objects to display.
 * @param modifier Modifier to apply to this composable.
 */
@Composable
fun HeroesList(
    heroes: List<Hero>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    LazyColumn (
        contentPadding = contentPadding,
    ) {
        items(heroes) { hero ->
            HeroCard(
                hero = hero
            )
        }
    }
}

/**
 * Composable that displays a single character card.
 *
 * @param hero The character object to display.
 * @param modifier Modifier to apply to this composable.
 */
@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.card_elevation)
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Left
                )
            }
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.padding_medium))
            )
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    modifier = Modifier.clip(shape = MaterialTheme.shapes.small)
                )
            }
        }
    }
}

@Preview
@Composable
private fun HeroCardPreview() {
    SuperheroTheme {
        HeroCard(
            hero = Hero(
                R.string.hero1,
                R.string.description1,
                R.drawable.android_superhero1
            )
        )
    }
}

@Preview
@Composable
private fun HeroesListPreview() {
    SuperheroTheme {
        HeroesList(
            heroes = heroes,
        )
    }
}