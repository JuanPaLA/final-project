package com.proyecto404.finalProjectJP.core.domain

interface Relationships {
    fun add(relationship: Relationship)
    fun getFollowings(followings: String): List<String>
    fun getFollowers(followers: String): List<String>
    fun remove(relationship: Relationship)
}
