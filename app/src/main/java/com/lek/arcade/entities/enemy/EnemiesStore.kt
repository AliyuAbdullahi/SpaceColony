package com.lek.arcade.entities.enemy

import android.content.Context

object EnemiesStore {

    private val enemies = arrayListOf<Enemy>()

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

    fun get() = enemies

    fun clearHurtEnemy() {
        val iterator = enemies.iterator()
        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current.hasExploded()) {
                iterator.remove()
            }
        }
    }

    fun allOf(action: (Enemy) -> Unit) {
        for (enemy in enemies) {
            action(enemy)
        }
    }
}
