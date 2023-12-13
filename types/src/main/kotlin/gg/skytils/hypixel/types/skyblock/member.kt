package gg.skytils.hypixel.types.skyblock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
class Member(
    val player_data: PlayerData,
    //TODO: Rift
    @SerialName("accessory_bag_storage")
    val accessory_data: AccessoryBagData,
    val dungeons: DungeonsData,
    val player_stats: PlayerStats
)

@Serializable
class PlayerData(
    val visited_zones: List<String>,
    val last_death: Long,
    val perks: Map<String, Double> = emptyMap(),
    val active_effects: List<PotionEffect>,
    val paused_effects: List<PotionEffect>,
    val temp_stat_buffs: JsonElement, //FIXME
    val death_count: Double,
    val disabled_potion_effects: List<String>,
    val visited_modes: List<String>,
    val unlocked_coll_tiers: List<String>,
    val crafted_generators: List<String>,
    val fastest_target_practice: Double,
    val fishing_treasure_caught: Double,
    val experience: Map<String, Double>,
)

@Serializable
class PotionEffect(
    @SerialName("effect")
    val name: String,
    val level: Double,
    val modifiers: List<PotionModifier> = emptyList(),
    val ticks_remaining: Long = 0,
    val infinite: Boolean
) {
    @Serializable
    class PotionModifier(
        val name: String,
        val amp: Double
    )
}

@Serializable
class AccessoryBagData(
    val tuning: JsonObject,
    val selected_power: String? = null,
    val unlocked_powers: List<String> = emptyList(),
    val highest_magical_power: Double = 0.0,
    val bag_upgrades_purchased: Double = 0.0
)

//TODO: Finish other stuff
@Serializable
class PlayerStats(
    val kills: Map<String, Double>,
    val deaths: Map<String, Double>,
    val highest_critical_damage: Double,
    val items_fished: Map<String, Double>,
    val auctionStats: AuctionStats
)

@Serializable
class AuctionStats(
    val bids: Double,
    val highest_bid: Double,
    val won: Double,
    val total_bought: Map<String, Double>,
    val gold_spent: Double,
    val created: Double,
    val fees: Double,
    val completed: Double,
    val total_sold: Map<String, Double>,
    val gold_earned: Double,
    val no_bods: Double
)