package com.lek.arcade.entities.enemy

import android.content.Context
import android.graphics.Canvas
import com.lek.arcade.entities.Entity

abstract class Enemy(
    private val id: String,
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
}
