@file:Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")

package com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory

import com.proyecto404.finalProjectJP.core.domain.repositories.Posts
import com.proyecto404.finalProjectJP.core.domain.repositories.Relationships
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.repositories.RepositoryProvider
import kotlin.reflect.KClass

class InMemoryRepositoryProvider: RepositoryProvider() {
    override fun <T : Any> create(repositoryType: KClass<T>): T {
        return when(repositoryType) {
            Users::class -> InMemoryUsers()
            Posts::class -> InMemoryPosts()
            Relationships::class -> InMemoryRelationships()
            else -> NotImplementedError("InMemory${repositoryType.simpleName} not implemented.")
        } as T
    }
}
