package com.example.domsapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var animalImageView: ImageView
    private lateinit var spinButton: Button
    private lateinit var repeatButton: Button
    private var mediaPlayer: MediaPlayer? = null
    private val random = Random()

    private val animals = listOf(
        Animal("cow", R.drawable.cow, R.raw.cow_sound),
        Animal("pig", R.drawable.pig, R.raw.pig_sound),
        // Add remaining animals here
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animalImageView = findViewById(R.id.animalImageView)
        spinButton = findViewById(R.id.spinButton)
        repeatButton = findViewById(R.id.repeatButton)

        spinButton.setOnClickListener {
            spin()
        }

        repeatButton.setOnClickListener {
            playSound()
        }
        // Initialize with a random animal.
        spin()

        // Stop the audio when the activity is destroyed.
        mediaPlayer?.setOnCompletionListener { mediaPlayer!!.release() }
    }

    private fun spin() {
        // Generate a random index for the animals list.
        val randomIndex = random.nextInt(animals.size)

        // Get the animal at the random index.
        val selectedAnimal = animals[randomIndex]

        // Update the ImageView with the selected animal's image.
        animalImageView.setImageResource(selectedAnimal.imageResId)

        // Release the previous MediaPlayer.
        mediaPlayer?.release()

        // Create a new MediaPlayer with the selected animal's sound.
        mediaPlayer = MediaPlayer.create(this, selectedAnimal.soundResId)
        playSound()
    }

    private fun playSound() {
        // Start playing the sound.
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }

    private data class Animal(
        val name: String,
        val imageResId: Int,
        val soundResId: Int
    )
}



