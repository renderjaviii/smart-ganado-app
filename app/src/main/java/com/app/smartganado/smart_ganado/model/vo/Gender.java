package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;


public class Gender implements Serializable {



    private Integer id;

    private String name;

    public Gender( String name) {
        this.name = name;
    }

    public Gender() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}