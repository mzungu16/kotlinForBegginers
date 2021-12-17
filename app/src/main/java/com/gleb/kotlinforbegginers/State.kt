package com.gleb.kotlinforbegginers

sealed class State {
    data class Success(val filmCards:List<FilmCard>) : State()
}

