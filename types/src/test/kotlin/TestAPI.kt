import gg.skytils.hypixel.types.player.Player
import gg.skytils.hypixel.types.skyblock.Profile
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.util.*

/*
 * Skytils - Hypixel Skyblock Quality of Life Mod
 * Copyright (C) 2020-2024 Skytils
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

object TestAPI {
    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            include(serializersModule)
            contextual(UUID::class, UUIDAsString)
        }
    }
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }
    private const val baseUrl = "hypixel.skytils.gg"

    suspend fun getSkyblockProfiles(uuid: UUID) =
        client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = baseUrl
                path("v2", "skyblock", "profiles")
                parameter("uuid", uuid.toString())
            }
        }.body<ProfileResponse>().profiles

    suspend fun getSelectedSkyblockProfile(uuid: UUID) =
        getSkyblockProfiles(uuid)?.find { it.selected }

    fun getSelectedSkyblockProfileSync(uuid: UUID) =
        runBlocking {
            getSelectedSkyblockProfile(uuid)
        }

    suspend fun getPlayer(uuid: UUID) =
        client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = baseUrl
                path("v2", "player")
                parameter("uuid", uuid.toString())
            }
        }.body<PlayerResponse>().player

    fun getPlayerSync(uuid: UUID) =
        runBlocking {
            getPlayer(uuid)
        }
}

object UUIDAsString : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID =
        UUID.fromString(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: UUID) {
        TODO("Not yet implemented")
    }

}

@Serializable
sealed class HypixelResponse {
    abstract val success: Boolean
    val cause: String? = null
}

@Serializable
data class ProfileResponse(
    override val success: Boolean,
    val profiles: List<Profile>? = null
) : HypixelResponse()

@Serializable
data class PlayerResponse(
    override val success: Boolean,
    val player: Player
) : HypixelResponse()