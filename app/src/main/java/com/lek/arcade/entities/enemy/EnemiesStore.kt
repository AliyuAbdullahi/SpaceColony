package com.lek.arcade.entities.enemy

import android.content.Context

class EnemiesStore {

    val enemies = arrayListOf<Enemy>()

    fun initEnemies(context: Context, size: Int) {
        enemies.addAll(EnemiesGenerator.generateEnemies(context, size))
    }

    fun removeEnemy(enemy: Enemy) {
        val iterator = enemies.iterator()
        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current == enemy) {
                iterator.remove()
            }
        }
    }
}
