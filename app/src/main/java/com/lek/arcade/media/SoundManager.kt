package com.lek.arcade.media

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Looper
import android.util.SparseArray
import androidx.annotation.RawRes
import com.lek.arcade.R


class SoundManager private constructor(private val context: Context) {

    private var mediaPlayerReleased = true

    private var audioAttributes = AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .setUsage(AudioAttributes.USAGE_GAME)
        .build()

    private var soundPool: SoundPool? = SoundPool.Builder().setMaxStreams(MAX_SOUND).setAudioAttributes(
        audioAttributes
    ).build()

    private var audioManager: AudioManager? = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private var soundPoolMap: SparseArray<Int>? = SparseArray()

    private var isSoundTurnedOff = false

    fun playLongTrack(backgroundSound: BackgroundSound) {
        mediaPlayer = MediaPlayer.create(context, backgroundSound.resId)
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
        mediaPlayerReleased = false
    }

    fun playLongTrackAsync(backgroundSound: BackgroundSound) {
        if (!SoundBox.isPlaying) {
            SoundBox.isPlaying = true
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                val afd = context.resources.openRawResourceFd(backgroundSound.resId)
                mediaPlayer = MediaPlayer()
                mediaPlayer?.apply {
                    setAudioAttributes(audioAttributes)
                    setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    isLooping = true
                    prepareAsync()
                }
                mediaPlayer?.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                    override fun onPrepared(mediaPlayer: MediaPlayer?) {
                        mediaPlayer?.start()
                        mediaPlayerReleased = false
                    }
                })

                afd.close()
            }, 900)
        }
    }

    fun playShortSound(sound: ShortSound, loop: Loop) {
        if (isSoundTurnedOff) return
        val soundId = soundPool?.load(context, sound.resId, 1)!!
        soundPool?.setOnLoadCompleteListener(object : SoundPool.OnLoadCompleteListener {
            override fun onLoadComplete(soundPool: SoundPool?, sampleId: Int, status: Int) {
                val streamVolume: Float? =
                    audioManager?.getStreamVolume(AudioManager.STREAM_MUSIC)?.toFloat()
                soundPool?.play(soundId, 0.3F, 0.3F, 0, loop.value, 1F)
            }
        })
    }

    fun unload(index: Int) {
        soundPool?.unload(index)
    }

    fun unloadAll(vararg indexes: Int) {
        for (index in indexes) {
            soundPool?.unload(index)
        }
    }

    fun stopBackgroundSound() {
        if (mediaPlayerReleased.not()) {
            val theMediaPlayer = mediaPlayer
            if (theMediaPlayer != null && theMediaPlayer.isPlaying) {
                theMediaPlayer.stop()
                theMediaPlayer.release()
                mediaPlayerReleased = true
            }
        }
    }

    fun pauseBackgroundSound() {
        if (!mediaPlayerReleased) {
            val theMediaPlayer = mediaPlayer
            if (theMediaPlayer != null && theMediaPlayer.isPlaying) {
                theMediaPlayer.pause()
            }
        }
    }

    fun stopSound(index: Int) {
        soundPool?.stop(index)
    }

    fun resume() {
        if (mediaPlayer != null && mediaPlayerReleased.not()) {
            mediaPlayer?.start()
        }
    }

    fun turnOff() {
        isSoundTurnedOff = true
    }

    fun tournOn() {
        isSoundTurnedOff = false
    }

    companion object {
        const val MAX_SOUND = 12

        var mediaPlayer: MediaPlayer? = MediaPlayer()

        var MENU_1 = 0
        var MENU_2 = 1
        var MENU_LOOPING = 2
        var PAUSE_SCREEN = 3
        var LEVEL_UP = 4
        var IN_GAME_1 = 5
        var IN_GAME_2 = 6
        var IN_GAME_3 = 7
        var IN_GAME_4 = 8
        var FOOD_EATEN = 9
        var DAMAGE = 10
        var STAGE_ONE = 11
        var REVEAL_ONE = 12
        var REVEAL_TWO = 13
        var CLICK = 14

        @Volatile
        private var soundManager: SoundManager? = null

        fun clear() {
            if (soundManager != null) {
                soundManager?.soundPool?.release()
                soundManager?.soundPool = null
                soundManager?.audioManager = null
                soundManager?.soundPoolMap = null
            }
            val theMediaPlayer = mediaPlayer
            if (theMediaPlayer != null) {
                if (theMediaPlayer.isPlaying) {
                    theMediaPlayer.stop()
                }
                mediaPlayer = null
            }
            soundManager = null
        }


        fun instance(context: Context): SoundManager {
            val theSoundManager = soundManager
            if (theSoundManager != null) {
                return theSoundManager
            } else {
                synchronized(SoundManager::class) {
                    if (soundManager == null) {
                        soundManager = SoundManager(context)
                    }
                }
            }

            return soundManager!!
        }
    }

    enum class Loop(val value: Int) {
        LOOP(-1), DONT_LOOP(0)
    }

    enum class BackgroundSound(@RawRes val resId: Int) {
        MENU_SCREEN_ONE(R.raw.level4),
        WELCOME_SCREEN(R.raw.welcome_screen),
        WELCOME_SCREEN_2(R.raw.game_over),
        MENU_SCREEN(R.raw.menu_screen_2),
        HOME_SOUND(R.raw.home),
        GAME_OVER(R.raw.game_over),
        LEVEL1(R.raw.level1),
        LEVEL3(R.raw.level2),
        LEVEL4(R.raw.level3),
        LEVEL5(R.raw.level4)
    }

    enum class ShortSound(@RawRes val resId: Int) {
        CLICK(R.raw.click),
        REVEAL_ONE(R.raw.reveal_one),
        REVEAL_TWO(R.raw.reveal_2),
        DAMAGE(R.raw.damage),
        FIRE_ROCKET(R.raw.defaultgun)
    }

}
