package de.bybackfish.telosapi

import de.bybackfish.telosapi.struct.GuildResponse
import de.bybackfish.telosapi.struct.PlayerResponse
import kotlinx.serialization.json.Json
import java.net.URL
import java.util.concurrent.CompletableFuture

class TelosAPI(private val url: String = "https://api.telosrealms.com", val useCache: Boolean = true, val cacheTime: Long = 1000 * 60 * 30) {

    data class CacheObject<T>(val data: T, val timestamp: Long)

    private val cache = mutableMapOf<String, CacheObject<*>>()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private inline fun <reified T> get(path: String): T? {
        if (useCache) {
            val cached = cache[path]
            if (cached != null && cached.timestamp + cacheTime > System.currentTimeMillis()) {
                println("Cache hit for $path")
                return cached.data as T
            }
        }

        val url = URL("$url/$path")

        runCatching {
            val json: T = json.decodeFromString(url.readText())

            if (useCache) {
                cache[path] = CacheObject(json, System.currentTimeMillis())
            }

            return json
        } .onFailure {
            println("Error: ${it.message}")
        }

        return null
    }

    fun getPlayer(username: String): PlayerResponse? {
        return get("lookup/player/$username")
    }

    fun getGuild(guild: String): GuildResponse? {
        return get("lookup/guild/$guild")
    }

    fun getPlayerFuture(username: String): CompletableFuture<PlayerResponse?> {
        return CompletableFuture.supplyAsync {
            getPlayer(username)
        }
    }

    fun getGuildFuture(guild: String): CompletableFuture<GuildResponse?> {
        return CompletableFuture.supplyAsync {
            getGuild(guild)
        }
    }
}