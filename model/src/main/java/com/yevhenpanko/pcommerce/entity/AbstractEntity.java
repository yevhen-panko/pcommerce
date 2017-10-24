package com.yevhenpanko.pcommerce.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public abstract class AbstractEntity {
    private static final String INCREMENT = "increment";

    @Id
    @GeneratedValue(strategy = IDENTITY, generator = INCREMENT)
    @GenericGenerator(name = INCREMENT, strategy = INCREMENT)
    @Column(name = "ID", nullable = false)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
