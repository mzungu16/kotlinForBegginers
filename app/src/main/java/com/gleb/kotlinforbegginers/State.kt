package com.gleb.kotlinforbegginers

sealed class State {
    data class Success(val card: Any) : State()
    data class Error(val error: Throwable) : State()
    object Loading : State()
}
