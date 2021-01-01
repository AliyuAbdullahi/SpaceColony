package com.lek.arcade.entities.enemy

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF
import com.lek.arcade.R
import com.lek.arcade.core.helper.ViewDimention
import com.lek.arcade.core.helper.scaleBitMap
import com.lek.arcade.entities.missile.ATAM
import com.lek.arcade.entities.missile.MissileTracker
import kotlin.random.Random

class XRoger(
    private val xRogerContext: Context,
    private var xRogerX: Float,
    private var xRogerY: Float,
    private val xRogerWidth: Float,
    private val xRogerHeight: Float,
    private val speed: Float = Random.nextInt(7, 10).toFloat()
) : Enemy(xRogerContext, xRogerX, xRogerY, xRogerWidth, xRogerHeight) {

    private fun intersectWith(atam: ATAM): Boolean {
        return RectF(xRogerX, xRogerY, xRogerX + xRogerWidth, xRogerY + xRogerHeight).intersect(
            RectF(
                atam.atamX,
                atam.atamY,
                atam.atamX + atam.aamWidth,
                atam.y + atam.aamHeight
            )
        )
    }

    private var isHurt = false

    override fun image(): Int {
        return if (isHurt) R.drawable.explosion1_5 else R.drawable.enemy_1
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
        checkCollisionWithBullet()
        if (xRogerX <= xRogerWidth) {
            xRogerX = ViewDimention.width + xRogerWidth
        }
        xRogerX -= speed
    }

    private fun checkCollisionWithBullet() {
        MissileTracker.all { atam ->
            if (this.intersectWith(atam)) {
                isHurt = true
            }
        }
    }

    override fun hasExploded(): Boolean = isHurt

    override fun background(): Int? {
        return null
    }
}
