package com.example.rickandmortycharactercatalog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rickandmortycharactercatalog.R
import com.example.rickandmortycharactercatalog.data.Character

const val EXTRA_CHARACTER = "com.example.rickandmortycharactercatalog.EXTRA_CHARACTER"

class CharacterDetailActivity : AppCompatActivity() {
    private var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        if (intent != null && intent.hasExtra(EXTRA_CHARACTER)) {
            character = intent.getSerializableExtra(EXTRA_CHARACTER) as Character

            Glide.with(this)
                .load(character!!.image)
                .into(findViewById(R.id.iv_image))

            findViewById<TextView>(R.id.tv_name).text =
                character!!.name

            val originTitle = getString(R.string.Origin)
            val origin = character!!.origin.name

            findViewById<TextView>(R.id.tv_originTitle).text =
                originTitle

            findViewById<TextView>(R.id.tv_origin).text =
                origin

            val locationTitle = getString(R.string.Location)
            val location = character!!.location.name

            findViewById<TextView>(R.id.tv_locationTitle).text =
                locationTitle

            findViewById<TextView>(R.id.tv_location).text =
                location

            val firstTitle = getString(R.string.First)
            val first = character!!.episode.first()

            findViewById<TextView>(R.id.tv_first).text =
                firstTitle

            findViewById<TextView>(R.id.tv_firstEp).text =
                first

            val lastTitle = getString(R.string.Last)
            val last = character!!.episode.last()

            findViewById<TextView>(R.id.tv_last).text =
                lastTitle

            findViewById<TextView>(R.id.tv_lastEp).text =
                last
        }
    }
    /*
    private fun shareCharacterText() {
        if (character != null) {


            val shareText = ()

            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, null))
        }
    }
    */
}