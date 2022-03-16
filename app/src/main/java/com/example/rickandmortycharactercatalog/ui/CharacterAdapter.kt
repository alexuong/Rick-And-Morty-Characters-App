package com.example.rickandmortycharactercatalog.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortycharactercatalog.R
import com.example.rickandmortycharactercatalog.data.Character

class CharacterAdapter(private val onClick: (Character) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    var allCharacters: List<Character> = listOf()
    var filteredCharacters: List<Character> = listOf()

    fun update(characters: List<Character>?) {
        allCharacters = characters.orEmpty()
        filteredCharacters = allCharacters
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.filteredCharacters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.filteredCharacters[position])
    }

    fun filter(text: String) {
        filteredCharacters = allCharacters.filter {
            it.name.startsWith(text, ignoreCase = true) ||
                    it.name.contains(" $text", ignoreCase = true) ||
                    it.status.startsWith(text, ignoreCase = true) ||
                    it.gender.startsWith(text, ignoreCase = true) ||
                    it.species.startsWith(text, ignoreCase = true)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, val onClick: (Character) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.tv_name)
        private val statusTV: TextView = itemView.findViewById(R.id.tv_status)
        private val genderTV: TextView = itemView.findViewById(R.id.tv_gender)
        private val speciesTV: TextView = itemView.findViewById(R.id.tv_species)
        private val imageIV: ImageView = itemView.findViewById(R.id.iv_image)

        private var currentCharacter: Character? = null

        init {
            itemView.setOnClickListener {
                currentCharacter?.let(onClick)
            }
        }

        fun bind(character: Character) {
            currentCharacter = character

            val ctx = itemView.context

            nameTV.text = character.name
            statusTV.text = character.status
            genderTV.text = character.gender
            speciesTV.text = character.species

            /*
             * Load forecast icon into ImageView using Glide: https://bumptech.github.io/glide/
             */
            Glide.with(ctx)
                .load(character.image)
                .into(imageIV)
        }
    }
}