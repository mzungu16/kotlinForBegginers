package com.gleb.kotlinforbegginers

class CreditsDTO(
    val cast: List<CreditsCardDTO?>
)

data class CreditsCardDTO(
    val name: String?,
    val profile_path: String?
)