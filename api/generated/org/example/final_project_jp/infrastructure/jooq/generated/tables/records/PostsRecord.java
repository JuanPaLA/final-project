/*
 * This file is generated by jOOQ.
 */
package org.example.final_project_jp.infrastructure.jooq.generated.tables.records;


import java.time.LocalDateTime;

import org.example.final_project_jp.infrastructure.jooq.generated.tables.Posts;
import org.example.final_project_jp.infrastructure.jooq.generated.tables.pojos.PostsDto;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostsRecord extends UpdatableRecordImpl<PostsRecord> implements Record4<Integer, String, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.posts.id</code>.
     */
    public PostsRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.posts.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.posts.userid</code>.
     */
    public PostsRecord setUserid(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.posts.userid</code>.
     */
    public String getUserid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.posts.content</code>.
     */
    public PostsRecord setContent(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.posts.content</code>.
     */
    public String getContent() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.posts.date</code>.
     */
    public PostsRecord setDate(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.posts.date</code>.
     */
    public LocalDateTime getDate() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, LocalDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Posts.POSTS.ID;
    }

    @Override
    public Field<String> field2() {
        return Posts.POSTS.USERID;
    }

    @Override
    public Field<String> field3() {
        return Posts.POSTS.CONTENT;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Posts.POSTS.DATE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUserid();
    }

    @Override
    public String component3() {
        return getContent();
    }

    @Override
    public LocalDateTime component4() {
        return getDate();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUserid();
    }

    @Override
    public String value3() {
        return getContent();
    }

    @Override
    public LocalDateTime value4() {
        return getDate();
    }

    @Override
    public PostsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public PostsRecord value2(String value) {
        setUserid(value);
        return this;
    }

    @Override
    public PostsRecord value3(String value) {
        setContent(value);
        return this;
    }

    @Override
    public PostsRecord value4(LocalDateTime value) {
        setDate(value);
        return this;
    }

    @Override
    public PostsRecord values(Integer value1, String value2, String value3, LocalDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PostsRecord
     */
    public PostsRecord() {
        super(Posts.POSTS);
    }

    /**
     * Create a detached, initialised PostsRecord
     */
    public PostsRecord(Integer id, String userid, String content, LocalDateTime date) {
        super(Posts.POSTS);

        setId(id);
        setUserid(userid);
        setContent(content);
        setDate(date);
    }

    /**
     * Create a detached, initialised PostsRecord
     */
    public PostsRecord(PostsDto value) {
        super(Posts.POSTS);

        if (value != null) {
            setId(value.getId());
            setUserid(value.getUserid());
            setContent(value.getContent());
            setDate(value.getDate());
        }
    }
}