/*
 * This file is generated by jOOQ.
 */
package org.example.final_project_jp.infrastructure.jooq.generated;


import java.util.Arrays;
import java.util.List;

import org.example.final_project_jp.infrastructure.jooq.generated.tables.FlywaySchemaHistory;
import org.example.final_project_jp.infrastructure.jooq.generated.tables.Follows;
import org.example.final_project_jp.infrastructure.jooq.generated.tables.Posts;
import org.example.final_project_jp.infrastructure.jooq.generated.tables.Users;
import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.follows</code>.
     */
    public final Follows FOLLOWS = Follows.FOLLOWS;

    /**
     * The table <code>public.posts</code>.
     */
    public final Posts POSTS = Posts.POSTS;

    /**
     * The table <code>public.users</code>.
     */
    public final Users USERS = Users.USERS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.asList(
            Sequences.FOLLOWS_ID_SEQ,
            Sequences.POSTS_ID_SEQ,
            Sequences.USERS_ID_SEQ
        );
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            Follows.FOLLOWS,
            Posts.POSTS,
            Users.USERS
        );
    }
}
