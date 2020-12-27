package com.lek.arcade.entities.ship

import com.lek.arcade.R


sealed class ExhaustResource {
    data class PowerOneResource(
        val resources: List<Int> = listOf(
            R.drawable.ship_1_01,
            R.drawable.ship_1_02,
            R.drawable.ship_1_03,
            R.drawable.ship_1_04
        )
    ) : ExhaustResource()
}
