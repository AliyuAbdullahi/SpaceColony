package com.lek.arcade.entities.missile

import android.content.Context
import com.lek.arcade.R

object MissileLauncher {

    fun launch(context: Context, x: Float, y: Float, shipWidth: Float): ATAM {
        val bulletWidth = context.resources.getDimensionPixelSize(R.dimen.button_width).toFloat()
        val bulletHeight = context.resources.getDimensionPixelSize(R.dimen.bullet_height).toFloat()
        val bulletX = ((shipWidth / 2) + x) - (bulletWidth / 2)
        val aam = ATAM(context, bulletX, y, bulletWidth, bulletHeight)
        MissileTracker.add(aam)
        return aam
    }
}
