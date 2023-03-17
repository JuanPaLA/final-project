package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.proyecto404.finalProjectJP.core.domain.repositories.Posts
import com.proyecto404.finalProjectJP.core.domain.repositories.Relationships
import com.proyecto404.finalProjectJP.core.domain.repositories.RepositoryProvider
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import kotlin.reflect.KClass

class JooqRepositoryProvider: RepositoryProvider() {
    private val credentials = Credentials.fromEnv()

    override fun <T : Any> create(repositoryType: KClass<T>): T {
        return when(repositoryType) {
            Users::class -> JooqUsers(credentials)
            Posts::class -> JooqPosts(credentials)
            Relationships::class -> JooqRelationships(credentials)
            else -> throw NotImplementedError("")
        } as T
    }
}
