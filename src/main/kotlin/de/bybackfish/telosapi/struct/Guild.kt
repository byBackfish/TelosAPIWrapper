package de.bybackfish.telosapi.struct

import kotlinx.serialization.Serializable

@Serializable
data class GuildResponse(
    val guild: String,
    val data: GuildData
)

@Serializable
data class GuildData(
    val uniqueId: String,
    val name: String,
    val tier: Int,
    val balance: Double,
    val vault: List<VaultItem>,
    val audit: List<AuditEntry>,
    val members: Map<String, String>
)

@Serializable
data class VaultItem(
    val contents: List<VaultContent>,
    val viewer: String?
)

@Serializable
data class VaultContent(
    val identifier: String,
    val id: String,
    val owner: String,
    val features: List<String>
)

@Serializable
data class AuditEntry(
    val type: String,
    val source: PlayerReference? = null,
    val invited: PlayerReference? = null,
    val cause: String? = null,
    val amount: Double? = null,
    val timestamp: Long,
    val reason: String? = null,
    val tier: Int? = null,
    val destination: AuditEntryDestination? = null
)

@Serializable
data class PlayerReference(
    val type: String,
    val uniqueId: String,
    val server: String? = null
)

@Serializable
data class AuditEntryDestination(
    val type: String? = null,
    val uniqueId: String,
    val server: String? = null
)
