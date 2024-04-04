package com.ring.ring.validateSession.delete

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

internal class DeleteCacheController(
    private val deleteCache: DeleteCache = DeleteCache(),
    private val deleteCacheById: DeleteCacheById = DeleteCacheById(),
) {
    suspend fun delete(call: ApplicationCall) {
        val req = call.receive<DeleteCache.ReqSession>()
        deleteCache(req)
        call.respond(HttpStatusCode.OK)
    }

    suspend fun deleteById(call: ApplicationCall) {
        val req = call.receive<DeleteCacheById.ReqSession>()
        deleteCacheById(req)
        call.respond(HttpStatusCode.OK)
    }
}