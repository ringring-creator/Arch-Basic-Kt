package com.ring.ring.todo

abstract class UseCase<Req : UseCase.Req, Res : UseCase.Res> {
    abstract suspend fun execute(req: Req): Res

    suspend operator fun invoke(req: Req): Res {
        return execute(req)
    }

    interface Req
    interface Res
}
