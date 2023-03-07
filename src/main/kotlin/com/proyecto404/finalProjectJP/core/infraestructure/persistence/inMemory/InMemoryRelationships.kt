package com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory

import com.proyecto404.finalProjectJP.core.domain.Relationship
import com.proyecto404.finalProjectJP.core.domain.Relationships

class InMemoryRelationships: Relationships {
    private val relationships = mutableMapOf<String, MutableSet<String>>()

    override fun add(relationship: Relationship) {
        val follower = relationship.follower
        val following = relationship.following
        val userFollowings = relationships[follower.name] ?: mutableSetOf()
        userFollowings.add(following.name)
        relationships[follower.name] = userFollowings
    }

    override fun getFollowings(followings: String): List<String> {
        return relationships[followings]?.toList() ?: emptyList()
    }

    override fun getFollowers(follower: String): List<String> {
        return relationships.filter { it.value.contains(follower) }.keys.toList()
    }

    override fun remove(relationship: Relationship) {
        val follower = relationship.follower
        val following = relationship.following
        val userFollowings = relationships[follower.name] ?: mutableSetOf()
        userFollowings.remove(following.name)
        relationships[follower.name] = userFollowings
    }
}
