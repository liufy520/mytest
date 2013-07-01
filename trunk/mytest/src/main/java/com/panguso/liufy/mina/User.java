package com.panguso.liufy.mina;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 3662999696659260807L;

    private String            name;
    private Long              id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", id=" + id + "]";
    }

}
