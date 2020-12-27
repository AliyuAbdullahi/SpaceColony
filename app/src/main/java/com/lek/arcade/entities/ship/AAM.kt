package com.lek.arcade.entities.ship

import android.content.Context
import com.lek.arcade.entities.Entity

/**
 * Air To Air Missile
 */
class AAM(
    aamContext: Context,
    aamX: Float,
    aamY: Float,
    aamWidth: Float,
    aamHeight: Float
) : Entity(aamContext, aamX, aamY, aamWidth, aamHeight, false, speedX = 25F) {

    override fun image(): Int? {
        return null
    }

    override fun background(): Int? {
        return null
    }
}
