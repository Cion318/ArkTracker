package com.cion318.arktracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

}