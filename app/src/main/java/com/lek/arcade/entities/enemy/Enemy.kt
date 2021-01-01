package com.lek.arcade.entities.enemy

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import com.lek.arcade.entities.Entity
import com.lek.arcade.entities.missile.ATAM

abstract class Enemy(
    enemyContext: Context,
    enemyX: Float,
    enemyY: Float,
    enemyWidth: Float,
    enemyHeight: Float
) : Entity(enemyContext, enemyX, enemyY, enemyWidth, enemyHeight) {

    abstract fun update()

    override fun equals(other: Any?): Boolean {
        return this.id == (other as Enemy).id
    }

    abstract fun hasExploded(): Boolean
}
