package com.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Book {

    private String id;

    private String name;

    @NotNull
    private Author author;

}
