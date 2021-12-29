package com.gleb.kotlinforbegginers

class CreditsDTO(
    val cast: List<CastDTO?>
)

data class CastDTO(
    val name: String?,
    val profile_path: String?
)