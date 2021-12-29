package com.gleb.kotlinforbegginers

sealed class State {
    data class Success(val filmCards:List<FactDTO?>) : State()
    data class SuccessToCredits(val creditCard:List<CastDTO?>) : State()
}

