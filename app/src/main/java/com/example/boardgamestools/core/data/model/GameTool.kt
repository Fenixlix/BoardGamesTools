package com.example.boardgamestools.core.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GameTool(
    @StringRes val name: Int,
    @StringRes val toolDescription : Int,
    @DrawableRes val icon: Int
)
