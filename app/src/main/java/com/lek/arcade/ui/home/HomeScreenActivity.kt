package com.lek.arcade.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lek.arcade.core.helper.SoundManager
import com.lek.arcade.core.helper.fitSystemWindow
import com.lek.arcade.core.helper.screenDimension
import com.lek.arcade.databinding.ActivityHomeScreenBinding
import com.lek.arcade.ui.gamehost.GameHostActivity

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var soundManager: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        fitSystemWindow()
        soundManager = SoundManager.instance(this)
        setContentView(binding.root)
        obserViewEvents()
        setHomeSound()
        initClickListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateDimention(screenDimension().first, screenDimension().second)
        soundManager.resume()
    }

    private fun setHomeSound() {
        soundManager.playLongTrack(SoundManager.BackgroundSound.HOME_SOUND)
    }

    override fun onStop() {
        super.onStop()
        soundManager.pauseBackgroundSound()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundManager.stopBackgroundSound()
    }

    private fun initClickListeners() {
        binding.startGame.setOnClickListener { viewModel.goToGameHostClicked() }
    }

    private fun obserViewEvents() {
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        viewModel.event.observe(this, Observer { state ->
            when (state) {
                HomeScreenViewModel.HomeScreenEvent.StartGameClicked -> {
                    goToGameScreen()
                }
                HomeScreenViewModel.HomeScreenEvent.StartGame -> {
                    GameHostActivity.start(this)
                }
            }
        })
    }

    private fun goToGameScreen() {
        soundManager.playShortSound(
            SoundManager.ShortSound.REVEAL_TWO,
            SoundManager.Loop.DONT_LOOP
        )
        this.finish()
        viewModel.goToGameHost()
    }
}
