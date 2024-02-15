package gg.skytils.hypixel.types.skyblock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
data class Member(
    val player_data: PlayerData = PlayerData(),
    //TODO: Rift
    @SerialName("accessory_bag_storage")
    val accessory_data: AccessoryBagData = AccessoryBagData(),
    val dungeons: DungeonsData? = null,
    val player_stats: PlayerStats = PlayerStats(),
    val inventory: InventoryData = InventoryData(),
    val pets_data: PetsData = PetsData(),
    val slayer: SlayerData = SlayerData(),
    @SerialName("jacobs_contests")
    val jacob: JacobData = JacobData(),
    val trophy_fish: TrophyFishData = TrophyFishData(),
    val item_data: ItemData = ItemData()
)

@Serializable
data class PlayerData(
    val visited_zones: List<String> = emptyList(),
    val last_death: Long = 0L,
    val perks: Map<String, Double> = emptyMap(),
    val active_effects: List<PotionEffect> = emptyList(),
    val paused_effects: List<PotionEffect> = emptyList(),
    val temp_stat_buffs: JsonElement = JsonObject(emptyMap()), //FIXME
    val death_count: Double = 0.0,
    val disabled_potion_effects: List<String> = emptyList(),
    val visited_modes: List<String> = emptyList(),
    val unlocked_coll_tiers: List<String> = emptyList(),
    val crafted_generators: List<String> = emptyList(),
    val fastest_target_practice: Double = 0.0,
    val fishing_treasure_caught: Double = 0.0,
    val experience: Map<String, Double> = emptyMap()
)

@Serializable
data class PotionEffect(
    @SerialName("effect")
    val name: String,
    val level: Double,
    val modifiers: List<PotionModifier> = emptyList(),
    val ticks_remaining: Long = 0,
    val infinite: Boolean = false
) {
    @Serializable
    data class PotionModifier(
        @SerialName("key")
        val name: String,
        val amp: Double
    )
}

@Serializable
data class AccessoryBagData(
    val tuning: JsonObject = JsonObject(emptyMap()),
    val selected_power: String? = null,
    val unlocked_powers: List<String> = emptyList(),
    val highest_magical_power: Double = 0.0,
    val bag_upgrades_purchased: Double = 0.0
)

//TODO: Finish other stuff
@Serializable
data class PlayerStats(
    val kills: Map<String, Double> = emptyMap(),
    val deaths: Map<String, Double> = emptyMap(),
    val highest_critical_damage: Double = 0.0,
    val items_fished: Map<String, Double> = emptyMap(),
    val auctionStats: AuctionStats = AuctionStats()
)

@Serializable
data class ItemData(
    @SerialName("favorite_arrow")
    val favoriteArrow: String = "",
)

@Serializable
data class AuctionStats(
    val bids: Double = 0.0,
    val highest_bid: Double = 0.0,
    val won: Double = 0.0,
    val total_bought: Map<String, Double> = emptyMap(),
    val gold_spent: Double = 0.0,
    val created: Double = 0.0,
    val fees: Double = 0.0,
    val completed: Double = 0.0,
    val total_sold: Map<String, Double> = emptyMap(),
    val gold_earned: Double = 0.0,
    val no_bods: Double = 0.0
)