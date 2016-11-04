package com.zt.foos.model;

import io.realm.RealmObject;

public class Player extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
