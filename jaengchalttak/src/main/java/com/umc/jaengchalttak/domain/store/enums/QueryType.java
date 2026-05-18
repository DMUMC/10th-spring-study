package com.umc.jaengchalttak.domain.store.enums;

import lombok.Getter;

@Getter
public enum QueryType {
    ID("id"),
    STAR("reviewStar");

    private final String name;

    QueryType(String name) {
        this.name = name;
    }
}
