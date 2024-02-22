package com.cion318.arktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.cion318.arktracker.data.character.CharacterDatabase
import com.cion318.arktracker.ui.AddCharacterFragment
import com.cion318.arktracker.ui.CharactersFragment
import com.cion318.arktracker.ui.HomeFragment
import com.cion318.arktracker.ui.theme.ArkTrackerTheme
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var characterDatabase: CharacterDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Room Character Database
        characterDatabase = Room.databaseBuilder(
            applicationContext,
            CharacterDatabase::class.java,
            "character_database"
        ).build()

        setupNavigation()
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }


    private fun setupNavigation() {
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> loadFragment(HomeFragment())
                R.id.navigation_characters -> loadFragment(CharactersFragment())
                R.id.navigation_add_character -> loadFragment(AddCharacterFragment())
            }
            true
        }
    }
}
