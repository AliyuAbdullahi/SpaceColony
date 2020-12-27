package com.lek.arcade.entities.ship

import android.content.Context
import com.lek.arcade.R
import com.lek.arcade.entities.Entity

class PlayerBody(
    bodyContext: Context,
    bodyX: Float,
    bodyY: Float,
    bodyWidth: Float,
    val bodyHeight: Float
) : Entity(bodyContext, bodyX, bodyY, bodyWidth, bodyHeight, false) {

    override fun image(): Int = ShipBodyResource.resource

    override fun background(): Int? {
        return null
    }

    companion object {
        fun bodyWidth(context: Context) = context.resources.getDimensionPixelSize(R.dimen.player_body_width).toFloat()
        fun bodyHeight(context: Context) = context.resources.getDimensionPixelSize(R.dimen.player_body_height).toFloat()
    }
}
