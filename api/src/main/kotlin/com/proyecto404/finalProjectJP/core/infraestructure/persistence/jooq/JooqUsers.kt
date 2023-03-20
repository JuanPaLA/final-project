package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import org.example.final_project_jp.infrastructure.jooq.generated.Sequences
import org.example.final_project_jp.infrastructure.jooq.generated.Tables.USERS
import org.jooq.impl.DSL

class JooqUsers(private val credentials: Credentials): Users {
    private fun context() = DSL.using(credentials.url, credentials.user, credentials.password)

    override fun add(user: User) {
        val snapshot = user.snapshot().also { it.id = nextId() }
        context()
            .insertInto(USERS, USERS.ID, USERS.NAME, USERS.PASSWORD)
            .values(snapshot.id, snapshot.name, snapshot.password)
            .execute()
    }

    override fun get(userName: String): User {
        return context()
            .selectFrom(USERS)
            .where(USERS.NAME.eq(userName))
            .fetch()
            .map { User.from(User.UserSnapshot(it.id, it.name, it.password, it.sessionToken)) }
            .single()
    }

    override fun get(): List<User> {
        return context()
            .selectFrom(USERS)
            .fetch()
            .map { User.from(User.UserSnapshot(it.id, it.name, it.password, it.sessionToken)) }
    }

    override fun delete(userName: String) {
        context()
            .deleteFrom(USERS)
            .where(USERS.NAME.eq(userName))
            .execute()
    }

    override fun update(user: User) {
        context()
            .update(USERS)
            .set(USERS.SESSION_TOKEN, user.snapshot().sessionToken)
            .where(USERS.ID.eq(user.snapshot().id))
            .execute()
    }

    override fun nextId() = context().nextval(Sequences.USERS_ID_SEQ)
}
