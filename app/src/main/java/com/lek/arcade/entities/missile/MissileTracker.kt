package com.lek.arcade.entities.missile

import java.util.*

object MissileTracker {
    private val availableMissiles = LinkedList<ATAM>()

    fun add(atam: ATAM) = availableMissiles.addLast(atam)

    fun clearGoneMissiles() {
        val iterator = availableMissiles.iterator()
        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current.isOutOFBound()) {
                iterator.remove()
            }
        }
    }

    fun all() = availableMissiles

    fun all(predicate: (ATAM) -> Unit) {
        for (missile in availableMissiles) {
            predicate(missile)
        }
    }
}
