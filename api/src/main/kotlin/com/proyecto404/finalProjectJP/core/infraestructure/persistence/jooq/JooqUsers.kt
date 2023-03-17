package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import org.example.final_project_jp.infrastructure.jooq.generated.Sequences
import org.example.final_project_jp.infrastructure.jooq.generated.Tables
import org.example.final_project_jp.infrastructure.jooq.generated.Tables.USERS
import org.jooq.impl.DSL

class JooqUsers(private val credentials: Credentials): Users {
    private fun context() = DSL.using(credentials.url, credentials.user, credentials.password)

    override fun add(user: User) {
        //TODO: solucionar el tema del id, cuando se crea un objeto deberia usarse el repositorio para obtener el id
        val snapshot = user.snapshot().also { it.id = nextId() }
        context()
            .insertInto(USERS, USERS.ID, Tables.USERS.NAME, Tables.USERS.PASSWORD)
            .values(snapshot.id, snapshot.name, snapshot.password)
            .execute()
    }

    override fun get(userName: String): User {
        return context()
            .selectFrom(Tables.USERS)
            .where(Tables.USERS.NAME.eq(userName))
            .fetch()
            .map { User.from(User.UserSnapshot(it.id, it.name, it.password, it.sessionToken)) }
            .single()
    }

    override fun delete(userName: String) {
        TODO()
    }

    override fun update(user: User) {
        context()
            .update(Tables.USERS)
            .set(Tables.USERS.SESSION_TOKEN, user.snapshot().sessionToken)
            .where(USERS.ID.eq(user.snapshot().id))
            .execute()
    }

    override fun nextId() = context().nextval(Sequences.USERS_ID_SEQ)
}
