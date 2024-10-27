package com.example.dailygenaitips.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * A data class representing a tip.
 */
data class Tip(
    @StringRes val dayRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int,
)
