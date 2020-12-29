package com.lek.arcade.entities.enemy

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.lek.arcade.R
import com.lek.arcade.core.helper.ViewDimention
import com.lek.arcade.core.helper.scaleBitMap
import kotlin.random.Random

class XRoger(
    private val id: String,
    private val xRogerContext: Context,
    private var xRogerX: Float,
    private var xRogerY: Float,
    private val xRogerWidth: Float,
    xRogerHeight: Float,
    private val speed: Float = Random.nextInt(7, 10).toFloat()
) : Enemy(id, xRogerContext, xRogerX, xRogerY, xRogerWidth, xRogerHeight) {

    override fun image(): Int {
        return R.drawable.enemy_1
    }

    override fun draw(canvas: Canvas) {
        update()
        image().let { theImage ->
            val bitmap = BitmapFactory.decodeResource(xRogerContext.resources, theImage)
            val scaledBitmap = scaleBitMap(bitmap, width, height)
            canvas.drawBitmap(scaledBitmap, xRogerX, xRogerY, paint)
        }
    }

    override fun update() {
        if (xRogerX <= xRogerWidth) {
            xRogerX = ViewDimention.width + xRogerWidth
        }
        xRogerX -= speed
    }

    override fun background(): Int? {
       return null
    }
}
