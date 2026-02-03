package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Ключ для сохранения состояния изображения
    private val KEY_INDEX = "current_artwork_index"

    private val gallery = listOf(
        Artwork(
            R.drawable.img1,
            R.string.artwork_1_title,
            R.string.artwork_1_author,
            descriptionResId = R.string.artwork_1_description),

        Artwork(
            R.drawable.img2,
            R.string.artwork_2_title,
            R.string.artwork_2_author,
            R.string.artwork_2_description),

        Artwork(
            R.drawable.img3,
            R.string.artwork_3_title,
            R.string.artwork_3_author,
            R.string.artwork_3_description),

        Artwork(
            R.drawable.img4,
            R.string.artwork_4_title,
            R.string.artwork_4_author,
            R.string.artwork_4_description),

        Artwork(
            R.drawable.img5,
            R.string.artwork_5_title,
            R.string.artwork_5_author,
            R.string.artwork_5_description),
    )

    private var currentIndex = 0

    private lateinit var imageView: ImageView
    private lateinit var titleText: TextView
    private lateinit var authorText: TextView
    private lateinit var btnPrev: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
        }

        imageView = findViewById(R.id.artwork_image)
        titleText = findViewById(R.id.artwork_title)
        authorText = findViewById(R.id.artwork_author)
        btnPrev = findViewById(R.id.btn_previous)
        btnNext = findViewById(R.id.btn_next)

        updateUI()

        btnNext.setOnClickListener {
            if (currentIndex < gallery.size - 1) {
                currentIndex++
                updateUI()
            }
        }

        btnPrev.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }
    }

    private fun updateUI() {
        val currentArtwork = gallery[currentIndex]

        imageView.setImageResource(currentArtwork.imageResourceId)
        titleText.setText(currentArtwork.titleResId)
        authorText.setText(currentArtwork.authorResId)

        //автоматически проставляем описание картинки более подробным описанием из файлов ресурсов
        imageView.contentDescription = getString(currentArtwork.descriptionResId)

        btnPrev.isEnabled = currentIndex > 0
        btnNext.isEnabled = currentIndex < gallery.size - 1
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, currentIndex)
    }
}