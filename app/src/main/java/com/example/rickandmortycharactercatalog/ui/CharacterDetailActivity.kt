package com.example.rickandmortycharactercatalog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmortycharactercatalog.R

const val EXTRA_CHARACTER = "com.example.rickandmortycharactercatalog.EXTRA_CHARACTER"

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val character = intent.getSerializableExtra(EXTRA_CHARACTER)
        /*To Do implement activity from character data*/
    }
}