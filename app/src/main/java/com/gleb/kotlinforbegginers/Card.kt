package com.gleb.kotlinforbegginers

import android.content.Context
import android.graphics.drawable.Drawable
import android.service.quicksettings.Tile

data class Card(val image: Int = R.drawable.harry, val title: String = "Harry Potter") {
}