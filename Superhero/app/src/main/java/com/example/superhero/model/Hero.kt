package com.example.superhero.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * A data class that represents a Hero.
 *
 * @param nameRes The name of the hero.
 * @param descriptionRes The description of the hero.
 * @param imageRes The image of the hero.
 */
data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int,
)
