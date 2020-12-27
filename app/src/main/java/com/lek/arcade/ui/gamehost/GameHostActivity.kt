package com.lek.arcade.ui.gamehost

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.lek.arcade.core.helper.fitSystemWindow
import com.lek.arcade.game.Game

class GameHostActivity : AppCompatActivity() {
    private lateinit var game: Game
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = Game.instance(this)
        setContentView(game)
        fitSystemWindow()
    }

    override fun onDestroy() {
        super.onDestroy()
        game.onDestroy()
    }

    companion object {
        fun start(sourceContext: Context) {
            sourceContext.startActivity(Intent(sourceContext, GameHostActivity::class.java))
        }
    }
}
