package de.bybackfish.telosapi.struct

import de.bybackfish.telosapi.TelosAPI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class PlayerResponse(
    val player: PlayerInfo,
    val data: PlayerData,
)

@Serializable
data class PlayerInfo(
    val name: String,
    val id: String
)

@Serializable
data class PlayerData(
    val uniqueId: String,
    val username: String,
    val currentServer: String,
    val lastPlayed: Long,
    val rank: String,
    val balance: Double,
    val boost: Boost,
    val guild: Guild,
    val sticker: String?,
    val companions: Companions,
    val quests: Quests,
    val rewards: List<String>,
    val characters: Characters,
    val glow: String? = null,
    val visibility: Visibility,
    val stash: Stash,
    val statistics: Map<String, Double>,
    val store: Map<String, String>,
    @SerialName("playTime") val playtime: Duration
) {
    fun fetchGuild(api: TelosAPI): GuildResponse? {
        return this.guild.fetch(api)
    }
}

@Serializable
data class Boost(
    val fame: Double,
    val loot: Double
)

@Serializable
data class Guild(
    val guild: String? = null,
    val invites: Map<String, String> = mapOf()
) {
    fun fetch(api: TelosAPI): GuildResponse? {
        return guild?.let { api.getGuild(it) }
    }
}

@Serializable
data class Companions(
    val selected: CompanionSelection,
    val unlocked: List<String>
)

@Serializable
data class CompanionSelection(
    val pet: String,
    val mount: String
)

@Serializable
data class Quests(
    val progress: Map<String, QuestProgress>,
    val questlines: Map<String, QuestlineInfo>
)

@Serializable
data class QuestProgress(
    val limit: Int,
    val value: Int
)

@Serializable
data class QuestlineInfo(
    val id: String,
    val tree: QuestTree?
)

@Serializable
data class QuestTree(
    val id: String,
    val children: List<QuestTree>
)

@Serializable
data class Characters(
    val selected: String,
    val slots: Int,
    val all: List<CharacterDetails>,
    val data: CharacterData
)

@Serializable
data class CharacterDetails(
    val uniqueId: String,
    val created: Long,
    val lastPlayed: Long,
    @SerialName("playTime") val playtime: String,
    val type: String,
    val ruleset: Ruleset,
    val stats: CharacterStats,
    val fame: Int,
    val highestFame: Int,
    val level: Int,
    val itemFilter: ItemFilter,
    val runes: List<String>,
    val inventory: Inventory,
    val previousLife: PreviousLife?,
    val backpack: Backpack?,
    val potions: Int,
    val statistics: Map<String, Double>,
    val store: Map<String, Double>
)

@Serializable
data class PreviousLife(
    val cause: String,
    val timestamp: Long,
    val data: PreviousLifeData
)

@Serializable
data class PreviousLifeData(
    val uniqueId: String,
    val created: Long,
    val lastPlayed: Long,
    val playTime: String,
    val type: String,
    val ruleset: Ruleset,
    val stats: CharacterStats,
    val fame: Int,
    val highestFame: Int,
    val level: Int,
    val itemFilter: ItemFilter,
    val runes: List<String>,
    val inventory: Inventory,
    val previousLife: PreviousLife?,
    val backpack: Backpack?,
    val potions: Int,
    val statistics: Map<String, Double>,
    val store: Map<String, String>
)

@Serializable
data class Ruleset(
    val type: String
)

@Serializable
data class CharacterStats(
    val values: StatValues,
    val restorable: StatValues
)

@Serializable
data class StatValues(
    @SerialName("HEALTH")
    val health: Int,
    @SerialName("ATTACK")
    val attack: Int,
    @SerialName("DEFENSE")
    val defense: Int,
    @SerialName("SPEED")
    val speed: Int,
    @SerialName("EVASION")
    val evasion: Int,
    @SerialName("VITALITY")
    val vitality: Int,
    @SerialName("CRITCHANCE")
    val critChance: Int,
)

@Serializable
data class ItemFilter(
    val components: List<FilterComponent>
)

@Serializable
data class FilterComponent(
    val type: String,
    val cutoff: Int? = null,
    val whitelist: List<String>? = null,
    val allowNormal: Boolean? = null,
    val allowGreater: Boolean? = null
)

@Serializable
data class Inventory(
    val armor: List<InventoryItem>,
    val extra: List<InventoryItem>,
    val storage: List<InventoryItem>,
    val selectedSlot: Int
)

@Serializable
data class Backpack(
    val contents: List<InventoryItem>
)

@Serializable
data class InventoryItem(
    val type: String,
    val identifier: String? = null,
    val id: String? = null,
    val owner: String? = null,
    val features: List<ItemFeature>? = listOf()
)

@Serializable
data class ItemFeature(
    val type: String
)

@Serializable
data class CharacterData(
    val mastery: Map<String, Boolean>
)

@Serializable
data class Visibility(
    val pets: Boolean,
    val mounts: Boolean,
    val others: Boolean,
    val stickers: Boolean,
    val hidden: Boolean
)

@Serializable
data class Stash(
    val size: Int,
    val contents: List<List<InventoryItem>>
)
