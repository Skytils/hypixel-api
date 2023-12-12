package gg.skytils.hypixel.types.skyblock

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Profile(
    @Contextual
    @SerialName("profile_id")
    val id: UUID,
    @SerialName("cute_name")
    val name: String,
    val selected: Boolean,
    val community_upgrades: UpgradeData = UpgradeData(null),
    val members: Map<String, Member>
)

@Serializable
class UpgradeData(
    val currently_upgrading: UpgradeInProgress? = null,
    val upgrade_states: List<ProfileUpgrade> = emptyList()
)

@Serializable
class UpgradeInProgress(
    @SerialName("upgrade")
    val name: String,
    val new_tier: Int,
    @SerialName("start_ms")
    val started: Long,
    @SerialName("who_started")
    val started_by: String
)

@Serializable
class ProfileUpgrade(
    @SerialName("upgrade")
    val name: String,
    val tier: Int,
    @SerialName("started_ms")
    val started: Long,
    val started_by: String,
    @SerialName("claimed_ms")
    val claimed: Long,
    val claimed_by: String,
    val fasttracked: Boolean
)

@Serializable
class BankData(
    val balance: Double,
    val transactions: List<BankTransaction>
)

@Serializable
class BankTransaction(
    val amount: Int,
    val timestamp: Long,
    val action: String,
    @SerialName("initiator_name")
    val initiator: String // for some reason this is the color formatted name, no clue why
)