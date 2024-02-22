package com.cion318.arktracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.cion318.arktracker.MainActivity
import com.cion318.arktracker.R
import com.cion318.arktracker.data.character.CharacterViewModel
import com.cion318.arktracker.data.character.CharacterViewModelFactory
import com.cion318.arktracker.data.character.ViewPagerAdapter
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator3

class CharactersFragment : Fragment() {

    private lateinit var viewpager: ViewPager2
    private lateinit var indicator: CircleIndicator3

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        viewpager = view.findViewById(R.id.viewpager)
        indicator = view.findViewById(R.id.indicator)


        // Initialize the RecipeViewModel
        characterViewModel = ViewModelProvider(
            this,
            CharacterViewModelFactory((requireActivity() as MainActivity).characterDatabase.characterDao())
        )[CharacterViewModel::class.java]



        lifecycleScope.launch {
            val characters = characterViewModel.getAllCharacters()
            viewpager.adapter = ViewPagerAdapter(characters)
            viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            indicator.setViewPager(viewpager)
        }

        return view
    }

}