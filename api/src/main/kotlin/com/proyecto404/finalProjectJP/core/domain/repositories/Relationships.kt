package com.proyecto404.finalProjectJP.core.domain.repositories

import com.proyecto404.finalProjectJP.core.domain.Relationship

interface Relationships {
    fun add(relationship: Relationship)
    fun getFollowings(followings: String): List<String>
    fun getFollowers(followers: String): List<String>
    fun remove(relationship: Relationship)
}

fun RepositoryProvider.relationships() = this.get<Relationships>()
