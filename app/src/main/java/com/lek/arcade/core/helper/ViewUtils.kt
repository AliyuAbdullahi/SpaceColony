package com.lek.arcade.core.helper

import android.app.Activity
import android.database.MatrixCursor
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.DisplayMetrics
import android.view.WindowManager

fun Activity.screenDimension(): WidthHeightPair<Int, Int> {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return Pair(metrics.widthPixels, metrics.heightPixels)
}

object ViewDimention {
    var width: Int = 0
    var height: Int = 0
}

fun Activity.fitSystemWindow() {
    getWindow().setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

enum class ScreenSize(val width: Int, val height: Int) {
    Unknown(0, 0),
    Small(400, 700),
    Medium(800, 1400),
    Large(1500, 2800),
    VeryLarge(2900, 5600);
}

fun scaleBitMap(bitmap: Bitmap, maxWidth: Float, maxHeight: Float): Bitmap {
    val theScale = (maxHeight / bitmap.width).coerceAtMost(maxWidth / bitmap.height)
    val matrix = Matrix().apply { postScale(theScale, theScale) }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

typealias WidthHeightPair<A, B> = Pair<A, B>

