package com.cion318.arktracker.data.character

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.cion318.arktracker.R
import com.cion318.arktracker.data.Constants
import java.util.concurrent.TimeUnit

class ViewPagerAdapter(private var characters: List<Character>) :
    RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        val itemLayoutChaosDungeon: RelativeLayout = itemView.findViewById(R.id.layout_chaosDungeon)
        val itemLayoutGuardianRaid: RelativeLayout = itemView.findViewById(R.id.layout_guardianRaid)
        val itemLayoutUnaDaily: RelativeLayout = itemView.findViewById(R.id.layout_unaDaily)
        val itemLayoutGuildSupport: RelativeLayout = itemView.findViewById(R.id.layout_guildSupport)
        val itemLayoutUnaWeekly: RelativeLayout = itemView.findViewById(R.id.layout_unaWeekly)
        val itemLayoutGuildBloodstones: RelativeLayout = itemView.findViewById(R.id.layout_guildBloodstones)
        val itemLayoutGuildContribution: RelativeLayout = itemView.findViewById(R.id.layout_guildContribution)
        val itemLayoutValtan: RelativeLayout = itemView.findViewById(R.id.layout_valtan)
        val itemLayoutVykas: RelativeLayout = itemView.findViewById(R.id.layout_vykas)
        val itemLayoutKakul: RelativeLayout = itemView.findViewById(R.id.layout_kakul)
        val itemLayoutBrelshaza: RelativeLayout = itemView.findViewById(R.id.layout_brelshaza)
        val itemLayoutKayangel: RelativeLayout = itemView.findViewById(R.id.layout_kayangel)
        val itemLayoutAkkan: RelativeLayout = itemView.findViewById(R.id.layout_akkan)
        val itemLayoutIvory: RelativeLayout = itemView.findViewById(R.id.layout_ivory)
        val itemLayoutThaemine: RelativeLayout = itemView.findViewById(R.id.layout_thaemine)
        val itemLayoutEchidna: RelativeLayout = itemView.findViewById(R.id.layout_echidna)

        val itemSeparatorDaily: LinearLayout = itemView.findViewById(R.id.separator_daily)
        val itemSeparatorWeekly: LinearLayout = itemView.findViewById(R.id.separator_weekly)
        val itemSeparatorWeeklyRaids: LinearLayout = itemView.findViewById(R.id.separator_weeklyRaids)

        val itemChaosDungeonSet: Button = itemView.findViewById(R.id.btn_setChaosDungeon)
        val itemChaosDungeonReset: ImageButton = itemView.findViewById(R.id.btn_resetChaosDungeon)
        val itemGuardianRaidSet: Button = itemView.findViewById(R.id.btn_setGuardianRaid)
        val itemGuardianRaidReset: ImageButton = itemView.findViewById(R.id.btn_resetGuardianRaid)
        val itemUnaDailySet: Button = itemView.findViewById(R.id.btn_setUnaDaily)
        val itemUnaDailyReset: ImageButton = itemView.findViewById(R.id.btn_resetUnaDaily)
        val itemGuildSupportSet: Button = itemView.findViewById(R.id.btn_setGuildSupport)
        val itemGuildSupportReset: ImageButton = itemView.findViewById(R.id.btn_resetGuildSupport)
        val itemUnaWeeklySet: Button = itemView.findViewById(R.id.btn_setUnaWeekly)
        val itemUnaWeeklyReset: ImageButton = itemView.findViewById(R.id.btn_resetUnaWeekly)
        val itemGuildBloodstonesSet: Button = itemView.findViewById(R.id.btn_setGuildBloodstones)
        val itemGuildBloodstonesReset: ImageButton = itemView.findViewById(R.id.btn_resetGuildBloodstones)
        val itemGuildContributionSet: Button = itemView.findViewById(R.id.btn_setGuildContribution)
        val itemGuildContributionReset: ImageButton = itemView.findViewById(R.id.btn_resetGuildContribution)
        val itemValtanSet: Button = itemView.findViewById(R.id.btn_setValtan)
        val itemValtanReset: ImageButton = itemView.findViewById(R.id.btn_resetValtan)
        val itemVykasSet: Button = itemView.findViewById(R.id.btn_setVykas)
        val itemVykasReset: ImageButton = itemView.findViewById(R.id.btn_resetVykas)
        val itemKakulSet: Button = itemView.findViewById(R.id.btn_setKakul)
        val itemKakulReset: ImageButton = itemView.findViewById(R.id.btn_resetKakul)
        val itemBrelshazaSet: Button = itemView.findViewById(R.id.btn_setBrelshaza)
        val itemBrelshazaReset: ImageButton = itemView.findViewById(R.id.btn_resetBrelshaza)
        val itemKayangelSet: Button = itemView.findViewById(R.id.btn_setKayangel)
        val itemKayangelReset: ImageButton = itemView.findViewById(R.id.btn_resetKayangel)
        val itemAkkanSet: Button = itemView.findViewById(R.id.btn_setAkkan)
        val itemAkkanReset: ImageButton = itemView.findViewById(R.id.btn_resetAkkan)
        val itemIvorySet: Button = itemView.findViewById(R.id.btn_setIvory)
        val itemIvoryReset: ImageButton = itemView.findViewById(R.id.btn_resetIvory)
        val itemThaemineSet: Button = itemView.findViewById(R.id.btn_setThaemine)
        val itemThaemineReset: ImageButton = itemView.findViewById(R.id.btn_resetThaemine)
        val itemEchidnaSet: Button = itemView.findViewById(R.id.btn_setEchidna)
        val itemEchidnaReset: ImageButton = itemView.findViewById(R.id.btn_resetEchidna)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {

        // Title Text and Image
        "${characters[position].name} [${characters[position].itemLevel}]".also {
            holder.itemTitle.text = it
        }

        var imageId: Int = R.drawable.ic_lostark
        when (characters[position].ascendancy) {
            "Berserker" -> { imageId = R.drawable.resized_ascendancy_berserker }
            "Destroyer" -> { imageId = R.drawable.resized_ascendancy_destroyer }
            "Gunlancer" -> { imageId = R.drawable.resized_ascendancy_gunlancer }
            "Paladin" -> { imageId = R.drawable.resized_ascendancy_paladin }
            "Slayer" -> { imageId = R.drawable.resized_ascendancy_slayer }

            "Arcanist" -> { imageId = R.drawable.resized_ascendancy_arcanist }
            "Bard" -> { imageId = R.drawable.resized_ascendancy_bard }
            "Sorceress" -> { imageId = R.drawable.resized_ascendancy_sorceress }
            "Summoner" -> { imageId = R.drawable.resized_ascendancy_summoner }

            "Breaker" -> { imageId = R.drawable.ic_lostark }
            "Glaivier" -> { imageId = R.drawable.resized_ascendancy_glaivier }
            "Scrapper" -> { imageId = R.drawable.resized_ascendancy_scrapper }
            "Soulfist" -> { imageId = R.drawable.resized_ascendancy_soulfist }
            "Striker" -> { imageId = R.drawable.resized_ascendancy_striker }
            "Wardancer" -> { imageId = R.drawable.resized_ascendancy_wardancer }

            "Artillerist" -> { imageId = R.drawable.resized_ascendancy_artillerist }
            "Deadeye" -> { imageId = R.drawable.resized_ascendancy_deadeye }
            "Gunslinger" -> { imageId = R.drawable.resized_ascendancy_gunslinger }
            "Machinist" -> { imageId = R.drawable.resized_ascendancy_machinist }
            "Sharpshooter" -> { imageId = R.drawable.resized_ascendancy_sharpshooter }

            "Deathblade" -> { imageId = R.drawable.resized_ascendancy_deathblade }
            "Reaper" -> { imageId = R.drawable.resized_ascendancy_reaper }
            "Shadowhunter" -> { imageId = R.drawable.resized_ascendancy_shadowhunter }
            "Souleater" -> { imageId = R.drawable.resized_ascendancy_souleater }

            "Aeromancer" -> { imageId = R.drawable.resized_ascendancy_aeromancer }
            "Artist" -> { imageId = R.drawable.resized_ascendancy_artist }
        }
        holder.itemTitle.setCompoundDrawablesWithIntrinsicBounds(imageId, 0, 0, 0)


        // Setup visibility of daily items
        if (characters[position].enableChaos == "Yes") {
            holder.itemLayoutChaosDungeon.visibility = View.VISIBLE
            "${characters[position].chaosDungeonCounter}/${Constants.MAX_CHAOS_DUNGEON}".also {
                holder.itemChaosDungeonSet.text = it
            }
        }
        if (characters[position].enableGuardian == "Yes") {
            holder.itemLayoutGuardianRaid.visibility = View.VISIBLE
            "${characters[position].guardianCounter}/${Constants.MAX_GUARDIAN_RAID}".also {
                holder.itemGuardianRaidSet.text = it
            }
        }
        if (characters[position].enableUnaDaily == "Yes") {
            holder.itemLayoutUnaDaily.visibility = View.VISIBLE
            "${characters[position].unaDailyCounter}/${Constants.MAX_UNA_DAILY}".also {
                holder.itemUnaDailySet.text = it
            }
        }
        if (characters[position].enableGuildSupport == "Yes") {
            holder.itemLayoutGuildSupport.visibility = View.VISIBLE
            "${characters[position].guildSupportCounter}/${Constants.MAX_GUILD_SUPPORT}".also {
                holder.itemGuildSupportSet.text = it
            }
        }
        if (characters[position].enableChaos == "No" &&
            characters[position].enableGuardian == "No" &&
            characters[position].enableUnaDaily == "No" &&
            characters[position].enableGuildSupport == "No"
        ) {
            holder.itemSeparatorDaily.visibility = View.GONE
        }

        // Setup visibility of weekly items
        if (characters[position].enableUnaWeekly == "Yes") {
            holder.itemLayoutUnaWeekly.visibility = View.VISIBLE
            "${characters[position].unaWeeklyCounter}/${Constants.MAX_UNA_WEEKLY}".also {
                holder.itemUnaWeeklySet.text = it
            }
        }
        if (characters[position].enableGuildBloodstones == "Yes") {
            holder.itemLayoutGuildBloodstones.visibility = View.VISIBLE
            "${characters[position].guildBloodstonesCounter}/${Constants.MAX_GUILD_BLOODSTONES}".also {
                holder.itemGuildBloodstonesSet.text = it
            }
        }
        if (characters[position].enableGuildContribution == "Yes") {
            holder.itemLayoutGuildContribution.visibility = View.VISIBLE
            "${characters[position].guildContributionCounter}/${Constants.MAX_GUILD_CONTRIBUTION}".also {
                holder.itemGuildContributionSet.text = it
            }
        }
        if (characters[position].enableUnaWeekly == "No" &&
            characters[position].enableGuildBloodstones == "No" &&
            characters[position].enableGuildContribution == "No"
        ) {
            holder.itemSeparatorWeekly.visibility = View.GONE
        }


        // Setup visibility of weekly raid items
        if (characters[position].enableLegionRaids == "Yes") {
            if (characters[position].itemLevel < 1445) {
                holder.itemSeparatorWeeklyRaids.visibility = View.GONE
            } else {
                if (characters[position].itemLevel >= 1445) {
                    holder.itemLayoutValtan.visibility = View.VISIBLE
                    "${characters[position].valtanCounter}/${Constants.MAX_VALTAN}".also {
                        holder.itemValtanSet.text = it
                    }
                }
                if (characters[position].itemLevel >= 1460) {
                    holder.itemLayoutVykas.visibility = View.VISIBLE
                    "${characters[position].vykasCounter}/${Constants.MAX_VYKAS}".also {
                        holder.itemVykasSet.text = it
                    }
                }
                if (characters[position].itemLevel >= 1475) {
                    holder.itemLayoutKakul.visibility = View.VISIBLE
                    "${characters[position].kakulCounter}/${Constants.MAX_KAKUL}".also {
                        holder.itemKakulSet.text = it
                    }
                }
                if (characters[position].itemLevel >= 1490) {
                    holder.itemLayoutBrelshaza.visibility = View.VISIBLE
                    "${characters[position].brelshazaCounter}/${Constants.MAX_BRELSHAZA}".also {
                        holder.itemBrelshazaSet.text = it
                    }
                }
                if (characters[position].itemLevel >= 1580) {
                    holder.itemLayoutAkkan.visibility = View.VISIBLE
                    "${characters[position].akkanCounter}/${Constants.MAX_AKKAN}".also {
                        holder.itemAkkanSet.text = it
                    }
                }
                if (characters[position].itemLevel >= 1610) {
                    holder.itemLayoutThaemine.visibility = View.VISIBLE
                    "${characters[position].thaemineCounter}/${Constants.MAX_THAEMINE}".also {
                        holder.itemThaemineSet.text = it
                    }
                }
                if (characters[position].itemLevel >= 1620) {
                    holder.itemLayoutEchidna.visibility = View.VISIBLE
                    "${characters[position].echidnaCounter}/${Constants.MAX_ECHIDNA}".also {
                        holder.itemEchidnaSet.text = it
                    }
                }
            }
        }

        if (characters[position].enableAbyssDungeons == "Yes") {
            if (characters[position].itemLevel < 1445) {
                holder.itemSeparatorWeeklyRaids.visibility = View.GONE
            } else {
                if (characters[position].itemLevel >= 1540) {
                    holder.itemLayoutKayangel.visibility = View.VISIBLE
                    "${characters[position].kayangelCounter}/${Constants.MAX_KAYANGEL}".also {
                        holder.itemKayangelSet.text = it
                    }
                }
                if (characters[position].itemLevel >= 1600) {
                    holder.itemLayoutIvory.visibility = View.VISIBLE
                    "${characters[position].ivoryCounter}/${Constants.MAX_IVORY}".also {
                        holder.itemIvorySet.text = it
                    }
                }
            }
        }

        if (characters[position].enableLegionRaids == "No" &&
            characters[position].enableAbyssDungeons == "No"
        ) {
            holder.itemSeparatorWeeklyRaids.visibility = View.GONE
        }

        // Setup button click listeners

    }


    private fun applyButtonListener(button: Button, view: View, layoutId: Int, characters: Array<String>, position: Int) {
        button.setOnClickListener {
            val selectedString = characters[position]
            if (selectedString == "3 Days") {
                val designatedRelativeLayout: RelativeLayout = view.findViewById(layoutId)
                designatedRelativeLayout.visibility = View.GONE
                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
                val visibilityChangeRequest = OneTimeWorkRequestBuilder<VisibilityChangeWorker>()
                    .setInitialDelay(20, TimeUnit.SECONDS)
                    .setConstraints(constraints)
                    .setInputData(workDataOf("layoutId" to layoutId))
                    .build()
                WorkManager.getInstance(view.context).enqueue(visibilityChangeRequest)
            }
        }
    }
}

class VisibilityChangeWorker(context: Context, params: WorkerParameters, private val view: View, private val layoutId: Int) : Worker(context, params) {
    override fun doWork(): Result {
        val designatedRelativeLayout: RelativeLayout = view.findViewById(layoutId)
        designatedRelativeLayout.visibility = View.VISIBLE
        return Result.success()
    }
}