package com.ring.ring.session.login

import com.ring.ring.session.shared.SharedModules

internal object LoginModules {
    val getUserRepository = createGetUserRepository()
    val saveSessionRepository = createSaveSessionRepository()

    private fun createGetUserRepository(): ExistUserRepository = ExistUserRepository(
        httpClient = SharedModules.httpClient,
    )

    private fun createSaveSessionRepository(): SaveSessionRepository = SaveSessionRepository(
        queries = SharedModules.db.sessionQueries
    )
}