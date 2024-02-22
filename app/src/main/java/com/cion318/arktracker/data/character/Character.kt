package com.cion318.arktracker.data.character

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cion318.arktracker.data.Converters

@Entity(tableName = "characters")
@TypeConverters(Converters::class)
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "ascendancy") val ascendancy: String,
    @ColumnInfo(name = "imageUri") val imageUri: Uri,
    @ColumnInfo(name = "item_level") val itemLevel: Int,

    // Daily Chaos Dungeons
    @ColumnInfo(name = "enable_chaos") val enableChaos: String,
    @ColumnInfo(name = "chaos_counter") val chaosDungeonCounter: Int = 0,

    // Daily Guardians
    @ColumnInfo(name = "enable_guardian") val enableGuardian: String,
    @ColumnInfo(name = "guardian_counter") val guardianCounter: Int = 0,

    // Daily Una's Tasks
    @ColumnInfo(name = "enable_una_daily") val enableUnaDaily: String,
    @ColumnInfo(name = "una_daily_counter") val unaDailyCounter: Int = 0,

    // Daily Guild Support
    @ColumnInfo(name = "enable_guild_support") val enableGuildSupport: String,
    @ColumnInfo(name = "guild_support_counter") val guildSupportCounter: Int = 0,

    // Weekly Una's Tasks
    @ColumnInfo(name = "enable_una_weekly") val enableUnaWeekly: String,
    @ColumnInfo(name = "una_weekly_counter") val unaWeeklyCounter: Int = 0,

    // Weekly Legion Raids
    @ColumnInfo(name = "enable_legion_raids") val enableLegionRaids: String,
    @ColumnInfo(name = "valtan_counter") val valtanCounter: Int = 0,
    @ColumnInfo(name = "vykas_counter") val vykasCounter: Int = 0,
    @ColumnInfo(name = "kakul_counter") val kakulCounter: Int = 0,
    @ColumnInfo(name = "brelshaza_counter") val brelshazaCounter: Int = 0,
    @ColumnInfo(name = "akkan_counter") val akkanCounter: Int = 0,
    @ColumnInfo(name = "thaemine_counter") val thaemineCounter: Int = 0,
    @ColumnInfo(name = "echidna_counter") val echidnaCounter: Int = 0,

    // Weekly Abyss Dungeons
    @ColumnInfo(name = "enable_abyss_dungeons") val enableAbyssDungeons: String,
    @ColumnInfo(name = "kayangel_counter") val kayangelCounter: Int = 0,
    @ColumnInfo(name = "ivory_counter") val ivoryCounter: Int = 0,

    // Weekly Guild Bloodstones
    @ColumnInfo(name = "enable_guild_bloodstones") val enableGuildBloodstones: String,
    @ColumnInfo(name = "guild_bloodstones_counter") val guildBloodstonesCounter: Int = 0,

    // Weekly Guild Contribution
    @ColumnInfo(name = "enable_guild_contribution") val enableGuildContribution: String,
    @ColumnInfo(name = "guild_contribution_counter") val guildContributionCounter: Int = 0

)
