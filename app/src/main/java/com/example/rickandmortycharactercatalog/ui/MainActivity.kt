package com.example.rickandmortycharactercatalog.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycharactercatalog.R
import com.example.rickandmortycharactercatalog.data.Character
import com.example.rickandmortycharactercatalog.data.LoadingStatus
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private val viewModel: CharacterViewModel by viewModels()

    private lateinit var charactersAdapter: CharacterAdapter

    private lateinit var characterListRV: RecyclerView
    private lateinit var loadingErrorTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingErrorTV = findViewById(R.id.tv_loading_error)
        loadingIndicator = findViewById(R.id.loading_indicator)
        characterListRV = findViewById(R.id.rv_character_list)
        searchView = findViewById(R.id.search_view);

        charactersAdapter = CharacterAdapter(::onCharacterItemClick)

        characterListRV.layoutManager = LinearLayoutManager(this)
        characterListRV.setHasFixedSize(true)
        characterListRV.adapter = charactersAdapter

        viewModel.characters.observe(this) { characters ->
            charactersAdapter.update(characters?.results)
        }

        viewModel.loadingStatus.observe(this) { loadingStatus ->
            when (loadingStatus) {
                LoadingStatus.LOADING -> {
                    loadingIndicator.visibility = View.VISIBLE
                    characterListRV.visibility = View.INVISIBLE
                    loadingErrorTV.visibility = View.INVISIBLE
                }
                LoadingStatus.ERROR -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    characterListRV.visibility = View.INVISIBLE
                    loadingErrorTV.visibility = View.VISIBLE
                }
                else -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    characterListRV.visibility = View.VISIBLE
                    loadingErrorTV.visibility = View.INVISIBLE
                }
            }
        }

        viewModel.loadCharacters()
        searchView.setOnQueryTextListener(this);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onCharacterItemClick(character: Character) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(EXTRA_CHARACTER, character)
        }
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(text: String): Boolean {
        charactersAdapter.filter(text)
        return false
    }
}

