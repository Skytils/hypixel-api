/*
 * Skytils - Hypixel Skyblock Quality of Life Mod
 * Copyright (C) 2020-2023 Skytils
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package gg.skytils.hypixel.types.skyblock

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class SlayerData(
    val slayer_quest: SlayerQuestData? = null,
    val slayer_bosses: Map<String, SlayerBossData> = emptyMap()
)

@Serializable
data class SlayerQuestData(
    val type: String,
    val tier: Double,
    val start_timestamp: Long = 0L,
    val combat_xp: Double,
    val recent_mob_kills: List<SlayerKill> = emptyList()
)

@Serializable
data class SlayerKill(
    val xp: Double,
    val timestamp: Long
)

@Serializable
data class SlayerBossData(
    val claimed_levels: Map<String, Boolean> = emptyMap(),
    @Serializable(with = BossKillsSerializer::class)
    val boss_kills: Map<String, Double> = emptyMap(),
    val xp: Double = 0.0
)

object BossKillsSerializer : KSerializer<Map<String, Double>> {
    override val descriptor = MapSerializer(String.serializer(), Double.serializer()).descriptor

    override fun deserialize(decoder: Decoder): Map<String, Double> =
        (decoder as? JsonDecoder)?.decodeJsonElement()?.jsonObject?.entries?.filter { (key, _) ->
            key.startsWith("boss_kills_tier_")
        }?.associate { (key, value) ->
            key to value.jsonPrimitive.double
        } ?: error("Only json supported")

    override fun serialize(encoder: Encoder, value: Map<String, Double>) =
        TODO("Not yet implemented")
}