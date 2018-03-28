package ru.spring.api.bean

import ru.spring.api.utils.NoArgConstructor
import java.io.Serializable

@NoArgConstructor
data class KeyUserAuthority(
        val userId: Long,
        val authority: String
) : Serializable