package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.proyecto404.finalProjectJP.core.domain.Relationship
import com.proyecto404.finalProjectJP.core.domain.repositories.Relationships

class JooqRelationships(credentials: Credentials): Relationships {
    override fun add(relationship: Relationship) {
        TODO("Not yet implemented")
    }

    override fun getFollowings(followings: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun getFollowers(followers: String): List<String> {
        TODO("Not yet implemented")
    }

    override fun remove(relationship: Relationship) {
        TODO("Not yet implemented")
    }
}
