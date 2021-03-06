package com.lek.arcade.entities.enemy

import android.content.Context
import com.lek.arcade.R
import com.lek.arcade.core.helper.ViewDimention
import java.util.*
import kotlin.math.abs
import kotlin.random.Random


object EnemiesGenerator {

    fun generateEnemies(context: Context, numberOfEnemies: Int): List<Enemy> {
        val shipHeight = context.resources.getDimensionPixelSize(R.dimen.player_height)
        val yMax  = ViewDimention.height - (shipHeight / 2)
        val startX = ViewDimention.width + Random.nextInt(100, 1000)

        val xRandoms = MutableList(numberOfEnemies) { Random.nextInt(startX, startX + 100) }
        val yRandoms = MutableList(numberOfEnemies) { Random.nextInt(0, yMax) }

        // Minimize overlap
        xRandoms.forEachIndexed { index, item ->
            if (index < (xRandoms.size - 1)) {
                if (abs(xRandoms[index] - xRandoms[index + 1]) < 1000) {
                    xRandoms[index + 1] = xRandoms[index + 1] + (Random.nextInt(1000, 2000))
                }
            }
        }

        yRandoms.forEachIndexed { index, _ ->
            if (index < (yRandoms.size - 1)) {
                val absValue = abs(yRandoms[index] - yRandoms[index + 1])
                if ( absValue < 80 && absValue < yMax) {
                    yRandoms[index + 1] = yRandoms[index + 1] + 80
                }
            }
        }

        val enemiesList = arrayListOf<Enemy>()
        val enemyWidth = context.resources.getDimensionPixelSize(R.dimen.enemy_width).toFloat()
        val enemyHeight = context.resources.getDimensionPixelSize(R.dimen.enemy_height).toFloat()

        for (i in 0 until numberOfEnemies) {
            enemiesList.add(
                XRoger(
                    context,
                    xRandoms[i].toFloat(),
                    yRandoms[i].toFloat(),
                    enemyWidth,
                    enemyHeight
                )
            )
        }

        return enemiesList
    }
}
