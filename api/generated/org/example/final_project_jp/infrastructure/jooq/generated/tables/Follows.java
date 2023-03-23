/*
 * This file is generated by jOOQ.
 */
package org.example.final_project_jp.infrastructure.jooq.generated.tables;


import java.util.Arrays;
import java.util.List;

import org.example.final_project_jp.infrastructure.jooq.generated.Indexes;
import org.example.final_project_jp.infrastructure.jooq.generated.Keys;
import org.example.final_project_jp.infrastructure.jooq.generated.Public;
import org.example.final_project_jp.infrastructure.jooq.generated.tables.records.FollowsRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Follows extends TableImpl<FollowsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.follows</code>
     */
    public static final Follows FOLLOWS = new Follows();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FollowsRecord> getRecordType() {
        return FollowsRecord.class;
    }

    /**
     * The column <code>public.follows.id</code>.
     */
    public final TableField<FollowsRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.follows.followee</code>.
     */
    public final TableField<FollowsRecord, String> FOLLOWEE = createField(DSL.name("followee"), SQLDataType.VARCHAR(16).nullable(false), this, "");

    /**
     * The column <code>public.follows.follower</code>.
     */
    public final TableField<FollowsRecord, String> FOLLOWER = createField(DSL.name("follower"), SQLDataType.VARCHAR(16).nullable(false), this, "");

    private Follows(Name alias, Table<FollowsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Follows(Name alias, Table<FollowsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.follows</code> table reference
     */
    public Follows(String alias) {
        this(DSL.name(alias), FOLLOWS);
    }

    /**
     * Create an aliased <code>public.follows</code> table reference
     */
    public Follows(Name alias) {
        this(alias, FOLLOWS);
    }

    /**
     * Create a <code>public.follows</code> table reference
     */
    public Follows() {
        this(DSL.name("follows"), null);
    }

    public <O extends Record> Follows(Table<O> child, ForeignKey<O, FollowsRecord> key) {
        super(child, key, FOLLOWS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.FOLLOWS_ID_UQ);
    }

    @Override
    public Identity<FollowsRecord, Integer> getIdentity() {
        return (Identity<FollowsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<FollowsRecord> getPrimaryKey() {
        return Keys.FOLLOWS_PKEY;
    }

    @Override
    public Follows as(String alias) {
        return new Follows(DSL.name(alias), this);
    }

    @Override
    public Follows as(Name alias) {
        return new Follows(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Follows rename(String name) {
        return new Follows(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Follows rename(Name name) {
        return new Follows(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}