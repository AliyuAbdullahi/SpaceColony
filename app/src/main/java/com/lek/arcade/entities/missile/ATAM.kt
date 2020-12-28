package com.lek.arcade.entities.missile

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.lek.arcade.core.helper.ViewDimention
import com.lek.arcade.core.helper.scaleBitMap
import com.lek.arcade.entities.Entity

/**
 * Air To Air Missile
 */
class ATAM(
    private val aamContext: Context,
    var atamX: Float,
    var atamY: Float,
    private val aamWidth: Float,
    private val aamHeight: Float
) : Entity(aamContext, atamX, atamY, aamWidth, aamHeight, false, speedX = 25F) {

    private var renderedIndex = 0

    override fun image(): Int {
        val image = MissileResource.resources[renderedIndex]
        if (renderedIndex < MissileResource.resources.size - 1) {
            renderedIndex++
        }
        return image
    }

    override fun draw(canvas: Canvas) {
        image().let { theImage ->
            val bitmap = BitmapFactory.decodeResource(aamContext.resources, theImage)
            val scaledBitmap = scaleBitMap(bitmap, width, height)
            canvas.drawBitmap(scaledBitmap, atamX, y, paint)
        }
    }

    fun update() {
        atamX = atamX + speedX
    }

    override fun background(): Int? {
        return null
    }

    fun isOutOFBound(): Boolean {
        return atamX > ViewDimention.width.toFloat()
    }
}
