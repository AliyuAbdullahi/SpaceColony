package com.lek.arcade.ui.gamehost

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lek.arcade.media.SoundManager
import com.lek.arcade.core.helper.fitSystemWindow
import com.lek.arcade.game.Game
import com.lek.arcade.media.SoundBox

class GameHostActivity : AppCompatActivity() {
    private lateinit var game: Game
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val soundManager = SoundManager.instance(this)
        game = Game.instance(this, soundManager)
        setContentView(game)
        fitSystemWindow()
    }

    override fun onPause() {
        super.onPause()
        SoundBox.pause()
    }

    override fun onResume() {
        super.onResume()
        SoundBox.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        game.onDestroy()
        SoundBox.quit()
    }

    companion object {
        fun start(sourceContext: Context) {
            sourceContext.startActivity(Intent(sourceContext, GameHostActivity::class.java))
        }
    }
}
