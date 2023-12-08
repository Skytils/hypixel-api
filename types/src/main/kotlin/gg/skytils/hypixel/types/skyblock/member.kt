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
    val dungeons: DungeonsData
)

@Serializable
class PlayerData(
    val visited_zones: List<String>,
    val last_death: Long,
    val perks: Map<String, Int> = emptyMap(),
    val active_effects: List<PotionEffect>,
    val paused_effects: List<PotionEffect>,
    val temp_stat_buffs: JsonElement, //FIXME
    val death_count: Int,
    val disabled_potion_effects: List<String>,
    val visited_modes: List<String>,
    val unlocked_coll_tiers: List<String>,
    val crafted_generators: List<String>,
    val fastest_target_practice: Double,
    val fishing_treasure_caught: Int,
    val experience: Map<String, Double>,
)

@Serializable
class PotionEffect(
    @SerialName("effect")
    val name: String,
    val level: Int,
    val modifiers: List<PotionModifier> = emptyList(),
    val ticks_remaining: Long = 0,
    val infinite: Boolean
) {
    @Serializable
    class PotionModifier(
        val name: String,
        val amp: Int
    )
}

@Serializable
class AccessoryBagData(
    val tuning: JsonObject,
    val selected_power: String? = null,
    val unlocked_powers: List<String> = emptyList(),
    val highest_magical_power: Int = 0,
    val bag_upgrades_purchased: Int = 0
)