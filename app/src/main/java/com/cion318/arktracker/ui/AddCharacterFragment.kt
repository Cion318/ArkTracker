package com.cion318.arktracker.ui

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cion318.arktracker.MainActivity
import com.cion318.arktracker.R
import com.cion318.arktracker.data.character.Character
import com.cion318.arktracker.data.character.CharacterViewModel
import com.cion318.arktracker.data.character.CharacterViewModelFactory
import com.cion318.arktracker.databinding.FragmentAddCharacterBinding
import com.google.android.material.textfield.TextInputLayout
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.launch

class AddCharacterFragment : Fragment() {

    private lateinit var etl_characterName: TextInputLayout
    private lateinit var et_characterName: EditText
    private lateinit var etl_characterAscendancy: TextInputLayout

    //private lateinit var et_characterAscendancy: EditText
    private lateinit var etl_characterItemLevel: TextInputLayout
    private lateinit var et_characterItemLevel: EditText
    private lateinit var rg_chaosDungeon: RadioGroup
    private lateinit var rg_guardian: RadioGroup
    private lateinit var rg_unaDaily: RadioGroup
    private lateinit var rg_guildSupport: RadioGroup
    private lateinit var rg_unaWeekly: RadioGroup
    private lateinit var rg_legionRaids: RadioGroup
    private lateinit var rg_abyssDungeons: RadioGroup
    private lateinit var rg_bloodstones: RadioGroup
    private lateinit var rg_contribution: RadioGroup
    private lateinit var btn_saveCharacterData: Button


    // Character Database ViewModel
    private lateinit var characterViewModel: CharacterViewModel

    private var _binding: FragmentAddCharacterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCharacterBinding.inflate(inflater, container, false)

        initializeViews()
        initializeViewModel()
        btn_saveCharacterData.setOnClickListener {
            saveCharacterData()
        }


        return binding.root
    }


    override fun onResume() {
        super.onResume()
        val classes = resources.getStringArray(R.array.characterClasses)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_classes, classes)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initializeViews() {
        etl_characterName = binding.etlCharacterName
        et_characterName = binding.etCharacterName
        etl_characterAscendancy = binding.etlCharacterAscendancy
        etl_characterItemLevel = binding.etlCharacterItemLevel
        et_characterItemLevel = binding.etCharacterItemLevel
        rg_chaosDungeon = binding.rgChaosDungeon
        rg_guardian = binding.rgGuardianRaid
        rg_unaDaily = binding.rgUnaDaily
        rg_guildSupport = binding.rgGuildSupport
        rg_unaWeekly = binding.rgUnaWeekly
        rg_legionRaids = binding.rgLegionRaids
        rg_abyssDungeons = binding.rgAbyssDungeons
        rg_bloodstones = binding.rgBloodstones
        rg_contribution = binding.rgContribution
        btn_saveCharacterData = binding.btnSaveCharacterData
    }


    private fun initializeViewModel() {
        characterViewModel = ViewModelProvider(
            this,
            CharacterViewModelFactory((requireContext() as MainActivity).characterDatabase.characterDao())
        )[CharacterViewModel::class.java]
    }


    private fun saveCharacterData() {
        etl_characterName.error = null
        etl_characterAscendancy.error = null
        etl_characterItemLevel.error = null

        Log.d("SelectedOption", "Name Text: ${et_characterName.text}")
        Log.d("SelectedOption", "Ascendancy Text: ${binding.autoCompleteTextView.text}")
        Log.d("SelectedOption", "Item Level Text: ${et_characterItemLevel.text}")


        if (et_characterName.text.isNotEmpty() && binding.autoCompleteTextView.text.isNotEmpty() && et_characterItemLevel.text.isNotEmpty()) {

            val name = et_characterName.text.toString()
            val ascendancy = binding.autoCompleteTextView.text.toString()
            val itemLevel = et_characterItemLevel.text.toString().toInt()

            // Ascendancy Image
            var imageId: Int = R.drawable.ic_lostark
            when (ascendancy) {
                "Berserker" -> { imageId = R.drawable.ascendancy_berserker }
                "Destroyer" -> { imageId = R.drawable.ascendancy_destroyer }
                "Gunlancer" -> { imageId = R.drawable.ascendancy_gunlancer }
                "Paladin" -> { imageId = R.drawable.ascendancy_paladin }
                "Slayer" -> { imageId = R.drawable.ascendancy_slayer }

                "Arcanist" -> { imageId = R.drawable.ascendancy_arcanist }
                "Bard" -> { imageId = R.drawable.ascendancy_bard }
                "Sorceress" -> { imageId = R.drawable.ascendancy_sorceress }
                "Summoner" -> { imageId = R.drawable.ascendancy_summoner }

                "Breaker" -> { imageId = R.drawable.ic_lostark }
                "Glaivier" -> { imageId = R.drawable.ascendancy_glaivier }
                "Scrapper" -> { imageId = R.drawable.ascendancy_scrapper }
                "Soulfist" -> { imageId = R.drawable.ascendancy_soulfist }
                "Striker" -> { imageId = R.drawable.ascendancy_striker }
                "Wardancer" -> { imageId = R.drawable.ascendancy_wardancer }

                "Artillerist" -> { imageId = R.drawable.ascendancy_artillerist }
                "Deadeye" -> { imageId = R.drawable.ascendancy_deadeye }
                "Gunslinger" -> { imageId = R.drawable.ascendancy_gunslinger }
                "Machinist" -> { imageId = R.drawable.ascendancy_machinist }
                "Sharpshooter" -> { imageId = R.drawable.ascendancy_sharpshooter }

                "Deathblade" -> { imageId = R.drawable.ascendancy_deathblade }
                "Reaper" -> { imageId = R.drawable.ascendancy_reaper }
                "Shadowhunter" -> { imageId = R.drawable.ascendancy_shadowhunter }
                "Souleater" -> { imageId = R.drawable.ascendancy_souleater }

                "Aeromancer" -> { imageId = R.drawable.ascendancy_aeromancer }
                "Artist" -> { imageId = R.drawable.ascendancy_artist }
            }

            val image = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        resources.getResourcePackageName(imageId) + '/' +
                        resources.getResourceTypeName(imageId) + '/' +
                        resources.getResourceEntryName(imageId))

            // Daily
            val rbChaosDungeonSelected =
                binding.root.findViewById<RadioButton>(rg_chaosDungeon.checkedRadioButtonId).text.toString()
            val rbGuardianRaidSelected =
                binding.root.findViewById<RadioButton>(rg_guardian.checkedRadioButtonId).text.toString()
            val rbUnaDailySelected =
                binding.root.findViewById<RadioButton>(rg_unaDaily.checkedRadioButtonId).text.toString()
            val rbGuildSupportSelected =
                binding.root.findViewById<RadioButton>(rg_guildSupport.checkedRadioButtonId).text.toString()

            // Weekly
            val rbUnaWeeklySelected =
                binding.root.findViewById<RadioButton>(rg_unaWeekly.checkedRadioButtonId).text.toString()
            val rbLegionRaidsSelected =
                binding.root.findViewById<RadioButton>(rg_legionRaids.checkedRadioButtonId).text.toString()
            val rbAbyssDungeonsSelected =
                binding.root.findViewById<RadioButton>(rg_abyssDungeons.checkedRadioButtonId).text.toString()
            val rbBloodstonesSelected =
                binding.root.findViewById<RadioButton>(rg_bloodstones.checkedRadioButtonId).text.toString()
            val rbContributionSelected =
                binding.root.findViewById<RadioButton>(rg_contribution.checkedRadioButtonId).text.toString()

            Log.d("SelectedOption", "Chaos Dungeon Option: $rbChaosDungeonSelected")
            Log.d("SelectedOption", "Guardian Raid Option: $rbGuardianRaidSelected")
            Log.d("SelectedOption", "Una Daily Option: $rbUnaDailySelected")
            Log.d("SelectedOption", "Guild Support Option: $rbGuildSupportSelected")
            Log.d("SelectedOption", "Una Weekly Option: $rbUnaWeeklySelected")
            Log.d("SelectedOption", "Legion Raids Option: $rbLegionRaidsSelected")
            Log.d("SelectedOption", "Abyss Dungeons Option: $rbAbyssDungeonsSelected")
            Log.d("SelectedOption", "Bloodstones Option: $rbBloodstonesSelected")
            Log.d("SelectedOption", "Contribution Option: $rbContributionSelected")


            val character = Character(
                name = name,
                ascendancy = ascendancy,
                imageUri = image,
                itemLevel = itemLevel,
                enableChaos = rbChaosDungeonSelected,
                enableGuardian = rbGuardianRaidSelected,
                enableUnaDaily = rbUnaDailySelected,
                enableGuildSupport = rbGuildSupportSelected,
                enableUnaWeekly = rbUnaWeeklySelected,
                enableLegionRaids = rbLegionRaidsSelected,
                enableAbyssDungeons = rbAbyssDungeonsSelected,
                enableGuildBloodstones = rbBloodstonesSelected,
                enableGuildContribution = rbContributionSelected,
                )

            lifecycleScope.launch {
                characterViewModel.insertCharacter(character)
            }

            et_characterName.text.clear()
            et_characterItemLevel.text.clear()

            StyleableToast.makeText(
                requireContext(),
                getString(R.string.s_toastSavedData),
                R.style.customToast
            ).show()


        } else {
            if (et_characterName.text.isEmpty()) {
                etl_characterName.error = "Enter a character name"
            }
            if (binding.autoCompleteTextView.text.isEmpty()) {
                etl_characterAscendancy.error = "Select character class"
            }
            if (et_characterItemLevel.text.isEmpty()) {
                etl_characterItemLevel.error = "Enter an item level"
            }
        }


    }
}