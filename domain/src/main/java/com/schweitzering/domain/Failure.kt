package com.schweitzering.domain

sealed class ActionResponse {
    object Correct: ActionResponse()
    object UnknownError: ActionResponse()
    object ServerError: ActionResponse()
    object DatabaseError: ActionResponse()
    object NetworkConnectionError: ActionResponse()
    object CannotDeleteError: ActionResponse()
    object NotEmptyAccountError: ActionResponse()
    object NotEnoughMoney: ActionResponse()
}